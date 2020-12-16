package sample;

import java.io.Serializable;
import java.util.HashMap;

public class App implements Serializable {
    private LeaderboardMain leaderB;
    HashMap <String, Game> gameMap=new HashMap<String, Game>();
    public App(){
        this.gameMap = new HashMap<String, Game>();
        this.leaderB = new LeaderboardMain();
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
