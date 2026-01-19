package vechiclefleet.model;
public abstract class CommercialVehicle extends Vehicle{
    public CommercialVehicle(String name, double purchasePrice, int currentMileage){
        super(name, purchasePrice, currentMileage);

    }
    public String getCategory(){
        return "Commercial Vehicle" ;
    }
    
}
