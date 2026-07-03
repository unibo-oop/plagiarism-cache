package it.unibo.apice.oop.machikoro.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Classe usata per selezionare i giocatori.
 */
public class GUIChoosePlayers extends JFrame {
    /**
     * Global Variables
     */
    private static final long serialVersionUID = 1L;
    private static final int STARTVAR = 0;
    private static final int WINDOW_X = 800;
    private static final int WINDOW_Y = 600;
    private static final int PANELGRIDDIMENSION = 15;
    private static final int GRIDAREA_X = 230;
    private static final double GRIDDIMENSION_X = 0.30;
    private static final double BUTTONDIMENSION_X = 0.25;
    private static final double GRIDDIMENSION_Y = 0.8;
    private static final double BUTTONDIMENSION_Y = 0.60 * 0.25;
    private static final double PANELGRIDWEST_X = 0.65;
    private static final double TEXTFIELD_X = 0.60;
    private static final double LABEL_Y = 0.15;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private final Image imgamerica = new ImageIcon(this.getClass().getResource("/cAmerica.png")).getImage();
    private final Image imgironman = new ImageIcon(this.getClass().getResource("/ironman.png")).getImage();
    private final Image imgthor = new ImageIcon(this.getClass().getResource("/thor.png")).getImage();
    private final Image imgflash = new ImageIcon(this.getClass().getResource("/flash.png")).getImage();
    private final Image imgred = new ImageIcon(this.getClass().getResource("/red.png")).getImage();
    private final Image imggreen = new ImageIcon(this.getClass().getResource("/green.png")).getImage();
    private final Image imgrobot1 = new ImageIcon(this.getClass().getResource("/robot1.png")).getImage();
    private final Image imgrobot2 = new ImageIcon(this.getClass().getResource("/robot2.png")).getImage();
    private final Image imgrobot3 = new ImageIcon(this.getClass().getResource("/robot3.png")).getImage();
    private final JRadioButton rb = new RadioButtonChanged();
    private final JRadioButton rb1 = new RadioButtonChanged();
    private final JRadioButton rb2 = new RadioButtonChanged();
    private final JRadioButton rb3 = new RadioButtonChanged();
    private final JLabel lblap = new JLabel();
    private final JLabel lblac1 = new JLabel();
    private final JLabel lblac2 = new JLabel();
    private final JLabel lblac3 = new JLabel();
    private final JButton btnStartMatch = new ButtonChanged("START");
    private final JButton btnBack = new ButtonChanged("BACK");
    private final JButton btnSettings = new ButtonChanged("SKIN");
    private final List<JTextField> listtextfield = new LinkedList<JTextField>();
    private final List<JButton> listbutton = new LinkedList<JButton>();
    private final List<JLabel> listlabelimage = new LinkedList<JLabel>();
    private final List<JRadioButton> listradiobutton = new LinkedList<JRadioButton>();
    private final JTextField text1 = new TextFieldChanged("PLAYER 1", 1);
    private final JTextField textcomputer1 = new TextFieldChanged("", STARTVAR);
    private final JTextField textcomputer2 = new TextFieldChanged("", STARTVAR);
    private final JTextField textcomputer3 = new TextFieldChanged("", STARTVAR);
    private int[] vectPlayers = { 1, STARTVAR, STARTVAR, STARTVAR };
    private int contatore;
    private int skin = 1;
    private final JPanel panelgridwest = new JPanel(new GridBagLayout());
    private final JPanel panelgrideast = new JPanel(new GridBagLayout());
    private final JPanel panelgridinv = new JPanel(new GridBagLayout());
    private GridBagConstraints gbcwest = new GridBagConstraints();
    private GridBagConstraints gbceast = new GridBagConstraints();

