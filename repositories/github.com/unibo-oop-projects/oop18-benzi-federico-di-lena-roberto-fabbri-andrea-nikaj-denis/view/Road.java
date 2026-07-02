package view;

import java.util.List;
import controller.command.CommandController;
import controller.command.MoveDown;
import controller.command.MoveLeft;
import controller.command.MoveRight;
import controller.command.MoveUp;
import controller.command.Stop;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Entity;

public class Road {

	private static final String SCORE = "Score: ";
	private static final String SHIELD = "Shields: ";

	private final static double WINDOWS_WIDTH = 600;
	private final static double WINDOWS_HEIGHT = 700;

	private DrawEntities drawable;
	private CommandController cmd;
	public Scene scena;

	private BorderPane bpane = new BorderPane();
	private Pane mapPane = new Pane();
	private Pane horPane = new Pane();
	private VBox statBox = new VBox();
	private HBox life = new HBox();
	private StackPane stack = new StackPane();
	private Pane entityPane = new Pane();

	private Text textScore;
	private Text textShield;
	private ProgressBar health;

	public Road(final CommandController cmd) {
		this.drawable = new DrawEntities();
		this.cmd = cmd;
		health = new ProgressBar();

		textScore = new Text(SCORE);
		textShield = new Text(SHIELD);

		textShield.setFill(Paint.valueOf("#0066ff"));
		textScore.setFill(Paint.valueOf("#009933"));
		textScore.setFont(new Font("Verdana", 30));
		textShield.setFont(new Font("Verdana", 30));

		health.setPrefWidth(150);
		health.setPrefHeight(30);
		health.setProgress(0.22);

		life.getChildren().addAll(health);

		mapPane.setPrefHeight(700);
		mapPane.setPrefWidth(400);

		entityPane.setPrefHeight(700);
		entityPane.setPrefWidth(400);

		stack.setPrefHeight(700);
		stack.setPrefWidth(400);

		statBox.setPrefHeight(700);
		statBox.setPrefWidth(190);

		statBox.getChildren().addAll(life, textScore, textShield);

		stack.getChildren().addAll(mapPane, entityPane);

		Insets margin = new Insets(this.statBox.getPrefWidth() / 8);
		VBox.setMargin(life, margin);
		bpane.setCenter(stack);
		bpane.setRight(statBox);
		bpane.setTop(horPane);
		this.scena = new Scene(bpane, WINDOWS_WIDTH, WINDOWS_HEIGHT);
		this.onPress();
		this.onRelease();
	}

	/**
	 * Input handler on key press
	 */
	public void onPress() {
		this.scena.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.LEFT) {
				cmd.addCommand(new MoveLeft());
			} else if (e.getCode() == KeyCode.RIGHT) {
				cmd.addCommand(new MoveRight());
			} else if (e.getCode() == KeyCode.UP) {
				cmd.addCommand(new MoveUp());
			} else if (e.getCode() == KeyCode.DOWN) {
				cmd.addCommand(new MoveDown());
			}
		});
	}

	/**
	 * Input handler on key release
	 */
	public void onRelease() {
		this.scena.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.LEFT) {
				cmd.addCommand(new Stop());
			} else if (e.getCode() == KeyCode.RIGHT) {
				cmd.addCommand(new Stop());
			} else if (e.getCode() == KeyCode.UP) {
				cmd.addCommand(new Stop());
			} else if (e.getCode() == KeyCode.DOWN) {
				cmd.addCommand(new Stop());
			}
		});
	}

	/**
	 * Draw enemies on this stage
	 * 
	 * @param listEntity enemies on the stage
	 */
	public void drawEntity(List<Entity> listEntity) {
		this.drawable.drawEntity(this.entityPane, listEntity);
	}

	/**
	 * Update the stats in the pane
	 * 
	 * @param health
	 * @param shield
	 * @param score
	 */
	public void updateText(final double health, final int shield, final int score) {
		this.health.setProgress(health);
		this.textShield.setText("Shield: " + String.valueOf(shield));
		this.textScore.setText("Score: " + String.valueOf(score));
	}

	/**
	 * 
	 * @return this scene
	 */
	public Scene get() {
		return this.scena;
	}

	/**
	 * Initialize road background
	 */
	public void initializeRoad() {
		this.drawable.drawStarters(this.mapPane);
	}

}
