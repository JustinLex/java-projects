package com.company;

public class Pawn extends Chesspiece {

    public final byte pieceID = 0;

    private boolean hasMoved = false;
    final byte movementDirection;

    public Pawn(char column, byte row, boolean pieceIsWhite) {
        super(column, row, pieceIsWhite);
        //white pieces move north (positive movement), black pieces move south (negative movement)
        movementDirection = (byte) (pieceIsWhite ? 1 : -1);
    }

    @Override
    public String getPieceSymbol() {
        return "P"; //TODO: stub
    }

    @Override
    public boolean isValidMove(char destColumn, byte destRow, ChessBoard cb) {
        //TODO: fixme

        //make sure this is an actual move
        if(destColumn == column && destRow == row) return false;

        //make sure pawn is only moving forward one or two rows
        if (destRow != row + movementDirection && (hasMoved || destRow != row + 2*movementDirection)) return false;

        //make sure pawn isn't trying to move sideways
        if(destColumn <= column - 1 || destColumn >= column + 1) return false;

        //if we are moving forwards, make sure there is no piece in the way

        //if we are moving diagonally, make sure that there is a piece to kill

        //check if we're protecting our king from check


        return true;
    }

    @Override
    public void move(char destColumn, byte destRow, ChessBoard cb) {
        //double check to see if the move is a valid move
        if(!isValidMove(destColumn, destRow, cb))
            throw new NotValidFieldException("Tried to move Pawn (" + this.toString() + ") in an invalid way!");
        else {

            //check if move would make piece a queen
            if(destRow == 1 || destRow == 8) {
                //create queen on the board where we are moving
                //TODO:Queen successor = new Queen()
            }

            else {
                //create next instance of the pawn with the new location and put it on the board
                Pawn nextInstance = new Pawn(destColumn, destRow, pieceIsWhite);
                cb.setSquare(destColumn, destRow, nextInstance);
            }

            //delete this piece from the board (and open up for GC)
            cb.setSquare(column, row, null);

        }
    }
}
