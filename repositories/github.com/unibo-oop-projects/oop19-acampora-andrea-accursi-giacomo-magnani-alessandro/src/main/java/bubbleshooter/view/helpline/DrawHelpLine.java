package bubbleshooter.view.helpline;

import bubbleshooter.utility.Settings;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

/**
 * Class used for draw the help line if the 'help' CheckBox in {@link GameController} is selected.
 */
public class DrawHelpLine {

    private final Point2D startPointFirstLine;
    private final HelpLine helpLine;
    private final HelpLine boundsLine;
    private final Line borderRight;
    private final Line borderLeft;
    private final Rotate rotation = new Rotate();
    private boolean helpSelected;

    /**
     * Constructor for a new DrawHelpLine. 
     * @param pane                   The panel where draw the {@link HelpLine}.
     * @param shootingBubblePosition The position of {@link ShootingBubble}.
     */
    public DrawHelpLine(final AnchorPane pane, final Point2D shootingBubblePosition) {
        this.startPointFirstLine = shootingBubblePosition;
        this.helpLine = new HelpLine(this.startPointFirstLine, new Point2D(this.startPointFirstLine.getX(), 0));
        this.boundsLine = new HelpLine(new Point2D(0, 0), new Point2D(0, 0));
        this.borderRight = new Line(Settings.getGuiWidth(), 0, Settings.getGuiWidth(), Settings.getGuiHeight());
        this.borderRight.setVisible(false);
        this.borderLeft = new Line(0, 0, 0, Settings.getGuiHeight());
        this.borderLeft.setVisible(false);

        this.setRotation();

        pane.getChildren().add(helpLine.getLine());
        pane.getChildren().add(boundsLine.getLine());
        pane.getChildren().add(borderRight);
        pane.getChildren().add(borderLeft);
    }

    /**
     * Private method for set the rotation of help line.
     */
    private void setRotation() {
        this.rotation.setPivotX(this.startPointFirstLine.getX());
        this.rotation.setPivotY(this.startPointFirstLine.getY());
        this.helpLine.getLine().getTransforms().add(this.rotation);
    }

    /**
     * @return the bounds of help line.
     */
    public final Bounds getHelpBounds() {
        return helpLine.getLine().getBoundsInParent();
    }

    /**
     * @return the bounds of right line.
     */
    public final Bounds getRightBounds() {
        return borderRight.getBoundsInParent();
    }

    /**
     * @return the bounds of left line.
     */
    public final Bounds getLeftBounds() {
        return borderLeft.getBoundsInParent();
    }

    /**
     * @return the rotation of help line.
     */
    public final Rotate getRotation() {
        return this.rotation;
    }

    /**
     * @return the bounds line.
     */
    public final Line getBoundsLine() {
        return this.boundsLine.getLine();
    }

    /**
     * @return the help line.
     */
    public final Line getHelpLine() {
        return this.helpLine.getLine();
    }

    /**
     * @return the value of help CheckBox.
     */
    public final boolean isHelpSelected() {
        return this.helpSelected;
    }

    /**
     * Method for draw the help line.
     */
    public final void drawLine() {
        this.helpLine.getLine().setVisible(true);
        this.boundsLine.getLine().setVisible(true);
        this.helpSelected = true;
    }

    /**
     * Method for delete the help line.
     */
    public final void deleteLine() {
        this.helpLine.getLine().setVisible(false);
        this.boundsLine.getLine().setVisible(false);
        this.helpSelected = false;
    }

    /**
     * Method called by {@link HandlerAdapterMouseMoved} for draw the bounds line 
     * passing start point and end point.
     * @param startPointSecondLine The start point.
     * @param endPointSecondLine   The end point.
     */
    public final void drawBoundsLine(final Point2D startPointSecondLine, final Point2D endPointSecondLine) {
        this.boundsLine.getLine().setStartX(startPointSecondLine.getX());
        this.boundsLine.getLine().setStartY(startPointSecondLine.getY());
        this.boundsLine.getLine().setEndX(endPointSecondLine.getX());
        this.boundsLine.getLine().setEndY(endPointSecondLine.getY());
        this.boundsLine.getLine().setVisible(true);
    }
}
