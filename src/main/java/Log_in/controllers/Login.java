package Log_in.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.Objects;

public class Login {


    @FXML
    public Text loginMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public Button btn_msg;


    public void pressButton(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty()) {
            loginMessage.setText("Introduceti un e-mail valid! ");
            return;
        }

        if (password == null || password.isEmpty()) {
            loginMessage.setText("Introduceti parola!");
            return;
        }

        if(Objects.equals(username,"administrator") && Objects.equals(password,"administrator")){
            loginMessage.setText("Administrator");
        }
        else
        if(Objects.equals(username,"manager") && Objects.equals(password,"manager")){
            loginMessage.setText("Manager");
        }
        else
        if(Objects.equals(username,"furnizor") && Objects.equals(password,"furnizor")){
            loginMessage.setText("Furnizor");
        }
        else loginMessage.setText("Date invalide");


    }

}
