package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardMain implements Serializable {
    private ArrayList<Game> games;
    public LeaderboardMain(){
        this.games=new ArrayList<Game>();
    }
    public void update(Game g){
        games.add(g);
        Collections.sort(this.games, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                if(o1.getScore()>o2.getScore()){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
    }
    public ArrayList<Game> getBoard(){
        return this.games;
    }
}
