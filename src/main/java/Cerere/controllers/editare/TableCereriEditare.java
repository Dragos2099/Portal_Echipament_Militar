package Cerere.controllers.editare;

import Cerere.model.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class TableCereriEditare {
    @FXML
    private javafx.scene.control.TableView<Request> TableView;

    @FXML
    private TableColumn<Request,String> Echipament;

    @FXML
    private TableColumn<Request,String> Bucati;

    @FXML
    private TableColumn<Request,String> Urgent;
    public void changeEchipament(TableColumn.CellEditEvent<Request, String> requestStringCellEditEvent ) {

    }

    public void changeBuc(TableColumn.CellEditEvent<Request, String> requestStringCellEditEvent) {

    }

    public void changeUrgenta(TableColumn.CellEditEvent<Request, String> requestStringCellEditEvent) {

    }

    public void Salvare(ActionEvent actionEvent) {
    }

    public void Anulare(ActionEvent actionEvent) {
    }
}
