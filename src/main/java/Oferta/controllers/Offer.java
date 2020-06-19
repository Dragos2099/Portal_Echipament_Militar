package Oferta.controllers;

import Oferta.services.OfertaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class Offer {

    @FXML
    public TextField Bucati;
    @FXML
    public TextField Pret;
    @FXML
    public TextField Echipament;
    @FXML
    public DatePicker Date;
    @FXML
    public Text Mesaj;

    public void Creare(ActionEvent actionEvent) throws IOException {
        String s="Asteptare";

            OfertaService.addOferte(Echipament.getText(),Bucati.getText(),Pret.getText(), Date.getValue().toString(),s);
            Mesaj.setText("Oferta creata !");

    }

    public void Anulare(ActionEvent actionEvent) {
    }
}
