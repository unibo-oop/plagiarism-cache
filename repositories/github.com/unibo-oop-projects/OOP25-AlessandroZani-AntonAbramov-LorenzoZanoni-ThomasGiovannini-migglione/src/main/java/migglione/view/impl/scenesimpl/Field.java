package migglione.view.impl.scenesimpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import migglione.controller.api.Controller;
import migglione.model.api.Player;
import migglione.model.impl.Card;
import migglione.model.impl.Mosquito;
import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.api.music.MusicTracks;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;
import migglione.view.impl.scenesimpl.Hovering.HoveringCard;

/** 
 * Class for managing the playing field's view, 
 * with both player's hands, decks and scores,
 * along with the current turn's played card.
 * */
public final class Field extends AbstractGamePanel implements MusicProvider {

    private static final long serialVersionUID = 9879879887L;

    private static final String CARDS_IMAGE_PATH = "/images/cards/";
    private static final String CARD_BACKSIDE_PATH = "/images/utilities/backside.png";
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
    private static final String FONT_NAME = "Times New Roman";
    private static final String PNG_EXT = ".png";
    private static final String CARD_CC_KEY = "card";
    private static final double CARDS_WIDTH_MULT = 0.25;
    private static final double CARDS_HEIGHT_MULT = 0.1;
    private static final int SPACE_BETWEEN_CARDS = 500;
    private static final int BASE_HEIGHT = 200;
    private static final int ATTR_BOX_WIDTH = 200;
    private static final int ATTR_BOX_HEIGHT = 150;
    private static final int STRUT_DISTANCE = 50;

    private final Font titleFont = new Font(FONT_NAME, Font.BOLD, 17);
    private final Font boxFont = new Font(FONT_NAME, Font.BOLD, 23);

    private final transient Image playField;
    private final transient Controller controller;
    private final String[] attrs = {"Attk", "Deff", "Strength", "Intelligence", "Stealth"};
    private boolean finalTurn;

    private final JPanel pCards = new JPanel();
    private final JPanel oCards = new JPanel();
    private final JPanel mainField = new JPanel();
    private final JPanel scoreCol = new JPanel();
    private final JPanel plays = new JPanel();
    private final JButton pScore = new JButton("00");
    private final JButton oScore = new JButton("00");
    private final JButton pPlay = new JButton();
    private final JButton oPlay = new JButton(); 
    private final JComboBox<String> attrChoice = new JComboBox<>(attrs);

    private int cycleCount;
    private transient Optional<Timer> timer = Optional.empty();

    private transient HoveringCard hoveredCard;

