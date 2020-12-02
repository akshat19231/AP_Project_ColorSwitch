package sample;

public class Player {
    private Game myGame;
    private String Username;
    public Player(String s){
        this.Username=s;
    }
    public void setMyGame(Game g){
        this.myGame=g;
    }
}
