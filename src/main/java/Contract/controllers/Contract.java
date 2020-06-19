package Contract.controllers;

import Contract.exceptions.ContractAlreadyExistsException;
import Contract.services.ContractsService;
import Oferta.controllers.afisare.TableOferte;
import Oferta.model.Oferta;
import Oferta.services.OfertaService;
import Register.services.FileSystemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class Contract {
    private static List<Oferta> oferte;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("oferte", "oferte.json");
    public TextField buc;
    public TextField total;
    public DatePicker date;
    public TextField ec;
    public TextField nr_f;
    public TextField nr_mil;
    public Text eroare;

    public void creeare_contract(ActionEvent actionEvent) throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(OfertaService.class.getClassLoader().getResource("oferte.json")), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper2 = new ObjectMapper();

        oferte = objectMapper2.readValue(USERS_PATH.toFile(), new TypeReference<List<Oferta>>() {
        });

        Oferta o=TableOferte.metoda();
        for(Oferta i : oferte){
            if(Objects.equals(i.getStare(),"Asteptare")) {
                i.setStare("Acceptata");
                break;
            }
        }

        persistOferte();
        try {
            ContractsService.addContracts(date.getValue().toString(), buc.getText(), total.getText(), ec.getText(), nr_f.getText(), nr_mil.getText());
            eroare.setText("Contractul a fost creat !");
        }catch(ContractAlreadyExistsException e){
            eroare.setText(e.getMessage());
        }


    }


    public void cancel(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }


    private void persistOferte() throws IOException {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), oferte);

    }
}
