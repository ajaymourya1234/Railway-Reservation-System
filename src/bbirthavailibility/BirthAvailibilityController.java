/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbirthavailibility;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import reservationsmade.ReservationmadeController;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class BirthAvailibilityController implements Initializable {
    
    
    ObservableList<train> list = FXCollections.observableArrayList();
    @FXML
    private JFXComboBox<String> selecttrainno;
    ObservableList<String> list2 = FXCollections.observableArrayList("12592","12591","05079","05078","51827","51828","51801","51802","51813","51814","51910","51910","51911","12608","12609");
    ObservableList<String> list3 = FXCollections.observableArrayList("GORAKHPUR","YESHWANTPUR","MANKAPUR","GONDA JN","ITARSI JN","JHANSI JN","MANIKPUR JN","KANPUR","LUCKNOW","SAHARANPUR","DELHI","BANGALORE CITY","CHENNAI");
    @FXML
    private TableView<train> tableview;
    @FXML
    private TableColumn<train, String> traincol;
    @FXML
    private TableColumn<train, String> trainnamecol;
    @FXML
    private TableColumn<train, String> sourcecol;
    @FXML
    private TableColumn<train, String> destcol;
    @FXML
    private TableColumn<train, Integer> seatscol;
    @FXML
    private JFXButton submit;
    
    @FXML
     void submit(ActionEvent event){
        //String SELECTTRAIN = selecttrainno.getValue();
       // selecttrainno.setItems(list2);
        System.out.println("ajay mourya");
        initcol();
        loadData();
      
       // initcol();
       // loadData();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("ajay mourya");
        selecttrainno.setItems(list2);
        //selectsource.setItems(list3);
        //selectdest.setItems(list3);
        //String SELECTTRAIN = selecttrainno.getValue();
        //initcol();
        //loadData();
    }    

    private void initcol() {
        traincol.setCellValueFactory(new PropertyValueFactory<>("train"));
        trainnamecol.setCellValueFactory(new PropertyValueFactory<>("trainname"));
        sourcecol.setCellValueFactory(new PropertyValueFactory<>("source"));
        destcol.setCellValueFactory(new PropertyValueFactory<>("dest"));
        seatscol.setCellValueFactory(new PropertyValueFactory<>("seats"));
    }

    private void loadData() {
       DatabaseHandler handler = new DatabaseHandler();
       String SELECTTRAIN = selecttrainno.getValue();
        String qu = "SELECT * FROM TRAIN_DETAIL WHERE TRAIN_NO= '" + SELECTTRAIN + "'";
        ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()){
                String trainx = rs.getString("TRAIN_NO");
                String trainnamex = rs.getString("TRAIN_NAME");
                String sourcex = rs.getString("SOURCE");
                String destx = rs.getString("DESTINATION");
                int seatsx = rs.getInt("SEATS");
              
                list.add(new BirthAvailibilityController.train(trainx,trainnamex,sourcex,destx,seatsx));


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
        private final SimpleIntegerProperty seats;

        public train(String train,String trainname,String source,String dest,int seats) {
            this.train = new SimpleStringProperty(train);
            this.trainname = new SimpleStringProperty(trainname);
            this.source = new SimpleStringProperty(source);
            this.dest = new SimpleStringProperty(dest);
            this.seats = new SimpleIntegerProperty(seats);

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

        public int getSeats() {
            return seats.get();
        }
        
        
        
        
    }
    
    
    
}




