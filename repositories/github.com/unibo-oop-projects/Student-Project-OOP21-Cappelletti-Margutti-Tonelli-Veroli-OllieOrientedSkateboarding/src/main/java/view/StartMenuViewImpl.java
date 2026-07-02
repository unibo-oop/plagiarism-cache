package view;
 
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
 
public final class StartMenuViewImpl implements StartMenuView {

    private static final double GAME_SCREEN_WIDTH = 854.0;
    private static final double GAME_SCREEN_HEIGHT = 445.0;
    private static final int FONT_SIZE = 18;
    private static final int BUTTON_WIDTH = 150;
    private static final int ALL_BUTTON_X = 340;
    private static final int START_BUTTON_Y = 300;
    private static final int EXIT_BUTTON_Y = 350;
    private static final String FAMILY_FONT_NAME = "Arial";
    private static final int TITLE_FONT_SIZE = 90;
    private static final int TITLE_X = 330;
    private static final int TITLE_Y = 130;
    private static final int SUBTITLE_FONT_SIZE = 45;
    private static final int SUBTITLE_X = 20;
    private static final int SUBTITLE_Y = 190;
    private static final int INSTRUCTION_FONT_SIZE = 25;
    private static final int INSTRUCTIONS_X = 270;
    private static final int INSTRUCTIONS_Y = 240;

    private final View view;
    private final Stage stage;
    private final Pane pane;

    /**
     * Creates a new StartMenuViewImpl.
     * @param view the {@link View} of the application.
     * @param stage the {@link Stage}.
     * @param pane the {@link Pane}.
     */
    public StartMenuViewImpl(final View view, final Stage stage, final Pane pane) {
        super();
        this.view = view;
        this.stage = stage;
        this.pane = pane;
        final Image backImage = new Image("GameScreen.png", GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, true, true);
        final BackgroundImage background = new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.pane.setBackground(new Background(background));
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        final Button start = new Button();
        start.setLayoutX(ALL_BUTTON_X);
        start.setLayoutY(START_BUTTON_Y);
        start.setPrefWidth(BUTTON_WIDTH);
        start.setTextAlignment(TextAlignment.CENTER);
        start.setFont(new Font("Arial", FONT_SIZE));
        start.setText("START");
        start.setOnAction(e -> {
            pane.getChildren().clear();
            this.view.getController().start();
        });
 
        final Button exit = new Button();
        exit.setLayoutX(ALL_BUTTON_X);
        exit.setLayoutY(EXIT_BUTTON_Y);
        exit.setPrefWidth(BUTTON_WIDTH);
        exit.setTextAlignment(TextAlignment.CENTER);
        exit.setFont(new Font("Arial", FONT_SIZE));
        exit.setText("EXIT");
        exit.setOnAction(e -> {
            stage.close();
        });

        final Text title = new Text("OOS");
        title.setFont(Font.font(FAMILY_FONT_NAME, FontWeight.EXTRA_BOLD, TITLE_FONT_SIZE));
        title.setX(TITLE_X);
        title.setY(TITLE_Y);

        final Text subtitle = new Text("OLLIE ORIENTED SKATEBOARDING");
        subtitle.setFont(Font.font(FAMILY_FONT_NAME, FontWeight.EXTRA_BOLD, SUBTITLE_FONT_SIZE));
        subtitle.setX(SUBTITLE_X);
        subtitle.setY(SUBTITLE_Y);

        final Text instructions = new Text("Press SPACEBAR to jump");
        instructions.setFont(Font.font(FAMILY_FONT_NAME, FontWeight.EXTRA_BOLD, INSTRUCTION_FONT_SIZE));
        instructions.setX(INSTRUCTIONS_X);
        instructions.setY(INSTRUCTIONS_Y);

        this.pane.getChildren().add(start);
        this.pane.getChildren().add(exit);
        this.pane.getChildren().add(title);
        this.pane.getChildren().add(subtitle);
        this.pane.getChildren().add(instructions);
    }
 
}
