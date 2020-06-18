package Log_in.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeFurnizor {
    public void SignOut(ActionEvent actionEvent) {
        try {

            Parent Log_in = FXMLLoader.load(getClass().getResource("/Login/login.fxml"));
            Stage stage =new Stage() ;
            stage.setTitle("Portal Echipament Militar");
            Scene scene = new Scene(Log_in, 700, 500);
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scmb_parola(ActionEvent actionEvent) {
        try {

            Parent Log_in = FXMLLoader.load(getClass().getResource("/Schimbare_Parola/SchimbareParola.fxml"));
            Stage stage =new Stage() ;
            stage.setTitle("Portal Echipament Militar");
            Scene scene = new Scene(Log_in, 600, 400);
            stage.setScene(scene);
            stage.show();
           // ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afis_cereri(ActionEvent actionEvent) {
    }

    public void add_oferta(ActionEvent actionEvent) {
    }

    public void edit_oferta(ActionEvent actionEvent) {
    }

    public void sterge_oferta(ActionEvent actionEvent) {
    }
}
