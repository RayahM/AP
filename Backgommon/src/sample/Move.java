package sample;

public class Move{

    private boolean movePermit ;
    private int newX;
    private int newY;
    private MoveType moveType;
    private Piece piece;
    private int nextCell;
    private int currentCell;


    public void setCurrentCell(int currentCell) {
        this.currentCell = currentCell;
    }

    public void setNextCell(int nextCell) {
        this.nextCell = nextCell;
    }

    public int getCurrentCell() {
        return currentCell;
    }

    public int getNextCell() {
        return nextCell;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setMovePermit(boolean movePermit) {
        this.movePermit = movePermit;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    public void setNewX(int newX) {
        this.newX = newX;
    }

    public void setNewY(int newY) {
        this.newY = newY;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isMovePermit() {
        return movePermit;
    }
}
