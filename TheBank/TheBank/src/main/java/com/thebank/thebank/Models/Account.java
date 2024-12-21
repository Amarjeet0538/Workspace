package com.thebank.thebank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Account {
    private final StringProperty owner;
    private final StringProperty account_number;
    private final DoubleProperty balance ;

    public Account(String owner, String account_number, Double balance) {
        this.owner = new SimpleStringProperty(this,"Owner",owner);
        this.account_number = new SimpleStringProperty(this,"Account Number",account_number);
        this.balance = new SimpleDoubleProperty(this,"Balance",balance);
    }

    public StringProperty ownerProperty() {
        return this.owner;
    }

    public StringProperty accountNumberProperty() {
        return this.account_number;
    }

    public DoubleProperty amountProperty() {
        return this.balance;
    }
}
