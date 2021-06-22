package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SampleController {
	
	@FXML
	private void addBtn() throws IOException {
		Main.showAddStage();
	}
	
	
	/*@FXML
	private void initialize1() {
		
		//---Suchen---
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
		//---Suchen ende---
	}*/
	
	//---Kartenfeld 1---//
	@FXML
	private Label nameFieldKF1;
	@FXML
	private Label typeBoxKF1;
	
	//Monster Stuff
	@FXML
	private GridPane monsterGridKF1;
	
	@FXML
	private Label elementKF1;
	@FXML
	private Label levelBoxKF1;
	@FXML
	private Label attackFieldKF1;
	@FXML
	private Label defenseFieldKF1;
	
	//Zauber Stuff
	@FXML
	private GridPane magicGridKF1;
	
	@FXML
	private Label magicTypeBoxKF1;
	@FXML
	private Label magicTagBoxKF1;

	//Fallen stuff
	@FXML
	private GridPane trapGridKF1;
	
	@FXML
	private Label trapTypeBoxKF1;
	@FXML
	private Label trapTagBoxKF1;
	
	//---Kartenfeld 1 ende---
	
	//---Kartenfeld 2---//
		@FXML
		private Label nameFieldKF2;
		@FXML
		private Label typeBoxKF2;
		
		//Monster Stuff
		@FXML
		private GridPane monsterGridKF2;
		
		@FXML
		private Label elementKF2;
		@FXML
		private Label levelBoxKF2;
		@FXML
		private Label attackFieldKF2;
		@FXML
		private Label defenseFieldKF2;
		
		//Zauber Stuff
		@FXML
		private GridPane magicGridKF2;
		
		@FXML
		private Label magicTypeBoxKF2;
		@FXML
		private Label magicTagBoxKF2;

		//Fallen stuff
		@FXML
		private GridPane trapGridKF2;
		
		@FXML
		private Label trapTypeBoxKF2;
		@FXML
		private Label trapTagBoxKF2;
		
		//---Kartenfeld 1 ende---
	
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
		@FXML
		private TextArea magicEffectSearch;
		
		ObservableList<String> magicTypeListSearch = FXCollections.observableArrayList("Normal", "Schnellzauber", "Permanentenzauber", "Ausrüstungszauber");
		@FXML
		private ComboBox magicTypeBoxSearch;
		@FXML
		private CheckBox magicTagZerSearch;
		@FXML
		private CheckBox magicTagBesSearch;
		@FXML
		private CheckBox magicTagEinSearch;
		@FXML
		private CheckBox magicTagPosSearch;
		@FXML
		private CheckBox magicTagSucSearch;
		
		//Fallen stuff
		@FXML
		private GridPane trapGridSearch;
		@FXML
		private TextArea trapEffectSearch;
		
		ObservableList<String> trapTypeListSearch = FXCollections.observableArrayList("Normal", "Konterfalle", "Permanentenfalle");
		@FXML
		private ComboBox trapTypeBoxSearch;
		@FXML
		private CheckBox trapTagZerSearch;
		@FXML
		private CheckBox trapTagBesSearch;
		@FXML
		private CheckBox trapTagEinSearch;
		@FXML
		private CheckBox trapTagPosSearch;
		@FXML
		private CheckBox trapTagSucSearch;
		
		ObservableList<String> magicTrapTagListSearch = FXCollections.observableArrayList("zerstören", "beschwören", "einschränken", "Position ändern", "suchen", "anderes");
//<<<<<<< Updated upstream

		
		@FXML
		private void initialize() {
			// TODO - SO LADET IHR ALLE KARTEN //
			// List<Card> cards = CardsHandler.get().getCards();
			/////////////////////////////////////
			
			//--KartenFeld 1 start--
//			monsterGridKF1.setManaged(false);
			//monsterGridKF1.setVisible(false);
			
//			magicGridKF1.setManaged(false);
//			magicGridKF1.setVisible(false);
			
//			trapGridKF1.setManaged(false);
//			trapGridKF1.setVisible(false);
			//--KartenFeld 1 ende--
			
			//--KartenFeld 2 start--
//			monsterGridKF2.setManaged(false);
//			monsterGridKF2.setVisible(false);
			
//			magicGridKF2.setManaged(false);
//			magicGridKF2.setVisible(false);
			
//			trapGridKF2.setManaged(false);
//			trapGridKF2.setVisible(false);
			//--KartenFeld 2 ende--
			
			//--Suche start--
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
			
			//Fallen stuff
			trapTypeBoxSearch.setItems(trapTypeListSearch);
			trapTypeBoxSearch.setValue("Normal");
			
			//entferne andere stuff
			magicGridSearch.setManaged(false);
			magicGridSearch.setVisible(false);
			trapGridSearch.setManaged(false);
			trapGridSearch.setVisible(false);
			//--Suche ende--
		}
//=======
//>>>>>>> Stashed changes
		
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
