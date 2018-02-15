package com.company;

public abstract class Chesspiece {

    public final char column;
    public final byte row;
    public final boolean pieceIsWhite;

    public Chesspiece(char column, byte row, boolean pieceIsWhite) {
        //check if row and column is valid
        if(row < 1 || row > 8 || column < 'A' || column > 'H')
            throw new IllegalArgumentException("Tried to create a chesspiece with the coordinate " + column + row + "!");
        this.column = column;
        this.row = row;
        this.pieceIsWhite = pieceIsWhite;
    }

    /**
     * Checks to see if move is a valid move, and wouldn't put the king into check
     * @param destColumn Column letter to move to
     * @param destRow Row number to move to
     * @param cb ChessBoard to interact with
     * @return True only if move is valid
     */

    public abstract boolean isValidMove(char destColumn, byte destRow, ChessBoard cb);

    /**
     * Moves the piece by creating a new piece on the board at the destination and deleting itself (Star Trek teleportation)
     * Make sure that we double check that the move is valid before we do the move.
     * @param destColumn Column letter to move to
     * @param destRow Row number to move to
     * @param cb ChessBoard to interact with
     */
    public abstract void move(char destColumn, byte destRow, ChessBoard cb);

    public final boolean isPieceWhite() { return pieceIsWhite; }

    public final char getColumn() { return column; }

    public final byte getRow() { return row; }

    public final String toString() {
        return null; //TODO:stub
    }

    public abstract String getPieceSymbol();

}
