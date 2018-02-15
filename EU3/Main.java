package com.company;

public class Main {

    public enum Mode { CHESS_TEST, MARK_SQUARES }

    public static final Mode mode = Mode.MARK_SQUARES;

    public static void main(String[] args) {
        if(mode == mode.CHESS_TEST) chessTest();
        if(mode == mode.MARK_SQUARES) {
            try { markSquares(); }
            catch(InterruptedException e) {}
        }
    }

    private static void chessTest() {
        ChessBoard cb = new ChessBoard();
        System.out.println(cb);
        cb.getPiece('C', (byte) 2).move('C', (byte) 3, cb);
        System.out.println(cb);
        cb.getPiece('D', (byte) 1).move('B', (byte) 3, cb);
        System.out.println(cb);
    }

    private static void markSquares() throws InterruptedException {
        ChessBoard cb;
        char column = 'D';
        byte row = 6;
        Pawn pawn = new Pawn(column, row, true);
        Rook rook = new Rook(column, row, true);
        Knight knight = new Knight(column, row, true);
        Bishop bishop = new Bishop(column, row, true);
        Queen queen = new Queen(column, row, true);
        King king = new King(column, row, true);


        cb = new ChessBoard(true);
        //intro
        System.out.println(cb.toString());
        System.out.println("Let's look at some pieces!");
        Thread.sleep(1000);

        cb = new ChessBoard(true);
        //show pawn
        cb.setSquare(column, row, pawn);
        System.out.println(cb.toString());
        System.out.println("This is a pawn.");
        Thread.sleep(1000);

        markAndPrint(cb, pawn);
        System.out.println(cb.toString());
        System.out.println("This is where it can move.");
        Thread.sleep(1000);

        cb = new ChessBoard(true);
        //show queen
        cb.setSquare(column, row, queen);
        System.out.println(cb.toString());
        System.out.println("This is a queen.");
        Thread.sleep(1000);

        markAndPrint(cb, queen);
        System.out.println(cb.toString());
        System.out.println("This is where it can move.");
        Thread.sleep(1000);


    }

    private static void markAndPrint(ChessBoard cb, Chesspiece piece) {
        //d6
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                boolean needMarker = piece.isValidMove((char) (i+65), (byte) (j+1), cb);
                if(needMarker) {
                    Marker marker = new Marker((char) (i+65), (byte) (j+1));
                    cb.setSquare((char) (i+65), (byte) (j+1), marker);
                }
            }
        }
        System.out.println(cb);
    }

}


