package com.thebank.thebank.Models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Client {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty payeeAddress;
    private final ObjectProperty<LocalDate> dateCreated;

    public Client(String fName, String lName, String payeeAddress, LocalDate dateCreated){
        this.firstName = new SimpleStringProperty(this,"First Name",fName);
        this.lastName = new SimpleStringProperty(this,"Last Name",lName);
        this.payeeAddress = new SimpleStringProperty(this,"Payee Address",payeeAddress);
        this.dateCreated = new SimpleObjectProperty<>(this,"Date Created",dateCreated);
    }

    public StringProperty firstNameProperty(){
        return this.firstName;
    }
    public StringProperty lastNameProperty(){
        return this.lastName;
    }
    public StringProperty payeeAddressProperty(){
        return this.payeeAddress;
    }
    public ObjectProperty<LocalDate> dateCreatedProperty(){
        return this.dateCreated;
    }

}
