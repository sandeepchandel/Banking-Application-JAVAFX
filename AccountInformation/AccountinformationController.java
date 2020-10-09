/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccountInformation;

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
import javafx.scene.text.Text;

public class AccountinformationController implements Initializable {
    @FXML
    private Text accountno;
    @FXML
    private Text gender;
    @FXML
    private Text accounttype;
    @FXML
    private Text religion;
    @FXML
    private Label balance;
    @FXML
    private Pane account_information;
    @FXML
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
           {   balance.setText(rs.getString("Balance")); 
               accountno.setText(rs.getString("AccountNo"));
                gender.setText(rs.getString("Gender"));
                accounttype.setText(rs.getString("AccountType"));
                religion.setText(rs.getString("Religion"));
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
    public void withdraw(MouseEvent event) throws IOException{
    Parent fxml=FXMLLoader.load(getClass().getResource("/Withdraw/withdraw.fxml"));
        account_information.getChildren().clear();
        account_information.getChildren().addAll(fxml);
    }
    @FXML
    public void deposit(MouseEvent event) throws IOException{
    Parent fxml=FXMLLoader.load(getClass().getResource("/Deposit/deposit.fxml"));
        account_information.getChildren().clear();
        account_information.getChildren().addAll(fxml);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setInfo();
        } catch (IOException ex) {
            Logger.getLogger(AccountinformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
