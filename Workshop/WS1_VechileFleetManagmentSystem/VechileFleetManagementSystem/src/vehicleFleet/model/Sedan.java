package vehicleFleet.model;

public class Sedan extends PassengerVehicle{
    
    public Sedan(int currentMileage){
        super("Sedan", 28500.0, currentMileage);
        this.serviceInterval = 10000;
        this.maintenanceCost = 350.00;
        this.fuelType = "Gasoline";
        this.primaryFunction = "Executive transportation, client visits";
  }
  public String getCategory(){
    return "Economy";
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
