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
          double location_x;
          double location_y;

          if(x<7)
               location_x=x*tile_x+annotation_x+middle;
          else
               location_x=x*tile_x+annotation_x;

          if(0<y )
               location_y=y*tile_y+annotation_y;
          else
               location_y=y*tile_y+offset_y;

         relocate(location_x , location_y);

          Circle mypiece = new Circle(11);
          mypiece.setFill(type ? Color.valueOf("#4d3319") : Color.valueOf("#dfbe9f"));
          mypiece.setStroke(Color.valueOf("#271a0c"));
          mypiece.setStrokeWidth(0.1 );
          //mypiece.setTranslateX(tile * 0.15);
         // mypiece.setTranslateY(tile * 0.25);

          Circle annot = new Circle(15);
          annot.setStroke(Color.valueOf("#000000"));
          annot.setStrokeWidth(0.15);
          //mypiece.setTranslateX(tile * 0.15);
          //mypiece.setTranslateY(tile * 0.25);

          getChildren().addAll(annot,mypiece);


         /* setOnMousePressed (e ->{
               mousex=e.getSceneX();
               mousey=e.getSceneY();
          });

          setOnMouseDragged(e ->{
               relocate(  e.getSceneX()-mousex+ this.x*tile ,e.getSceneY()-mousey+this.y*tile );
          });
      */


     }

////////
    /* public void move( int newx,int newy){
          this.x=newx;
          this.y=newy;
          relocate(x*,y*);

          return;}*/
}