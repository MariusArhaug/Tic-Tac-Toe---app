package app;

import java.util.ArrayList;

public class Player {
	private String playerName; 
	private char type = ' ';
	private int moves = 0;
	private int wins = 0;
	private int draws = 0;
	private ArrayList<Integer> playerMoves = new ArrayList<>();
	
	public Player(String playerName, char type) {
		setPlayerName(playerName);
		setType(type);
	}
	

	public String getType() {
		return Character.toString(type);
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	void setWins(int wins) {
		this.wins = wins;
	}
	
	public int getWins() {
		return wins;
	}

	public void updateWins() {
		this.wins++;
	}
	
	
	public ArrayList<Integer> getPlayerMoves() {
		return playerMoves;
	}

	public void updatePlayerMoves(int newMove) {
		this.playerMoves.add(newMove);
	}
	
	public String toString() {
		return "Player: " + this.playerName + " Type: "+ Character.toString(this.type) + " Wins: " + this.wins + " Draws: " + this.draws; 
	}

	public int getDraws() {
		return draws;
	}

	public void updateDraws() {
		this.draws++;
	}
	
	void setDraws(int draws) {
		this.draws = draws;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
