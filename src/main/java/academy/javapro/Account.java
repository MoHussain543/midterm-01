package academy.javapro;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements AccountOperations {
    private final String accountNumber;
    private final String customerName;
    private double balance;
    private final List<String> transactionHistory = new ArrayList<>();

    public Account(String accountNumber, String customerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = initialBalance;
        logTransaction("INITIAL_DEPOSIT", initialBalance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive");
            return;
        }
        balance += amount;
        logTransaction("DEPOSIT", amount);
    }

    @Override
    public abstract void withdraw(double amount);

    @Override
    public void logTransaction(String type, double amount) {
        String record = String.format("%s | $%.2f | Balance: $%.2f", type, amount, balance);
        transactionHistory.add(record);
    }

    @Override
    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public void displayInfo() {
        System.out.println("===== Account Information =====");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Customer Name: " + getCustomerName());
        System.out.println("Balance: $" + String.format("%.2f", balance));
    }
}