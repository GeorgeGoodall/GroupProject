/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import static com.SignUpController.DB_URL;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ivonna
 */
public class ChangePasswordController implements Initializable {
     // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://csmysql.cs.cf.ac.uk/c1522412" ;

   //  Database credentials
   static final String USER = "c1522412";
   static final String PASS = "UQPOMhh3g";
//    char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
//    String randomStr = RandomStringUtils.random( randomStrLength, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
//    System.out.println( randomStr );
    @FXML
    private TextField email;
    @FXML 
    private Label error;
    @FXML
    private void sendPassword(ActionEvent event) throws Exception{
        
        Pattern emailAddress = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
            if(!emailAddress.matcher(email.getText()).find()){
                System.out.println("Invalid email adress ");
            }
             
        ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/changePassword.fxml"));
            //System.out.println("Button Clicked");
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Forgot password frame");
            stage.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        
        email.focusedProperty().addListener(new ChangeListener<Boolean>()
                
        {
        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                error.setText("");
                if (newPropertyValue){
                    //user.setText(user.getText().replace(" miles", ""));
                    System.out.println("In that focus!!!");
                }
                else{                
                        //user.setText(user.getText().concat(" miles"));
                    System.out.println("Out that focus???");
                    if(email.getText().length()!=0) {
                System.out.println("Reached ");
                Connection conn = null;
                Statement stmt = null;
                try{

               //STEP 3: Open a connection
               System.out.println("Connecting to database...");
               conn = DriverManager.getConnection(DB_URL,USER,PASS);

               //STEP 4: Execute a query
               System.out.println("Creating statement...");
               stmt = conn.createStatement();
               String sql;
               //String sql2;
               sql = "SELECT email FROM Users";
               ResultSet rs = stmt.executeQuery(sql);
               //STEP 5: Extract data from result set
                while(rs.next()){
                   //Retrieve by column name
                   String emailCheck = rs.getString("email");
                   System.out.println("Reached???");
                   System.out.print("email == " + email.getText() + "  ");
                   System.out.println("emailCheck == " + emailCheck);
                   String str = email.getText();
                   if(str.equals(emailCheck)){
                       //errors.add("Username already taken");
                       error.setText("Email exists");
                       break;
                   }                   

                }

                //end try
                }catch(SQLException se){
               //Handle errors for JDBC
               se.printStackTrace();
            }catch(Exception e){
               //Handle errors for Class.forName
               e.printStackTrace();
            }finally{
               //finally block used to close resources
               try{
                  if(stmt!=null)
                     stmt.close();
               }catch(SQLException se2){
               }// nothing we can do
               try{
                  if(conn!=null)
                     conn.close();
               }catch(SQLException se){
                  se.printStackTrace();
               }}
                }
        		        }
        		    }
        });    
        
        // TODO
    }
    
}
