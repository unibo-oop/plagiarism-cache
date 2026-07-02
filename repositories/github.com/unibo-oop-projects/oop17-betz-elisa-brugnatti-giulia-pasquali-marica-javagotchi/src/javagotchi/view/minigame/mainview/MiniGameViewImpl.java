package javagotchi.view.minigame.mainview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.apache.commons.lang3.Pair;

import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.utility.Utility;
import javagotchi.view.minigame.AbstractFrameDefault;
import javagotchi.view.minigame.component.Components;
import javagotchi.view.minigame.component.GameButton;

/**
 * 
 * @author marica
 *
 */
public final class MiniGameViewImpl extends AbstractFrameDefault implements MiniGameView {

    private static final long serialVersionUID = 7089303176083988074L;
    private static final int WIDTH = 850;
    private static final int HEIGHT = 850;

    private static final String TITLEFRAME = "MiniGame";
    private static final Pair<Integer, Integer> DIMENSIONGAME = MiniGame.getFactoryController().getControllerMiniGame()
            .getModel().getGameGrid().getDimensionGame();

    private final JPanel panelGame = new JPanel(new GridLayout(DIMENSIONGAME.left, DIMENSIONGAME.right));
    private final JProgressBar progressTime = Components.createTimeProgress(this.getWidth() / 4);
    private final JLabel scoreLabel = new JLabel(
            MiniGame.getFactoryController().getControllerMiniGame().getModel().getScore().getStringScore());
    private final JButton pauseButton = Components.createPauseButton();
    private static final int FIRST_ROW = 0;
    private final List<GameButton> buttons = new ArrayList<>();

    /**
     * Constructor for ViewMiniGame.
     */
    public MiniGameViewImpl() {
        super(WIDTH, HEIGHT);
        this.startSetting();
        this.setGameView();
    }

    /**
     * Constructor for ViewMiniGame.
     * 
     * @param list
     *            {@link MiniGameViewImpl#buttons} is a list of previous game.
     */
    public MiniGameViewImpl(final List<GameButton> list) {
        super(WIDTH, HEIGHT);
        this.startSetting();
        this.rebuildGameView(list);
    }

    private void startSetting() {
        super.setTitle(TITLEFRAME);
        this.setPanelState();
        this.getContentPane().add(panelGame);
    }

    private void setPanelState() {
        final JPanel panelNorth = new JPanel(new GridLayout(1, 3, 5, 0));
        panelNorth.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().add(panelNorth, BorderLayout.NORTH);

        this.addInPanel(panelNorth, pauseButton, BorderLayout.WEST, new FlowLayout());
        final FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 0, 20);
        this.addInPanel(panelNorth, progressTime, BorderLayout.CENTER, layout);
        this.addInPanel(panelNorth, scoreLabel, BorderLayout.EAST, layout);
    }

    private void addInPanel(final JPanel panel, final JComponent comp, final Object layoutPosition,
            final LayoutManager layout) {
        final JPanel pane = new JPanel(layout);
        pane.setBackground(panel.getBackground());
        pane.add(comp, BorderLayout.CENTER);
        panel.add(pane, layoutPosition);
    }

    private void setGameView() {
        buttons.addAll(MiniGame.getFactoryController().getControllerMiniGame().getModel().getGameGrid().getCoords()
                .stream().map(Components.createGameButton()).peek(panelGame::add).collect(Collectors.toList()));

        buttons.stream().filter(b -> b.getCoord().left != DIMENSIONGAME.left - 1)
                .peek(b -> this.paintRow(b.getCoord().left)).collect(Collectors.toList());

        buttons.stream().peek(GameButton::setTextStart).collect(Collectors.toList());
        buttons.stream().peek(GameButton::disableGameButtonIfLastRow).collect(Collectors.toList());

    }

    private void paintRow(final int row) {
        this.getListGameButton(row).forEach(GameButton::reset);
        this.getRandomGameButton(row).repaintGameButton();
    }

    private List<GameButton> getListGameButton(final int row) {
        return this.buttons.stream().filter(e -> e.getCoord().left == row).collect(Collectors.toList());
    }

    private GameButton getRandomGameButton(final int row) {
        return getGameButton(Pair.of(row, Utility.generateRandom(DIMENSIONGAME.right).intValue()));
    }

    private GameButton getGameButton(final Pair<Integer, Integer> pair) {
        return this.buttons.stream().filter(e -> e.getCoord().equals(pair)).findFirst().get();
    }

    private void rebuildGameView(final List<GameButton> list) {
        this.buttons.addAll(
                list.stream().sorted(GameButton.inOrderToRow()).peek(panelGame::add).collect(Collectors.toList()));
    }

    @Override
    public void repaintGameView() {
        this.paintRow(FIRST_ROW);
        this.buttons.stream().sorted(GameButton.inOrderToRow()).peek(GameButton::disableGameButtonIfLastRow)
                .peek(panelGame::add).collect(Collectors.toList());
    }

    @Override
    public List<GameButton> getButtons() {
        return buttons;
    }

    @Override
    public void setTime(final int sec) {
        progressTime.setValue(sec);
        progressTime.setString(String.valueOf(sec));
    }

    @Override
    public void setScore(final String score) {
        this.scoreLabel.setText(score);
    }

    @Override
    public void reActive(final boolean cond) {
        this.pauseButton.setEnabled(cond);
        this.buttons.stream().filter(b -> b.getCoord().left.intValue() != DIMENSIONGAME.left - 1)
                .forEach(b -> b.setEnabled(cond));
        this.setFocusableWindowState(cond);
    }

    @Override
    protected void setEvent() {
    }

}
