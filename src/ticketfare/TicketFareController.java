/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketfare;

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
import reservationsmade.ReservationmadeController;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class TicketFareController implements Initializable {

    ObservableList<TicketFareController.train> list = FXCollections.observableArrayList();
    @FXML
    private TableView<train> tableview;
    @FXML
    private TableColumn<train, String> trainnocol;
    @FXML
    private TableColumn<train, String> sourcecol;
    @FXML
    private TableColumn<train, String> destcol;
    @FXML
    private TableColumn<train, String> farecol;

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
        trainnocol.setCellValueFactory(new PropertyValueFactory<>("train"));
        sourcecol.setCellValueFactory(new PropertyValueFactory<>("source"));
        destcol.setCellValueFactory(new PropertyValueFactory<>("dest"));
        farecol.setCellValueFactory(new PropertyValueFactory<>("fare"));
        //journeydatecol.setCellValueFactory(new PropertyValueFactory<>("journeydate"));
    }
    
     private void loadData() {
         System.out.println("ajay mourya");
        DatabaseHandler handler = new DatabaseHandler();
        String qu = "SELECT * FROM FARE";
        ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()){
                String trainx = rs.getString("TRAIN_NO");
                String sourcex = rs.getString("SOURCE");
                String destx = rs.getString("DESTINATION");
                int farex = rs.getInt("FARE");
                //String journeydatex = rs.getString("JOURNEY_DATE");
              
                list.add(new TicketFareController.train(trainx,sourcex,destx,farex));


            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationmadeController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        tableview.getItems().setAll(list);

    }
     
      public static class train{
        private final SimpleStringProperty train;
        private final SimpleStringProperty source;
        private final SimpleIntegerProperty fare;
        private final SimpleStringProperty dest;
       // private final SimpleStringProperty journeydate;

        public train(String train,String source,String dest,int fare) {
            this.train = new SimpleStringProperty(train);
            this.source = new SimpleStringProperty(source);
            this.fare = new SimpleIntegerProperty(fare);
            this.dest = new SimpleStringProperty(dest);
            //this.journeydate = new SimpleStringProperty(journeydate);

        }

        public String getTrain() {
            return train.get();
        }

        public String getSource() {
            return source.get();
        }

        public String getDest() {
            return dest.get();
        }

        public int getFare() {
            return fare.get();
        }

       
        
        
        
    }
}
