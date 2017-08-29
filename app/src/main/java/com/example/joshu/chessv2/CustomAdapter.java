package com.example.joshu.chessv2;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


/**
 * Created by joshu on 7/30/2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    int[] imageId;
    private static LayoutInflater inflater = null;
    ImageView img;
    View rowView;
    boolean isBlack, isDrawn = false;
    int[] board = {1, 2, 3, 4, 5, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 8, 9, 10, 12, 11, 10, 9, 8};

    final byte bPAWN = 0; final byte wPAWN=7;
    final byte bROOK = 1; final byte wROOK=8;
    final byte bKNIGHT = 2; final byte wKNIGHT=9;
    final byte bBISHOP = 3; final byte wBISHOP=10;
    final byte bQUEEN = 4; final byte wQUEEN=11;
    final byte bKING = 5; final byte wKING=12;
    final byte BLANK = 6;

    public CustomAdapter(Context context, int[] prgmImages) {
        this.context = context;
        this.imageId = prgmImages;
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
        int piece = 0;

        if ((position) % 8 != 0) isBlack = !isBlack;
        if (isBlack) rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
        else rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));

        switch (board[position]) {
            case bPAWN: img.setImageResource(imageId[bPAWN]); piece = bPAWN; break;
            case wPAWN: img.setImageResource(imageId[wPAWN]); piece = wPAWN; break;

            case bROOK: img.setImageResource(imageId[bROOK]); piece = bROOK; break;
            case wROOK: img.setImageResource(imageId[wROOK]); piece = wROOK; break;

            case bKNIGHT: img.setImageResource(imageId[bKNIGHT]); piece = bKNIGHT; break;
            case wKNIGHT: img.setImageResource(imageId[wKNIGHT]); piece = wKNIGHT; break;

            case bBISHOP: img.setImageResource(imageId[bBISHOP]); piece = bBISHOP; break;
            case wBISHOP: img.setImageResource(imageId[wBISHOP]); piece = wBISHOP; break;

            case bQUEEN: img.setImageResource(imageId[bQUEEN]); piece = bQUEEN; break;
            case wQUEEN: img.setImageResource(imageId[wQUEEN]); piece = wQUEEN; break;

            case bKING: img.setImageResource(imageId[bKING]); piece = bKING; break;
            case wKING: img.setImageResource(imageId[wKING]); piece = wKING; break;

            case BLANK: img.setImageResource(imageId[BLANK]); piece = BLANK; break;
            default: break;
        }
        if (position == 63) {
            isDrawn = true;
        }

        rowView.setTag(piece);
        return rowView;
    }
}
