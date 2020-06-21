package Contract.controllers.afisare;

import Contract.model.Contracts;
import Contract.services.ContractsService;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TableContracte implements Initializable {
    private static List<Contracts> contracts;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("contract", "contracts.json");
    @FXML
    public javafx.scene.control.TableView<Contracts> TableView;

    @FXML
    public TableColumn<Contracts,String> Data;

    @FXML
    public TableColumn<Contracts,String> Echipamente;
    @FXML
    public TableColumn<Contracts,String> Total;
    @FXML
    public TableColumn<Contracts,String> Buc;
    @FXML
    public TableColumn<Contracts,String> Nr_bm;
    @FXML
    public TableColumn<Contracts,String> Nr_f;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Data.setCellValueFactory(new PropertyValueFactory<>("Data"));
        Nr_bm.setCellValueFactory(new PropertyValueFactory<>("Nr_bm"));
        Nr_f.setCellValueFactory(new PropertyValueFactory<>("Nr_f"));
        Echipamente.setCellValueFactory(new PropertyValueFactory<>("Echipamente"));
        Buc.setCellValueFactory(new PropertyValueFactory<>("Buc"));
        Total.setCellValueFactory(new PropertyValueFactory<>("Total"));


        try {
            TableView.setItems(getPeople());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public ObservableList<Contracts> getPeople() throws IOException {

        ObservableList<Contracts> contracte= FXCollections.observableArrayList();


        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(ContractsService.class.getClassLoader().getResource("contracts.json")), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper2 = new ObjectMapper();

        contracts = objectMapper2.readValue(USERS_PATH.toFile(), new TypeReference<List<Contracts>>() {
        });


        contracte.addAll(contracts);
        //contracte.add(new Contracts ("a","a","a","a","a","a"));

        return contracte;


    }

    public List<Contracts> getContracts(){
        return contracts;
    }

    public void back(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
