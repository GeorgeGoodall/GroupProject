
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class currentWindow extends Application{
	@Override
	public void start(Stage stage) {
		try{
			Parent root = FXMLLoader.load(getClass().getResource("/OpeningScreen.fxml"));
			Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.setTitle("Login");
	        stage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
