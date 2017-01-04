package com.example.geyangxie.lol;

import java.io.Serializable;

/**
 * Created by GeyangXie on 1/2/2017.
 */
public class GameInfo implements Serializable {
    private String gameId;
    public int ipEarned;
    public int spell1;
    public int teamId;
    public int spell2;
    public String gameMode;
    public int mapId;
    public int level;
    public boolean invalid;
    public String subType;
    public long createDate;
    public int championId;
    public String gameType;
    public Stats stats;
    public String championName;
    public FellowPlayers fellowPlayers[];

    public GameInfo(){
        stats = new Stats();
        fellowPlayers = new FellowPlayers[9];
        for(int i =0;i<9;i++){
            fellowPlayers[i] = new FellowPlayers();
        }
    }

}

class FellowPlayers implements Serializable{
    public int championId;
    public int teamId;
    public int summonerId;
}

class Stats implements Serializable{
    public int totalDamageDealtToChampions;
    public int goldEarned;
    public int item0;
    public int item1;
    public int item2;
    public int item3;
    public int item4;

    public int item6;
    public int wardPlaced;
    public int totalDamageTaken;
    public int trueDamageDealtPlayer;
    public int physicalDamageDealtPlayer;
    public int trueDamageDealtToChampions;
    public int playerRole;
    public int playerPosition;
    public int level;
    public int totalUnitsHealed;
    public int totalDamageDealtToBuildings;
    public int magicDamageDealtToChampions;
    public int turretsKilled;
    public int magicDamageDealtPlayer;
    public int neutralMinionsKilledEnemyJungle;
    public int assists;
    public int magicDamageTaken;
    public int numDeaths;
    public int totalTimeCrowdControlDealt;
    public int largestMultiKill;
    public int physicalDamageTaken;
    public Boolean win;
    public int team;
    public int totalDamageDealt;
    public int totalHeal;
    public int minionsKilled;
    public int timePlayed;
    public int physicalDamageDealtToChampions;
    public int championsKilled;
    public int trueDamageTaken;
    public int neutralMinionsKilled;
    public int goldSpent;

}
