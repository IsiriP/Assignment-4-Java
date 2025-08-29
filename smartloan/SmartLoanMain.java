package smartloan;

import java.lang.reflect.Method;
import java.util.*;

public class SmartLoanMain {

    private static Map<LoanType, List<LoanApplication>> processedApps = new HashMap<>();

    public static <T extends LoanApplication> void printApplications(List<T> apps, String title) {
        System.out.println("\n--- " + title + " ---");
        for (T app : apps) {
            System.out.println(app);
        }
    }

    public static void printAuditedMethods(Class<?> clazz) {
        System.out.println("\n--- Audited Methods ---");
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(AuditLog.class)) {
                System.out.println("Audited: " + method.getName() + " in " + clazz.getSimpleName());
            }
        }
    }

    public static void main(String[] args) {
        LoanProcessor processor = (app) -> {
            if (app.validateApplication()) {
                app.apply();
                if (app instanceof Approvable) {
                    ((Approvable) app).printDecision();
                }
            }
        };

        List<LoanApplication> apps = Arrays.asList(
                new HomeLoan("Alice", 4000000),
                new HomeLoan("Bob", 6000000),
                new PersonalLoan("Charlie", 200000),
                new PersonalLoan("David", 700000)
        );

        for (LoanApplication app : apps) {
            try {
                processor.process(app);

                processedApps.computeIfAbsent(app.type, k -> new ArrayList<>()).add(app);
            } catch (LoanException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }

        for (LoanType type : processedApps.keySet()) {
            printApplications(processedApps.get(type), type + " LOANS");
        }
        printAuditedMethods(HomeLoan.class);
        printAuditedMethods(PersonalLoan.class);

        System.out.println("\n--- Sorted Applications by Amount ---");
        apps.stream().sorted((a, b) -> Double.compare(b.amount, a.amount))
                .forEach(System.out::println);
    }
}
