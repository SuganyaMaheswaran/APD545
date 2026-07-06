package application.controller;

import application.AppInitializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainDashboardController {

    @FXML private BorderPane mainLayout;
    @FXML private VBox contentArea;
    @FXML private Button btnAddLoan;
    @FXML private Button btnViewCustomers;
    @FXML private Button btnTools;
    @FXML private Button btnLogout;
    @FXML private VBox sideMenu;
    @FXML private Button btnToggle;
    private boolean isMenuVisible = true;
    private AppInitializer app;

    public void setAppInitializer(AppInitializer app) {
        this.app = app;
    }


    @FXML
    private void toggleMenu() {
        if (isMenuVisible) {
            // Collapse
            sideMenu.setManaged(false);
            sideMenu.setVisible(false);
            sideMenu.setPrefWidth(0);
            btnToggle.setText("→"); 
        } else {
            // Expand
            sideMenu.setManaged(true);
            sideMenu.setVisible(true);
            sideMenu.setPrefWidth(154.0);
            btnToggle.setText("←"); 
        }
        isMenuVisible = !isMenuVisible;
    }
    /**
     * Swaps the center content of the BorderPane with a new View.
     */
    public void setCenterView(Node view) {
        mainLayout.setCenter(view);
    }

    @FXML
    void handleNav(ActionEvent event) {
        Button source = (Button) event.getSource();

        if (source == btnAddLoan) {
            System.out.println("Loading Add Loan/Customer View...");
            if (app != null) {
                setCenterView(app.loadLoanView());
            }
        } else if (source == btnViewCustomers) {
            System.out.println("Loading View Customers...");
            if (app != null) {
                // Call the AppInitializer to get the Customers View and set it in the center
                setCenterView(app.loadViewCustomersView());
            }
        } 
    }
    @FXML
    void handleLogout(ActionEvent event) {
        if (app != null) {
            app.showLoginView();
        }
    }
}