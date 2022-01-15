package jeuDesDames;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Alliance extends VBox{
	
	private Button valider1 = new Button("Play");	
	TextField player2 = new TextField();
	TextField player1 = new TextField();		
	HBox ro = new HBox(20);
	
	VBox roo = new VBox(6);
	VBox roo1 = new VBox(6);		
	Label label1 = new Label("Player 1");
	//ImageView start = new ImageView(getClass().getResource("startGame.png").toString());
	Label startG = new Label("Alliance");
	
	public Alliance() {
		
		this.setPadding(new Insets(10));
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
		

		valider1.setMaxSize(100, 60);

		valider1.getStylesheets().add(getClass().getResource("square.css").toString());
		valider1.getStyleClass().add("valider1");
		player2.setPrefColumnCount(20);
		
		player1.setPrefColumnCount(20);
		player1.setPromptText("entez votre nom");
		player2.setPromptText("entez votre nom");
		roo.getChildren().addAll(label1,player1);
	
		valider1.setMaxSize(100, 60);
		startG.setFont(Font.font("Arial"));
		//startG.setGraphic(start);
		//startG.setContentDisplay(ContentDisplay.BOTTOM);;
		valider1.getStylesheets().add(getClass().getResource("square.css").toString());
		valider1.getStyleClass().add("valider1");
		
		roo1.getChildren().addAll(new Label("Player 2"),player2);
		ro.getChildren().addAll(roo,roo1);
		this.getChildren().addAll(ro,valider1);
		
		this.setStyle("-fx-background-color: BISQUE; -fx-effect: innershadow(gaussian, rgba(0,0,0,0.4), 75, 0.5, 0, 10);");
		
		
	}
	
	
	public Button getValider() {
		return valider1;
		
	}

}
