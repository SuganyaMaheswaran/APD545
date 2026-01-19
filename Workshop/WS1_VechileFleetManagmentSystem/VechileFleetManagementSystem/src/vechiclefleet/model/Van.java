package vechiclefleet.model;

public class Van extends CommercialVehicle{
    public Van(int currentMileage){
        super("Van", 38500.0, currentMileage);
        this.serviceInterval = 10000;
        this.maintenanceCost = 400.0;
        this.fuelType = "Gasoline";
        this.primaryFunction = "Passenger group transport, deliveries";
    }
    public String getCategory(){
        return "Commercial";
    }
    
}
