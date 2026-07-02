package pvz.view.menuview.impl;

import pvz.utilities.Resolution;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Font;



/**
 * A dialog that shows the game tutorial to the user.
 */
public final class TutorialView {

    private static final int TEXT_FONT_SIZE = 18;
    private final JDialog dialog;

    /**
     * Shows the TutorialView window.
     *
     * @param resolution the Resolution used to set the window size
     */
    public TutorialView(final Resolution resolution) {
        this.dialog = new JDialog();
        dialog.setTitle("Tutorial");
        dialog.setModal(true);

        final JTextArea text = new JTextArea("""
                - Uccidi 20 Zombie per vincere in modalità facile, 35 in normale e 45 in difficile!

                - In modalità difficile spawneranno anche degli zombie BEAST!! Saranno molto difficili da uccidere!

                - Se uno Zombie arriva oltre le tue piante due volte hai perso!
                  (La prima volta si attiverà il tosaerba!)

                - Per continuare a piantare piante ti servono i soli!
                Assicurati di piantare SEMPRE i girasoli per generare i tuoi soli!

                PIANTE DISPONIBILI:

                - Sunflower: Genera periodicamente soli per poter piazzare altre piante!

                - Peashooter: Spara semi agli zombie per proteggere il tuo giardino!

                - Wallnut: Una noce esplosiva che uccide istantaneamente il primo zombie che la tocca insieme a se stessa!
                """);
        text.setEditable(false);
        text.setFont(new Font("Arial", Font.PLAIN, TEXT_FONT_SIZE));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        final JScrollPane scroll = new JScrollPane(text);

        final JButton back = new JButton("Indietro");
        back.addActionListener(e -> dialog.dispose());

        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(back, BorderLayout.SOUTH);

        dialog.getContentPane().add(panel);
        dialog.setSize(resolution.getWidth(), resolution.getHeight());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
