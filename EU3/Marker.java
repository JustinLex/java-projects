package com.company;

public class Marker extends Chesspiece{

    public Marker(char column, byte row) {
        super(column, row, true);
    }

    @Override
    public boolean isValidMove(char destColumn, byte destRow, ChessBoard cb) {
        return false;
    }

    @Override
    public void move(char destColumn, byte destRow, ChessBoard cb) {
    }

    @Override
    public String getPieceSymbol() {
        return "#";
    }
}
