package Log_in.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;

public class SchimbareParolaSuccess {
    public void ok(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
