package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalendarController implements Initializable {

	    @FXML
	    private JFXDrawer drawer;

	    @FXML
	    private JFXHamburger hamburger;
	    
		@FXML
		private void handleButtonAction(ActionEvent event) throws IOException{
			Parent homeParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene homeScene = new Scene(homeParent);
			Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			//accessing window, one main stage with multiple scene(can have more than one stage)
			mainStage.setScene(homeScene);
			mainStage.show();
		}
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			try{
				VBox box = FXMLLoader.load(getClass().getClassLoader().getResource("application/GroupDrawer.fxml"));
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
				Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		

	}