package com.thebank.thebank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CheckingAccount extends Account {

    private final IntegerProperty transaction_lmt;

    public CheckingAccount(String owner , String account_number , Double balance , int transaction_lmt) {
        super(owner,account_number,balance);
        this.transaction_lmt = new SimpleIntegerProperty(this , "Transaction Limit",transaction_lmt);
    }

    public IntegerProperty transaction_lmtProperty() {
        return transaction_lmt;
    }
}
