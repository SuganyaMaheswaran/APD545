/*********************************************** 
 * Workshop # 1 
 * Course: APD545 - Winter2026 
 * Last Name: Maheswaran
 * First Name: Suganya
 * ID: 048298137
 * This assignment represents my own work in accordance with Seneca Academic Policy. 
 * Date: Jan 20, 2026
 *  ***********************************************/
package vehicleFleet.view;

import java.util.ArrayList;
import java.util.Scanner;
import vehicleFleet.model.Vehicle;

public class VehicleView {

    private Scanner scanner = new Scanner(System.in);

    // =========================
    // INPUT METHODS
    // =========================

    /**
     * Prompts the user to enter the current mileage for a vehicle.
     */
    public int getCurrentMileage(String vehicleType) {
        int vehicleMileage;
        do {
            System.out.print("Enter current mileage for " + vehicleType + " (km): ");
            vehicleMileage = scanner.nextInt();
            scanner.nextLine();
            if (vehicleMileage < 0) {
                System.out.println("Mileage cannot be negative. Please try again.");
            }
        } while (vehicleMileage < 0);
        return vehicleMileage;
    }

    /**
     * Prompts the user to select a vehicle type to add to the fleet.
     * Returns null if the user wants to exit.
     */
    public String promptVehicleType() {
        String vehicleType = null;

        do {

            System.out.print(
                    "Enter the type of vehicle to add (Sedan, SUV, Truck, Van, Ambulance) or type 'N' to exit: ");
            vehicleType = scanner.nextLine().trim();

            // Exit condition
            if (vehicleType.equalsIgnoreCase("N")) {
                return null;
            }
            // Check for valid types
            if (vehicleType.equalsIgnoreCase("SUV") ||
                    vehicleType.equalsIgnoreCase("Sedan") ||
                    vehicleType.equalsIgnoreCase("Truck") ||
                    vehicleType.equalsIgnoreCase("Van") ||
                    vehicleType.equalsIgnoreCase("Ambulance")) {

                return vehicleType; // valid type entered
            } else {
                System.out.println("Invalid vehicle type! Please try again.");
            }

        } while (true); // loop until valid input or exit

    }

    /**
     * Prompts the user to select a vehicle category.
     */
    public String getVehicleCategory() {
        String vehicleCategory;
        String[] validCategories = { "Passenger", "Commercial", "Specialized" };

        do {
            System.out.print("Enter a vehicle category (Passenger, Commercial, Specialized): ");
            vehicleCategory = scanner.nextLine().trim();

            // Check if input matches a valid category (ignore case)
            for (String valid : validCategories) {
                if (valid.equalsIgnoreCase(vehicleCategory)) {
                    return valid; // return properly capitalized version
                }
            }

            System.out.println("Invalid category! Please try again.");
        } while (true); // loop until valid input
    }
    // =========================
    // DISPLAY METHODS
    // =========================

    /**
     * Displays details of the vehicle that requires the next maintenance.
     */
    public void displayNextVehicleForMaintenance(Vehicle vehicle) {
        System.out.println("\n--- Vehicle Requiring Urgent Maintenance ---");
        System.out.println("Vehicle Name: " + vehicle.name);
        System.out.println("Purchase Price: $" + vehicle.getPurchasePrice());
        System.out.println("Primary Function: " + vehicle.getPrimaryFunction());
        System.out.println("Service Interval: " + vehicle.getServiceInterval() + " km");
        System.out.println("Maintenance Cost: $" + vehicle.getMaintenanceCost());
        System.out.println("-------------------------------------------\n");
    }

    /**
     * Displays sorted fleet by purchase price.
     */
    public void displayFleetByPurchasePrice(ArrayList<Vehicle> fleet) {
        if (fleet.isEmpty()) {
            System.out.println("The fleet is empty");
            return;
        }
        System.out.println("\nVehicles in Descending Order of Purchase Price:");
        for (Vehicle vehicle : fleet) {
            System.out.println(vehicle.name + " - $" + vehicle.getPurchasePrice());
        }
        System.out.println("----------------------------------------\n");
    }

    /**
     * Displays sorted fleet order by nex in service.
     */
    public void displayFleetByNextService(ArrayList<Vehicle> fleet) {
        System.out.println("Vehicles sorted by maintenance urgency (closest to service interval first): ");
        for (Vehicle vehicle : fleet) {
            System.out.println(vehicle.name + " ("
                    + vehicle.getCurrentMileage() + " km / "
                    + vehicle.getServiceInterval() + " km - "
                    + vehicle.mileageToNextService() + " km remaining)");
        }
        System.out.println("----------------------------------------\n");
    }

    /**
     * Displays vehicle by category.
     */
    public void displayVehiclesByCategory(String category, ArrayList<Vehicle> fleet) {
        System.out.println("\n--- Vehicles in " + category + " Category ---");

        if (fleet.isEmpty()) {
            System.out.println("No vehicles found in this category.");
        } else {
            for (Vehicle vehicle : fleet) {
                System.out.println(vehicle.name + " - Primary Function: " + vehicle.getPrimaryFunction()
                        + " - Fuel Type " + vehicle.getFuelType()); // calls vehicle.toString() for name + price
            }
        }

        System.out.println("----------------------------------------\n");
    }

}
