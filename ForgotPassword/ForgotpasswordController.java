/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForgotPassword;

import Login.Banking;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ForgotpasswordController implements Initializable {
    @FXML
    private TextField accountno;
    @FXML
    private ComboBox<String> securityquestion;
     ObservableList<String> list=FXCollections.observableArrayList("What is the name of your First School?","What is your Nick Name?","What is Your Favourite Colour?");
    
    @FXML
    private TextField answer;
    @FXML
    public void backtologin(MouseEvent event) throws IOException{
       Banking.stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Login/login.fxml")));
//       Parent fxml=FXMLLoader.load(getClass().getResource("/Login/login.fxml"));
//        create_main.getChildren().clear();
//        create_main.getChildren().addAll(fxml);
    }
    @FXML
    public void closeApp(MouseEvent event){
       Platform.exit();
      System.exit(0);
    }
    public void recoverPassword(MouseEvent event)
    { 
      Connection con=null;
      PreparedStatement ps=null;
      ResultSet rs=null;
      try{ 
          Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12344367", "sql12344367","8gqaKXNXDN");
String sql="SELECT * FROM userdata WHERE AccountNo=? and SecurityQuestion=? and Answer=?";
           ps=con.prepareStatement(sql);
           ps.setString(1,accountno.getText());
           ps.setString(2,securityquestion.getValue());
           ps.setString(3,answer.getText());
         
           rs=ps.executeQuery();
           if(rs.next())
           {  Alert a=new Alert(Alert.AlertType.INFORMATION);
           a.setTitle("Password Recovery");
           a.setHeaderText("Bellow is Your password");
           a.setContentText("Account No:  "+rs.getString("AccountNo")+"\n PIN:   "+rs.getString("PIN"));
           a.showAndWait();
           }
           else{
                Alert a=new Alert(Alert.AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Wrong Data");
           a.setContentText("Error in Recovery Please Try Again!!!");
           a.showAndWait();
           }
      }catch(ClassNotFoundException | SQLException e){
           Alert a=new Alert(Alert.AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Error in program");
           a.setContentText("Cannot Retrieve Data due to some technical issue");
           a.showAndWait();
      } 
    
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        securityquestion.setItems(list);
    }
}