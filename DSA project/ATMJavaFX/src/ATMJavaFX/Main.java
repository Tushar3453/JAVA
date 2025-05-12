package ATMJavaFX;  // Package declaration

public class Main {
    public static void main(String[] args) {
        // Database connection details
        BankDatabase db = new BankDatabase("jdbc:mysql://localhost:3306/atm_db", "root", "Mosalah11");
        ATM atm = new ATM(db);
        atm.start();
        db.close(); // Close connection after use
    }
}
