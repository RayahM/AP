package sample;

import javafx.scene.image.Image;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Model {


    LinkedHashMap<Integer,ArrayList<Integer>> max = new LinkedHashMap<>();
    LinkedHashMap<Integer,ArrayList<Integer>> min = new LinkedHashMap<>();
    //LinkedHashMap<Integer,ArrayList<Integer>> maxMin = new LinkedHashMap<>();
    //LinkedHashMap<Integer,ArrayList<Integer>> minMax = new LinkedHashMap<>();

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

    public void moveDetector(int dice1, int dice2, boolean turn , Cell[] board ){
        int diceMax = dice1 > dice2 ? dice1 : dice2;
        int diceMin = dice1 < dice2 ? dice1 : dice2;


        boolean moveOut = canMoveOut(turn,board);

        if(!moveOut){
            for(int i=0;i<24;i++){
                if(board[i].piece_n>0 && board[i].type==turn) {

                    cellMoveDetectMax(diceMax, diceMin, turn, board, i);
                }
            }

            boolean maxState = false;
            for(Integer i : max.keySet()){

                maxState = maxState | !max.get(i).isEmpty();
            }

            if(!maxState){
                for(int i=0;i<24;i++){
                    if(board[i].piece_n>0 && board[i].type==turn) {

                        cellMoveDetectMin(diceMin, diceMax, turn, board, i);
                    }
                }
            }

            detectStroke(board,diceMax,diceMin,turn);




        }

    }

    public void disStroke(Cell[] board){
        if(min.isEmpty()){
            if(!max.isEmpty()) {
                for (Integer i : max.keySet()) {
                    if(!max.get(i).isEmpty()) {
                        board[i].pieces.get(board[i].piece_n - 1).disStroke();
                    }
                }
            }else {
                //TODO

            }
        } else {

            boolean minState = false;

            for(Integer i: min.keySet()){
                minState = minState | !min.get(i).isEmpty();
            }

            if(minState){

                for(Integer i: min.keySet()){
                    if(!min.get(i).isEmpty()){
                        board[i].pieces.get(board[i].piece_n - 1).disStroke();
                    }
                }

            } if(max.isEmpty()){
                for(Integer i: min.keySet()){
                    board[i].pieces.get(board[i].piece_n - 1).disStroke();
                }
            } else{

                for (Integer i : max.keySet()) {
                    board[i].pieces.get(board[i].piece_n - 1).disStroke();
                }
            }


        }
    }

    public void detectStroke(Cell[] board, int diceMax , int diceMin, boolean turn){
        int possibleDest1;
        int possibleDest2;
        if(min.isEmpty()){
            if(!max.isEmpty()) {
                boolean maxEmpty = false;
                for (Integer i : max.keySet()) {
                    if(!max.get(i).isEmpty()) {
                       maxEmpty = true;
                        if(turn) {
                            possibleDest1 =  i - diceMax;

                            if(possibleDest1<0 ){
                                possibleDest1 += 24;
                            }

                            if(max.get(i).contains(possibleDest1)){
                                possibleDest2 = possibleDest1 - diceMin;

                                if(possibleDest2 <0){
                                    possibleDest2 += 24;
                                }

                            }
                            else {
                                possibleDest2 = -1;
                            }
                        } else {

                            possibleDest1 =  i + diceMax;

                            if(possibleDest1>23 ){
                                possibleDest1 -= 24;
                            }

                            if(max.get(i).contains(possibleDest1)) {
                                possibleDest2 = possibleDest1 + diceMin;

                                if (possibleDest2 > 23) {
                                    possibleDest2 -= 24;
                                }
                            } else {
                                possibleDest2 = -1;
                            }

                        }
                        board[i].pieces.get(board[i].piece_n - 1).canMove(possibleDest1,possibleDest2);
                        Controller.moveLevel = MoveLevel.maxMin1;

                    }
                }
                if(!maxEmpty){
                    for (Integer i : max.keySet()) {
                        possibleDest2 = -1;
                        if(turn){
                            possibleDest1 = i - diceMax;

                            if(possibleDest1 <0){
                                possibleDest1 += 24;

                            }
                        } else {
                            possibleDest1 = i + diceMax;

                            if(possibleDest1 > 23){
                                possibleDest1 -= 24;
                            }
                        }
                        board[i].pieces.get(board[i].piece_n - 1).canMove(possibleDest1,possibleDest2);
                        Controller.moveLevel = MoveLevel.max;
                    }

                }
            }else {
                //TODO


            }
        } else {

            boolean minState = false;

            for(Integer i: min.keySet()){
                minState = minState | !min.get(i).isEmpty();
            }

            if(minState){

                for(Integer i: min.keySet()){
                    if(!min.get(i).isEmpty()){
                        if(turn) {
                            possibleDest1 =  i - diceMin;

                            if(possibleDest1<0 ){
                                possibleDest1 += 24;
                            }

                            if(min.get(i).contains(possibleDest1)){
                                possibleDest2 = possibleDest1 - diceMax;

                                if(possibleDest2 <0){
                                    possibleDest2 += 24;
                                }

                            }
                            else {
                                possibleDest2 = -1;
                            }
                        } else {

                            possibleDest1 =  i + diceMin;

                            if(possibleDest1>23 ){
                                possibleDest1 -= 24;
                            }

                            if(min.get(i).contains(possibleDest1)) {
                                possibleDest2 = possibleDest1 + diceMax;

                                if (possibleDest2 > 23) {
                                    possibleDest2 -= 24;
                                }
                            } else {
                                possibleDest2 = -1;
                            }

                        }
                        board[i].pieces.get(board[i].piece_n - 1).canMove(possibleDest1,possibleDest2);
                        Controller.moveLevel = MoveLevel.minMax1;
                    }
                }

            } if(max.isEmpty()){
                for(Integer i: min.keySet()){
                    possibleDest2 = -1;

                    if(turn){
                        possibleDest1 = i - diceMin;

                        if(possibleDest1 <0){
                            possibleDest1 += 24;

                        }
                    } else {
                        possibleDest1 = i + diceMin;

                        if(possibleDest1 > 23){
                            possibleDest1 -= 24;
                        }
                    }
                    board[i].pieces.get(board[i].piece_n - 1).canMove(possibleDest1,possibleDest2);
                    Controller.moveLevel = MoveLevel.min;
                }
            } else{

                for (Integer i : max.keySet()) {
                    possibleDest2 = -1;
                    if(turn){
                        possibleDest1 = i - diceMax;

                        if(possibleDest1 <0){
                            possibleDest1 += 24;

                        }
                    } else {
                        possibleDest1 = i + diceMax;

                        if(possibleDest1 > 23){
                            possibleDest1 -= 24;
                        }
                    }
                        board[i].pieces.get(board[i].piece_n - 1).canMove(possibleDest1,possibleDest2);
                       Controller.moveLevel = MoveLevel.max;
                }
            }


        }
    }

    public void cellMoveDetectMin(int diceMax, int diceMin, boolean turn, Cell[] board, int cellNum){
        int colNum;
        if(turn) {
            colNum = cellNum - diceMax;
            if(colNum<0){
                colNum += 24;
            }

            if(!(cellNum >=12 && colNum<=11)) {
                if (board[colNum].checkAdd(turn, 0)) {
                    min.put(cellNum,new ArrayList<>());
                    for(int i=0;i<24;i++){
                        if(board[i].type ==turn || i==colNum) {
                            if (i != cellNum || (i == cellNum && board[cellNum].piece_n > 1)) {
                                if (secondCellDetect(diceMin, turn, board, colNum, i)) {
                                    min.get(cellNum).add(i);
                                }
                            }
                        }
                    }
                }
            }



        }

        else {
            colNum = cellNum + diceMax;
            if(colNum>23){
                colNum -= 24;
            }

            if(!( (cellNum >=6 && cellNum<=11) && colNum>11)) {
                if (board[colNum].checkAdd(turn, 0)) {
                    min.put(cellNum,new ArrayList<>());
                    for(int i=0;i<24;i++){
                        if(board[i].type ==turn || i==colNum) {
                            if (i != cellNum || (i == cellNum && board[cellNum].piece_n > 1)) {
                                if (secondCellDetect(diceMin, turn, board, colNum, i)) {
                                    min.get(cellNum).add(i);
                                }
                            }
                        }
                    }
                }
            }



        }


    }

    public void cellMoveDetectMax(int diceMax,int diceMin, boolean turn, Cell[] board,int cellNum){
        int colNum;
        if(turn) {
            colNum = cellNum - diceMax;
            if(colNum<0){
                colNum += 24;
            }

            if(!(cellNum >=12 && colNum<=11)) {
                if (board[colNum].checkAdd(turn, 0)) {
                        max.put(cellNum,new ArrayList<>());
                        for(int i=0;i<24;i++){
                            if(board[i].type == turn || i==colNum) {
                                if (i != cellNum || (i == cellNum && board[cellNum].piece_n > 1)) {
                                    if (secondCellDetect(diceMin, turn, board, colNum, i)) {
                                        max.get(cellNum).add(i);
                                    }
                                }
                            }
                        }
                }
            }

        }


        else {
            colNum = cellNum + diceMax;
            if(colNum>23){
                colNum -= 24;
            }

            if(!((cellNum >=6 && cellNum<=11) && colNum>11)) {
                if (board[colNum].checkAdd(turn, 0)) {
                    max.put(cellNum,new ArrayList<>());
                    for(int i=0;i<24;i++){
                        if(board[i].type == turn || i==colNum){
                        if(i!=cellNum || (i==cellNum && board[cellNum].piece_n>1)) {
                            if (secondCellDetect(diceMin, turn, board, colNum, i)) {
                                max.get(cellNum).add(i);
                            }
                        }
                    }
                    }
                }
            }

        }
    }


    public boolean secondCellDetect(int diceMin, boolean turn , Cell[] board , int dest , int cellNum){
        int colNum;
        if(turn) {

            colNum = cellNum - diceMin;
            if (colNum < 0) {
                colNum += 24;
            }

            if (!(cellNum >= 12 && colNum <= 11)) {
                if (board[cellNum].piece_n>0 && colNum == dest) {
                    if (board[colNum].checkAdd(turn, 1)) {
                       return true;
                    }
                } else {
                    if (board[cellNum].piece_n>0 && board[colNum].checkAdd(turn,0 )) {
                       return true;
                    }
                }
                }
            }

        else {

            colNum = cellNum + diceMin;
            if (colNum > 23) {
                colNum -= 24;
            }

            if (!((cellNum >=6 && cellNum<=11)&& colNum > 11)) {
                if (board[cellNum].piece_n>0 && colNum == dest) {
                    if (board[colNum].checkAdd(turn, 1)) {
                        return true;
                    }
                } else {
                    if (board[cellNum].piece_n>0 && board[colNum].checkAdd(turn,0 )) {
                        return true;
                    }
                }
            }
        }
                return false;
            }







    public boolean canMoveOut(boolean turn, Cell[] boardCell){
        int count = 0;
         if(turn){

             for(int i=12;i<18;i++){
                 if(boardCell[i].type ){
                     count += boardCell[i].piece_n;
                 }
             }
             if(count==15){
                 return true;
             }

         } else {

             for(int i=6;i<12;i++){
                 if(!boardCell[i].type ){
                     count += boardCell[i].piece_n;
                 }
             }
             if(count==15){
                 return true;
             }
         }


         return false;
    }

    public Move checkMove1(int dice1,int dice2,boolean turn,int newX,int newY, Cell[] board, Piece piece){

        Move move = new Move();

        if(!piece.canSelect){
            move.setMovePermit(false);
            move.setNewX(0);
            move.setNewY(0);
            return move;
        } else{
           int nextCell;
           if(newY==0 || newX==0){
               int diceMax = dice1 > dice2 ? dice1 : dice2;
               int diceMin = dice1 < dice2 ? dice1 : dice2;
               move.setMovePermit(false);
               move.setNewX(0);
               move.setNewY(0);
               detectStroke(board,diceMax,diceMin,turn);
               return move;
           } else if(newY<0){
                nextCell = 24-newX;
            } else {
               nextCell = newX-1;
           }

           if(nextCell == piece.possibleDestCell1 || nextCell == piece.possileDestCell2){
               move.setMovePermit(true);
               if(board[nextCell].type != turn && board[nextCell].piece_n==1){
                   move.setMoveType(MoveType.attack);
                   move.setPiece(board[nextCell].pieces.get(0));
               } else{
                   move.setMoveType(MoveType.normalMove);
               }

               move.setNewX(newX);
               move.setNewY(newY);
               move.setCurrentCell(piece.currentCell);
               move.setNextCell(nextCell);
                return move;
           } else{
               int diceMax = dice1 > dice2 ? dice1 : dice2;
               int diceMin = dice1 < dice2 ? dice1 : dice2;
               move.setMovePermit(false);
               move.setNewX(0);
               move.setNewY(0);
               detectStroke(board,diceMax,diceMin,turn);
               return move;
           }


        }


    }


    public Move checkMove2(int dice,boolean turn,int newX,int newY, Cell[] board, Piece piece,int cell){

        Move move = new Move();

        if(!piece.canSelect){
            move.setMovePermit(false);
            move.setNewX(0);
            move.setNewY(0);
            return move;
        } else{
            int nextCell;
            if(newY==0 || newX==0){

                move.setMovePermit(false);
                move.setNewX(0);
                move.setNewY(0);
                move2Stroke(cell,dice,board,turn);
                return move;
            } else if(newY<0){
                nextCell = 24-newX;
            } else {
                nextCell = newX-1;
            }

            if(nextCell == piece.possibleDestCell1){
                move.setMovePermit(true);
                if(board[nextCell].type != turn && board[nextCell].piece_n==1){
                    move.setMoveType(MoveType.attack);
                    move.setPiece(board[nextCell].pieces.get(0));
                } else{
                    move.setMoveType(MoveType.normalMove);
                }

                move.setNewX(newX);
                move.setNewY(newY);
                move.setCurrentCell(piece.currentCell);
                move.setNextCell(nextCell);
                return move;
            } else{
                move.setMovePermit(false);
                move.setNewX(0);
                move.setNewY(0);
                move2Stroke(cell,dice,board,turn);
                return move;
            }


        }


    }



    public void move2Stroke(int cell,int dice,Cell[] board, boolean turn){
        ArrayList<Integer> arrayList;
        int possibleDestCell1 ;
        Cell cellSource;

        if(Controller.moveLevel==MoveLevel.minMax2) {
            arrayList = min.get(cell);
        }else{
            arrayList = max.get(cell);
        }

            for(int i=0;i<arrayList.size();i++){
                cellSource = board[arrayList.get(i)];
                if(turn){
                    possibleDestCell1 = arrayList.get(i) - dice;

                    if(possibleDestCell1<0){
                        possibleDestCell1 +=24;
                    }

                }else{
                    possibleDestCell1 = arrayList.get(i) + dice;

                    if(possibleDestCell1>23){
                        possibleDestCell1 -=24;
                    }
                }
                board[arrayList.get(i)].pieces.get(board[arrayList.get(i)].piece_n-1).canMove(possibleDestCell1,-1);
            }





    }



}


