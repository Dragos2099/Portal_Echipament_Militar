package Contract.controllers;

import Contract.services.ContractsService;
import Oferta.controllers.afisare.TableOferte;
import Oferta.model.Oferta;
import Oferta.services.OfertaService;
import Register.services.FileSystemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    public void creeare_contract(ActionEvent actionEvent)  {

    }

    public void cancel(ActionEvent actionEvent) {
    }
}
