package controller;

import java.io.IOException;

import controller.forms.AddMaintenanceFormController;
import controller.forms.AddUsageFormController;
import controller.forms.AddVehicleFormController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import services.MaintenanceService;
import services.UsageService;
import services.VehicleService;
import utils.ButtonUtils;

public class MainController {

    // --- FXML UI Components ---
    @FXML
    private StackPane centerPane; // Main content area where forms are loaded

    @FXML
    private Button btnAddVehicle, btnAddUsage, btnAddMaintenance; // Menu buttons

    // --- Services for forms ---
    private VehicleService vService = new VehicleService();
    private MaintenanceService mService = new MaintenanceService();
    private UsageService uService = new UsageService();

    // --- Initialization called automatically after FXML load ---
    @FXML
    public void initialize() throws IOException {
        // Show Vehicle form by default on app load
        showAddVehicleForm();

        // Highlight the default selected button
        ButtonUtils.highlightButton(btnAddVehicle, btnAddVehicle, btnAddUsage, btnAddMaintenance);
    }

    // --- Load the Vehicle form into the center pane ---
    public void showAddVehicleForm() throws IOException {
        // Highlight the selected button
        ButtonUtils.highlightButton(btnAddVehicle, btnAddVehicle, btnAddUsage, btnAddMaintenance);

        // Load FXML for AddVehicle form
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/formViews/AddVehicleForm.fxml"));
        Node form = loader.load();

        // Pass vehicle service to form controller
        AddVehicleFormController controller = loader.getController();
        controller.setVehicleService(vService);

        // Display form in the center pane
        centerPane.getChildren().setAll(form);
    }

    // --- Load the Usage form into the center pane ---
    @FXML
    private void showAddUsageForm() throws IOException {
        // Highlight the selected button
        ButtonUtils.highlightButton(btnAddUsage, btnAddVehicle, btnAddUsage, btnAddMaintenance);

        // Load FXML for AddUsage form
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/formViews/AddUsageForm.fxml"));
        Node form = loader.load();

        // Pass required services to form controller
        AddUsageFormController controller = loader.getController();
        controller.setVehicleService(vService);
        controller.setUsageService(uService);

        // Display form in the center pane
        centerPane.getChildren().setAll(form);
    }

    // --- Load the Maintenance form into the center pane ---
    @FXML
    private void showAddMaintenanceForm() throws IOException {
        // Highlight the selected button
        ButtonUtils.highlightButton(btnAddMaintenance, btnAddVehicle, btnAddUsage, btnAddMaintenance);

        // Load FXML for AddMaintenance form
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/formViews/AddMaintenanceForm.fxml"));
        Node form = loader.load();

        // Pass required services to form controller
        AddMaintenanceFormController controller = loader.getController();
        controller.setVehicleService(vService);
        controller.setMaintenanceService(mService);

        // Display form in the center pane
        centerPane.getChildren().setAll(form);
    }
}
