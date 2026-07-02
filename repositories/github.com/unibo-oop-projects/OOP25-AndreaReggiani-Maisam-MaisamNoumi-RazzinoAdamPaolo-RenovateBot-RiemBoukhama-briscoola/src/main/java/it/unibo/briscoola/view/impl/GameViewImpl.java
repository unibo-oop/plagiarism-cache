package it.unibo.briscoola.view.impl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import it.unibo.briscoola.controller.api.MenuController;
import it.unibo.briscoola.controller.impl.utils.Pair;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.view.api.CardView;
import it.unibo.briscoola.view.api.View;
import it.unibo.briscoola.view.api.popup.PopupFactory;
import it.unibo.briscoola.view.api.popup.Popups;
import it.unibo.briscoola.view.impl.popup.PopupFactoryImpl;

/**
 * Implementazion of {@link  View} interface.
 * This class extends {@link JFrame} and implements {@link View}, giving 
 * the visual container for the game board, the startup screen,
 * the player cards and the center match arena.
 *
 * @author Andrea Reggiani
 * @author Riem Boukhama
 */
public final class GameViewImpl extends JFrame implements View {

    private static final long serialVersionUID = 1L;

    private static final String MENU_ID = "MENU";
    private static final String GAME_ID = "GAME";
    private static final int NUMBER_OF_CARDS = 3;

    private static final int WINDOW_WIDTH = 1250;
    private static final int WINDOW_HEIGHT = 850;
    private static final int CARD_WIDTH = 120;
    private static final int CARD_HEIGHT = 170;

    private static final int BG_R = 20;
    private static final int BG_G = 80;
    private static final int BG_B = 25;

    private static final int BORDER_PADDING = 10;
    private static final int WEST_PADDING = 30;
    private static final int FLOW_GAP_HAND = 20;
    private static final int FLOW_GAP_DECK = 15;
    private static final int FLOW_GAP_TABLE = 30;
    private static final int EAST_SPACER_WIDTH = 380;

    private static final int FALLBACK_R = 100;
    private static final int FALLBACK_G = 149;
    private static final int FALLBACK_B = 237;

    private final JLabel deckLabel = new JLabel();
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel container = new JPanel(cardLayout);

    private final PileView playerPile = new PileView("Player");
    private final PileView cpuPile = new PileView("CPU");
    private final transient PopupFactory popup;

    private CardViewImpl briscolaCardView;
    private CardViewImpl playerPlayedCardView;
    private CardViewImpl cpuPlayedCardView;

    private final CardViewImpl[] playerHandCards = new CardViewImpl[NUMBER_OF_CARDS];
    private final CardViewImpl[] cpuHandCards = new CardViewImpl[NUMBER_OF_CARDS];

    private transient BiConsumer<String, Difficulty> onGameStartListener;
    private transient Consumer<Integer> onCardPlayedListener;

    private final transient MenuController menuController;
    private StartScreen startScreen;

    /**
     * Constructs a new {@code GameViewImpl} with the specified Menu Controller.
     * 
     * @param menuController the controller with the role of handling the menu events
     */
    public GameViewImpl(final MenuController menuController) {
        super("BriscOOla");
        this.menuController = menuController;
        this.popup = new PopupFactoryImpl(this.getRootPane(),
            () -> this.menuController != null ? this.menuController.getLeaderboardData() : List.of());
        this.initLayoutConfiguration();
    }

    /*
     * Configures the main window parameters.
     */
    private void initLayoutConfiguration() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        /*
         * initialization of StartScreen
         * with Play, Quit, Rules, Scores.
         */
        this.startScreen = new StartScreen(
            (players, diff) -> {
                if (this.onGameStartListener != null) {
                this.onGameStartListener.accept(players, diff);
                }
            },
            e -> quit()
        );

        final JPanel gamePanel = createGamePanel();

        container.add(startScreen, MENU_ID);
        container.add(gamePanel, GAME_ID);

