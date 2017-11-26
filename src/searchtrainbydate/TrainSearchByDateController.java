/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchtrainbydate;

import bbirthavailibility.BirthAvailibilityController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import database.DatabaseHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mainwindow.MainwindowController;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class TrainSearchByDateController implements Initializable {

    ObservableList<TrainSearchByDateController.train> list = FXCollections.observableArrayList();
    @FXML
    private JFXDatePicker datepicker;
    @FXML
    private TableView<train> tableview;
    @FXML
    private TableColumn<train, String> trainnocol;
    @FXML
    private TableColumn<train, String> sourcecol;
    @FXML
    private TableColumn<train, String> destcol;
    @FXML
    private Label username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submitaction(ActionEvent event) {
         LocalDate date = datepicker.getValue();
          if(date==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter The Date");
            alert.showAndWait();
            return;
        }
        initcol();
        loadData();
        
    }
    
     private void initcol() {
        trainnocol.setCellValueFactory(new PropertyValueFactory<>("train"));
        //trainnamecol.setCellValueFactory(new PropertyValueFactory<>("trainname"));
        sourcecol.setCellValueFactory(new PropertyValueFactory<>("source"));
        destcol.setCellValueFactory(new PropertyValueFactory<>("dest"));
        //seatscol.setCellValueFactory(new PropertyValueFactory<>("seats"));
    }
     
    private void loadData() {
       DatabaseHandler handler = new DatabaseHandler();
        LocalDate date = datepicker.getValue();
        String qu = "SELECT * FROM TRAIN_ROUTE WHERE JOURNEY_DATE= '" + date + "'";
        ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()){
                String trainx = rs.getString("TRAIN_NO");
                //String trainnamex = rs.getString("TRAIN_NAME");
                String sourcex = rs.getString("SOURCE");
                String destx = rs.getString("DESTINATION");
                //int seatsx = rs.getInt("SEATS");
              
                list.add(new TrainSearchByDateController.train(trainx,sourcex,destx));


            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainSearchByDateController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        tableview.getItems().setAll(list);
}

    @FXML
    private void refreshaction(ActionEvent event) {
          ((Stage) username.getScene().getWindow()).close();
          try {
            Parent root = FXMLLoader.load(getClass().getResource("TrainSearchByDate.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle("");
            //Parent Parent = null;
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainwindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static class train{
        private final SimpleStringProperty train;
       // private final SimpleStringProperty trainname;
        private final SimpleStringProperty source;
        private final SimpleStringProperty dest;
       // private final SimpleIntegerProperty seats;

        public train(String train,String source,String dest) {
            this.train = new SimpleStringProperty(train);
           // this.trainname = new SimpleStringProperty(trainname);
            this.source = new SimpleStringProperty(source);
            this.dest = new SimpleStringProperty(dest);
            //this.seats = new SimpleIntegerProperty(seats);

        }

        public String getTrain() {
            return train.get();
        }

       // public String getTrainname() {
       //     return trainname.get();
        //}

        public String getSource() {
            return source.get();
        }

        public String getDest() {
            return dest.get();
        }

       // public int getSeats() {
       //     return seats.get();
       // }
        
        
        
        
    }
    
    
    
}
