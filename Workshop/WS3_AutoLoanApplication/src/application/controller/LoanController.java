package application.controller;

import java.util.List;

import application.model.*;
import application.repository.LoanRepository;
import application.service.AmortizationService;
import application.service.LoanCalculation;
import application.util.AppAlert;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import application.service.LoanCalculation;

public class LoanController {

    // UI Fields
    @FXML private TextField txtName, txtPhone, txtCity, txtPrice, txtDownPayment;
    @FXML private ComboBox<String> cmbProvince, cmbInterest;
    @FXML private ComboBox<PaymentFrequency> cmbFrequency;
    @FXML private RadioButton rbCar, rbTruck, rbVan, rbNew, rbUsed;
    @FXML private Slider sldMonths;
    @FXML private TextArea txtLoanDetails;
    @FXML private ToggleGroup vehicleTypeGroup, conditionGroup;
    @FXML private ListView<Loan> lvSavedLoans;

    // Injected dependencies
    @FXML private TableView<AmortizationEntry> tblAmortization;
    private LoanRepository loanRepository;
    private LoanCalculation loanCalculation;
    private Loan currentEditingLoan = null;
    @FXML private LoanAmortizationController amorIncludeController;
    @FXML private VBox amorView;
    public LoanController() {};

    // Dependency Injection Setters
    public void setLoanRepository(LoanRepository loanRepository) { this.loanRepository = loanRepository; }
    public void setLoanCalculation(LoanCalculation loanCalculation) {
        this.loanCalculation = loanCalculation;
    }

