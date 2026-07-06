package controller.summary;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Vehicle;
import services.VehicleService;

public class VehicleSummaryController {
    @FXML
    private TableView<Vehicle> vehiclesSummaryTable;

    @FXML
    private TableColumn<Vehicle, String> makeColumn;

    @FXML
    private TableColumn<Vehicle, String> modelColumn;

    @FXML
    private TableColumn<Vehicle, Integer> yearColumn;

    @FXML
    private TableColumn<Vehicle, String> typeColumn;

    @FXML
    public void initialize() {
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        vehiclesSummaryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

    private VehicleService vehicleService;

    public void setVehicleService(VehicleService service) {
        this.vehicleService = service;
        initializeTable();
    }

    private void initializeTable() {
        if (vehicleService != null) {
            vehiclesSummaryTable.getItems().setAll(vehicleService.getAllVehicles());
        }
    }

}
