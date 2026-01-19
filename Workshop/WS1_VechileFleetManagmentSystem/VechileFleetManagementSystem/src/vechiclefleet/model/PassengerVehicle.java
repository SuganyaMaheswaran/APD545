package vechiclefleet.model;

public abstract class PassengerVehicle extends Vehicle{

        public PassengerVehicle(String name, double purchasePrice, int currentMileage){
            super(name, purchasePrice, currentMileage);
        };
       public String getCategory() {
        return "Passenger Vehicle";  // default category
    }
    
}
