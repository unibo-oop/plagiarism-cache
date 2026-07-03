package it.unibo.memory.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

// Pannello che mi fa vedere mosse, le coppie e lo status
public class StatoPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JLabel mosseLabel;
    private final JLabel coppieLabel;
    private final JLabel statoLabel;

    // valori di default 
    public StatoPanel() {
        super(new FlowLayout(FlowLayout.CENTER, 12, 4));

        mosseLabel = new JLabel("Mosse: 0");
        coppieLabel = new JLabel("Coppie: 0/0");
        statoLabel = new JLabel(" ");

        add(mosseLabel);
        add(coppieLabel);
        add(statoLabel);
    }
//metodi per aggiornare le label mosse, coppie, stato
    public void setMosse(final int moves) {
        mosseLabel.setText("Mosse: " + moves);
    }

    public void setCoppie(final int matched, final int total) {
        coppieLabel.setText("Coppie: " + matched + "/" + total);
    }
    public void setStato(final String message) {
        statoLabel.setText(message);
    }
}
