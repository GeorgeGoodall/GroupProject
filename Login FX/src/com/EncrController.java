/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

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


/**
 * FXML Controller class
 *
 * @author Ivonna
 */
public class EncrController implements Initializable {
        
    byte[] input;
    byte[] keyBytes = "12345678".getBytes();
    byte[] ivBytes = "fnYbw123".getBytes();    
    SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
    IvParameterSpec icSpec = new IvParameterSpec(ivBytes);
    Cipher cipher;
    byte[] cipherText;
    int ctLength;
    
    @FXML
    private TextField txtInput; 
    @FXML
    private TextField encrypt_txt;
    @FXML
    private TextField decrypt_txt;
    @FXML
    private void encrAction(ActionEvent event) throws Exception{
        try{
            
            Security.addProvider(new BouncyCastleProvider());
            input = txtInput.getText().getBytes();
             SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
             IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
             cipher = Cipher.getInstance("DES/CTR/NoPadding", "BC");
             cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
             cipherText = new byte[cipher.getOutputSize(input.length)];
             ctLength = cipher.update(input, 0, input.length, cipherText, 0);
             ctLength += cipher.doFinal(cipherText, ctLength);
                encrypt_txt.setText(new String(cipherText));
             System.out.println("cipher: " + new String(cipherText));
        }catch (Exception ex){
            
        System.out.println("");
        }
    }  
    
    @FXML
    private void decrAction(ActionEvent event) throws Exception{
        try{
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
             cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
             byte[] plainText = new byte[cipher.getOutputSize(ctLength)];
             int ptLength = cipher.update(cipherText, 0, ctLength, plainText, 0);
             ptLength += cipher.doFinal(plainText, ptLength); 
             decrypt_txt.setText(new String(plainText));
            
        }catch (Exception ex){
            
        System.out.println("");
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
