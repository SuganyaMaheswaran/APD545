package application.model;

public enum PaymentFrequency {
    WEEKLY("Weekly", 52), BI_WEEKLY("Bi-weekly", 26), MONTHLY("Monthly", 12);
    private final String label;
    private final int periodsPerYear;
    PaymentFrequency(String label, int periodsPerYear) {
        this.label = label;
        this.periodsPerYear = periodsPerYear;
    }
    public int getPeriodsPerYear() { return periodsPerYear; }
    @Override public String toString() { return label; }
}