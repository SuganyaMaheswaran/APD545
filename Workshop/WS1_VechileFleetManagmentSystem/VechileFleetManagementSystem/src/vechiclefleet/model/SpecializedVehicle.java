package vechiclefleet.model;
public abstract class SpecializedVehicle extends Vehicle{
    public SpecializedVehicle(String name, double purchasePrice, int currentMileage ){
        super(name, purchasePrice, currentMileage);
    }
    public String getCategory(){
        return "Specialized Vehicle";
    }
}