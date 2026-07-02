package helpline;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import bubbleshooter.view.helpline.DrawHelpLine;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;

/**
 * Class used to test the {@link HelpLine}.
 *
 */
public class TestHelpLine {

    @FXML private final AnchorPane pane = new AnchorPane();

    /**
     * Method to test if a {@link DrawHelpLine} draw a new {@link HelpLine}.
     */
    @Test
    public final void testVisibilityHelpLine() {
        final DrawHelpLine drawHelpLine = new DrawHelpLine(this.pane, new Point2D(0, 0));

        assertFalse(drawHelpLine.getHelpLine().isVisible());
        assertFalse(drawHelpLine.getBoundsLine().isVisible());

        drawHelpLine.drawLine();

        assertTrue(drawHelpLine.getHelpLine().isVisible());
        assertTrue(drawHelpLine.getBoundsLine().isVisible());

        drawHelpLine.deleteLine();

        assertFalse(drawHelpLine.getHelpLine().isVisible());
        assertFalse(drawHelpLine.getBoundsLine().isVisible());
     }

}
