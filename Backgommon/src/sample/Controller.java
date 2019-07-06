package sample;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
   @FXML
   private AnchorPane boardPane;

   public Cell[] board_cell;
   boolean actionDone;
   static double tile_x = 37.5;
   static double tile_y = 32;
   static double annotation_x=40;
   static double annotation_y=-5;
   static double middle = 40;
   static double offset_y = 548;


    double layoutx;
    double layouty;


   private Image imageDice1 = new Image(getClass().getResourceAsStream("spritesheet.png"));
    private Image imageDice2 = new Image(getClass().getResourceAsStream("spritesheetR.png"));
   private int spriteColumns = 3;
   private int spriteCounts = 6;
   private int spriteOffsetX = 0;
   private int spriteOffsetY = 0;
   private int spriteWidth = 67;
   private int spriteHeight = 70;


    @FXML
    void rollDice(MouseEvent event) {
        ImageView imageViewDice1 = new ImageView(imageDice1);
        imageViewDice1.setTranslateX(350);
        imageViewDice1.setTranslateY(260);

        ImageView imageViewDice2 = new ImageView(imageDice2);
        imageViewDice2.setTranslateX(440);
        imageViewDice2.setTranslateY(260);
        imageViewDice1.setViewport(new Rectangle2D(spriteOffsetX,spriteOffsetY,spriteWidth,spriteHeight));
        imageViewDice2.setViewport(new Rectangle2D(spriteOffsetX,spriteOffsetY,spriteWidth,spriteHeight));
        Animation animationDice1 = new SpriteAnimation(
                imageViewDice1,
                Duration.millis(1500),
                spriteCounts,
                spriteColumns,
                spriteOffsetX,
                spriteOffsetY,
                spriteWidth,
                spriteHeight
        );

        Animation animationDice2 = new SpriteAnimation(
                imageViewDice2,
                Duration.millis(1500),
                spriteCounts,
                spriteColumns,
                spriteOffsetX,
                spriteOffsetY,
                spriteWidth,
                spriteHeight
        );

        animationDice1.setCycleCount(1);
        animationDice1.play();
        animationDice2.setCycleCount(1);
        animationDice2.play();
        animationDice1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boardPane.getChildren().remove(imageViewDice1);
                boardPane.getChildren().remove(imageViewDice2);
            }
        });
        boardPane.getChildren().addAll(imageViewDice1,imageViewDice2);
    }

   @Override
   public void initialize(URL location, ResourceBundle resources){
   int i;
   int x1=1;
   int x2=7;
   int k1=1;
   int k2=1;
 board_cell = new Cell[24];
 for(i=0;i<24;i++){
     board_cell[i] = new Cell();
 }



   for(i=1;i<6;i++){
     boardPane.getChildren().add(pieceMake(true,x1,i,k1));
     actionDone=board_cell[x1-1].addPiece(true,k1);
     k1++;
     boardPane.getChildren().add(pieceMake(true,x2,-1*i,k1));
     actionDone=board_cell[24-x2].addPiece(true,k1);
     k1++;
     boardPane.getChildren().add(pieceMake(false,x2,i,k2));
     actionDone=board_cell[x2-1].addPiece(false,k2);
     k2++;
     boardPane.getChildren().add(pieceMake(false,x1,-1*i,k2));
     actionDone=board_cell[24-x1].addPiece(false,k2);
     k2++;

   }

   x1=5;
      for(i=1;i<4;i++) {

         boardPane.getChildren().add(pieceMake(false,x1,i,k2));
         actionDone=board_cell[x1].addPiece(false,k2);
         k1++;

         boardPane.getChildren().add(pieceMake(true,x1,-1*i,k1));
         actionDone=board_cell[24-x1].addPiece(true,k1);
         k1++;


      }

      x2=12;
      for(i=1;i<3;i++) {
         boardPane.getChildren().add(pieceMake(true,x2,i,k1));
          actionDone=board_cell[x2].addPiece(true,k1);
         k1++;
         boardPane.getChildren().add(pieceMake(false,x2,-1*i,k2));
          actionDone=board_cell[24-x2].addPiece(false,k2);
         k2++;
      }
   }

 private Piece pieceMake(boolean type,int x,int y,int num){
   Piece piece = new Piece(type,x,y,num,board_cell);

   piece.setOnMouseReleased(e -> {
     layoutx=piece.getLayoutX();

      layouty=piece.getLayoutY();

   });

   return piece;
 }
}
