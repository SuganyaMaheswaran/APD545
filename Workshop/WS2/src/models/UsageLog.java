package models;

import java.time.LocalDate;

public class UsageLog {
    private LocalDate startDate;
    private LocalDate endDate;
    private double distance;

    // Constructors
    public UsageLog(LocalDate startDt, LocalDate endDt, double distance) {
        this.startDate = startDt;
        this.endDate = endDt;
        this.distance = distance;
    }

    // Getters
    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getDistance() {
        return distance;
    }

    // Setters
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // String methods
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
