package it.unibo.apice.oop.machikoro.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import it.unibo.apice.oop.machikoro.controller.HaveMaxSameCardException;
import it.unibo.apice.oop.machikoro.controller.JudgeImpl;
import it.unibo.apice.oop.machikoro.model.AimCard;
import it.unibo.apice.oop.machikoro.model.AlreadyBoughtCardException;
import it.unibo.apice.oop.machikoro.model.IAImpl;
import it.unibo.apice.oop.machikoro.model.NotEnoughMoneyException;
import javafx.util.Pair;
import javax.swing.JButton;

/**
 * Classe usata per gestire l'intera partita che svolgono i giocatori.
 */
public class GUINewMatch extends JFrame {
    /**
     * Global Variables
     */
    private static final long serialVersionUID = 1L;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    private ImageIcon imageicon;

    // GridBagConstraints per gestire rispettivamente i pannelli elencati sotto.
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final GridBagConstraints gbc1 = new GridBagConstraints();
    private final GridBagConstraints gbc2 = new GridBagConstraints();
    private final GridBagConstraints gbc3 = new GridBagConstraints();
    private final GridBagConstraints gbc4 = new GridBagConstraints();
    private GridBagConstraints gbcgiantwest = new GridBagConstraints();
    private GridBagConstraints gbcgianteast = new GridBagConstraints();
    private GridBagConstraints gbcgiantsouth = new GridBagConstraints();
    private final GridBagConstraints gbccenter = new GridBagConstraints();

    // Pannelli usati per i giocatori e le carte in comune.
    private final JPanel panelgrid = new JPanel(new GridBagLayout());
    private final JPanel panelgridplayer1 = new JPanel(new GridBagLayout());
    private final JPanel panelgridplayer2 = new JPanel(new GridBagLayout());
    private final JPanel panelgridplayer3 = new JPanel(new GridBagLayout());
    private final JPanel panelgridplayer4 = new JPanel(new GridBagLayout());
    private final JPanel panelgiantgridwest = new JPanel(new GridBagLayout());
    private final JPanel gridbagcenter = new JPanel(new GridBagLayout());
    private final JPanel panelgiantgrideast = new JPanel(new GridBagLayout());
    private final JPanel panelgiantgridsouth = new JPanel(new GridBagLayout());

    private final JPanel flowlayout = new JPanel(new FlowLayout());
    private final JPanel flowlayoutplayer1 = new JPanel(new FlowLayout());
    private final JPanel flowlayoutplayer2 = new JPanel(new FlowLayout());
    private final JPanel flowlayoutplayer3 = new JPanel(new FlowLayout());
    private final JPanel flowlayoutplayer4 = new JPanel(new FlowLayout());

    // Immagine moneta e dado.
    private final Image coinimage = new ImageIcon(this.getClass().getResource("/Moneta.png")).getImage();

    // Immagine moneta per ogni player.
    private final JLabel coin1 = new JLabel();
    private final JLabel coin2 = new JLabel();
    private final JLabel coin3 = new JLabel();
    private final JLabel coin4 = new JLabel();

    // Per capire il giocatore di turno.
    private final JLabel lblplayerinturn = new JLabel();

    // Nomi dei player.
    private final JLabel lblplayer1 = new JLabel();
    private final JLabel lblplayer2 = new JLabel();
    private final JLabel lblplayer3 = new JLabel();
    private final JLabel lblplayer4 = new JLabel();

    // Quantità di monete.
    private final JLabel coinplayer1 = new JLabel();
    private final JLabel coinplayer2 = new JLabel();
    private final JLabel coinplayer3 = new JLabel();
    private final JLabel coinplayer4 = new JLabel();

    // Label che mi permette di capire quante volte una carta è posseduta da un
    // giocatore.
    private final JLabel per = new JLabel("");

    // Label immagine che si ingrandisce.
    private final JLabel lblBigImage = new JLabel("", SwingConstants.CENTER);

    // Label tiri dadi.
    private final JLabel labeldado = new JLabel();
    private final JLabel labeldado2 = new JLabel();

    // Istanza di Judge.
    private final JudgeImpl judge = new JudgeImpl();

