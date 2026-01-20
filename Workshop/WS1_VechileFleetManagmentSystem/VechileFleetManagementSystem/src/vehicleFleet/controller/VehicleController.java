package vehicleFleet.controller;

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
    private ArrayList<Vehicle> fleet = new ArrayList<>();

    public VehicleController(VehicleView view) {
        this.view = view; 
    }

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

    /**
     * Finds and displays the vehicle that is next due for maintenance.
     */
    public void displayNextVehicleForMaintenance() {
        if (fleet.isEmpty()) return;

        Vehicle nextVehicleForService = fleet.get(0);

        for (Vehicle vehicle : fleet) {
            if (calculateMileageToNextService(vehicle) < calculateMileageToNextService(nextVehicleForService)) {
                nextVehicleForService = vehicle;
            }
        }

        view.displayNextVehicleForMaintenance(nextVehicleForService);
    }

    /**
     * Calculates the mileage remaining before the vehicle requires its next service.
     */
    private int calculateMileageToNextService(Vehicle vehicle) {
        int currentMileage = vehicle.getCurrentMileage();
        int serviceInterval = vehicle.getServiceInterval();
        return serviceInterval - (currentMileage % serviceInterval);
    }

    /**
     * Getter for the fleet (useful for sorting or displaying vehicles).
     */
    public ArrayList<Vehicle> getFleet() {
        return fleet;
    }
    public void sortFleetByPurchasePrice(){
         fleet.sort((v1, v2) -> Double.compare(v2.getPurchasePrice(), v1.getPurchasePrice()));
      
    }
    public void displayFleet(){
        this.view.displayFleet(this.fleet);
    }

    public void displayVehiclesByCategory() {
        String vehicleCategory = this.view.getVehicleCategory(); // prompt user
        ArrayList<Vehicle> filteredFleet = new ArrayList<>();

        for (Vehicle vehicle : this.fleet) {
            // Use equalsIgnoreCase for string comparison
            if (vehicle.getCategory().equalsIgnoreCase(vehicleCategory)) {
                filteredFleet.add(vehicle);
            }
        }

        // Delegate printing to the view
        this.view.displayVehiclesByCategory(vehicleCategory, filteredFleet);
    }
}