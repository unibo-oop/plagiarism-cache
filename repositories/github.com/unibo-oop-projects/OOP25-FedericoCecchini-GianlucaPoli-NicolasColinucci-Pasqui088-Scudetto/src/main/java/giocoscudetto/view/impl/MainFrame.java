package giocoscudetto.view.impl;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.view.api.ViewManager;

/**
 * This class represents the main frame of the game.
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "SE_BAD_FIELD"},
        justification = "ViewManager is shared by design and serialization is not used in this application"
    )
    private final ViewManager viewManager;

    /**
     * Constructor of the MainFrame class, it initializes the view manager and sets the main panel of the frame.
     * 
     * @param manager the view manager to set for the frame.
     */
    @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
    public MainFrame(final ViewManager manager) {
        this.viewManager = manager;
        this.setContentPane(viewManager.getContainer());

        //Setting screen responsive resolution and placing it in the center
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int minimumWidth = screenSize.width / 2;
        final int minimumHeight = screenSize.height / 2;

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setResizable(true);
        this.setMinimumSize(new Dimension(minimumWidth, minimumHeight));

        //Setting frame main panel
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
