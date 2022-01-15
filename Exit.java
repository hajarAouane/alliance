package jeuDesDames;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Exit extends VBox{
	public Button RestoreGFromDB = new Button("Save");
	private Button exit = new Button("Exit");	
		
	HBox ro = new HBox(20);
	
	
	Label label1 = new Label("If you want to save the game Press \"Save\"");
	
	public Exit() {
		
		this.setPadding(new Insets(10));
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
		
		
		
		exit.setMaxSize(100, 60);

		RestoreGFromDB.getStylesheets().add(getClass().getResource("square.css").toString());
		RestoreGFromDB.getStyleClass().add("valider1");
	
		exit.setMaxSize(100, 60);
		
		exit.getStylesheets().add(getClass().getResource("square.css").toString());
		exit.getStyleClass().add("valider1");
		ro.setAlignment(Pos.CENTER);
		ro.getChildren().addAll(RestoreGFromDB,exit);
		this.getChildren().addAll(label1,ro);
		
		this.setStyle("-fx-background-color: BISQUE; -fx-effect: innershadow(gaussian, rgba(0,0,0,0.4), 75, 0.5, 0, 10);");
		
		
	}
	
	public Button getExit() {
		return exit;
		
	}
	
	
}

