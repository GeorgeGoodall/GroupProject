package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class HomeController implements Initializable {

		@FXML
		private AnchorPane rootPane;
		
		@FXML
		private void loadViewAllGroups(ActionEvent event) {
		

		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewAllGroups.fxml"));
			rootPane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
}

		
		

