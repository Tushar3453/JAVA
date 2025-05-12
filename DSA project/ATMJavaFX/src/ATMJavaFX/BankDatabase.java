package ATMJavaFX;

import java.sql.*;

public class BankDatabase {
    private Connection connection;

    // Constructor to initialize the connection
    public BankDatabase(String dbUrl, String user, String password) {
        try {
            // MySQL JDBC connection establish karna
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
    }

    // Method to get Account by account number
    public Account getAccount(String accountNumber) {
        Account account = null;
        String query = "SELECT * FROM accounts WHERE account_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String accNum = rs.getString("account_number");
                String pin = rs.getString("pin");
                double balance = rs.getDouble("balance");
                account = new Account(accNum, pin, balance);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching account: " + e.getMessage());
        }
        return account;
    }

    // Method to save or update account in database
    public void saveAccount(Account account) {
        String query = "REPLACE INTO accounts (account_number, pin, balance) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, account.getAccountNumber());
            stmt.setString(2, account.getPin());
            stmt.setDouble(3, account.getBalance());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving account: " + e.getMessage());
        }
    }

    // Method to validate user credentials
    public boolean validateUser(String accNum, String pin) {
        String query = "SELECT * FROM accounts WHERE account_number = ? AND pin = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accNum);
            stmt.setString(2, pin);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // If result found, valid user
        } catch (SQLException e) {
            System.out.println("Error validating user: " + e.getMessage());
            return false;
        }
    }

    // Close the database connection
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}

