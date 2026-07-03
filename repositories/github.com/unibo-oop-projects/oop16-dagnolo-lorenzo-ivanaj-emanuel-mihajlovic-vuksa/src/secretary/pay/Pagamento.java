package secretary.pay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import secretary.LogIn;
import secretary.mainview.SceltaOpzione;

public class Pagamento {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JPanel northPanel;
    private JButton conferma;
    private JButton ok;
    private JButton indietro;
    private JButton esci;
    private JLabel label;
    private JLabel labelPagamento;
    private JTextField text;
    private Dimension screenSize;
    private Image backIcon;
    private Image okIcon;
    private Image exitIcon;
    private Image confirmIcon;
    private SceltaOpzione scelta;
    private LogIn log;

    public Pagamento() {
        this.frame = new JFrame("Pagamento");
        this.panel = new JPanel();
        this.panel.setBackground(Color.CYAN);
        this.panel.setVisible(false);
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.CYAN);
        this.northPanel = new JPanel();
        this.northPanel.setBackground(Color.CYAN);
        this.frame.setSize(new Dimension(600, 350));
        String ciao = "150€";
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        Font font = new Font("Baskerville", Font.HANGING_BASELINE, 120);
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/cash.png")).getImage();
        this.confirmIcon = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.exitIcon = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.text = new JTextField(20);
        this.text.setBackground(Color.yellow);
        this.label = new JLabel("Inserisci il numero del documento");
        this.labelPagamento = new JLabel("" + ciao);
        this.labelPagamento.setFont(font);
        this.panel.add(this.labelPagamento);
        this.conferma = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.okIcon));
        this.indietro = new JButton("");
        this.indietro.setIcon(new ImageIcon(this.backIcon));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exitIcon));
        this.ok = new JButton("");
        this.ok.setIcon(new ImageIcon(this.confirmIcon));
        this.conferma.addActionListener(a -> {
            if (this.text.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Inserisci un numero di documento", "Errore di inserimento",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane.showConfirmDialog(this.frame,
                        "Vuoi confermare il pagamento della camera " + this.text.getText() + " ?", "Conferma",
                        JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.panel.setVisible(true);
                }
            }
        });
        this.indietro.addActionListener(b -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Vuoi tornare indietro?" + this.text.getText() + " ?",
                    "Conferma", JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.scelta = new SceltaOpzione();
            }
        });
        this.esci.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Confermare di volere uscire ?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.log = new LogIn();
            }
        });
        this.ok.addActionListener(d -> {
            if (this.text.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Inserisci un numero di documento", "Errore di inserimento",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane
                        .showConfirmDialog(
                                this.frame, "Confermare il pagamento della camera " + this.text.getText()
                                        + " al prezzo di " + this.labelPagamento.getText() + " ?",
                                "Conferma", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.frame.setVisible(false);
                    this.log = new LogIn();
                }
            }
        });
        this.northPanel.add(this.label);
        this.northPanel.add(this.text);
        this.northPanel.add(this.conferma);
        this.southPanel.add(this.indietro);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.ok);
        this.frame.getContentPane().add(this.panel, BorderLayout.CENTER);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.northPanel, BorderLayout.NORTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new Pagamento();

    }

}
