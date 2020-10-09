/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TransactionHistory;

import Login.loginController;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author offic
 */
public class TransactionhistoryController implements Initializable {
    Connection con=null;
   PreparedStatement ps=null;
   ResultSet rs=null;
    @FXML
    private TableView<History> withdraw_table;
    @FXML
    private TableColumn<History,String> withdraw_accountno;
    @FXML
    private TableColumn<History,String> withdraw_amount;
    @FXML
    private TableColumn<History,String> withdraw_remainingamount;
    @FXML
    private TableColumn<History,String> withdraw_date;
    @FXML
    private TableColumn<History,String> withdraw_time;
    
    //Deposit
    @FXML
    private TableView<History> deposit_table;
    @FXML
    private TableColumn<History,String> deposit_accountno;
    @FXML
    private TableColumn<History,String> deposit_amount;
    @FXML
    private TableColumn<History,String> deposit_remainingamount;
    @FXML
    private TableColumn<History,String> deposit_date;
    @FXML
    private TableColumn<History,String> deposit_time;
    //Transfer
    @FXML
    private TableView<History> transfer_table;
    @FXML
    private TableColumn<History,String> transfer_accountno;
    @FXML
    private TableColumn<History,String> transfer_amount;
    @FXML
    private TableColumn<History,String> transfer_remainingamount;
    @FXML
    private TableColumn<History,String> transfer_date;
    @FXML
    private TableColumn<History,String> transfer_time;
    //receive
    @FXML
    private TableView<History> receive_table;
    @FXML
    private TableColumn<History,String> receive_accountno;
    @FXML
    private TableColumn<History,String> receive_amount;
    @FXML
    private TableColumn<History,String> receive_remainingamount;
    @FXML
    private TableColumn<History,String> receive_date;
    @FXML
    private TableColumn<History,String> receive_time;
    @FXML
    public void withDraw(){
          withdraw_accountno.setCellValueFactory(new PropertyValueFactory<History,String>("name"));
          withdraw_amount.setCellValueFactory(new PropertyValueFactory<History,String>("amount"));
          withdraw_remainingamount.setCellValueFactory(new PropertyValueFactory<History,String>("generic"));
          withdraw_date.setCellValueFactory(new PropertyValueFactory<History,String>("date"));
          withdraw_time.setCellValueFactory(new PropertyValueFactory<History,String>("time"));
          ObservableList<History> list=FXCollections.observableArrayList();
          try{ 
          Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12344367", "sql12344367","8gqaKXNXDN");
String sql="SELECT * FROM withdraw WHERE AccountNo=?";
           ps=con.prepareStatement(sql);
           ps.setString(1,loginController.acc);
           rs=ps.executeQuery();
           while(rs.next())
           {    list.add(new History(rs.getString("AccountNo"),rs.getString("WithdrawAmount"),rs.getString("RemainingAmount"),rs.getString("Date"),rs.getString("Time")));
                   }
           
      }catch(ClassNotFoundException | SQLException e){
           Alert a=new Alert(Alert.AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Error in Fetching Data");
           a.setContentText("Error in Fetching Data. TRY AGAIN!!");
           a.showAndWait();
      }
          withdraw_table.setItems(list);   
    }
    public void deposit(){
          deposit_accountno.setCellValueFactory(new PropertyValueFactory<History,String>("name"));
          deposit_amount.setCellValueFactory(new PropertyValueFactory<History,String>("amount"));
          deposit_remainingamount.setCellValueFactory(new PropertyValueFactory<History,String>("generic"));
          deposit_date.setCellValueFactory(new PropertyValueFactory<History,String>("date"));
          deposit_time.setCellValueFactory(new PropertyValueFactory<History,String>("time"));
          ObservableList<History> list=FXCollections.observableArrayList();
          try{ 
          Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12344367", "sql12344367","8gqaKXNXDN");
String sql="SELECT * FROM deposit WHERE AccountNo=?";
           ps=con.prepareStatement(sql);
           ps.setString(1,loginController.acc);
           rs=ps.executeQuery();
           while(rs.next())
           {    list.add(new History(rs.getString("AccountNo"),rs.getString("DepositAmount"),rs.getString("NewAmount"),rs.getString("Date"),rs.getString("Time")));
                   }
           
      }catch(ClassNotFoundException | SQLException e){
           Alert a=new Alert(Alert.AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Error in Fetching Data");
           a.setContentText("Error in Fetching Data. TRY AGAIN!!");
           a.showAndWait();
      }
          deposit_table.setItems(list);   
    }
     public void Transfer(){
          transfer_accountno.setCellValueFactory(new PropertyValueFactory<History,String>("name"));
          transfer_amount.setCellValueFactory(new PropertyValueFactory<History,String>("amount"));
          transfer_remainingamount.setCellValueFactory(new PropertyValueFactory<History,String>("generic"));
          transfer_date.setCellValueFactory(new PropertyValueFactory<History,String>("date"));
          transfer_time.setCellValueFactory(new PropertyValueFactory<History,String>("time"));
          ObservableList<History> list=FXCollections.observableArrayList();
          try{ 
          Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12344367", "sql12344367","8gqaKXNXDN");
String sql="SELECT * FROM transferamount WHERE AccountNo=?";
           ps=con.prepareStatement(sql);
           ps.setString(1,loginController.acc);
           rs=ps.executeQuery();
           while(rs.next())
           {    list.add(new History(rs.getString("AccountNo"),rs.getString("Amount"),rs.getString("SendTo"),rs.getString("Date"),rs.getString("Time")));
                   }
           
      }catch(ClassNotFoundException | SQLException e){
           Alert a=new Alert(Alert.AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Error in Fetching Data");
           a.setContentText("Error in Fetching Data. TRY AGAIN!!");
           a.showAndWait();
      }
          transfer_table.setItems(list);   
    }
      public void Received(){
          receive_accountno.setCellValueFactory(new PropertyValueFactory<History,String>("name"));
          receive_amount.setCellValueFactory(new PropertyValueFactory<History,String>("amount"));
          receive_remainingamount.setCellValueFactory(new PropertyValueFactory<History,String>("generic"));
          receive_date.setCellValueFactory(new PropertyValueFactory<History,String>("date"));
          receive_time.setCellValueFactory(new PropertyValueFactory<History,String>("time"));
          ObservableList<History> list=FXCollections.observableArrayList();
          try{ 
          Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12344367", "sql12344367","8gqaKXNXDN");
String sql="SELECT * FROM transferamount WHERE SendTo=?";
           ps=con.prepareStatement(sql);
           ps.setString(1,loginController.acc);
           rs=ps.executeQuery();
           while(rs.next())
           {    list.add(new History(rs.getString("SendTo"),rs.getString("Amount"),rs.getString("AccountNo"),rs.getString("Date"),rs.getString("Time")));
                   }
           
      }catch(ClassNotFoundException | SQLException e){
           Alert a=new Alert(Alert.AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Error in Fetching Data");
           a.setContentText("Error in Fetching Data. TRY AGAIN!!");
           a.showAndWait();
      }
          receive_table.setItems(list);   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        withDraw();
        deposit();
        Transfer();
        Received();
    }    
    
}
