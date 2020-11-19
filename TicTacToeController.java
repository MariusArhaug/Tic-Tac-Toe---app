package app;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TicTacToeController implements Initializable{
	@FXML Button btnZero;
	@FXML Button btnOne;
	@FXML Button btnTwo;
	@FXML Button btnThree;
	@FXML Button btnFour;
	@FXML Button btnFive;
	@FXML Button btnSix;
	@FXML Button btnSeven;
	@FXML Button btnEight;
	@FXML Button resetBtn;
	@FXML Button hideStartWindow;
	@FXML Button saveButton;
	
	@FXML Label currentPlayerTag;
	@FXML Label infoLabel;
	@FXML Label playerOneWins;
	@FXML Label playerOneDraws;
	@FXML Label playerTwoWins;
	@FXML Label playerTwoDraws;
	@FXML Label playerOneLabel;
	@FXML Label playerTwoLabel;
	@FXML Label saveErrorLabel;
	
	@FXML TextField playerOneName;
	@FXML TextField playerTwoName;
	@FXML TextField saveName;
	@FXML TextField fileName;
	
	@FXML AnchorPane startWindow;
	@FXML AnchorPane gameWindow;
	@FXML AnchorPane saveGameWindow;
	
	
	@FXML private ArrayList<Button> buttonList;

	Player playerOne = new Player("Player One", 'X');
	Player playerTwo = new Player("Player Two", 'O');
	
	RotatePlayer gameRotator;
	
	Player currentPlayer;
	Player previousPlayer;
	
	Game game = new Game();
	UpdateFile fileUpdater = new UpdateFile();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buttonList = new ArrayList<Button>(Arrays.asList(btnZero,btnOne,btnTwo,btnThree,btnFour,btnFive,btnSix,btnSeven,btnEight));
	}
	
	@FXML public void showSaveGameWindow() {
		saveGameWindow.setStyle("visibility: visible");
		gameWindow.setStyle("visibility: hidden");
	}
	
	private int pressedSaved = 0;
	@FXML public void saveGame() throws IllegalArgumentException {
		if (saveName.getText() == null) {
			throw new IllegalArgumentException("You must give the save a filename!");
		}
		
		
		String fileName = saveName.getText();
		File savedFile = fileUpdater.readFile(fileName);
		if (savedFile != null) {
			pressedSaved++;
			saveErrorLabel.setStyle("-fx-text-fill: red");
			saveErrorLabel.setText("this file already exists, \n Press \"Save Game\" again to overwrite");
			
		}
		if(savedFile == null || pressedSaved == 2) {
			saveToFile(fileName);
			saveGameWindow.setStyle("visibility: hidden");
			gameWindow.setStyle("visibility: visible");
			pressedSaved = 0;
		}
	}
	
	@FXML public void loadGame() throws IllegalArgumentException {
		if (fileName.getText() == null) {
			throw new IllegalArgumentException("You must give the save a filename!");
		}
		
		loadFile(fileName.getText());

	}
	
	private void loadFile(String name) {
		File savedFile = fileUpdater.readFile(name);
	
		if (savedFile != null) {
			playerOne.setPlayerName(savedFile.getP1Name());
			playerTwo.setPlayerName(savedFile.getP2Name());
			
			playerOne.setDraws(savedFile.getP1Draws());
			playerTwo.setDraws(savedFile.getP2Draws());
			
			playerOne.setWins(savedFile.getP1Wins());
			playerTwo.setWins(savedFile.getP2Wins());

			hideStartWindow();
		}
	}
	
	
	private void saveToFile(String name) {
		File file = new File(name, playerOne.getPlayerName(), playerTwo.getPlayerName(), playerOne.getWins(), playerOne.getDraws(), playerTwo.getWins(), playerTwo.getDraws());
		fileUpdater.printToFile(file.getFileName(), file);
	}

	@FXML void hideStartWindow() {
		startWindow.setStyle("visibility: hidden");
		gameWindow.setStyle("visibility: visible");
		
		if (!playerOneName.getText().equals("")) {
			playerOne.setPlayerName(playerOneName.getText());
		}
		
		if (!playerTwoName.getText().equals("")) {
			playerTwo.setPlayerName(playerTwoName.getText());
		} 
		initGame();
		updateLabels();
	}
	
	private void updateLabels() {
		playerOneLabel.setText(playerOne.getPlayerName());
		playerOneWins.setText("Wins: " + playerOne.getWins());
		playerOneDraws.setText("Draws: " + playerOne.getDraws());
		
		playerTwoLabel.setText(playerTwo.getPlayerName());
		playerTwoWins.setText("Wins: " + playerTwo.getWins());
		playerTwoDraws.setText("Draws: " + playerTwo.getDraws());
	}
	
	void initGame() {
		for (Button button : buttonList) {
			button.setText("");
			button.setStyle("-fx-text-fill: black;");
			button.setDisable(false);
			button.setOnAction(e -> updateGame(button));	//adds an on action to every button 
		}
		
		gameRotator = new RotatePlayer(playerOne, playerTwo, game);
		
		updateCurrentPlayerLabel(game.getCurrentPlayer().getPlayerName());
		game.setRunning(true);
		playerOneLabel.setText(playerOne.getPlayerName());
		playerTwoLabel.setText(playerTwo.getPlayerName());
	}
	
	@FXML
	public void restartGame() {
		game.resetGame();
		game.setWinnerKey(null);
		
		playerOne.getPlayerMoves().clear();
		playerTwo.getPlayerMoves().clear();
		infoLabel.setText("");
		saveName.setStyle("visibility: hidden");
		saveButton.setStyle("visibility: hidden");
		
		gameRotator.rotateTurn();
		initGame();
	}
	
	void updateGame(Button buttonElement) throws IllegalStateException{
		int buttonId = Integer.parseInt(buttonElement.getId());
	
		if (playerOne.getPlayerMoves().contains(buttonId) || playerTwo.getPlayerMoves().contains(buttonId)) {	
			throw new IllegalStateException("This square is already checked");
		} 
		
		game.updateLegalMoves();
		updatePlayer(buttonId);
		updateField(buttonElement);
		gameRotator.rotateTurn();
		updateCurrentPlayerLabel(game.getCurrentPlayer().getPlayerName());
		checkBoard();
	}

	void updatePlayer(int buttonId) {
		game.getCurrentPlayer().updatePlayerMoves(buttonId);
	}	
	
	void updateCurrentPlayerLabel(String string) {
		currentPlayerTag.setText(string);
	}
	
	void updateField(Button button) {
		button.setText(game.getCurrentPlayer().getType());
	}

	private void checkBoard() {
		if (game.checkCombo()) {
			playerOneWins.setText("Wins: " + playerOne.getWins());
			playerTwoWins.setText("Wins: " + playerTwo.getWins());
			
			infoLabel.setText(game.getPreviousPlayer().getPlayerName() + " Has won!");
			
			updateCurrentPlayerLabel("");
			showWinnerCombo(game.getWinnerKey());
			endGame();
		}
	
		if (game.checkDraw(buttonList)) {
			updateCurrentPlayerLabel("");
			endGame();
			
			playerOne.updateDraws();
			playerTwo.updateDraws();
			infoLabel.setText("Its a draw!");
			playerOneDraws.setText("Draws: " + playerOne.getDraws());
			playerTwoDraws.setText("Draws: " + playerTwo.getDraws());
		}
	}
	

	void endGame() {
		for (Button button : buttonList) {
			button.setDisable(true);
		}
		saveName.setStyle("visibility: visible");
		saveButton.setStyle("visibility: visible");
		game.setRunning(false);
		game.endGame();
	}
	
	void showWinnerCombo(int[] key) {
		for (int i : key) {
			buttonList.get(i).setStyle("-fx-text-fill: green;");
		}
	}
}