        this.add(container);
        this.pack();
        this.setLocationRelativeTo(null); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOnGameStartListener(final BiConsumer<String, Difficulty> onStartGame) {
        this.onGameStartListener = onStartGame;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public void setOnCardPlayedListener(final Consumer<Integer> onCardPlayed) {
        this.onCardPlayedListener = onCardPlayed;
    }

    /** 
     * Creation of the board.
     * 
     * @return gamePanel 
     */
    private JPanel createGamePanel() {
        final JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(new Color(BG_R, BG_G, BG_B)); 

        /*
         * North Area, dedicated for the cpu.
         */
        final JPanel northArea = new JPanel(new BorderLayout());
        northArea.setOpaque(false); 
        northArea.setBorder(BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));

        final JPanel cpuHandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, FLOW_GAP_HAND, 0));
        cpuHandPanel.setOpaque(false);
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            cpuHandCards[i] = new CardViewImpl();
            cpuHandCards[i].renderCard(null, null); 
            cpuHandPanel.add(cpuHandCards[i]);
        }

        northArea.add(cpuHandPanel, BorderLayout.CENTER);
        northArea.add(cpuPile, BorderLayout.EAST); 
        mainPanel.add(northArea, BorderLayout.NORTH);

        /*
         * South Area, dedicated for the player.
         */
        final JPanel southArea = new JPanel(new BorderLayout());
        southArea.setOpaque(false); 
        southArea.setBorder(BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));

        final JPanel playerHandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, FLOW_GAP_HAND, 0));
        playerHandPanel.setOpaque(false);
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            playerHandCards[i] = new CardViewImpl();
            playerHandCards[i].renderCard(null, null);

            /*
             * final copy necessary for the usage with the lambda listener.
             */
            final int cardIndex = i;
            playerHandCards[i].addCardClickListener(e -> {
                if (this.onCardPlayedListener != null) {
                    this.onCardPlayedListener.accept(cardIndex);
                }
            });
            playerHandPanel.add(playerHandCards[i]);
        }

        southArea.add(playerHandPanel, BorderLayout.CENTER);
        southArea.add(playerPile, BorderLayout.EAST); 
        mainPanel.add(southArea, BorderLayout.SOUTH);

        final JPanel westArea = new JPanel(new GridBagLayout());
        westArea.setOpaque(false);
        westArea.setBorder(BorderFactory.createEmptyBorder(0, WEST_PADDING, 0, 0));

        final JPanel deckBriscolaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, FLOW_GAP_DECK, 0));
        deckBriscolaPanel.setOpaque(false);

        final URL deckUrl = getClass().getResource("/cards/deck.png");

        if (deckUrl != null) {
            final ImageIcon deckIcon = new ImageIcon(deckUrl);

            final Image img = deckIcon.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
            this.deckLabel.setIcon(new ImageIcon(img));
        } else {

            this.deckLabel.setText("Mazzo");
            this.deckLabel.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
            this.deckLabel.setOpaque(true);
            this.deckLabel.setBackground(new Color(FALLBACK_R, FALLBACK_G, FALLBACK_B));
            this.deckLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.deckLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }

        briscolaCardView = new CardViewImpl();
        briscolaCardView.renderCard(null, null);

        deckBriscolaPanel.add(this.deckLabel);
        deckBriscolaPanel.add(briscolaCardView);

        westArea.add(deckBriscolaPanel);
        mainPanel.add(westArea, BorderLayout.WEST);

        final JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);

        final JPanel tableCenter = new JPanel(new FlowLayout(FlowLayout.CENTER, FLOW_GAP_TABLE, 0));
        tableCenter.setOpaque(false);

        this.playerPlayedCardView = new CardViewImpl();
        this.cpuPlayedCardView = new CardViewImpl();

        this.playerPlayedCardView.setVisible(false);
        this.cpuPlayedCardView.setVisible(false);

        tableCenter.add(this.playerPlayedCardView);
        tableCenter.add(this.cpuPlayedCardView);

        centerWrapper.add(tableCenter);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        final JPanel eastSpacer = new JPanel();
        eastSpacer.setOpaque(false);
        eastSpacer.setPreferredSize(new Dimension(EAST_SPACER_WIDTH, 1));
        mainPanel.add(eastSpacer, BorderLayout.EAST);

        return mainPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.setVisible(true);
        this.setEscKeybind();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame() {
        /*
         * including this calls to update table.
         * without this the table, if the game will be stopped, 
         * and restarted with "Esc" -> "Menu" -> "Play" 
         * it would be graphicaly frozen on the previous match.
         * Now instead it will be all restarted.
         */
        updateTable(null, null, null, null, NUMBER_OF_CARDS);
        updatePile(0, true);
        updatePile(0, false);
        cardLayout.show(container, GAME_ID);
    }

    private void setEscKeybind() {
        final InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        final ActionMap actionMap = this.getRootPane().getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "escAction");
        actionMap.put("escAction", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!popup.isShowing()) {
                    popup.create(Popups.PAUSE, "Pause").show();
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHand(final int playerID, final List<Pair<String, String>> handCards) {
        if (playerID == 0) {

            /*
             * Update the cards showed to the player.
             */
            for (int i = 0; i < NUMBER_OF_CARDS; i++) {
                if (i < handCards.size()) {
                    final Pair<String, String> cardData = handCards.get(i);
                    final CardView cardComponent = this.playerHandCards[i];

                    final String seedStr = cardData.x();
                    final String valueStr = cardData.y();

                    cardComponent.renderCard(seedStr, valueStr);
                    this.playerHandCards[i].setVisible(true);
                } else {
                    this.playerHandCards[i].setVisible(false);
                }
            }
        } else {

            /*
             * Update the cards showed to the cpu.
             */
            for (int i = 0; i < NUMBER_OF_CARDS; i++) {
                if (i < handCards.size()) {
                    final CardView cardComponent = this.cpuHandCards[i];
                    cardComponent.renderCard(null, null);
                    this.cpuHandCards[i].setVisible(true);
                } else {
                    this.cpuHandCards[i].setVisible(false);
                }
            }
        }

        /*
         * update the elements.
         */
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * Changes the active layout state back to the Start/Menu screen.
     */
    public void showMainMenu() {
        if (this.startScreen != null) {
            this.startScreen.resetToMainMenu();
        }
        this.cardLayout.show(this.container, MENU_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePile(final int cardsCount, final boolean player) {
        if (player) {
            this.playerPile.updateCount(cardsCount);
        } else {
            this.cpuPile.updateCount(cardsCount);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayMessage(final Popups type, final String message) {
        popup.closeLatest();
        popup.create(type, message).show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        this.dispose();
    }

    /**
     * Returns a safe clone of the player hand components array.
     * 
     * @return an array containing the hand component views
     */
    @Override
    public List<CardView> getPlayerHandCards() {
        return List.of(this.playerHandCards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBriscola(final String seed, final String value) {
        this.briscolaCardView.renderCard(seed, value);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTable(final String playerSeed,
                            final String playerValue,
                            final String cpuSeed,
                            final String cpuValue,
                            final int deckSize) {

        /*
         * updates the graphic cards on the table of the player
         */
        if (playerSeed != null && playerValue != null) {
            this.playerPlayedCardView.renderCard(playerSeed, playerValue);
            this.playerPlayedCardView.setVisible(true);
        } else {
            this.playerPlayedCardView.setVisible(false);
        }

        /*
         * updates the graphic cards on the table of the cpu
         */
        if (cpuSeed != null && cpuValue != null) {
            this.cpuPlayedCardView.renderCard(cpuSeed, cpuValue);
            this.cpuPlayedCardView.setVisible(true);
        } else {
            this.cpuPlayedCardView.setVisible(false);
        }

        this.deckLabel.setVisible(deckSize > 0);

       final String imagePath = "/cards/deck.png";

        final URL deckUrl = getClass().getResource(imagePath);

        final ImageIcon deckIcon = new ImageIcon(deckUrl);
        final Image img = deckIcon.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
        this.deckLabel.setIcon(new ImageIcon(img));

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

}
