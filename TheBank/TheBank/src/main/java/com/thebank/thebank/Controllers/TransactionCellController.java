package com.thebank.thebank.Controllers;

import com.thebank.thebank.Models.Transaction;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {

    public Label trans_date_lbl;
    public Label trans_reciever_lbl;
    public Label trans_amt_lbl;
    public Label trans_sender_lbl;

    private final Transaction transaction;

    public TransactionCellController(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
