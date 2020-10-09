/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Deposit;

import Login.loginController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class DepositController implements Initializable {

   @FXML 
   private Label accountno;
   @FXML
   private Label balance;
   @FXML 
   private TextField amount;
   @FXML 
   private TextField pin;
   Calendar cal=Calendar.getInstance();
   int year=cal.get(Calendar.YEAR);
   int month=cal.get(Calendar.MONTH);
   int day=cal.get(Calendar.DAY_OF_MONTH);
   int hour=cal.get(Calendar.HOUR);
   int minute=cal.get(Calendar.MINUTE);
   int second=cal.get(Calendar.SECOND);
   int daynight=cal.get(Calendar.AM_PM);
   DateFormat dateformat=new SimpleDateFormat("yyyy/mm/dd");
   Date d=new Date();
   String date=dateformat.format(d);
   
   LocalTime localtime=LocalTime.now();
   DateTimeFormatter dt=DateTimeFormatter.ofPattern("hh:mm:ss a");
   String time=localtime.format(dt);
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
           {    accountno.setText(rs.getString("AccountNo"));
                balance.setText(rs.getString("Balance"));
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
    public void depositButton() throws IOException
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
           ps.setString(2, pin.getText());
           rs=ps.executeQuery();
           if(rs.next())
           {   int da=Integer.parseInt(amount.getText());
               int ta=Integer.parseInt(balance.getText());
               
                   int total=ta+da;
                   String sql1="UPDATE userdata SET Balance='"+total+"'WHERE AccountNo='"+loginController.acc+"'";
                   ps=con.prepareStatement(sql1);
                   ps.execute();
                   String sql2="INSERT INTO deposit(AccountNo,DepositAmount,NewAmount,Date,Time) VALUES(?,?,?,?,?)";
                   ps=con.prepareStatement(sql2);
                   ps.setString(1, loginController.acc);
                   ps.setString(2,String.valueOf(da));
                   ps.setString(3,String.valueOf(total));
                   ps.setString(4,date);
                   ps.setString(5,time);
                   int i=ps.executeUpdate();
                   if(i>0)
                   { Alert a=new Alert(Alert.AlertType.INFORMATION);
                     a.setTitle("Amount Deposited");
                     a.setHeaderText("Amount Deposited Successfully");
                     a.setContentText("Amount"+da+" has been Successfully Deposited\n"+"Current Balance= "+total);
                     a.showAndWait();
                     amount.setText("");
                     pin.setText("");
                     balance.setText(String.valueOf(total));
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
       try {
           setInfo();
       } catch (IOException ex) {
           Logger.getLogger(DepositController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }    
    
}
