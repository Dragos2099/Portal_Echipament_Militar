package Cerere.controllers.editare;

import Cerere.exceptions.CouldNotWriteCerereException;
import Cerere.model.Request;
import Cerere.services.CerereService;
import Register.services.FileSystemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TableCereriEditare implements Initializable {
    private static List<Request> cereri;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("cerere", "cerere.json");
    @FXML
    public javafx.scene.control.TableView<Request> TableView;

    @FXML
    public TableColumn<Request,String> Echipament;

    @FXML
    public TableColumn<Request,String> Bucati;

    @FXML
    public TableColumn<Request,String> Urgent;

    public void initialize(URL location, ResourceBundle resources) {

        Echipament.setCellValueFactory(new PropertyValueFactory<>("Echipament"));
        Bucati.setCellValueFactory(new PropertyValueFactory<>("Bucati"));
        Urgent.setCellValueFactory(new PropertyValueFactory<>("Urgent"));

        try {
            TableView.setItems(getPeople());
        } catch (IOException e) {
            e.printStackTrace();
        }

        TableView.setEditable(true);
        Echipament.setCellFactory((TextFieldTableCell.forTableColumn()));
        Bucati.setCellFactory((TextFieldTableCell.forTableColumn()));
        Urgent.setCellFactory((TextFieldTableCell.forTableColumn()));
    }

    private ObservableList<Request> getPeople() throws IOException {
        ObservableList<Request> requests= FXCollections.observableArrayList();

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(CerereService.class.getClassLoader().getResource("cerere.json")), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper2 = new ObjectMapper();

        cereri = objectMapper2.readValue(USERS_PATH.toFile(), new TypeReference<List<Request>>() {
        });

        requests.addAll(cereri);
        return requests;
    }


    public void changeEchipament(TableColumn.CellEditEvent<Request, String> requestStringCellEditEvent ) {
        Request req=TableView.getSelectionModel().getSelectedItem();
        req.setEchipament(requestStringCellEditEvent.getNewValue());
    }

    public void changeBuc(TableColumn.CellEditEvent<Request, String> requestStringCellEditEvent) {
        Request req=TableView.getSelectionModel().getSelectedItem();
        req.setBucati(requestStringCellEditEvent.getNewValue());
    }

    public void changeUrgenta(TableColumn.CellEditEvent<Request, String> requestStringCellEditEvent) {
        Request req=TableView.getSelectionModel().getSelectedItem();
        req.setUrgent(requestStringCellEditEvent.getNewValue());
    }

    public void Salvare() {
        cereri=TableView.getItems();
        persistCereri();
    }

    public void Anulare(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void persistCereri() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), cereri);
        } catch (IOException e) {
            throw new CouldNotWriteCerereException();
        }
    }

    public static List<Request> getCereri() {
        return cereri;
    }
}
