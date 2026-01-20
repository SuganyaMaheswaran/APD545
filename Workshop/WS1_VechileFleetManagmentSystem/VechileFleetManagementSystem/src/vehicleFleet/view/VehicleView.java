package vehicleFleet.view;

import java.util.ArrayList;
import java.util.Scanner;
import vehicleFleet.model.Vehicle;

public class VehicleView {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to enter the current mileage for a vehicle.
     */
    public int getCurrentMileage(String vehicleType) {
        System.out.print("Enter current mileage for " + vehicleType + " (km): ");
        return scanner.nextInt();
    }

    /**
     * Prompts the user to select a vehicle type to add to the fleet.
     * Returns null if the user wants to exit.
     */
    public String promptVehicleType() {
        String vehicleType;

        do {
            System.out.print("Enter the type of vehicle to add (Sedan, SUV, Truck, Van, Ambulance) or type 'N' to exit: ");
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
    public void displayFleet(ArrayList <Vehicle> fleet){
        if(fleet.isEmpty()){
            System.out.println("The fleet is empty");
            return;
        }

        System.out.println("\n--- Fleet of Vehicles  ---");
        for (Vehicle vehicle : fleet){
            vehicle.toString();
        }
          System.out.println("---------------------------------------------------\n");
    }

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
        public void displayVehiclesByCategory(String category, ArrayList<Vehicle> fleet) {
            System.out.println("\n--- Vehicles in " + category + " Category ---");

            if (fleet.isEmpty()) {
                System.out.println("No vehicles found in this category.");
            } else {
                for (Vehicle vehicle : fleet) {
                    System.out.println(vehicle.name+ " - Primary Function: "+ vehicle.getPrimaryFunction() +" - Fuel Type " + vehicle.getFuelType()); // calls vehicle.toString() for name + price
                }
            }

            System.out.println("----------------------------------------\n");
        }
}
