
package DashBoard;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Login.loginController;
import static Login.loginController.stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

public class DashboardController implements Initializable {
    private double xOffset=0;
    private double yOffset=0;
    @FXML
    private Pane dashboard_main;
    @FXML
    private FontAwesomeIconView ico;
    @FXML
    private Text name;
     @FXML
    private void closeApp(MouseEvent event) {
            Platform.exit();
            System.exit(0);
    }
    @FXML
    private void minimizeApp(MouseEvent event) {
            Stage stage=(Stage) ico.getScene().getWindow();
            stage.setIconified(true);
    }
    @FXML
    public void setData() throws IOException
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
    public void click(MouseEvent event){
      xOffset=event.getSceneX();
            yOffset=event.getSceneY();
    }
    @FXML
    public void drag(MouseEvent event){
    stage.setX(event.getScreenX()-xOffset);
            stage.setY(event.getScreenY()-yOffset);
    }
     @FXML
    public void accountInformation(MouseEvent event) throws IOException{
    Parent fxml=FXMLLoader.load(getClass().getResource("/AccountInformation/accountinformation.fxml"));
        dashboard_main.getChildren().clear();
        dashboard_main.getChildren().addAll(fxml);
    }
    @FXML
    public void withdraw(MouseEvent event) throws IOException{
    Parent fxml=FXMLLoader.load(getClass().getResource("/Withdraw/withdraw.fxml"));
        dashboard_main.getChildren().clear();
        dashboard_main.getChildren().addAll(fxml);
    }
    @FXML
    public void Deposit(MouseEvent event) throws IOException{
    Parent fxml=FXMLLoader.load(getClass().getResource("/Deposit/deposit.fxml"));
        dashboard_main.getChildren().clear();
        dashboard_main.getChildren().addAll(fxml);
    }
    @FXML
    public void transactionHistoryData(MouseEvent event) throws IOException{
    Parent fxml=FXMLLoader.load(getClass().getResource("/TransactionHistory/transactionhistory.fxml"));
        dashboard_main.getChildren().clear();
        dashboard_main.getChildren().addAll(fxml);
    }
    @FXML
    public void changepin(MouseEvent event) throws IOException{
    Parent fxml=FXMLLoader.load(getClass().getResource("/Changepin/changepin.fxml"));
        dashboard_main.getChildren().clear();
        dashboard_main.getChildren().addAll(fxml);
    }
    @FXML
    public void transfermoney(MouseEvent event) throws IOException{
    Parent fxml=FXMLLoader.load(getClass().getResource("/TransferAmount/transferamount.fxml"));
        dashboard_main.getChildren().clear();
        dashboard_main.getChildren().addAll(fxml);
    }
    @FXML
    public void mainScreen() throws IOException{
    Parent fxml=FXMLLoader.load(getClass().getResource("/DashBoard/mainscreen.fxml"));
        dashboard_main.getChildren().clear();
        dashboard_main.getChildren().addAll(fxml);
    }
    @FXML
    public void logout(MouseEvent event) throws IOException{
      ((Node)event.getSource()).getScene().getWindow().hide();
      Parent root=FXMLLoader.load(getClass().getResource("/Login/login.fxml"));
                Scene scene=new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/Design/design.css").toExternalForm());
                Stage stage=new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
        root.setOnMousePressed((MouseEvent e) -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent e) -> {
            stage.setX(event.getScreenX()-xOffset);
            stage.setY(event.getScreenY()-yOffset);
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setData();
            mainScreen();
            
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
