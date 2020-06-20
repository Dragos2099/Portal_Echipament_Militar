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
import java.util.Objects;

public class Cerere {
    @FXML
    public TextField Echipament;
    @FXML
    public TextField Bucati;
    @FXML
    public ChoiceBox Urgent;
    @FXML
    public Text Mesaj;

    public void initialize() {
        Urgent.getItems().addAll("Da", "Nu");

    }


    public void Creare()  {
        if(Objects.equals(Bucati.getText(),"") || Objects.equals(Echipament.getText(), "") || (Objects.equals(Urgent.getValue(),""))) {
            Mesaj.setText("Completati toate campurile !");
        }
        else
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
