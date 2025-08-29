package smartloan;

public interface Approvable {
    LoanStatus evaluateRisk();

    default void printDecision() {
        System.out.println("Final Decision: " + evaluateRisk());
    }
}
