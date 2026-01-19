package vechiclefleet.model;

public class Truck  extends CommercialVehicle{
    public Truck(int currentMileage){
        super("Truck", 62000.0, currentMileage);
        this.serviceInterval = 15000;
        this.maintenanceCost = 600;
        this.fuelType = "Diesel";
        this.primaryFunction = "Heavy cargo, long distance hauling";
    }
    public String getCategory(){
        return "Commercial";
    }
    
}
