package com.thebank.thebank.Models;

import javafx.scene.control.Alert;

import java.sql.*;

public class DatabaseDriver {
    private Connection conn;

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Amarjeet\\Downloads\\TheBank\\TheBank\\thebank.db");
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getBalance(String username) {
        double balance = 0.0;
        String query = "SELECT Balance FROM Clients WHERE PayeeAddress = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("Balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to retrieve balance.");
        }
        return balance;
    }

    public boolean updateBalance(double amount, String username) {
        String sqlUpdate = "UPDATE Clients SET Balance = Balance + ? WHERE PayeeAddress = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
            pstmt.setDouble(1, amount);
            pstmt.setString(2, username);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Balance updated successfully for user: " + username);
                return true;
            } else {
                System.out.println("No rows affected. Check if username exists in the database.");
                showAlert("Error", "No record found to update balance.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to update balance. " + e.getMessage());
            return false;
        }
    }

    public ResultSet getClientData(String username, String password) {
        ResultSet resultSet = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Clients WHERE PayeeAddress = ? AND Password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            resultSet = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Connection getConnection() {
        return conn;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void closeConnection() {
        try {
            if (conn != null  && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
