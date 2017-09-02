package com.example.joshu.chessv2;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.IntegerRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * Created by joshu on 7/30/2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;

    // We want to include the array for the images here because the GridView needs access to them.  This is labeled FINAL because the array does not change.
    public final int[] prgmImages = {R.drawable.black_chess_piece_pawn, R.drawable.black_chess_piece_rook, R.drawable.black_chess_piece_knight, R.drawable.black_chess_piece_bishop, R.drawable.black_chess_piece_queen, R.drawable.black_chess_piece_king, R.drawable.blank, R.drawable.white_chess_piece_pawn, R.drawable.white_chess_piece_rook, R.drawable.white_chess_piece_knight, R.drawable.white_chess_piece_bishop, R.drawable.white_chess_piece_queen, R.drawable.white_chess_piece_king};

    private static LayoutInflater inflater = null;
    ImageView img;
    View rowView;
    boolean isBlack, isDrawn = false;
    int[] board;
    ArrayList<Integer> possMoves;


    public CustomAdapter(Context context, int[] board) {
        this.context = context;
        // We are passing in the initial status of the board when this Adapter is instantiated.  This constructor only runs when the object is initially
        // instantiated.  The Adapter will relayout when changes are made to this array.
        this.board = board;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return board.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        rowView = inflater.inflate(R.layout.program_list, null);

        img = (ImageView) rowView.findViewById(R.id.imageView1);
        int piece;

        if ((position) % 8 != 0) isBlack = !isBlack;
        GradientDrawable border = new GradientDrawable();
        border.setColor(ContextCompat.getColor(context, isBlack
                ? R.color.gray
                : R.color.white));
        if (possMoves != null && possMoves.contains(position)) border.setStroke(4, 0xFFFF0000); //red border with full opacity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            rowView.setBackgroundDrawable(border);
        } else {
            rowView.setBackground(border);
        }
//        if (isBlack) rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
//        else rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));

        // Assign current piece number to type of piece in our images array at the same location (position)
        piece = board[position];
        // Set image to whatever default graphic ID is currently in our images array at the same location (position)
        img.setImageResource(prgmImages[piece]);

        if (position == 63) {
            isDrawn = true;
        }

        rowView.setTag(piece);
        return rowView;
    }

    protected void setPossibleMoves (ArrayList<Integer> possMoves) {

        this.possMoves = possMoves;
        this.notifyDataSetChanged();
    }
}