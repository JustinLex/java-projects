package com.company;

public class Bishop extends Chesspiece {

    public final byte pieceID = 3;

    public Bishop(char column, byte row, boolean pieceIsWhite) {
        super(column, row, pieceIsWhite);
    }

    @Override
    public String getPieceSymbol() {
        return "B"; //TODO: stub
    }

    @Override
    public boolean isValidMove(char destColumn, byte destRow, ChessBoard cb) {
        //TODO: fixme

        //check if we are moving in a straight, diagonal line

        //check if there is no piece between us and the destination

        //check if we're protecting our king from check

        return true;
    }

    @Override
    public void move(char destColumn, byte destRow, ChessBoard cb) {
        //double check to see if the move is a valid move
        if(!isValidMove(destColumn, destRow, cb))
            throw new NotValidFieldException("Tried to move Bishop (" + this.toString() + ") in an invalid way!");
        else {
            //create next instance of the bishop with the new location and put it on the board
            Bishop nextInstance = new Bishop(destColumn, destRow, pieceIsWhite);
            cb.setSquare(destColumn, destRow, nextInstance);

            //delete this piece from the board (and open up for GC)
            cb.setSquare(column, row, null);
        }
    }
}
