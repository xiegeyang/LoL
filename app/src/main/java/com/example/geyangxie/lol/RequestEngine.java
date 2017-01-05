package com.example.geyangxie.lol;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import org.json.*;

/**
 * Created by GeyangXie on 1/1/2017.
 */
public class RequestEngine {

    private static RequestEngine Instence = null;
    private String strKey = "d185183d-10b0-4410-b9c7-c623475d3523";
    private RequestEngine(){}

    public static RequestEngine getInstence(){
        if(Instence == null)
            Instence = new RequestEngine();
        return Instence;
    }

    public void requestUserInfo(String summerName, String region,SummonerInfo summoner) throws Exception{
        String strURI ="https://na.api.pvp.net/api/lol/"+region+
                "/v1.4/summoner/by-name/"+summerName+
                "?api_key="+strKey;
        JSONObject jObject = requestJson(strURI);
        if(jObject == null){
            return;
        }
        int id = jObject.getJSONObject(summerName).getInt("id");
        summoner.id = Integer.toString(id);
        summoner.name = jObject.getJSONObject(summerName).getString("name");
        summoner.profileIconId = jObject.getJSONObject(summerName).getInt("profileIconId");
        summoner.summonerLevel = jObject.getJSONObject(summerName).getInt("summonerLevel");
        summoner.revisionDate = jObject.getJSONObject(summerName).getInt("revisionDate");
        return;
    }

