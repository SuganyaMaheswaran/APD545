package vechiclefleet.model;

public abstract class Vehicle {
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
    };
    public abstract String getCategory();
    public int getCurrentMileage(){
        return currentMileage; 
        
    }
    public double getPurchasePrice(){
        return purchasePrice;
    }
    public String toString(){
        return " ";
    }
    public int compareTo(Vehicle vehicle){
        return 1;
    }

}
