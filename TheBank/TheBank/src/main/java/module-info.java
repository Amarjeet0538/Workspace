module com.thebank.thebank {
    requires javafx.controls;
    requires javafx.fxml;


    requires  de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;



    opens com.thebank.thebank.Controllers to javafx.fxml;
    exports com.thebank.thebank;
    exports com.thebank.thebank.Controllers;
    exports com.thebank.thebank.Models;
    exports com.thebank.thebank.Views;

}
