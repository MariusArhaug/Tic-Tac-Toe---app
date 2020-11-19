package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	@Override
	public void start(final Stage primaryStage) throws Exception {
		//TicTacToeController mainController = new TicTacToeController();
		primaryStage.setTitle("My Application");
		Scene scene = new Scene(FXMLLoader.load(App.class.getResource("App.fxml")));		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(final String[] args) {
		App.launch(args);
	}
}