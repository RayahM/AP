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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    Model model = new Model();


    double layoutx;
    double layouty;



   private int spriteColumns = 3;
   private int spriteCounts = 6;
   private int spriteOffsetX = 0;
   private int spriteOffsetY = 0;
   private int spriteWidth = 67;
   private int spriteHeight = 70;
    private int dice1;
    private int dice2;
    static int player1TimeLimit;
    static int player2TimeLimit;
    static int roundsOfPlay;
    private boolean turn = false;

    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

    @FXML
    void rollDice(MouseEvent event) {
        ImageView imageViewDice1 = new ImageView(model.getImage("spritesheet.png"));
        imageViewDice1.setTranslateX(350);
        imageViewDice1.setTranslateY(260);

        ImageView imageViewDice2 = new ImageView(model.getImage("spritesheetR.png"));
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
                setDice1(model.randomDice());
                setDice2(model.randomDice());
                ImageView diceI1 = new ImageView(model.getImage("Dice_"+getDice1()+".png"));
                diceI1.setTranslateX(350);
                diceI1.setTranslateY(260);
                ImageView diceI2 = new ImageView(model.getImage("Dice_"+getDice2()+".png"));
                diceI2.setTranslateX(440);
                diceI2.setTranslateY(260);
                boardPane.getChildren().addAll(diceI1,diceI2);
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
     boardPane.getChildren().add(pieceMake(false ,x1,-1*i,k2));
     actionDone=board_cell[24-x1].addPiece(false ,k2);
     k2++;

   }

   x1=5;
      for(i=1;i<4;i++) {

         boardPane.getChildren().add(pieceMake(false,x1,i,k2));
         actionDone=board_cell[x1-1].addPiece(false,k2);
         k1++;

         boardPane.getChildren().add(pieceMake(true,x1,-1*i,k1));
         actionDone=board_cell[24-x1].addPiece(true,k1);
         k1++;


      }

      x2=12;
      for(i=1;i<3;i++) {
         boardPane.getChildren().add(pieceMake(true,x2,i,k1));
          actionDone=board_cell[x2-1].addPiece(true,k1);
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

      piece.move(findx(layoutx),findy(layouty),board_cell);

   });

   return piece;
 }

 public int findx(double xlayout){
    int x;

        if(xlayout<283.75){
           x= (int)((xlayout-40+18.75)/37.5);
           if(x<1)
               x=0;
        }

        else if(xlayout<342.5 || xlayout>548.75){
            x=0;
        }

        else{
            x= (int)((xlayout-80+18.75)/37.5);
        }
    return x;
 }

    public int findy(double ylayout){
        int y;

        if(ylayout<300 && ylayout>15){
            y= 1;
        }

        else if(ylayout>300 && ylayout<548){
            y=-1;
        }

        else{

            y=0;
        }
        return y;
    }

    public void initializeClk(){
        StackPane[] stackSecond = new StackPane[2];
        stackSecond[0]= new StackPane();
        Circle[] circ = new Circle[2];
        circ[0]= new Circle(16, Color.BISQUE);
        stackSecond[0].getChildren().add(circ[0]);
        Text[] text = new Text[3];
        System.out.println(player1TimeLimit);
        text[0] = new Text((Integer.toString(player1TimeLimit)));
        text[0].setFont(Font.loadFont(getClass().getResource("Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(),20));
        text[0].setFill(Color.DARKGREEN);
        stackSecond[0].getChildren().add(text[0]);
        stackSecond[0].relocate(780,450);
        boardPane.getChildren().add(stackSecond[0]);

        Text[] textSep = new Text[2];
        textSep[0] = new Text((":"));
        textSep[0].setFont(Font.loadFont(getClass().getResource("Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(),20));
        textSep[0].setFill(Color.BISQUE);
        textSep[0].setTranslateX(773);
        textSep[0].setTranslateY(470);
        boardPane.getChildren().add(textSep[0]);

        StackPane[] stackMinute = new StackPane[2];
        stackMinute[0] = new StackPane();
        Circle[] circMinute = new Circle[2];
        circMinute[0] = new Circle(16,Color.BISQUE);
        stackMinute[0].getChildren().add(circMinute[0]);
        Text[] textMinute = new Text[2];
        textMinute[0] = new Text((Integer.toString((player1TimeLimit/60)%60)));
        textMinute[0].setFont(Font.loadFont(getClass().getResource("Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(),20));
        textMinute[0].setFill(Color.DARKGREEN);
        stackMinute[0].getChildren().add(textMinute[0]);
        stackMinute[0].relocate(740,450);
        boardPane.getChildren().add(stackMinute[0]);

        Text[] gameTime = new Text[2];
        gameTime[0] = new Text(("Player1 Time:"));
        gameTime[0].setFont(Font.loadFont(getClass().getResource("Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(),14));
        gameTime[0].setFill(Color.GOLD);
        gameTime[0].setTranslateX(637);
        gameTime[0].setTranslateY(470);
        boardPane.getChildren().add(gameTime[0]);


        stackSecond[1]= new StackPane();
        circ[1]= new Circle(16, Color.BISQUE);
        stackSecond[1].getChildren().add(circ[1]);
        text[1] = new Text((Integer.toString(player2TimeLimit)));
        text[1].setFont(Font.loadFont(getClass().getResource("Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(),20));
        text[1].setFill(Color.DARKGREEN);
        stackSecond[1].getChildren().add(text[1]);
        stackSecond[1].relocate(780,350);
        boardPane.getChildren().add(stackSecond[1]);


        textSep[1] = new Text((":"));
        textSep[1].setFont(Font.loadFont(getClass().getResource("Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(),20));
        textSep[1].setFill(Color.BISQUE);
        textSep[1].setTranslateX(773);
        textSep[1].setTranslateY(370);
        boardPane.getChildren().add(textSep[1]);


        stackMinute[1] = new StackPane();
        circMinute[1] = new Circle(16,Color.BISQUE);
        stackMinute[1].getChildren().add(circMinute[1]);
        textMinute[1] = new Text((Integer.toString((player2TimeLimit/60)%60)));
        textMinute[1].setFont(Font.loadFont(getClass().getResource("Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(),20));
        textMinute[1].setFill(Color.DARKGREEN);
        stackMinute[1].getChildren().add(textMinute[1]);
        stackMinute[1].relocate(740,350);
        boardPane.getChildren().add(stackMinute[1]);

        gameTime[1] = new Text(("Player2 Time:"));
        gameTime[1].setFont(Font.loadFont(getClass().getResource("Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(),14));
        gameTime[1].setFill(Color.GOLD);
        gameTime[1].setTranslateX(637);
        gameTime[1].setTranslateY(370);
        boardPane.getChildren().add(gameTime[1]);



    }

}
