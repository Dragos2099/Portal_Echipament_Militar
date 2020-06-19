package Contract.controllers.afisare;

import Contract.model.Contracts;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableContracte {

    @FXML
    private javafx.scene.control.TableView<Contracts> TableView;

    @FXML
    private TableColumn<Contracts,String> Data;

    @FXML
    private TableColumn<Contracts,String> Echipamente;
    @FXML
    private TableColumn<Contracts,String> Total;
    @FXML
    private TableColumn<Contracts,String> Buc;
    @FXML
    private TableColumn<Contracts,String> Nr_bm;
    @FXML
    private TableColumn<Contracts,String> Nr_f;


}
