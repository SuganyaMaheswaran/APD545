package application.model;

public enum VehicleType {
    CAR("Car"), TRUCK("Truck"), FAMILY_VAN("Family Van");
    private final String label;
    VehicleType(String label) { this.label = label; }
    @Override public String toString() { return label; }
}