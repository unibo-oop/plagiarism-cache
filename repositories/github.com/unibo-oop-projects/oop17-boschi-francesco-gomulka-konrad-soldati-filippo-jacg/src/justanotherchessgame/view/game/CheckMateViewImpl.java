package justanotherchessgame.view.game;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import justanotherchessgame.util.GameResult;
import justanotherchessgame.util.ImageGenerator;

/**
 * Class used to show the chackmate view when the game end.
 */
public class CheckMateViewImpl implements CheckMateView {
    private final GameResult result;

    /**
     * Class constructor.
     * @param result is the game result: black win, white win, stale.
     */
    public CheckMateViewImpl(final GameResult result) {
        this.result = result;
    }

    @Override
    public final void showCheckMateView() {
        final Stage stage = new Stage();
        final BorderPane main = new BorderPane();
        main.setStyle("-fx-background-color:rgb(204, 255, 255)");
        main.setPrefSize(333, 80);
        String imgName, desc;
        if (result == GameResult.WHITE_WIN) {
            imgName = "blackCheckMate.png";
            desc = "WHITE WIN";
        } else if (result == GameResult.BLACK_WIN) {
            imgName = "whiteCheckMate.png";
            desc = "BLACK WIN";
        } else {
            imgName = "staleMate.png";
            desc = "STALE";
        }
        final ImageView imageView = ImageGenerator.generateImage(imgName);
        final ImageView imageView1 = ImageGenerator.generateImage(imgName);
        imageView.setFitHeight(300);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        imageView1.setFitHeight(300);
        imageView1.setFitWidth(50);
        imageView1.setPreserveRatio(true);
        final StackPane left = new StackPane(imageView);
        final StackPane right = new StackPane(imageView1);
        final StackPane center = new StackPane(generateTitle(desc));
        //bot.setStyle("-fx-background-color: rgb(207,181,59)");
        center.setPrefSize(133, 80);
        left.setPrefSize(100, 80);
        right.setPrefSize(100, 80);
        main.setRight(right);
        main.setLeft(left);
        main.setCenter(center);
        final Scene scene = new Scene(main);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("CHECKMATE");
        ImageGenerator.iconGenerator(stage, "Icon.png");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * Function used to create the "title" of the view.
     * @param name is the text of the title.
     * @return the graphic component used as title.
     */
    private static StackPane generateTitle(final String name) {
        final StackPane result = new StackPane();
        final Rectangle bg = new Rectangle(133, 80);
        bg.setFill(Color.rgb(230, 255, 255));

        final Text text = new Text(name);
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Terminator Two", FontWeight.SEMI_BOLD, 20));

        result.setAlignment(Pos.CENTER);
        result.getChildren().addAll(bg, text);
        return result;
    }
}
