/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchtrain;

import bbirthavailibility.BirthAvailibilityController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import database.DatabaseHandler;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class SearchTrainController implements Initializable {
    
    ObservableList<SearchTrainController.train> list = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList("GORAKHPUR","YESHWANTPUR","MANKAPUR JN","GONDA JN","ITARSI JN","JHANSI JN","MANIKPUR JN","KANPUR CENTRAL","LUCKNOW","SAHARANPUR","DELHI","BANGALORE CITY","CHENNAI");

    @FXML
    private JFXComboBox<String> source;
    @FXML
    private JFXComboBox<String> destination;
    @FXML
    private JFXButton submit;
    @FXML
    private TableColumn<train, String> trainnocol;
    @FXML
    private TableColumn<train, String> trainnamecol;
    @FXML
    private TableColumn<train, String> sourcecol;
    @FXML
    private TableColumn<train, String> destcol;
    @FXML
    private TableView<train> tableview;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        source.setItems(list2);
        destination.setItems(list2);
    }    

    @FXML
    private void submitaction(ActionEvent event) {
        initcol();
        loadData();
    }
    
     private void initcol() {
        trainnocol.setCellValueFactory(new PropertyValueFactory<>("train"));
        trainnamecol.setCellValueFactory(new PropertyValueFactory<>("trainname"));
        sourcecol.setCellValueFactory(new PropertyValueFactory<>("source"));
        destcol.setCellValueFactory(new PropertyValueFactory<>("dest"));
        //seatscol.setCellValueFactory(new PropertyValueFactory<>("seats"));
    }
     
     private void loadData() {
       DatabaseHandler handler = new DatabaseHandler();
       String sourcexy = source.getValue();
       String destxy = destination.getValue();
       if(sourcexy.isEmpty()||destxy.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
       }else if(sourcexy==destxy){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Train cannot have same source and destination");
            alert.showAndWait();
            return;
       }
        String qu = "SELECT * FROM TRAIN_DETAIL WHERE SOURCE= '" + sourcexy + "' AND DESTINATION='" + destxy + "'";
        ResultSet rs = handler.execQuery(qu);
      
            
        
        try {
            
            while (rs.next()){
                String trainx = rs.getString("TRAIN_NO");
                String trainnamex = rs.getString("TRAIN_NAME");
                String sourcex = rs.getString("SOURCE");
                String destx = rs.getString("DESTINATION");
                //int seatsx = rs.getInt("SEATS");
              
                list.add(new SearchTrainController.train(trainx,trainnamex,sourcex,destx));


            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(BirthAvailibilityController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        tableview.getItems().setAll(list);
    
}
     


public static class train{
        private final SimpleStringProperty train;
        private final SimpleStringProperty trainname;
        private final SimpleStringProperty source;
        private final SimpleStringProperty dest;
       // private final SimpleIntegerProperty seats;

        public train(String train,String trainname,String source,String dest) {
            this.train = new SimpleStringProperty(train);
            this.trainname = new SimpleStringProperty(trainname);
            this.source = new SimpleStringProperty(source);
            this.dest = new SimpleStringProperty(dest);
            //this.seats = new SimpleIntegerProperty(seats);

        }



        public String getTrain() {
            return train.get();
        }

        public String getTrainname() {
            return trainname.get();
        }

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


