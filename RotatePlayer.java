package app;

public class RotatePlayer {

	private Game game;
	
	private Player currentPlayer;
	private Player previousPlayer;
	
	private Player playerOne;
	private Player playerTwo;
	
	public RotatePlayer(Player playerOne, Player playerTwo, Game game) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.currentPlayer = this.playerOne;
		this.setPreviousPlayer(this.playerTwo);
		
		this.game = game;
		
		this.game.setCurrentPlayer(this.playerOne);
		this.game.setPreviousPlayer(this.playerTwo);
	}
	
	public void rotateTurn() {
		boolean rotated = false; 
		if (this.currentPlayer.getPlayerName().equals(playerOne.getPlayerName()) && !rotated){
			this.setPreviousPlayer(playerOne);
			this.currentPlayer = playerTwo;
			
			game.setPreviousPlayer(playerOne);
			game.setCurrentPlayer(playerTwo);
			rotated = true;
		}
		
		else if (this.currentPlayer.getPlayerName().equals(playerTwo.getPlayerName()) && !rotated){
			this.setPreviousPlayer(playerTwo);
			this.currentPlayer = playerOne;
			
			game.setPreviousPlayer(playerTwo);
			game.setCurrentPlayer(playerOne);
			rotated = true;
		}		
	}

	public Player getPreviousPlayer() {
		return previousPlayer;
	}

	public void setPreviousPlayer(Player previousPlayer) {
		this.previousPlayer = previousPlayer;
	}
}
