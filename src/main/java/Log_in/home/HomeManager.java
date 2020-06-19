package Log_in.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeManager {
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
            //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afis_cereri(ActionEvent actionEvent) throws IOException {
        Parent Log_in = FXMLLoader.load(getClass().getResource("/Cerere/Table_cereri.fxml"));
        Stage stage =new Stage() ;
        stage.setTitle("Portal Echipament Militar");
        Scene scene = new Scene(Log_in, 360, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void add_cerere(ActionEvent actionEvent) {

        try {

            Parent Log_in = FXMLLoader.load(getClass().getResource("/Cerere/Cerere.fxml"));
            Stage stage =new Stage() ;
            stage.setTitle("Portal Echipament Militar");
            Scene scene = new Scene(Log_in, 360, 400);
            stage.setScene(scene);
            stage.show();
            //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit_cerere(ActionEvent actionEvent) throws IOException {
        Parent Log_in = FXMLLoader.load(getClass().getResource("/Cerere/Table_cereri_editare.fxml"));
        Stage stage =new Stage() ;
        stage.setTitle("Portal Echipament Militar");
        Scene scene = new Scene(Log_in, 350, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void sterge_cerere(ActionEvent actionEvent) throws IOException {
        Parent Log_in = FXMLLoader.load(getClass().getResource("/Cerere/Table_cereri_sterge.fxml"));
        Stage stage =new Stage() ;
        stage.setTitle("Portal Echipament Militar");
        Scene scene = new Scene(Log_in, 360, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void viz_oferte(ActionEvent actionEvent) throws IOException {
        Parent Log_in = FXMLLoader.load(getClass().getResource("/Oferta/Table_Oferte.fxml"));
        Stage stage =new Stage() ;
        stage.setTitle("Portal Echipament Militar");
        Scene scene = new Scene(Log_in, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
