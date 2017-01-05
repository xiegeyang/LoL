package com.example.geyangxie.lol;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;

public class SummonerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private SummonerInfo summoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner);
        summoner = (SummonerInfo) getIntent().getSerializableExtra("summoner");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(summoner.name);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView textView_Name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.name);
        //TextView textView_Tier = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tier);


        textView_Name.setText(summoner.name);
        //textView_Tier.setText(summoner.id);

        SummonerRequestTask requestTask = new SummonerRequestTask(summoner);
        requestTask.execute((Void) null);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.summoner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.logout) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class SummonerRequestTask extends AsyncTask<Void, Void, SummonerInfo> {
        private SummonerInfo summoner;
        private Bitmap bmp;
        private Bitmap[] championIcon;
        private String strWin[];
        private String[] strLevel;
        private String[] strKDA;
        private Bitmap[][] itemIcon;
        //private Bitmap nav_bar_image;

        public SummonerRequestTask(SummonerInfo summoner){
            this.summoner = summoner;
            championIcon = new Bitmap[summoner.recentGames.length];
            strWin = new String[summoner.recentGames.length];
            strLevel = new String[summoner.recentGames.length];
            strKDA = new String[summoner.recentGames.length];
            for(int i =0;i<strWin.length;i++){
                strWin[i] = new String();
                strLevel[i] = new String();
                strKDA[i] = "0//0//0";
            }

        }


        @Override
        protected SummonerInfo doInBackground(Void... params) {
            RequestEngine.getInstence().requestSummonerInfo(summoner);
            bmp = RequestEngine.getInstence().requetImage(summoner);
            RequestEngine.getInstence().requestGamesInfo(summoner);
            championIcon = RequestEngine.getInstence().requetChampionIcon(summoner);
            for(int i =0;i<summoner.recentGames.length;i++){
                if(summoner.recentGames[i].stats.win == null) break;
                else if(summoner.recentGames[i].stats.win == true) strWin[i] = "Win";
                else if(summoner.recentGames[i].stats.win == false) strWin[i] = "Loss";
                strLevel[i] = Integer.toString(summoner.recentGames[i].stats.level);
            }

            itemIcon = RequestEngine.getInstence().requestItemIcon(summoner);
            //nav_bar_image = RequestEngine.getInstence().requetChampionImage(summoner);
            return summoner;
        }

        @Override
        protected void onPostExecute(SummonerInfo summoner) {
            if(summoner == null) return;
            TextView textView = (TextView)findViewById(R.id.textView_testinfo);
            textView.setText(Integer.toString(summoner.wins)+"/"+Integer.toString(summoner.losses));
            ImageView summonerIcon = (ImageView)findViewById(R.id.profileIcon);
            summonerIcon.setImageBitmap(bmp);
            TextView textView_Tier = (TextView) findViewById(R.id.tier);
            textView_Tier.setText(summoner.tier);
            ImageView imageView_tier = (ImageView)findViewById(R.id.imageView_tier);
            switch (summoner.tier){
                case "MASTER":
                    imageView_tier.setImageResource(R.drawable.master);
                    break;
                case "CHALLENGER":
                    imageView_tier.setImageResource(R.drawable.challenger);
                    break;
                case "DIAMOND":
                    switch (summoner.division){
                        case "I":
                            imageView_tier.setImageResource(R.drawable.diamond_i);
                            break;
                        case "II":
                            imageView_tier.setImageResource(R.drawable.diamond_ii);
                            break;
                        case "III":
                            imageView_tier.setImageResource(R.drawable.diamond_iii);
                            break;
                        case "IV":
                            imageView_tier.setImageResource(R.drawable.diamond_iv);
                            break;
                        case "V":
                            imageView_tier.setImageResource(R.drawable.diamond_v);
                            break;
                        default:
                            imageView_tier.setImageResource(R.drawable.diamond);
                            break;

                    }
                    break;
                case "PLATINUM":
                    switch (summoner.division){
                        case "I":
                            imageView_tier.setImageResource(R.drawable.platinum_i);
                            break;
                        case "II":
                            imageView_tier.setImageResource(R.drawable.platinum_ii);
                            break;
                        case "III":
                            imageView_tier.setImageResource(R.drawable.platinum_iii);
                            break;
                        case "IV":
                            imageView_tier.setImageResource(R.drawable.platinum_iv);
                            break;
                        case "V":
                            imageView_tier.setImageResource(R.drawable.platinum_v);
                            break;
                        default:
                            imageView_tier.setImageResource(R.drawable.platinum);
                            break;

                    }
                    break;
                case "GOLD":
                    switch (summoner.division){
                        case "I":
                            imageView_tier.setImageResource(R.drawable.gold_i);
                            break;
                        case "II":
                            imageView_tier.setImageResource(R.drawable.gold_ii);
                            break;
                        case "III":
                            imageView_tier.setImageResource(R.drawable.gold_iii);
                            break;
                        case "IV":
                            imageView_tier.setImageResource(R.drawable.gold_iv);
                            break;
                        case "V":
                            imageView_tier.setImageResource(R.drawable.gold_v);
                            break;
                        default:
                            imageView_tier.setImageResource(R.drawable.gold);
                            break;
                    }
                    break;
                case "SILVER":
                    switch (summoner.division){
                        case "I":
                            imageView_tier.setImageResource(R.drawable.silver_i);
                            break;
                        case "II":
                            imageView_tier.setImageResource(R.drawable.silver_ii);
                            break;
                        case "III":
                            imageView_tier.setImageResource(R.drawable.silver_iii);
                            break;
                        case "IV":
                            imageView_tier.setImageResource(R.drawable.silver_iv);
                            break;
                        case "V":
                            imageView_tier.setImageResource(R.drawable.silver_v);
                            break;
                        default:
                            imageView_tier.setImageResource(R.drawable.silver);
                            break;

                    }
                    break;
                case "BRONZE":
                    switch (summoner.division){
                        case "I":
                            imageView_tier.setImageResource(R.drawable.bronze_i);
                            break;
                        case "II":
                            imageView_tier.setImageResource(R.drawable.bronze_ii);
                            break;
                        case "III":
                            imageView_tier.setImageResource(R.drawable.bronze_iii);
                            break;
                        case "IV":
                            imageView_tier.setImageResource(R.drawable.bronze_iv);
                            break;
                        case "V":
                            imageView_tier.setImageResource(R.drawable.bronze_v);
                            break;
                        default:
                            imageView_tier.setImageResource(R.drawable.bronze);
                            break;
                    }
                    break;
                default:
                    imageView_tier.setImageResource(R.drawable.provisional);
                    break;
            }
            //LinearLayout navBarLayout = (LinearLayout)findViewById(R.id.nav_bar);
            //navBarLayout.setBackgroundResource(R.drawable.bronze);

            for(int i =0;i<summoner.recentGames.length;i++){
                strKDA[i] = summoner.recentGames[i].stats.championsKilled + "/" +
                        summoner.recentGames[i].stats.numDeaths+"/"+
                        summoner.recentGames[i].stats.assists;
            }
            Date date = new Date(summoner.recentGames[0].createDate);
            System.out.println(date);

            CustomListAdapter adapter = new CustomListAdapter(SummonerActivity.this, strWin, strLevel, strKDA,summoner, championIcon, itemIcon);
            ListView list_RecentGame = (ListView)findViewById(R.id.recentGameList);
            list_RecentGame.setAdapter(adapter);


        }
    }
}
