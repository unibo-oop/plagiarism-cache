package pvz.view.endgameview.impl;

import pvz.controller.endgamecontroller.api.EndGameController;
import pvz.utilities.Resolution;
import pvz.view.endgameview.api.EndGameView;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.Serializable;

/**
 * Implementazione della view di fine partita.
 * Mostra il risultato (vittoria/sconfitta) e permette di tornare al menu o uscire.
 */
public final class EndGameViewImpl extends JPanel implements EndGameView, Serializable {

    private static final long serialVersionUID = 1L;

    private final JButton backToMenuButton = new JButton("Torna al menu");
    private final JButton exitButton = new JButton("Esci");
    private final transient EndGameController parentController;
    private final JFrame frame = new JFrame();
    private final Resolution resolution;

    private static final int MESSAGE_FONT_SIZE = 40;
    private static final int MESSAGE_TOP_BOTTOM_PADDING = 50;

    /**
     * Costruisce la view di fine partita.
     *
     * @param controller il controller di fine partita
     * @param hasWon true se il giocatore ha vinto, false altrimenti
     * @param resolution the resolution to use for the end-game screen
     */
    public EndGameViewImpl(final EndGameController controller, final boolean hasWon, final Resolution resolution) {
        this.parentController = controller;
        this.resolution = resolution;
        this.setLayout(new BorderLayout());

        final String messageText = hasWon ? "Hai vinto!" : "Hai perso!";
        final JLabel message = new JLabel(messageText, SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.BOLD, MESSAGE_FONT_SIZE));
        message.setBorder(BorderFactory.createEmptyBorder(
                MESSAGE_TOP_BOTTOM_PADDING, 0, MESSAGE_TOP_BOTTOM_PADDING, 0));
        this.add(message, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backToMenuButton);
        buttonPanel.add(exitButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

        configureFrame();
        initActionListeners();
    }

    /**
     * Chiude la finestra di fine partita.
     */
    public void close() {
        this.frame.dispose();
    }

    private void configureFrame() {
        frame.setTitle("Piante contro Zombie");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(resolution.getWidth(), resolution.getHeight());
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void initActionListeners() {
        backToMenuButton.addActionListener(e -> parentController.goToMenu());
        exitButton.addActionListener(e -> {
            parentController.quit();
        });
    }
}
