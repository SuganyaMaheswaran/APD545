package vehicleFleet.model;

public class Ambulance extends SpecializedVehicle{
    public Ambulance(int currentMileage){
        super("Ambulance", 85000.0, currentMileage);
        this.serviceInterval = 8000;
        this.maintenanceCost = 800.0; 
        this.fuelType = "Diesel";
        this.primaryFunction = "Emergency medical transport, life saving";
    }
    public String getCategory(){
        return "Specialized";
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
