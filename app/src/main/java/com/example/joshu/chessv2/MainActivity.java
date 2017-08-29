package com.example.joshu.chessv2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    Context context;
    CustomAdapter ca;
    int i = 0;

    public static int [] prgmImages={R.drawable.black_chess_piece_pawn, R.drawable.black_chess_piece_rook,  R.drawable.black_chess_piece_knight, R.drawable.black_chess_piece_bishop, R.drawable.black_chess_piece_queen, R.drawable.black_chess_piece_king, R.drawable.blank, R.drawable.white_chess_piece_pawn, R.drawable.white_chess_piece_rook,  R.drawable.white_chess_piece_knight, R.drawable.white_chess_piece_bishop, R.drawable.white_chess_piece_queen, R.drawable.white_chess_piece_king};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ca = new CustomAdapter(this,prgmImages);
        gv = (GridView) findViewById(R.id.gridView);

        gv.setAdapter(ca);
        gv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Integer piece = (Integer) view.getTag();
        Toast.makeText(getApplicationContext(), ""+piece, Toast.LENGTH_SHORT).show();
        //int[] possibilities = getPossibleMoves(piece);
        System.out.println(Arrays.toString(getPossibleMoves(piece,position)));
        ca.board[position+getPossibleMoves(piece,position)[0]]=ca.board[position];
        ca.board[position]=ca.BLANK;
        ca.notifyDataSetChanged();
    }

    public int[] getPossibleMoves(int p, int position){
        int[] possMoves = new int[29];
        switch(p){
            case 0:
            case 7:
                if(p==0){
                    possMoves[0]=8;
                    return possMoves;
                }else{
                    possMoves[0]=-8;
                    return possMoves;
                }
            case 1:
            case 8:
                if(p==1) {
                    int i = 0;
                    //horizontal possible moves
                    for (int l = position+1; l%8==0; l++){
                        if(l!=0) {
                            possMoves[i] = l;
                        }
                        i++;
                    }
                    for (int l = position; l >= 0; l--){
                        possMoves[i] = -l;
                        i++;
                    }
                    //vertical possible moves
                    for (int l = position; l <= 63; l+=8){
                        possMoves[i] = l;
                        i++;
                    }
                }
                break;
            case 2:
            case 9:
                break;
            case 3:
            case 10:
                break;
            case 4:
            case 11:
                break;
            case 5:
            case 12:
                break;
            default:
                break;
        }
        return possMoves;
    }
}