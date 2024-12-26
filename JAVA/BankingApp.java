import java.util.Scanner;

class Account {
    private String owner;
    private double balance;

    public Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
        } else {
            System.out.println("Invalid withdrawal amount");
        }
    }
}

class Bank {
    private Account[] accounts;
    private int numOfAccounts;

    public Bank() {
        accounts = new Account[100];
        numOfAccounts = 0;
    }

    public void addAccount(Account account) {
        if (numOfAccounts < accounts.length) {
            accounts[numOfAccounts++] = account;
            System.out.println("Account for " + account.getOwner() + " added.");
        } else {
            System.out.println("Bank is full, cannot add more accounts.");
        }
    }

    public Account findAccount(String owner) {
        for (int i = 0; i < numOfAccounts; i++) {
            if (accounts[i].getOwner().equalsIgnoreCase(owner)) {
                return accounts[i];
            }
        }
        return null;
    }
}

public class BankingApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    addNewAccount();
                    break;
                case 2:
                    depositToAccount();
                    break;
                case 3:
                    withdrawFromAccount();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        System.out.println("Thank you for using the banking app.");
    }

    private static void printMenu() {
        System.out.println("\nBanking App Menu:");
        System.out.println("1. Add new account");
        System.out.println("2. Deposit to account");
        System.out.println("3. Withdraw from account");
        System.out.println("4. Check balance");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addNewAccount() {
        System.out.print("Enter owner name: ");
        String owner = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        Account account = new Account(owner, balance);
        bank.addAccount(account);
    }

    private static void depositToAccount() {
        System.out.print("Enter owner name: ");
        String owner = scanner.nextLine();
        Account account = bank.findAccount(owner);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();  // Consume newline
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawFromAccount() {
        System.out.print("Enter owner name: ");
        String owner = scanner.nextLine();
        Account account = bank.findAccount(owner);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();  // Consume newline
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter owner name: ");
        String owner = scanner.nextLine();
        Account account = bank.findAccount(owner);
        if (account != null) {
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}