package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

/**Root class common to {@link MenuPanel}, {@link SelectionPlayerPanel}, {@link HighScoresPanel}. It gathers 
 * all their common-features (JComponent sizes, button character styles and fonts, background images) so that future
 * changes in font or style of these classes will affect this one.*/

public abstract class GamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final View view;
    private final String title;
    private final BackgroundAnimator animator = new BackgroundAnimator(MenuPanel.TITLE);

    /**Size of the Button based to the actual dimension of the screen.*/
    public static final Dimension BUTTON_SIZE = new Dimension(ViewImpl.SCREEN_WIDTH / 4, ViewImpl.SCREEN_HEIGHT / 7);

    /**Constructs a GamePanel associated to the specified view and the title. Title is needed because GamePanel 
     * subclasses are thought to be added to a JPanel managed by a {@link java.awt.CardLayout}.
     * @param view View instance to associate to this JPanel.
     * @param title identifier for CardLayout.*/
    public GamePanel(final View view, final String title) {
        super();
        this.view = view;
        this.title = title;
    }

    /**Method to display the JPanel.*/
    public void display() { //title is identifier for CardLayout
        this.view.switchWindow(this, this.title);
    }

    /**
     *@param title text to set the button to.
     *@param panel panel to add the button to.
     *@return a new {@link JButton} instance with the specified text added to the specified {@link JPanel}.*/
    protected JButton prepareButton(final String title, final JPanel panel /*,String pathToImage*/) {
        final JButton button = new JButton(title);
        button.setMaximumSize(new Dimension(BUTTON_SIZE.width, BUTTON_SIZE.height / 2));
        button.setFont(new Font("BankGothic Md BT", Font.HANGING_BASELINE, BUTTON_SIZE.height / 3));
        button.setBackground(Color.WHITE);
        button.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(button);
        return button;
    }

    /**
     * {@inheritDoc}. This method is overridden in order to draw the background image of all this subclasses panels.
     */
    @Override
    protected void paintComponent(final Graphics g) { //to draw backGround
        g.drawImage(this.animator.loadImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }

    /**
     * @return the view associated to this class (specified in the constructor).
     */
    protected View getView() {
        return this.view;
    }
}
