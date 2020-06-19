package Oferta.controllers.sterge;

import Oferta.model.Oferta;
import Register.services.FileSystemService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.nio.file.Path;
import java.util.List;

public class TableOferte_sterge {
    private static List<Oferta> oferte;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("oferte", "oferte.json");
    private static Oferta of;
    @FXML
    private javafx.scene.control.TableView<Oferta> TableView;

    @FXML
    private TableColumn<Oferta,String> Data;

    @FXML
    private TableColumn<Oferta,String> Echipament;
    @FXML
    private TableColumn<Oferta,String> Pret;
    @FXML
    private TableColumn<Oferta,String> Bucati;
    @FXML
    private TableColumn<Oferta,String> Stare;



    public void Sterge(ActionEvent actionEvent) {
    }

    public void Anulare(ActionEvent actionEvent) {
    }
}
