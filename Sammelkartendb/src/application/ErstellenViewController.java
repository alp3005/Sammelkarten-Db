package application;

import java.io.FileNotFoundException;

import Cards.Monster;
import Cards.Spell;
import Cards.Trap;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
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
	
	ObservableList<String> monsterElementList = FXCollections.observableArrayList("Licht", "Finsternis", "Wind", "Feuer", "Wasser", "Erde");
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
	
	ObservableList<String> magicTypeList = FXCollections.observableArrayList("Normal", "Schnellzauber", "Permanentenzauber", "Ausrüstungszauber");
	@FXML
	private ComboBox magicTypeBox;
	@FXML
	private TextArea magicEffectBox;
	
	@FXML
	private FlowPane CheckMagic;
	@FXML
	private CheckBox magicTagZer;
	@FXML
	private CheckBox magicTagBes;
	@FXML
	private CheckBox magicTagEin;
	@FXML
	private CheckBox magicTagPos;
	@FXML
	private CheckBox magicTagSuc;

	//Fallen stuff
	@FXML
	private GridPane trapGrid;
	
	ObservableList<String> trapTypeList = FXCollections.observableArrayList("Normal", "Konterfalle", "Permanentenfalle");
	@FXML
	private ComboBox trapTypeBox;
	@FXML
	private TextArea trapEffectBox;
    @FXML
    private FlowPane CheckTrap;
	@FXML
	private CheckBox trapTagZer;
	@FXML
	private CheckBox trapTagBes;
	@FXML
	private CheckBox trapTagEin;
	@FXML
	private CheckBox trapTagPos;
	@FXML
	private CheckBox trapTagSuc;

	ObservableList<String> magicTrapTagList = FXCollections.observableArrayList("zerstören", "beschwören", "einschränken", "Position ändern", "suchen", "anderes");

	
	@FXML
	private void initialize() {
		typeBox.setItems(cardTypeList);
		typeBox.setValue("Monster");
		
		//Monster stuff
		elementBox.setItems(monsterElementList);
		elementBox.setValue("Licht");
		levelBox.setItems(monsterLevelList);
		levelBox.setValue("1");
		
		//Zauber stuff
		magicTypeBox.setItems(magicTypeList);
		magicTypeBox.setValue("Normal");
		
		//Fallen stuff
		trapTypeBox.setItems(trapTypeList);
		trapTypeBox.setValue("Normal");
		
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
	
	@FXML
	private void createCard(ActionEvent event) {
		try {
			String cardType = (String)typeBox.getValue();
			switch (cardType) {
				case "Monster":
					createMonsterCard();
					break;
				case "Zauber":
					createSpellCard();
					break;
				case "Falle":
					createTrapCard();
					break;
			}
			Platform.exit();
		} catch(Exception ex) {
			// Maybe revert the highest id in CardsHandler
			ex.printStackTrace();
		}
		
	}
	
	private void createMonsterCard() throws FileNotFoundException {
		CardsHandler cardsHandler = CardsHandler.get();
		String name = nameField.getText();
		int id = cardsHandler.getNextId();
		//Statt den Typ Element, kann man ebenso den Wert an der Stelle des Arrays wiedergeben
		//int element = monsterElementList.indexOf(elementBox.getValue());
		String element = (String) elementBox.getValue();
		int level = Integer.parseInt((String)levelBox.getValue());
		int attack = Integer.parseInt(attackField.getText());
		int defense = Integer.parseInt(defenseField.getText());
		
		Monster monster = new Monster(name, id, element, level, attack, defense);
		cardsHandler.addMonster(monster);
	}
	
	private void createSpellCard() throws FileNotFoundException {
		CardsHandler cardsHandler = CardsHandler.get();
		String name = nameField.getText();
		int id = cardsHandler.getNextId();
		String type = (String) magicTypeBox.getValue();
 		String effect = magicEffectBox.getText();
 		//Buggt
 		//String tag =  (String) CheckMagic.getValue();
		Spell spell = new Spell(name, id, type, effect, tag);
		cardsHandler.addSpell(spell);
	}
	
	private void createTrapCard() throws FileNotFoundException {
		CardsHandler cardsHandler = CardsHandler.get();
		String name = nameField.getText();
		int id = cardsHandler.getNextId();
		//int type = trapTypeList.indexOf(trapTypeBox.getValue());
		String type = (String) trapTypeBox.getValue();
		String effect = trapEffectBox.getText();
		//Buggt
		//String tag = (String) CheckTrap.getValue();
		Trap trap = new Trap(name, id, type, effect, tag);
		cardsHandler.addTrap(trap);
	}
}
