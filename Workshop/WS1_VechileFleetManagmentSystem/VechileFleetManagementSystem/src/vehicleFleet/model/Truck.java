package vehicleFleet.model;

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
