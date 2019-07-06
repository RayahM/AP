package sample;

import java.util.ArrayList;

public class Cell {

    boolean type;
    int piece_n;
    ArrayList<Integer> piece=  new ArrayList<>();

    public Cell (){

        this.piece_n=0;

    }

    public boolean addPiece (boolean type,int piece_num){
        if(piece_n==0){
            piece.add(piece_num);
            piece_n++;
            this.type=type;
            return true;
        }

        else if(type==this.type){
            piece.add(piece_num);
            piece_n++;

            return true;
        }
        else if( piece_n==1){
            this.type=type;
            //// mohre ra bezanad
            piece.clear();
            piece.add(piece_num);

            return true;
        }

        else{
            return false;
        }

    }

    public void move_from (int piece_num){

        int remove=piece.indexOf(piece_num);
        piece.remove(remove);
        piece_n--;

    }
}
