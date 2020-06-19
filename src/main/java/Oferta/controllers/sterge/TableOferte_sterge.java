package Oferta.controllers.sterge;

import Oferta.exceptions.CouldNotWriteOfertaException;
import Oferta.model.Oferta;
import Oferta.services.OfertaService;
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
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TableOferte_sterge implements Initializable {
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

    public void initialize(URL location, ResourceBundle resources) {


        Data.setCellValueFactory(new PropertyValueFactory<>("Data"));
        Stare.setCellValueFactory(new PropertyValueFactory<>("Stare"));
        Echipament.setCellValueFactory(new PropertyValueFactory<>("Echipament"));
        Bucati.setCellValueFactory(new PropertyValueFactory<>("Bucati"));
        Pret.setCellValueFactory(new PropertyValueFactory<>("Pret"));


        try {
            TableView.setItems(getPeople());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private ObservableList<Oferta> getPeople() throws IOException {

        ObservableList<Oferta> offers= FXCollections.observableArrayList();


        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(OfertaService.class.getClassLoader().getResource("oferte.json")), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper2 = new ObjectMapper();

        oferte = objectMapper2.readValue(USERS_PATH.toFile(), new TypeReference<List<Oferta>>() {
        });



        for(Oferta i : oferte){
            if(i.getStare().equals("Asteptare") || i.getStare().equals("Respinsa"))
                offers.add(i);
        }


        return offers;


    }


    public void Sterge(ActionEvent actionEvent) {
        Oferta o=TableView.getSelectionModel().getSelectedItem();

        oferte.remove(o);
        persisOferte();

        TableView.getItems().removeAll(TableView.getSelectionModel().getSelectedItem());
    }

    private void persisOferte() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), oferte);
        } catch (IOException e) {
            throw new CouldNotWriteOfertaException();
        }
    }

    public void Anulare(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
