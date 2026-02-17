package controller.summary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.UsageLog;
import models.Vehicle;
import services.UsageService;
import services.VehicleService;

import java.time.LocalDate;

public class UsageSummaryController {

    // --- Services ---
    private VehicleService vehicleService;
    private UsageService usageService;

    // --- FXML Table and Columns ---
    @FXML
    private TableView<UsageRow> usageSummaryTable;
    @FXML
    private TableColumn<UsageRow, Vehicle> vehicleCol;
    @FXML
    private TableColumn<UsageRow, LocalDate> startDateCol;
    @FXML
    private TableColumn<UsageRow, LocalDate> endDateCol;
    @FXML
    private TableColumn<UsageRow, Double> distanceCol;

    // --- Observable list for table data ---
    private final ObservableList<UsageRow> rows = FXCollections.observableArrayList();

    // --- Initialization ---
    @FXML
    public void initialize() {
        // Map TableColumns to properties in UsageRow
        vehicleCol.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        distanceCol.setCellValueFactory(new PropertyValueFactory<>("distance"));

        // Attach observable list to TableView
        usageSummaryTable.setItems(rows);

        // Enable automatic column resizing to fill available width
        usageSummaryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

    // --- Set the services before using the controller ---
    public void setService(VehicleService vService, UsageService uService) {
        this.vehicleService = vService;
        this.usageService = uService;
    }

    // --- Refresh the table with current usage logs ---
    // Call this every time before showing the view to ensure up-to-date data
    public void refreshTable() {
        rows.clear(); // Clear existing rows to avoid duplicates
        if (vehicleService != null && usageService != null) {
            for (Vehicle v : vehicleService.getAllVehicles()) {
                for (UsageLog log : usageService.getUsageLogs(v)) {
                    rows.add(new UsageRow(v, log));
                }
            }
        }
    }

    // --- Inner class representing a single row in the usage table ---
    public class UsageRow {
        private Vehicle vehicle;
        private UsageLog log;

        public UsageRow(Vehicle vehicle, UsageLog log) {
            this.vehicle = vehicle;
            this.log = log;
        }

        // --- Table column getters ---
        public Vehicle getVehicle() {
            return vehicle;
        }

        public LocalDate getStartDate() {
            return log.getStartDate();
        }

        public LocalDate getEndDate() {
            return log.getEndDate();
        }

        public double getDistance() {
            return log.getDistance();
        }
    }
}
