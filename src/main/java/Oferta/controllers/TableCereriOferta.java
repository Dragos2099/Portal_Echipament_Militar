package Oferta.controllers;

import Cerere.model.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class TableCereriOferta {
    @FXML
    private javafx.scene.control.TableView<Request> TableView;

    @FXML
    private TableColumn<Request,String> Echipament;

    @FXML
    private TableColumn<Request,String> Bucati;

    @FXML
    private TableColumn<Request,String> Urgent;
    public void selectareCerere(ActionEvent actionEvent) throws IOException {
        Parent Log_in = FXMLLoader.load(getClass().getResource("/Oferta/Oferta.fxml"));
        Stage stage =new Stage() ;
        stage.setTitle("Portal Echipament Militar");
        Scene scene = new Scene(Log_in, 360, 490);
        stage.setScene(scene);
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

    }
}
