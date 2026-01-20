import vehicleFleet.controller.VehicleController;
import vehicleFleet.view.*;

public class App {
    public static void main(String[] args) throws Exception {
        VehicleView view = new VehicleView();
        VehicleController controller = new VehicleController(view);
        System.out.println("Welcome to Vehicle Fleet Management System!");
        controller.populateFleet();
        controller.displayNextVehicleForMaintenance();
        controller.sortFleetByPurchasePrice();
        controller.displayFleet();
        controller.displayVehiclesByCategory();

    }
}
