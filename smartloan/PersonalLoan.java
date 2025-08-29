package smartloan;

public class PersonalLoan extends LoanApplication implements Approvable {

    public PersonalLoan(String customerName, double amount) {
        super(customerName, amount, LoanType.PERSONAL);
    }

    @Override
    public boolean validateApplication() throws LoanException {
        if (amount <= 0) throw new LoanException("Invalid personal loan amount!");
        return true;
    }

    @Override
    @AuditLog
    public LoanStatus evaluateRisk() {
        if (amount > 500000) {
            status = LoanStatus.REJECTED;
        } else {
            status = LoanStatus.APPROVED;
        }
        return status;
    }
}
