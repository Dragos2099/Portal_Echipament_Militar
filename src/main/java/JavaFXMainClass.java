import Register.services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFXMainClass extends Application {



    @Override
    public void start(Stage primaryStage) throws IOException {
        UserService.loadUsersFromFile();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/Login/login.fxml"));
        primaryStage.setTitle("Portal Echipament Militar");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
