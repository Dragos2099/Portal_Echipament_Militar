package Cerere.controllers;

import Cerere.exceptions.CerereAlreadyExistsException;
import Cerere.services.CerereService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class Cerere {
    @FXML
    public TextField Echipament;
    @FXML
    public TextField Bucati;
    @FXML
    public ChoiceBox Urgent;
    @FXML
    private Text Mesaj;

    public void initialize() {
        Urgent.getItems().addAll("Da", "Nu");

    }


    public void Creare(ActionEvent actionEvent)  {

        try{
            CerereService.addCereri(Echipament.getText(),Bucati.getText(),(String) Urgent.getValue());
            Mesaj.setText("Cerere creata !");
        } catch (
                CerereAlreadyExistsException e) {
            Mesaj.setText(e.getMessage());
        }
    }

    public void Anulare(ActionEvent actionEvent) {

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
