 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.sql.*;
import java.net.URL;
import java.security.Security;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.sql.Blob;
import javax.sql.rowset.serial.SerialBlob;

/**
 * FXML Controller class
 *
 * @author Ivonna
 */
public class LoginController implements Initializable {
    byte[] input;
    byte[] keyBytes = "12345678".getBytes();
    byte[] ivBytes = "fnYbw123".getBytes();    
    SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
    IvParameterSpec icSpec = new IvParameterSpec(ivBytes);
    Cipher cipher;
    byte[] cipherText;
    int ctLength;
    // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://csmysql.cs.cf.ac.uk/c1522412" ;

   //  Database credentials
   static final String USER = "c1522412";
   static final String PASS = "UQPOMhh3g";    
    @FXML
    private Label labelMessage;
    @FXML
    private TextField txtUsername; 
    @FXML
    private PasswordField txtPassword;
    @FXML
    //btnSignUpAction
    private void btnSignUpAction(ActionEvent event) throws Exception{
        ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/SignUp.fxml"));
            //System.out.println("Button Clicked");
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Sign up frame");
            stage.show();
    }
    @FXML
    private void btnLoginAction(ActionEvent event) throws Exception{
        System.out.println("User  " +txtUsername.getText().getClass());
        
        if(txtUsername.getText().equals("") || txtPassword.getText().equals("")) {
            labelMessage.setText("Please enter your credentials or Sign Up");
        }
        else{
        Connection conn = null;
        Statement stmt = null;
        try{
           //STEP 2: Register JDBC driver
           //Class.forName("com.mysql.jdbc.Driver");

           //STEP 3: Open a connection
           System.out.println("Connecting to database...");
           conn = DriverManager.getConnection(DB_URL,USER,PASS);

           //STEP 4: Execute a query
           System.out.println("Creating statement...");
           stmt = conn.createStatement();
           String sql;
           sql = "SELECT ID, username, password  FROM Users where userName = '" + txtUsername.getText() + "'";
           ResultSet rs = stmt.executeQuery(sql);

           //STEP 5: Extract data from result set
            if(rs.next()){
               //Retrieve by column name
               int id  = rs.getInt("id");
               String username = rs.getString("userName");
               Blob password = rs.getBlob("password");

             
                Security.addProvider(new BouncyCastleProvider());
            input = txtPassword.getText().getBytes();
             SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
             IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
             cipher = Cipher.getInstance("DES/CTR/NoPadding", "BC");
             cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
             cipherText = new byte[cipher.getOutputSize(input.length)];
             ctLength = cipher.update(input, 0, input.length, cipherText, 0);
             ctLength += cipher.doFinal(cipherText, ctLength);
                //encrypt_txt.setText(new String(cipherText));

             System.out.println("cipher: " + new String (cipherText));
             
               //Display values 
               //convert blob into string
               String passwordFix = new String(password.getBytes(1l, (int) password.length()));
               
               if (passwordFix.equals( new String (cipherText)) && username.equals(txtUsername.getText())){
                   //System.out.println("Fixed Pass; " + fixPass.getClass().getSimpleName());
                   System.out.println("ID: " + id);
                   System.out.println(" Password: " + password);
                    labelMessage.setText("Welcome " + txtUsername.getText());
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    //Parent parent = FXMLLoader.load(getClass().getResource("/com/encr.fxml"));
                    Parent parent = FXMLLoader.load(getClass().getResource("/com/Main.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle("Main Frame");
                    stage.show();
                    //break;
               }
                   else{
            labelMessage.setText("Password incorrect");
        }
             
//                if(txtUsername.getText().equals(username) && txtPassword.getText().equals(fixPass)){
//                   
//                }
               
               
            }
            //STEP 6: Clean-up environment
            else{
            labelMessage.setText("Username incorrect");
            }
            rs.close();
            stmt.close();
            conn.close();
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
           }//end finally try
        }//end try
        }
            
           

    }        
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }      
    
}
