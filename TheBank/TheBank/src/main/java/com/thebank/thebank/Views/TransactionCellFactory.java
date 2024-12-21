package com.thebank.thebank.Views;

import com.thebank.thebank.Controllers.TransactionCellController;
import com.thebank.thebank.Models.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class TransactionCellFactory extends ListCell<Transaction> {
    @Override
    protected void updateItem(Transaction transaction, boolean empty) {
        super.updateItem(transaction, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/transactioncell.fxml"));
                TransactionCellController controller = new TransactionCellController(transaction);
                loader.setController(controller);
                setText(null);
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
