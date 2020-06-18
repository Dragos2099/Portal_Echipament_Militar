package Oferta.controllers;

import Cerere.model.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class TableCereriOferta {
    @FXML
    private javafx.scene.control.TableView<Request> TableView;

    @FXML
    private TableColumn<Request,String> Echipament;

    @FXML
    private TableColumn<Request,String> Bucati;

    @FXML
    private TableColumn<Request,String> Urgent;
    public void selectareCerere(ActionEvent actionEvent) {
    }
}
