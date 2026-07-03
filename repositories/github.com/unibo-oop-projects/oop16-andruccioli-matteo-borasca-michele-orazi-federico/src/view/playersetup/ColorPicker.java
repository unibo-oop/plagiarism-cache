package view.playersetup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import utils.enumerations.Color;

/**
 * 
 * This class displays the button responsible of picking a color to be assigned to a player. 
 * It has controls that its impossible choose the same color twice.
 * <p>
 * It is used in AddDataPanel. Extends a HBox.
 * <p>
 * @see HBox
 * @see HeaderPanel
 *
 */
public class ColorPicker extends HBox {

    private final JFXButton colorPickerBtn = new JFXButton("Color");
    private final Rectangle colorViewer = new Rectangle(20, 20);

    private Optional<Paint> color = Optional.empty();
    private Optional<Color> value = Optional.empty();
    private static final int N_COLOR = 6;
    private static final int TILE_GAP = 10;
    private static final int INSETS = 7;
    private static final int SPACING = 15;
    private static final int RECT_ARC_SIZE = 8;
    private final List<Color> colorList = new ArrayList<>(Arrays.asList(Color.BLUE, Color.PURPLE, Color.YELLOW, Color.GREEN, Color.BLACK, Color.RED));
    private final List<ColorButton> buttonList = new ArrayList<>();

    /**
     * 
     * Class constructor.
     * 
     */
    public ColorPicker() {
        initialize();
        this.getChildren().addAll(colorPickerBtn, colorViewer);
        this.setSpacing(SPACING);
    }

    private void initialize() {
        final TilePane colorPalette = new TilePane(TILE_GAP, TILE_GAP);
        colorPalette.setPadding(new Insets(INSETS));
        colorPalette.setPrefColumns(3);
        final JFXPopup popup = new JFXPopup(colorPalette);

        colorViewer.setArcHeight(RECT_ARC_SIZE);
        colorViewer.setArcWidth(RECT_ARC_SIZE);
        colorViewer.setFill(javafx.scene.paint.Color.TRANSPARENT);

        colorPickerBtn.setOnAction((e)-> popup.show(this, PopupVPosition.TOP, PopupHPosition.LEFT));

        for (int i = 0; i < N_COLOR; i++) {
            final ColorButton bt = new ColorButton(colorList.get(i));
            buttonList.add(bt);
            bt.setShape(new Circle(4));
            bt.setOnAction(e -> {
                setColor(Optional.of(bt.getBackground().getFills().get(0).getFill()));
                setValue(Optional.of(bt.getValue()));
                popup.hide();
            });
            colorPalette.getChildren().add(bt);
        }

        colorPickerBtn.getStyleClass().add("color-picker");
        this.setStyle("-fx-background-color: transparent;");
        this.setAlignment(Pos.CENTER);
    }

    /**
     * 
     * Getter of color.
     * 
     * @return
     *          The effective color chosen.
     * 
     */
    public Optional<Paint> getColor() {
        return color;
    }

    /**
     * 
     * Getter for Button list.
     * 
     * @return
     *          Return the button list.
     * 
     */
    public List<ColorButton> getButtonList() {
        return buttonList;
    }

    /**
     * 
     * Reset color button and color selection.
     * 
     */
    public void reset() {
        setColor(Optional.empty());
    }

    /**
     * 
     * When a player is removed it re-enables ColorBtn button of the specified color.
     * 
     * @param color
     *              Color of the button to be re-enabled.
     * 
     */
    public void refreshButtonList(final Color color) {
        buttonList.stream().filter(p -> p.getValue().equals(color)).findFirst().ifPresent(o ->o.setDisable(false));
    }

    /**
     * 
     * Getter of color value.
     * 
     * @return
     *          Return button's Color value.
     * 
     */
    public Optional<Color> getValue() {
        return value;
    }

    /**
     * 
     * Disables the selected button.
     * 
     */
    public void disableButton() {
        getButtonList().stream().filter(bt -> bt.getValue().getPaint().equals(getColor().get())).findAny().ifPresent(c ->  c.setDisable(true));
    }

    private void setColor(final Optional<Paint> color) {
        this.color = color;
        this.colorViewer.setFill(color.isPresent() ? color.get() : javafx.scene.paint.Color.TRANSPARENT);
    }

    private void setValue(final Optional<Color> value) {
        this.value = value;
    }

}
