package com.example.joshu.chessv2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements GridView.OnItemClickListener{
    GridView gv;
    CustomAdapter ca;
    int i = 0, selectedPosition, selectedPiece;
    boolean pieceIsSelected;

    // The Adapter is simply used to lay out the board.
    // These variables moved to MainActivity since this is where all the game analysis will occur.
    final int bPAWN = 0; final int wPAWN=7;
    final int bROOK = 1; final int wROOK=8;
    final int bKNIGHT = 2; final int wKNIGHT=9;
    final int bBISHOP = 3; final int wBISHOP=10;
    final int bQUEEN = 4; final int wQUEEN=11;
    final int bKING = 5; final int wKING=12;
    final int BLANK = 6;

    final ArrayList<Integer> blackPieces = new ArrayList<>();
    final ArrayList<Integer> whitePieces = new ArrayList<>();

    // This is the initial layout of the board.
    int[] board = {
            bROOK, bKNIGHT, bBISHOP, bQUEEN, bKING, bBISHOP, bKNIGHT, bROOK,
            bPAWN, bPAWN, bPAWN, bPAWN, bPAWN, bPAWN, bPAWN, bPAWN,
            BLANK, BLANK, wKING, BLANK, BLANK, BLANK, BLANK, BLANK,
            BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK,
            BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK,
            BLANK, BLANK, BLANK, wQUEEN, BLANK, bKNIGHT, BLANK, BLANK,
            wPAWN, wPAWN, wPAWN, wPAWN, wPAWN, wPAWN, wPAWN, wPAWN,
            wROOK, wKNIGHT, wBISHOP, wKING, wQUEEN, wBISHOP, wKNIGHT, wROOK};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blackPieces.add(bROOK);
        blackPieces.add(bKNIGHT);
        blackPieces.add(bBISHOP);
        blackPieces.add(bKING);
        blackPieces.add(bQUEEN);
        blackPieces.add(bPAWN);
        whitePieces.add(wROOK);
        whitePieces.add(wKING);
        whitePieces.add(wKNIGHT);
        whitePieces.add(wQUEEN);
        whitePieces.add(wPAWN);
        whitePieces.add(wBISHOP);

        ca = new CustomAdapter(this,board);
        gv = (GridView) findViewById(R.id.gridView);

        gv.setAdapter(ca);
        gv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Integer piece = (Integer) view.getTag();

        if(!pieceIsSelected) {

            // Player has selected the piece he is interested in moving.  Record the piece and position.
            // If it isn't blank and moves are available, set a flag to note that a piece has been selected to move.
            selectedPosition = position;
            selectedPiece = (Integer) view.getTag();
            ArrayList<Integer> possMoves = getPossibleMoves(piece, position);
            pieceIsSelected = selectedPiece != BLANK && possMoves.size() > 0
                    ? true
                    : false;

            ca.setPossibleMoves(possMoves);
        }
        else {

            // Previous piece was selected.  Now, destination square has presumably been selected.

            // Only allow the move if one of the possible move squares have been touched.  Ignore other squares
            if(getPossibleMoves(selectedPiece, selectedPosition).contains(position)) {
                // Move selected piece to new position
                board[position] = selectedPiece;
                board[selectedPosition] = BLANK;
            }

            // Notify that piece is no longer selected.
            pieceIsSelected = false;

            // Clear Stroke by sending a blank ArrayList
            ca.setPossibleMoves(new ArrayList<Integer>());
        }

        ca.notifyDataSetChanged();
    }

    public ArrayList<Integer> getPossibleMoves(int piece, int position){

        ArrayList<Integer> possMoves = new ArrayList<>();
        int testPosition;
        switch(piece){
            case bPAWN:
                testPosition = position + 8;
                if ((testPosition) < 64)
                {
                    // Pawn has room to advance forward one square.  Using the ++ after "i" increments it by one after this statement is complete.
                    if (board[testPosition] == BLANK) possMoves.add(testPosition);

                    // Since we know the pawn can move forward, we need to test to see if it can attack diagonally
                    if ((testPosition + 1) % 8 != 0){

                        Log.i("joshChess", String.valueOf(whitePieces.contains(board[testPosition + 1])));
                        // Pawn not on right edge of board. Check to see if square is occupied by a white piece.
                        if (whitePieces.contains(board[testPosition + 1])) possMoves.add(testPosition + 1);
                    }
                    if (testPosition % 8 != 0) {

                        // Pawn is not on left edge of board.  Check to see if square is occupied by a white piece.
                        if (whitePieces.contains(board[testPosition - 1])) possMoves.add(testPosition - 1);
                    }

                    // Since we know the pawn can move forward, we should also check if it can be moved forward two squares (if first time it is moved)
                    // without jumping over a square.
                    testPosition = position + 16;
                    if (position < 16 && testPosition < 64 && board[testPosition] == BLANK && board[testPosition-8] == BLANK) possMoves.add(testPosition);
                }
                return possMoves;

            case wPAWN:
                testPosition = position - 8;
                if ((testPosition) > 0)
                {
                    // Pawn has room to advance forward one square.  Using the ++ after "i" increments it by one after this statement is complete.
                    if (board[testPosition] == BLANK) possMoves.add(testPosition);

                    // Since we know the pawn can move forward, we need to test to see if it can attack diagonally
                    if ((testPosition + 1) % 8 != 0){

                        // Pawn not on right edge of board. Check to see if square is occupied by a black piece.
                        if (blackPieces.contains(board[testPosition + 1])) possMoves.add(testPosition + 1);
                    }
                    if (testPosition % 8 != 0) {

                        // Pawn is not on left edge of board.  Check to see if square is occupied by a black piece.
                        if (blackPieces.contains(board[testPosition - 1])) possMoves.add(testPosition - 1);
                    }

                    // Since we know the pawn can move forward, we should also check if it can be moved forward two squares (if first time it is moved)
                    // without jumping over a square.
                    testPosition = position - 16;
                    if (position > 47 && testPosition > 0 && board[testPosition] == BLANK && board[testPosition + 8] == BLANK) possMoves.add(testPosition);
                }
                return possMoves;

            case bROOK:
                break;
            case wROOK:
                if(piece==1) {
                    i = 0;
                    //horizontal possible moves
                    for (int l = position+1; l%8==0; l++){
                        if(l!=0) {
//                            possMoves[i] = l;
                        }
                        i++;
                    }
                    for (int l = position; l >= 0; l--){
//                        possMoves[i] = -l;
                        i++;
                    }
                    //vertical possible moves
                    for (int l = position; l <= 63; l+=8){
//                        possMoves[i] = l;
                        i++;
                    }
                }
                break;
            case bKNIGHT:
                break;
            case wKNIGHT:
                break;
            case bBISHOP:
                break;
            case wBISHOP:
                break;
            case bQUEEN:
                break;
            case wQUEEN:
                break;
            case bKING:
                break;
            case wKING:
                break;
        }
        return possMoves;
    }
}