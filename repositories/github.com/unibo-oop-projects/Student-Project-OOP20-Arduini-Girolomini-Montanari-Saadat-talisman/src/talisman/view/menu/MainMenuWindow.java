package talisman.view.menu;

import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Window that contains the talisman main menu.
 * 
 * @author Alberto Arduini
 *
 */
public class MainMenuWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int TITLE_FONT_SIZE = 20;
    private static final int BORDER_SIZE = 10;

    /**
     * Creates a new main menu window.
     */
    public MainMenuWindow() {
        final LayoutManager layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JLabel titleLabel = new JLabel("Talisman");
        titleLabel.setFont(new Font("Serif", Font.BOLD, MainMenuWindow.TITLE_FONT_SIZE));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(MainMenuWindow.BORDER_SIZE, MainMenuWindow.BORDER_SIZE,
                MainMenuWindow.BORDER_SIZE, MainMenuWindow.BORDER_SIZE));
        this.add(titleLabel);

        final MainMenuOptionsPanel optionsPanel = new MainMenuOptionsPanel();
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(MainMenuWindow.BORDER_SIZE,
                MainMenuWindow.BORDER_SIZE, MainMenuWindow.BORDER_SIZE, MainMenuWindow.BORDER_SIZE));
        optionsPanel.addQuitListener(() -> this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        optionsPanel.addStartListener(() -> this.showPlayerSetupWindow());
        this.add(optionsPanel);

        this.pack();
    }

    private void showPlayerSetupWindow() {
        final PlayerSetupWindow window = new PlayerSetupWindow();
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                MainMenuWindow.this.setVisible(true);
            }
        });
        window.setVisible(true);
        this.setVisible(false);
    }
}
