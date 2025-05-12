package ATMJavaFX;  // Package declaration

public class Main {
    public static void main(String[] args) {
        String filePath = "./data/accounts.txt";  
        BankDatabase db = new BankDatabase(filePath);  
        ATM atm = new ATM(db); 
        atm.start();  
        db.saveAccountsToFile(filePath);  
    }
}