    /**
     * Launch the application.
     */
    public void start() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    setVisible(true);
                } catch (Exception e) {
                    System.out.println("Error launch");
                }
            }
        });
    }

    /**
     * Metodo che viene utilizzato per ricordare quale skin il giocatore ha
     * deciso di utilizzare.
     * 
     * @param n
     *            Skin utilizzata
     */
    public void pickSkin(final int n) {
        this.skin = n;
    }

    // Fa partire la finestra di "Scelta dell'aspetto delle carte"
    private void link(final GUISettings gs) {
        gs.start(this);
    }

    // Se la JRadioButton non è abilitata.
    private void notIn(final int n, final JRadioButton radiobutton, final JTextField text, final JLabel label,
            final Image image, final int j) {
        // Cambio JRadioButton da rossa a verde.
        radiobutton.setIcon(new ImageIcon(imggreen));
        // Faccio comparire il nickname del player.
        text.setText("PLAYER " + j);
        contatore++;
        text.setVisible(true);
        label.setIcon(new ImageIcon(image));
        label.setVisible(true);
        vectPlayers[n] = 1;
        // Rendo editabile la JTextField
        text.setEditable(true);
    }

    // Se la JRadioButton è abilitata e c'è un player.
    private void playerIn(final int n, final JTextField text, final JLabel label, final Image image, final int j) {
        // Faccio comparire il nickname del computer.
        text.setText("COMPUTER " + j);
        vectPlayers[n] = 2;
        label.setIcon(new ImageIcon(image));
        text.setEditable(false);
    }

    // Se la JRadioButton è abilitata e c'è un computer.
    private void robotIn(final int n, final JRadioButton radio, final JTextField text, final JLabel label) {
        // Disabilito tutto e faccio tornare allo stato iniziale
        radio.setIcon(new ImageIcon(imgred));
        text.setVisible(false);
        label.setVisible(false);
        vectPlayers[n] = 0;
        contatore--;
    }

    /**
     * Crea l'applicazione.
     * 
     * @throws IOException
     *             Eccezione che può essere causata.
     */
    public GUIChoosePlayers() throws IOException {
        super();
        initialize();
    }

    // Riempio la parte destra della finestra
    private void fillEast() {
        listbutton.addAll(Arrays.asList(btnStartMatch, btnSettings, btnBack));
        gbceast.gridx = STARTVAR;
        gbceast.gridy = STARTVAR;
        panelgrideast.add(Box.createRigidArea(new Dimension(0, PANELGRIDDIMENSION)), gbceast);
        for (int i = 0; i < listbutton.size(); i++) {
            gbceast.gridy++;
            panelgrideast.add(listbutton.get(i), gbceast);
            gbceast.gridy++;
            panelgrideast.add(Box.createRigidArea(new Dimension(0, PANELGRIDDIMENSION)), gbceast);
        }
        panelgrideast.setOpaque(false);
    }

    // Riempio la parte sinistra della finestra
    private void fillWest() {
        listlabelimage.addAll(Arrays.asList(lblap, lblac1, lblac2, lblac3));
        listradiobutton.addAll(Arrays.asList(rb, rb1, rb2, rb3));
        gbcwest.gridx = STARTVAR;
        gbcwest.gridy = STARTVAR;
        for (int i = 0; i < listradiobutton.size(); i++) {
            panelgridwest.add(listradiobutton.get(i), gbcwest);
            gbcwest.gridx++;
            panelgridwest.add(Box.createRigidArea(new Dimension(PANELGRIDDIMENSION, 0)), gbcwest);
            gbcwest.gridx++;
            panelgridwest.add(listlabelimage.get(i), gbcwest);
            gbcwest.gridx++;
            panelgridwest.add(Box.createRigidArea(new Dimension(PANELGRIDDIMENSION, 0)), gbcwest);
            gbcwest.gridx++;
            panelgridwest.add(listtextfield.get(i), gbcwest);
            gbcwest.gridy++;
            panelgridwest.add(Box.createRigidArea(new Dimension(0, 10)), gbcwest);
            gbcwest.gridy++;
            gbcwest.gridx = 0;
        }
        panelgridwest.setOpaque(false);
    }

    private void setDimension() {
        // Parte destra
        panelgrideast.setPreferredSize(
                new Dimension((int) (WINDOW_X * GRIDDIMENSION_X), (int) (WINDOW_Y * GRIDDIMENSION_Y)));
        btnStartMatch.setPreferredSize(
                new Dimension((int) (WINDOW_X * BUTTONDIMENSION_X), (int) (WINDOW_Y * BUTTONDIMENSION_Y)));
        btnSettings.setPreferredSize(
                new Dimension((int) (WINDOW_X * BUTTONDIMENSION_X), (int) (WINDOW_Y * BUTTONDIMENSION_Y)));
        btnBack.setPreferredSize(
                new Dimension((int) (WINDOW_X * BUTTONDIMENSION_X), (int) (WINDOW_Y * BUTTONDIMENSION_Y)));

        // Parte sinistra
        panelgridwest.setPreferredSize(
                new Dimension((int) (WINDOW_X * PANELGRIDWEST_X), (int) (WINDOW_Y * GRIDDIMENSION_Y)));
        for (int i = 0; i < listradiobutton.size(); i++) {
            listlabelimage.get(i).setPreferredSize(new Dimension((int) (WINDOW_X * PANELGRIDWEST_X * BUTTONDIMENSION_X),
                    (int) (WINDOW_Y * GRIDDIMENSION_Y * LABEL_Y)));
            listtextfield.get(i).setPreferredSize(new Dimension((int) (WINDOW_X * PANELGRIDWEST_X * TEXTFIELD_X),
                    (int) (WINDOW_Y * GRIDDIMENSION_Y * LABEL_Y)));
        }

    }

    /**
     * Initialize the contents of the frame.
     * 
     * @throws IOException
     */
    private void initialize() throws IOException {
        setTitle("Machi-Koro by Tentoni,Mondaini,Pracucci");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);

        final WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close This Application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(1);
                }
            }

            // Quando si riattiva la finestra.
            @Override
            public void windowActivated(final WindowEvent e) {
                setEnabled(true);
            }
        };
        addWindowListener(exitListener);

        final Image background = javax.imageio.ImageIO.read(this.getClass().getResource("/machi-koro.png"));
        setContentPane(new JPanel(new BorderLayout()) {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(final Graphics g) {
                g.drawImage(background, STARTVAR, STARTVAR, null);
            }
        });

        setSize(WINDOW_X, WINDOW_Y);
        final int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        final int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        rb.setIcon(new ImageIcon(imggreen));
        // JRadioButton 1
        rb1.setIcon(new ImageIcon(imgred));
        rb1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (vectPlayers[1] == 0) {
                    notIn(1, rb1, textcomputer1, lblac1, imgflash, 2);
                } else if (vectPlayers[1] == 1) {
                    playerIn(1, textcomputer1, lblac1, imgrobot1, 1);
                } else {
                    robotIn(1, rb1, textcomputer1, lblac1);
                }
            }
        });
        // JRadioButton 2
        rb2.setIcon(new ImageIcon(imgred));
        rb2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (vectPlayers[2] == 0) {
                    notIn(2, rb2, textcomputer2, lblac2, imgironman, 3);
                } else if (vectPlayers[2] == 1) {
                    playerIn(2, textcomputer2, lblac2, imgrobot2, 2);
                } else {
                    robotIn(2, rb2, textcomputer2, lblac2);
                }
            }
        });
        // JRadioButton 3
        rb3.setIcon(new ImageIcon(imgred));
        rb3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (vectPlayers[3] == 0) {
                    notIn(3, rb3, textcomputer3, lblac3, imgthor, 4);
                } else if (vectPlayers[3] == 1) {
                    playerIn(3, textcomputer3, lblac3, imgrobot3, 3);
                } else {
                    robotIn(3, rb3, textcomputer3, lblac3);
                }
            }
        });
        // Avatar Image Player
        lblap.setIcon(new ImageIcon(imgamerica));

        // Avatar Computer1
        lblac1.setIcon(new ImageIcon(imgrobot1));
        lblac1.setVisible(false);

        // Avatar Computer2
        lblac2.setIcon(new ImageIcon(imgrobot2));
        lblac2.setVisible(false);

        // Avatar Computer3
        lblac3.setIcon(new ImageIcon(imgrobot3));
        lblac3.setVisible(false);

        // Label Player
        text1.setVisible(true);

        // JButton StartMatch
        btnStartMatch.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (contatore > 0) {
                    int uguale = 0;
                    // Controllo che i nickname dei giocatori siano differenti
                    // l'uno dall'altro.
                    for (int i = 0, k = 1; i < listtextfield.size() - 1;) {
                        if (listtextfield.get(i).getText().equals(listtextfield.get(k).getText())
                                && vectPlayers[i] == 1) {
                            uguale = 1;
                            break;
                        }
                        if (k == 3) {
                            i++;
                            k = i + 1;
                        } else {
                            k++;
                        }
                    }
                    // Se sono differenti faccio partire il match.
                    if (uguale == 0) {
                        try {
                            final GUINewMatch gnm = new GUINewMatch(vectPlayers, 1, listtextfield, skin);
                            gnm.start();
                            dispose();
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        // Se non lo sono comparirà messaggio di errore.
                    } else {
                        JOptionPane.showMessageDialog(null, "Nicknames must be all differents!", "Same Nicknames",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    // Se non viene selezionato più di 1 giocatore.
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Add at least 1 player by clicking the red button, press the red button twice for add a computer opponent.",
                            "You can't play alone!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Button Back
        btnBack.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent arg0) {
                GUI gui;
                try {
                    // Torna alla schermata principale.
                    gui = new GUI();
                    gui.start();
                } catch (IOException e) {
                    System.out.println("Error btnBack");
                }
                dispose();
            }
        });

        // Button Settings
        btnSettings.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                GUISettings guis;
                try {
                    // Apre schermata "Scelta degli aspetti delle carte".
                    guis = new GUISettings();
                    link(guis);

                } catch (IOException e1) {
                    System.out.println("Error btnSettings");
                }
            }
        });

        getContentPane().add(panelgrideast, BorderLayout.EAST);
        getContentPane().add(panelgridwest, BorderLayout.WEST);
        getContentPane().add(panelgridinv, BorderLayout.SOUTH);
        panelgridinv.add(Box.createRigidArea(new Dimension(0, GRIDAREA_X)));
        panelgridinv.setOpaque(false);
        listtextfield.addAll(Arrays.asList(text1, textcomputer1, textcomputer2, textcomputer3));
        fillEast();
        fillWest();
        setDimension();

    }
}
