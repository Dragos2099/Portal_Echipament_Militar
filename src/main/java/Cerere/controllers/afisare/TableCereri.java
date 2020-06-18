package Cerere.controllers.afisare;

import Cerere.model.Request;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableCereri {
    @FXML
    public javafx.scene.control.TableView<Request> TableView;
    @FXML
    public TableColumn<Request,String> Echipament;
    @FXML
    public TableColumn<Request,String> Bucati;
    @FXML
    public TableColumn<Request,String>Urgent;
}
