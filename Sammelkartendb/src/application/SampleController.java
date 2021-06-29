package application;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Cards.Card;
import Cards.Monster;
import Cards.Spell;
import Cards.Trap;
import SuchenUndSortieren.Search.SearchAlgorithm;
import SuchenUndSortieren.Sorting.SortAlgorithm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
	
	
	//----Suchen----
	//Optionen die im drop down menu für den Karten Typ angezeigt werden
		ObservableList<String> cardTypeList = FXCollections.observableArrayList("Monster", "Zauber", "Falle");


		@FXML
		private VBox cardsVBox;
		@FXML
		private ComboBox sortAlgorithmComboBox;
		@FXML
		private ComboBox searchAlgorithmComboBox;
		private ObservableList<String> sortAlgorithms = FXCollections.observableArrayList("Mergesort", "Quicksort", "Selectionsort");
		private ObservableList<String> searchAlgorithms = FXCollections.observableArrayList("Binäre Suche", "Lineare Suche", "Exponential Suche");
		

		@FXML
		private Button cardInfoButton;
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
	    @FXML
	    private TextArea cardInfoEffect;
	    
	    @FXML
		private Button cardInfoButton2;
	    @FXML
	    private Label cardInfoName2;
	    @FXML
	    private Label cardInfoArt2;
	    @FXML
	    private Label cardInfoLvl2;
	    @FXML
	    private Label cardInfoAtk2;
	    @FXML
	    private Label cardInfoDef2;
	    @FXML
	    private Label cardInfoType2;
	    @FXML
	    private Label cardInfoTags2;
	    @FXML
	    private Label cardInfoElement2;
	    @FXML
	    private TextArea cardInfoEffect2;
	    @FXML
	    private ComboBox searchTextAttribute;
	    @FXML
	    private TextField searchText;
	    private ObservableList<String> attributes = FXCollections.observableArrayList("Name", "Kategorie", "Stufe", "Attacke", "Verteidigung");
	    
	    private HBox selectedCardHBox;
	    private int selectedCardId;

	    
	    private int cardInfoArea = 1;
		
		private Font labelFont = new Font("Arial", 18.0); 
		private Font labelSelectedFont = new Font("Arial Black", 22.0); 
		
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
			
			sortAlgorithmComboBox.setItems(sortAlgorithms);
			sortAlgorithmComboBox.setValue("Mergesort");
			searchAlgorithmComboBox.setItems(searchAlgorithms);
			searchAlgorithmComboBox.setValue("Binäre Suche");
			
			searchTextAttribute.setItems(attributes);
			searchTextAttribute.setValue("Name");
			
			initializeCardsVBox();
		}
		
		@FXML
		private void selectCardInfo1(ActionEvent event) {
			cardInfoButton.setStyle("-fx-background-color: gold");
			cardInfoButton2.setStyle("-fx-background-color: ");
			
			cardInfoArea = 1;
		}
		
		@FXML
		private void selectCardInfo2(ActionEvent event) {
			cardInfoButton2.setStyle("-fx-background-color: gold");
			cardInfoButton.setStyle("-fx-background-color: ");
			
			cardInfoArea = 2;
		}
		
		private void initializeCardsVBox() {
			//hole Karten aus Cardshandler
			CardsHandler cardsHandler = CardsHandler.get();
			List<Card> cards = cardsHandler.getCards();
			
			ObservableList<Node> cardsVBoxChildren = cardsVBox.getChildren();
			//Titel: Kartenname Attribut soll immer bestehen
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
			SortAttribute sortType = CardsHandler.get().getSortAttribute();
			switch (sortType) {
			//Schaue auf Sample.fxml: Buttons haben Userdata übergeben (Name=0 ....Tag=7) anhanddessen weiß man welcher Button angeklickt wurde und übergibt diesen Wert
				case NAME: return card.getName();
			//Gebe Eintrag der Kategorie (Monster, Spell, Trap) als Index wieder und subtrahiere -1 da der Index von 0-2 in diesem Fall ist 
				case CATEGORY: return cardTypeList.get(card.getKategory() - 1);
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
		//Übergebe Userdata der jeweiligen Buttons an Sorttype
		private void setSortType(ActionEvent event) {
			int value = getUserDataValue(event);
		    SortAttribute sortAttribute = SortAttribute.values()[value];
		    System.out.println(sortAttribute);
		    CardsHandler.get().setSortAttribute(sortAttribute);
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
		
		//Kartenauswahl 
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
				
				if(cardInfoArea == 1) {
					cardInfoName.setText(card.getName());
					cardInfoArt.setText(cardTypeList.get(card.getKategory() - 1));
					// Frage ab ob geklickte Karte eine Monsterkarte ist, wenn ja: gebe Wert der Atk. Andernfalls gebe "-" wieder
					cardInfoAtk.setText(card instanceof Monster ? String.valueOf(((Monster)card).getAtk()) : "-");
					cardInfoDef.setText(card instanceof Monster ? String.valueOf(((Monster)card).getDef()) : "-");
					cardInfoElement.setText(card instanceof Monster ? ((Monster)card).getAttribute() : "-");
					cardInfoLvl.setText(card instanceof Monster ? String.valueOf(((Monster)card).getLvl()) : "-");
					
					if (card instanceof Spell) { 
						Spell spell = (Spell)card;
						cardInfoTags.setText(String.join(", ", spell.getTags()));
						cardInfoType.setText(spell.getType());
						cardInfoEffect.setText(spell.getEffect());
					} else if (card instanceof Trap) { 
						Trap trap = (Trap)card;
						cardInfoTags.setText(String.join(", ", trap.getTags()));
						cardInfoType.setText(trap.getType());
						cardInfoEffect.setText(trap.getEffect());
					} else  {
						cardInfoTags.setText("-");
						cardInfoType.setText("-");
						cardInfoEffect.setText(null);
					}
				}
				else {
					cardInfoName2.setText(card.getName());
					cardInfoArt2.setText(cardTypeList.get(card.getKategory() - 1));
					// Frage ab ob geklickte Karte eine Monsterkarte ist, wenn ja: gebe Wert der Atk. Andernfalls gebe "-" wieder
					cardInfoAtk2.setText(card instanceof Monster ? String.valueOf(((Monster)card).getAtk()) : "-");
					cardInfoDef2.setText(card instanceof Monster ? String.valueOf(((Monster)card).getDef()) : "-");
					cardInfoElement2.setText(card instanceof Monster ? ((Monster)card).getAttribute() : "-");
					cardInfoLvl2.setText(card instanceof Monster ? String.valueOf(((Monster)card).getLvl()) : "-");
					
					if (card instanceof Spell) { 
						Spell spell = (Spell)card;
						cardInfoTags2.setText(String.join(", ", spell.getTags()));
						cardInfoType2.setText(spell.getType());
						cardInfoEffect2.setText(spell.getEffect());
					} else if (card instanceof Trap) { 
						Trap trap = (Trap)card;
						cardInfoTags2.setText(String.join(", ", trap.getTags()));
						cardInfoType2.setText(trap.getType());
						cardInfoEffect2.setText(trap.getEffect());
					} else  {
						cardInfoTags2.setText("-");
						cardInfoType2.setText("-");
						cardInfoEffect2.setText(null);
					}
				}
			}
		}
			
			
		@FXML
		// Speichere Sortierhalgorithmus in Cardshandler
		private void setSortAlgorithm(ActionEvent event) {
			int value = sortAlgorithms.indexOf(sortAlgorithmComboBox.getValue());
			SortAlgorithm sortAlgorithm = SortAlgorithm.values()[value];
			CardsHandler.get().setSortAlgorithm(sortAlgorithm);
			initializeCardsVBox();
		}
		
		@FXML
		// Speichere Suchalgorithmus in Cardshandler
		private void setSearchAlgorithm(ActionEvent event) {
			int value = sortAlgorithms.indexOf(sortAlgorithmComboBox.getValue()) + 1;
			SearchAlgorithm searchAlgorithm = SearchAlgorithm.values()[value];
			CardsHandler.get().setSearchAlgorithm(searchAlgorithm);
		}
		
		@FXML
		//Methode um aufsteigend oder absteigend (desc) zu sortieren 
		private void toggleSortDesc(ActionEvent event) {
			CheckBox checkBox = (CheckBox)event.getSource();
			boolean isAsc = !checkBox.isSelected();
			CardsHandler.get().setSortAsc(isAsc);
		}
		
		@FXML
		//Gebe gesuchte Karte in der Konsole wieder
		private void search(ActionEvent event) {
			String value = searchText.getText().toLowerCase();
			int attributeValue = attributes.indexOf(searchTextAttribute.getValue());
			SortAttribute attribute = SortAttribute.values()[attributeValue];
			Card card = CardsHandler.get().search(value, attribute);
			
			System.out.println( new GsonBuilder().setPrettyPrinting().create().toJson(card));
			
		}
}
