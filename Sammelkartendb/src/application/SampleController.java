package application;

import java.io.IOException;

import javafx.fxml.FXML;

public class SampleController {
	
	@FXML
	private void addBtn() throws IOException {
		Main.showAddStage();
	}
	
}
