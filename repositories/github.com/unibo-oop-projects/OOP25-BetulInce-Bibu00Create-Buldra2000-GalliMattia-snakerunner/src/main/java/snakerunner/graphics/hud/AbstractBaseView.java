package snakerunner.graphics.hud;

import java.awt.Font;

import javax.swing.JLabel;

/**
 * Base Graphics for HUD element.
 */
public abstract class AbstractBaseView extends JLabel implements BaseHUD {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for BaseView.
     */
    protected AbstractBaseView() {
        super();
    }

    /**
     * Set Font for BaseView.
     */
    protected final void initBaseView() {
        setFont(new Font("Arial", Font.BOLD, 16));
    }

}
