package application.repository;

import application.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanRepository {

    private final ObservableList<Loan> savedLoans =
            FXCollections.observableArrayList();

    public void saveLoan(Loan record) {
        savedLoans.add(record);
    }

    public ObservableList<Loan> getAllLoans() {
        return savedLoans;
    }
    public void seedDatabase() {
        if (savedLoans.isEmpty()) {
            addSampleLoan("John Doe", "4161112222", "Toronto", "ON", 35000, 1.99, 48, PaymentFrequency.MONTHLY);
            addSampleLoan("Jane Smith", "6473334444", "Mississauga", "ON", 28000, 2.99, 36, PaymentFrequency.MONTHLY);
            addSampleLoan("Robert Brown", "9055556666", "Oakville", "ON", 42000, 0.99, 60, PaymentFrequency.WEEKLY);
        }
    }

    private void addSampleLoan(String name, String phone, String city, String province, 
                               double price, double interest, int months, PaymentFrequency freq) {
        
        Customer c = new Customer();
        c.setName(name);
        c.setPhone(phone);
        c.setCity(city);
        c.setProvince(province);

        VehicleModel v = new VehicleModel();
        v.setPrice(price);
        // Setting defaults for seed data
        v.setType(VehicleType.CAR); 
        v.setAge(VehicleAge.NEW);

        saveLoan(new Loan(price, (price * 0.1), interest, months, freq, c, v));
    }
    public void updateLoan(Loan oldLoan, Loan updatedLoan) {
        int index = savedLoans.indexOf(oldLoan);
        if (index >= 0) {
            savedLoans.set(index, updatedLoan);
        }
    }

}