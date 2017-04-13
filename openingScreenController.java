import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class openingScreenController {
	
	public void showLoginScreen(ActionEvent event) throws IOException{
		
		((Node) (event.getSource())).getScene().getWindow().hide();
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/loginScreenBuilder.fxml"));
		Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
	}

}
