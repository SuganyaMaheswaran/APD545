package services;

import models.UsageLog;
import models.Vehicle;
import java.time.LocalDate;
import java.util.*;

public class UsageService {

    // Store UsageLogs by Vehicle
    private Map<Vehicle, List<UsageLog>> dataStore = new HashMap<>();

    // Add a usage log for a vehicle
    public void addUsage(Vehicle vehicle, UsageLog usage) {
        dataStore.putIfAbsent(vehicle, new ArrayList<>());
        dataStore.get(vehicle).add(usage);
    }

    // Overload: addUsage using individual fields
    public void addUsage(Vehicle vehicle, double distance,
            LocalDate start, LocalDate end) {
        UsageLog usage = new UsageLog(start, end, distance);
        addUsage(vehicle, usage);
    }

    // Get all usage logs for a vehicle
    public List<UsageLog> getUsageLogs(Vehicle vehicle) {
        return dataStore.getOrDefault(vehicle, new ArrayList<>());
    }

    // Optional: get all usage logs for all vehicles
    public List<UsageLog> getAllUsageLogs() {
        List<UsageLog> allLogs = new ArrayList<>();
        for (List<UsageLog> logs : dataStore.values()) {
            allLogs.addAll(logs);
        }
        return allLogs;
    }
}
