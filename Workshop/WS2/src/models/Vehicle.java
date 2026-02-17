package models;

public class Vehicle {
    private String make;
    private String model;
    private int year;
    private String type;

    // Constructor

    public Vehicle(String make, String model, int year, String type) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
    }

    // Getters
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    // Setters
    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return make + " " + model + " (" + year + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Vehicle))
            return false;

        Vehicle v = (Vehicle) o;
        return year == v.year &&
                make.equalsIgnoreCase(v.make) &&
                model.equalsIgnoreCase(v.model) &&
                type.equalsIgnoreCase(v.type);
    }

}