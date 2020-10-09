/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Changepin;

import DashBoard.DashboardController;
import Login.loginController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ChangepinController implements Initializable {
    @FXML
    private TextField oldpin;
    @FXML
    private TextField newpin;
    @FXML
    private TextField confirmpin;
    DashboardController d=new DashboardController();
    public void ChangePIN(MouseEvent event) throws IOException
    {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
      try{ 
          Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12344367", "sql12344367","8gqaKXNXDN");
         String sql="SELECT * FROM userdata WHERE AccountNo=? and PIN=?";
           ps=con.prepareStatement(sql);
           ps.setString(1,loginController.acc);
           ps.setString(2,oldpin.getText());
           rs=ps.executeQuery();
           if(rs.next())
           {       if(newpin.getText().equals(confirmpin.getText())){
                   String sql1="UPDATE userdata SET PIN='"+newpin.getText()+"'WHERE AccountNo='"+loginController.acc+"'";
                   ps=con.prepareStatement(sql1);
                   ps.execute();
                  
                     Alert a=new Alert(Alert.AlertType.INFORMATION);
                     a.setTitle("Pin Changed");
                     a.setHeaderText("Pin Changed Successfully");
                     a.setContentText("PIN has been Successfully Changed Now you can login using new PIN.");
                     a.showAndWait();
                     d.logout(event);
                     oldpin.setText("");
                     newpin.setText("");
                     confirmpin.setText("");
                   
                }
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
