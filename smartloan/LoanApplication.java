package smartloan;

public abstract class LoanApplication {
    private static int counter = 1000;
    protected int applicationId;
    protected String customerName;
    protected double amount;
    protected LoanType type;
    protected LoanStatus status;

    // Static block
    static {
        System.out.println("SmartLoan System initialized.");
    }

    // Constructor
    public LoanApplication(String customerName, double amount, LoanType type) {
        this.applicationId = ++counter;
        this.customerName = customerName;
        this.amount = amount;
        this.type = type;
        this.status = LoanStatus.PENDING;
    }

    // Abstract
    public abstract boolean validateApplication() throws LoanException;

    // Concrete
    public void apply() {
        this.status = LoanStatus.PENDING;
    }

    @Override
    public String toString() {
        return "ApplicationID: " + applicationId + " | Customer: " + customerName +
                " | Amount: " + amount + " | Type: " + type + " | Status: " + status;
    }
}
