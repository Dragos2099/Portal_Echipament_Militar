package Log_in.controllers;

import Register.model.User;
import Register.services.Criptare;
import Register.services.FileSystemService;
import Register.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class Login {
    private static List<User> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("utilizatori", "users.json");

    @FXML
    public Text loginMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public Button btn_msg;


    public void pressButton(ActionEvent actionEvent) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty()) {
            loginMessage.setText("Introduceti un e-mail valid ! ");
            return;
        }else
        if (password == null || password.isEmpty()) {
            loginMessage.setText("Introduceti parola !");
            return;
        }

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });

        for (User user : users) {
            String pws= Criptare.decrypt(user.getPassword(),user.getUsername());
            if (Objects.equals(username, user.getUsername()) && Objects.equals(password,pws)) {
                if(Objects.equals(user.getRole(),"Administrator")) {
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

                if(Objects.equals(user.getRole(),"Manager")) {
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
                if(Objects.equals(user.getRole(),"Furnizor")) {
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
            }
        }

        loginMessage.setText("Contul nu exista !");


    }

    public void register(ActionEvent actionEvent) {
        try {

            Parent Reg = FXMLLoader.load(getClass().getResource("/register.fxml"));
            Stage stage =new Stage() ;
            stage.setTitle("Register");
            Scene scene = new Scene(Reg, 700, 500);
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
