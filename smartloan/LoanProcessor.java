package smartloan;

@FunctionalInterface
public interface LoanProcessor {
    void process(LoanApplication app) throws LoanException;
}
