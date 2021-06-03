package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class ErstellenViewController {
	
	//Optionen die im drop down menu für den Karten Typ angezeigt werden
	ObservableList<String> cardTypeList = FXCollections.observableArrayList("Monster", "Zauber", "Falle");

	@FXML
	private TextField nameField;
	@FXML
	private ComboBox typeBox;
	
	//Monster Stuff
	@FXML
	private GridPane monsterGrid;
	
	ObservableList<String> monsterElementList = FXCollections.observableArrayList("Licht", "Finsternis", "Wind", "Feuer", "Wasser");
	ObservableList<String> monsterLevelList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
	@FXML
	private ComboBox elementBox;
	@FXML
	private ComboBox levelBox;
	@FXML
	private TextField attackField;
	@FXML
	private TextField defenseField;
	
	//Zauber Stuff
	@FXML
	private GridPane magicGrid;

	//Fallen stuff
	@FXML
	private GridPane trapGrid;
	
	@FXML
	private void initialize() {
		typeBox.setItems(cardTypeList);
		typeBox.setValue("Monster");
		
		//Monster stuff
		elementBox.setItems(monsterElementList);
		elementBox.setValue("Licht");
		levelBox.setItems(monsterLevelList);
		levelBox.setValue("1");
		
		//entferne andere stuff
		magicGrid.setManaged(false);
		magicGrid.setVisible(false);
		trapGrid.setManaged(false);
		trapGrid.setVisible(false);
	}
	
	@FXML
	private void selectCardType(ActionEvent event) {
		String cardType = typeBox.getSelectionModel().getSelectedItem().toString();
		//System.out.println(cardType);
		
		if(cardType.equals("Monster")) {
			monsterGrid.setManaged(true);
			monsterGrid.setVisible(true);
			//entferne andere stuff
			magicGrid.setManaged(false);
			magicGrid.setVisible(false);
			trapGrid.setManaged(false);
			trapGrid.setVisible(false);
		}
		else if(cardType.equals("Zauber")) {
			magicGrid.setManaged(true);
			magicGrid.setVisible(true);
			//entferne andere stuff
			monsterGrid.setManaged(false);
			monsterGrid.setVisible(false);
			trapGrid.setManaged(false);
			trapGrid.setVisible(false);
		}
		else if(cardType.equals("Falle")) {
			trapGrid.setManaged(true);
			trapGrid.setVisible(true);
			//entferne andere stuff
			monsterGrid.setManaged(false);
			monsterGrid.setVisible(false);
			magicGrid.setManaged(false);
			magicGrid.setVisible(false);
		}
	}
}
