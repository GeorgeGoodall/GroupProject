
import javafx. application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;


public class RegisterPage extends Application{
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/Register.fxml"));
		Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
	}
}
