package secretary.modify;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
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
import secretary.Modifica;

public class ModificaTipoPensione {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JPanel northPanel;
    private Dimension screenSize;
    private JLabel labelDoc;
    private JTextField textDoc;
    private JButton conferma;
    private JButton annulla;
    private Image backIcon;
    private Image okIcon;
    public ModificaTipoPensione(){
        this.frame = new JFrame("Modifica durata soggiorno");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setBackground(Color.CYAN);
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.CYAN);
        this.frame.setSize(new Dimension(400, 150));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.labelDoc = new JLabel("Inserisci numero documento");
        this.textDoc = new JTextField(20);
        this.textDoc.setBackground(Color.YELLOW);
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.conferma = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.okIcon));
        this.annulla = new JButton("");
        this.annulla.setIcon(new ImageIcon(this.backIcon));
        this.conferma.addActionListener(e -> {
            if (this.textDoc.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Inserisci un numero di documento", "Errore inserimento",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                Optional<Client> opt = Hotel.instance().getClientView().stream()
                        .filter(client -> client.getDocument().equals(this.textDoc.getText())).findAny();

                if (opt.isPresent() && Hotel.instance().getStayView().stream().filter(
                        stay -> stay.getClient().equals(opt.get()) && stay.getState().equals(StayState.INACTIVE))
                        .count() == 1) {
                    this.frame.setVisible(false);
                    new ModifyBoardType(this.textDoc.getText());
                }
            }
        });
        this.annulla.addActionListener(e -> {
            this.frame.setVisible(false);
            new Modifica();
        });
        this.southPanel.add(this.annulla);
        this.southPanel.add(this.conferma);
        this.panel.add(this.labelDoc);
        this.panel.add(this.textDoc);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }
    public static void main(String[] args) {
        new ModificaTipoPensione();

    }

}
