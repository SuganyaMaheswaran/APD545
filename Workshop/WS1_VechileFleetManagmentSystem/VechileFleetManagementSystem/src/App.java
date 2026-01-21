
/*********************************************** 
 * Workshop # 1 
 * Course: APD545 - Winter2026 
 * Last Name: Maheswaran
 * First Name: Suganya
 * ID: 048298137
 * This assignment represents my own work in accordance with Seneca Academic Policy. 
 * Date: Jan 20, 2026
 *  ***********************************************/
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
        controller.displayFleetByPurchasePrice();
        controller.displayVehiclesByCategory();
        controller.sortFleetByMileageAndService();
        controller.displayFleetByNextService();

    }
}
