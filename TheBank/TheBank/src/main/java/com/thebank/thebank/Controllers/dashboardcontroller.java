package com.thebank.thebank.Controllers;

import com.thebank.thebank.Models.DatabaseDriver;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class dashboardcontroller implements Initializable {

    public Label login_date;
    public Text user_name; // Display "Hi, FirstName"
    public Label checking_acc_num; // Display "FirstName" as checking account number
    public Label checking_bal; // Display "Balance"
    public Text income_lbl;
    public Text expense_lbl;
    public ListView<String> trans_listview;
    public TextField client_username;
    public TextField send_money;
    public Button send_money_btn;
    public TextField withdraw_money;
    public Button withdraw_money_btn;
    public TextField deposit_money;
    public Button deposit_money_btn;
    public Label checking_username;

    private final DatabaseDriver databaseDriver;
    private String loggedInUsername;

    public dashboardcontroller() {
        this.databaseDriver = new DatabaseDriver();

    }

    public dashboardcontroller(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
        this.databaseDriver = new DatabaseDriver();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login_date.setText(LocalDate.now().toString());
        loadClientData(loggedInUsername);
    }

    public void onClick_Withdraw(ActionEvent actionEvent) {
        String amountStr = withdraw_money.getText();
        if (validateAmount(amountStr)) {
            double amount = Double.parseDouble(amountStr);
            if (withdraw(amount)) {
                withdraw_money.clear();
            }
        } else {
            showAlert("Invalid Amount", "Please enter a valid amount to withdraw.");
        }
    }

    public void onClick_Send(ActionEvent actionEvent) {
        String recipient = client_username.getText();
        String amountStr = send_money.getText();
        if (validateAmount(amountStr)) {
            double amount = Double.parseDouble(amountStr);
            if (sendMoney(recipient, amount)) {
                client_username.clear();
                send_money.clear();
            }
        } else {
            showAlert("Invalid Amount", "Please enter a valid amount to send.");
        }
    }

    public void onClick_Deposit(ActionEvent actionEvent) {
        String amountStr = deposit_money.getText();
        if (validateAmount(amountStr)) {
            double amount = Double.parseDouble(amountStr);
            if (deposit(amount)) {
                deposit_money.clear();
            }
        } else {
            showAlert("Invalid Amount", "Please enter a valid amount to deposit.");
        }
    }

    private boolean withdraw(double amount) {
        double currentBalance = databaseDriver.getBalance(loggedInUsername);
        if (currentBalance >= amount) {
            return databaseDriver.updateBalance(-amount, loggedInUsername);
        } else {
            showAlert("Insufficient Funds", "You do not have enough balance to withdraw this amount.");
            return false;
        }
    }

    private boolean deposit(double amount) {
        return databaseDriver.updateBalance(amount, loggedInUsername);
    }

    private boolean sendMoney(String recipient, double amount) {
        double currentBalance = databaseDriver.getBalance(loggedInUsername);
        if (currentBalance >= amount) {
            double recipientBalance = databaseDriver.getBalance(recipient);
            if (recipientBalance >= 0) { // Check if recipient exists
                databaseDriver.updateBalance(-amount, loggedInUsername);
                databaseDriver.updateBalance(amount, recipient);
                loadClientData(loggedInUsername); // Load updated data
                return true; // Transaction successful
            } else {
                showAlert("Error", "Recipient not found.");
                return false;
            }
        } else {
            showAlert("Insufficient Funds", "You do not have enough balance to send this amount.");
            return false;
        }
    }

    private boolean validateAmount(String amountStr) {
        try {
            double amount = Double.parseDouble(amountStr);
            return amount > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void loadClientData(String username) {
        try {
            ResultSet rs = databaseDriver.getClientData(username, "");
            if (rs.next()) {
                user_name.setText("Hi, " + rs.getString("FirstName"));
                checking_acc_num.setText(rs.getString("ID"));
                checking_bal.setText("$" + rs.getDouble("Balance"));
                checking_username.setText(rs.getString("FirstName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load client data.");
        }
    }

    public void setLoggedInUsername(String username) {
        this.user_name.setText("Hi, " + username);
        loadClientData(username);
    }
}
