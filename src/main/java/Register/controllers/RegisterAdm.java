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
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), role.getValue());
            registrationMessage.setText("Contul a fost creat!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }

    }

    public void back(ActionEvent actionEvent) {
        try {

            Parent Log_in = FXMLLoader.load(getClass().getResource("/Login/login.fxml"));
            Stage stage =new Stage() ;
            stage.setTitle("LOG_IN");
            Scene scene = new Scene(Log_in, 700, 500);
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
