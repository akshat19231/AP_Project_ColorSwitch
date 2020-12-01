package sample;

import java.util.ArrayList;

public class Game {
    private Player player;
    private int level;
    private int GameId;
    private ArrayList<gameElements> ar;
    public void Game(int idx){
        this.GameId=idx;
        this.level=1;
    }
    public void setPlayer(Player p){
        this.player=p;
        this.ar=new ArrayList<gameElements>();
    }
    public void levelUp(){
        this.level++;
    }
    public void initialiseObs(){

    }
}
