package ATMJavaFX;
import java.util.Stack;

public class Account {
    private String accountNumber;
    private String pin;
    private double balance;
    private Stack<String> transactionHistory;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new Stack<>();
    }

    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        transactionHistory.push("Deposited: " + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.push("Withdrew: " + amount);
            return true;
        } else {
            return false;
        }
    }

    public void printMiniStatement() {
        System.out.println("Last 5 Transactions:");
        int count = 0;
        for (int i = transactionHistory.size() - 1; i >= 0 && count < 5; i--, count++) {
            System.out.println(transactionHistory.get(i));
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public String toFileString() {
        return accountNumber + "," + pin + "," + balance;
    }
}
