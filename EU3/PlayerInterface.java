package com.company;

public class PlayerInterface {
    private final ChessBoard cb;

    public PlayerInterface(ChessBoard cb) {
        this.cb = cb;
    }

    public void promptWhite() {
        System.out.println("WHITE's turn:");
        //ask stuff here
    }
}
