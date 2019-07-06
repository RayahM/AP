<<<<<<< HEAD
package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Model {

    public int randomDice(){

        int max = 6;
        int min = 1;
        int diceRange = max - min +1;

        int rand = (int)(Math.random()*diceRange) + min;
        return rand;
    }

    public Image getImage(String address){
        return new Image(getClass().getResourceAsStream(address));
    }

    public void moveDetector(int dice1, int dice2, boolean turn , Cell[] board){
                int sumDice = dice1 + dice2;


            for(int i=0;i<24;i++){
                if(board[i].piece_n >0){
                    if(board[i].type == turn){
                        if(turn){
                            int colNum = i- sumDice;
                            if(colNum<0){
                                colNum += 24;
                            }
                            if(board[colNum].checkAdd(turn)){

                                Piece piece = board[i].pieces.get(board[i].piece_n-1);
                                piece.canMove();
                                Controller.hintMove.put(i,new ArrayList<Integer>());
                                Controller.hintMove.get(i).add(colNum);

                            }


                        } else {

                            int colNum = sumDice + i;
                            if(colNum>23){
                                colNum -= 24;
                            }
                            if(board[colNum].checkAdd(turn)){

                                Piece piece = board[i].pieces.get(board[i].piece_n-1);
                                piece.canMove();
                                Controller.hintMove.put(i,new ArrayList<Integer>());
                                Controller.hintMove.get(i).add(colNum);

                            }
                        }
                    }
                }
            }


    }

}
=======
package sample;

import javafx.scene.image.Image;

public class Model {

    public int randomDice(){

        int max = 6;
        int min = 1;
        int diceRange = max - min +1;

        int rand = (int)(Math.random()*diceRange) + min;
        return rand;
    }

    public Image getImage(String address){
        return new Image(getClass().getResourceAsStream(address));
    }

}
>>>>>>> 37ea10325f8a72f8fbc3c0b9b1d532551c0eec16
