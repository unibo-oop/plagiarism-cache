package it.unibo.monoopoly.view.panel.game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 * The default {@link JPanel} that should be placed at the {@link InteractivePanel},
 * when a selection doesn't need to be made.
 */
public final class DefaultInteractivePanel extends JPanel {

    private static final int TEXT_RESIZE = 50;
    private static final long serialVersionUID = 1L;
    private static final Color GREEN_MONOPOLY = new Color(0xecfcf4);

    /**
     * Constructor of the class.
     * 
     * @param mainFrameHeight used to set the font size.
     */
    public DefaultInteractivePanel(final int mainFrameHeight) {
        final JTextArea startText = new JTextArea(
                "Qui appariranno le celle di propria proprietà tra cui scegliere.\n"
                        + "\t*************************\n"
                        + "Se si vuole chiudere l'applicazione:\n"
                        + "-> continuare il gioco fino ad arrivare a una di quelle scelte, poi premere esc e selezionare si.\n"
                        + "\t*************************\n"
                        + "Ora una piccola spiegazione della gameBoard:\n"
                        + "-> Lo spostamento di un giocatore è visualizzato tramite"
                        + " lo spostamento del pallino col relativo colore nelle celle.\n"
                        + "-> Se una proprietà appartiene a un giocatore "
                        + "sarà visibile un pallino del colore corrispondente al di sopra.\n"
                        + "-> Se un proprietà è in bancarotta sarà visibile un pallino grigio al di sopra.\n"
                        + "-> Nel momento in cui si costruirà una casa su una proprietà, "
                        + "comparira un numero sulla relativa banda colorata a identificare il numero di case costruite.\n"
                        + "\t*************************\n"
                        + "Infine i messaggi di notifica o le scelte semplici"
                        + " compariranno in una finestrella al centro del pannello.");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        startText.setFont(new Font("Arial", Font.PLAIN, mainFrameHeight / TEXT_RESIZE));
        startText.setEnabled(false);
        startText.setLineWrap(true);
        startText.setWrapStyleWord(true);
        startText.setDisabledTextColor(Color.BLACK);
        startText.setBorder(new LineBorder(Color.BLACK));
        startText.setBackground(GREEN_MONOPOLY);
        add(startText);
    }

}