    /**
     * Constructor of this class. 
     * Divides screen into play field, player's hand,
     * opponent's hand, menu and scores.
     * 
     * @param controller the controller to use when interacting with the model side of the game.
     */
    public Field(final Controller controller) {

        this.controller = controller;
        this.playField = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();
        this.setLayout(new BorderLayout());

        pCards.setOpaque(false);
        oCards.setOpaque(pCards.isOpaque());
        pCards.setLayout(new FlowLayout(FlowLayout.CENTER, SPACE_BETWEEN_CARDS, SPACE_BETWEEN_CARDS));
        oCards.setLayout(pCards.getLayout());

        this.addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(final ComponentEvent e) {
                resizeCards();
            }

            @Override
            public void componentMoved(final ComponentEvent e) {
            }

            @Override
            public void componentShown(final ComponentEvent e) {
                resizeCards();
            }

            @Override
            public void componentHidden(final ComponentEvent e) {
            }

        });

        final JPanel attrHold = createAttributeBox();

        for (final Player p : controller.getPlayers()) {
            final boolean isCPU = p.equals(controller.getPlayers().getLast());
            final JPanel pHand = isCPU ? oCards : pCards;
            for (final Card c : p.getHand()) {
                final JButton card = new JButton();
                changeIcon(card, isCPU ? CARD_BACKSIDE_PATH : CARDS_IMAGE_PATH + c.getName() + PNG_EXT);
                setTransparentWithIcon(card);
                card.putClientProperty(CARD_CC_KEY, c);
                if (!isCPU) {
                    card.addMouseListener(new Hovering(c, this));
                    card.addActionListener(createCardListener(card, p));
                }
                pHand.add(card);
            }
        }

        this.add(oCards, BorderLayout.NORTH);
        this.add(mainField, BorderLayout.CENTER);
        this.add(pCards, BorderLayout.SOUTH);

        pScore.setBackground(Color.BLUE);
        oScore.setBackground(Color.RED);
        pScore.setEnabled(false);
        oScore.setEnabled(false);
        pScore.setBorderPainted(false);
        oScore.setBorderPainted(false);

        mainField.setOpaque(false);
        mainField.setLayout(new BorderLayout());
        mainField.add(scoreCol, BorderLayout.EAST);
        mainField.add(attrHold, BorderLayout.WEST);
        mainField.add(plays);
        plays.add(pPlay, BorderLayout.WEST);
        Set.of(oPlay, pPlay).forEach(b -> {
            setTransparentWithIcon(b);
        });
        plays.add(oPlay, BorderLayout.EAST);
        plays.setOpaque(false);

        final JPanel centerCards = new JPanel();
        centerCards.setLayout(new BoxLayout(centerCards, BoxLayout.X_AXIS));
        centerCards.setOpaque(false);
        centerCards.add(Box.createHorizontalGlue());
        centerCards.add(pPlay);
        centerCards.add(Box.createHorizontalStrut(STRUT_DISTANCE));
        centerCards.add(oPlay);
        centerCards.add(Box.createHorizontalGlue());
        plays.add(centerCards);

        scoreCol.setLayout(new BoxLayout(scoreCol, BoxLayout.Y_AXIS));
        scoreCol.setOpaque(false);
        scoreCol.add(oScore);
        scoreCol.add(Box.createVerticalGlue());
        scoreCol.add(pScore);

        final Set<JPanel> cards = new HashSet<>();
        cards.add(oCards);
        cards.add(pCards);
        for (final JPanel p : cards) {
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        }
    }

    @Override
    public void doLayout() {
        super.doLayout();
        final Dimension newSize = new Dimension(getWidth(), getHeight() / 4);
        pCards.setPreferredSize(newSize);
        oCards.setPreferredSize(newSize);
    }

    private void resizeCards() {
        for (final var c : pCards.getComponents()) {
            if (c instanceof JButton) {
                final JButton button = (JButton) c;
                final Card card = (Card) button.getClientProperty(CARD_CC_KEY);
                if (card != null) {
                    changeIcon(button, CARDS_IMAGE_PATH + card.getName() + PNG_EXT);
                }
            }
        }

        for (final var c : oCards.getComponents()) {
            if (c instanceof JButton) {
                final JButton button = (JButton) c;
                changeIcon(button, CARD_BACKSIDE_PATH);
            }
        }

        if (pPlay.getIcon() != null) {
            final Card card = (Card) pPlay.getClientProperty(CARD_CC_KEY);
            if (card != null) {
                changeIcon(pPlay, CARDS_IMAGE_PATH + card.getName() + PNG_EXT);
            }
        }

        if (oPlay.getIcon() != null && oPlay.isVisible()) {
            final Card card = (Card) oPlay.getClientProperty(CARD_CC_KEY);
            if (card != null) {
                changeIcon(oPlay, CARD_BACKSIDE_PATH);
            }
        }
    }

    /**
     * Method for hovering(used by mouseEntered).
     * 
     * @param card the hovering Card
     */
    public void setHoveredCard(final HoveringCard card) {
        this.hoveredCard = card;
        repaint();
    }

    /**
     * Method for hovering(used by mouseExited).
     */
    public void clearHoveredCard() {
        this.hoveredCard = null;
        repaint();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (hoveredCard == null) {
            return;
        }
        final Image cardImg = new ImageIcon(getClass().getResource(hoveredCard.getImage())).getImage();
        final Image statsImg = new ImageIcon(getClass().getResource(hoveredCard.getStats())).getImage();
        g.drawImage(cardImg, (this.getWidth() - getCardResizableWidth() * 2) / 2,
            this.getHeight() * 2 / 3, getCardResizableWidth(), getCardResizableHeight(), this);
        g.drawImage(statsImg, ((this.getWidth() - getCardResizableWidth() * 2) / 2)
            + getCardResizableWidth(), this.getHeight() * 2 / 3, getCardResizableWidth(),
            getCardResizableHeight(), this);
    }

    private void changeIcon(final JButton jb, final String path) {
        final int cardsWidth = getCardResizableWidth();
        final int cardHeight = getCardResizableHeight();

        final ImageIcon bc = new ImageIcon(
            getClass().getResource(path)
        );
        final ImageIcon bg = new ImageIcon(
            bc.getImage().getScaledInstance(cardsWidth, cardHeight, Image.SCALE_SMOOTH)
        );
        jb.setIcon(bg);
    }

    private ActionListener createCardListener(final JButton jb, final Player p) {
        return new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent dispose) {
                if (!timer.isEmpty()) {
                    return;
                }
                cycleCount = 0;
                final Card cc = (Card) jb.getClientProperty(CARD_CC_KEY);
                final Timer t = new Timer(850, new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        switch (cycleCount) {
                            case 0: if (controller.getTurnLeader().equals(p)) {
                                        controller.playTurnLead(
                                            attrChoice.getItemAt(attrChoice.getSelectedIndex()), cc
                                        );
                                    } else {
                                        controller.playTurnTail(cc);
                                    }
                                    pPlay.putClientProperty(CARD_CC_KEY, cc);
                                    changeIcon(pPlay, CARDS_IMAGE_PATH + cc.getName() + PNG_EXT);
                                    pPlay.setVisible(true); 
                                    pPlay.addMouseListener(new Hovering(cc, Field.this));
                                    resetHandIcons();
                                    break;
                            case 1: if (controller.getTurnLeader().equals(p)) {
                                        controller.playTurnTail(cc);
                                        final Card sub = controller
                                                        .getPlayers()
                                                        .getLast()
                                                        .getPlayedCard();
                                        oPlay.putClientProperty(CARD_CC_KEY, sub);
                                        changeIcon(oPlay, CARD_BACKSIDE_PATH);
                                        oPlay.setVisible(true);
                                    }
                                    resetHandIcons();
                                    break;
                            case 2: flipCards();
                                    finalTurn = controller.playTurnStored();
                                    break;
                            case 3: updateScores();
                                    Set.of(pPlay, oPlay).forEach(jb -> {
                                        jb.setIcon(null);
                                        for (final MouseListener ml : jb.getMouseListeners()) {
                                            jb.removeMouseListener(ml);
                                        }
                                    });
                                    if (finalTurn) {
                                        ((Timer) e.getSource()).stop();
                                    }
                                    controller.checkSession();
                                    break;
                            case 4: if (!p.equals(controller.getTurnLeader())) {
                                        controller.playTurnLead(attrs[0], cc);
                                        final Card sub = controller.getTurnLeader().getPlayedCard();
                                        oPlay.putClientProperty(CARD_CC_KEY, sub);
                                        changeIcon(oPlay, CARD_BACKSIDE_PATH);
                                        oPlay.setVisible(true);
                                        attrChoice.setEnabled(false);
                                    } else {
                                        attrChoice.setEnabled(true);
                                    }
                                    attrChoice.setSelectedItem(controller.getCurrAttr());
                                    resetHandIcons();
                                    ((Timer) e.getSource()).stop();
                                    timer = Optional.empty();
                                    break;
                                default: ((Timer) e.getSource()).stop();
                                        break;
                        }
                        cycleCount++;
                    }

                });
                timer = Optional.of(t);
                t.start();
            }
        };
    }

    private int getCardResizableHeight() {
        final int height = getHeight();
        return height == 0 ? BASE_HEIGHT : (int) (getHeight() * CARDS_WIDTH_MULT);
    }

    private int getCardResizableWidth() {
        final int width = getWidth();
        return width == 0 ? 100 : (int) (getWidth() * CARDS_HEIGHT_MULT);
    }

    private void flipCards() {
        for (final JButton jb : Set.of(oPlay, pPlay)) {
            changeIcon(jb, CARDS_IMAGE_PATH + ((Card) jb.getClientProperty(CARD_CC_KEY)).getName() + PNG_EXT);
            jb.addMouseListener(new Hovering((Card) jb.getClientProperty(CARD_CC_KEY), this));
        }
    }

    private void resetHandIcons() {
        for (final Player p : controller.getPlayers()) {
            final boolean isCPU = p.equals(controller.getPlayers().getLast());
            final JPanel pHand = isCPU ? oCards : pCards;
            pHand.removeAll();
            for (final Card c : p.getHand()) {
                final JButton jb = new JButton();
                jb.putClientProperty("card", c);
                changeIcon(jb, isCPU ? CARD_BACKSIDE_PATH : CARDS_IMAGE_PATH + c.getName() + PNG_EXT);
                setTransparentWithIcon(jb);
                if (!isCPU) {
                    jb.addMouseListener(new Hovering(c, this));
                    jb.addActionListener(createCardListener(jb, p));
                }
                pHand.add(jb);
            }

            pHand.revalidate();
            pHand.repaint();
        }
    }

    private void updateScores() {
        for (final Player p : controller.getPlayers()) {
            final JButton b = (p instanceof Mosquito) ? oScore : pScore;
            b.setText(
                controller.getScore(p) >= 10 ? String.valueOf(controller.getScore(p)) : "0" + controller.getScore(p)
            );
        }
    }

    private void setTransparentWithIcon(final JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    private JPanel createAttributeBox() {
        final JPanel attrBox = new JPanel();
        final JLabel title = new JLabel("CURRENT ATTRIBUTE:");

        title.setFont(titleFont);
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setBackground(Color.BLACK);
        title.setOpaque(true);
        title.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        title.setForeground(Color.YELLOW);

        attrBox.setLayout(new BoxLayout(attrBox, BoxLayout.Y_AXIS));
        attrBox.add(Box.createVerticalGlue());
        attrBox.add(title);

        attrChoice.setFont(boxFont);
        attrChoice.setBackground(Color.BLACK);
        attrChoice.setForeground(Color.YELLOW);
        attrChoice.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        attrChoice.setAlignmentX(CENTER_ALIGNMENT);
        attrChoice.setMaximumSize(new Dimension(ATTR_BOX_WIDTH, ATTR_BOX_HEIGHT));

        attrBox.add(attrChoice);
        attrBox.add(Box.createVerticalGlue());
        attrBox.setOpaque(false);

        return attrBox;
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(MusicTracks.DELTARUNE.getTrackPath());
    }

    @Override
    protected Image getImage() {
        return playField;
    }
}