    public boolean requestSummonerInfo(SummonerInfo summoner){
        if(summoner == null) return false;
        String strURL = "https://na.api.pvp.net/api/lol/"+summoner.getRegion()+
                "/v2.5/league/by-summoner/"+summoner.id+
                "/entry?api_key="+ strKey;
        JSONObject jObject = requestJson(strURL);
        if(jObject == null) return false;
        try {
            summoner.battleGroup = jObject.getJSONArray(summoner.id).getJSONObject(0).getString("name");
            summoner.tier = jObject.getJSONArray(summoner.id).getJSONObject(0).getString("tier");
            summoner.queue = jObject.getJSONArray(summoner.id).getJSONObject(0).getString("queue");
            summoner.division = jObject.getJSONArray(summoner.id).getJSONObject(0).getJSONArray("entries").getJSONObject(0).getString("division");
            summoner.leaguePoints = jObject.getJSONArray(summoner.id).getJSONObject(0).getJSONArray("entries").getJSONObject(0).getInt("leaguePoints");
            summoner.wins = jObject.getJSONArray(summoner.id).getJSONObject(0).getJSONArray("entries").getJSONObject(0).getInt("wins");
            summoner.losses = jObject.getJSONArray(summoner.id).getJSONObject(0).getJSONArray("entries").getJSONObject(0).getInt("losses");
            summoner.isHotStreak = jObject.getJSONArray(summoner.id).getJSONObject(0).getJSONArray("entries").getJSONObject(0).getBoolean("isHotStreak");
            summoner.isVeteran = jObject.getJSONArray(summoner.id).getJSONObject(0).getJSONArray("entries").getJSONObject(0).getBoolean("isVeteran");
            summoner.isFreshBlood = jObject.getJSONArray(summoner.id).getJSONObject(0).getJSONArray("entries").getJSONObject(0).getBoolean("isFreshBlood");
            summoner.isInactive = jObject.getJSONArray(summoner.id).getJSONObject(0).getJSONArray("entries").getJSONObject(0).getBoolean("isInactive");
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean requestGamesInfo(SummonerInfo summoner){
        if(summoner == null) return false;
        String strURL = "https://na.api.pvp.net/api/lol/"+summoner.getRegion()+
                "/v1.3/game/by-summoner/"+summoner.id+"/recent?api_key="+strKey;
        JSONObject jObject = requestJson(strURL);
        if(jObject == null) return false;

            for(int i =0;i<summoner.recentGames.length;i++){
                try{
                    summoner.recentGames[i].championId = jObject.getJSONArray("games").getJSONObject(i).getInt("championId");
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    summoner.recentGames[i].stats.item0 = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getInt("item0");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    summoner.recentGames[i].stats.item1 = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getInt("item1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    summoner.recentGames[i].stats.item2 = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getInt("item2");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    summoner.recentGames[i].stats.item3 = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getInt("item3");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    summoner.recentGames[i].stats.item4 = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getInt("item4");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    summoner.recentGames[i].stats.item5 = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getInt("item5");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    summoner.recentGames[i].stats.item6 = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getInt("item6");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    summoner.recentGames[i].stats.win = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getBoolean("win");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    summoner.recentGames[i].stats.team = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getInt("team");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    summoner.recentGames[i].stats.numDeaths = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getInt("numDeaths");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    summoner.recentGames[i].stats.championsKilled = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getInt("championsKilled");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    summoner.recentGames[i].stats.assists = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getInt("assists");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    summoner.recentGames[i].stats.level = jObject.getJSONArray("games").getJSONObject(i).getJSONObject("stats").getInt("level");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        return true;
    }

    public Bitmap requetImage(SummonerInfo summoner){
        if(summoner == null) return null;
        Bitmap bmp = null;
        try {
            URL url = new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/"+summoner.profileIconId +".png");
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    public Bitmap requetChampionImage(SummonerInfo summoner){
        if(summoner == null) return null;
        Bitmap bmp = null;
        try {
            URL url = new URL("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/"+summoner.recentGames[0].championName+"_0.jpg");
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    public Bitmap[] requetChampionIcon(SummonerInfo summoner){
        if(summoner == null) return null;
        Bitmap bmp[] = new Bitmap[summoner.recentGames.length];
        try {
            for(int i =0;i<summoner.recentGames.length;i++){
                JSONObject jsonObject= requestJson("https://global.api.pvp.net/api/lol/static-data/"+summoner.getRegion()+
                        "/v1.2/champion/"+summoner.recentGames[i].championId+"?api_key="+strKey);
                summoner.recentGames[i].championName = jsonObject.getString("key");
                URL url = new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/"+summoner.recentGames[i].championName+".png");
                bmp[i] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    public Bitmap[][] requestItemIcon(SummonerInfo summoner){
        if(summoner == null) return null;
        Bitmap[][] bmp = new Bitmap[summoner.recentGames.length][7];
        for(int i =0;i<summoner.recentGames.length;i++){
            URL url = null;
            try {
                if(summoner.recentGames[i].stats.item0!=null){
                    url = new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/"+summoner.recentGames[i].stats.item0+".png");
                    bmp[i][0] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }
                if(summoner.recentGames[i].stats.item1!=null){
                    url = new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/"+summoner.recentGames[i].stats.item1+".png");
                    bmp[i][1] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }
                if(summoner.recentGames[i].stats.item2!=null){
                    url = new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/"+summoner.recentGames[i].stats.item2+".png");
                    bmp[i][2] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }
                if(summoner.recentGames[i].stats.item3!=null){
                    url = new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/"+summoner.recentGames[i].stats.item3+".png");
                    bmp[i][3] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }
                if(summoner.recentGames[i].stats.item4!=null){
                    url = new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/"+summoner.recentGames[i].stats.item4+".png");
                    bmp[i][4] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }
                if(summoner.recentGames[i].stats.item5!=null){
                    url = new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/"+summoner.recentGames[i].stats.item5+".png");
                    bmp[i][5] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }
                if(summoner.recentGames[i].stats.item6!=null){
                    url = new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/"+summoner.recentGames[i].stats.item6+".png");
                    bmp[i][6] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        return bmp;
    }

    private JSONObject requestJson(String strURL){
        JSONObject res = null;
        StringBuilder json = new StringBuilder();
        String temp;
        try{
            URL url = new URL(strURL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            do{
                temp = br.readLine();
                json.append(temp);
            }while(temp != null);
            br.close();
            isr.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            res = (JSONObject)(new JSONTokener(json.toString()).nextValue());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }

}

