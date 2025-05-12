package ATMJavaFX;
import java.util.Scanner;

public class ATM {
    private BankDatabase bankDB;
    private Scanner sc = new Scanner(System.in);

    public ATM(BankDatabase bankDB) {
        this.bankDB = bankDB;
    }

    public void start() {
        System.out.println("Welcome to ATM!");
        System.out.print("Enter Account Number: ");
        String accNum = sc.nextLine();
        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        if (bankDB.validateUser(accNum, pin)) {
            Account user = bankDB.getAccount(accNum);
            System.out.println("Login successful.\n");

            int choice;
            do {
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Mini Statement");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // to clear the buffer after nextInt()

                switch (choice) {
                    case 1:
                        System.out.println("Balance: " + user.getBalance());
                        pause();
                        break;

                    case 2:
                        System.out.print("Enter deposit amount (in rupees): ");
                        if (!sc.hasNextInt()) { // Check if input is not an integer
                            System.out.println("Invalid input. Please enter a valid integer value.");
                            sc.next(); // Clear the invalid input
                        } else {
                            int dep = sc.nextInt();
                            sc.nextLine(); // clear input buffer
                            if (dep > 0) {
                                user.deposit(dep);
                                System.out.println("Deposited successfully.");
                            } else {
                                System.out.println("Deposit amount must be positive.");
                            }
                        }
                        pause();
                        break;

                    case 3:
                        System.out.print("Enter withdrawal amount (in rupees): ");
                        if (!sc.hasNextInt()) { // Check if input is not an integer
                            System.out.println("Invalid input. Please enter a valid integer value.");
                            sc.next(); // Clear the invalid input
                        } else {
                            int amt = sc.nextInt();
                            sc.nextLine(); // clear buffer
                            if (amt <= 0) {
                                System.out.println("Withdrawal amount must be positive.");
                            } else if (user.withdraw(amt)) {
                                System.out.println("Withdrawn successfully.");
                            } else {
                                System.out.println("Insufficient balance.");
                            }
                        }
                        pause();
                        break;

                    case 4:
                        user.printMiniStatement();
                        pause();
                        break;

                    case 5:
                        System.out.println("Thank you for using ATM!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } while (choice != 5);
        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }

    // Method to pause the program until user presses enter
    private void pause() {
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }
}
