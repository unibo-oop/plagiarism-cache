package justanotherchessgame.view.utils;


import java.util.Arrays;
import java.util.List;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import justanotherchessgame.model.Bishop;
import justanotherchessgame.model.Knight;
import justanotherchessgame.model.Piece;
import justanotherchessgame.model.Queen;
import justanotherchessgame.model.Rook;
import justanotherchessgame.util.ImageGenerator;

/**
 * Class used to show the view when a piece needs to be promoted. Is a static class.
 */
public final class PiecePromotionView {

    private static Class<? extends Piece> returnValue;

    private PiecePromotionView() {
    };

    /**
     * Function used to create the promotion view.
     * @param color is the color of the piece that will be promoted.
     * @return the class of the promoted piece chosen by the user.
     */
    public static Class<? extends Piece> showPromotionView(final boolean color) {
        final Stage stage = new Stage();
        final BorderPane root = new BorderPane();
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        ImageGenerator.iconGenerator(stage, "Icon.png");
        stage.setTitle("PAWN PROMOTION");
        root.getStylesheets().add("justAnotherChessGame/view/style.css");
        root.setTop(generateTitle("Promote your pawn"));
        List<String> list;
        final ListView<String> listview = new ListView<String>();
        root.setCenter(listview);
        listview.setOrientation(Orientation.HORIZONTAL);
        if (color) {
            list = Arrays.asList("white_tQueen", "white_tRook", "white_tKnight", "white_tBishop");
        } else {
            list = Arrays.asList("black_tQueen", "black_tRook", "black_tKnight", "black_tBishop");
        }
        for (final String s : list) {
            listview.getItems().add(s);
        }
        listview.getStyleClass().add("indList");
        listview.setCellFactory(e -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(final String item, final boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    imageView = ImageGenerator.generateImage(item + ".png");
                    setText("      ");
                    setGraphic(imageView);
                }
            }
        });
        listview.setOnMouseEntered(event -> {
            listview.getScene().setCursor(Cursor.HAND);
        });
        listview.setOnMouseExited(event -> {
            listview.getScene().setCursor(Cursor.DEFAULT);
        });
        returnValue = null;
        listview.setOnMouseClicked(event -> {
            if (listview.getSelectionModel().getSelectedItem() != null) {
                switch (listview.getSelectionModel().getSelectedIndex()) {
                case 0: 
                    returnValue = Queen.class;
                    break;
                case 1: 
                    returnValue = Rook.class;
                    break;
                case 2: 
                    returnValue = Knight.class;
                    break;
                case 3: 
                    returnValue = Bishop.class;
                    break;
                default: 
                    returnValue = null;
                    break;
                }
                root.getScene().getWindow().hide();
            }
        });
        final Image img = ImageGenerator.generateImage("wood.jpg").getImage();
        listview.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        listview.setPrefSize(370, 150);
        root.getTop().prefHeight(60);
        root.getTop().prefWidth(370);
        root.setPrefSize(370, 210);
        //root.getChildren().add(listview);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        return returnValue;
    }

    /**
     * Function used to create the "title" of the view.
     * @param name is the text of the title.
     * @return the graphic component used as title.
     */
    private static StackPane generateTitle(final String name) {
        final StackPane result = new StackPane();
        final Rectangle bg = new Rectangle(370, 60);
        bg.setStroke(Color.SADDLEBROWN);
        bg.setStrokeWidth(2);
        bg.setFill(Color.rgb(222, 184, 135));

        final Text text = new Text(name);
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Terminator Two", FontWeight.SEMI_BOLD, 20));

        result.setAlignment(Pos.CENTER);
        result.getChildren().addAll(bg, text);
        return result;
    }
}
