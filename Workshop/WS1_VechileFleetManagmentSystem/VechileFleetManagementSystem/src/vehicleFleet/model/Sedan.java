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

public class Sedan extends PassengerVehicle {

  public Sedan(int currentMileage) {
    super("Sedan", 28500.0, currentMileage);
    this.serviceInterval = 10000;
    this.maintenanceCost = 350.00;
    this.fuelType = "Gasoline";
    this.primaryFunction = "Executive transportation, client visits";
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

  public String getVehicleCategory() {
    return "Economy";
  }
}
