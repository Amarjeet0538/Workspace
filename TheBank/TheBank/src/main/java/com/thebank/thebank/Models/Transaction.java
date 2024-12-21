package com.thebank.thebank.Models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Transaction {
    private final StringProperty sender;
    private final StringProperty receiver;
    private final DoubleProperty amount;
    private final ObjectProperty<LocalDate> date ;


    public Transaction(String sender, String receiver, Double amount,  LocalDate date) {
        this.sender = new SimpleStringProperty(this,"Sender",sender);
        this.receiver = new SimpleStringProperty(this,"Receiver",receiver);
        this.amount = new SimpleDoubleProperty(this,"Amount",amount);
        this.date = new SimpleObjectProperty<>(this,"Date",date);
    }


    public StringProperty SenderProperty() {
        return this.sender;
    }
    public StringProperty receiverProperty() {
        return this.receiver;
    }
    public DoubleProperty amountProperty() {
        return this.amount;
    }
    public ObjectProperty<LocalDate> dateProperty() {
        return this.date;
    }



}
