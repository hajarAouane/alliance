package jeuDesDames;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Tableau extends GridPane{
	
	private Label players = new Label("Players");
	private Label score = new Label("Score");
	private Label play1 = new Label("play1");
	private Label play2 = new Label("play2");
	private Label score1 = new Label("score1");
	private Label score2 = new Label("score2");
	public Tableau() {
		
		
		this.setGridLinesVisible(true);
		this.setPadding(new Insets(15));
		this.setHgap(5);
		
		for (int i = 0; i < 2; i++) {
	         ColumnConstraints column = new ColumnConstraints();
	         column.setHgrow(Priority.ALWAYS);
	         this.getColumnConstraints().add(column);
	        
	     }
		for (int i = 0; i < 3; i++) {
	         RowConstraints Row = new RowConstraints();
	         //etierement
	         Row.setVgrow(Priority.ALWAYS);
	         this.getRowConstraints().add(Row);
	         
	     }
		players.setFont(Font.font(getAccessibleHelp(), FontWeight.BOLD, 20));
		players.setTextFill(Color.valueOf("#582"));
		score.setFont(Font.font(getAccessibleHelp(), FontWeight.BOLD, 20));
		score.setTextFill(Color.valueOf("#582"));
		
		
		this.add(players, 0, 0, 1, 1);
		players.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.add(score, 1, 0, 1, 1);
		score.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.add(play1, 0, 1, 1, 1);
		play1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.add(play2, 0, 2, 1, 1);
		play2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.add(score1, 1, 1, 1, 1);
		score1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.add(score2, 1, 2, 1, 1);
		score2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		
	}
	
	public Label getPlay1() {
		return this.play1;
	}
	public Label getPlay2() {
		return this.play2;
	}
	public Label getScore1() {
		return this.score1;
	}
	public Label getScore2() {
		return this.score2;
	}
}
