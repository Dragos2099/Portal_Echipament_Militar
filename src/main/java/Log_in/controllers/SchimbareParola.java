package Log_in.controllers;

import Register.exceptions.CouldNotWriteUserException;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class SchimbareParola {
    private static List<User> users;
    private static final Path USERS_PATH= FileSystemService.getPathToFile("utilizatori","users.json");
    @FXML
    public Text ChangeMessage;
    @FXML
    public TextField UsernameField;
    @FXML
    public javafx.scene.control.PasswordField OldPasswordField;
    @FXML
    public javafx.scene.control.PasswordField NewPasswordField;

    public void scmb_parola(ActionEvent actionEvent) throws IOException {
        String username = UsernameField.getText();
        String oldpassword = OldPasswordField.getText();
        String newpassword = NewPasswordField.getText();
        if (username == null || username.isEmpty()) {
            ChangeMessage.setText("E-mail invalid");
            return;
        } if (oldpassword == null || oldpassword.isEmpty()) {
            ChangeMessage.setText("Parola introdusa este gresita");
            return;
        }
        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });

        for (User user : users) {
            String pws=Criptare.decrypt(user.getPassword(),user.getUsername());
            if(Objects.equals(username,user.getUsername()) && Objects.equals(pws,oldpassword)){
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                user.setPassword(Criptare.encrypt(newpassword,username));
                persistUsers();
                Parent Log_in = FXMLLoader.load(getClass().getResource("/Schimbare_Parola/SchimbareParolaSuccess.fxml"));
                Stage stage =new Stage() ;
                stage.setTitle("Portal Echipament Militar");
                Scene scene = new Scene(Log_in, 350, 200);
                stage.setScene(scene);
                stage.show();
                return;
            }
        }
        ChangeMessage.setText("Date incorecte!");
    }


    private void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUserException();
        }
    }
}
