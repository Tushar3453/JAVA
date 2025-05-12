package ATMJavaFX;
import java.io.*;
import java.util.*;

public class BankDatabase {
    private HashMap<String, Account> accounts = new HashMap<>();

    public BankDatabase(String filename) {
        loadAccountsFromFile(filename);
    }

    private void loadAccountsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String accNum = data[0];
                String pin = data[1];
                double balance = Double.parseDouble(data[2]);
                Account acc = new Account(accNum, pin, balance);
                accounts.put(accNum, acc);
            }
        } catch (IOException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    public void saveAccountsToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Account acc : accounts.values()) {
                pw.println(acc.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean validateUser(String accNum, String pin) {
        Account acc = accounts.get(accNum);
        return acc != null && acc.validatePin(pin);
    }
}
