package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml")); 
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
	
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Test");
		launch(args);
	}
	
	public static Stage showAddStage() throws IOException {
		System.out.println("Button pressed");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("ErstellenView.fxml"));
		AnchorPane erstellenView = loader.load();
	 
		Stage addDialogeStage = new Stage();
		addDialogeStage.setTitle("Erstelle Sie eine eigene Karte");
		addDialogeStage.initModality(Modality.WINDOW_MODAL);
		addDialogeStage.initOwner(primaryStage);
		Scene scene = new Scene(erstellenView);
		addDialogeStage.setScene(scene);
		addDialogeStage.showAndWait();	
		return addDialogeStage;
	}
}
