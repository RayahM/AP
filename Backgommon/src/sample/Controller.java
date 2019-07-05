package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
   @FXML
   private AnchorPane boardPane;

   static double tile_x = 37;
   static double tile_y = 26;
   static double annotation_x=58;
   static double annotation_y=12;
   static double middle = 73;
   static double offset_y = 562;



   @Override
   public void initialize(URL location, ResourceBundle resources){
   int i;
   int x1=1;
   int x2=7;
   int k1=1;
   int k2=1;
   for(i=0;i<5;i++){
     boardPane.getChildren().add(pieceMake(true,x1,i,k1));
     k1++;
     boardPane.getChildren().add(pieceMake(true,x2,-1*i,k1));
     k1++;
     boardPane.getChildren().add(pieceMake(false,x2,i,k2));
     k2++;
     boardPane.getChildren().add(pieceMake(false,x1,-1*i,k2));
     k2++;

   }

   x1=5;
      for(i=0;i<3;i++) {
         boardPane.getChildren().add(pieceMake(true,x1,-1*i,k1));
         k1++;
         boardPane.getChildren().add(pieceMake(false,x1,i,k2));
         k2++;
      }

      x2=12;
      for(i=0;i<2;i++) {
         boardPane.getChildren().add(pieceMake(true,x2,i,k1));
         k1++;
         boardPane.getChildren().add(pieceMake(false,x2,-1*i,k2));
         k2++;
      }
   }

 private Piece pieceMake(boolean type,int x,int y,int num){
   Piece piece = new Piece(type,x,y,num);

   piece.setOnMouseReleased(e -> {
      double newx=piece.getLayoutX();

      double newy=piece.getLayoutY();

   });

   return piece;
 }
}
