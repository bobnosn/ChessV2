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

public class MainActivity extends AppCompatActivity implements GridView.OnItemClickListener{
    GridView gv;
    Context context;
    CustomAdapter ca;
    int i = 0;

    public static int [] prgmImages={R.drawable.black_chess_piece_pawn, R.drawable.black_chess_piece_rook,  R.drawable.black_chess_piece_knight, R.drawable.black_chess_piece_bishop, R.drawable.black_chess_piece_queen, R.drawable.black_chess_piece_king, R.drawable.white_chess_piece_pawn, R.drawable.white_chess_piece_rook,  R.drawable.white_chess_piece_knight, R.drawable.white_chess_piece_bishop, R.drawable.white_chess_piece_queen, R.drawable.white_chess_piece_king, R.drawable.blank};
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
        //ca.board[position+8]=ca.board[position];
        //ca.notifyDataSetChanged();
    }
}
class Pawn{
    int[] moves = {8,7,9};
}