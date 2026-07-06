package application.model;

import javafx.beans.property.*;

public class Loan {
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final DoubleProperty downPayment = new SimpleDoubleProperty();
    private final DoubleProperty interestRate = new SimpleDoubleProperty();
    private final IntegerProperty months = new SimpleIntegerProperty();
    private final ObjectProperty<PaymentFrequency> frequency = new SimpleObjectProperty<>();  
    // Add Customer and Vehicle properties
    private final ObjectProperty<Customer> customer = new SimpleObjectProperty<>();
    private final ObjectProperty<VehicleModel> vehicle = new SimpleObjectProperty<>();

    public Loan(double price, double downPayment, double interestRate, int months, PaymentFrequency frequency, Customer customer, VehicleModel vehicle) {
        this.price.set(price);
        this.downPayment.set(downPayment);
        this.interestRate.set(interestRate);
        this.months.set(months);
        this.frequency.set(frequency);
        this.customer.set(customer);
        this.vehicle.set(vehicle);
    }

 
    public DoubleProperty priceProperty() { return price; }
    public DoubleProperty downPaymentProperty() { return downPayment; }
    public DoubleProperty interestRateProperty() { return interestRate; }
    public IntegerProperty monthsProperty() { return months; }
    public ObjectProperty<PaymentFrequency> frequencyProperty() { return frequency; }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(customer.get() != null ? customer.get().getName() : "");
    }

    public StringProperty phoneProperty() {
        return new SimpleStringProperty(customer.get() != null ? customer.get().getPhone() : "");
    }

    public DoubleProperty loanAmountProperty() {
        return new SimpleDoubleProperty(getLoanAmount());
    }

    public StringProperty vehicleTypeProperty() {
        // Return a string property based on vehicle model
        return new SimpleStringProperty(vehicle.get() != null ? vehicle.get().toString() : "N/A");
    }
     public Customer getCustomer() {
        return this.customer.get();
    }

    public double getLoanAmount() { return price.get() - downPayment.get(); }

    public double calculateMonthlyPayment() {
        double p = getLoanAmount();
        double i = getMonthlyRate();
        int n = months.get();
        if (interestRate.get() <= 0) {
            return p / n;
        }
        return (p * i) / (1 - Math.pow(1 + i, -n));
    }
    
    public double getMonthlyRate() {
        return (interestRate.get() / 100.0) / 12.0;
    }
    public String getName() {
        if (customer.get() != null) {
            return customer.get().getName(); 
        }
        return "";
    }

    public String getPhoneNumber() {
        if (customer.get() != null) {
            return customer.get().getPhone();
        }
        return "";
    }

    public String getVehicleType() {
        if (vehicle.get() != null) {
           
            return vehicle.get().toString(); 
        }
        return "";
    }
    
    public VehicleModel getVehicle() {
        return this.vehicle.get();
    }

    public double getDownPayment() {
        return this.downPayment.get();
    }

    public double getDuration() {
        return this.months.get(); 
    }

    public double getInterestRate() {
        return this.interestRate.get();
    }
    public PaymentFrequency getFrequency() {
        return frequency.get();
    }

    @Override
    public String toString() {
        if (customer.get() != null) {
            return customer.get().getName() + " - " + frequency.get() + " Payment";
        }
        return "Saved Loan Application";
    }
}