package Register.controllers;

import Register.exceptions.UsernameAlreadyExistsException;
import Register.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterAdm {
    @FXML
    public Text registrationMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public ChoiceBox<String> role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Manager","Furnizor");
    }
    public void handleRegisterAction()  {
        String username=usernameField.getText();
        String password=passwordField.getText();


        if(username.length()==0 || password.length()==0 || Objects.equals(role.getValue(),null))
            registrationMessage.setText("Completati toate campurile !");
        else {
            try {
                UserService.addUser(usernameField.getText(), passwordField.getText(), role.getValue());
                registrationMessage.setText("Contul a fost creat!");
            } catch (UsernameAlreadyExistsException e) {
                registrationMessage.setText(e.getMessage());
            }
        }

    }

    public void back(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

    }
}
