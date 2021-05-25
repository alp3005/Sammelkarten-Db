package application;
	
import java.util.ArrayList;

import Cards.Card;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

//Kommentar von Mathieu

//Kommentar von Felix

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// nur test
			Database database = new Database();
			ArrayList<Card> cards = database.loadCards();
		
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml")); 
			Scene scene = new Scene(root,1600,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
	
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
