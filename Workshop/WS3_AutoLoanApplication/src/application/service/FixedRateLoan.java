package application.service;

import application.model.Loan;

public class FixedRateLoan implements LoanCalculation {
    @Override
    public double calculatePayment(Loan loan) {
        double principal = loan.getVehicle().getPrice() - loan.getDownPayment();
        if (principal <= 0) return 0.0;

        double annualRate = loan.getInterestRate() / 100.0;
        int periodsPerYear = loan.getFrequency().getPeriodsPerYear();
        double periodRate = annualRate / periodsPerYear;
        
        // Total periods based on monthly slider duration adjusted to frequency context
        int totalPayments = (int) Math.round((loan.getDuration() / 12.0) * periodsPerYear);

        if (periodRate == 0) return principal / totalPayments;

        return (principal * periodRate * Math.pow(1 + periodRate, totalPayments)) / 
               (Math.pow(1 + periodRate, totalPayments) - 1);
    }
}