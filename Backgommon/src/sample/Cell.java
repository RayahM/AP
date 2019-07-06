<<<<<<< HEAD
package sample;

import java.util.ArrayList;

public class Cell {

    boolean type;
    int piece_n;
    ArrayList<Piece> pieces=  new ArrayList<>();
    Piece killed=null;

    public Cell (){

        this.piece_n=0;

    }

    public boolean addPiece (boolean type,Piece addPiece){
        if(piece_n==0){
            pieces.add(addPiece);
            piece_n++;
            this.type=type;
            return true;
        }

        else if(type==this.type){
            pieces.add(addPiece);
            piece_n++;

            return true;
        }
        else if( piece_n==1){
            this.type=type;
            killed= pieces.get(0);
            pieces.clear();
            pieces.add(addPiece);

            return true;
        }

        else{
            return false;
        }

    }

    public boolean checkAdd (boolean type){
        if(piece_n==0){

            return true;
        }

        else if(type==this.type){

            return true;
        }
        else if( piece_n==1){

            return true;
        }

        else{
            return false;
        }

    }

    public void move_from (Piece piece){

        int remove=pieces.indexOf(piece);
        pieces.remove(remove);
        piece_n--;

    }
}
=======
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
>>>>>>> 37ea10325f8a72f8fbc3c0b9b1d532551c0eec16
