package sample;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

import static sample.Controller.*;


public class Piece extends StackPane {
     int x;
     int y;
     int num;
    boolean type;
    Cell home ;
    int currentCell;
   boolean canSelect = false;
    double location_x;
    double location_y;
    int possibleDestCell1=-1;
    int possileDestCell2=-1;

    private double mousex, mousey;

    Circle mypiece = new Circle(16);
    Circle annot = new Circle(9);

     public Piece(boolean type, int x, int y, int num,Cell[] board) {
          this.type = type;
          this.x = x;
          this.y = y;
          this.num = num;


          if(y>0) {
              home = board[x - 1];
              currentCell = x-1;
          }
          else {
              home = board[24 - x];
              currentCell = 24-x;
          }



          if(x>6)
               location_x=x*tile_x+annotation_x+middle;
          else
               location_x=x*tile_x+annotation_x;

          if(0<y )
               location_y=y*tile_y+annotation_y;
          else
               location_y=y*tile_y+offset_y;

         relocate(location_x , location_y);


          mypiece.setFill(!type ? Color.valueOf("#4d3319") : Color.valueOf("#dfbe9f"));
          mypiece.setStroke(Color.valueOf("#140d06"));
          mypiece.setStrokeWidth(0.15 );



          annot.setFill(!type ? Color.valueOf("#3b2712") : Color.valueOf("#dbb48a"));
          annot.setStroke(Color.valueOf("#140d06"));
          annot.setStrokeWidth(0.17);


          getChildren().addAll(mypiece,annot);


         setOnMousePressed (e ->{
               mousex=e.getSceneX();
               mousey=e.getSceneY();
               if(moveLevel!=MoveLevel.done  && type== Controller.turn) {
                   if (canSelect) {
                       if (moveLevel != MoveLevel.maxMin2 && moveLevel != MoveLevel.minMax2) {
                           model.disStroke(board);
                       } else {
                           model.disStrokeMove2(board, undo[0]);
                       }
                       mypiece.setStroke(Color.valueOf("#008000"));
                       mypiece.setStrokeWidth(3);


                   }
               }
          });

          setOnMouseDragged(e ->{
               relocate(  e.getSceneX()-mousex+location_x ,e.getSceneY()-mousey+location_y);
          });



     }


     public void canMove (int possibleDestCell1,int possileDestCell2){


        mypiece.setStroke(Color.valueOf("#ff6600"));
        mypiece.setStrokeWidth(2);
        canSelect = true;
        this.possibleDestCell1 = possibleDestCell1;
        this.possileDestCell2 = possileDestCell2;



     }
     public void disStroke(){
         mypiece.setStroke(Color.valueOf("#140d06"));
         mypiece.setStrokeWidth(0.15 );
     }



     public void move( int newx,int newy,Cell[] board) {
         if (newy == 0 || newx == 0) {
             relocate(location_x, location_y);
         }

         else {

             if (newy > 0) {
                 home = board[newx - 1];
                 newy = board[newx - 1].piece_n ;
             }
             else {
                 home = board[24 - newx];
                 newy = (board[24 - newx].piece_n )*-1;
             }

         if (newx > 6) {
             location_x = newx * tile_x + annotation_x + middle;
         }

         else {
             location_x = newx * tile_x + annotation_x;
         }

         if (0 < newy) {
             location_y = newy * tile_y + annotation_y;
         }
         else {
             location_y = newy * tile_y + offset_y;
         }

         relocate(location_x, location_y);


     }
          return;}
}