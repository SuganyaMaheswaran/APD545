package vechilefleet.model;

public class Vehicle {
    String name;
    double purchasePrice; 
    int currentMileage; 
    int serviceInterval;
    double maintenanceCost; 
    String primaryFunction;
    String fuelType; 

    public Vehicle(String name, double purchasePrice, int currentMileage){
        this.name = name; 
        this.purchasePrice = purchasePrice;
        this.currentMileage = currentMileage;

        
    }
}
