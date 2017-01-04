package com.example.geyangxie.lol;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by GeyangXie on 1/3/2017.
 */
public class CustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] isWin;
    private final Bitmap[] imgid;
    private final String[] strLevel;
    private final String[] strKDA;

    public CustomListAdapter(Activity context, String[] isWin, String[] strLevel, String[] strKDA, Bitmap[] imgid){
        super(context,R.layout.imagetextlist, isWin);
        this.context=context;
        this.isWin=isWin;
        this.imgid=imgid;
        this.strLevel = strLevel;
        this.strKDA = strKDA;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.imagetextlist, null,true);

        TextView textView_isWin = (TextView) rowView.findViewById(R.id.textView_isWin);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.champiomIcon);
        TextView textView_level = (TextView) rowView.findViewById(R.id.textView_level);

        textView_isWin.setText(isWin[position]);
        if(isWin[position].equals("Loss")){
            textView_isWin.setTextColor(context.getResources().getColor(R.color.colorRed));
        }else if(isWin[position].equals("Win")){
            textView_isWin.setTextColor(context.getResources().getColor(R.color.colorGreen));
        }

        imageView.setImageBitmap(imgid[position]);
        textView_level.setText("Level: " + strLevel[position]);
        return rowView;

    };
}
