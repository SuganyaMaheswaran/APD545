package application.service;

import application.model.AmortizationEntry;
import application.model.Loan;
import java.util.ArrayList;
import java.util.List;

public class AmortizationService {
	private final LoanCalculation loanCalculation;

    public AmortizationService() {
        this.loanCalculation = new FixedRateLoan(); 
    }
    public AmortizationService(LoanCalculation loanCalculation) {
        this.loanCalculation = loanCalculation;
    }
    
    public List<AmortizationEntry> generateSchedule(Loan loan) {
        List<AmortizationEntry> schedule = new ArrayList<>();
        double principalBalance = loan.getVehicle().getPrice() - loan.getDownPayment();

        System.out.println("[AmortizationService] Starting amortization schedule generation.");
        System.out.println("  Vehicle price=" + loan.getVehicle().getPrice() + ", down payment=" + loan.getDownPayment() + ", initial principal=" + principalBalance);

        if (principalBalance <= 0) {
            System.out.println("[AmortizationService] Aborting schedule because principal balance is <= 0.");
            return schedule;
        }

        int periodsPerYear = loan.getFrequency().getPeriodsPerYear();
        int totalPayments = (int) Math.round((loan.getDuration() / 12.0) * periodsPerYear);
        double annualRate = loan.getInterestRate() / 100.0;
        double periodRate = annualRate / periodsPerYear;
        double payment = loanCalculation.calculatePayment(loan);

        System.out.println("  Frequency=" + loan.getFrequency() + ", periods per year=" + periodsPerYear + ", total payments=" + totalPayments);
        System.out.println("  Annual rate=" + loan.getInterestRate() + "% -> period rate=" + periodRate);
        System.out.println("  Calculated payment per period=" + payment);

        if (totalPayments <= 0) {
            System.out.println("[AmortizationService] Aborting schedule because total payments <= 0.");
            return schedule;
        }

        for (int i = 1; i <= totalPayments; i++) {
            double interestAmount = principalBalance * periodRate;
            double principalAmount = payment - interestAmount;

            if (i == totalPayments || principalAmount > principalBalance) {
                principalAmount = principalBalance;
                payment = principalAmount + interestAmount;
                principalBalance = 0;
            } else {
                principalBalance -= principalAmount;
            }

            schedule.add(new AmortizationEntry(i, principalAmount, interestAmount, principalBalance));
            System.out.println(String.format("  Payment %d => principal=%.2f, interest=%.2f, balance=%.2f", i, principalAmount, interestAmount, principalBalance));
            if (principalBalance <= 0) break;
        }

        System.out.println("[AmortizationService] Finished schedule generation. Entries=" + schedule.size());
        return schedule;
    }
}
