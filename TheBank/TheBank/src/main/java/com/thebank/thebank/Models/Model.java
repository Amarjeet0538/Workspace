package com.thebank.thebank.Models;

import com.thebank.thebank.Views.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;

    private final Client client;
    private boolean clientLoginSucessFlag ;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.client = new Client("","","",null);
        this.clientLoginSucessFlag = false;
    }

    public static synchronized Model getInstance() {
        if (model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public boolean getClientLoginSucessFlag() {
        return clientLoginSucessFlag;
    }

    public void setClientLoginSucessFlag(boolean flag) {
        this.clientLoginSucessFlag = flag;
    }

    public Client getClient() {
        return client;
    }

    public void evaluateClientCred(String pAddress, String password) {
        ResultSet resultSet = databaseDriver.getClientData(pAddress, password);
        try {
            if (resultSet.isBeforeFirst()) {
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastNameProperty().set(resultSet.getString("LastName"));
                this.client.payeeAddressProperty().set(resultSet.getString("PayeeAddress"));

                String dateString = resultSet.getString("Date");
                LocalDate date = LocalDate.parse(dateString);
                this.client.dateCreatedProperty().set(date);

                this.clientLoginSucessFlag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

