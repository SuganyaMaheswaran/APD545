package application.model;

public enum VehicleAge {
    NEW("New"), USED("Used");
    private final String label;
    VehicleAge(String label) { this.label = label; }
    @Override public String toString() { return label; }
}