    private static final int CARDS = 16;
    private static final int TOAIM = 12;
    private static final int FONTBUTTON = 30;
    private static final int FONTLABELVAR = 15;
    private static final int FONTBIGLABEL = 40;
    private static final int FONTLABELPLAYERTURN = 20;
    private static final int STARTX = 0;
    private static final int STARTY = 0;
    private static final int RB = 255;
    private static final int GB = 255;
    private static final int BB = 255;
    private static final int R = 0;
    private static final int G = 128;
    private static final int B = 255;
    private static final double NORTH_H = 0.10;
    private static final double SOUTH_H = 0.30;
    private static final double CENTER_H = 0.60;
    private static final double BUTTONUP_W = 0.25;
    private static final double BUTTONUP_H = 0.80;
    private static final double BUTTONUPOTHER_W = 0.12;
    private static final double LABELIMAGE_H = 0.90;
    private static final double GRIDCENTER_W = 0.30;
    private static final double LABELIMAGE_W = 0.20;
    private static final double LEFT_W = 0.35;
    private static final double LEFTFLOW_H = 0.08;
    private static final double LEFTGRID_H = 0.35;
    private static final int MAXNAME = 20;
    private static final int START_1 = 16;
    private static final int STOP_1 = 27;
    private static final int START_2 = 32;
    private static final int STOP_2 = 43;
    private static final int START_3 = 48;
    private static final int STOP_3 = 59;
    private static final int START_4 = 64;
    private static final int STOP_4 = 75;
    private static final double BUTTONW = 0.40 * 0.08;
    private static final double BUTTONH = 0.30;

    // Button Exit.
    private final JButton btnExit = new ButtonChanged("EXIT", FONTBUTTON);

    // Button Buy.
    private final JButton btnBuy = new ButtonChanged("BUY", FONTBUTTON);

    // Button Pass.
    private final JButton btnPass = new ButtonChanged("PASS", FONTBUTTON);

    // Button LaunchDice.
    private final JButton btnLaunchOne = new ButtonChanged("LAUNCH 1", FONTBUTTON);
    private final JButton btnLaunchTwo = new ButtonChanged("LAUNCH 2", FONTBUTTON);

    // Liste che contengono label e bottoni.
    private final List<JLabel> listlabelplayer = new LinkedList<JLabel>();
    private final List<JLabel> listlabelcoin = new LinkedList<JLabel>();
    private final List<JLabel> listlabelcoinimage = new LinkedList<JLabel>();
    private final List<JPanel> listflowlayoutplayer = new LinkedList<JPanel>();
    private final List<Pair<JButton, String>> list = new LinkedList<Pair<JButton, String>>();
    // Liste di bottoni che contengono ogni carta per ogni giocatore.
    private final List<JButton> listbutton = new LinkedList<JButton>();
    private final List<JButton> listbutton1 = new LinkedList<JButton>();
    private final List<JButton> listbutton2 = new LinkedList<JButton>();
    private final List<JButton> listbutton3 = new LinkedList<JButton>();
    private final List<JButton> listbutton4 = new LinkedList<JButton>();

    // Variabili di aiuto.
    private int skin2;
    private static String lastclicked = "";
    private String lastplayer;
    private String s = "";
    private int indiceclicked;
    private int win = 1;
    private int load = 1;

