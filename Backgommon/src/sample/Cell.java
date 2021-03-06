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

    public boolean checkAdd (boolean type, int offset){
        if(offset==0) {
            if (piece_n == 0) {

                return true;

            } else if (type == this.type) {

                return true;
            } else if (piece_n == 1) {

                return true;
            } else {
                return false;
            }
        } else {

            return true;

        }

    }

    public void removePiece(){
        pieces.remove(piece_n-1);
        piece_n--;
    }

    public void move_from (Piece piece){

        int remove=pieces.indexOf(piece);
        pieces.remove(remove);
        piece_n--;

    }
}
