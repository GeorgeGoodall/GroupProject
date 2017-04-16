package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


public class CreateFileController implements Initializable {


	    @FXML
	    private JFXDrawer drawer;

	    @FXML
	    private JFXHamburger hamburger;
	    

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			try{
				VBox box = FXMLLoader.load(getClass().getClassLoader().getResource("application/HomeDrawer.fxml"));
				drawer.setSidePane(box);
				
				HamburgerBackArrowBasicTransition burger2arr = new HamburgerBackArrowBasicTransition(hamburger);
				burger2arr.setRate(-1);
				hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
					burger2arr.setRate(burger2arr.getRate() * -1);
					burger2arr.play();
					
					if(drawer.isShown())
						drawer.close();
					else
						drawer.open();
					});
			}catch (IOException e){
				Logger.getLogger(CreateFileController.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}