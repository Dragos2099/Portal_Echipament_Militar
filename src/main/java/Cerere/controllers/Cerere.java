package Cerere.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Cerere {
    @FXML
    public TextField Echipament;
    @FXML
    public TextField Bucati;
    @FXML
    public ChoiceBox Urgent;

    public void initialize() {
        Urgent.getItems().addAll("Da", "Nu");

    }

    
}
