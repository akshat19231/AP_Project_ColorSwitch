package sample;

import java.io.Serializable;
import java.util.HashMap;

public class App implements Serializable {
    private LeaderboardMain leaderB;
    private int totalStars;
    HashMap <String, Game> gameMap=new HashMap<String, Game>();
    public App(){
        this.gameMap = new HashMap<String, Game>();
        this.leaderB = new LeaderboardMain();
        this.totalStars = 0;
    }
    public void addStars(){
        this.totalStars++;
    }
    public void removeStars(int val){
        this.totalStars-=val;
    }
    public int getStars(){
        return this.totalStars;
    }
    public Game getGame(String name){
        return this.gameMap.get(name);
    }
    public void addGame(Game g){
        this.gameMap.put(g.getPlayer().getUname(),g);
    }
    public HashMap<String, Game>  getGameMap(){
        return this.gameMap;
    }
    public LeaderboardMain getLeaderBoard(){
        return this.leaderB;
    }
}
