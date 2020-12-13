package sample;

import java.io.Serializable;
import java.util.HashMap;

public class App implements Serializable {
    HashMap <String, Game> gameMap=new HashMap<String, Game>();
    public App(){
        gameMap = new HashMap<String, Game>();
    }
    public Game getGame(String name){
        return gameMap.get(name);
    }
    public void addGame(Game g){
        gameMap.put(g.getPlayer().getUname(),g);
    }
    public HashMap<String, Game>  getGameMap(){
        return this.gameMap;
    }
}
