package controller.forms;

import java.io.IOException;
import java.time.LocalDate;

import controller.SummaryController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Vehicle;
import services.MaintenanceService;
import services.UsageService;
import services.VehicleService;
import utils.ButtonUtils;
import utils.FormStyleUtils;
import utils.WindowConfig;

public class AddMaintenanceFormController {

    // --- FXML Components ---
    @FXML
    private ComboBox<Vehicle> cmbVehicles;
    @FXML
    private DatePicker serviceDate;
    @FXML
    private TextField txtCost;
    @FXML
    private TextField txtDescription;
    @FXML
    private Button btnCancel, btnSubmit;

    // --- Services ---
    private VehicleService vehicleService;
    private MaintenanceService maintenanceService;
    private UsageService usageService; // optional if needed for summary view

    // --- Initialization ---
    @FXML
    private void initialize() {
        // Configure vehicle dropdown display
        configureVehicleComboBox();

        // Default date to today
        serviceDate.setValue(LocalDate.now());

        // Apply standard button styles
        ButtonUtils.setDefaultStyle(btnCancel);
        ButtonUtils.setDefaultStyle(btnSubmit);
    }

    // --- ComboBox Configuration ---
    private void configureVehicleComboBox() {
        // Customize how each vehicle appears in the dropdown list
        cmbVehicles.setCellFactory(listView -> new ListCell<Vehicle>() {
            @Override
            protected void updateItem(Vehicle v, boolean empty) {
                super.updateItem(v, empty);
                setText(empty || v == null
                        ? null
                        : v.getMake() + " " + v.getModel() + " (" + v.getYear() + ")");
            }
        });

        // Customize how the selected vehicle appears in the ComboBox button
        cmbVehicles.setButtonCell(new ListCell<Vehicle>() {
            @Override
            protected void updateItem(Vehicle v, boolean empty) {
                super.updateItem(v, empty);
                setText(empty || v == null
                        ? null
                        : v.getMake() + " " + v.getModel() + " (" + v.getYear() + ")");
            }
        });
    }

    // --- Service Setters ---
    public void setVehicleService(VehicleService vService) {
        this.vehicleService = vService;
        cmbVehicles.getItems().setAll(vehicleService.getAllVehicles());
    }

    public void setMaintenanceService(MaintenanceService mService) {
        this.maintenanceService = mService;
    }

    // --- Form Actions ---
    @FXML
    private void clearForm() {
        // Reset all fields to default values
        cmbVehicles.getSelectionModel().clearSelection();
        serviceDate.setValue(LocalDate.now());
        txtCost.clear();
        txtDescription.clear();

        // Reset styling for all inputs
        FormStyleUtils.resetAll(
                cmbVehicles,
                serviceDate,
                txtCost,
                txtDescription);
    }

    @FXML
    private void onSubmit() {
        // --- Retrieve values from form ---
        Vehicle selectedVehicle = cmbVehicles.getValue();
        LocalDate date = serviceDate.getValue();
        String description = txtDescription.getText();
        String costText = txtCost.getText();

        // Reset previous error styles
        FormStyleUtils.resetAll(
                cmbVehicles,
                serviceDate,
                txtCost,
                txtDescription);

        boolean valid = true;
        double cost = 0;

        // --- Validate vehicle ---
        if (selectedVehicle == null) {
            FormStyleUtils.markError(cmbVehicles);
            valid = false;
        }

        // --- Validate date ---
        if (date == null) {
            FormStyleUtils.markError(serviceDate);
            valid = false;
        }

        // --- Validate cost ---
        try {
            cost = Double.parseDouble(costText);
            if (cost <= 0) {
                FormStyleUtils.markError(txtCost);
                valid = false;
            }
        } catch (NumberFormatException e) {
            FormStyleUtils.markError(txtCost);
            valid = false;
        }

        // Stop submission if invalid
        if (!valid)
            return;

        // --- Add record to MaintenanceService ---
        maintenanceService.addRecord(selectedVehicle, date, cost, description);

        // --- Reset form for next entry ---
        clearForm();

        // --- Show maintenance summary popup ---
        openSummaryPopup();
    }

    // --- Open Summary Popup ---
    private void openSummaryPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SummaryView.fxml"));
            Stage popup = new Stage();
            popup.setTitle("Maintenance Summary");
            popup.setScene(new javafx.scene.Scene(loader.load()));

            // Set services for SummaryController and show maintenance view
            SummaryController controller = loader.getController();
            controller.setServices(vehicleService, usageService, maintenanceService);
            controller.showMaintenance();

            // Standardize popup size
            popup.setWidth(WindowConfig.WIDTH);
            popup.setHeight(WindowConfig.HEIGHT);
            popup.setMinWidth(WindowConfig.WIDTH);
            popup.setMinHeight(WindowConfig.HEIGHT);
            popup.setMaxWidth(WindowConfig.WIDTH);
            popup.setMaxHeight(WindowConfig.HEIGHT);

            popup.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
