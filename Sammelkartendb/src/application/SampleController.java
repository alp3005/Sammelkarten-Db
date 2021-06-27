package application;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import Cards.Card;
import Cards.Monster;
import Cards.Spell;
import Cards.Trap;
import SuchenUndSortieren.SortierenMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SampleController {
	
	@FXML
	private void addBtn() throws IOException {
		Stage stage = Main.showAddStage();
		initializeCardsVBox();
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
		private VBox cardsVBox;
		@FXML
		private ComboBox sortAlgorithmComboBox;
		@FXML
		private ComboBox searchAlgorithmComboBox;
		private ObservableList<String> sortAlgorithms = FXCollections.observableArrayList("Mergesort", "Quicksort", "Selectionsort", "Heapsort");
		private ObservableList<String> searchAlgorithms = FXCollections.observableArrayList("Binäre Suche", "Fibonacci Suche", "Exponential Suche", "Interpolationssuche");
		

	    @FXML
	    private Label cardInfoName;
	    @FXML
	    private Label cardInfoArt;
	    @FXML
	    private Label cardInfoLvl;
	    @FXML
	    private Label cardInfoAtk;
	    @FXML
	    private Label cardInfoDef;
	    @FXML
	    private Label cardInfoType;
	    @FXML
	    private Label cardInfoTags;
	    @FXML
	    private Label cardInfoElement;
	    private HBox selectedCardHBox;
	    private int selectedCardId;
		
		
		private Font labelFont = new Font("Arial", 22.0); 
		private Font labelSelectedFont = new Font("Arial Black", 22.0); 
		private SortType sortType;
		
		@FXML
		private void initialize() {
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
			
			sortAlgorithmComboBox.setItems(sortAlgorithms);
			searchAlgorithmComboBox.setItems(searchAlgorithms);
			
			sortType = sortType.CATEGORY;
			initializeCardsVBox();
		}
		
		private void initializeCardsVBox() {
			//hole Karten aus Cardshandler
			CardsHandler cardsHandler = CardsHandler.get();
			List<Card> cards = cardsHandler.getCards();
			
			ObservableList<Node> cardsVBoxChildren = cardsVBox.getChildren();
			//bleibt immer
			HBox titleNode = (HBox)cardsVBoxChildren.get(0);
			
			//Lösche Inhalt damit beim Buttonpress die alte Liste an Karten gelöscht ist außer Title
			cardsVBoxChildren.clear();
			selectedCardHBox = null;

			cardsVBoxChildren.add(titleNode);
			//erstelle für jede Karte einen neuen Node
			for (Card card : cards) {
				Node cardNode = createNodeForCard(card);
				cardsVBoxChildren.add(cardNode);
			}
		}
		
		private Node createNodeForCard(Card card) {
			HBox hBox = new HBox();
			hBox.setPrefHeight(39.0);
			hBox.setPrefWidth(569.0);
			hBox.setSpacing(5.0);
			hBox.setPadding(new Insets(5, 5, 5, 5));
			hBox.setOnMouseClicked(new selectCardEventHandler());
			
			//erstelle unsichtbare IdLabel, um beim Klicken der jeweiligen Labels der Kartenliste auf die Id zuzugreifen.
			Label idLabel = new Label();
			idLabel.setVisible(false);
			idLabel.setPrefWidth(0);
			idLabel.setText(String.valueOf(card.getId()));
			
			Label nameLabel = new Label();
			nameLabel.setPrefHeight(29.0);
			nameLabel.setPrefWidth(350.0);
			//nameLabel.setPadding(new Insets(0, 50.0, 0, 0));
			nameLabel.setText(card.getName());
			nameLabel.setFont(labelFont);

			Label attributeLabel = new Label();
			attributeLabel.setPrefHeight(29.0);
			attributeLabel.setPrefWidth(220.0);
			attributeLabel.setText(getAttributeLabelText(card));
			attributeLabel.setFont(labelFont);
			
			ObservableList<Node> hBoxChildren = hBox.getChildren();
			//packe Name und Attribute in Hbox 
			hBoxChildren.add(idLabel);
			hBoxChildren.add(nameLabel);
			hBoxChildren.add(attributeLabel);
			
			// Abfrage: Wenn selektierte Karte angeklickt wurde, färbe die neu erstellte Hbox
			if (card.getId() == selectedCardId) {
				setNewSelectedHBox(hBox);
			}
			
			return hBox;
		}
		
		private String getAttributeLabelText(Card card) {
			switch (sortType) {
			//Schaue auf Sample.fxml: Buttons haben Userdata übergeben (Name=0 ....Tag=7) anhanddessen weiß man welcher Button angeklickt wurde und übergibt diesen Wert
				case NAME: return card.getName();
			//Gebe Eintrag der Kategorie (Monster, Spell, Trap) als Index wieder und subtrahiere -1 da der Index von 0-2 in diesem Fall ist 
				case CATEGORY: return cardTypeListSearch.get(card.getKategory() - 1);
				case LEVEL: 
					if (card instanceof Monster) return String.valueOf(((Monster)card).getLvl());
					return "";
				case ATTACK:
					if (card instanceof Monster) return String.valueOf(((Monster)card).getAtk());
					return "";
				case DEFENSE:
					if (card instanceof Monster) return String.valueOf(((Monster)card).getDef());
					return "";
				case TYPE: 
					if (card instanceof Spell) return ((Spell)card).getType();
					if (card instanceof Trap) return ((Trap)card).getType();
					return "";
				default: return "";
			}
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
		
		@FXML 
		//Übergebe Userdata der jeweiligen Buttons an Sorttype
		private void setSortType(ActionEvent event) {
			int value = getUserDataValue(event);
		    sortType = SortType.values()[value];
		    System.out.println(sortType);
		    CardsHandler.get().setSortAttribute(sortType);
		//Erstelle neue Kartenliste nach Klicken eines Buttons, da sonst die alte Kartenliste noch vorhanden wäre
		    initializeCardsVBox();
		}
		
		private int getUserDataValue(ActionEvent event) {
			//Bekomme den Wert des Userdatas des jeweiligen Buttons
			Node button = (Node) event.getSource() ;
		    String data = (String) button.getUserData();
		    //Übertrage von String to Integer
		    return Integer.parseInt(data);
		}
		
		private void setNewSelectedHBox(HBox hBox) {
			if (selectedCardHBox != null) {
				// setLabelsFont(selectedCardHBox.getChildren(), labelFont);
				selectedCardHBox.setStyle("-fx-background-color: 'transparent'");
			}
			
			selectedCardHBox = hBox;
			selectedCardId = Integer.parseInt(((Label)hBox.getChildren().get(0)).getText());
			hBox.setStyle("-fx-background-color: 'powderblue'");
		}
		
		private class selectCardEventHandler implements EventHandler<MouseEvent> {

			@Override
			public void handle(MouseEvent event) {
				HBox hBox = (HBox)event.getSource();
				setNewSelectedHBox(hBox);
				Label idLabel = (Label)hBox.getChildren().get(0); 
				int id = Integer.parseInt(idLabel.getText());
				Card card = CardsHandler.get().getCardById(id);
				
				cardInfoName.setText(card.getName());
				cardInfoArt.setText(cardTypeListSearch.get(card.getKategory() - 1));
				// Frage ab ob geklickte Karte eine Monsterkarte ist, wenn ja: gebe Wert der Atk. Andernfalls gebe "-" wieder
				cardInfoAtk.setText(card instanceof Monster ? String.valueOf(((Monster)card).getAtk()) : "-");
				cardInfoDef.setText(card instanceof Monster ? String.valueOf(((Monster)card).getDef()) : "-");
				cardInfoElement.setText(card instanceof Monster ? ((Monster)card).getAttribute() : "-");
				cardInfoLvl.setText(card instanceof Monster ? String.valueOf(((Monster)card).getLvl()) : "-");
				
				if (card instanceof Spell) { 
					Spell spell = (Spell)card;
					cardInfoTags.setText(String.join(", ", spell.getTags()));
					cardInfoType.setText(spell.getType());
				} else if (card instanceof Trap) { 
					Trap trap = (Trap)card;
					cardInfoTags.setText(String.join(", ", trap.getTags()));
					cardInfoType.setText(trap.getType());
				} else  {
					cardInfoTags.setText("-");
					cardInfoType.setText("-");
				}
			}
		}
			
			
		@FXML
		private void setSortAlgorithm(ActionEvent event) {
			int sortAlgorithm = sortAlgorithms.indexOf(sortAlgorithmComboBox.getValue()) + 1;
			CardsHandler.get().setSortAlgorithm(sortAlgorithm);
		}
		
		@FXML
		private void setSearchAlgorithm(ActionEvent event) {
			// int sortAlgorithm = sortAlgorithms.indexOf(sortAlgorithmComboBox.getValue()) + 1;
			// CardsHandler.get().setSortAlgorithm(sortAlgorithm);
		}
}
