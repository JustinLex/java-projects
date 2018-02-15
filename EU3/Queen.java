package com.company;

public class Queen extends Chesspiece {

    public final byte pieceID = 4;

    public Queen(char column, byte row, boolean pieceIsWhite) {
        super(column, row, pieceIsWhite);
    }

    @Override
    public String getPieceSymbol() {
        return "Q"; //TODO: stub
    }

    @Override
    public boolean isValidMove(char destColumn, byte destRow, ChessBoard cb) {
        //TODO: fixme

        //make sure this is an actual move
        if(destColumn == column && destRow == row) return false;

        //check if we are moving in a straight line
        if(destColumn != column && destRow != row && (destColumn - column != destRow - row) && (destColumn - column != row - destRow)) return false;

        //check if there is no piece between us and the destination

        //check if we're protecting our king from check

        return true;
    }

    @Override
    public void move(char destColumn, byte destRow, ChessBoard cb) {
        //double check to see if the move is a valid move
        if(!isValidMove(destColumn, destRow, cb))
            throw new NotValidFieldException("Tried to move Queen (" + this.toString() + ") in an invalid way!");
        else {
            //create next instance of the queen with the new location and put it on the board
            Queen nextInstance = new Queen(destColumn, destRow, pieceIsWhite);
            cb.setSquare(destColumn, destRow, nextInstance);

            //delete this piece from the board (and open up for GC)
            cb.setSquare(column, row, null);
        }
    }
}
