package secretary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jtattoo.plaf.JTattooUtilities;

import secretary.mainview.SceltaOpzione;
import secretary.modify.ConfermaPrenotazione;

public class VisualizzazioneCamerePrenotate {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private Image ok;
    private Image exit;
    private Image backIcon;
    private JButton conferma;
    private JButton annulla;
    private Font font;
    private Dimension screenSize;
    
    // LISTA DI CAMERE PRENOTATE IN BASE A UN NUM DOC. LISTA DI "NUMERO CAMERE,
    // INDICE CAMERA"
    public VisualizzazioneCamerePrenotate() {
        this.frame = new JFrame();
        this.frame.setSize(new Dimension(500, 500));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.panel = new JPanel();
        this.southPanel = new JPanel();
        this.ok = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.conferma = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.ok));
        this.annulla = new JButton("");
        this.annulla.setIcon(new ImageIcon(this.backIcon));
        this.font = new Font("Baskerville", Font.HANGING_BASELINE, 60);
        this.annulla.addActionListener(e -> {
            this.frame.setVisible(false);
            new ConfermaPrenotazione();
        });
        this.conferma.addActionListener(e -> {
            new SceltaOpzione();
        });
        this.southPanel.add(this.annulla);
        this.southPanel.add(this.conferma);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new VisualizzazioneCamerePrenotate();
    }
}