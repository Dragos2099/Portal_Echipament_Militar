package Cerere.controllers;

import Cerere.services.CerereService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

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


    public void Creare(ActionEvent actionEvent) throws IOException {
        CerereService.addCereri(Echipament.getText(),Bucati.getText(),(String) Urgent.getValue());
    }

    public void Anulare(ActionEvent actionEvent) {

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
