package view.windows;

import controller.game.GameControllerImpl;
import game.logics.GameManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class EndGameViewImpl extends Pane implements EndGameView {

  private final Label textFinal;
  private final Label txtLogin;
  private final Rectangle gameFinish;
  private final Button turnBack;
  private final Button reset;
  private final Button save;
  private final TextArea textArea;
  private final GameControllerImpl controller;
  private final GameManager.GameStatus status;
  private static final int SCREEN_WIDTH = 900;
  private static final int SCREEN_HEIGTH = 700;

  /**
   * Constructor.
   */
  public EndGameViewImpl(final GameControllerImpl gameController) {
    this.textFinal = new Label();
    this.txtLogin = new Label();
    this.gameFinish = new Rectangle();
    this.turnBack = new Button();
    this.reset = new Button();
    this.save = new Button();
    this.textArea = new TextArea();
    this.status = gameController.getGameStatus();
    this.gameFinish.setWidth(SCREEN_WIDTH);
    this.gameFinish.setHeight(SCREEN_HEIGTH);
    this.controller = gameController;
  }

  @Override
  public void setWin(final int score) {
    this.gameFinish.setFill(Color.ORANGE);
    this.textFinal.setPrefSize(500, 200);
    this.textFinal.setText("  YOU WIN!\nSCORE: " + score);
    this.textFinal.setBackground(getBackground());
    this.textFinal.setAlignment(Pos.CENTER);
    this.textFinal.setLayoutX(SCREEN_WIDTH / 2 - 250);
    this.textFinal.setLayoutY(180);
    this.textFinal.getStyleClass().add("game-title");
    this.gameFinish.setOpacity(0.7);
    endGame();
  }

  @Override
  public void setLost(final int score) {
    this.gameFinish.setFill(Color.BLACK);
    this.textFinal.setAlignment(Pos.CENTER);
    this.textFinal.setText("GAME \n OVER");
    this.textFinal.setLayoutX(320);
    this.textFinal.setLayoutY(150);
    this.textFinal.getStyleClass().add("gameover-title");
    this.gameFinish.setOpacity(0.7);
    endGame();
  }

  /**
   * This method set the final scene of the game. if the user reaches the value
   * 2048 (the status of the game is Win) the method set the win scene, in
   * reverse if the user lost the method set the lost scene.
   */
  private void endGame() {
    this.txtLogin.setText("INSERT YOUR USERNAME HERE\n       TO SAVE YOUR SCORE:");
    this.txtLogin.setPrefSize(500, 100);
    this.txtLogin.setAlignment(Pos.CENTER);
    this.txtLogin.setLayoutX(SCREEN_WIDTH / 2 - 250);
    this.txtLogin.setLayoutY(450);
    switch (this.status) {
      case WIN:
        this.txtLogin.getStyleClass().add("gamewin-words");
        break;
      case LOST:
        this.txtLogin.getStyleClass().add("gameover-words");
        break;
      default:
        break;
    }
    this.getChildren().add(gameFinish);
    insertUsername();
    this.getChildren().add(textFinal);
    this.getChildren().add(txtLogin);
    setButtons();
  }

  /**
   * This method set the button of the view. Respect to the other scene this
   * method adds the button to save the result of the game.
   * 
   */
  private void setButtons() {
    this.turnBack.setPrefSize(80, 80);
    this.turnBack.setLayoutX(800);
    this.turnBack.setLayoutY(590);
    this.turnBack.setText("TURN\nBACK");
    this.turnBack.getStyleClass().add("turnback-button");
    this.turnBack.setOnAction(e -> controller.pressTB());
    this.getChildren().add(turnBack);

    this.reset.setPrefSize(80, 80);
    this.reset.setLayoutX(800);
    this.reset.setLayoutY(490);
    this.reset.setText("RESET");
    this.reset.getStyleClass().add("reset-button");
    this.reset.setOnAction(e -> controller.pressRT(e));
    this.getChildren().add(reset);

    this.save.setPrefSize(70, 40);
    this.save.setLayoutX(600);
    this.save.setLayoutY(580);
    this.save.setText("SAVE");
    this.save.getStyleClass().add("reset-button");
    this.save.setOnAction(e -> pressSave());
    this.getChildren().add(save);
  }

  /**
   * This method allows to save the result in the ranking and it will be linked
   * to the name insert in the login.
   */
  private void pressSave() {
    this.controller.addRankingScore(textArea.getText().toString());
    this.save.setDisable(true);
    System.out.println("Salvato");
  }

  /**
   * This method allows to login, the user can enter his name.
   */
  private void insertUsername() {
    textArea.setPrefSize(350, 40);
    textArea.setFont(Font.font(20));
    textArea.setLayoutX(SCREEN_WIDTH / 2 - 220);
    textArea.setLayoutY(580);
    textArea.setAccessibleText(getAccessibleText());
    this.getChildren().add(textArea);
  }

}
