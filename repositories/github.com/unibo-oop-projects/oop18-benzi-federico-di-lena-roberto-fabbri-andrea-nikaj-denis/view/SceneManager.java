package view;

import java.io.IOException;

import controller.Game;
import controller.command.CommandController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This class is the controller of the all scenes that are present in the
 * application
 */
public class SceneManager {

	private final static double GAME_WIDTH = 600;
	private final static double GAME_HEIGHT = 700;
	private final static double MENU_WIDTH = 400;
	private final static double MENU_HEIGHT = 650;
	private final static double LOGINWINDOWS_WIDTH = 400;
	private final static double LOGINWINDOWS_HEIGHT = 250;
	private String nick = "";

	private View v = new ViewImpl(this);
	private Stage gameOver;
	private Stage game;

	public TextField txtNick;

	public String getNickname() {
		return this.nick;
	}

	/**
	 * This method switch the scene to the high score scene when high score is
	 * pressed
	 *
	 * @param event
	 * @throws IOException
	 */
	public void goHighScore(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.HIGHSCORE.getPath()));
		Scene highScoreScene = new Scene(root);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(highScoreScene);
		window.show();
	}

	/**
	 * This method switch the scene to the login scene when star is pressed
	 *
	 * @param event
	 * @throws IOException
	 */
	public void goLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.LOGIN.getPath()));
		Scene loginScene = new Scene(root);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setHeight(LOGINWINDOWS_HEIGHT);
		window.setWidth(LOGINWINDOWS_WIDTH);
		window.setScene(loginScene);
		window.show();
	}

	/**
	 * This method switch the scene to the help scene when help is pressed
	 *
	 * @param event
	 * @throws IOException
	 */
	public void goHelp(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.HELP.getPath()));
		Scene helpScene = new Scene(root);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(helpScene);
		window.show();
	}

	/**
	 * This method switch the scene to the menu scene when back is pressed
	 *
	 * @param event
	 * @throws IOException
	 */
	public void goBack(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.MENU.getPath()));
		Scene menuScene = new Scene(root);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setHeight(MENU_HEIGHT);
		window.setWidth(MENU_WIDTH);
		window.setScene(menuScene);
		window.show();
	}

	/**
	 * This method switch the scene to game over scene
	 *
	 * @throws IOException
	 */
	public void gameOver() throws IOException {
		this.game.close();
		Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.GAMEOVER.getPath()));
		Scene menuScene = new Scene(root);
		this.gameOver = new Stage();
		this.gameOver.setHeight(MENU_HEIGHT);
		this.gameOver.setWidth(MENU_WIDTH);
		this.gameOver.setScene(menuScene);
		this.gameOver.show();
	}

	/**
	 * This method switch the scene to the game scene,than start the game when start
	 * game is pressed
	 *
	 * @param event
	 * @throws IOException
	 */
	public void start(ActionEvent event) {
		CommandController cmd = new CommandController();
		Game game = new Game(v, cmd);
		game.start();

		this.nick = txtNick.getText();
		final Road gameScreen = new Road(cmd);
		v.setScene(gameScreen);
		this.game = (Stage) ((Node) event.getSource()).getScene().getWindow();
		this.game.setHeight(GAME_HEIGHT);
		this.game.setWidth(GAME_WIDTH);
		this.game.setScene(gameScreen.get());
		this.game.show();

	}

	/**
	 * This method close the application when the button exit is pressed
	 *
	 * @param event
	 * @throws IOException
	 */
	public void exitGame(ActionEvent event) throws IOException {
		Platform.exit();
	}

}
