import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Bank Account Class to represent the user account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// ATM Class representing the ATM machine
public class ATM extends JFrame implements ActionListener {
    private BankAccount account;
    private JTextField amountField;
    private JTextArea displayArea;

    public ATM(BankAccount account) {
        this.account = account;

        // Frame setup
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Menu Bar setup
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem withdrawOption = new JMenuItem("Withdraw");
        JMenuItem depositOption = new JMenuItem("Deposit");
        JMenuItem balanceOption = new JMenuItem("Check Balance");
        menu.add(withdrawOption);
        menu.add(depositOption);
        menu.add(balanceOption);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Scrollbar setup for display area
        displayArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(displayArea);
        scroll.setBounds(50, 50, 300, 100);
        add(scroll);

        // Text field to enter amount
        amountField = new JTextField();
        amountField.setBounds(50, 160, 150, 30);
        add(amountField);

        // Button setup
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(210, 160, 100, 30);
        add(submitButton);

        // Action Listeners
        withdrawOption.addActionListener(e -> withdraw());
        depositOption.addActionListener(e -> deposit());
        balanceOption.addActionListener(e -> checkBalance());
        submitButton.addActionListener(this);
    }

    public void withdraw() {
        String amountStr = amountField.getText();
        try {
            double amount = Double.parseDouble(amountStr);
            if (account.withdraw(amount)) {
                displayArea.setText("Withdrawal successful.\nNew Balance: " + account.getBalance());
            } else {
                displayArea.setText("Error: Insufficient funds or invalid amount.");
            }
        } catch (NumberFormatException ex) {
            displayArea.setText("Error: Please enter a valid number.");
        }
    }

    public void deposit() {
        String amountStr = amountField.getText();
        try {
            double amount = Double.parseDouble(amountStr);
            account.deposit(amount);
            displayArea.setText("Deposit successful.\nNew Balance: " + account.getBalance());
        } catch (NumberFormatException ex) {
            displayArea.setText("Error: Please enter a valid number.");
        }
    }

    public void checkBalance() {
        displayArea.setText("Current Balance: " + account.getBalance());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // For submit button, this method will handle appropriate actions
    }

    public static void main(String[] args) {
        // Create a bank account with an initial balance of $1000
        BankAccount account = new BankAccount(1000.0);
        // Initialize ATM frame
        ATM atm = new ATM(account);
        atm.setVisible(true);
    }
}
