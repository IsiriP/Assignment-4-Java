package smartloan;

public class HomeLoan extends LoanApplication implements Approvable {

    public HomeLoan(String customerName, double amount) {
        super(customerName, amount, LoanType.HOME);
    }

    @Override
    public boolean validateApplication() throws LoanException {
        if (amount <= 0) throw new LoanException("Invalid home loan amount!");
        return true;
    }

    @Override
    @AuditLog
    public LoanStatus evaluateRisk() {
        if (amount < 5000000) {
            status = LoanStatus.APPROVED;
        } else {
            status = LoanStatus.REJECTED;
        }
        return status;
    }
}
