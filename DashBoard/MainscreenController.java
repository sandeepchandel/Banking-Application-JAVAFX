/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DashBoard;

import Login.loginController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainscreenController implements Initializable {
    @FXML
    private Label name;
    @FXML
    private Label body;
    @FXML
    private Pane dashboard_main;
    public void setInfo() throws IOException
    {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
      try{ 
          Class.forName("com.mysql.jdbc.Driver");
          con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12344367", "sql12344367","8gqaKXNXDN");
          String sql="SELECT * FROM userdata WHERE AccountNo=?";
           ps=con.prepareStatement(sql);
           ps.setString(1,loginController.acc);
           rs=ps.executeQuery();
           if(rs.next())
           {    name.setText(rs.getString("Name"));
           }
           else{
                Alert a=new Alert(Alert.AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Account Number or Password incorrect");
           a.setContentText("Please Try Again!!!");
           a.showAndWait();
           }
      }catch(ClassNotFoundException | SQLException e){
           Alert a=new Alert(Alert.AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Error in Loging in");
           a.setContentText("Cannot log in due to some technical issue");
           a.showAndWait();
      }
    }
    @FXML
    public void transferit(MouseEvent event) throws IOException{
    Parent fxml=FXMLLoader.load(getClass().getResource("/TransferAmount/transferamount.fxml"));
        dashboard_main.getChildren().clear();
        dashboard_main.getChildren().addAll(fxml);
    }
     @FXML
    public void viewTransactions(MouseEvent event) throws IOException{
    Parent fxml=FXMLLoader.load(getClass().getResource("/TransactionHistory/transactionhistory.fxml"));
        dashboard_main.getChildren().clear();
        dashboard_main.getChildren().addAll(fxml);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        body.setText("The MyBank Limited is an Indian multinational, public sector banking and\nfinancial services statutory body. Headquartered in jalandhar, Punjab.\nMyBank is ranked as 236th in the Fortune Global 500 list of the world's\n biggest corporations of 2019.");
        try {
            setInfo();
        } catch (IOException ex) {
            Logger.getLogger(MainscreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
