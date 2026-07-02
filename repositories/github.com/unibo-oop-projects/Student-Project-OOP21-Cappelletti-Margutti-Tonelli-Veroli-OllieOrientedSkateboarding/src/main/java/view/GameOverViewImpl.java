package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.statistic.Statistics;

/**
 * 
 * Implementation of {@link GameOverView}.
 *
 */
public class GameOverViewImpl implements GameOverView {

    private static final int TITLE_FONT_SIZE = 90;
    private static final int TITLE_X = 150;
    private static final int TITLE_Y = 120;
    private static final String FAMILY_FONT_NAME = "Arial";
    private static final int STAT_FONT_SIZE = 40;
    private static final int ACTUAL_STAT_X = 100;
    private static final int TOTAL_STAT_X = 500;
    private static final int COIN_STAT_Y = 250;
    private static final int DISTANCE_STAT_Y = 300;
    private static final int BUTTON_Y = 355;
    private static final int PLAY_AGAIN_BUTTON_X = 325;
    private static final int SHOP_BUTTON_X = 450;
    private static final int SQUARE_WIDTH = 50;
    private static final int EXIT_FONT_SIZE = 18;
    private static final int EXIT_BUTTON_X = 700;
    private static final int EXIT_BUTTON_Y = 20;
    private static final int BUTTON_WIDTH = 100;

    private final View view;
    private final Stage stage;
    private final Pane pane;
    private final Statistics statistics;

    /**
     * Creates a new GameOverViewImpl.
     * @param view the {@link View} of the application.
     * @param stage the {@link Stage}.
     * @param pane the {@link Pane}.
     * @param statistics the {@link Statistics} of the game.
     */
    public GameOverViewImpl(final View view, final Stage stage, final Pane pane, final Statistics statistics) {
        super();
        this.view = view;
        this.stage = stage;
        this.pane = pane;
        this.statistics = statistics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {

        final Font statFont = Font.font(FAMILY_FONT_NAME, FontWeight.EXTRA_BOLD, STAT_FONT_SIZE);

        final ImageView playAgainImage = new ImageView(); 
        playAgainImage.setImage(new Image("PlayAgain.png"));
        playAgainImage.setFitHeight(SQUARE_WIDTH);
        playAgainImage.setPreserveRatio(true);

        final Button playAgain = new Button(); 
        playAgain.setLayoutX(PLAY_AGAIN_BUTTON_X);
        playAgain.setLayoutY(BUTTON_Y);
        playAgain.setPrefWidth(SQUARE_WIDTH);
        playAgain.setPrefHeight(SQUARE_WIDTH);
        playAgain.setGraphic(playAgainImage);
        playAgain.setOnAction(e -> {
            this.view.getController().start();
        });

        final ImageView shopImage = new ImageView();
        shopImage.setImage(new Image("ShopKart.png"));
        shopImage.setFitHeight(SQUARE_WIDTH);
        shopImage.setPreserveRatio(true);

        final Button shop = new Button(); 
        shop.setLayoutX(SHOP_BUTTON_X);
        shop.setLayoutY(BUTTON_Y);
        shop.setPrefWidth(SQUARE_WIDTH);
        shop.setPrefHeight(SQUARE_WIDTH);
        shop.setGraphic(shopImage);
        shop.setOnAction(e -> {
            this.view.shop();
        });

        final Button exit = new Button();
        exit.setLayoutX(EXIT_BUTTON_X);
        exit.setLayoutY(EXIT_BUTTON_Y);
        exit.setPrefWidth(BUTTON_WIDTH);
        exit.setTextAlignment(TextAlignment.CENTER);
        exit.setFont(new Font("Arial", EXIT_FONT_SIZE));
        exit.setText("EXIT");
        exit.setOnAction(e -> {
            stage.close();
        });

        final Text title = new Text("GAME OVER");
        title.setFont(new Font(TITLE_FONT_SIZE));
        title.setX(TITLE_X);
        title.setY(TITLE_Y);

        final Text actualCoinStat = new Text("Collected Coins: " + Integer.toString(this.statistics.getGameCoins()));
        actualCoinStat.setFont(statFont);
        actualCoinStat.setX(ACTUAL_STAT_X);
        actualCoinStat.setY(COIN_STAT_Y);

        final Text actualDistanceStat = new Text("Distance: " + Integer.toString(this.statistics.getDistance()));
        actualDistanceStat.setFont(statFont);
        actualDistanceStat.setX(ACTUAL_STAT_X);
        actualDistanceStat.setY(DISTANCE_STAT_Y);

        final Text totalCoinStat = new Text("Total Coins: " + Integer.toString(this.statistics.getTotalCoins()));
        totalCoinStat.setFont(statFont);
        totalCoinStat.setX(TOTAL_STAT_X);
        totalCoinStat.setY(COIN_STAT_Y);


        final Text recordDistanceStat = new Text("Record: " + Integer.toString(this.statistics.getRecordDistance()));
        recordDistanceStat.setFont(statFont);
        recordDistanceStat.setX(TOTAL_STAT_X);
        recordDistanceStat.setY(DISTANCE_STAT_Y);

        this.pane.getChildren().add(playAgain);
        this.pane.getChildren().add(shop);
        this.pane.getChildren().add(exit);
        this.pane.getChildren().add(title);
        this.pane.getChildren().add(actualDistanceStat);
        this.pane.getChildren().add(actualCoinStat);
        this.pane.getChildren().add(recordDistanceStat);
        this.pane.getChildren().add(totalCoinStat);
    }

}
