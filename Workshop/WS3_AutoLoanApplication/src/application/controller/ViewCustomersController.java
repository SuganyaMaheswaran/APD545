package application.controller;

import java.text.NumberFormat;

import application.AppInitializer;
import application.model.Loan;
import application.repository.LoanRepository;
import application.util.AppAlert;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewCustomersController {

    @FXML private TableView<Loan> tvSavedLoans;
    @FXML private TableColumn<Loan, String> colName;
    @FXML private TableColumn<Loan, String> colPhoneNumber;
    @FXML private TableColumn<Loan, Double> colLoanAmount; 
    @FXML private TableColumn<Loan, String> colVehicleType;
    @FXML private TableColumn<Loan, Double> colMonthlyPayment; 
    @FXML private TableColumn<Loan, Double> colTotalRepayment; 
    @FXML private TableColumn<Loan, String> colInterestRate;   
    

    private AppInitializer app;
    private LoanRepository loanRepository;

    /**
     * Manual Dependency Injection: Wiring the repository and app initializer.
     */
    public void setDependencies(AppInitializer app, LoanRepository loanRepository) {
        this.app = app;
        this.loanRepository = loanRepository;
        
        // Create the currency formatter
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        // Mapping...
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colPhoneNumber.setCellValueFactory(cellData -> 
        new SimpleStringProperty(formatPhoneNumber(cellData.getValue().getPhoneNumber())));
        // Currency Columns
        colLoanAmount.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLoanAmount()));
        colLoanAmount.setCellFactory(col -> createCurrencyCell(currencyFormat));
        
        colMonthlyPayment.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().calculateMonthlyPayment()));
        colMonthlyPayment.setCellFactory(col -> createCurrencyCell(currencyFormat));
        
        colTotalRepayment.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().calculateMonthlyPayment() * cellData.getValue().monthsProperty().get()));
        colTotalRepayment.setCellFactory(col -> createCurrencyCell(currencyFormat));
        
        // String Columns
        colVehicleType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVehicleType()));
        colInterestRate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().interestRateProperty().get() + "%"));
        colName.setStyle("-fx-alignment: CENTER-LEFT;");
        colPhoneNumber.setStyle("-fx-alignment: CENTER;");
        colLoanAmount.setStyle("-fx-alignment: CENTER-RIGHT;");
        colMonthlyPayment.setStyle("-fx-alignment: CENTER-RIGHT;");
        colTotalRepayment.setStyle("-fx-alignment: CENTER-RIGHT;");
        colInterestRate.setStyle("-fx-alignment: CENTER;");
        colVehicleType.setStyle("-fx-alignment: CENTER;");
        colName.getStyleClass().add("align-left");
        colPhoneNumber.getStyleClass().add("align-center");
        colLoanAmount.getStyleClass().add("align-right");
        colMonthlyPayment.getStyleClass().add("align-right");
        colTotalRepayment.getStyleClass().add("align-right");
        colInterestRate.getStyleClass().add("align-center");
        colVehicleType.getStyleClass().add("align-center");
        if (this.loanRepository != null) {
            tvSavedLoans.setItems(this.loanRepository.getAllLoans());
        }
    }
    
    private String formatPhoneNumber(String phone) {
        if (phone == null || phone.length() != 10) {
            return phone; // Return as-is if it's not a standard 10-digit number
        }
        return String.format("(%s)-%s-%s", 
                phone.substring(0, 3), 
                phone.substring(3, 6), 
                phone.substring(6, 10));
    }
    
    private TableCell<Loan, Double> createCurrencyCell(NumberFormat format) {
        return new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(format.format(item));
                }
            }
        };
    }
    @FXML
    void handleLoadSelected() {
        Loan selectedLoan = tvSavedLoans.getSelectionModel().getSelectedItem();
        
        if (selectedLoan == null) {
        	// Updated to use the AppAlert utility
            AppAlert.showError("Selection Required", "Please select a customer from the list.");
            return;
        }

        if (app != null) {
            app.loadLoanViewWithData(selectedLoan);
        }
    }
}