package view;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;
/**
 * 
 * This class represent the in-game view.
 *
 */
public class GameBoard extends Pane {

    private static final String URL_IMAGE = "images/wordImage.png";
    private static final String URL_BACKGROUND = "images/gameBackground.jpg";
    private static final String URL_PLAYER = "images/gamePlayer.png";
    private static final String FONT_NAME = "Times New Roman";

    private static final double FONT_PERCENTAGE = 0.025;
    private static final double PERCENTAGE = 0.2;

    private final double fontSize;
    private final double playerSize;

    private final Image cloud;
    private final ImageView unicorn;

    public GameBoard(final double width, final double height) {

        this.resize(width, height);
        this.fontSize = this.getWidth() * FONT_PERCENTAGE;
        this.playerSize = this.getHeight() * PERCENTAGE;
        this.cloud = new Image(URL_IMAGE);
        this.unicorn = new ImageView(new Image(URL_PLAYER));
        this.unicorn.setPreserveRatio(true);
        this.unicorn.setFitHeight(playerSize);
        this.setBorder(new Border(new BorderStroke(Color.LIGHTPINK, BorderStrokeStyle.DOTTED, null, BorderStroke.THICK)));
        this.setBackground(createBackground());

    }

    /**
     * 
     * @param words
     * @param character
     *                      the entities to refresh
     * @param worldX
     *                      the number of x coordinates to scale from
     * @param worldY
     *                      the number of y coordinates to scale from
     */
    public void refresh(final Map<Pair<Double, Double>, Pair<String, Boolean>> words, final Pair<Double, Double> character,
            final double worldX, final double worldY) {

        final double yScale = (this.getHeight() - this.playerSize) / worldY;
        final double xScale = (this.getWidth() * 0.8) / worldX;

        this.getChildren().clear();
        //I need to sort the set in order to solve graphical bug
        for (final Entry<Pair<Double, Double>, Pair<String, Boolean>> w : words.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e1.getKey().getKey(), e2.getKey().getKey())).collect(Collectors.toList())) {
            final Pair<Double, Double> position = w.getKey();
            this.getChildren().add(wordGraphics(w.getValue().getKey(), position.getKey() * xScale, position.getValue() * yScale,
                    w.getValue().getValue()));
        }
        this.getChildren().add(playerGraphics(character.getKey() * xScale, character.getValue() * yScale));
    }

    /*
     * Method to get the chosen graphic representation for the words
     */
    private StackPane wordGraphics(final String word, final double x, final double y, final boolean selected) {

        final StackPane panel = new StackPane();
        final Text text = new Text(word);
        final ImageView image = new ImageView(this.cloud);
        //text settings
        text.setFont(new Font(FONT_NAME, fontSize));
        text.setFill(selected ? Color.MEDIUMVIOLETRED : Color.WHITESMOKE);
        text.setStroke(Color.BLACK);
        //image settings
        image.setPreserveRatio(true);
        image.setFitWidth(text.getBoundsInLocal().getWidth() + 2 * fontSize);
        //panel settings
        panel.setAlignment(Pos.CENTER);
        panel.setLayoutX(x);
        panel.setLayoutY(y);
        panel.getChildren().addAll(image, text);

        return panel;
    }
    /*
     * Method to get the chosen graphics for the player
     */
    private ImageView playerGraphics(final double x, final double y) {

        this.unicorn.setLayoutX(x);
        this.unicorn.setLayoutY(y);

        return this.unicorn;
    }

    private Background createBackground() {

        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(new Image(URL_BACKGROUND)), CornerRadii.EMPTY,
                Insets.EMPTY);
        return new Background(backgroundFill);
    }

}
