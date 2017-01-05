package com.example.geyangxie.lol;

import android.content.res.Resources;

import java.io.Serializable;

/**
 * Created by GeyangXie on 1/1/2017.
 */
public class SummonerInfo implements Serializable{
    private String region;
    public String name;
    public String id;
    public int profileIconId;
    public int summonerLevel;
    public int revisionDate;
    public String queue;
    public String battleGroup;
    public String tier = new String();
    public int leaguePoints;
    public boolean isFreshBlood;
    public boolean isHotStreak;
    public String division;
    public boolean isInactive;
    public boolean isVeteran;
    public int losses;
    public int wins;
    public GameInfo[] recentGames;



    public String getRegion(){
        return region;
    }

    public SummonerInfo(String region){
        //this.name = name;
        switch(region){
            case "NA":
                this.region = "na";
                break;
            default:
                this.region = region;
                break;
        }
        recentGames = new GameInfo[5];
        for(int i =0;i<recentGames.length;i++){
            recentGames[i] = new GameInfo();
        }

    }
}
