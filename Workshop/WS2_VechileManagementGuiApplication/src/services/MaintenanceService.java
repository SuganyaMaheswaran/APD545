package services;

import models.Vehicle;
import models.MaintenanceRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaintenanceService {

    // Map each vehicle to its list of maintenance records
    private final Map<Vehicle, List<MaintenanceRecord>> dataStore = new HashMap<>();

    /**
     * Adds a maintenance record to a specific vehicle.
     * 
     * @param vehicle The vehicle to add the record for
     * @param record  The maintenance record to add
     */
    public void addRecord(Vehicle vehicle, LocalDate date, Double cost, String description) {
        MaintenanceRecord record = new MaintenanceRecord(date, cost, description);
        dataStore.putIfAbsent(vehicle, new ArrayList<>());
        dataStore.get(vehicle).add(record);
        System.out.println("Added maintenance record for " + vehicle + ": " + record);
    }

    /**
     * Retrieves all maintenance records for a specific vehicle.
     * 
     * @param vehicle The vehicle to get records for
     * @return A list of maintenance records; empty if none exist
     */
    public List<MaintenanceRecord> getRecords(Vehicle vehicle) {
        return dataStore.getOrDefault(vehicle, new ArrayList<>());
    }

    /**
     * Retrieves the latest maintenance record for a specific vehicle.
     * 
     * @param vehicle The vehicle to get the latest record for
     * @return The most recent MaintenanceRecord, or null if none exist
     */
    public MaintenanceRecord getLatestRecord(Vehicle vehicle) {
        List<MaintenanceRecord> records = getRecords(vehicle);
        if (records.isEmpty()) {
            return null;
        }
        return records.get(records.size() - 1); // assumes records are added chronologically
    }

    /**
     * Clears all maintenance records for a specific vehicle.
     * 
     * @param vehicle The vehicle to clear records for
     */
    public void clearRecords(Vehicle vehicle) {
        dataStore.remove(vehicle);
    }

    /**
     * Clears all maintenance records for all vehicles.
     */
    public void clearAllRecords() {
        dataStore.clear();
    }

    public void setVehicleService(VehicleService vehicleService) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVehicleService'");
    }
}
