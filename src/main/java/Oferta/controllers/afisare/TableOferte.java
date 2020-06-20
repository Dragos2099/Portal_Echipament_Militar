package Oferta.controllers.afisare;

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

public class TableOferte implements Initializable {
    private static List<Oferta> oferte;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("oferte", "oferte.json");
    private static Oferta of;
    @FXML
    public javafx.scene.control.TableView<Oferta> TableView;

    @FXML
    public TableColumn<Oferta,String> Data;

    @FXML
    public TableColumn<Oferta,String> Echipament;
    @FXML
    public TableColumn<Oferta,String> Pret;
    @FXML
    public TableColumn<Oferta,String> Bucati;
    @FXML
    public TableColumn<Oferta,String> Stare;
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

    public ObservableList<Oferta> getPeople() throws IOException {

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

    private void persistOferte() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), oferte);
        } catch (IOException e) {
            throw new CouldNotWriteOfertaException();
        }
    }
    public void Accept(ActionEvent actionEvent) throws IOException {

        of=TableView.getSelectionModel().getSelectedItem();

        persistOferte();
        Parent Log_in = FXMLLoader.load(getClass().getResource("/Contract/Contract.fxml"));
        Stage stage =new Stage();
        stage.setTitle("Portal Echipament Militar");
        Scene scene = new Scene(Log_in, 423, 616);
        stage.setScene(scene);
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

    }

    public void Deny(ActionEvent actionEvent) throws IOException {
        Oferta o=TableView.getSelectionModel().getSelectedItem();
        for(Oferta i : oferte){
            if(Objects.equals(i,o)) {
                i.setStare("Respinsa");
                break;
            }
        }

        persistOferte();

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Parent Log_in = FXMLLoader.load(getClass().getResource("/Oferta/Table_Oferte.fxml"));
        Stage stage =new Stage() ;
        stage.setTitle("Portal Echipament Militar");
        Scene scene = new Scene(Log_in, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static Oferta metoda(){
        return of;
    }

    public static List<Oferta> getOferte() {
        return oferte;
    }
}
