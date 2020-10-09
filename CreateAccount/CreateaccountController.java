/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreateAccount;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Login.Banking;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
public class CreateaccountController implements Initializable {
    @FXML
    private Pane create_main;
    @FXML
    private TextField name;
    @FXML
    private TextField idcardno;
    @FXML
    private TextField mobileno;
    @FXML
    private TextField city;
    @FXML
    private TextField address;
    @FXML
    private TextField accountno;
    @FXML
    private TextField pin;
    @FXML
    private TextField balance;
    @FXML
    private TextField answer;
    @FXML
    private DatePicker dob;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private ComboBox<String> maritalstatus;
    @FXML
    private ComboBox<String> religion;
    @FXML
    private ComboBox<String> accounttype;
    @FXML
    private ComboBox<String> questions;
    ObservableList<String> list=FXCollections.observableArrayList("MALE","FEMALE","OTHER");
    ObservableList<String> list1=FXCollections.observableArrayList("SINGLE","MARRIED");
    ObservableList<String> list2=FXCollections.observableArrayList("Hindu","Muslim","Christian","Others");
    ObservableList<String> list3=FXCollections.observableArrayList("SAVINGS","CURRENT");
    ObservableList<String> list4=FXCollections.observableArrayList("What is the name of your First School?","What is your Nick Name?","What is Your Favourite Colour?");
    
    
    @FXML
    public void newaccount(MouseEvent event) throws FileNotFoundException
    { 
      Connection con=null;
      PreparedStatement ps=null;
      try{ 
          Class.forName("com.mysql.cj.jdbc.Driver");
          con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12344367", "sql12344367","8gqaKXNXDN");
          if(validateName() &&  validateIdcardNo()&& validateMobileNo() &&validateBalance()){
           String sql="INSERT INTO userdata(Name,ICN,MobileNo,Gender,MaritalStatus,Religion,DOB,City,Address,AccountNo,PIN,AccountType,Balance,SecurityQuestion,Answer) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
           ps=con.prepareStatement(sql);
           ps.setString(1,name.getText());
           ps.setString(2,idcardno.getText());
           ps.setString(3,mobileno.getText());
           ps.setString(4,gender.getValue());
           ps.setString(5,maritalstatus.getValue());
           ps.setString(6,religion.getValue());
           ps.setString(7,((TextField)dob.getEditor()).getText());
           ps.setString(8,city.getText());
           ps.setString(9,address.getText());
           ps.setString(10,accountno.getText());
           ps.setString(11,pin.getText());
           ps.setString(12,accounttype.getValue());
           ps.setString(13,balance.getText());
           ps.setString(14,questions.getValue());
           ps.setString(15,answer.getText());
           
           int i=ps.executeUpdate();
           if(i>0)
           {  Alert a=new Alert(AlertType.INFORMATION);
           a.setTitle("ACCOUNT CREATED");
           a.setHeaderText("Account Created Successfully");
           a.setContentText("Your Account is created. You can log in using Username and Password provided");
           a.showAndWait();
           ClearAllFields();
           }
           else{
                Alert a=new Alert(AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Account Not Created");
           a.setContentText("Your Account is not created. Try Again");
           a.showAndWait();
           }
            }
      }catch(ClassNotFoundException | SQLException e){
           Alert a=new Alert(AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Error in Creating Account");
           a.setContentText("Your Account is not created due to some technical issue");
           a.showAndWait();
      }
          
    }
    @FXML
    public void backtologin(MouseEvent event) throws IOException{
       Banking.stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Login/login.fxml")));
//       Parent fxml=FXMLLoader.load(getClass().getResource("/Login/login.fxml"));
//        create_main.getChildren().clear();
//        create_main.getChildren().addAll(fxml);
    }
    public boolean validateName(){
       Pattern p=Pattern.compile("[a-zA-Z ]+");
       Matcher m=p.matcher(name.getText());
       if(m.find() && m.group().equals(name.getText())){
         return true;
       }
       else{Alert a=new Alert(AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Error in Name");
           a.setContentText("Please Enter Alphabets only.");
           a.showAndWait();
           return false;
       }
    }
    public boolean validateMobileNo(){
       Pattern p=Pattern.compile("[0-9]+");
       Matcher m=p.matcher(mobileno.getText());
       if(m.find() && m.group().equals(mobileno.getText())){
         return true;
       }
       else{Alert a=new Alert(AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Error in MobileNo Field");
           a.setContentText("Please Enter Digits only.");
           a.showAndWait();
           return false;
       }
    }
    public boolean validateIdcardNo(){
       Pattern p=Pattern.compile("[0-9]+");
       Matcher m=p.matcher(idcardno.getText());
       if(m.find() && m.group().equals(idcardno.getText())){
         return true;
       }
       else{Alert a=new Alert(AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Error in ID Card No. Field");
           a.setContentText("Please Enter Digits only.");
           a.showAndWait();
           return false;
       }
    }
    public boolean validateBalance(){
       Pattern p=Pattern.compile("[0-9]+");
       Matcher m=p.matcher(balance.getText());
       if(m.find() && m.group().equals(balance.getText())){
         return true;
       }
       else{Alert a=new Alert(AlertType.ERROR);
           a.setTitle("Error");
           a.setHeaderText("Error in Balance Field");
           a.setContentText("Please Enter Digits only.");
           a.showAndWait();
           return false;
       }
    }
    public void ClearAllFields(){
       name.clear();
       idcardno.clear();
       gender.getSelectionModel().clearSelection();
       maritalstatus.getSelectionModel().clearSelection();
       religion.getSelectionModel().clearSelection();
       dob.getEditor().clear();
       city.clear();
       address.clear();
       pin.clear();
       accounttype.getSelectionModel().clearSelection();
       balance.clear();
       questions.getSelectionModel().clearSelection();
       answer.clear();
//       Image img=new Image("/Images/download.png");
//       pic.setImage(img);
       accountno.setText(String.valueOf(generateAccountNo()));
    }
    public int generateAccountNo(){
       Random rand=new Random();
       int num=rand.nextInt(899999)+100000;
       return num;
    }
    @FXML
    public void closeApp(MouseEvent event){
       Platform.exit();
      System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gender.setItems(list);
        maritalstatus.setItems(list1);
        religion.setItems(list2);
        accounttype.setItems(list3);
        questions.setItems(list4);
        accountno.setText(String.valueOf(generateAccountNo()));
        accountno.setEditable(false);
    }    
    
}
