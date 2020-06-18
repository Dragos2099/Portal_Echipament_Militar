package Oferta.controllers;

import Cerere.model.Request;
import Cerere.services.CerereService;
import Register.services.FileSystemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TableCereriOferta implements Initializable {
    public static Request req ;
    private static List<Request> cereri;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("cerere", "cerere.json");
    @FXML
    private javafx.scene.control.TableView<Request> TableView;

    @FXML
    private TableColumn<Request,String> Echipament;

    @FXML
    private TableColumn<Request,String> Bucati;

    @FXML
    private TableColumn<Request,String> Urgent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Echipament.setCellValueFactory(new PropertyValueFactory<>("Echipament"));
        Bucati.setCellValueFactory(new PropertyValueFactory<>("Bucati"));
        Urgent.setCellValueFactory(new PropertyValueFactory<>("Urgent"));

        try {
            TableView.setItems(getPeople());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void selectareCerere(ActionEvent actionEvent) throws IOException {
        Parent Log_in = FXMLLoader.load(getClass().getResource("/Oferta/Oferta.fxml"));
        Stage stage =new Stage() ;
        stage.setTitle("Portal Echipament Militar");
        Scene scene = new Scene(Log_in, 360, 490);
        stage.setScene(scene);
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

    }

    public static Request metoda(){

        return req;
    }
}
