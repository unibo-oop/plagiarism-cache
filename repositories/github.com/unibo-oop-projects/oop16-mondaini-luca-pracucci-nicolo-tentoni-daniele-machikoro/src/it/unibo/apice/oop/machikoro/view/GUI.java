package it.unibo.apice.oop.machikoro.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import it.unibo.apice.oop.machikoro.controller.JudgeImpl;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

/**
 * GUI Iniziale.
 */
public class GUI extends JFrame {
    /**
     * Variabili globali.
     */
    private static final long serialVersionUID = 1L;
    private static final int STARTX = 0;
    private static final int STARTY = 0;
    private static final int WINDOW_X = 800;
    private static final int WINDOW_Y = 600;
    private static final double GRIDX = 0.50;
    private static final double GRIDY = 0.70;
    private static final double BUTTONY = 0.55 * 0.33;
    private static final int PANELDIMENSION = 15;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private final JButton btnPlay = new ButtonChanged("SINGLE-PLAYER");
    private final JButton btnLoad = new ButtonChanged("LOAD-MATCH");
    private final JButton btnExit = new ButtonChanged("EXIT");
    private final JudgeImpl judge = new JudgeImpl();
    private final JPanel panelgrid = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Lancia l'applicazione iniziale.
     * 
     * @param args
     *            Argomenti main.
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    final GUI window = new GUI();
                    window.setVisible(true);
                } catch (Exception e) {
                    System.out.println("Error Launch");
                }
            }
        });
    }

    /**
     * Lancia l'applicazione.
     */
    public void start() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    setVisible(true);
                } catch (Exception e) {
                    System.out.println("Error Launch 2");
                }
            }
        });
    }

    /**
     * Create the application.
     * 
     * @throws IOException
     *             Eccezione che può essere causata dal programma.
     */
    public GUI() throws IOException {
        super();
        initialize();
    }

    // Quando si vuole chiudere la finestra compare un messaggio di conferma.
    private void closing() {
        final int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure to Close this Application?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    // Riempie il centro della schermata con i bottoni appositi.
    private void fillCenter() {
        final List<JButton> listbutton = new LinkedList<JButton>();
        listbutton.addAll(Arrays.asList(btnPlay, btnLoad, btnExit));
        gbc.gridx = STARTX;
        gbc.gridy = STARTY;
        for (int i = 0; i < listbutton.size(); i++) {
            panelgrid.add(listbutton.get(i), gbc);
            gbc.gridy++;
            panelgrid.add(Box.createRigidArea(new Dimension(0, PANELDIMENSION)), gbc);
            gbc.gridy++;
        }
        panelgrid.setOpaque(false);
    }

    // Uso di setPreferredSize per adattare le componenti grafiche ad ogni tipo
    // di risoluzione schermo.
    private void setDimension() {
        panelgrid.setPreferredSize(new Dimension((int) (WINDOW_X * GRIDX), (int) (WINDOW_Y * GRIDY)));
        btnPlay.setPreferredSize(new Dimension((int) (WINDOW_X * GRIDX), (int) (WINDOW_Y * BUTTONY)));
        btnLoad.setPreferredSize(new Dimension((int) (WINDOW_X * GRIDX), (int) (WINDOW_Y * BUTTONY)));
        btnExit.setPreferredSize(new Dimension((int) (WINDOW_X * GRIDX), (int) (WINDOW_Y * BUTTONY)));
    }

    /**
     * Inizializzazione frame.
     * 
     * @throws IOException
     */
    private void initialize() throws IOException {
        setTitle("Machi-Koro by Tentoni,Mondaini,Pracucci");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);

        // Quando la finestra sta per essere chiusa.
        final WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                closing();
            }
        };
        addWindowListener(exitListener);

        // Controllo se esiste un salvataggio esistente.
        if (!judge.checkExistMatch()) {
            btnLoad.setEnabled(false);
        }

        // Caricamento immagine di background e uso di BorderLayout come panel
        // principale.
        final Image background = javax.imageio.ImageIO.read(this.getClass().getResource("/machi-koro.png"));
        setContentPane(new JPanel(new BorderLayout()) {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(final Graphics g) {
                g.drawImage(background, STARTX, STARTY, null);
            }
        });

        // La finestra sarà al centro dello schermo.
        setSize(WINDOW_X, WINDOW_Y);
        final int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        final int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        // JButton "Play"
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                dispose();
                try {
                    // Apertura finestra "Scelta dei giocatori".
                    final GUIChoosePlayers gcp = new GUIChoosePlayers();
                    gcp.start();
                } catch (IOException e1) {
                    System.out.println("Error btnPlay");
                }
            }
        });

        // JButton "Load"
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to load match?", "Load Match",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    dispose();
                    try {
                        // Apertura finestra "Schermata di gioco".
                        final GUINewMatch gnm = new GUINewMatch(null, 0, null, 1);
                        gnm.start();
                    } catch (IOException e1) {
                        System.out.println("Error btnLoad");
                    }
                }

            }
        });

        // JButton "Exit"
        btnExit.addActionListener(e -> closing());
        fillCenter();
        getContentPane().add(panelgrid, BorderLayout.NORTH);
        setDimension();

    }
}
