package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import services.MaintenanceService;
import services.UsageService;
import services.VehicleService;
import utils.ButtonUtils;
import controller.summary.*;

import java.io.IOException;

public class SummaryController {

    @FXML
    private StackPane contentPane;
    @FXML
    private Button vehicleBtn, usageBtn, maintenanceBtn;

    private VehicleService vehicleService;
    private UsageService usageService;
    private MaintenanceService maintenanceService;

    private Parent vehicleView, usageView, maintenanceView;
    private VehicleSummaryController vehicleController;
    private UsageSummaryController usageController;
    private MaintenanceSummaryController maintenanceController;

    public void setServices(VehicleService vehicleService,
            UsageService usageService,
            MaintenanceService maintenanceService) {
        this.vehicleService = vehicleService;
        this.usageService = usageService;
        this.maintenanceService = maintenanceService;
    }

    @FXML
    public void showVehicles() {
        try {
            if (vehicleView == null) {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/views/summaryViews/VehicleSummaryView.fxml"));
                vehicleView = loader.load();
                vehicleController = loader.getController();
                vehicleController.setVehicleService(vehicleService);
            }

            contentPane.getChildren().setAll(vehicleView);
            ButtonUtils.highlightButton(vehicleBtn, vehicleBtn, usageBtn, maintenanceBtn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showUsage() {
        try {
            if (usageView == null) {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/views/summaryViews/UsageSummaryView.fxml"));
                usageView = loader.load();
                usageController = loader.getController();
            }

            // Always set services BEFORE refreshing
            usageController.setService(vehicleService, usageService);
            usageController.refreshTable(); // <-- refresh every time

            contentPane.getChildren().setAll(usageView);
            ButtonUtils.highlightButton(usageBtn, usageBtn, vehicleBtn, maintenanceBtn);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showMaintenance() {
        try {
            if (maintenanceView == null) {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/views/summaryViews/MaintenanceSummaryView.fxml"));
                maintenanceView = loader.load();
                maintenanceController = loader.getController();
            }

            // Ensure services are set
            maintenanceController.setVehicleService(vehicleService);
            maintenanceController.setMaintenaceService(maintenanceService);

            // Load the table rows
            maintenanceController.loadAllRows();

            // Show the view
            contentPane.getChildren().setAll(maintenanceView);

            // Highlight the button
            ButtonUtils.highlightButton(maintenanceBtn, maintenanceBtn, vehicleBtn, usageBtn);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
