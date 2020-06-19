package Oferta.controllers;

import Oferta.exceptions.OfertaAlreadyExistsException;
import Oferta.services.OfertaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

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

    public void Creare(ActionEvent actionEvent)  {
        String s="Asteptare";

        if(Objects.equals(Echipament.getText(),"") || Objects.equals(Bucati.getText(),"") || Objects.equals(Pret.getText(),"") || Date.getValue()==null)
            Mesaj.setText("Completati toate campurile !");
          else {
            try {
                OfertaService.addOferte(Echipament.getText(),Bucati.getText(),Pret.getText(), Date.getValue().toString(),s);
                Mesaj.setText("Oferta creata !");
            } catch (OfertaAlreadyExistsException e) {
                Mesaj.setText(e.getMessage());
            }
        }

    }

    public void Anulare(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
