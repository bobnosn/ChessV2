package com.example.joshu.chessv2;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.color.black;

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
    int[] board = {1, 2, 3, 4, 5, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 5, 4, 3, 2, 1};

    final int PAWN = 0;
    final int ROOK = 1;
    final int KNIGHT = 2;
    final int BISHOP = 3;
    final int QUEEN = 4;
    final int KING = 5;
    final int BLANK = 6;

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

        if (isDrawn == false) {
            if ((position) % 8 != 0) isBlack = !isBlack;

            if (isBlack) rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
            else rowView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));

            switch (board[position]) {
                case PAWN:
                    if (position < 16)
                        img.setImageResource(imageId[PAWN]);
                    else
                        img.setImageResource(imageId[PAWN + 6]);
                    piece = PAWN;
                    break;
                case ROOK:
                    if (position < 16)
                        img.setImageResource(imageId[ROOK]);
                    else
                        img.setImageResource(imageId[ROOK + 6]);
                    piece = ROOK;
                    break;
                case KNIGHT:
                    if (position < 16)
                        img.setImageResource(imageId[KNIGHT]);
                    else
                        img.setImageResource(imageId[KNIGHT + 6]);
                    piece = KNIGHT;
                    break;
                case BISHOP:
                    if (position < 16)
                        img.setImageResource(imageId[BISHOP]);
                    else
                        img.setImageResource(imageId[BISHOP + 6]);
                    piece = BISHOP;
                    break;
                case QUEEN:
                    if (position < 16)
                        img.setImageResource(imageId[QUEEN]);
                    else
                        img.setImageResource(imageId[QUEEN + 6]);
                    piece = QUEEN;
                    break;
                case KING:
                    if (position < 16)
                        img.setImageResource(imageId[KING]);
                    else
                        img.setImageResource(imageId[KING + 6]);
                    piece = KING;
                    break;
                case BLANK:
                    img.setImageResource(imageId[BLANK + 6]);
                    piece = BLANK;
                    break;
                default:
                    break;
            }
            if (position == 64) isDrawn = true;
        }
        else{

        }


        rowView.setTag(piece);
        return rowView;
    }
}
