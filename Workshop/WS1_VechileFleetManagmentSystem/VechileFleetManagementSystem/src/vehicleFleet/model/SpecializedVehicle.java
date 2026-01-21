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

public abstract class SpecializedVehicle extends Vehicle {
    public SpecializedVehicle(String name, double purchasePrice, int currentMileage) {
        super(name, purchasePrice, currentMileage);
    }

    public String getCategory() {
        return "Specialized";
    }
}