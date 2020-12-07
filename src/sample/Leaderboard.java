package sample;

import java.util.*;
import java.io.*;

public class Leaderboard{
    private ArrayList<Game> games;

    public Leaderboard(){
        games = new ArrayList<Game>();
    }

    public void addGame(Game game){
        games.add(game);
        update();
    }

    public void update(){
        Collections.sort(games);
    }
    public ArrayList<Game> getGames(){
        return games;
    }
}

