package app;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class Game {
	private int legalMoves = 9;
	private boolean isRunning = false; 
	final static int[][] correctKeys = {{0,3,6},{1,4,7},{2,5,8},{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6}};
	private int[] winnerKey = null;
	//Winner combinations 				 //0v	 //1v	 //2v	 //3h	 //4h	 //5h	 //6a	 //7a
	
	private Player currentPlayer;
	private Player previousPlayer;
	
	
	public int[] getWinnerKey() {
		return this.winnerKey;
	}
	
	public void setWinnerKey(int[] value) {
		this.winnerKey = value;
	}
	
	public void updateLegalMoves() {
		this.legalMoves--; 
	}
		
	public void endGame() {
		if (!isRunning) {
			this.legalMoves = 0;
		}
	}
	
	public void resetGame() {
		this.legalMoves = 9;
		this.isRunning = false;
		
	}
	
	public int getLegalMoves() {
		return this.legalMoves;
	}
	
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	boolean checkCombo() {
		boolean hasWon = false;									//if all conditions are met set to true
		int counter;
		for (int[] key : correctKeys) {							//loops through each innerArray inside integer array
			counter = 0;				
			for (int j = 0; j < key.length; j++) {
				if (previousPlayer.getPlayerMoves().contains(key[j])) {	//checks if the player ArrayList
					counter++;											//contains the integer value at key[j]
				}
			}
			if (counter == key.length) {									//if all are correct the counter should be equal to three
				hasWon = true;									//stop loop and set value to true
				this.winnerKey = key;
				previousPlayer.updateWins();
				break;
			}
		}	
		return hasWon; 
	}
	
	public boolean checkDraw(ArrayList<Button> buttonList) {
		int counter = 0;
		for (Button button : buttonList) {
			if (!button.getText().equals("")) {
				counter++;
			}
		}
		return counter == 9; 
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getPreviousPlayer() {
		return previousPlayer;
	}

	public void setPreviousPlayer(Player previousPlayer) {
		this.previousPlayer = previousPlayer;
	}

	

}
