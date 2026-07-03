package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.GameMode;

/**
 * Panel displayed after clicking "newGame" button. It submits to the user the opportunity to enter the SinglePlayer
 * mode or the MultiPlayer mode, eventually to return to the previous (starting) panel.
 */
public class SelectionPlayerPanel extends GamePanel {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a SelectionPlayerPanel instance attached to the view.
     * @param view the View instance associated with the game.
     */
    public SelectionPlayerPanel(final View view) {
        super(view, "SelectionPlayerPanel");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.add(Box.createVerticalGlue());

        final JLabel title = new JLabel("Select player Mode");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(new Font("BankGothic Md BT", Font.BOLD, BUTTON_SIZE.height * 2 / 3));
        title.setForeground(Color.WHITE);

        this.add(title);

        this.add(Box.createVerticalGlue());

        final JPanel modes = new JPanel();    //def flowLayout
        modes.setMinimumSize(new Dimension(BUTTON_SIZE));
        modes.setOpaque(false);

        this.add(modes);

        final ActionListener al = e -> {
            this.getView().getObserver().update(this, GameMode.valueOf(GameMode.class, ((JButton) (e.getSource())).getText()).equals(GameMode.SINGLEPLAYER) ? ViewState.START_SINGLEPLAYER : ViewState.START_MULTIPLAYER);
        };
        this.prepareButton(GameMode.SINGLEPLAYER.name(), modes).addActionListener(al);
        this.prepareButton(GameMode.MULTIPLAYER.name(), modes).addActionListener(al);
        this.prepareButton("back", this).addActionListener(e -> {
            this.getView().switchWindow(this, MenuPanel.TITLE);
        });
        this.add(Box.createVerticalGlue());
    }
}
