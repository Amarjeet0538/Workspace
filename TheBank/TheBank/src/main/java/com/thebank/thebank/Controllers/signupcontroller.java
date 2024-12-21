package com.thebank.thebank.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.lang.Math;

public class signupcontroller implements Initializable {

    public TextField password_fld;
    public TextField balance_fld;
    public Label username_lbl;
    @FXML
    private ResourceBundle resources;
    @FXML
    private TextField first_name_fld;
    @FXML
    private TextField last_name_fld;

    private int Balance ;


    private Stage signupWindow ;


    signupcontroller(){
        int rand = (int)Math.random() * 10;
        String username = (first_name_fld.getText()) + (last_name_fld.getText()) + rand;
    }



    @FXML
    private URL location;

    @FXML
    void onClick_Signup_Button(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
