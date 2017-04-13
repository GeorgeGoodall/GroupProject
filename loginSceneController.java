import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class loginSceneController{
	
	@FXML
	private Text txt_error;
	
	public void showErrorMessage(ActionEvent event) throws IOException {
		//testing if login details are correct go here
		txt_error.setVisible(true);
		((Node) (event.getSource())).getScene().getWindow().hide();
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/HomeController.fxml"));
		Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
	}
	
}
