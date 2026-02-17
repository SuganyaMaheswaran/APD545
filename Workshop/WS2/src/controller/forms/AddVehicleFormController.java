package controller.forms;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.SummaryController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class AddVehicleFormController {

    // --- FXML Components ---
    @FXML
    private ComboBox<String> cmbMake, cmbModel, cmbType;
    @FXML
    private TextField txtYear;
    @FXML
    private Button btnCancel, btnSubmit;

    // --- Data / Services ---
    private final Map<String, List<String>> makeModelMap = new HashMap<>();
    private VehicleService vehicleService;
    private UsageService usageService;
    private MaintenanceService maintenanceService;

    // --- Service Setters ---
    public void setVehicleService(VehicleService vService) {
        this.vehicleService = vService;
    }

    public void setMaintenanceService(MaintenanceService mService) {
        this.maintenanceService = mService;
    }

    public void setUsageService(UsageService uService) {
        this.usageService = uService;
    }

    // --- Initialization ---
    @FXML
    private void initialize() {
        // Populate make-model mapping
        makeModelMap.put("Toyota", Arrays.asList("Corolla", "Camry", "RAV4", "Highlander"));
        makeModelMap.put("Ford", Arrays.asList("F-150", "Mustang", "Explorer", "Focus"));
        makeModelMap.put("Chevrolet", Arrays.asList("Silverado", "Malibu", "Equinox", "Traverse"));
        makeModelMap.put("Honda", Arrays.asList("Civic", "Accord", "CR-V", "Pilot"));
        makeModelMap.put("Nissan", Arrays.asList("Altima", "Sentra", "Rogue", "Murano"));
        makeModelMap.put("BMW", Arrays.asList("3 Series", "5 Series", "X3", "X5"));
        makeModelMap.put("Mercedes-Benz", Arrays.asList("C-Class", "E-Class", "GLA", "GLE"));
        makeModelMap.put("Hyundai", Arrays.asList("Elantra", "Sonata", "Tucson", "Santa Fe"));
        makeModelMap.put("Volkswagen", Arrays.asList("Golf", "Passat", "Tiguan", "Atlas"));
        makeModelMap.put("Subaru", Arrays.asList("Impreza", "Legacy", "Outback", "Forester"));

        // Populate type ComboBox
        cmbType.getItems().addAll("Car", "Truck", "Van", "SUV");

        // Populate make ComboBox
        cmbMake.getItems().addAll(makeModelMap.keySet());

        // Update models dynamically based on selected make
        cmbMake.valueProperty().addListener((obs, oldMake, newMake) -> {
            cmbModel.getItems().clear();
            if (newMake != null && makeModelMap.containsKey(newMake)) {
                cmbModel.getItems().addAll(makeModelMap.get(newMake));
            }
        });

        // Apply default button styling
        ButtonUtils.setDefaultStyle(btnCancel, btnSubmit);
    }

    // --- Form Actions ---
    @FXML
    private void clearForm() {
        // Reset all ComboBoxes and TextField
        cmbMake.getSelectionModel().clearSelection();
        FormStyleUtils.reset(cmbMake);

        cmbModel.getSelectionModel().clearSelection();
        FormStyleUtils.reset(cmbModel);

        cmbType.getSelectionModel().clearSelection();
        FormStyleUtils.reset(cmbType);

        txtYear.clear();
        FormStyleUtils.reset(txtYear);
    }

    @FXML
    private void onSubmit() {
        // --- Get form values ---
        String make = cmbMake.getValue();
        String model = cmbModel.getValue();
        String type = cmbType.getValue();
        String yearInput = txtYear.getText();

        boolean valid = true;

        // Reset previous error styling
        FormStyleUtils.resetAll(cmbMake, cmbModel, cmbType, txtYear);

        // --- Validation ---
        if (ValidationUtils.isNullOrEmpty(make)) {
            FormStyleUtils.markError(cmbMake);
            valid = false;
        }

        if (ValidationUtils.isNullOrEmpty(model)) {
            FormStyleUtils.markError(cmbModel);
            valid = false;
        }

        if (ValidationUtils.isNullOrEmpty(type)) {
            FormStyleUtils.markError(cmbType);
            valid = false;
        }

        if (!ValidationUtils.isValidYear(yearInput)) {
            FormStyleUtils.markError(txtYear);
            valid = false;
        }

        // Stop submission if validation fails
        if (!valid)
            return;

        // --- Save Vehicle ---
        int year = Integer.parseInt(yearInput);
        vehicleService.addVehicle(make, model, year, type);

        // Clear form for next entry
        clearForm();

        // Open the summary popup
        openSummaryPopup();
    }

    // --- Open Summary Popup ---
    private void openSummaryPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SummaryView.fxml"));
            Stage popup = new Stage();
            popup.setTitle("Vehicle Summary");

            // Load FXML and set scene
            popup.setScene(new javafx.scene.Scene(loader.load()));

            // Initialize SummaryController with all services
            SummaryController controller = loader.getController();
            controller.setServices(vehicleService, usageService, maintenanceService);

            // Show the vehicles summary view
            controller.showVehicles();

            // Standardize popup size
            popup.setWidth(WindowConfig.WIDTH);
            popup.setHeight(WindowConfig.HEIGHT);
            popup.setMinWidth(WindowConfig.WIDTH);
            popup.setMinHeight(WindowConfig.HEIGHT);
            popup.setMaxWidth(WindowConfig.WIDTH);
            popup.setMaxHeight(WindowConfig.HEIGHT);

            // Show the popup
            popup.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
