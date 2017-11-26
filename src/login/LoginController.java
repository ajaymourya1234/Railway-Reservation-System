/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mainwindow.MainwindowController;
import reservationsmade.ReservationmadeController;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    
    Preferences preference;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //preference = Preferences.getPrefernces();
    }    

    @FXML
    private void login(ActionEvent event) {
       // loadwindow("/mainwindow/MainWindow.fxml", "Main Window");
       String uname = username.getText();
       String pass = password.getText();
       
         System.out.println("ajay mourya");
        DatabaseHandler handler = new DatabaseHandler();
        String qu = "SELECT * FROM USER WHERE USERID='" + uname + "' AND PASSWORD='" + pass + "'";
        ResultSet rs = handler.execQuery(qu);
        try {
             if(rs.next()){
               CloseStage();
               loadwindow("/mainwindow/MainWindow.fxml", "Main Window");
            }else{
                 label.setText("Invalid Credentials");
                 label.setStyle("-fx-background-color:#d32f2f;-fx-text-fill:white");
             }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationmadeController.class.getName()).log(Level.SEVERE,null,ex);
        }
     
    }
    

    @FXML
    private void signup(ActionEvent event) {
       loadwindow("/addnewuser/NewUser.fxml", "New User Registration");
       
    }
    
      void loadwindow(String loc,String title){
      
        try {
            Parent root = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            //Parent Parent = null;
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainwindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void CloseStage() {
        ((Stage) username.getScene().getWindow()).close();
    }


}


  //label.setText("Invalid Credentials");
  //         label.setStyle("-fx-background-color:#d32f2f;-fx-text-fill:white");