package Contract.controllers;

import Oferta.model.Oferta;
import Register.services.FileSystemService;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.nio.file.Path;
import java.util.List;

public class Contract {
    private static List<Oferta> oferte;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("oferte", "oferte.json");
    public TextField buc;
    public TextField total;
    public DatePicker date;
    public TextField ec;
    public TextField nr_f;
    public TextField nr_mil;

    public void creeare_contract(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
    }
}
