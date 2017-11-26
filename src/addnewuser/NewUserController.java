/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addnewuser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.ComboBoxTreeTableCell;
import javafx.stage.Stage;
import javax.swing.plaf.RootPaneUI;
import org.mariadb.jdbc.internal.io.socket.UnixDomainSocket;
import database.DatabaseHandler;

/**
 *
 * @author Ajay
 */
public class NewUserController implements Initializable{

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXTextField phoneno;
    @FXML
    private JFXTextField userid;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton register;
    private JFXTextField gender;
    @FXML
    private JFXButton cancel;

    DatabaseHandler databaseHandler;
    @FXML
    private JFXPasswordField password1;
    @FXML
   // ObservableList<String> list = FXCollections.observableArrayList();
   // list.add("Male");
    private JFXComboBox<String> selectgender;
    ObservableList<String> list = FXCollections.observableArrayList("Male","Female","Other");
    @Override
    public void initialize(URL url,ResourceBundle rb){
        databaseHandler = new DatabaseHandler();
        selectgender.setItems(list);
    }
    
   
    @FXML
    private void registeruser(ActionEvent event) {
        String Name = name.getText();
        String Age = age.getText();
        String Gender = selectgender.getValue();
        String Phoneno = phoneno.getText();
        String Userid = userid.getText();
        String Password = password.getText();

        if(Name.isEmpty()||Age.isEmpty()||Gender.isEmpty()||Phoneno.isEmpty()||Userid.isEmpty()||Password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
        }

        String qu = "INSERT INTO USER VALUES (" +
                "'" + Name + "'," +
                "" + Age  + "," +
                "'" + Gender + "'," +
                "'" + Phoneno + "',"+
                "'" + Userid + "',"+
                "'" + Password + "'"+
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

    @FXML
    private void cancel(ActionEvent event) throws IOException {
       // Parent root = FXMLLoader.load(getClass().getResource("railways/Login.fxml"));
       System.exit(0);
       // Stage stage = (Stage) rootPane.getScene().getWindow();
       // stage.close();
   
    }
    
}
