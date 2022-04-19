package Model;

import java.util.ArrayList;

public class Game {
    private Map map;
    private ArrayList<Player> players;
    private Player currentPlayer;
    int turnCount, playerTurn;

    public Game(Map map, ArrayList<Player> players) {
        this.map = map;
        this.players = players;
        turnCount = 1;
        playerTurn = 0;
        currentPlayer = players.get(0);
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
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void nextTurn() {
        // TODO: 4/17/2022
    }

    public void passTurn() {
        // TODO: 4/17/2022
    }
}
