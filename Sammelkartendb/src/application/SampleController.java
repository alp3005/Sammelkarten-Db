package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SampleController {
	
	@FXML
	private void addBtn() throws IOException {
		Main.showAddStage();
	}
	
	
	//----Suchen----
	//Optionen die im drop down menu für den Karten Typ angezeigt werden
		ObservableList<String> cardTypeListSearch = FXCollections.observableArrayList("Monster", "Zauber", "Falle");

		@FXML
		private TextField nameFieldSearch;
		@FXML
		private ComboBox typeBoxSearch;
		
		//Monster Stuff
		@FXML
		private GridPane monsterGridSearch;
		
		ObservableList<String> monsterElementListSearch = FXCollections.observableArrayList("Licht", "Finsternis", "Wind", "Feuer", "Wasser", "Erde");
		ObservableList<String> monsterLevelListSearch = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
		@FXML
		private ComboBox elementBoxSearch;
		@FXML
		private ComboBox levelBoxSearch;
		@FXML
		private TextField attackFieldSearch;
		@FXML
		private TextField defenseFieldSearch;
		
		//Zauber Stuff
		@FXML
		private GridPane magicGridSearch;
		
		ObservableList<String> magicTypeListSearch = FXCollections.observableArrayList("Normal", "Schnellzauber", "Permanentenzauber", "Ausrüstungszauber");
		@FXML
		private ComboBox magicTypeBoxSearch;
		@FXML
		private ComboBox magicTagBoxSearch;

		//Fallen stuff
		@FXML
		private GridPane trapGridSearch;
		
		ObservableList<String> trapTypeListSearch = FXCollections.observableArrayList("Normal", "Konterfalle", "Permanentenfalle");
		@FXML
		private ComboBox trapTypeBoxSearch;
		@FXML
		private ComboBox trapTagBoxSearch;
		
		ObservableList<String> magicTrapTagListSearch = FXCollections.observableArrayList("zerstören", "beschwören", "einschränken", "Position ändern", "suchen", "anderes");

		
		@FXML
		private void initialize() {
			// TODO - SO LADET IHR ALLE KARTEN //
			// List<Card> cards = CardsHandler.get().getCards();
			/////////////////////////////////////
			
			
			typeBoxSearch.setItems(cardTypeListSearch);
			typeBoxSearch.setValue("Monster");
			
			//Monster stuff
			elementBoxSearch.setItems(monsterElementListSearch);
			elementBoxSearch.setValue("Licht");
			levelBoxSearch.setItems(monsterLevelListSearch);
			levelBoxSearch.setValue("1");
			
			//Zauber stuff
			magicTypeBoxSearch.setItems(magicTypeListSearch);
			magicTypeBoxSearch.setValue("Normal");
			magicTagBoxSearch.setItems(magicTrapTagListSearch);
			magicTagBoxSearch.setValue("anderes");
			
			//Fallen stuff
			trapTypeBoxSearch.setItems(trapTypeListSearch);
			trapTypeBoxSearch.setValue("Normal");
			trapTagBoxSearch.setItems(magicTrapTagListSearch);
			trapTagBoxSearch.setValue("anderes");
			
			//entferne andere stuff
			magicGridSearch.setManaged(false);
			magicGridSearch.setVisible(false);
			trapGridSearch.setManaged(false);
			trapGridSearch.setVisible(false);
		}
		
		@FXML
		private void selectCardType(ActionEvent event) {
			String cardTypeSearch = typeBoxSearch.getSelectionModel().getSelectedItem().toString();
			//System.out.println(cardType);
			
			if(cardTypeSearch.equals("Monster")) {
				monsterGridSearch.setManaged(true);
				monsterGridSearch.setVisible(true);
				//entferne andere stuff
				magicGridSearch.setManaged(false);
				magicGridSearch.setVisible(false);
				trapGridSearch.setManaged(false);
				trapGridSearch.setVisible(false);
			}
			else if(cardTypeSearch.equals("Zauber")) {
				magicGridSearch.setManaged(true);
				magicGridSearch.setVisible(true);
				//entferne andere stuff
				monsterGridSearch.setManaged(false);
				monsterGridSearch.setVisible(false);
				trapGridSearch.setManaged(false);
				trapGridSearch.setVisible(false);
			}
			else if(cardTypeSearch.equals("Falle")) {
				trapGridSearch.setManaged(true);
				trapGridSearch.setVisible(true);
				//entferne andere stuff
				monsterGridSearch.setManaged(false);
				monsterGridSearch.setVisible(false);
				magicGridSearch.setManaged(false);
				magicGridSearch.setVisible(false);
			}
		}
	//---Suchen ende---
}
