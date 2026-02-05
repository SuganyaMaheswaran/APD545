/*********************************************** 
 * Workshop # 1 
 * Course: APD545 - Winter2026 
 * Last Name: Maheswaran
 * First Name: Suganya
 * ID: 048298137
 * This assignment represents my own work in accordance with Seneca Academic Policy. 
 * Date: Jan 20, 2026
 *  ***********************************************/
package vehicleFleet.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import vehicleFleet.model.Ambulance;
import vehicleFleet.model.SUV;
import vehicleFleet.model.Sedan;
import vehicleFleet.model.Truck;
import vehicleFleet.model.Van;
import vehicleFleet.model.Vehicle;
import vehicleFleet.view.VehicleView;

public class VehicleController {

    private VehicleView view;
    private ArrayList<Vehicle> fleet;

    public VehicleController(VehicleView view) {
        this.view = view;
        this.fleet = new ArrayList<>();
    }
    // =========================
    // Vehicle Creation
    // =========================

    /**
     * Creates a vehicle object based on the type and mileage provided by the user.
     */
    public Vehicle createVehicle(String vehicleType) {
        int mileage = view.getCurrentMileage(vehicleType);

        switch (vehicleType.toLowerCase()) {
            case "sedan":
                return new Sedan(mileage);
            case "suv":
                return new SUV(mileage);
            case "truck":
                return new Truck(mileage);
            case "van":
                return new Van(mileage);
            case "ambulance":
                return new Ambulance(mileage);
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }

    /**
     * Populates the fleet by repeatedly asking the user for vehicle types.
     */
    public void populateFleet() {
        String vehicleType;
        while ((vehicleType = view.promptVehicleType()) != null) {
            fleet.add(createVehicle(vehicleType));
        }
    }

    // =========================
    // Display next vehicle for maintenance
    // =========================

    /**
     * Finds and displays the vehicle that is next due for maintenance.
     */
    public void displayNextVehicleForMaintenance() {
        if (fleet.isEmpty())
            return;

        Vehicle nextVehicleForService = Collections.max(this.fleet);
        view.displayNextVehicleForMaintenance(nextVehicleForService);
    }

    // =========================
    // Sorting methods
    // =========================

    public void sortFleetByPurchasePrice() {
        // fleet.sort((v1, v2) -> Double.compare(v2.getPurchasePrice(),
        // v1.getPurchasePrice()));
        Collections.sort(this.fleet, Comparator.comparingDouble(Vehicle::getPurchasePrice).reversed());

    }

    public void sortFleetByMileageAndService() {
        fleet.sort((v1, v2) -> {
            // Compare the current mileage(asc)
            int cmp = Integer.compare(v2.getCurrentMileage(), v1.getCurrentMileage());
            if (cmp != 0)
                return cmp;
            // Compare by remaining mileage to next service
            return Integer.compare(v1.mileageToNextService(), v2.mileageToNextService());
        });
    }

    // =========================
    // Display Methods
    // =========================

    public void displayFleetByPurchasePrice() {
        this.view.displayFleetByPurchasePrice(this.fleet);
    }

    public void displayFleetByNextService() {
        this.view.displayFleetByNextService(this.fleet);
    }

    public void displayVehiclesByCategory() {

        // Get the category string from the view
        String vehicleCategory = this.view.getVehicleCategory();

        // Filter the fleet into a new array
        ArrayList<Vehicle> filteredFleet = new ArrayList<>();
        for (Vehicle vehicle : this.fleet) {
            // equalsIgnoreCase for string comparison
            if (vehicle.getCategory().equalsIgnoreCase(vehicleCategory)) {
                filteredFleet.add(vehicle);
            }
        }

        // printing to the view
        this.view.displayVehiclesByCategory(vehicleCategory, filteredFleet);
    }

    /**
     * Getter for the fleet (useful for sorting or displaying vehicles).
     */
    public ArrayList<Vehicle> getFleet() {
        return fleet;
    }
}