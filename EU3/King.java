package com.company;

public class King extends Chesspiece {

    public final byte pieceID = 5;

    public King(char column, byte row, boolean pieceIsWhite) {
        super(column, row, pieceIsWhite);
    }

    @Override
    public String getPieceSymbol() {
        return "K"; //TODO: stub
    }

    @Override
    public boolean isValidMove(char destColumn, byte destRow, ChessBoard cb) {
        //TODO: fixme

        //check if we are moving only 1 space away

        //check if we're putting ourselves in check

        return true;
    }

    @Override
    public void move(char destColumn, byte destRow, ChessBoard cb) {
        //double check to see if the move is a valid move
        if(!isValidMove(destColumn, destRow, cb))
            throw new NotValidFieldException("Tried to move King (" + this.toString() + ") in an invalid way!");
        else {
            //create next instance of the king with the new location and put it on the board
            King nextInstance = new King(destColumn, destRow, pieceIsWhite);
            cb.setSquare(destColumn, destRow, nextInstance);

            //delete this piece from the board (and open up for GC)
            cb.setSquare(column, row, null);
        }
    }
}
