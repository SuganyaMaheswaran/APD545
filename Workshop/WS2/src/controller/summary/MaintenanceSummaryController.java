package controller.summary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.MaintenanceRecord;
import models.Vehicle;
import services.MaintenanceService;
import services.VehicleService;

import java.time.LocalDate;

public class MaintenanceSummaryController {

    private VehicleService vehicleService;
    private MaintenanceService maintenanceService;

    @FXML
    private TableView<MaintenanceRow> maintenanceSummaryTable;

    @FXML
    private TableColumn<MaintenanceRow, Vehicle> vehicleCol;
    @FXML
    private TableColumn<MaintenanceRow, LocalDate> dateCol;
    @FXML
    private TableColumn<MaintenanceRow, Double> costCol;
    @FXML
    private TableColumn<MaintenanceRow, String> descriptionCol;

    // Cached list to prevent table reset
    private final ObservableList<MaintenanceRow> rows = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        vehicleCol.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        maintenanceSummaryTable.setItems(rows);
        maintenanceSummaryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        System.out.println("Columns: " + maintenanceSummaryTable.getColumns());
    }

    public void loadAllRows() {
        rows.clear();
        if (vehicleService != null && maintenanceService != null) {
            for (Vehicle v : vehicleService.getAllVehicles()) {
                for (MaintenanceRecord record : maintenanceService.getRecords(v)) {
                    rows.add(new MaintenanceRow(v, record));
                }
            }
        }
    }

    // Call this after adding a new maintenance record
    public void addRow(Vehicle vehicle, MaintenanceRecord record) {
        rows.add(new MaintenanceRow(vehicle, record));
    }

    public void setMaintenaceService(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public class MaintenanceRow {
        private Vehicle vehicle;
        private MaintenanceRecord record;

        public MaintenanceRow(Vehicle v, MaintenanceRecord r) {
            this.vehicle = v;
            this.record = r;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public LocalDate getDate() {
            return record.getDate();
        }

        public Double getCost() {
            return record.getCost();
        }

        public String getDescription() {
            return record.getDescription();
        }
    }

}
