package justanotherchessgame.view.game;


import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import justanotherchessgame.model.Main;
import justanotherchessgame.model.Piece;
import justanotherchessgame.util.ImageGenerator;
import justanotherchessgame.util.PieceImageGenerator;
import justanotherchessgame.util.Point;
/**
 * Class used to draw and manage every box of the chessboard.
 */
public class BoxViewImpl extends Button implements BoxView {

    private Piece piece;
    private final int x;
    private final int y;
    private ImageView img;
    private static double size = Math.min(Main.getStageHeight(), Main.getStageWidth() * 0.5);

    /**
     * Class constructor.
     * @param color is the color of the box: dark or light.
     * @param x is the first coordinate of the box.
     * @param y is the second coordinate of the box.
     */
    public BoxViewImpl(final boolean color, final int x, final int y) {
        super();
        this.x = x;
        this.y = y;
        this.piece = null;
        this.setStyle("-fx-background-radius: 0em;");
        this.setPrefSize(size / 16, size / 16);
        if (color) {
            this.getStyleClass().add("whiteButton");
        } else {
            this.getStyleClass().add("blackButton");
        }
    }

    @Override
    public final boolean hasPiece() {
        return this.piece != null;
    }

    @Override
    public final Piece getPiece() {
        return this.piece;
    }

    @Override
    public final void reachableBox() {
        this.getStyleClass().add("reachableSpace");
    }

    @Override
    public final void notReachableBox() {
        this.getStyleClass().remove("reachableSpace");
    }

    @Override
    public final void selectBox() {
        this.getStyleClass().add("selectedSpace");
    }

    @Override
    public final void deselectBox() {
        this.getStyleClass().remove("selectedSpace");
    }

    @Override
    public final void drawPiece(final Piece piece) {
        this.piece = piece;
        if (this.piece != null) {
            img = ImageGenerator.generateImage(PieceImageGenerator.getImage(piece));
            img.setPreserveRatio(true);
            this.setGraphic(img);
            this.piece.setPoint(new Point(x, y));
        } else {
            img = new ImageView();
            this.setGraphic(img);
        }
        resize();
    }

    @Override
    public final void resize() {
        size = Math.min(Main.getStageHeight(), Main.getStageWidth() * 0.5);
        if (img != null) {
            img.setFitWidth(size * 0.5 / 10);
            img.setFitHeight(size * 0.5 / 10);
        }
        final double siz = Math.min(Main.getStageWidth() * GameViewImpl.CHESSBOARD_PROPORTION, Main.getStageHeight()) / GameViewImpl.ROW_LENGTH;
        this.setPrefSize(siz, siz);
    }

    @Override
    public final int getX() {
        return x;
    }

    @Override
    public final int getY() {
        return y;
    }
}
