package Log_in.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
            loginMessage.setText("Introduceti un e-mail valid ! ");
            return;
        }else
        if (password == null || password.isEmpty()) {
            loginMessage.setText("Introduceti parola !");
            return;
        } else
        if(Objects.equals(username,"administrator") && Objects.equals(password,"administrator")){
            try {
                Stage stage = (Stage) loginMessage.getScene().getWindow();
                stage.setTitle("Administrator");
                Parent AdmHome = FXMLLoader.load(getClass().getResource("/HomeAdministrator.fxml"));
                Scene scene = new Scene(AdmHome, 700, 500);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        if(Objects.equals(username,"manager") && Objects.equals(password,"manager")){
            try {
                Stage stage = (Stage) loginMessage.getScene().getWindow();
                stage.setTitle("Manager");
                Parent AdmHome = FXMLLoader.load(getClass().getResource("/HomeManager.fxml"));
                Scene scene = new Scene(AdmHome, 700, 500);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        if(Objects.equals(username,"furnizor") && Objects.equals(password,"furnizor")){
            try {
                Stage stage = (Stage) loginMessage.getScene().getWindow();
                stage.setTitle("Furnizor");
                Parent AdmHome = FXMLLoader.load(getClass().getResource("/HomeFurnizor.fxml"));
                Scene scene = new Scene(AdmHome, 700, 500);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else loginMessage.setText("Date invalide !!");


    }

}
