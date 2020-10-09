/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class loginController implements Initializable {
    public static Stage stage=null;
    public static String acc;
    @FXML
    private Pane main_area;
    @FXML
     private TextField accountno;
    @FXML
    private PasswordField pin;
    @FXML
    public void logintoaccount(MouseEvent event) throws IOException
    { 
      Connection con=null;
      PreparedStatement ps=null;
      ResultSet rs=null;
      try{ 
          Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12344367", "sql12344367","8gqaKXNXDN");
String sql="SELECT * FROM userdata WHERE AccountNo=? and PIN=?";
           ps=con.prepareStatement(sql);
           ps.setString(1,accountno.getText());
           ps.setString(2,pin.getText());
           acc=accountno.getText();
           rs=ps.executeQuery();
           if(rs.next())
           {   try{ 
                ((Node)event.getSource()).getScene().getWindow().hide();
               Parent root=FXMLLoader.load(getClass().getResource("/DashBoard/dashboard.fxml"));
                Scene scene=new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/Design/design.css").toExternalForm());
                Stage stage=new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
                this.stage=stage;
           }catch(Exception e)
           { System.out.println(e.getMessage());
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
    @FXML
    private void closeApp(MouseEvent event) {
            Platform.exit();
            System.exit(0);
    }
    @FXML
    private void createAccount(MouseEvent event) throws IOException{
        Parent fxml=FXMLLoader.load(getClass().getResource("/CreateAccount/createaccount.fxml"));
        main_area.getChildren().removeAll();
        main_area.getChildren().addAll(fxml);
    }
     @FXML
    private void forgotpass(MouseEvent event) throws IOException{
        Parent fxml=FXMLLoader.load(getClass().getResource("/ForgotPassword/forgotpassword.fxml"));
        main_area.getChildren().clear();
        main_area.getChildren().addAll(fxml);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
