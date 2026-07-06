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
import utils.ValidationUtils;
import utils.WindowConfig;

public class AddUsageFormController {

    @FXML
    private ComboBox<Vehicle> cmbVehicles;
    @FXML
    private TextField txtDistance;
    @FXML
    private DatePicker startDate, endDate;
    @FXML
    private Button btnCancel, btnSubmit;

    private VehicleService vehicleService;
    private UsageService usageService;

    private MaintenanceService maintenanceService;

    @FXML
    private void initialize() {

        // ComboBox display formatting
        cmbVehicles.setCellFactory(listView -> new ListCell<Vehicle>() {
            @Override
            protected void updateItem(Vehicle v, boolean empty) {
                super.updateItem(v, empty);
                setText((empty || v == null) ? null : v.getMake() + " " + v.getModel() + " (" + v.getYear() + ")");
            }
        });

        cmbVehicles.setButtonCell(new ListCell<Vehicle>() {
            @Override
            protected void updateItem(Vehicle v, boolean empty) {
                super.updateItem(v, empty);
                setText((empty || v == null) ? null : v.getMake() + " " + v.getModel() + " (" + v.getYear() + ")");
            }
        });

        ButtonUtils.setDefaultStyle(btnCancel, btnSubmit);
    }

    public void setVehicleService(VehicleService vService) {
        this.vehicleService = vService;
        loadVehiclesIntoComboBox();
    }

    public void setUsageService(UsageService uService) {
        this.usageService = uService;
    }

    public void setMaintenanceService(MaintenanceService mService) {
        this.maintenanceService = mService;
    }

    private void loadVehiclesIntoComboBox() {
        cmbVehicles.getItems().clear();
        cmbVehicles.getItems().addAll(vehicleService.getAllVehicles());
    }

    @FXML
    private void clearForm() {
        cmbVehicles.getSelectionModel().clearSelection();
        FormStyleUtils.reset(cmbVehicles);

        txtDistance.clear();
        FormStyleUtils.reset(txtDistance);

        startDate.setValue(null);
        FormStyleUtils.reset(startDate);

        endDate.setValue(null);
        FormStyleUtils.reset(endDate);
    }

    @FXML
    private void onSubmit() {
        // Get form values
        Vehicle selectedVehicle = cmbVehicles.getValue();
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
        String distanceText = txtDistance.getText();

        boolean valid = true;

        // Reset styles on all fields
        FormStyleUtils.resetAll(cmbVehicles, txtDistance, startDate, endDate);

        // Validate vehicle selection
        if (selectedVehicle == null) {
            FormStyleUtils.markError(cmbVehicles);
            valid = false;
        }

        // Validate distance as positive double
        if (!ValidationUtils.isPositiveDouble(distanceText)) {
            FormStyleUtils.markError(txtDistance);
            valid = false;
        }

        // Validate start date
        if (start == null) {
            FormStyleUtils.markError(startDate);
            valid = false;
        }

        // Validate end date
        if (end == null || (start != null && end.isBefore(start))) {
            FormStyleUtils.markError(endDate);
            valid = false;
        }

        // Stop if validation fails
        if (!valid)
            return;

        // All validations passed, parse distance and save usage
        double distance = Double.parseDouble(distanceText);
        usageService.addUsage(selectedVehicle, distance, start, end);

        // Clear form
        clearForm();
        openSummaryPopup();
    }

    private void openSummaryPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SummaryView.fxml"));
            Stage popup = new Stage();
            popup.setTitle("Usage Summary");

            popup.setScene(new javafx.scene.Scene(loader.load()));

            SummaryController controller = loader.getController();
            controller.setServices(vehicleService, usageService, maintenanceService);

            controller.showUsage();
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
