package jeuDesDames;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


//plus long move ++ long move config ++ changement de piece



public class Checkers extends Application {
	int score=0;
	public Pane root;
	public int posCurrentPLayer;
	public static int pla = 0;
	private int startTime = 100;
	private Integer seconds = startTime;
	private Integer secondsB = startTime;
	CheckersBoard board;
	CheckersBoard board1;
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
	
	Timeline time = new Timeline();
	Label timeB = new Label("100");
	Label timeW = new Label("100");
	
	Label Player1 = new Label("Player 2");
	Label Player2 = new Label("Player 1");
	Label Play1 = new Label();
	Label Play2 = new Label();
	AudioClip sound1 = new AudioClip(getClass().getResource("sound1.mp3").toString());
	AudioClip gameover = new AudioClip(getClass().getResource("gameover.mp3").toString());
	
	

	public void start(Stage stage) {
		root = new Pane();
		//writeObject();
		this.stage = stage;
		ImageView start = new ImageView(getClass().getResource("start.png").toString());
		message = new Label("Click \"New Game\" to begin.");
		movementsMsgs = new Label("Movements");
		message.setTextFill(Color.BLACK); // Light green.
		message.setFont(Font.font(null, FontWeight.BOLD, 14));
		movementsMsgs.setTextFill(Color.rgb(100, 255, 100)); // Light green.
		 
		movementsMsgs.setFont(Font.font(null, FontWeight.BOLD, 9));
		newGameButton = new Button();
		resignButton = new Button("Resign");
		saveGameButton = new Button("Help");
		board = new CheckersBoard();
		board.DrowBoard();
		
		newGameButton.setOnAction(e -> board.doNewGame());
		resignButton.setOnAction(e -> board.doResign());
		// saveGameButton.setOnAction(e -> board.saveGame());
		board.setOnMousePressed(e -> board.mousePressed(e));
		//Changer.setOnAction(null);
		//board.chang.relocate(20, 300);
		
		
		board.relocate(20,80);
		newGameButton.relocate(370, 200);
		//Changer.relocate(370, 440);
		saveGameButton.relocate(370, 250);
		resignButton.relocate(370, 300);
		message.relocate(20, 450);
		movementsMsgs.relocate(20,500);
		timeB.relocate(300, 50);
		timeW.relocate(300, 420);
		resignButton.setManaged(false);
		resignButton.resize(70, 30);
		newGameButton.setManaged(false);
		newGameButton.resize(50, 30);
		saveGameButton.setManaged(false);
		saveGameButton.resize(50, 30);
Button valider1 = new Button("Play");	
TextField player2 = new TextField();
		player2.setPrefColumnCount(20);
		 
		TextField player1 = new TextField();
		player1.setPrefColumnCount(20);

		
		VBox root1 = new VBox(10);
		HBox ro = new HBox(20);
		VBox roo = new VBox(6);
		VBox roo1 = new VBox(6);
		root1.setPadding(new Insets(10));
		
		valider1.setMaxSize(100, 60);
		
		valider1.getStylesheets().add(getClass().getResource("square.css").toString());
		valider1.getStyleClass().add("valider1");
		
		
		newGameButton.getStylesheets().add(getClass().getResource("anim.css").toString());
		newGameButton.getStyleClass().add("start");
		
		saveGameButton.getStylesheets().add(getClass().getResource("anim.css").toString());
		saveGameButton.getStyleClass().add("start");
		
		resignButton.getStylesheets().add(getClass().getResource("anim.css").toString());
		resignButton.getStyleClass().add("start");
		//root1.getStylesheets().add(getClass().getResource("square.css").toString());
		//root1.getStyleClass().add("scene");
		Label label1 = new Label("Player 1");
		
		
		Player1.setFont(Font.font("NewSerif", FontWeight.EXTRA_BOLD, 14));
		Player2.setFont(Font.font("NewSerif", FontWeight.EXTRA_BOLD, 14));
		Player2.relocate(20, 50);
		Play2.relocate(80, 420);
		//Player1.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-opacity: 1.0;");
		Player1.relocate(20, 420);
		Play1.relocate(80, 50);
		
		Player1.setTextFill(Color.valueOf("#B8860B"));
		Player2.setTextFill(Color.valueOf("#B8860B"));
		
		Play1.textProperty().bind(player1.textProperty());
		
		Play1.setTextFill(Color.BLACK);
		Play2.setTextFill(Color.WHITE);
		Play1.setFont(Font.font("NewSerif", FontWeight.BOLD, FontPosture.ITALIC, 12));
		Play2.setFont(Font.font("NewSerif", FontWeight.BOLD,FontPosture.ITALIC, 12));
		
		Play2.textProperty().bind(player2.textProperty());
		
		player1.setPromptText("entez votre nom");
		player2.setPromptText("entez votre nom");
		roo.getChildren().addAll(label1,player1);
		roo1.getChildren().addAll(new Label("Player 2"),player2);
		ro.getChildren().addAll(roo,roo1);
		Label welcome = new Label("Welcome");
		//welcome.getStylesheets().add(getClass().getResource("square.css").toString());
	    //welcome.getStyleClass().add("valider1");
	
		root1.getChildren().addAll(ro,valider1);
		root1.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(root1); 
		
		newGameButton.setGraphic(start);
		
		
		Stage stage2 = new Stage();
		stage2.setTitle("About Alliance");
		BorderPane aide = new BorderPane();
		Label aid = new Label();
		aide.setCenter(aid);
	root.setStyle("-fx-background-color: BISQUE; -fx-effect: innershadow(gaussian, rgba(0,0,0,0.4), 75, 0.5, 0, 10);");
		root.setPadding(new Insets(20));
		Scene scene3 = new Scene(aide);
		ImageView iv = new ImageView(getClass().getResource("aide.png").toString());
    	iv.setFitWidth(500);
    	iv.setFitHeight(500);
        
    	aid.setGraphic(iv);
    root1.setStyle("-fx-background-color: BISQUE; -fx-effect: innershadow(gaussian, rgba(0,0,0,0.4), 75, 0.5, 0, 10);");
		stage2.setScene(scene3);
		saveGameButton.setOnAction(e -> {stage2.show();});
		root.getChildren().addAll(board, newGameButton,Play1,Play2, board.chang,Player1,Player2,timeB,timeW, resignButton, message, saveGameButton,movementsMsgs);
		
		//root.setStyle("-fx-background-color: BISQUE; " + "-fx-border-color: darkred; -fx-border-width:3");
		Scene scene = new Scene(root,500,550);
		stage.setScene(scene1);
		stage.setResizable(true);
		valider1.setOnAction(e->{stage.setScene(scene);});
		stage.setResizable(false);
		stage.setTitle("Checkers!");
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

		CheckersData board;
		boolean gameInProgress;
		int currentPlayer;
		int selectedRow, selectedCol;
		int noir = 10;
		int blanc = 9;
		
		private Button chang = new Button ("change");
		
		public void time() {
			
			Timeline time = new Timeline();
			time.setCycleCount(Timeline.INDEFINITE);
			if(time != null) {
				time.stop();
			}
			KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					// TODO Auto-generated method stub
					if(pla == 1) {
						seconds--;
						timeW.setText(TimeUnit.SECONDS.toMinutes(seconds) + ":" + (seconds % 60));
					}
					else if (pla == 2 ) {
						
						secondsB--;
						 
						timeB.setText(TimeUnit.SECONDS.toMinutes(secondsB) + ":" + (secondsB % 60));
					}
					if(seconds <= 0) {
						gameOver("Sorry white you are out of time");
						time.stop();
					}if(secondsB <= 0) {
						
						gameOver("Sorry Black you are out of time");
						
						time.stop();
						
					}
					
				}
				
			});
			//time.frame.setCycleCount(Timeline.INDEFINITE);
			//timer.timeline.play();
			time.getKeyFrames().add(frame);
			time.playFromStart();
		}
		
		CheckersMove[] legalMoves;

		CheckersBoard() {
			super(324, 324);
			board = new CheckersData();
			doNewGame();
			
		}

		void doNewGame() {
			
			seconds =100; 
			secondsB = 100;
			timeW.setText("100");
			timeB.setText("100");
			
			if (gameInProgress == true) {
				message.setText("Finish the current game first!");
				return;
			}
			board.setUpGame(); // Set up the pieces.
			pla = 1;
			time();
			currentPlayer = CheckersData.RED; // RED moves first.
			legalMoves = board.getLegalMoves(CheckersData.RED);
			selectedRow = -1; // RED has not yet selected a piece to move.
			message.setText(Play2.getText() +": Make your move.");
			gameInProgress = true;
			newGameButton.setDisable(true);
			resignButton.setDisable(false);
			
			
			DrowBoard();
			
		}

		// -----------------------------
		void doResign() {
			if (gameInProgress == false) {
				message.setText("There is no game in progress!");
				return;
			}
			if (currentPlayer == CheckersData.RED) {
				gameOver(Play2.getText() +" resigns "+  Play1.getText() +" wins.\n Congratulations "+ Play1.getText());
				score ++;
				System.out.println("scoreeeeeeee"+score);
			}
			else {
				gameOver(Play1.getText() +" resigns "+  Play2.getText() +" wins.");
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

		// -----------------------------
		void doClickSquare(int row, int col) {

			for (int i = 0; i < legalMoves.length; i++)
				if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
					selectedRow = row;
					selectedCol = col;
					if (currentPlayer == CheckersData.RED || currentPlayer == CheckersData.RED_DIA)
						message.setText(Play2.getText()+": Make your move.");
					else
						message.setText(Play1.getText()+": Make your move.");
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
		
		
		// -----------------------------------------------a modifierrrrrrrrrrrr
		void doMakeMove(CheckersMove move) {

			board.makeMove(move);
            
			if (move.isJump()) {
				legalMoves = board.getLegalJumpsFrom(currentPlayer, move.toRow, move.toCol);
				if (legalMoves != null) {
					if (currentPlayer == CheckersData.RED)
						message.setText(Play2.getText()+": You must continue jumping.");
					else
						message.setText(Play1.getText()+": You must continue jumping.");
			
				selectedRow = move.toRow;
			    selectedCol = move.toCol;
			    
				DrowBoard();
				
					return;
				}
			}
//changer le joueur
			
			
			
			if (currentPlayer == CheckersData.RED ||currentPlayer == CheckersData.RED_DIA  ) {
				if(currentPlayer == CheckersData.RED)	{
				currentPlayer = CheckersData.BLACK;
				System.out.println(timeoverA);
				}
				if(currentPlayer == CheckersData.RED_DIA)	{
					currentPlayer = CheckersData.BLACK_DIA;
					
					}
				legalMoves = board.getLegalMoves(currentPlayer);
				if (legalMoves == null)
					gameOver("BLACK has no moves.  RED wins.");
				else if (legalMoves[0].isJump())
					message.setText(Play1.getText()+": Make your move. You must jump.");
				else
					message.setText(Play1.getText()+": Make your move.");
			} else if(currentPlayer == CheckersData.BLACK ||currentPlayer == CheckersData.BLACK_DIA){
				if(currentPlayer == CheckersData.BLACK)	{
					currentPlayer = CheckersData.RED;
					
					}
					if(currentPlayer == CheckersData.BLACK_DIA)	{
						currentPlayer = CheckersData.BLACK;
						
						}
				
				timeoverB  = -1;
				
				legalMoves = board.getLegalMoves(currentPlayer);
				if (legalMoves == null) {
					gameOver("RED has no moves.  BLACK wins.");
				    score ++;
				    System.out.println("scoreeeeeeee"+score);}
				else if (legalMoves[0].isJump())
					message.setText(Play2.getText()+": Make your move.  You must jump.");
				else
					message.setText(Play2.getText()+": Make your move.");
			}

			// to record that the player has not yet selected a piece
			selectedRow = -1;

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

		public void DrowBoard() {
			
			
			GraphicsContext g = getGraphicsContext2D();
			
			g.setFont(Font.font(18));
			g.setStroke(Color.DARKRED);
			g.setLineWidth(5);
			g.strokeRect(1, 1, 322, 322);
			//g.strokeRect(10, 10, 800, 800);
			/* Draw the squares of the checkerboard and the checkers. */
			char c = 'a';
			char aa = '8';
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					
					
					if (row % 2 == col % 2)
						g.setFill(Color.valueOf("#feb") );
					else
						 
						g.setFill(Color.valueOf("#582"));
					    g.fillRect(2 + col * 40, 2 + row * 40, 40, 40);
					     g.setFill(Color.BLACK);
					     
					if (row == 7) {
						
							String S = ""+c;
							g.fillText(S, 2 + col * 40, 40+ row * 40);
							
						c++;
					}
					if (col == 7) {
						String S = ""+aa;
						 g.fillText(S, 30 + col * 40, 40+ row * 40);
						 aa--;
					}
					
					switch (board.pieceAt(row, col)) {
					case CheckersData.RED:
						Image iv = new Image(getClass().getResource("fois_wight.png").toString());
		                g.drawImage(iv,8 + col * 40, 8 + row * 40, 28, 28);
						
						break;
					case CheckersData.BLACK:
						Image iv2 = new Image(getClass().getResource("noir_fois.png").toString());
		                g.drawImage(iv2,8 + col * 40, 8 + row * 40, 28, 28);
						break;
					case CheckersData.RED_KING:
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
					case CheckersData.BLACK_DIA:
						
						Image iv3 = new Image(getClass().getResource("black_plus.png").toString());
		                g.drawImage(iv3,8 + col * 40, 8 + row * 40, 28, 28);
						break;
					case CheckersData.RED_DIA:
						Image iv1 = new Image(getClass().getResource("plus_white.png").toString());
		                g.drawImage(iv1,8 + col * 40, 8 + row * 40, 28, 28);
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
				posCurrentPLayer = getCurrentPiece(col,row);
				
				System.out.println("current pla : "+posCurrentPLayer);
				if (col >= 0 && col < 8 && row >= 0 && row < 8)
					doClickSquare(row, col);
			}
		}
		
		public int getCurrentPiece(int coll, int roow) {
			CheckersData check = this.board;
			int cur = check.pieceAt(roow, coll);
			if ( cur == 0)//empty
				return 0;
			else if (cur == 1)//red
				return 1;
			else if (cur == 2)//red king
				return 2;
			else if (cur == 3)//black
				return 3;
			else if (cur == 4)//black king
				return 4;
			else if (cur == 5)//red dia
				return 5;
			
			return 6;			//black dia
		}

	}
	
	
	private static class CheckersData {

		static final int EMPTY = 0;
		static final int RED = 1;
		static final int RED_KING = 2;
		static final int BLACK = 3;
		static final int BLACK_KING = 4;
		static final int RED_DIA = 5;
		static final int BLACK_DIA = 6;//plus

		int[][] board; // board[r][c]

		CheckersData() {
			board = new int[8][8];
			setUpGame();
		}

		void setUpGame() {
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if ((row == 1) && (col > 0 && col < 7)) {
						board[row][col] = BLACK_DIA;
					} else if ((row == 2) && (col > 0 && col < 7)) {
						board[row][col] = BLACK;
					} else if ((row == 5) && (col > 0 && col < 7)) {
						board[row][col] = RED;
					} else if ((row == 6) && (col > 0 && col < 7)) {
						board[row][col] = RED_DIA;
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
          //move
		void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
			 AudioClip move = new AudioClip(getClass().getResource("move.mp3").toString());
			board[toRow][toCol] = board[fromRow][fromCol];
			move.play();
			
			//if(changer == false) {
			if (pieceAt(fromRow, fromCol) == RED_DIA) {
				board[toRow][toCol] = RED;
			}
			if (pieceAt(fromRow, fromCol) == BLACK_DIA) {
				board[toRow][toCol] = BLACK;

			}
			if (pieceAt(fromRow, fromCol) == RED) {
				board[toRow][toCol] = RED_DIA;
			}
			if (pieceAt(fromRow, fromCol) == BLACK) {
				board[toRow][toCol] = BLACK_DIA;
			}
			
			board[fromRow][fromCol] = EMPTY;
			//}
			
			//Killllllllll
			if (fromRow - toRow == 2 || fromRow - toRow == -2) {

				int jumpRow = (fromRow + toRow) / 2; // Row of the jumped piece.
				int jumpCol = (fromCol + toCol) / 2; // Column of the jumped piece.
				board[jumpRow][jumpCol] = EMPTY;
			}
			else if (fromCol - toCol == 2 || fromCol - toCol == -2) { //diago

				int jumpRow = (fromRow + toRow) / 2; // Row of the jumped piece.
				int jumpCol = (fromCol + toCol) / 2; // Column of the jumped piece.
				board[jumpRow][jumpCol] = EMPTY;
			}
			//transoformer en king
			if (toRow == 0 && (board[toRow][toCol] == RED || board[toRow][toCol] == RED_DIA) )
				board[toRow][toCol] = RED_KING;
			if (toRow == 7 && (board[toRow][toCol] == BLACK || board[toRow][toCol] == BLACK_DIA))
				board[toRow][toCol] = BLACK_KING;
		}

		CheckersMove[] getLegalMoves(int player) {

			if (player != RED && player != BLACK &&  player != BLACK_DIA && player != RED_DIA) {
				return null;
			}
			
			int playerKing;

			if (player == RED) {
				playerKing = RED_KING;

			} else {
				playerKing = BLACK_KING;

			}

			ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>(); // Moves will be stored in this list.
			
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if(player == RED_KING || player == BLACK_KING) {
						if (canJump(player, row, col, row + 1, col + 1, row +2, col + 2))
							moves.add(new CheckersMove(row, col, row + 2, col + 2));
						
						
						if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
							moves.add(new CheckersMove(row, col, row + 2, col - 2));
						if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
							moves.add(new CheckersMove(row, col, row -2, col - 2));
						if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
							moves.add(new CheckersMove(row, col, row - 2, col + 2));
						
						if (canJump(player, row, col, row, col + 1, row , col + 2))
							moves.add(new CheckersMove(row, col, row , col + 2));
						if (canJump(player, row, col, row , col - 1, row, col - 2))
							moves.add(new CheckersMove(row, col, row , col - 2));
						if (canJump(player, row, col, row + 1, col, row+2, col))
							moves.add(new CheckersMove(row, col, row + 2, col ));
						if (canJump(player, row, col, row - 1, col, row-2, col))
							moves.add(new CheckersMove(row, col, row - 2, col ));
						
						
					}
					
					if(player == BLACK) {
						pla = 2;
						
						
					if (board[row][col] == BLACK || board[row][col] == playerKing) {//croix
						if (canJump(player, row, col, row + 1, col + 1, row +2, col + 2))
							moves.add(new CheckersMove(row, col, row + 2, col + 2));
						
						
						if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
							moves.add(new CheckersMove(row, col, row + 2, col - 2));
						if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
							moves.add(new CheckersMove(row, col, row -2, col - 2));
						if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
							moves.add(new CheckersMove(row, col, row - 2, col + 2));
					
					}
					
					if (board[row][col] == BLACK_DIA || board[row][col] == playerKing) {
						
						if (canJump(player, row, col, row, col + 1, row , col + 2))
							moves.add(new CheckersMove(row, col, row , col + 2));
						if (canJump(player, row, col, row , col - 1, row, col - 2))
							moves.add(new CheckersMove(row, col, row , col - 2));
						if (canJump(player, row, col, row + 1, col, row+2, col))
							moves.add(new CheckersMove(row, col, row + 2, col ));
						if (canJump(player, row, col, row - 1, col, row-2, col))
							moves.add(new CheckersMove(row, col, row - 2, col ));
					}
				}
					if(player == RED) {
						pla =1;
						
						if (board[row][col] == RED || board[row][col] == playerKing) {
							if (canJump(player, row, col, row - 1, col - 1, row-2 , col - 2))
								moves.add(new CheckersMove(row, col, row-2 , col - 2));
							if (canJump(player, row, col, row - 1, col + 1, row-2, col + 2))
								moves.add(new CheckersMove(row, col, row- 2, col + 2));
							if (canJump(player, row, col, row + 1, col + 1, row+2 , col + 2))
								moves.add(new CheckersMove(row, col, row+2 , col + 2));
							if (canJump(player, row, col, row + 1, col - 1, row+2, col - 2))
								moves.add(new CheckersMove(row, col, row+ 2, col - 2));
						}
						
						if (board[row][col] == RED_DIA || board[row][col] == playerKing) {
							
							if (canJump(player, row, col, row , col - 1, row , col - 2))
								moves.add(new CheckersMove(row, col, row , col - 2));
							if (canJump(player, row, col, row , col + 1, row, col +2 ))
								moves.add(new CheckersMove(row, col, row , col + 2));
							if (canJump(player, row, col, row - 1, col, row-2, col))
								moves.add(new CheckersMove(row, col, row - 2, col ));
							if (canJump(player, row, col, row + 1, col, row+2, col))
								moves.add(new CheckersMove(row, col, row + 2, col ));
						}
						
					
					}
					
					/*if (board[row][col] == player || board[row][col] == playerKing || board[row][col] == playerOrt) {
						
						if (canJump(player, row, col, row + 1, col, row + 2, col ))
							moves.add(new CheckersMove(row, col, row + 2, col));
						
						if (canJump(player, row, col, row , col + 1, row , col + 2))
							moves.add(new CheckersMove(row, col, row , col + 2));
						
						if (canJump(player, row, col, row, col - 1, row , col - 2))
							moves.add(new CheckersMove(row, col, row , col - 2));
						
						
						
					}*/
				}
			}

			
			
			if (moves.size() == 0) {
				for (int row = 0; row < 8; row++) {
					for (int col = 0; col < 8; col++) {
						//foisssss
						if (board[row][col] == playerKing)  {
							if (canMove(player, row, col, row , col + 1)) {
								moves.add(new CheckersMove(row, col, row , col + 1));
								
							}
							if (canMove(player, row, col, row , col - 1))
								moves.add(new CheckersMove(row, col, row , col-1 ));
							if (canMove(player, row, col, row + 1, col ))
								moves.add(new CheckersMove(row, col, row + 1, col ));
							
							if (canMove(player, row, col, row +1, col + 1))
								moves.add(new CheckersMove(row, col, row+1 , col + 1));
							if (canMove(player, row, col, row +1, col - 1))
								moves.add(new CheckersMove(row, col, row +1, col-1 ));
							
						
								if (canMove(player, row, col, row , col + 1))
									moves.add(new CheckersMove(row, col, row , col + 1));
								if (canMove(player, row, col, row , col - 1))
									moves.add(new CheckersMove(row, col, row , col-1 ));
								if (canMove(player, row, col, row - 1, col ))
									moves.add(new CheckersMove(row, col, row - 1, col));
								
								if (canMove(player, row, col, row-1 , col + 1))
									moves.add(new CheckersMove(row, col, row-1 , col + 1));
								if (canMove(player, row, col, row -1, col - 1))
									moves.add(new CheckersMove(row, col, row-1 , col-1 ));	
							
						}
						if(player == BLACK) {
							pla = 2;
						if (board[row][col] == BLACK || board[row][col] == playerKing)  {
							if (canMove(player, row, col, row , col + 1)) {
								moves.add(new CheckersMove(row, col, row , col + 1));
								
							}
							if (canMove(player, row, col, row , col - 1))
								moves.add(new CheckersMove(row, col, row , col-1 ));
							if (canMove(player, row, col, row + 1, col ))
								moves.add(new CheckersMove(row, col, row + 1, col ));
						}if (board[row][col] == BLACK_DIA || board[row][col] == playerKing)  {
								if (canMove(player, row, col, row +1, col + 1))
									moves.add(new CheckersMove(row, col, row+1 , col + 1));
								if (canMove(player, row, col, row +1, col - 1))
									moves.add(new CheckersMove(row, col, row +1, col-1 ));
							
							}
						
						
							
							
						}
						if(player == RED) {
							//plusssss
							pla =1;
							if (board[row][col] == RED || board[row][col] == playerKing)  {
							if (canMove(player, row, col, row , col + 1))
								moves.add(new CheckersMove(row, col, row , col + 1));
							if (canMove(player, row, col, row , col - 1))
								moves.add(new CheckersMove(row, col, row , col-1 ));
							if (canMove(player, row, col, row - 1, col ))
								moves.add(new CheckersMove(row, col, row - 1, col));
						}
							
							// asepareerrr 
							if (board[row][col] == RED_DIA || board[row][col] == playerKing)  {
								if (canMove(player, row, col, row-1 , col + 1))
									moves.add(new CheckersMove(row, col, row-1 , col + 1));
								if (canMove(player, row, col, row -1, col - 1))
									moves.add(new CheckersMove(row, col, row-1 , col-1 ));
								
								
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
//modiiiiiifier
		/*CheckersMove[] getLegalJumpsFrom(int player, int row, int col) {
			if (player != RED && player != BLACK)
				return null;
			int playerKing; // The constant representing a King belonging to player.
			if (player == RED)
				playerKing = RED_KING;
			else
				playerKing = BLACK_KING;
			
			ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>(); // The legal jumps will be stored in this
			// a modifierrrrrrrrrrrrrrrrrrrrrrrr																// list.
			if (board[row][col] == player || board[row][col] == playerKing) {
				if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2)) {
					moves.add(new CheckersMove(row, col, row + 2, col + 2));System.out.println("cocooooooooooooooooooooooooooooooooooo");}
						movementsMsgs.setText(new CheckersMove(row, col, row + 2, col + 2).toString());
				
				if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
					moves.add(new CheckersMove(row, col, row - 2, col + 2));
				if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
					moves.add(new CheckersMove(row, col, row + 2, col - 2));
				if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
					moves.add(new CheckersMove(row, col, row - 2, col - 2));
			}
			if (moves.size() == 0)
				return null;
			else {
				CheckersMove[] moveArray = new CheckersMove[moves.size()];
				for (int i = 0; i < moves.size(); i++)
					moveArray[i] = moves.get(i);
				return moveArray;
			}
		}*/
		CheckersMove[] getLegalJumpsFrom(int player, int row, int col) {
			if (player != RED && player != BLACK)
				return null;
			int playerKing; // The constant representing a King belonging to player.
			if (player == RED)
				playerKing = RED_KING;
			else
				playerKing = BLACK_KING;
			
			ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>(); // The legal jumps will be stored in this
			// a modifierrrrrrrrrrrrrrrrrrrrrrrr																// list.
			if(player == RED_KING || player == BLACK_KING) {
				if (canJump(player, row, col, row + 1, col + 1, row +2, col + 2))
					moves.add(new CheckersMove(row, col, row + 2, col + 2));
				
				
				if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
					moves.add(new CheckersMove(row, col, row + 2, col - 2));
				if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
					moves.add(new CheckersMove(row, col, row -2, col - 2));
				if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
					moves.add(new CheckersMove(row, col, row - 2, col + 2));
				
				if (canJump(player, row, col, row, col + 1, row , col + 2))
					moves.add(new CheckersMove(row, col, row , col + 2));
				if (canJump(player, row, col, row , col - 1, row, col - 2))
					moves.add(new CheckersMove(row, col, row , col - 2));
				if (canJump(player, row, col, row + 1, col, row+2, col))
					moves.add(new CheckersMove(row, col, row + 2, col ));
				if (canJump(player, row, col, row - 1, col, row-2, col))
					moves.add(new CheckersMove(row, col, row - 2, col ));
				
				
			}
			
			if(player == BLACK) {
				pla = 2;
				
				
			if (board[row][col] == BLACK || board[row][col] == playerKing) {//croix
				if (canJump(player, row, col, row + 1, col + 1, row +2, col + 2))
					moves.add(new CheckersMove(row, col, row + 2, col + 2));
				
				
				if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
					moves.add(new CheckersMove(row, col, row + 2, col - 2));
				if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
					moves.add(new CheckersMove(row, col, row -2, col - 2));
				if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
					moves.add(new CheckersMove(row, col, row - 2, col + 2));
			
			}
			
			if (board[row][col] == BLACK_DIA || board[row][col] == playerKing) {
				
				if (canJump(player, row, col, row, col + 1, row , col + 2))
					moves.add(new CheckersMove(row, col, row , col + 2));
				if (canJump(player, row, col, row , col - 1, row, col - 2))
					moves.add(new CheckersMove(row, col, row , col - 2));
				if (canJump(player, row, col, row + 1, col, row+2, col))
					moves.add(new CheckersMove(row, col, row + 2, col ));
				if (canJump(player, row, col, row - 1, col, row-2, col))
					moves.add(new CheckersMove(row, col, row - 2, col ));
			}
		}
			if(player == RED) {
				pla =1;
				
				if (board[row][col] == RED || board[row][col] == playerKing) {
					if (canJump(player, row, col, row - 1, col - 1, row-2 , col - 2))
						moves.add(new CheckersMove(row, col, row-2 , col - 2));
					if (canJump(player, row, col, row - 1, col + 1, row-2, col + 2))
						moves.add(new CheckersMove(row, col, row- 2, col + 2));
					if (canJump(player, row, col, row + 1, col + 1, row+2 , col + 2))
						moves.add(new CheckersMove(row, col, row+2 , col + 2));
					if (canJump(player, row, col, row + 1, col - 1, row+2, col - 2))
						moves.add(new CheckersMove(row, col, row+ 2, col - 2));
				}
				
				if (board[row][col] == RED_DIA || board[row][col] == playerKing) {
					
					if (canJump(player, row, col, row , col - 1, row , col - 2))
						moves.add(new CheckersMove(row, col, row , col - 2));
					if (canJump(player, row, col, row , col + 1, row, col +2 ))
						moves.add(new CheckersMove(row, col, row , col + 2));
					if (canJump(player, row, col, row - 1, col, row-2, col))
						moves.add(new CheckersMove(row, col, row - 2, col ));
					if (canJump(player, row, col, row + 1, col, row+2, col))
						moves.add(new CheckersMove(row, col, row + 2, col ));
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
			if(board[r1][c1] == RED ||board[r1][c1] == RED_DIA ||board[r1][c1] == BLACK ||board[r1][c1] == BLACK_DIA) {
				if (board[r2][c2] == RED_KING || board[r2][c2] == BLACK_KING)
					return false;
			}
			
				if(board[r1][c1] == RED_KING && board[r2][c2] == BLACK_KING) {
					
					  return true;
				   	
				}
				
			
				if (board[r1][c1] == BLACK_KING && board[r2][c2] == RED_KING) {
					return true;
				}
			
			
			if (player == RED) {
				//if (board[r1][c1] == RED && r3 > r1)
					//return false;
				// Regular red piece can only move up.
				if (board[r2][c2] != BLACK && board[r2][c2] != BLACK_KING && board[r2][c2] != BLACK_DIA)
					return false;
				// There is no black piece to jump.
				
				return true; // The jump is legal.
			}else {
				//if (board[r1][c1] == BLACK && r3 < r1)
				//	return false; // Regular black piece can only move downn.
				if (board[r2][c2] != RED && board[r2][c2] != RED_KING & board[r2][c2] != RED_DIA)
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

			if (player == RED) {
				if (board[r1][c1] == RED && r2 > r1) {
					return false; // Regular red piece can only move down.
				}
				return true; // The move is legal.
			} else {
				if (board[r1][c1] == BLACK && r2 < r1) {
					return false;
				}
				return true; // The move is legal.
			}

		
		
			
		}

	}

}
