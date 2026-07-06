package application.controller;

import application.model.AmortizationEntry;
import application.model.Loan;
import application.service.AmortizationService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.NumberFormat;
import java.util.List;

public class LoanAmortizationController {

    @FXML private TableView<AmortizationEntry> tblAmortization;
    @FXML private TableColumn<AmortizationEntry, Number> colMonth, colPrincipal, colInterest, colBalance;
    
    private AmortizationService amortizationService;
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    /**
     * Injects the required AmortizationService and initializes the table columns.
     * This follows the Manual Dependency Injection pattern used in the AppInitializer.
     * 
     * @param service The service responsible for calculating the loan schedule.
     */
    public void setDependencies(AmortizationService service) {
        this.amortizationService = service;
        setupTable();
    }

    /**
     * Configures the TableView columns to map to the properties of the AmortizationEntry model.
     * Also applies custom cell factories for currency formatting.
     */
    private void setupTable() {
        colMonth.setCellValueFactory(data -> data.getValue().monthProperty());
        
        colPrincipal.setCellValueFactory(data -> data.getValue().principalProperty());
        colPrincipal.setCellFactory(col -> createCurrencyCell());
        
        colInterest.setCellValueFactory(data -> data.getValue().interestProperty());
        colInterest.setCellFactory(col -> createCurrencyCell());
        
        colBalance.setCellValueFactory(data -> data.getValue().balanceProperty());
        colBalance.setCellFactory(col -> createCurrencyCell());
    }
    
    /**
     * Retrieves the generated amortization schedule for a given loan.
     * This allows the parent controller to perform calculations on the data.
     */
    public List<AmortizationEntry> getSchedule(Loan loan) {
        if (amortizationService != null) {
            return amortizationService.generateSchedule(loan);
        }
        return new java.util.ArrayList<>(); // Return empty list if service is missing
    }

    /**
     * Creates a custom TableCell to format numeric values as currency (e.g., $1,234.56).
     * 
     * @return A TableCell formatted with the NumberFormat currency instance.
     */
    private TableCell<AmortizationEntry, Number> createCurrencyCell() {
        return new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : currencyFormat.format(item.doubleValue()));
            }
        };
    }
    
    /**
     * Generates and displays the loan amortization schedule in the TableView.
     * 
     * @param loan The loan object containing principal, interest, and term details.
     */
    public void displaySchedule(Loan loan) {
        System.out.println("Displaying schedule..."); 
        if (amortizationService != null) {
            // Generate the list of entries using the injected service
            List<AmortizationEntry> schedule = amortizationService.generateSchedule(loan);
            // Populate the TableView
            tblAmortization.getItems().setAll(schedule);
            System.out.println("Items set: " + schedule.size()); 
        } else {
            System.err.println("ERROR: amortizationService is null!");
        }
    }

    /**
     * Clears all rows from the amortization table, used typically when a user clicks 'Clear'.
     */
    public void clearTable() {
        if (tblAmortization != null) {
            tblAmortization.getItems().clear();
        }
    }
}