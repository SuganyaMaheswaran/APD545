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

public class SUV extends PassengerVehicle {
    public SUV(int currentMileage) {
        super("SUV", 45000.0, currentMileage);
        this.serviceInterval = 12000;
        this.maintenanceCost = 450.0;
        this.primaryFunction = "Family transport, offroad capability";
        this.fuelType = "Hybrid";
    }

    public String getCategory() {
        return "Passenger";
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