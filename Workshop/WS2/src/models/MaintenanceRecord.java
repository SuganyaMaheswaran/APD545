
package models;

import java.time.LocalDate;

public class MaintenanceRecord {
    private LocalDate date;
    private double cost;
    private String description;

    // Constructors
    public MaintenanceRecord(LocalDate dt, double cost, String description) {
        this.date = dt;
        this.cost = cost;
        this.description = description;
    }

    // Getters
    public double getCost() {
        return cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
