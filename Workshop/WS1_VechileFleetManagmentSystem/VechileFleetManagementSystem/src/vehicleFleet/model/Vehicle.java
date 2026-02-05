/*********************************************** 
 * Workshop # 1 
 * Course: APD545 - Winter2026 
 * Last Name: Maheswaran
 * First Name: Suganya
 * ID: 048298137
 * This assignment represents my own work in accordance with Seneca Academic Policy. 
 * Date: Jan 20, 2026
 *  ***********************************************/
package vehicleFleet.model;

import vehicleFleet.interfaces.IVehicleMaintenance;
import vehicleFleet.interfaces.IVehicleOperations;

public abstract class Vehicle implements IVehicleMaintenance, IVehicleOperations, Comparable<Vehicle> {
    public String name;
    double purchasePrice;
    int currentMileage;
    int serviceInterval;
    double maintenanceCost;
    String primaryFunction;
    String fuelType;

    public Vehicle(String name, double purchasePrice, int currentMileage) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.currentMileage = currentMileage;
    };

    public abstract String getCategory();

    public int getCurrentMileage() {
        return currentMileage;

    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public String toString() {
        return name + " - $" + purchasePrice;
    }

    public int compareTo(Vehicle other) {
        return Integer.compare(this.getCurrentMileage(), other.getCurrentMileage());
    }

    public int mileageToNextService() {
        return serviceInterval - (currentMileage % serviceInterval);
    }
}
