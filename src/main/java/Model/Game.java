package Model;

import java.util.ArrayList;

public class Game {
    private Map map;
    private ArrayList<Player> players;
    private int currentPlayerID;
    int turnCount, playerTurn;

    public Game(Map map, ArrayList<Player> players) {
        this.map = map;
        this.players = players;
        turnCount = 1;
        playerTurn = 0;
        currentPlayerID = 0;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerID);
    }

    public void nextTurn() {
        currentPlayerID = (currentPlayerID + 1) % players.size();
    }

}