    /**
     * Lancia l'applicazione.
     */
    public void start() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }

    /**
     * Crea l'applicazione.
     * 
     * @param players
     *            Vettore composto da 0,1,2 dove 0 sta per giocatore vuoto, 1
     *            per giocatore reale e 2 computer.
     * @param n
     *            Variabile utilizzata per capire se l'utente ha voluto caricare
     *            una partita recentemente giocata.
     * @param list
     *            Lista contenente TextFields per ricavare i nomi dei giocatori.
     * @param skin
     *            Skin utilizzata
     * @throws IOException
     *             Eccezione che può essere causata dal programma.
     */
    public GUINewMatch(final int[] players, final int n, final List<JTextField> list, final int skin)
            throws IOException {
        super();
        if (n == 0) {
            // Se si tratta di una partita caricata.
            judge.loadMatch();
            skin2 = judge.loadSkin();

        } else {
            for (int i = 0; i < players.length; i++) {
                if (players[i] == 1) {
                    // Se non è una partita caricata, aggiungiamo i player alla
                    // partita.
                    if (list.get(i).getText().length() > MAXNAME) {
                        judge.addPlayer(list.get(i).getText().substring(0, MAXNAME), false);
                    } else {
                        judge.addPlayer(list.get(i).getText(), false);
                    }
                }
                if (players[i] == 2) {
                    judge.addPlayer(list.get(i).getText(), true);
                }
            }
            load = 0;
            this.skin2 = skin;
        }
        setPlayers();
        initialize();
    }

    // Metodo che mi permette di aggiornare la GUI con i player che hanno
    // iniziato a giocare.
    private void setPlayers() {
        listflowlayoutplayer
                .addAll(Arrays.asList(flowlayoutplayer1, flowlayoutplayer2, flowlayoutplayer3, flowlayoutplayer4));
        listlabelplayer.addAll(Arrays.asList(lblplayer1, lblplayer2, lblplayer3, lblplayer4));
        listlabelcoin.addAll(Arrays.asList(coinplayer1, coinplayer2, coinplayer3, coinplayer4));
        listlabelcoinimage.addAll(Arrays.asList(coin1, coin2, coin3, coin4));
        for (int i = 0; i < judge.getPlayers().size(); i++) {
            listlabelplayer.get(i).setText(judge.getPlayers().get(i).getName() + "  ");
            fontLabel(listlabelplayer.get(i), FONTLABELVAR);
            fontLabel(listlabelcoin.get(i), FONTLABELVAR);
            listlabelcoinimage.get(i).setIcon(new ImageIcon(coinimage));
        }
    }

    // Metodo che modifica esteticamente i bottoni e associa a quest'ultimi
    // un'immagine.
    private void createCard(final JButton btn, final String string) throws IOException {
        if (skin2 == 2) {
            final Image image = ImageIO.read(this.getClass().getResource("/" + string + "Cesena60x96.png"));
            imageicon = new ImageIcon(image);
        } else {
            final Image image = ImageIO.read(this.getClass().getResource("/" + string + "60x96.png"));
            imageicon = new ImageIcon(image);
        }
        // decoriamo i bottoni.
        btn.setBackground(new Color(R, G, B));
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btn.setIcon(imageicon);
        list.add(new Pair<JButton, String>(btn, string));
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getKey() == btn) {
                        // Settiamo l'immagine dei bottoni in base alla skin
                        // scelta dai giocatori.
                        Image image2 = null;
                        if (skin2 == 2) {
                            image2 = new ImageIcon(
                                    this.getClass().getResource("/" + list.get(i).getValue() + "Cesena.png"))
                                            .getImage();
                        } else {
                            image2 = new ImageIcon(this.getClass().getResource("/" + list.get(i).getValue() + ".png"))
                                    .getImage();
                        }
                        lblBigImage.setIcon(new ImageIcon(image2));
                        lastclicked = list.get(i).getValue();
                        indiceclicked = i;
                        // Operazioni per mostrare a video quanti edifici dello
                        // stesso tipo ha un giocatore.
                        if (indiceclicked >= START_1 && indiceclicked <= STOP_1) {
                            per.setText("X" + judge.sameCard(judge.getBoardCards().get(indiceclicked % 16),
                                    judge.getPlayers().get(0)));
                        } else if (indiceclicked >= START_2 && indiceclicked <= STOP_2) {
                            per.setText("X" + judge.sameCard(judge.getBoardCards().get(indiceclicked % 16),
                                    judge.getPlayers().get(1)));
                        } else if (indiceclicked >= START_3 && indiceclicked <= STOP_3) {
                            per.setText("X" + judge.sameCard(judge.getBoardCards().get(indiceclicked % 16),
                                    judge.getPlayers().get(2)));
                        } else if (indiceclicked >= START_4 && indiceclicked <= STOP_4) {
                            per.setText("X" + judge.sameCard(judge.getBoardCards().get(indiceclicked % 16),
                                    judge.getPlayers().get(3)));
                        } else {
                            per.setText(" ");
                        }

                    }
                }
            }
        });
    }

    // Metodo che data una lista aggiunge tutti i bottoni esistenti all'interno
    // di essa.
    private void addComponentToList(final List<JButton> listbuttongeneral) throws IOException {
        // Tutti i tipi di bottoni.
        final JButton btnFurnitureFactory = new JButton();
        final JButton btnForest = new JButton();
        final JButton btnCheeseFactory = new JButton();
        final JButton btnCafe = new JButton();
        final JButton btnWheatField = new JButton();
        final JButton btnRanch = new JButton();
        final JButton btnMiniMarket = new JButton();
        final JButton btnBakery = new JButton();
        final JButton btnFamilyRestaurant = new JButton();
        final JButton btnMine = new JButton();
        final JButton btnApple = new JButton();
        final JButton btnFruit = new JButton();
        final JButton btnStazione = new JButton();
        final JButton btnCentroCommerciale = new JButton();
        final JButton btnLunaPark = new JButton();
        final JButton btnTorreRadio = new JButton();

        // Aggiunta bottoni ad una lista.
        listbuttongeneral.addAll(Arrays.asList(btnWheatField, btnRanch, btnBakery, btnCafe, btnMiniMarket, btnForest,
                btnCheeseFactory, btnFurnitureFactory, btnMine, btnFamilyRestaurant, btnApple, btnFruit, btnStazione,
                btnCentroCommerciale, btnLunaPark, btnTorreRadio));
        // Setto dimensione bottoni.
        for (int i = 0; i < listbuttongeneral.size(); i++) {
            listbuttongeneral.get(i).setPreferredSize(
                    new Dimension((int) (dimension.width * BUTTONW), (int) (dimension.height * SOUTH_H * BUTTONH)));
        }
        // Creo le BoardCard
        for (int i = 0; i < judge.getBoardCards().size(); i++) {
            createCard(listbuttongeneral.get(i), judge.getBoardCards().get(i).getName());
        }
        // Creo le AimCard
        for (int i = 0; i < judge.getPlayers().get(1).getAimCards().size(); i++) {
            createCard(listbuttongeneral.get(i + TOAIM), judge.getPlayers().get(1).getAimCards().get(i).getName());
        }

    }

    // Metodo che permette di creare il posto dove tutti i giocatori possono
    // comprare le carte.
    private void createGridCommon(final JPanel p1, final GridBagConstraints g1) {
        g1.gridx = 0;
        g1.gridy = 1;
        for (int i = 0; i < listbutton.size(); i++) {
            if (i == TOAIM) {
                g1.gridy++;
                g1.gridx = 0;
                lblplayerinturn.setText(listlabelplayer.get(judge.getTurn()).getText());
                p1.add(lblplayerinturn, g1);
                g1.gridx = 4;
            }
            g1.gridx++;
            p1.add(listbutton.get(i), g1);
        }
        fontLabel(lblplayerinturn, FONTLABELPLAYERTURN);
    }

    // Metodo che permette di creare il posto di ogni giocatore, con le proprie
    // carte che ha comprato e che deve comprare.
    private void createGridPlayer(final JPanel p, final GridBagConstraints g, final List<JButton> listbuttongeneral) {
        g.gridx = 0;
        g.gridy = 0;
        for (int i = 0; i < listbuttongeneral.size(); i++) {
            if (i == listbuttongeneral.size() / 2) {
                g.gridy++;
                g.gridx = 0;
            }
            if (i == 0 || i == 1) {
                listbuttongeneral.get(i).setEnabled(true);
            } else {
                listbuttongeneral.get(i).setEnabled(false);
            }
            p.add(listbuttongeneral.get(i), g);
            g.gridx++;
        }
    }

    // Metodo che permette di modificare una label per adattarla al meglio alla
    // GUI.
    private void fontLabel(final JLabel lbl, final int n) {
        lbl.setForeground(new Color(RB, GB, BB));
        lbl.setFont(new Font("Segoe Print", Font.BOLD, n));
    }

    // Metodo che aggiorna le monete dei giocatori.
    private void refreshMoney() {
        for (int i = 0; i < judge.getPlayers().size(); i++) {
            final int monete = judge.getPlayers().get(i).getMoney();
            listlabelcoin.get(i).setText(String.valueOf(monete));
        }
    }

    // Messaggio che mostra l'IA quando tira i dadi e compra qualcosa.
    private void messageIA(final int i, final int n, final String s) {
        JOptionPane.showMessageDialog(null,
                judge.getPlayers().get(judge.getTurn() - i).getName() + " rolled: " + n + " and bought: " + s,
                "Computer's Turn ", JOptionPane.CANCEL_OPTION);
    }

    // Metodo che chiede di salvare appena si vuole chiudere la schermata.
    private void closeAndSave() {
        final int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close This Application?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == JOptionPane.YES_OPTION && win == 1) {
            final int confirm2 = JOptionPane.showOptionDialog(null, "Want to Save the Match?", "Save Confirmation",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm2 == JOptionPane.YES_OPTION) {
                judge.saveMatch(skin2);
            }
            dispose();
        } else if (confirm == JOptionPane.YES_OPTION && win == 0) {
            dispose();
        }

    }

    // Metodo che compra una carta.
    private int buyCard(final List<JButton> listbutton) {

        boolean result;
        try {
            // Se compra una AimCard.
            if (indiceclicked % CARDS >= TOAIM) {
                result = judge.wantBuy(
                        judge.getPlayers().get(judge.getTurn()).getAimCards().get(indiceclicked % CARDS - TOAIM));
            } else {
                // Se compra una BoardCard.
                result = judge.wantBuy(judge.getBoardCards().get(indiceclicked % CARDS));
            }

            // Vari tipi di eccezioni.
        } catch (NotEnoughMoneyException e) {
            JOptionPane.showMessageDialog(null, "You can't buy this card, you don't have enough money",
                    "You can't by this card!", JOptionPane.ERROR_MESSAGE);
            return 1;
        } catch (AlreadyBoughtCardException a) {
            JOptionPane.showMessageDialog(null, "You can't buy this card because u have it", "Error ",
                    JOptionPane.ERROR_MESSAGE);
            return 1;
        } catch (HaveMaxSameCardException h) {
            JOptionPane.showMessageDialog(null, "You can't buy this card because u have it in too much copy", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return 1;
        }
        // Visualizzazione a schermo della carta comprata.
        listbutton.get(indiceclicked % CARDS).setEnabled(true);
        // In caso di vittoria.
        if (result) {
            btnBuy.setEnabled(false);
            btnLaunchOne.setEnabled(false);
            btnLaunchTwo.setEnabled(false);
            btnPass.setEnabled(false);
            win = 0;
            JOptionPane.showMessageDialog(null, lastplayer + " WON!", "Victory!", JOptionPane.CLOSED_OPTION);
            return 2;
        }
        return 0;
    }

    // Azioni per l'intelligenza artificiale.
    private void controlIA() {
        // Controllo che sia un computer.
        if (judge.getPlayers().get(judge.getTurn()).isIAPlayer()) {
            btnLaunchOne.setEnabled(false);
            btnLaunchTwo.setEnabled(false);
            btnBuy.setEnabled(false);
            btnPass.setEnabled(false);
            boolean extra = false;
            per.setText(" ");
            lastclicked = "";
            // Controllo se rigioca un altro turno.
            if (judge.getPlayers().get(judge.getTurn()).getName().equals(lastplayer)) {
                JOptionPane.showMessageDialog(null,
                        judge.getPlayers().get(judge.getTurn()).getName() + " got an extra turn", "Extra turn!",
                        JOptionPane.CLOSED_OPTION);
                extra = true;
            }
            lastplayer = judge.getPlayers().get(judge.getTurn()).getName();
            lblBigImage.setIcon(null);
            JOptionPane.showMessageDialog(null, "Is " + judge.getPlayers().get(judge.getTurn()).getName() + "'s Turn",
                    "Computer's Turn ", JOptionPane.CANCEL_OPTION);
            // Tiro i dadi.
            final int numero = IAImpl.getIA().rollingDice(judge.getPlayers().get(judge.getTurn()));
            final int n = judge.rollDice(numero);
            labeldado.setText(String.valueOf(n));
            refreshMoney();
            if (judge.getPlayers().get(judge.getTurn()).getMoney() == 0) {
                s = "nothing";
                messageIA(0, n, s);
            } else {
                for (int k = 0; k < listbutton.size(); k++) {
                    // Compro carta.
                    if (judge.iaBuyCard().getName().equals(list.get(k).getValue())) {
                        s = list.get(k).getValue();
                        if (judge.getTurn() == 1) {
                            listbutton2.get(k).setEnabled(true);
                            break;
                        }
                        if (judge.getTurn() == 2) {
                            listbutton3.get(k).setEnabled(true);
                            break;
                        }
                        if (judge.getTurn() == 3) {
                            listbutton4.get(k).setEnabled(true);
                            break;
                        }
                    }
                }
            }
            boolean ok;
            // Visualizzazione monete aggiornate.
            refreshMoney();
            try {
                if (judge.getPlayers().get(judge.getTurn()).getMoney() == 0) {
                    judge.endTurn();
                    endTurn();
                } else {
                    ok = judge.wantBuy(judge.iaBuyCard());
                    if (ok) {
                        if (judge.getTurn() > 0) {
                            JOptionPane.showMessageDialog(null,
                                    judge.getPlayers().get(judge.getTurn() - 1).getName() + " YOU WON!", "Victory!",
                                    JOptionPane.CLOSED_OPTION);
                            return;
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    judge.getPlayers().get(judge.getPlayers().size() - 1).getName() + " YOU WON!",
                                    "Victory!", JOptionPane.CLOSED_OPTION);
                            return;
                        }
                    }
                    refreshMoney();
                    if (judge.getTurn() > 0) {
                        if (extra) {
                            messageIA(0, n, s);
                        } else {
                            messageIA(1, n, s);
                        }
                    } else {
                        JOptionPane
                                .showMessageDialog(null,
                                        judge.getPlayers().get(judge.getPlayers().size() - 1).getName() + " rolled: "
                                                + n + " and bought: " + s,
                                        "Computer's Turn", JOptionPane.CANCEL_OPTION);
                    }
                    endTurn();
                }
            } catch (HaveMaxSameCardException a) {
                JOptionPane.showMessageDialog(null, "You can't buy more than 4 establishments", "You can't by more",
                        JOptionPane.CLOSED_OPTION);
            } catch (AlreadyBoughtCardException a) {
                JOptionPane.showMessageDialog(null, "You already got it ", "Already got it", JOptionPane.CLOSED_OPTION);
            } catch (NotEnoughMoneyException e) {
                JOptionPane.showMessageDialog(null, "You have no money for buy this card", "No money",
                        JOptionPane.CLOSED_OPTION);
            }

        }

    }

    // Lancia dadi.
    private void launchDice(final int n, final JLabel label) {
        int dado;
        lastplayer = judge.getPlayers().get(judge.getTurn()).getName();
        dado = judge.rollDice(n);
        label.setText(String.valueOf(dado));
        refreshMoney();
        // Se può ritirare i dadi.
        if (judge.canReRoll()) {
            final int confirm = JOptionPane.showOptionDialog(null, "Want to ReRoll dice??", "ReRoll",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == JOptionPane.YES_OPTION) {
                dado = judge.rollDice(n);
                judge.checkCards(dado);
                refreshMoney();
            } else {
                judge.checkCards(dado);
                refreshMoney();
            }
        }
        label.setText(String.valueOf(dado));
        btnLaunchTwo.setEnabled(false);
        btnLaunchOne.setEnabled(false);
        btnPass.setEnabled(true);
        btnBuy.setEnabled(true);
    }

    // Finisce turno.
    private void endTurn() throws HaveMaxSameCardException, AlreadyBoughtCardException {
        btnBuy.setEnabled(false);
        btnLaunchOne.setEnabled(true);
        btnPass.setEnabled(false);
        labeldado.setText("");
        labeldado2.setText("");
        lblplayerinturn.setText(judge.getPlayers().get(judge.getTurn()).getName());
        if (judge.checkDoubleDice()) {
            btnLaunchTwo.setEnabled(true);
        }
        controlIA();

    }

    private void setDimension() {
        // Parte Sopra.
        flowlayout.setPreferredSize(new Dimension(dimension.width, (int) (dimension.height * NORTH_H)));
        btnLaunchOne.setPreferredSize(
                new Dimension((int) (dimension.width * BUTTONUP_W), (int) (dimension.height * NORTH_H * BUTTONUP_H)));
        btnLaunchTwo.setPreferredSize(
                new Dimension((int) (dimension.width * BUTTONUP_W), (int) (dimension.height * NORTH_H * BUTTONUP_H)));
        btnBuy.setPreferredSize(new Dimension((int) (dimension.width * BUTTONUPOTHER_W),
                (int) (dimension.height * NORTH_H * BUTTONUP_H)));
        btnPass.setPreferredSize(new Dimension((int) (dimension.width * BUTTONUPOTHER_W),
                (int) (dimension.height * NORTH_H * BUTTONUP_H)));
        btnExit.setPreferredSize(new Dimension((int) (dimension.width * BUTTONUPOTHER_W),
                (int) (dimension.height * NORTH_H * BUTTONUP_H)));

        // Parte Centrale.
        gridbagcenter.setPreferredSize(
                new Dimension((int) (dimension.width * GRIDCENTER_W), (int) (dimension.height * CENTER_H)));
        lblBigImage.setPreferredSize(new Dimension((int) (dimension.width * LABELIMAGE_W),
                (int) (dimension.height * CENTER_H * LABELIMAGE_H)));

        // Parte Sinistra.
        panelgiantgridwest
                .setPreferredSize(new Dimension((int) (dimension.width * LEFT_W), (int) (dimension.height * CENTER_H)));
        flowlayoutplayer1.setPreferredSize(
                new Dimension((int) (dimension.width * LEFT_W), (int) (dimension.height * CENTER_H * LEFTFLOW_H)));
        panelgridplayer1.setPreferredSize(
                new Dimension((int) (dimension.width * LEFT_W), (int) (dimension.height * CENTER_H * LEFTGRID_H)));
        flowlayoutplayer3.setPreferredSize(
                new Dimension((int) (dimension.width * LEFT_W), (int) (dimension.height * CENTER_H * LEFTFLOW_H)));
        panelgridplayer3.setPreferredSize(
                new Dimension((int) (dimension.width * LEFT_W), (int) (dimension.height * CENTER_H * LEFTGRID_H)));

        // Parte Destra.
        panelgiantgrideast
                .setPreferredSize(new Dimension((int) (dimension.width * LEFT_W), (int) (dimension.height * CENTER_H)));
        flowlayoutplayer2.setPreferredSize(
                new Dimension((int) (dimension.width * LEFT_W), (int) (dimension.height * CENTER_H * LEFTFLOW_H)));
        panelgridplayer2.setPreferredSize(
                new Dimension((int) (dimension.width * LEFT_W), (int) (dimension.height * CENTER_H * LEFTGRID_H)));
        flowlayoutplayer4.setPreferredSize(
                new Dimension((int) (dimension.width * LEFT_W), (int) (dimension.height * CENTER_H * LEFTFLOW_H)));
        panelgridplayer4.setPreferredSize(
                new Dimension((int) (dimension.width * LEFT_W), (int) (dimension.height * CENTER_H * LEFTGRID_H)));

        // Parte Sotto.
        panelgiantgridsouth
                .setPreferredSize(new Dimension((int) (dimension.width), (int) (dimension.height * SOUTH_H)));

    }

    // Metodo usato durante il load match per abilitare visualmente le carte che
    // un player ha in possesso.
    private void enableButtons() {
        for (int i = 0; i < judge.getPlayers().size(); i++) {
            for (int j = 0; j < judge.getPlayers().get(i).getAimCards().size(); j++) {
                // Carico AimCard.
                if (((AimCard) judge.getPlayers().get(i).getAimCards().get(j)).isEnabled() && j <= 3) {
                    if (i == 0) {
                        listbutton1.get(j + TOAIM).setEnabled(true);
                    }
                    if (i == 1) {
                        listbutton2.get(j + TOAIM).setEnabled(true);
                    }
                    if (i == 2) {
                        listbutton3.get(j + TOAIM).setEnabled(true);
                    }
                    if (i == 3) {
                        listbutton4.get(j + TOAIM).setEnabled(true);
                    }
                }
            }
            // Carico BoardCard.
            for (int k = 0; k < judge.getPlayers().get(i).getBoardCards().size(); k++) {
                for (int u = 0; u < listbutton1.size(); u++) {
                    if (judge.getPlayers().get(i).getBoardCards().get(k).getName().equals(list.get(u).getValue())) {
                        if (i == 0) {
                            listbutton1.get(u).setEnabled(true);
                        }
                        if (i == 1) {
                            listbutton2.get(u).setEnabled(true);
                        }
                        if (i == 2) {
                            listbutton3.get(u).setEnabled(true);
                        }
                        if (i == 3) {
                            listbutton4.get(u).setEnabled(true);
                        }
                    }
                }
            }
        }

    }

    // Metodo che crea il centro della grafica.
    private void createCenter() {
        gbccenter.gridx = STARTX;
        gbccenter.gridy = STARTY;
        gridbagcenter.add(lblBigImage, gbccenter);
        gbccenter.gridy = 1;
        gridbagcenter.add(per, gbccenter);
        gridbagcenter.setOpaque(false);

    }

    // Metodo che riempie una lista di panel flowlayout.
    private void fillFlowLayout() {
        for (int i = 0; i < judge.getPlayers().size(); i++) {
            listflowlayoutplayer.get(i).add(listlabelplayer.get(i));
            listflowlayoutplayer.get(i).add(listlabelcoinimage.get(i));
            listflowlayoutplayer.get(i).add(listlabelcoin.get(i));
            listflowlayoutplayer.get(i).setOpaque(false);
        }
    }

    // Metodo che crea la parte sinistra della schermata.
    private void fillWest() {
        gbcgiantwest.gridx = STARTX;
        gbcgiantwest.gridy = STARTY;
        panelgiantgridwest.add(flowlayoutplayer1, gbcgiantwest);
        gbcgiantwest.gridy++;
        panelgiantgridwest.add(panelgridplayer1, gbcgiantwest);
        gbcgiantwest.gridy++;
        if (judge.getPlayers().size() >= 3) {
            panelgiantgridwest.add(flowlayoutplayer3, gbcgiantwest);
            gbcgiantwest.gridy++;
            panelgiantgridwest.add(panelgridplayer3, gbcgiantwest);
        }
        panelgiantgridwest.setOpaque(false);
        panelgridplayer1.setOpaque(false);
        panelgridplayer3.setOpaque(false);
    }

    // Metodo che crea la parte bassa della schermata.
    private void fillSouth() {
        gbcgiantsouth.gridx = STARTX;
        gbcgiantsouth.gridy = STARTY;
        panelgiantgridsouth.add(lblplayerinturn, gbcgiantsouth);
        gbcgiantsouth.gridy++;
        panelgiantgridsouth.add(panelgrid, gbcgiantsouth);
        panelgiantgridsouth.setOpaque(false);
        panelgrid.setOpaque(false);
    }

    // Metodo che crea la parte destra della schermata.
    private void fillEast() {
        gbcgianteast.gridx = STARTX;
        gbcgianteast.gridy = STARTY;
        if (judge.getPlayers().size() >= 2) {
            panelgiantgrideast.add(flowlayoutplayer2, gbcgianteast);
            gbcgianteast.gridy++;
            panelgiantgrideast.add(panelgridplayer2, gbcgianteast);
        }
        if (judge.getPlayers().size() >= 4) {
            gbcgianteast.gridy++;
            panelgiantgrideast.add(flowlayoutplayer4, gbcgianteast);
            gbcgianteast.gridy++;
            panelgiantgrideast.add(panelgridplayer4, gbcgianteast);
        }
        panelgiantgrideast.setOpaque(false);
        panelgridplayer2.setOpaque(false);
        panelgridplayer4.setOpaque(false);
    }

    /**
     * Initialize the contents of the frame.
     * 
     * @throws IOException
     */
    private void initialize() throws IOException {
        setTitle("Machi-Koro by Tentoni,Mondaini,Pracucci");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addComponentToList(listbutton);
        addComponentToList(listbutton1);
        addComponentToList(listbutton2);
        addComponentToList(listbutton3);
        addComponentToList(listbutton4);
        fontLabel(labeldado, FONTBIGLABEL);
        fontLabel(labeldado2, FONTBIGLABEL);
        fontLabel(per, FONTBIGLABEL);

        // Creazione griglia comune e players.
        createGridCommon(panelgrid, gbc);
        createGridPlayer(panelgridplayer1, gbc1, listbutton1);
        for (int i = 1; i < judge.getPlayers().size(); i++) {
            if (i == 1) {
                createGridPlayer(panelgridplayer2, gbc2, listbutton2);
            } else if (i == 2) {
                createGridPlayer(panelgridplayer3, gbc3, listbutton3);
            } else if (i == 3) {
                createGridPlayer(panelgridplayer4, gbc4, listbutton4);
            }
        }
        // Finestra in schermo intero.
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        final Image background = ImageIO.read(this.getClass().getResource("/azzurro.png"));
        setContentPane(new JPanel(new BorderLayout()) {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(final Graphics g) {
                g.drawImage(background, STARTX, STARTY, null);
            }
        });

        final WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                closeAndSave();
            }
        };
        addWindowListener(exitListener);

        // BtnExit.
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                closeAndSave();
            }
        });

        // BtnBuy.
        btnBuy.addActionListener(new ActionListener() {
            private int result;

            public void actionPerformed(final ActionEvent arg0) {
                if (lastclicked.equals("")) {
                    JOptionPane.showMessageDialog(null, "You didn't select any card", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Se tutto va bene compra la carta.
                    if (judge.getTurn() == 0) {
                        result = buyCard(listbutton1);
                    } else if (judge.getTurn() == 1) {
                        result = buyCard(listbutton2);
                    } else if (judge.getTurn() == 2) {
                        result = buyCard(listbutton3);
                    } else if (judge.getTurn() == 3) {
                        result = buyCard(listbutton4);
                    }
                    if (result == 0) {
                        if (judge.getPlayers().get(judge.getTurn()).getName().equals(lastplayer)) {
                            JOptionPane.showMessageDialog(null,
                                    judge.getPlayers().get(judge.getTurn()) + " got an extra turn", "Extra turn!",
                                    JOptionPane.CLOSED_OPTION);
                        }
                        refreshMoney();
                        try {
                            endTurn();
                        } catch (HaveMaxSameCardException e) {
                            System.out.println("max same card");
                        } catch (AlreadyBoughtCardException e) {
                            System.out.println("already bought that card");
                        }
                    }

                }
            }
        });

        // BtnPass.
        btnPass.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                judge.endTurn();
                if (judge.getPlayers().get(judge.getTurn()).getName().equals(lastplayer)) {
                    JOptionPane.showMessageDialog(null, judge.getPlayers().get(judge.getTurn()) + " got an extra turn",
                            "Extra turn!", JOptionPane.CLOSED_OPTION);
                }
                try {
                    endTurn();
                } catch (HaveMaxSameCardException | AlreadyBoughtCardException e) {
                    System.out.println("Error btnPass");
                }
            }
        });

        // btnLaunchOne.
        btnLaunchOne.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                launchDice(1, labeldado);

            }
        });

        // btnLaunchTwo.
        btnLaunchTwo.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                launchDice(2, labeldado2);
            }
        });

        panelgridplayer2.setOpaque(false);
        getContentPane().add(panelgiantgridsouth, BorderLayout.SOUTH);
        getContentPane().add(panelgiantgridwest, BorderLayout.WEST);
        getContentPane().add(panelgiantgrideast, BorderLayout.EAST);
        getContentPane().add(gridbagcenter, BorderLayout.CENTER);
        getContentPane().add(flowlayout, BorderLayout.NORTH);
        fillSouth();
        fillFlowLayout();
        fillWest();
        fillEast();
        createCenter();
        flowlayout.setOpaque(false);
        flowlayout.add(btnLaunchOne);
        flowlayout.add(labeldado);
        flowlayout.add(btnLaunchTwo);
        flowlayout.add(labeldado2);
        flowlayout.add(btnBuy);
        flowlayout.add(btnPass);
        flowlayout.add(btnExit);
        setDimension();
        refreshMoney();
        if (load == 1) {
            enableButtons();
        }
        btnLaunchTwo.setEnabled(false);
        btnBuy.setEnabled(false);
        btnPass.setEnabled(false);
        setVisible(true);
    }

}