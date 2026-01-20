package vehicleFleet.model;
import vehicleFleet.interfaces.IVehicleMaintenance;
import vehicleFleet.interfaces.IVehicleOperations; 
public abstract class Vehicle implements IVehicleMaintenance, IVehicleOperations {
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
    public int getCurrentMileage(){
        return currentMileage; 
        
    }
    public double getPurchasePrice(){
        return purchasePrice;
    }
    public String toString(){
        return name + " - $" + purchasePrice;
    }
    public int compareTo(Vehicle other){
        return this.currentMileage - other.currentMileage;
    }


}
