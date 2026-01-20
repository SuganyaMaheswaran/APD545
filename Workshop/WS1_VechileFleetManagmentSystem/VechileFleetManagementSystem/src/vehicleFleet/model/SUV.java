package vehicleFleet.model;
public class SUV extends PassengerVehicle{
    public SUV(int currentMileage){
        super("SUV", 45000.0, currentMileage);
        this.serviceInterval = 12000;
        this.maintenanceCost = 450.0;
        this.primaryFunction = "Family transport, offroad capability";
        this.fuelType = "Hybrid";
    }
    public String getCategory(){
        return "Premium";
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