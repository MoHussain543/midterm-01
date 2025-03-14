package academy.javapro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface AccountOperations {
    List<String> transactionHistory = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();

    default void logTransaction(String type, double amount) {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(formatter);
        String record = String.format("%s | %s | $%.2f | Balance: $%.2f", timestamp, type, amount, getBalance());
        transactionHistory.add(record);
    }

    default List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
}