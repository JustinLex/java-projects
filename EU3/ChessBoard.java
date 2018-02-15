package com.company;

public class ChessBoard {

    private Chesspiece[][] board;

    public ChessBoard() {
        setupBoard();
    }
    public ChessBoard(boolean makeEmpty) {
        if(makeEmpty) board = new Chesspiece[8][8];
        else setupBoard();
    }

    public  Chesspiece getPiece(char column, byte row) {
        column -=  65; //convert column letter into array index number
        row -= 1; //shift row number down into array index number
        return board[column][row];
    }

    public void setSquare(char column, byte row, Chesspiece piece) {
        //double check that piece thinks it's in the right place on the board
        if(piece != null && ( column != piece.getColumn() || row != piece.getRow()) )
            throw new IllegalArgumentException("Tried to place a broken piece at " + column + row +" that thought it" +
                    "was at " + column + row + "!?");

        column -=  65; //convert row letter into array index number
        row -= 1; //shift row number down into array index number
        board[column][row] = piece; //place piece onto board
    }

    public String toString() {

        //create a new stringbuilder with the capacity to hold 64 chars, 8 new lines, and 20x4 color and bold tags
        StringBuilder sb = new StringBuilder(64+8+80);

        for(int i = 7; i >= 0; i--) { //print row 8 at the top
            for (int j = 0; j < 8; j++) {
                Chesspiece p = board[j][i];
                if(p == null) sb.append('x'); //no piece here, print x
                else sb.append(p.getPieceSymbol()); //print
            }
            sb.append('\n'); //new line
        }
        return sb.toString(); //stub
    }

    private void setupBoard() {
        board = new Chesspiece[8][8];

        //place white pawns
        for(int i=0; i < 8; i++) {
            board[i][1] = new Pawn((char) (i+65), (byte) 2, true);
        }

        //place white queen
        board[3][0] = new Queen('D', (byte) 1, true);

        //place black pawns
        for(int i=0; i < 8; i++) {
            board[i][6] = new Pawn((char) (i+65), (byte) 7, false);
        }

        //place black queen
        board[3][7] = new Queen('D', (byte) 8, false);


    }
}
