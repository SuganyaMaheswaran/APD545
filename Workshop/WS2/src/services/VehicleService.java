package services;

import java.util.ArrayList;
import java.util.List;

import models.Vehicle;

public class VehicleService {
    private final List<Vehicle> vehicles = new ArrayList<>();

    // Add vehicle with parsed data
    public void addVehicle(String make, String model, int year, String type) {
        Vehicle v = new Vehicle(make, model, year, type);
        vehicles.add(v);
        System.out.println("Vehicle added: " + v);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    // Validation methods (purely data-based, no UI)
    public boolean isValidYear(String yearStr) {
        try {
            int year = Integer.parseInt(yearStr);
            return year >= 1886 && year <= java.time.Year.now().getValue();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidVehicle(String make, String model, String type, String yearStr) {
        return make != null && !make.isEmpty() &&
                model != null && !model.isEmpty() &&
                type != null && !type.isEmpty() &&
                isValidYear(yearStr);
    }

    public boolean containsVehicle(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }

}
