package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.ControlCore;
import controller.ControlCoreImpl;

/**
 * gui per la connessione in locale o remoto
 * non al database ma al programma a cui sono associati diversi database
 * nella prossima interfaccia ci sarà la connessione al database
 * @author silviobrasini
 *
 */
public class ConnectionGUI {

    private JFrame frame;
    private JPanel panel;
    private JPanel panelLabel;
    private JPanel panelBody;
    private JPanel panelBtn;
    private ButtonGroup group;
    private JLabel labelLocalOrRemote;
    private JTextField jtfRemote;
    private JTextField jtf;
    private JButton btnOK;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int MIN_WIDTH = screenSize.width * 1 / 6;
    private static final int MIN_HEIGHT = screenSize.height * 1 / 5;

    /**
     * Costruttore di ConnectionGUI
     * @param cc
     */
    public ConnectionGUI(/*ControlCore cc*/) {
        this.panel = new JPanel(new BorderLayout());
        this.frame = new JFrame();
        this.frame.setTitle("ConnectionGUI"); 
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT)); // setta la dimensione minima della finestra (in base ai pixel dello schermo)

        this.panelLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.labelLocalOrRemote = new JLabel("Local or remote:"); // cambia in base alla lingua //se l'utente mette n indirizzo specifico 
        this.panelLabel.add(this.labelLocalOrRemote);
        this.panel.add(this.panelLabel, BorderLayout.NORTH);
        this.panelBody = new JPanel(new GridLayout(0, 1));
        JRadioButton option1 = new JRadioButton("Local path:");
        JRadioButton option2 = new JRadioButton("Local");
        JRadioButton option3 = new JRadioButton("Remote path:");
        option1.setSelected(true);
        this.group = new ButtonGroup();
        this.group.add(option1);
        this.group.add(option2);
        this.group.add(option3);
        this.btnOK = new JButton("OK");
        this.jtfRemote = new JTextField("http://");
        this.jtf = new JTextField();
        this.panelBody.add(option1);
        this.panelBody.add(this.jtf);
        this.panelBody.add(option2);
        this.panelBody.add(option3);
        this.panelBody.add(this.jtfRemote);
        this.panel.add(this.panelBody, BorderLayout.CENTER);
        this.panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.panelBtn.add(this.btnOK);
        this.panel.add(this.panelBtn, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.panel);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ConnectionGUI();
    }
}
