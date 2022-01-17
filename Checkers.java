package jeuDesDames;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Checkers extends Application {

	Tableau tab = new Tableau();
	public Pane pane;
	public int posCurrentPLayer;
	public static int pla = 0;
	private int startTime = 1000;
	private Integer seconds = startTime;
	private Integer secondsB = startTime;
	CheckersBoard board;
	private Exit rooot = new Exit();

	public String namePlayer1;
	public String namePlayer2;
	///////////////////

	private HBox btn = new HBox(10);
	private HBox joueur1 = new HBox(160);
	private HBox joueur2 = new HBox(160);
	private HBox boar = new HBox(10);
	private VBox root = new VBox(10);
	private HBox jou1 = new HBox(10);
	private HBox jou2 = new HBox(10);
	//////////////////

	private Button score = new Button("score");
	private Button newGameButton;
	private Button resignButton;
	private Button saveGameButton;
	private Label message;
	public static Label movementsMsgs;
	public int currentPLayer;
	Stage stage;
	int timeoverA = 0;
	int timeoverB = 0;
	Button Changer = new Button("Changer");
	static Boolean changer;
	Button restore = new Button("restore");

	Timeline time = new Timeline();
	Label timeB = new Label("16:40");
	Label timeW = new Label("16:40");

	Label Player1 = new Label("Player 2");
	Label Player2 = new Label("Player 1");
	Label Play1 = new Label();
	Label Play2 = new Label();
	AudioClip sound1 = new AudioClip(getClass().getResource("sound1.mp3").toString());
	AudioClip gameover = new AudioClip(getClass().getResource("gameover.mp3").toString());

	Button chang = new Button("Flip Piece");
	Alliance root1 = new Alliance();
	String hello;

	public void start(Stage stage) {
		pane = new Pane();
		// writeObject();
		this.stage = stage;
		ImageView start = new ImageView(getClass().getResource("start.png").toString());

		message = new Label("Click \"New Game\" to begin.");

		message.setTextFill(Color.BLACK); // Light green.
		message.setFont(Font.font(null, FontWeight.BOLD, 14));

		newGameButton = new Button();
		resignButton = new Button("Resign");
		saveGameButton = new Button("Help");
		board = new CheckersBoard();
		board.DrowBoard();
		chang.setOnAction(e -> board.resetPlace());
		newGameButton.setOnAction(e -> board.doNewGame());
		resignButton.setOnAction(e -> board.doResign());
		// saveGameButton.setOnAction(e -> board.saveGame());
		board.setOnMousePressed(e -> board.mousePressed(e));
		// Changer.setOnAction(null);
		// board.chang.relocate(20, 300);

		// board.relocate(20,80);
		newGameButton.relocate(90, 10);
		// Changer.relocate(370, 440);
		saveGameButton.relocate(250, 10);
		score.relocate(330, 10);
		resignButton.relocate(10, 10);
		restore.relocate(170, 10);
		score.setManaged(false);
		score.resize(70, 30);

		message.relocate(20, 450);
		restore.setOnAction(e -> {

			board.restoreAndShow();

		});
		rooot.RestoreGFromDB.setOnAction(e -> {
			board.PrintPPositions();
			Platform.exit();
		});
		// timeB.relocate(300, 50);
		// timeW.relocate(300, 420);
		resignButton.setManaged(false);
		resignButton.resize(70, 30);
		newGameButton.setManaged(false);
		newGameButton.resize(70, 30);
		saveGameButton.setManaged(false);
		saveGameButton.resize(70, 30);
		restore.setManaged(false);
		restore.resize(70, 30);

		jou1.getChildren().addAll(Player2, Play1);
		jou2.getChildren().addAll(Player1, Play2);
		joueur1.getChildren().addAll(jou1, timeB);
		joueur2.getChildren().addAll(jou2, timeW);
		boar.getChildren().addAll(board, chang);
		boar.setAlignment(Pos.CENTER_LEFT);
		pane.getChildren().addAll(resignButton, newGameButton, restore, saveGameButton, score);

		root.getChildren().addAll(joueur1, boar, joueur2, message, pane);
		root.setAlignment(Pos.CENTER_LEFT);
		// restore.setManaged(false);

		root.setPadding(new Insets(40));
		pane.setPadding(new Insets(10));

		chang.getStylesheets().add(getClass().getResource("anim.css").toString());
		chang.getStyleClass().add("start");

		restore.getStylesheets().add(getClass().getResource("anim.css").toString());
		restore.getStyleClass().add("start");

		score.getStylesheets().add(getClass().getResource("anim.css").toString());
		score.getStyleClass().add("start");

		newGameButton.getStylesheets().add(getClass().getResource("anim.css").toString());
		newGameButton.getStyleClass().add("start");

		saveGameButton.getStylesheets().add(getClass().getResource("anim.css").toString());
		saveGameButton.getStyleClass().add("start");

		resignButton.getStylesheets().add(getClass().getResource("anim.css").toString());
		resignButton.getStyleClass().add("start");

		Player1.setFont(Font.font("NewSerif", FontWeight.EXTRA_BOLD, 14));
		Player2.setFont(Font.font("NewSerif", FontWeight.EXTRA_BOLD, 14));
		Player2.relocate(20, 50);
		Play2.relocate(80, 420);
		// Player1.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-opacity:
		// 1.0;");
		Player1.relocate(20, 420);
		Play1.relocate(80, 50);

		Player1.setTextFill(Color.valueOf("#B8860B"));
		Player2.setTextFill(Color.valueOf("#B8860B"));

		Play1.textProperty().bind(root1.player1.textProperty());

		Play1.setTextFill(Color.BLACK);
		Play2.setTextFill(Color.BLACK);
		Play1.setFont(Font.font("NewSerif", FontWeight.BOLD, FontPosture.ITALIC, 12));
		Play2.setFont(Font.font("NewSerif", FontWeight.BOLD, FontPosture.ITALIC, 12));

		Play2.textProperty().bind(root1.player2.textProperty());

		Stage scor = new Stage();
		Scene Score = new Scene(tab, 400, 200);

		Scene scene1 = new Scene(root1);
		Scene exit1 = new Scene(rooot, 300, 100);
		rooot.getExit().setOnAction(e -> {
			Platform.exit();
		});
		newGameButton.setGraphic(start);

		Stage stage2 = new Stage();
		stage2.setTitle("About Alliance");
		BorderPane aide = new BorderPane();
		Label aid = new Label();
		aide.setCenter(aid);
		root.setStyle(
				"-fx-background-color: BISQUE; -fx-effect: innershadow(gaussian, rgba(0,0,0,0.4), 75, 0.5, 0, 10);");
		root.setPadding(new Insets(20));
		Scene scene3 = new Scene(aide);
		ImageView iv = new ImageView(getClass().getResource("aide.png").toString());
		iv.setFitWidth(500);
		iv.setFitHeight(500);

		aid.setGraphic(iv);
		Stage stag3 = new Stage();

		stage2.setScene(scene3);
		stage.setOnCloseRequest(e -> {
			stag3.setScene(exit1);
			stag3.show();
		});
		saveGameButton.setOnAction(e -> {
			stage2.show();
		});
		stage2.setResizable(false);

		Scene scene = new Scene(root, 500, 550);
		stage.setScene(scene1);

		root1.getValider().setOnAction(e -> {
			stage.centerOnScreen();
			stage.setScene(scene);
			this.namePlayer1 = Play1.getText();
			this.namePlayer2 = Play2.getText();
			System.out.println(namePlayer1 + "\n");
			System.out.println(namePlayer2 + "\n");

		});
		// score table--------------------------------------------

		score.setOnAction(e -> {
			scor.setScene(Score);
			scor.setTitle("Score");
			this.tab.getPlay1().setText(this.board.score()[0]);
			this.tab.getPlay2().setText(this.board.score()[2]);
			this.tab.getScore1().setText(this.board.score()[1]);
			this.tab.getScore2().setText(this.board.score()[3]);
			scor.show();
		});

		// --------------------------------------------------------

//			hello = root1.player1.getText();
//			System.out.println(hello);

		stage.setResizable(false);
		stage.setTitle("Alliance");
		stage.show();
		sound1.play();

	}

	// -------------------- Classes -------------------------------

	private static class CheckersMove {
		int fromRow, fromCol; // Position of piece to be moved.
		int toRow, toCol; // Square to move to.

		CheckersMove(int r1, int c1, int r2, int c2) {
			fromRow = r1;
			fromCol = c1;
			toRow = r2;
			toCol = c2;
		}

		boolean isJump() {

			return (fromRow - toRow == 2 || fromRow - toRow == -2);
		}
	}

	public class CheckersBoard extends Canvas {

		public int newSelectedRow, newSelectedCol;
		public String Information[] = new String[4]; // new
		CheckersData board;
		boolean gameInProgress;
		public int currentPlayer;
		public int selectedRow, selectedCol;
		int noir = 10;
		int blanc = 9;
		public int movedToRow;
		public int movedToCol;
		public String nameP1ToDB; // Store name toDB
		public String nameP2ToDB; // Store name toDB
		public String[] ScoreDB = new String[30]; // load and upload data // new
		public int indexPosi;
		public String winner; // new
		public String[] ScoreToDB = new String[30]; // load and upload data

		public void time() {
			Timeline time = new Timeline();
			time.setCycleCount(Timeline.INDEFINITE);
			if (time != null) {
				time.stop();
			}
			KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					// TODO import jeuDesDames.Checkers.CheckersMove;
					if (pla == 1) {
						seconds--;
						timeW.setText(TimeUnit.SECONDS.toMinutes(seconds) + ":" + (seconds % 60));
					} else if (pla == 2) {
						secondsB--;
						timeB.setText(TimeUnit.SECONDS.toMinutes(secondsB) + ":" + (secondsB % 60));
					}
					if (seconds <= 0) {
						gameOver("Sorry white you are out of time");
						winner = nameP2ToDB;
						initFileScoreDB();
						time.stop();
					}
					if (secondsB <= 0) {
						gameOver("Sorry Black you are out of time");
						winner = nameP1ToDB;
						initFileScoreDB();
						time.stop();
					}
				}
			});
			time.getKeyFrames().add(frame);
			time.playFromStart();
		}

		CheckersMove[] legalMoves; // Store all legal moves

		CheckersBoard() {
			super(324, 324);
			board = new CheckersData();
			doNewGame();
		}

		void doNewGame() {
			seconds = 1000;
			secondsB = 1000;
			timeW.setText("16:40");
			timeB.setText("16:40");
			if (gameInProgress == true) {
				message.setText("Finish the current game first!");
				return;
			}
			board.setUpGame(); // Set up the pieces.
			pla = 1;
			time();
			currentPlayer = CheckersData.WHITE_DIA; // WHITE_DIA moves first.
			legalMoves = board.getLegalMoves(CheckersData.WHITE_DIA);
			selectedRow = -1; // WHITE_DIA has not yet selected a piece to move.
			message.setText(Play2.getText() + ": Make your move.");
			gameInProgress = true;
			newGameButton.setDisable(true);
			resignButton.setDisable(false);
			DrowBoard();

		}

		// To flip pieces
		void resetPlace() {
			int Prow, Pcol;
			Prow = movedToRow;
			Pcol = movedToCol;
			// System.out.println("\n\n R-> " + Prow + " C -> " + Pcol);
			if (this.board.board[Prow][Pcol] == CheckersData.WHITE_PLUS) {
				// System.out.println("\n WP->WD ");
				this.board.board[Prow][Pcol] = CheckersData.WHITE_DIA;
				DrowBoard();
			} else if (this.board.board[Prow][Pcol] == CheckersData.WHITE_DIA) {
				// System.out.println("\n WD->WP");
				this.board.board[Prow][Pcol] = CheckersData.WHITE_PLUS;
				DrowBoard();

			} else if (this.board.board[Prow][Pcol] == CheckersData.BLACK_DIA) {
				// System.out.println("\n BD->BP");
				this.board.board[Prow][Pcol] = CheckersData.BLACK_PLUS;
				DrowBoard();

			} else if (this.board.board[Prow][Pcol] == CheckersData.BLACK_PLUS) {
				// System.out.println("\n BP->BD");
				this.board.board[Prow][Pcol] = CheckersData.BLACK_DIA;
				DrowBoard();
			}
		}

