package application.model;


import javafx.beans.property.*;

public class AmortizationEntry {
    private final IntegerProperty month = new SimpleIntegerProperty();
    private final DoubleProperty principal = new SimpleDoubleProperty();
    private final DoubleProperty interest = new SimpleDoubleProperty();
    private final DoubleProperty balance = new SimpleDoubleProperty();

    public AmortizationEntry(int month, double principal, double interest, double balance) {
        this.month.set(month);
        this.principal.set(principal);
        this.interest.set(interest);
        this.balance.set(balance);
    }

    public IntegerProperty monthProperty() { return month; }
    public DoubleProperty principalProperty() { return principal; }
    public DoubleProperty interestProperty() { return interest; }
    public DoubleProperty balanceProperty() { return balance; }
}