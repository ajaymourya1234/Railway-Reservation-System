/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationsmade;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class ReservationmadeController implements Initializable {

    ObservableList<train> list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane rootpane;
    @FXML
    private TableView<train> tableview;
    @FXML
    private TableColumn<train, String> passengercol;
    @FXML
    private TableColumn<train, Integer> agecol;
    @FXML
    private TableColumn<train, String> gendercol;
    @FXML
    private TableColumn<train, String> journeydatecol;
    @FXML
    private TableColumn<train, String> traincol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        initcol();
        loadData();
    }    

    private void initcol() {
        traincol.setCellValueFactory(new PropertyValueFactory<>("train"));
        passengercol.setCellValueFactory(new PropertyValueFactory<>("passenger"));
        agecol.setCellValueFactory(new PropertyValueFactory<>("age"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        journeydatecol.setCellValueFactory(new PropertyValueFactory<>("journeydate"));
    }

    private void loadData() {
         System.out.println("ajay mourya");
        DatabaseHandler handler = new DatabaseHandler();
        String qu = "SELECT * FROM PASSENGERS";
        ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()){
                String trainx = rs.getString("TRAIN_NO");
                String passengernamex = rs.getString("PASSENGER_NAME");
                int agex = rs.getInt("AGE");
                String genderx = rs.getString("GENDER");
                String journeydatex = rs.getString("JOURNEY_DATE");
              
                list.add(new train(trainx,passengernamex,agex,genderx,journeydatex));


            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationmadeController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        tableview.getItems().setAll(list);

    }
    
    public static class train{
        private final SimpleStringProperty train;
        private final SimpleStringProperty passenger;
        private final SimpleIntegerProperty age;
        private final SimpleStringProperty gender;
        private final SimpleStringProperty journeydate;

        public train(String train,String passenger,int age,String gender,String journeydate) {
            this.train = new SimpleStringProperty(train);
            this.passenger = new SimpleStringProperty(passenger);
            this.age = new SimpleIntegerProperty(age);
            this.gender = new SimpleStringProperty(gender);
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

        public String getJourneydate() {
            return journeydate.get();
        }
        
        
        
        
    }
    
}

