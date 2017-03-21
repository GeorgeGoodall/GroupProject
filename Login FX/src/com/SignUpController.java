/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import static com.LoginController.DB_URL;
import java.net.URL;
import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static javax.swing.text.html.HTML.Tag.INPUT;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * FXML Controller class
 *
 * @author Ivonna
 */
public class SignUpController implements Initializable {
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
   
   //FXMLbuttons and labels
    @FXML
    private TextField userName;
    @FXML
    private Label userNameError;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField passwordRepeat;
    @FXML
    private TextField email;
    @FXML
    private Label emailNameError;
    @FXML
    private TextArea hint;
    @FXML
    private void btnCancel(ActionEvent event) throws Exception{
        
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/Login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Home Frame");
            stage.show();
       
    }
    @FXML
    private void btnSubmit(ActionEvent event) throws Exception{
            ArrayList<String> errors = new ArrayList<String>();
            hint.setText("");
            
            Pattern emailAddress = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
            if(!emailAddress.matcher(email.getText()).find()){
                errors.add("Invalid email address");
            }
            
            if(password1.getText().length()<8){
                errors.add("Password too short");
            }
            
            if(email.getText().length()>80){
                errors.add("Email address is too long");
            }
            
            if(password1.getText().length()>30){
                errors.add("Password too long");
            }
          
            Pattern hasUppercase = Pattern.compile("([A-Z])");
            Pattern hasLowercase = Pattern.compile("([a-z])");
            Pattern hasNumber = Pattern.compile("\\d");
            Pattern username = Pattern.compile("[A-Za-z0-9\\s]");
            //Pattern hasSpecialChar = Pattern.compile("[^a-zA-Z0-9\\s-_]");
            if (!username.matcher(userName.getText()).find()) {
                 errors.add("Please use letters and digits only for your user name");
            }
            
            if (!hasLowercase.matcher(userName.getText()).find() && !hasUppercase.matcher(userName.getText()).find()) {
                 errors.add("Username has to have at least one letter");
            }
            
              if (userName.getText().length()<8 || userName.getText().length()>30) {
                 errors.add("User name too long or too short");
            }

             if (!hasUppercase.matcher(password1.getText()).find()) {
            //logger.info(password + " <-- needs uppercase");
            errors.add("Password needs at least 1 upper case letter");
        }
              if (!hasLowercase.matcher(password1.getText()).find()) {
            //logger.info(password + " <-- needs uppercase");
            errors.add("Password needs at least 1 lower case letter");
        }
             if (!hasNumber.matcher(password1.getText()).find()) {
            //logger.info(password + " <-- needs uppercase");
            errors.add("Password needs at least 1 digit");
        }
 
            if (!password1.getText().equals(passwordRepeat.getText())){
                //System.out.println("passwords don't match");
                errors.add("Passwords don't match");
            }
            
            if(errors.size()==0 &&userNameError.getText().equals("")&&emailNameError.getText().equals("") ){
                Connection conn = null;
                Statement stmt = null;
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                String sql3;
                String query3 = "Insert into Users ( userName, password, email)"  + " values (?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query3);
                
                //encryption
                try{            
                    Security.addProvider(new BouncyCastleProvider());
                    input = password1.getText().getBytes();
                     SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
                     IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
                     cipher = Cipher.getInstance("DES/CTR/NoPadding", "BC");
                     cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
                     cipherText = new byte[cipher.getOutputSize(input.length)];
                     ctLength = cipher.update(input, 0, input.length, cipherText, 0);
                     ctLength += cipher.doFinal(cipherText, ctLength);
                     System.out.println("cipher: " + new String(cipherText));
                     
                     preparedStmt.setString (1, userName.getText());
                     preparedStmt.setString (2, new String(cipherText));
                     preparedStmt.setString (3, email.getText());
                     preparedStmt.execute();
                     
                }catch (Exception ex){

                System.out.println("Something went wrong");
                }                
                
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Parent parent = FXMLLoader.load(getClass().getResource("/com/Main.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("Home Frame");
                stage.show();
            }
            else{
                
                System.out.println("Size before" + errors.size());
                for (int i=0; i<errors.size();i++){
                    hint.appendText( errors.get(i).toString());
                    hint.appendText("\n");
                }
               errors.clear(); 
               System.out.println("Size after" + errors.size());
               
                
        }

                
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userName.focusedProperty().addListener(new ChangeListener<Boolean>()
                
        {
        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                userNameError.setText("");
                if (newPropertyValue){
                    //user.setText(user.getText().replace(" miles", ""));
                    System.out.println("In that focus!!!");
                }
                else{                
                        //user.setText(user.getText().concat(" miles"));
                    System.out.println("Out that focus???");
                    if(userName.getText().length()!=0) {
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
               sql = "SELECT userName FROM Users";
               //sql2 = "SELECT email FROM Users ";
               ResultSet rs = stmt.executeQuery(sql);
               //ResultSet rs2 = stmt.executeQuery(sql2);
               //STEP 5: Extract data from result set
                while(rs.next()){
                   //Retrieve by column name
                   String usernameCheck = rs.getString("userName");
                   System.out.println("Reached???");
                   System.out.print("UserName == " + userName.getText() + "  ");
                   System.out.println("userNameCheck == " + usernameCheck);
                   String str = userName.getText();
                   if(str.equals(usernameCheck)){
                       //errors.add("Username already taken");
                       userNameError.setText("Username already taken");
                       break;
                   }                   

                }
//                else if (rs2.next()){
//                    String emailCheck = rs2.getString("email");
//                    if(emailCheck.equals(email.getText())){
//                        errors.add("Email address already in use");
//                    }
//                }
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
        
        email.focusedProperty().addListener(new ChangeListener<Boolean>()
                
        {
        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                emailNameError.setText("");
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
                       emailNameError.setText("Email already in use");
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
