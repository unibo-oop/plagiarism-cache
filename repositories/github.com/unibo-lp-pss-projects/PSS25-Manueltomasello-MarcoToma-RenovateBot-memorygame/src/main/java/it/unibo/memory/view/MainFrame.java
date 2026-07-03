package it.unibo.memory.view;

import javax.swing.*;
import it.unibo.memory.controller.GameController;
import it.unibo.memory.model.Board;
import it.unibo.memory.model.Difficulty;
import it.unibo.memory.model.Game;
import it.unibo.memory.util.ScoreManager;
import java.awt.*;


 //Finestra principale dell'app, gestisce la navigazione tra le schermate
 

public class MainFrame extends JFrame {

    // Colori di default del tema chiaro, letti dal Look&Feel o con fallback
    private static final Color LIGHT_PANEL_BG;
    private static final Color LIGHT_FG;

    static {
        // Legge i colori dal Look&Feel corrente; usa fallback se non disponibili
        Color c = UIManager.getColor("Panel.background");
        LIGHT_PANEL_BG = c != null ? c : new Color(238, 238, 238);
        c = UIManager.getColor("Label.foreground");
        LIGHT_FG = c != null ? c : Color.BLACK;
    }

    private final CardLayout cardLayout;
    private final JPanel mainContainer;
    private GameView gameView;

    public static final String MENU_CARD = "MENU";
    public static final String GAME_CARD = "GIOCO";

    public MainFrame() {
        setTitle("Memory Game - Progetto PSS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 900);
        setLocationRelativeTo(null); // Centra la finestra nello schermo

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        mainContainer.add(new MenuPanel(this), MENU_CARD);

        add(mainContainer);
        revalidate();
        repaint();
    }

    // avvia partita
    public void avviaPartita(final Difficulty difficulty) {
        // Rimuove la partita precedente se esiste
        if (gameView != null) {
            mainContainer.remove(gameView);
        }

        final Board board = new Board(difficulty);
        final Game game = new Game(difficulty.totalPairs());
        final ScoreManager scoreManager = new ScoreManager();

        // Il controller riceve due callback: una per ridisegnare, una per mostrare il dialogo di fine partita
        final GameController controller = new GameController(board, game,
                () -> repaint(),
                () -> {
                    final EndGameDialog dialog = new EndGameDialog(
                            this, difficulty, game.getMoves(), scoreManager,
                            () -> avviaPartita(difficulty),
                            () -> mostraSchermata(MENU_CARD));
                    dialog.setVisible(true);
                });
        final StatoPanel stato = new StatoPanel();
        final GamePanel griglia = new GamePanel(board, controller);
        controller.setStatoPanel(stato);
        gameView = new GameView(griglia, stato);

        mainContainer.add(gameView, GAME_CARD);
        mainContainer.revalidate();
        mostraSchermata(GAME_CARD);
    }

    
     // Mostra la schermata identificata dal nome carta nel CardLayout.
     
    public void mostraSchermata(final String nomeCarta) {
        cardLayout.show(mainContainer, nomeCarta);
    }

    
    //Imposto il tema a tutti i componenti della finestra.
     
    public void applicaTema(final boolean dark) {
        final Color panelBg = dark ? new Color(60, 63, 65) : LIGHT_PANEL_BG;
        final Color fg      = dark ? new Color(187, 187, 187) : LIGHT_FG;
        applicaTemaRicorsivo(mainContainer, panelBg, fg);
        repaint();
    }

    //Ricorsione dell'albero dei componenti per aggiornare i colori
    private void applicaTemaRicorsivo(final Container container, final Color panelBg, final Color fg) {
        container.setBackground(panelBg);
        for (final Component comp : container.getComponents()) {
            if (comp instanceof JTextArea area) {
                area.setBackground(panelBg);
                area.setForeground(fg);
            } else if (comp instanceof JLabel label) {
                label.setForeground(fg);
            } else if (comp instanceof AbstractButton btn) {
                btn.setForeground(fg);
            }
            if (comp instanceof Container c) {
                applicaTemaRicorsivo(c, panelBg, fg);
            }
        }
    }
}
