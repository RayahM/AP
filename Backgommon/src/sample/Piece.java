package sample;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static sample.Controller.*;


public class Piece extends StackPane {
     int x;
     int y;
     boolean type;
     int num;

     public Piece(boolean type, int x, int y, int num) {
          this.type = type;
          this.x = x;
          this.y = y;
          this.num = num;

       //   private double mousex, mousey;


          relocate(x * tile + annotation_x, y * tile + annotation_y);


          Circle mypiece = new Circle(tile * 0.3);
          mypiece.setFill(type ? Color.valueOf("#392613") : Color.valueOf("#dfbe9f"));
          mypiece.setStroke(Color.valueOf("#383838"));
          mypiece.setStrokeWidth(0.02 * tile);
          mypiece.setTranslateX(tile * 0.15);
          mypiece.setTranslateY(tile * 0.25);

          Circle annot = new Circle(tile * 0.2);
          mypiece.setStroke(Color.valueOf("#000000"));
          mypiece.setStrokeWidth(0.03 * tile);
          mypiece.setTranslateX(tile * 0.15);
          mypiece.setTranslateY(tile * 0.25);

          //getChildren().addAll(mypiece);


         /* setOnMousePressed (e ->{
               mousex=e.getSceneX();
               mousey=e.getSceneY();
          });

          setOnMouseDragged(e ->{
               relocate(  e.getSceneX()-mousex+ this.x*tile ,e.getSceneY()-mousey+this.y*tile );
          });
      */


     }
}