////////////////////////////score and players for table
		public int initFileScoreDB_bis() {
			int j = 0;
			try {

				File myObj = new File(
						"C:/Users/hp/OneDrive/Bureau/eclipse-workspace/jeuDesDames/src/jeuDesDames/score1.txt");
				Scanner myReader = new Scanner(myObj); // read data line by line

				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					this.ScoreToDB[j] = data;
					++j;
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			return j;
		}

		public String[] score() {
			int index = initFileScoreDB_bis();
			this.Information[0] = namePlayer1;
			this.Information[2] = namePlayer2;
			for (int i = 0; i < index; ++i) {
				String data = this.ScoreToDB[i];
				String[] words = data.split("\\s");
				String name1 = words[0];
				String name2 = words[2];
				String score1 = words[1];
				String score2 = words[3];
				if ((name2.equals(this.nameP2ToDB) && name1.equals(this.nameP1ToDB))
						|| (name1.equals(this.nameP2ToDB) && name2.equals(this.nameP1ToDB))) {
					this.Information[1] = score1;
					this.Information[3] = score2;
					break; //
				}

			}
			return this.Information;
		}

		/////////////////////////////////////////////////// 77777
		public void fileScoreAddOrModif(int index) {

			for (int i = 0; i < index; ++i) {
				String data = this.ScoreToDB[i];
				String[] words = data.split("\\s");
				String name1 = words[0];
				String name2 = words[2];
				String score1 = words[1];
				String score2 = words[3];
				if ((name2.equals(this.nameP2ToDB) && name1.equals(this.nameP1ToDB))
						|| (name1.equals(this.nameP2ToDB) && name2.equals(this.nameP1ToDB))) {
					if (winner == this.nameP1ToDB) {
						System.out.println("Match found winner is p1\n");
						int score = Integer.parseInt(score1);
						score += 1;
						score1 = Integer.toString(score);
						this.ScoreToDB[i] = name1 + " " + score1 + " " + name2 + " " + score2;
						writeScoreToDB(index);
						return;
					} else {
						System.out.println("Match found winner is p2\n");
						int score = Integer.parseInt(score2);
						score += 1;
						score2 = Integer.toString(score);
						this.ScoreToDB[i] = name1 + " " + score1 + " " + name2 + " " + score2;
						writeScoreToDB(index);
						return;
					}
				}

			}
			try {
				File myObj = new File(
						"C:/Users/hp/OneDrive/Bureau/eclipse-workspace/jeuDesDames/src/jeuDesDames/score1.txt");
				Scanner myReader = new Scanner(myObj); // read data line by line
				int k = 0;
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					this.ScoreToDB[k] = data;
					++k;
				}
				String data = updateScore_bis_add(0);
				this.ScoreToDB[k] = data;
				writeScoreToDB(k + 1);
				myReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

		}

		public void initFileScoreDB() {
			try {
				File myObj = new File(
						"C:/Users/hp/OneDrive/Bureau/eclipse-workspace/jeuDesDames/src/jeuDesDames/score1.txt");
				Scanner myReader = new Scanner(myObj); // read data line by line
				int j = 0;
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					this.ScoreToDB[j] = data;
					++j;
				}

				fileScoreAddOrModif(j);

				myReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}

		public void writeScoreToDB(int index) {
			try {
				FileWriter myWriter = new FileWriter(
						"C:/Users/hp/OneDrive/Bureau/eclipse-workspace/jeuDesDames/src/jeuDesDames/score1.txt");
				myWriter.flush();
				int i;
				for (i = 0; i < index; ++i) {
					if (i + 1 == index) {
						myWriter.write(this.ScoreToDB[i]);
					} else
						myWriter.write(this.ScoreToDB[i] + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}

		public String updateScore_bis_add(int index) { // add names if not exist
			String name1 = nameP1ToDB;
			String name2 = nameP2ToDB;
			String score1 = "0";
			String score2 = "0";
			if (this.winner == nameP1ToDB) {
				score1 = "1";
				score2 = "0";
			} else {
				score1 = "0";
				score2 = "1";
			}
			String data = name1 + " " + score1 + " " + name2 + " " + score2;
			System.out.println("\n\n" + data + "\n\n");
			return data;
		}

////////////////////////king
		/// ****************
		public int onlyKingsBis() {
			int white = 0;
			int black = 0;
			for (int i = 0; i < 8; ++i) { // i is row j is col
				for (int j = 0; j < 8; ++j) {
					if (board.pieceAt(i, j) == 2) { // white wins
						white++;
					}
					if (board.pieceAt(i, j) == 4) { // black wins
						black++;
					}
				}
			}
			if (white > black) {
				return 1;
			} else if (black > white) {
				return 2;
			} else
				return 0; // tie
		}

		public int onlyKings() {
			for (int i = 0; i < 8; ++i) { // i is row j is col
				for (int j = 0; j < 8; ++j) {
					if (board.pieceAt(i, j) != 0 && board.pieceAt(i, j) != 2 && board.pieceAt(i, j) != 4) { // white
																											// wins
						return -1;
					}
				}
			}
			int r = onlyKingsBis();
			return r;
		}

//////////////
		void restoreAndShow() {
			board.board = restorePreviousGame(0); // chagne now board to prev° board
			legalMoves = board.getLegalMoves(currentPlayer);

			DrowBoard();

		}

		int[][] restorePreviousGame(int unused) {
			// CheckersData backUpBoard;null
			int[][] board = new int[8][8];
			unused = 0;
			try {
				int i = 0;
				File myObj = new File("t.txt");
				Scanner myReader = new Scanner(myObj); // read data line by line
				int j = 0;
				while (myReader.hasNextLine()) {
					if (i < 64) {
						char row;
						char col;
						char type;
						String data = myReader.nextLine();
						char[] x = data.toCharArray();
						type = x[0]; // index 0 represent type
						row = x[1]; // index 1 represent row
						col = x[2]; // index 2 represent col
						board[Integer.parseInt(String.valueOf(row))][Integer.parseInt(String.valueOf(col))] = Integer
								.parseInt(String.valueOf(type)); // store all data in int format
						// debugging
						// System.out.print(i + "-" + type + row + col + "\n");
						i++;
					} else {
						if (j == 0) {
							String data = myReader.nextLine();
							namePlayer2 = data;
							root1.player2.textProperty().set(data);

							j++;
							i++;
						} else {
							String data = myReader.nextLine();
							namePlayer1 = data;
							root1.player1.textProperty().set(data);
						}

						++i;
					}
				}
				myReader.close();

			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			return board;
		}

		// read stored game
		void restorePreviousGame() {
			// CheckersData backUpBoard;
			int[][] board = new int[8][8];

			try {
				int i = 0;
				File myObj = new File("t.txt");
				Scanner myReader = new Scanner(myObj); // read data line by line
				int j = 0;
				while (myReader.hasNextLine()) {
					if (i < 64) {
						char row;
						char col;
						char type;
						String data = myReader.nextLine();
						char[] x = data.toCharArray();
						type = x[0]; // index 0 represent type
						row = x[1]; // index 1 represent row
						col = x[2]; // index 2 represent col
						board[Integer.parseInt(String.valueOf(row))][Integer.parseInt(String.valueOf(col))] = Integer
								.parseInt(String.valueOf(type)); // store all data in int format
						// debugging
						// System.out.print(i + "-" + type + row + col + "\n");
						++i;
					} else {
						if (j == 0) {
							String data = myReader.nextLine();
							namePlayer1 = data;

						} else {
							String data = myReader.nextLine();
							namePlayer1 = data;

						}
						++i;
					}

				}
				myReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			// debugging
			// System.out.println("\n\n\n"+board.length);
		}

		// SAVE game positions for next round
		///// ****************
		// SAVE game positions for next round
		public void PrintPPositions() {
			String player1 = new String();
			String player2 = new String();
			player1 = Play1.getText();
			player2 = Play2.getText();
			// System.out.println("\n\nn" + player1 + " " + player2);
			if (gameInProgress) {
				try {
					FileWriter myWriter = new FileWriter("t.txt");
					myWriter.flush(); // clean file every turn
					System.out.println("Successfully wrote to the file.");

					for (int i = 0; i < 8; ++i) { // i is row j is col
						for (int j = 0; j < 8; ++j) {
							if (board.pieceAt(i, j) == 0) {
								myWriter.write("0" + i + j + "\n"); // empty
								// System.out.print("");
							} else if (board.pieceAt(i, j) == 1) {
								// fileWriter.append("Line 5").append("\n");
								myWriter.write("1" + i + j + "\n"); // red
								// System.out.println("WHITE_DIA " + i + " " + j);
							} else if (board.pieceAt(i, j) == 2) {
								myWriter.write("2" + i + j + "\n"); // red king
								// System.out.println("2 is at " + i + " " + j);
							} else if (board.pieceAt(i, j) == 3) {
								myWriter.write("3" + i + j + "\n"); // black
								// System.out.println("3 is at " + i + " " + j);
							} else if (board.pieceAt(i, j) == 4) {
								myWriter.write("4" + i + j + "\n"); // black king
								// System.out.println("4 is at " + i + " " + j);
							} else if (board.pieceAt(i, j) == 5) {
								myWriter.write("5" + i + j + "\n"); // red_dia
								// System.out.println("5 is at " + i + " " + j);
							} else if (board.pieceAt(i, j) == 6) {
								myWriter.write("6" + i + j + "\n"); // black_dia
								// System.out.println("6 is at " + i + " " + j);
							}
						}
					}
					myWriter.write(this.nameP1ToDB + "\n");
					myWriter.write(this.nameP2ToDB + "\n");
					myWriter.close();
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			}
			restorePreviousGame();
			// fileWriter.close();
		}

		///// ****************
		// -----------------------------
		void doResign() {
			if (gameInProgress == false) {
				message.setText("There is no game in progress!");
				return;
			}
			if (currentPlayer == CheckersData.WHITE_DIA) {
				gameOver(Play2.getText() + " resigns " + Play1.getText() + " wins.\n Congratulations "
						+ Play1.getText());
				// scoreIntoDB(1);
				this.winner = nameP1ToDB;
				initFileScoreDB();

			} else {
				gameOver(Play1.getText() + " resigns " + Play2.getText() + " wins.");
				this.winner = nameP2ToDB;
				initFileScoreDB();
			}
		}

		// -------------------------------

		void gameOver(String str) {
			gameover.play();
			message.setText(str);
			newGameButton.setDisable(false);
			resignButton.setDisable(true);
			gameInProgress = false;
			time.stop();

		}

		void doClickSquare(int row, int col) {
			for (int i = 0; i < legalMoves.length; i++)
				if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
					selectedRow = row;
					selectedCol = col;
					if (currentPlayer == CheckersData.WHITE_DIA || currentPlayer == CheckersData.WHITE_PLUS)
						message.setText(Play2.getText() + ": Make your move.");
					else
						message.setText(Play1.getText() + ": Make your move.");

					DrowBoard();
					return;
				}

			if (selectedRow < 0) {
				message.setText("Click the piece you want to move.");
				return;
			}
			for (int i = 0; i < legalMoves.length; i++)
				if (legalMoves[i].fromRow == selectedRow && legalMoves[i].fromCol == selectedCol
						&& legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
					doMakeMove(legalMoves[i]);

					return;
				}
			message.setText("Click the square you want to move to.");
		}

		void doMakeMove(CheckersMove move) {
			int onlyKing = onlyKings(); // check if onlyKings are in game
			if (onlyKing != -1) {
				if (onlyKing == 1) {
					message.setText("White has more kings.  WHITE wins.");
					gameOver("White has more kings.  white wins.");

					this.winner = nameP2ToDB;
					initFileScoreDB();
				} else if (onlyKing == 2) {
					message.setText("black has more kings.  black wins.");
					gameOver("black has more kings.  black wins.");

					this.winner = nameP1ToDB;
					initFileScoreDB();
				} else {
					message.setText("Partie NULL");
					gameOver("Partie NULL ");
					// this.winner = nameP1ToDB;
					// initFileScoreDB();
				}
			} else {
				chang.setDisable(false);
				board.makeMove(move);
				movedToRow = move.toRow;
				movedToCol = move.toCol;
				System.out.println("r" + movedToRow + " c " + movedToCol);
				if (move.isJump()) {
					legalMoves = board.getLegalJumpsFrom(currentPlayer, move.toRow, move.toCol);
					if (legalMoves != null) {
						chang.setDisable(true);
						if (currentPlayer == CheckersData.WHITE_DIA)
							message.setText(Play2.getText() + ": You must continue jumping.");
						else
							message.setText(Play1.getText() + ": You must continue jumping.");

						selectedRow = move.toRow;
						selectedCol = move.toCol;
						DrowBoard();
						return;
					}
				}
				// change turn

				if (currentPlayer == CheckersData.WHITE_DIA || currentPlayer == CheckersData.WHITE_PLUS
						|| currentPlayer == CheckersData.WHITE_KING) {
					if (currentPlayer == CheckersData.WHITE_DIA) {
						currentPlayer = CheckersData.BLACK_DIA;
						System.out.println(timeoverA);
					}
					if (currentPlayer == CheckersData.WHITE_PLUS) {
						currentPlayer = CheckersData.BLACK_DIA;
					}
					legalMoves = board.getLegalMoves(currentPlayer);
					if (legalMoves == null) {
						gameOver(Play1.getText() + " has no moves." + Play2.getText() + " wins.");
						this.winner = nameP2ToDB;
						initFileScoreDB();
					} else if (legalMoves[0].isJump())
						message.setText(Play1.getText() + ": Make your move. You must jump.");
					else
						message.setText(Play1.getText() + ": Make your move.");
				} else if (currentPlayer == CheckersData.BLACK_DIA || currentPlayer == CheckersData.BLACK_DIA) {
					if (currentPlayer == CheckersData.BLACK_DIA) {
						currentPlayer = CheckersData.WHITE_DIA;
					}
					if (currentPlayer == CheckersData.BLACK_DIA) {
						currentPlayer = CheckersData.BLACK_DIA;
					}

					timeoverB = -1;
					legalMoves = board.getLegalMoves(currentPlayer);
					if (legalMoves == null) {
						gameOver(Play2.getText() + " has no moves." + Play1.getText() + " wins.");
						this.winner = nameP1ToDB;
						initFileScoreDB();

						System.out.println("scoreeeeeeee" + score);
					} else if (legalMoves[0].isJump())
						message.setText(Play2.getText() + ": Make your move.  You must jump.");
					else
						message.setText(Play2.getText() + ": Make your move.");
				}
				selectedRow = -1; // to record that the player has not yet selected a piece

				if (legalMoves != null) {
					boolean sameStartSquare = true;
					for (int i = 1; i < legalMoves.length; i++)
						if (legalMoves[i].fromRow != legalMoves[0].fromRow
								|| legalMoves[i].fromCol != legalMoves[0].fromCol) {
							sameStartSquare = false;
							break;
						}
					if (sameStartSquare) {
						selectedRow = legalMoves[0].fromRow;
						selectedCol = legalMoves[0].fromCol;
					}
				}
				DrowBoard();
			}
		}

		public void DrowBoard() {
			GraphicsContext g = getGraphicsContext2D();
			g.setFont(Font.font(18));
			g.setStroke(Color.DARKRED);
			g.setLineWidth(5);
			g.strokeRect(1, 1, 322, 322);

			/* Draw the squares of the checkerboard and the checkers. */
			char c = 'a';
			char aa = '8';
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if (row % 2 == col % 2)
						g.setFill(Color.valueOf("#feb"));
					else
						g.setFill(Color.valueOf("#582"));

					g.fillRect(2 + col * 40, 2 + row * 40, 40, 40);
					g.setFill(Color.BLACK);

					if (row == 7) {
						String S = "" + c;
						g.fillText(S, 2 + col * 40, 40 + row * 40);
						c++;
					}
					if (col == 7) {
						String S = "" + aa;
						g.fillText(S, 30 + col * 40, 16 + row * 40);
						aa--;
					}

					switch (board.pieceAt(row, col)) {
					case CheckersData.WHITE_DIA:
						Image iv = new Image(getClass().getResource("fois_wight.png").toString());
						g.drawImage(iv, 8 + col * 40, 8 + row * 40, 28, 28);
						break;

					case CheckersData.BLACK_DIA:
						Image iv2 = new Image(getClass().getResource("noir_fois.png").toString());
						g.drawImage(iv2, 8 + col * 40, 8 + row * 40, 28, 28);
						break;

					case CheckersData.WHITE_KING:
						g.setFill(Color.WHITE);
						g.fillOval(8 + col * 40, 8 + row * 40, 28, 28);
						g.setFill(Color.BLACK);
						g.fillText("K", 15 + col * 40, 29 + row * 40);
						break;

					case CheckersData.BLACK_KING:
						g.setFill(Color.BLACK);
						g.fillOval(8 + col * 40, 8 + row * 40, 28, 28);
						g.setFill(Color.WHITE);
						g.fillText("K", 15 + col * 40, 29 + row * 40);
						break;

					case CheckersData.BLACK_PLUS:
						Image iv3 = new Image(getClass().getResource("black_plus.png").toString());
						g.drawImage(iv3, 8 + col * 40, 8 + row * 40, 28, 28);
						break;

					case CheckersData.WHITE_PLUS:
						Image iv1 = new Image(getClass().getResource("plus_white.png").toString());
						g.drawImage(iv1, 8 + col * 40, 8 + row * 40, 28, 28);
						break;
					}
				}
			}

			if (gameInProgress) {
				g.setStroke(Color.CYAN);
				g.setLineWidth(4);
				for (int i = 0; i < legalMoves.length; i++) {
					g.strokeRect(4 + legalMoves[i].fromCol * 40, 4 + legalMoves[i].fromRow * 40, 36, 36);
				}
				if (selectedRow >= 0) {
					g.setStroke(Color.YELLOW);
					g.setLineWidth(4);
					g.strokeRect(4 + selectedCol * 40, 4 + selectedRow * 40, 36, 36);
					g.setStroke(Color.LIME);
					g.setLineWidth(4);
					for (int i = 0; i < legalMoves.length; i++) {
						if (legalMoves[i].fromCol == selectedCol && legalMoves[i].fromRow == selectedRow) {
							g.strokeRect(4 + legalMoves[i].toCol * 40, 4 + legalMoves[i].toRow * 40, 36, 36);
						}
					}
				}
			}

		}

		public void mousePressed(MouseEvent evt) {
			if (gameInProgress == false)
				message.setText("Click \"New Game\" to start a new game.");
			else {
				int col = (int) ((evt.getX() - 2) / 40);
				int row = (int) ((evt.getY() - 2) / 40);
				posCurrentPLayer = getCurrentPiece(col, row);
				this.nameP2ToDB = namePlayer1;
				this.nameP1ToDB = namePlayer2;
				this.Information[0] = namePlayer1;
				this.Information[1] = "0";
				this.Information[3] = "0";
				this.Information[2] = namePlayer2;
				System.out.println("current pla : " + posCurrentPLayer);
				if (col >= 0 && col < 8 && row >= 0 && row < 8)
					doClickSquare(row, col);
			}
		}

		public int getCurrentPiece(int coll, int roow) {
			CheckersData check = this.board;
			int cur = check.pieceAt(roow, coll);
			if (cur == 0)// empty
				return 0;
			else if (cur == 1)// red
				return 1;
			else if (cur == 2)// red king
				return 2;
			else if (cur == 3)// black
				return 3;
			else if (cur == 4)// black king
				return 4;
			else if (cur == 5)// red dia
				return 5;

			return 6; // black dia
		}
	}

	private static class CheckersData {

		static final int EMPTY = 0;
		static final int WHITE_DIA = 1;
		static final int WHITE_KING = 2;
		static final int BLACK_DIA = 3;
		static final int BLACK_KING = 4;
		static final int WHITE_PLUS = 5;
		static final int BLACK_PLUS = 6;// plus
		int[][] board; // board[r][c]

		CheckersData() {
			board = new int[8][8];
			setUpGame();
		}

		void setUpGame() {
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if ((row == 1) && (col > 0 && col < 7)) {
						board[row][col] = BLACK_PLUS;
					} else if ((row == 2) && (col > 0 && col < 7)) {
						board[row][col] = BLACK_DIA;
					} else if ((row == 5) && (col > 0 && col < 7)) {
						board[row][col] = WHITE_DIA;
					} else if ((row == 6) && (col > 0 && col < 7)) {
						board[row][col] = WHITE_PLUS;
					} else {
						board[row][col] = EMPTY;
					}
				}
			}

		}

		int pieceAt(int row, int col) {
			return board[row][col];
		}

		void makeMove(CheckersMove move) {
			makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
		}

		// move
		void makeMove(int fromRow, int fromCol, int toRow, int toCol) {

			AudioClip move = new AudioClip(getClass().getResource("move.mp3").toString());
			board[toRow][toCol] = board[fromRow][fromCol];
			move.play();
			board[fromRow][fromCol] = EMPTY;

			// Killllllllll
			if (fromRow - toRow == 2 || fromRow - toRow == -2) {
				// changePiece.setDisable(true);
				int jumpRow = (fromRow + toRow) / 2; // Row of the jumped piece.
				int jumpCol = (fromCol + toCol) / 2; // Column of the jumped piece.
				board[jumpRow][jumpCol] = EMPTY;
			} else if (fromCol - toCol == 2 || fromCol - toCol == -2) { // diago
				// changePiece.setDisable(true);
				int jumpRow = (fromRow + toRow) / 2; // Row of the jumped piece.
				int jumpCol = (fromCol + toCol) / 2; // Column of the jumped piece.
				board[jumpRow][jumpCol] = EMPTY;

			}
			// transoform to king
			if (toRow == 0 && (board[toRow][toCol] == WHITE_DIA || board[toRow][toCol] == WHITE_PLUS))
				board[toRow][toCol] = WHITE_KING;
			if (toRow == 7 && (board[toRow][toCol] == BLACK_DIA || board[toRow][toCol] == BLACK_PLUS))
				board[toRow][toCol] = BLACK_KING;

		}

		CheckersMove[] getLegalMoves(int player) {

			if (player != WHITE_DIA && player != BLACK_DIA && player != BLACK_PLUS && player != WHITE_PLUS) {
				return null;
			}

			int playerKing;

			if (player == WHITE_DIA) {
				playerKing = WHITE_KING;

			} else {
				playerKing = BLACK_KING;

			}

			ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>(); // Moves will be stored in this list.

			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if (player == WHITE_KING || player == BLACK_KING) {
						if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2))
							moves.add(new CheckersMove(row, col, row + 2, col + 2));
						if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
							moves.add(new CheckersMove(row, col, row + 2, col - 2));
						if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
							moves.add(new CheckersMove(row, col, row - 2, col - 2));
						if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
							moves.add(new CheckersMove(row, col, row - 2, col + 2));
						if (canJump(player, row, col, row, col + 1, row, col + 2))
							moves.add(new CheckersMove(row, col, row, col + 2));
						if (canJump(player, row, col, row, col - 1, row, col - 2))
							moves.add(new CheckersMove(row, col, row, col - 2));
						if (canJump(player, row, col, row + 1, col, row + 2, col))
							moves.add(new CheckersMove(row, col, row + 2, col));
						if (canJump(player, row, col, row - 1, col, row - 2, col))
							moves.add(new CheckersMove(row, col, row - 2, col));
					}

					if (player == BLACK_DIA) {
						pla = 2;

						if (board[row][col] == BLACK_DIA || board[row][col] == playerKing) {// croix
							if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2))
								moves.add(new CheckersMove(row, col, row + 2, col + 2));

							if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
								moves.add(new CheckersMove(row, col, row + 2, col - 2));
							if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
								moves.add(new CheckersMove(row, col, row - 2, col - 2));
							if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
								moves.add(new CheckersMove(row, col, row - 2, col + 2));

						}

						if (board[row][col] == BLACK_PLUS || board[row][col] == playerKing) {

							if (canJump(player, row, col, row, col + 1, row, col + 2))
								moves.add(new CheckersMove(row, col, row, col + 2));
							if (canJump(player, row, col, row, col - 1, row, col - 2))
								moves.add(new CheckersMove(row, col, row, col - 2));
							if (canJump(player, row, col, row + 1, col, row + 2, col))
								moves.add(new CheckersMove(row, col, row + 2, col));
							if (canJump(player, row, col, row - 1, col, row - 2, col))
								moves.add(new CheckersMove(row, col, row - 2, col));
						}
					}
					if (player == WHITE_DIA) {
						pla = 1;

						if (board[row][col] == WHITE_DIA || board[row][col] == playerKing) {
							if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
								moves.add(new CheckersMove(row, col, row - 2, col - 2));
							if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
								moves.add(new CheckersMove(row, col, row - 2, col + 2));
							if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2))
								moves.add(new CheckersMove(row, col, row + 2, col + 2));
							if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
								moves.add(new CheckersMove(row, col, row + 2, col - 2));
						}

						if (board[row][col] == WHITE_PLUS || board[row][col] == playerKing) {

							if (canJump(player, row, col, row, col - 1, row, col - 2))
								moves.add(new CheckersMove(row, col, row, col - 2));
							if (canJump(player, row, col, row, col + 1, row, col + 2))
								moves.add(new CheckersMove(row, col, row, col + 2));
							if (canJump(player, row, col, row - 1, col, row - 2, col))
								moves.add(new CheckersMove(row, col, row - 2, col));
							if (canJump(player, row, col, row + 1, col, row + 2, col))
								moves.add(new CheckersMove(row, col, row + 2, col));
						}

					}

				}
			}

			if (moves.size() == 0) {
				for (int row = 0; row < 8; row++) {
					for (int col = 0; col < 8; col++) {
						// foisssss
						if (board[row][col] == playerKing) {
							if (canMove(player, row, col, row, col + 1)) {
								moves.add(new CheckersMove(row, col, row, col + 1));
							}
							if (canMove(player, row, col, row, col - 1))
								moves.add(new CheckersMove(row, col, row, col - 1));
							if (canMove(player, row, col, row + 1, col))
								moves.add(new CheckersMove(row, col, row + 1, col));

							if (canMove(player, row, col, row + 1, col + 1))
								moves.add(new CheckersMove(row, col, row + 1, col + 1));
							if (canMove(player, row, col, row + 1, col - 1))
								moves.add(new CheckersMove(row, col, row + 1, col - 1));

							if (canMove(player, row, col, row, col + 1))
								moves.add(new CheckersMove(row, col, row, col + 1));
							if (canMove(player, row, col, row, col - 1))
								moves.add(new CheckersMove(row, col, row, col - 1));
							if (canMove(player, row, col, row - 1, col))
								moves.add(new CheckersMove(row, col, row - 1, col));

							if (canMove(player, row, col, row - 1, col + 1))
								moves.add(new CheckersMove(row, col, row - 1, col + 1));
							if (canMove(player, row, col, row - 1, col - 1))
								moves.add(new CheckersMove(row, col, row - 1, col - 1));

						}
						if (player == BLACK_DIA) {
							pla = 2;
							if (board[row][col] == BLACK_DIA || board[row][col] == playerKing) {
								if (canMove(player, row, col, row, col + 1)) {
									moves.add(new CheckersMove(row, col, row, col + 1));
								}
								if (canMove(player, row, col, row, col - 1))
									moves.add(new CheckersMove(row, col, row, col - 1));
								if (canMove(player, row, col, row + 1, col))
									moves.add(new CheckersMove(row, col, row + 1, col));
							}
							if (board[row][col] == BLACK_PLUS || board[row][col] == playerKing) {
								if (canMove(player, row, col, row + 1, col + 1))
									moves.add(new CheckersMove(row, col, row + 1, col + 1));
								if (canMove(player, row, col, row + 1, col - 1))
									moves.add(new CheckersMove(row, col, row + 1, col - 1));

							}
						}
						if (player == WHITE_DIA) {
							pla = 1;
							if (board[row][col] == WHITE_DIA || board[row][col] == playerKing) {
								if (canMove(player, row, col, row, col + 1))
									moves.add(new CheckersMove(row, col, row, col + 1));
								if (canMove(player, row, col, row, col - 1))
									moves.add(new CheckersMove(row, col, row, col - 1));
								if (canMove(player, row, col, row - 1, col))
									moves.add(new CheckersMove(row, col, row - 1, col));
							}

							if (board[row][col] == WHITE_PLUS || board[row][col] == playerKing) {
								if (canMove(player, row, col, row - 1, col + 1))
									moves.add(new CheckersMove(row, col, row - 1, col + 1));
								if (canMove(player, row, col, row - 1, col - 1))
									moves.add(new CheckersMove(row, col, row - 1, col - 1));

							}
						}
					}
				}
			}

			if (moves.size() == 0)
				return null;
			else {
				CheckersMove[] moveArray = new CheckersMove[moves.size()];
				for (int i = 0; i < moves.size(); i++)
					moveArray[i] = moves.get(i);
				return moveArray;
			}
		}

		CheckersMove[] getLegalJumpsFrom(int player, int row, int col) {
			if (player != WHITE_DIA && player != BLACK_DIA)
				return null;
			int playerKing; // The constant representing a King belonging to player.
			if (player == WHITE_DIA)
				playerKing = WHITE_KING;
			else
				playerKing = BLACK_KING;

			ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>(); // The legal jumps will be stored in this
			if (player == WHITE_KING || player == BLACK_KING) {
				if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2)) {
					moves.add(new CheckersMove(row, col, row + 2, col + 2));

				}
				if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2)) {
					moves.add(new CheckersMove(row, col, row + 2, col - 2));

				}
				if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2)) {
					moves.add(new CheckersMove(row, col, row - 2, col - 2));

				} //
				if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2)) {
					moves.add(new CheckersMove(row, col, row - 2, col + 2));

				}
				if (canJump(player, row, col, row, col + 1, row, col + 2)) {
					moves.add(new CheckersMove(row, col, row, col + 2));

				} //
				if (canJump(player, row, col, row, col - 1, row, col - 2)) {
					moves.add(new CheckersMove(row, col, row, col - 2));

				}
				if (canJump(player, row, col, row + 1, col, row + 2, col)) {
					moves.add(new CheckersMove(row, col, row + 2, col));

				}
				if (canJump(player, row, col, row - 1, col, row - 2, col)) {
					moves.add(new CheckersMove(row, col, row - 2, col));

				}

			}

			if (player == BLACK_DIA) {
				pla = 2;

				if (board[row][col] == BLACK_DIA || board[row][col] == playerKing) {// croix
					if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2)) {
						moves.add(new CheckersMove(row, col, row + 2, col + 2));

					}
					if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2)) {
						moves.add(new CheckersMove(row, col, row + 2, col - 2));

					}
					if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2)) {
						moves.add(new CheckersMove(row, col, row - 2, col - 2));

					}
				}
				if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2)) {
					moves.add(new CheckersMove(row, col, row - 2, col + 2));

				}

			}

			if (board[row][col] == BLACK_PLUS || board[row][col] == playerKing)

			{

				if (canJump(player, row, col, row, col + 1, row, col + 2))
					moves.add(new CheckersMove(row, col, row, col + 2));
				if (canJump(player, row, col, row, col - 1, row, col - 2))
					moves.add(new CheckersMove(row, col, row, col - 2));
				if (canJump(player, row, col, row + 1, col, row + 2, col))
					moves.add(new CheckersMove(row, col, row + 2, col));
				if (canJump(player, row, col, row - 1, col, row - 2, col))
					moves.add(new CheckersMove(row, col, row - 2, col));
			}
			if (player == WHITE_DIA)

			{
				pla = 1;

				if (board[row][col] == WHITE_DIA || board[row][col] == playerKing) {
					if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
						moves.add(new CheckersMove(row, col, row - 2, col - 2));
					if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
						moves.add(new CheckersMove(row, col, row - 2, col + 2));
					if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2))
						moves.add(new CheckersMove(row, col, row + 2, col + 2));
					if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
						moves.add(new CheckersMove(row, col, row + 2, col - 2));
				}

				if (board[row][col] == WHITE_PLUS || board[row][col] == playerKing) {

					if (canJump(player, row, col, row, col - 1, row, col - 2))
						moves.add(new CheckersMove(row, col, row, col - 2));
					if (canJump(player, row, col, row, col + 1, row, col + 2))
						moves.add(new CheckersMove(row, col, row, col + 2));
					if (canJump(player, row, col, row - 1, col, row - 2, col))
						moves.add(new CheckersMove(row, col, row - 2, col));
					if (canJump(player, row, col, row + 1, col, row + 2, col))
						moves.add(new CheckersMove(row, col, row + 2, col));
				}

			}
			if (moves.size() == 0)
				return null;
			else {
				CheckersMove[] moveArray = new CheckersMove[moves.size()];
				for (int i = 0; i < moves.size(); i++)
					moveArray[i] = moves.get(i);
				return moveArray;
			}
		}
		// -----------------------------

		private boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) {

			if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
				return false; // (r3,c3) is off the board.

			if (board[r3][c3] != EMPTY)
				return false; // (r3,c3) already contains a piece.
			if (board[r1][c1] == WHITE_DIA || board[r1][c1] == WHITE_PLUS || board[r1][c1] == BLACK_DIA
					|| board[r1][c1] == BLACK_PLUS) {
				if (board[r2][c2] == WHITE_KING || board[r2][c2] == BLACK_KING)
					return false;
			}

			if (board[r1][c1] == WHITE_KING && board[r2][c2] == BLACK_KING) {

				return true;

			}

			if (board[r1][c1] == BLACK_KING && board[r2][c2] == WHITE_KING) {
				return true;
			}

			if (player == WHITE_DIA) {
				// if (board[r1][c1] == WHITE_DIA && r3 > r1)
				// return false;
				// Regular red piece can only move up.
				if (board[r2][c2] != BLACK_DIA && board[r2][c2] != BLACK_KING && board[r2][c2] != BLACK_PLUS)
					return false;
				// There is no black piece to jump.//

				return true; // The jump is legal.
			} else {
				// if (board[r1][c1] == BLACK_DIA && r3 < r1)
				// return false; // Regular black piece can only move downn.
				if (board[r2][c2] != WHITE_DIA && board[r2][c2] != WHITE_KING && board[r2][c2] != WHITE_PLUS)
					return false; // There is no red piece to jump.
				return true; // The jump is legal.
			}

		}

		// -----------------------------

		private boolean canMove(int player, int r1, int c1, int r2, int c2) {

			if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
				return false; // (r2,c2) is off the board.

			if (board[r2][c2] != EMPTY)
				return false; // (r2,c2) already contains a piece.

			if (player == WHITE_DIA) {
				if (board[r1][c1] == WHITE_DIA && r2 > r1) {
					return false; // Regular red piece can only move down.
				}
				return true; // The move is legal.
			} else {
				if (board[r1][c1] == BLACK_DIA && r2 < r1) {
					return false;
				}
				return true; // The move is legal.
			}

		}

	}

}
