/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class MainwindowController implements Initializable {
    Stage stage = new Stage(StageStyle.DECORATED);
    @FXML
    private Label username;
    @FXML
    private AnchorPane anchorpane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadbookticket(ActionEvent event) {
        loadwindow("/bookticket/Bookticket.fxml", "Book Ticket");
    }

    @FXML
    private void loadbirthavailibility(ActionEvent event) {
        loadwindow("/bbirthavailibility/BirthAvailibility.fxml", "Birth Availibility");
    }

    @FXML
    private void loadreservationsmade(ActionEvent event) {
        loadwindow("/reservationsmade/Reservationmade.fxml", "Reservations made");

    }

    @FXML
    private void loadcancelticket(ActionEvent event) {
        loadwindow("/cancelticket/CancelTicket.fxml", "Ticket Cancellation");
        
    }
    
      
    void loadwindow(String loc,String title){
      
        try {
            Parent root = FXMLLoader.load(getClass().getResource(loc));
            //Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            //Parent Parent = null;
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainwindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void signoutaction(ActionEvent event) {
        ((Stage) username.getScene().getWindow()).close();
       // stage.close();
        loadwindow("/login/Login.fxml", "Login Window");
        
    }

    @FXML
    private void searchtrainaction(ActionEvent event) {
        loadwindow("/searchtrain/SearchTrain.fxml", "Search Train");
    }

    @FXML
    private void searchtrainbydateaction(ActionEvent event) {
        loadwindow("/searchtrainbydate/TrainSearchByDate.fxml", "Search Train");
    }

    @FXML
    private void checkticketfareaction(ActionEvent event) {
        loadwindow("/ticketfare/TicketFare.fxml", "Ticket Cancellation");
    }
    
}
