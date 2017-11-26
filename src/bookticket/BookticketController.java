/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookticket;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class BookticketController implements Initializable {

    @FXML
    private JFXTextField trainno;
    @FXML
    private JFXTextField passengername;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXComboBox<String> selectgender;
    @FXML
    private JFXTextField source;
    @FXML
    private JFXTextField destination;
    @FXML
    private JFXDatePicker datepicker;
    
    DatabaseHandler databaseHandler;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> list = FXCollections.observableArrayList("Male","Female","Other");
    @FXML
    private JFXButton book;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        databaseHandler = new DatabaseHandler();
        selectgender.setItems(list);
    }    

    @FXML
    private void bookticket(ActionEvent event) {
        String Train_no = trainno.getText();
        String Passengername = passengername.getText();
        String Age = age.getText();
        String Gender = selectgender.getValue();
        String Source = source.getText();
        String Destination = destination.getText();
        LocalDate Date = datepicker.getValue();
        String Status;
        
         if(Train_no.isEmpty()||Passengername.isEmpty()||Age.isEmpty()||Gender.isEmpty()||Source.isEmpty()||Destination.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
        }
         
         String qu = "INSERT INTO PASSENGERS VALUES (" +
                "'" + Train_no + "'," +
                "'" + Passengername + "'," +
                "" + Age  + "," +
                "'" + Gender + "'," +
                "'" + Source + "',"+
                "'" + Destination + "',"+
                "'" + Date + "'"+
                ")";
        System.out.println(qu);
        if(databaseHandler.execAction(qu)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
            return;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
            return;
        }
    }
    
   
    
}




