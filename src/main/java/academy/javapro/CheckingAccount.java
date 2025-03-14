package academy.javapro;

public class CheckingAccount extends Account {
    private double overdraftLimit;
    private static final double TRANSACTION_FEE = 1.5;

    public CheckingAccount(String accountNumber, String customerName, double initialBalance, double overdraftLimit) {
        super(accountNumber, customerName, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
        System.out.println("Overdraft limit updated to $" + String.format("%.2f", overdraftLimit));
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
            return;
        }
        if (getBalance() - amount < -overdraftLimit) {
            System.out.println("Cannot withdraw $" + amount + ". Overdraft limit exceeded.");
            return;
        }
        setBalance(getBalance() - amount);
        System.out.println("Withdrew $" + String.format("%.2f", amount) + " from checking account");
        if (getBalance() < 0) {
            setBalance(getBalance() - TRANSACTION_FEE);
            System.out.println("Transaction fee: $" + String.format("%.2f", TRANSACTION_FEE));
            System.out.println("Account is in overdraft. Current balance: $" + String.format("%.2f", getBalance()));
            logTransaction("WITHDRAWAL", amount);
            logTransaction("FEE", TRANSACTION_FEE);
        } else {
            logTransaction("WITHDRAWAL", amount);
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Account Type: Checking Account");
        System.out.println("Overdraft Limit: $" + String.format("%.2f", overdraftLimit));
        System.out.println("Transaction Fee: $" + String.format("%.2f", TRANSACTION_FEE));
    }
}