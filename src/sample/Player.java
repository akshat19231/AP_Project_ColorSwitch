package sample;

import java.io.Serializable;

public class Player implements Serializable {
    private Game myGame;
    private String Username;
    public Player(String s){
        this.Username=s;
    }
    public void setMyGame(Game g){
        this.myGame=g;
    }
    public String getUname(){
        return this.Username;
    }
}
