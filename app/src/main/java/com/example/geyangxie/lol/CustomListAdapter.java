package com.example.geyangxie.lol;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GeyangXie on 1/3/2017.
 */
public class CustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] isWin;
    private final Bitmap[] imgid;
    private final String[] strLevel;
    private final String[] strKDA;
    private final Bitmap[][] itemIcon;
    private final SummonerInfo summoner;

    public CustomListAdapter(Activity context, String[] isWin, String[] strLevel, String[] strKDA,SummonerInfo summoner, Bitmap[] imgid, Bitmap[][] itemIcon){
        super(context,R.layout.imagetextlist, isWin);
        this.context=context;
        this.isWin=isWin;
        this.imgid=imgid;
        this.strLevel = strLevel;
        this.strKDA = strKDA;
        this.itemIcon = itemIcon;
        this.summoner = summoner;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.imagetextlist, null,true);

        TextView textView_isWin = (TextView) rowView.findViewById(R.id.textView_isWin);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.champiomIcon);
        TextView textView_level = (TextView) rowView.findViewById(R.id.textView_level);

        ImageView item0View = (ImageView)rowView.findViewById(R.id.imageView_item0);
        ImageView item1View = (ImageView)rowView.findViewById(R.id.imageView_item1);
        ImageView item2View = (ImageView)rowView.findViewById(R.id.imageView_item2);
        ImageView item3View = (ImageView)rowView.findViewById(R.id.imageView_item3);
        ImageView item4View = (ImageView)rowView.findViewById(R.id.imageView_item4);
        ImageView item5View = (ImageView)rowView.findViewById(R.id.imageView_item5);
        ImageView item6View = (ImageView)rowView.findViewById(R.id.imageView_item6);
        TextView textView_matchDate = (TextView)rowView.findViewById(R.id.textView_date);
        Date currentDate = new Date();
        Date matchDate = new Date(summoner.recentGames[position].createDate);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss");
        textView_matchDate.setText(dateFormat.format(matchDate));

        textView_isWin.setText(isWin[position]);
        if(isWin[position].equals("Loss")){
            textView_isWin.setTextColor(context.getResources().getColor(R.color.colorRed));
        }else if(isWin[position].equals("Win")){
            textView_isWin.setTextColor(context.getResources().getColor(R.color.colorGreen));
        }

        imageView.setImageBitmap(imgid[position]);
        textView_level.setText(strKDA[position]);

        Bitmap[] items = itemIcon[position];
        if(items[0] != null)
            item0View.setImageBitmap(items[0]);
        if(items[1] != null)
            item1View.setImageBitmap(items[1]);
        if(items[2] != null)
            item2View.setImageBitmap(items[2]);
        if(items[3] != null)
            item3View.setImageBitmap(items[3]);
        if(items[4] != null)
            item4View.setImageBitmap(items[4]);
        if(items[5] != null)
            item5View.setImageBitmap(items[5]);
        if(items[6] != null)
            item6View.setImageBitmap(items[6]);




        return rowView;

    };
}
