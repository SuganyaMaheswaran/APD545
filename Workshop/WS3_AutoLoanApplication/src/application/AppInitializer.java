package application;

import java.io.IOException;

import application.controller.*;
import application.model.Loan;
import application.repository.*;
import application.service.AmortizationService;
import application.service.FixedRateLoan;
import application.service.LoanCalculation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    private Stage primaryStage;
    
    // Instantiate Repositories here for Manual DI
    private final UserRepository userRepository = new UserRepository();
    private final LoanRepository loanRepository = new LoanRepository();
    private final LoanCalculation loanCalculation = new FixedRateLoan();
    private final AmortizationService amortizationService = new AmortizationService();
    
    @Override
    public void start(Stage primaryStage) {
    	loanRepository.seedDatabase();
        
    	this.primaryStage = primaryStage;
        
        this.primaryStage.setTitle("Seneca Auto Loan");
        showWelcomeView();
    }

    // --- Navigation Methods ---

    public void showWelcomeView() {
        loadView("/application/view/WelcomeView.fxml", controller -> {
            ((WelcomeController) controller).setAppInitializer(this);
        });
    }

    public void showSignupView() {
        loadView("/application/view/SignUpView.fxml", controller -> {
            ((SignupController) controller).setDependencies(this, userRepository);
        });
    }

    public void showLoginView() {
        loadView("/application/view/LoginView.fxml", controller -> {
            ((LoginController) controller).setDependencies(this, userRepository);
        });
    }
    
    public void showDashboardView() {
        loadView("/application/view/MainDashboardView.fxml", controller -> {
            ((MainDashboardController) controller).setAppInitializer(this);
        });
    }
    
    public Parent loadLoanView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/LoanView.fxml"));
            Parent root = loader.load();
            LoanController controller = loader.getController();
            controller.setLoanRepository(this.loanRepository);
            controller.setLoanCalculation(this.loanCalculation);
            controller.setAmortizationService(this.amortizationService);
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Loads the View Customers FXML to be placed in the center of the Main Dashboard.
     */
    public Parent loadViewCustomersView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/ViewCustomers.fxml"));
            Parent root = loader.load();
            
            // Inject the shared LoanRepository into the ViewCustomersController
            ViewCustomersController controller = loader.getController();
            controller.setDependencies(this, loanRepository); 
            
            return root;
        } catch (Exception e) {
            System.err.println("CRITICAL ERROR: Could not find ViewCustomers.fxml.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Loads the Loan form and pre-fills it with a previously saved loan.
     */
    public void loadLoanViewWithData(Loan savedLoan) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/LoanView.fxml"));
            Parent root = loader.load();
            LoanController controller = loader.getController();
            
            // Inject all dependencies
            controller.setLoanRepository(this.loanRepository);
            controller.setLoanCalculation(this.loanCalculation);
            controller.setAmortizationService(this.amortizationService);
            controller.loadSavedLoan(savedLoan); 
            
            if (primaryStage.getScene().getRoot() instanceof javafx.scene.layout.BorderPane) {
                javafx.scene.layout.BorderPane dashboard = (javafx.scene.layout.BorderPane) primaryStage.getScene().getRoot();
                dashboard.setCenter(root);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Centralized helper to load FXML and inject dependencies.
     */
    private void loadView(String fxmlPath, java.util.function.Consumer<Object> controllerInitializer) {
        try {
            java.net.URL url = getClass().getResource(fxmlPath);
            System.out.println(url);
            if (url == null) {
                throw new Exception("FXML file not found at path: " + fxmlPath);
            }
            
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load(); 
            
            if (controllerInitializer != null) {
                controllerInitializer.accept(loader.getController());
            }
            
            // Create the Scene
            Scene scene = new Scene(root);
            
            // Load the CSS file and add it to the Scene
            try {
                String css = getClass().getResource("/application/application.css").toExternalForm();
                scene.getStylesheets().add(css);
            } catch (NullPointerException e) {
                System.err.println("WARNING: Could not find styles.css! Make sure it is in the 'application' package.");
            }
            
            // Set the scene ONCE
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}