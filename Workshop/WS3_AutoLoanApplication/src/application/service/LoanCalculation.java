package application.service;

import application.model.Loan;

public interface LoanCalculation {
    double calculatePayment(Loan loan);
}