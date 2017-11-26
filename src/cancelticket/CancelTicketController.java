/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cancelticket;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
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
import reservationsmade.ReservationmadeController;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class CancelTicketController implements Initializable {

    ObservableList<CancelTicketController.train> list = FXCollections.observableArrayList();
    
    ObservableList<CancelTicketController.train> list1 = FXCollections.observableArrayList();
    
 
    @FXML
    private JFXTextField namedelete;
    @FXML
    private JFXButton delete;
    @FXML
    private TableColumn<train, String> passengercol;
    @FXML
    private TableColumn<train, Integer> agecol;
    @FXML
    private TableColumn<train, String> gendercol;
    @FXML
    private TableColumn<train, String> sourcecol;
    @FXML
    private TableColumn<train, String> destcol;
    @FXML
    private TableColumn<train, String> journeydatecol;
    @FXML
    private TableColumn<train, String> traincol;
    @FXML
    private TableView<train> tableview;
    
    DatabaseHandler databaseHandler = new DatabaseHandler();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //JFXDepthManager.setDepth(control, 1);
        initcol();
        loadData();
    }    
    //String name = namedelete.getText();
    @FXML
     void delete(ActionEvent event) {
        
        String name = namedelete.getText();
        
        if(name.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
        }
        
        String qu = "DELETE FROM PASSENGERS WHERE PASSENGER_NAME='" + name + "'";
        System.out.println(qu);
       //DatabaseHandler databaseHandler = new DatabaseHandler();
        if(databaseHandler.execAction(qu)){
            System.out.println(qu);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            initcol1();
            loadData1();
            alert.showAndWait();
            
           // for ( int i = 0; i<tableview.getItems().size(); i++) {
            //      tableview.getItems().clear();
            //}
            //tableview.getItems().clear();

           
            //tableview.refresh();
            return;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
            return;
        }
        
     }

      void initcol() {
        passengercol.setCellValueFactory(new PropertyValueFactory<>("passenger"));
        agecol.setCellValueFactory(new PropertyValueFactory<>("age"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        sourcecol.setCellValueFactory(new PropertyValueFactory<>("source"));
        destcol.setCellValueFactory(new PropertyValueFactory<>("dest"));
        journeydatecol.setCellValueFactory(new PropertyValueFactory<>("journeydate"));
        traincol.setCellValueFactory(new PropertyValueFactory<>("train"));
    }

     void loadData() {
         System.out.println("ajay mourya");
         //tableview.getItems().clear();
//         for ( int i = 0; i<tableview.getItems().size(); i++) {
//                  tableview.getItems().clear();
//         }
        //DatabaseHandler handler = new DatabaseHandler();
        String qu = "SELECT * FROM PASSENGERS";
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            while (rs.next()){
                String trainx = rs.getString("TRAIN_NO");
                String passengernamex = rs.getString("PASSENGER_NAME");
                int agex = rs.getInt("AGE");
                String genderx = rs.getString("GENDER");
                String sourcex = rs.getString("SOURCE");
                String destx = rs.getString("DESTINATION");
                String journeydatex = rs.getString("JOURNEY_DATE");
              
                list.add(new train(trainx,passengernamex,agex,genderx,sourcex,destx,journeydatex));


            }
        } catch (SQLException ex) {
            Logger.getLogger(CancelTicketController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        tableview.getItems().setAll(list);
       //tableview.getItems().clear();
    }
    
    public static class train{
        private final SimpleStringProperty train;
        private final SimpleStringProperty passenger;
        private final SimpleIntegerProperty age;
        private final SimpleStringProperty gender;
        private final SimpleStringProperty source;
        private final SimpleStringProperty dest;
        private final SimpleStringProperty journeydate;

        public train(String train,String passenger,int age,String gender,String source,String dest,String journeydate) {
            this.train = new SimpleStringProperty(train);
            this.passenger = new SimpleStringProperty(passenger);
            this.age = new SimpleIntegerProperty(age);
            this.gender = new SimpleStringProperty(gender);
            this.source = new SimpleStringProperty(source);
            this.dest = new SimpleStringProperty(dest);
            this.journeydate = new SimpleStringProperty(journeydate);

        }

        public String getTrain() {
            return train.get();
        }

        public String getPassenger() {
            return passenger.get();
        }

        public int getAge() {
            return age.get();
        }

        public String getGender() {
            return gender.get();
        }
        
        public String getSource() {
            return source.get();
        }
        
        public String getDest() {
            return dest.get();
        }
        public String getJourneydate() {
            return journeydate.get();
        }
        
        
        
        
    }
    
      void initcol1() {
        passengercol.setCellValueFactory(new PropertyValueFactory<>("passenger"));
        agecol.setCellValueFactory(new PropertyValueFactory<>("age"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        sourcecol.setCellValueFactory(new PropertyValueFactory<>("source"));
        destcol.setCellValueFactory(new PropertyValueFactory<>("dest"));
        journeydatecol.setCellValueFactory(new PropertyValueFactory<>("journeydate"));
        traincol.setCellValueFactory(new PropertyValueFactory<>("train"));
    }
    
     
      void loadData1() {
         System.out.println("ajay mourya");
         //tableview.getItems().clear();
//         for ( int i = 0; i<tableview.getItems().size(); i++) {
//                  tableview.getItems().clear();
//         }
        //DatabaseHandler handler = new DatabaseHandler();
        String qu = "SELECT * FROM PASSENGERS";
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            while (rs.next()){
                String trainx = rs.getString("TRAIN_NO");
                String passengernamex = rs.getString("PASSENGER_NAME");
                int agex = rs.getInt("AGE");
                String genderx = rs.getString("GENDER");
                String sourcex = rs.getString("SOURCE");
                String destx = rs.getString("DESTINATION");
                String journeydatex = rs.getString("JOURNEY_DATE");
              
                list1.add(new train(trainx,passengernamex,agex,genderx,sourcex,destx,journeydatex));


            }
        } catch (SQLException ex) {
            Logger.getLogger(CancelTicketController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        tableview.getItems().setAll(list1);
       //tableview.getItems().clear();
    }
    
}

