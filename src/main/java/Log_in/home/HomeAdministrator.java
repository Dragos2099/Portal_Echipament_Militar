package Log_in.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HomeAdministrator {



    public void SignOut(ActionEvent actionEvent)  {
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


    public void register(ActionEvent actionEvent) {

        try {
            Parent Reg = FXMLLoader.load(getClass().getResource("/Register/register_adm.fxml"));
            Stage stage =new Stage() ;
            stage.setTitle("Portal Echipament Militar");
            Scene scene = new Scene(Reg, 700, 500);
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

    public void contracte(ActionEvent actionEvent) {
        try {

            Parent Log_in = FXMLLoader.load(getClass().getResource("/Contract/Table_contracte.fxml"));
            Stage stage =new Stage() ;
            stage.setTitle("Portal Echipament Militar");
            Scene scene = new Scene(Log_in, 700, 585);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
