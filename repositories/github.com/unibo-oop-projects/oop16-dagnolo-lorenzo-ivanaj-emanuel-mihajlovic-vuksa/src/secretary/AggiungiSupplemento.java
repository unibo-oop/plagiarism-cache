package secretary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hotelmaster.reservations.Client;
import hotelmaster.reservations.StayState;
import hotelmaster.structure.Hotel;
import secretary.mainview.SceltaOpzione;

public class AggiungiSupplemento {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JLabel label;
    private JButton conferma;
    private JButton annulla;
    private JButton esci;
    private JTextField text;
    private String risp;
    private Dimension screenSize;
    private Image ok;
    private Image exit;
    private Image backIcon;
    private GridBagConstraints grid;

    public AggiungiSupplemento() {
        this.frame = new JFrame("Conferma prenotazione");
        this.frame.setSize(new Dimension(650, 200));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));

        this.ok = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.exit = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.panel = new JPanel(new GridBagLayout());
        this.panel.setBackground(Color.CYAN);
        this.grid = new GridBagConstraints();
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.cyan);
        this.label = new JLabel("Inserisci numero di documento");
        Font font = new Font("Baskerville", Font.HANGING_BASELINE, 25);
        this.label.setFont(font);
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.label, this.grid);
        this.text = new JTextField(20);
        this.text.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.panel.add(this.text, this.grid);
        this.conferma = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.ok));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exit));
        this.annulla = new JButton("");
        this.annulla.setIcon(new ImageIcon(this.backIcon));
        this.annulla.addActionListener(e -> {
            this.frame.setVisible(false);
            new Modifica();
        });
        this.esci.addActionListener(e -> {
            this.frame.setVisible(false);
            new SceltaOpzione();
        });
        this.conferma.addActionListener(e -> {
            if (this.text.getText().length() == 0) {
                JOptionPane.showMessageDialog(this.frame, "Inserisci un numero di documento valido!",
                        "Errore inserimento", JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler confermare la prenotazione?",
                        "Conferma", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.risp = this.text.getText();
                    Optional<Client> opt = Hotel.instance().getClientView().stream()
                            .filter(client -> client.getDocument().equals(this.risp)).findAny();

                    if (opt.isPresent() && Hotel.instance().getStayView().stream().filter(
                            stay -> stay.getClient().equals(opt.get()) && stay.getState().equals(StayState.INACTIVE))
                            .count() == 1) {
                        this.frame.setVisible(false);
                        // TODO
                    } else {
                        JOptionPane.showMessageDialog(frame, "Prenotazione non trovata", "Errore conferma prenotazione",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        this.southPanel.add(this.annulla);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.conferma);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new AggiungiSupplemento();
    }
}