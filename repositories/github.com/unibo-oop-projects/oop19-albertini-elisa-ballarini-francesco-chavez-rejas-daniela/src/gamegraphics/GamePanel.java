package gamegraphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import playcontroller.PlayController;

/**
 * Panel for the Game. It's the main Panel of ViewGame.
 *
 */
public class GamePanel extends JPanel {
    private static final long serialVersionUID = -4822496451606533191L;
    private final TetrisPanel tetrisPanel;
    private final BoxPanel preview;
    private final BoxPanel holdbox;
    private final JLabel score;

    private static final String TEXTFONT = "Verdana";
    private static final int ROWSNUMBER = 20;
    private static final int COLUMNSNUMBER = 10;
    //All these dimensions are in function of the screenHeight
    private static final int VERTICALSPANSIDEBAR = 70;
    private static final int LABELHEIGHT = 20;
    private static final int FONTSIZEMAINLABEL = 24;
    private static final int FONTSIZECONTROLS = 13;

    /**
     * @param frameDimension : Dimension of the Frame
     * @param playController : PlayController Object
     */
    public GamePanel(final Dimension frameDimension, final PlayController playController) {
        final int screenHeight = frameDimension.height;
        final int screenWidth = frameDimension.width;

        // Creates the Panel of the main grid
        this.tetrisPanel = new TetrisPanel(screenHeight / 2, screenHeight, ROWSNUMBER, COLUMNSNUMBER); // width, height, rows, columns
        this.tetrisPanel.setPreferredSize(new Dimension(screenHeight / 2, screenHeight));

        // Creates the Panel that will contain all the sidebar's elements
        final JPanel sidebar = new JPanel();
        // screenHeight/70 is the vertical span between components in the sidebar
        sidebar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, screenHeight / VERTICALSPANSIDEBAR));
        sidebar.setPreferredSize(new Dimension(screenHeight / 4, screenHeight));

        // Creates the Label for the Preview Box
        final JLabel previewLabel = new JLabel("Next Piece", JLabel.LEFT);
        previewLabel.setPreferredSize(new Dimension(screenHeight / 4, screenHeight / LABELHEIGHT));
        previewLabel.setFont(new Font(TEXTFONT, Font.BOLD, FONTSIZEMAINLABEL));
        sidebar.add(previewLabel);

        // Creates the Panel for the Preview Box
        this.preview = new BoxPanel(screenHeight / 4, screenHeight / 4, 4, 4); // width, height, rows, columns
        this.preview.setPreferredSize(new Dimension(screenHeight / 4, screenHeight / 4));
        sidebar.add(this.preview);

        // Creates the Label for the Holdbox
        final JLabel holdboxLabel = new JLabel("Holdbox", JLabel.LEFT);
        holdboxLabel.setPreferredSize(new Dimension(screenHeight / 4, screenHeight / LABELHEIGHT));
        holdboxLabel.setFont(new Font(TEXTFONT, Font.BOLD, FONTSIZEMAINLABEL));
        sidebar.add(holdboxLabel);

        // Creates the Panel for the Holdbox
        this.holdbox = new BoxPanel(screenHeight / 4, screenHeight / 4, 4, 4); // width, height, rows, columns
        this.holdbox.setPreferredSize(new Dimension(screenHeight / 4, screenHeight / 4));
        sidebar.add(this.holdbox);

        // Creates the Label for the score
        this.score = new JLabel("Score:", JLabel.LEFT);
        this.score.setPreferredSize(new Dimension(screenHeight / 4, screenHeight / 10));
        this.score.setFont(new Font(TEXTFONT, Font.BOLD, FONTSIZEMAINLABEL));
        sidebar.add(this.score);

        // Creates the controls Label
        final JLabel controls = new JLabel(
                "<html><b>CONTROLS</b><br><br>○ <b>PAUSE GAME</b><br>• Press ESC button<br><br>"
                        + "○ <b>SHIFT RIGHT</b><br>• Press D button<br>"
                        + "○ <b>SHIFT LEFT</b><br>• Press A button<br>"
                        + "○ <b>ROTATE COUNTERCLOCKWISE</b><br>• Press Q button<br>"
                        + "○ <b>ROTATE CLOCKWISE</b><br>• Press E button<br><br>○ <b>HOLDBOX</b><br>"
                        + "• Press W button<br><br>○ <b>ACCELERATED SPEED</b><br>"
                        + "• Hold S button<br>" + "○ <b>INSTANT POSITIONING</b><br>• Press SPACE<br><br>"
                        + "○ <b>USE \"CANCEL LINES WITH 8 BLOCKS\" BOOSTER</b><br>• Press 1 button<br>"
                        + "○ <b>USE \"CANCEL FIRST LINE\" BOOSTER</b><br>• Press 2 button<br>"
                        + "○ <b>USE \"SWITCH WITH MINI-PIECE\" BOOSTER</b><br>• Press 3 button<br></html>",
                JLabel.LEFT);
        controls.setPreferredSize(new Dimension(screenWidth - (screenHeight / 2) - (screenHeight / 4), screenHeight));
        controls.setFont(new Font(TEXTFONT, Font.PLAIN, FONTSIZECONTROLS));

        // Adds the TetrisPanel, the Sidebar and the Controls to this JPanel
        this.setLayout(new BorderLayout(0, 0));
        this.add(this.tetrisPanel, BorderLayout.WEST);
        this.add(sidebar, BorderLayout.EAST);
        this.add(controls, BorderLayout.CENTER);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(playController.getControls());
    }

    /**
     * @return TetrisPanel : Panel for the main grid
     */
    public final TetrisPanel getTetrisPanel() {
        return this.tetrisPanel;
    }

    /**
     * @return BoxPanel : BoxPanel for the Holdbox
     */
    public final BoxPanel getHoldbox() {
        return this.holdbox;
    }

    /**
     * @return BoxPanel : BoxPanel for the Preview Box
     */
    public final BoxPanel getPreview() {
        return this.preview;
    }

    /**
     * @return JLabel : JLabel for the Score
     */
    public final JLabel getScore() {
        return this.score;
    }
}
