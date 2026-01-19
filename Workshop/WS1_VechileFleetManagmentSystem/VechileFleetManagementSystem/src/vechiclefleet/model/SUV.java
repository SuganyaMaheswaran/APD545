package vechiclefleet.model;
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
}