    @FXML
    public void initialize() {
        cmbProvince.getItems().addAll("ON", "QC", "BC", "AB");
        cmbInterest.getItems().addAll("0.99%", "1.99%", "2.99%","3.99");
        cmbFrequency.getItems().addAll(PaymentFrequency.values());
        cmbFrequency.setValue(PaymentFrequency.MONTHLY);

        if (lvSavedLoans != null && loanRepository != null) {
            lvSavedLoans.setItems(loanRepository.getAllLoans());
            lvSavedLoans.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> { if (newVal != null) loadSavedLoan(newVal); }
            );
        }
        populateDefaultData();
    }
    @FXML
    void handleCalculate() {
        if (!validateInputs()) return;

        Loan loan = buildLoanModel();

        double monthlyPayment = loanCalculation.calculatePayment(loan);

        // Get the schedule to perform summary calculations
        List<AmortizationEntry> schedule = amorIncludeController.getSchedule(loan);
        
        // Calculate totals from the schedule
        double totalInterest = 0.0;
        for (AmortizationEntry entry : schedule) {
            totalInterest += entry.interestProperty().get();;
        }
        
        double principal = loan.getLoanAmount();
        double totalCost = principal + totalInterest;
        int numberOfTerms = schedule.size();

        // Update the TextArea with the requested financial summary
        txtLoanDetails.setText(String.format(
            "Loan Amount: $%,.2f\n" +
            "Monthly Payment: $%,.2f\n" +
            "Number of Terms: %d\n" +
            "Total Interest Paid: $%,.2f\n" +
            "Total Cost (Principal + Interest): $%,.2f",
            principal, 
            monthlyPayment, 
            numberOfTerms, 
            totalInterest, 
            totalCost
        ));

        // Display the amortization table
        if (amorIncludeController != null) {
            amorIncludeController.displaySchedule(loan);
        } else {
            System.err.println("ERROR: amortizationController is null!");
        }
    }
    
    
    @FXML
    void handleClear() {
        this.currentEditingLoan = null;
        txtName.clear();
        txtPhone.clear();
        txtCity.clear();
        txtPrice.clear();
        txtDownPayment.clear();
        cmbProvince.getSelectionModel().clearSelection();
        cmbInterest.getSelectionModel().clearSelection();
        cmbFrequency.getSelectionModel().clearSelection();
        vehicleTypeGroup.selectToggle(null);
        conditionGroup.selectToggle(null);
        sldMonths.setValue(sldMonths.getMin());
        txtLoanDetails.clear();

        if (amorIncludeController != null) {
        	amorIncludeController.clearTable();
        }
        txtName.requestFocus();
    }

    @FXML
    void handleSaveToDatabase() {
        if (!validateInputs()) return;
        Loan newLoan = buildLoanModel();
        if (loanRepository != null) {
            if (currentEditingLoan == null) {
                loanRepository.saveLoan(newLoan);
                // Success alert for NEW save
                AppAlert.showInfo("Success", "Loan saved successfully!");
            } else {
                loanRepository.updateLoan(currentEditingLoan, newLoan);
                currentEditingLoan = newLoan;
                // Success alert for UPDATE
                AppAlert.showInfo("Success", "Loan updated successfully!");
            }
            
            if (lvSavedLoans != null) lvSavedLoans.refresh();
        }
    }

    private Loan buildLoanModel() {
        Customer customer = new Customer();
        customer.setName(txtName.getText().trim());
        customer.setPhone(txtPhone.getText().trim());
        customer.setCity(txtCity.getText().trim());
        customer.setProvince(cmbProvince.getValue());

        VehicleModel vehicle = new VehicleModel();
        vehicle.setPrice(Double.parseDouble(txtPrice.getText().trim()));

        double price = Double.parseDouble(txtPrice.getText().trim());
        double down = Double.parseDouble(txtDownPayment.getText().trim());
        double interest = Double.parseDouble(cmbInterest.getValue().replace("%", "").trim());
        int months = (int) sldMonths.getValue();
        PaymentFrequency frequency = cmbFrequency.getValue();

        return new Loan(price, down, interest, months, frequency, customer, vehicle);
    }
    
    public void setAmortizationService(AmortizationService service) {
      
        if (amorIncludeController != null) {
            amorIncludeController.setDependencies(service);
        } else {
            System.err.println("amorIncludeController is null. Check your fx:id in LoanView.fxml!");
        }
    }
    public void loadSavedLoan(Loan savedLoan) {
        this.currentEditingLoan = savedLoan;
        Customer customer = savedLoan.getCustomer();
        txtName.setText(customer.getName());
        txtPhone.setText(customer.getPhone());
        txtCity.setText(customer.getCity());
        cmbProvince.setValue(customer.getProvince());
        txtPrice.setText(String.valueOf(savedLoan.priceProperty().get()));
        txtDownPayment.setText(String.valueOf(savedLoan.downPaymentProperty().get()));
        cmbInterest.setValue(savedLoan.interestRateProperty().get() + "%");
        cmbFrequency.setValue(savedLoan.frequencyProperty().get());
        sldMonths.setValue(savedLoan.monthsProperty().get());
        handleCalculate();
    }
        /**
         * Populates the form with default values when the view is first loaded.
         */
        private void populateDefaultData() {
        
            // Set a default price and down payment for convenience
        	
        	cmbProvince.setValue("ON");
        	
        	
            txtPrice.setText("30000.00");
            txtDownPayment.setText("5000.00");
            
            // Select a default frequency and interest rate
            cmbInterest.getSelectionModel().select(1); // Selects "1.99%"
            cmbFrequency.setValue(PaymentFrequency.MONTHLY);
            
            // Reset slider to a middle-ground default (e.g., 48 months)
            sldMonths.setValue(48);
        }
        
        
        
    private void showError(String msg) {
        AppAlert.showError("Application Error", msg);
    }
    
    
    /**
     * Validates that all required fields are filled and contain valid numeric data.
     * @return true if inputs are valid, false otherwise.
     */

    private boolean validateInputs() {
        try {
            // 1. Validate Name (Not empty)
            if (txtName.getText().trim().isEmpty()) {
                showError("Name is required.");
                return false;
            }

            // 2. Validate Phone (Must be exactly 10 digits)
            String phone = txtPhone.getText().trim();
            if (!phone.matches("\\d{10}")) {
                showError("Phone number must be exactly 10 digits.");
                return false;
            }

            // 3. Validate City (Must be only letters)
            if (!txtCity.getText().trim().matches("^[a-zA-Z\\s]+$")) {
                showError("City must contain only letters.");
                return false;
            }

            // 4. Validate Province (Must be selected)
            if (cmbProvince.getValue() == null) {
                showError("Please select a province.");
                return false;
            }

            // 5. Validate Numeric Fields
            double price = Double.parseDouble(txtPrice.getText().trim());
            double down = Double.parseDouble(txtDownPayment.getText().trim());

            if (price <= 0) {
                showError("Vehicle price must be a positive number greater than 0.");
                return false;
            }
            
            if (down < 0) {
                showError("Down payment cannot be negative.");
                return false;
            }

            if (down >= price) {
                showError("Down payment must be less than the total vehicle price.");
                return false;
            }

            // 6. Validate Interest Rate
            if (cmbInterest.getValue() == null) {
                showError("Please select an interest rate.");
                return false;
            }

            // 7. Validate Term (Slider value is usually set, but check it's not default/zero if needed)
            if (sldMonths.getValue() <= 0) {
                showError("Please select a valid term length.");
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            showError("Please enter valid numeric values for Price and Down Payment.");
            return false;
        }
    }
}