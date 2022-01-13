package jeuDesDames;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Piece extends Application {

   public static void main(String[] args) {
       launch(args);
   }
 
   @Override
   public void start(Stage primaryStage) {
       primaryStage.setTitle("Drawing Operations Test");
       Group root = new Group();
       Canvas canvas = new Canvas(300, 250);
       GraphicsContext gc = canvas.getGraphicsContext2D();
       drawShapes(gc);
       root.getChildren().add(canvas);
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
   }

   private void drawShapes(GraphicsContext gc) {
	   Image iv = new Image(getClass().getResource("black_plus.png").toString());

             gc.drawImage(iv,10,10);

       
   }


}