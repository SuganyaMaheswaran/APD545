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

public class Van extends CommercialVehicle {
    public Van(int currentMileage) {
        super("Van", 38500.0, currentMileage);
        this.serviceInterval = 10000;
        this.maintenanceCost = 400.0;
        this.fuelType = "Gasoline";
        this.primaryFunction = "Passenger group transport, deliveries";
    }

    public String getCategory() {
        return "Commercial";
    }

    @Override
    public int getServiceInterval() {
        return this.serviceInterval;
    }

    @Override
    public double getMaintenanceCost() {
        return this.maintenanceCost;
    }

    @Override
    public String getPrimaryFunction() {
        return this.primaryFunction;
    }

    @Override
    public String getFuelType() {
        return this.fuelType;
    }
}
