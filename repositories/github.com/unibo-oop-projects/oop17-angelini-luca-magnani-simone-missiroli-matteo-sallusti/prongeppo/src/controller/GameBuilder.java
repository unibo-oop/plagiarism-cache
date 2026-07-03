package controller;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AIImpl.Difficult;
import javafx.util.Pair;
import model.Ball;
import model.BallImpl;
import model.Bar;
import model.BarImpl;
import model.World;
import model.WorldImpl;
import model.Ball.Combo;
import utility.BallRes;
import utility.GameValues;
import utility.GraphicType;
import utility.PlayerType;
import utility.Utilities;
import view.PongElement;
import view.panels.MainLedge;
import view.panels.PongPanel;

/**
 * This class is a builder for a new game.
 * @author Missi
 */
public class GameBuilder {
    private boolean built;
    private int nball;
    private List<Optional<PongElement>> graphicBarList;
    private GraphicManager gmanager;
    private List<BallRes> resList;
    private int index;
    private List<PlayerType> playerTypeList;
    private Pair<Combo, Combo> comboPair;
    private Image bgImage;
    private JFrame frame;
    private JPanel panel;

    /**
     * @param nball **the number of balls**
     */
    public void setNball(final int nball) {
        this.check();
        this.nball = nball;
    }
    /**
     * @param graphicBarList **the list of graphic elem to be used by the bars**
     */
    public void setGraphicBarList(final List<Optional<PongElement>> graphicBarList) {
        this.check();
        this.graphicBarList = graphicBarList;
    }
    /**
     * @param gmanager **the class that instantiate/remove graphic elems**
     */
    public void setGmanager(final GraphicManager gmanager) {
        this.check();
        this.gmanager = gmanager;
    }
    /**
     * @param resList **the list of res used**
     */
    public void setResList(final List<BallRes> resList) {
        this.check();
        this.resList = resList;
    }
    /**
     * @param index **the index of the selected res**
     */
    public void setIndex(final int index) {
        this.check();
        this.index = index;
    }
    /**
     * @param playerTypeList **the list of selected player type**
     */
    public void setPlayerTypeList(final List<PlayerType> playerTypeList) {
        this.check();
        this.playerTypeList = playerTypeList;
    }
    /**
     * @param comboPair **the pair of combo selected**
     */
    public void setComboPair(final Pair<Combo, Combo> comboPair) {
        this.check();
        this.comboPair = comboPair;
    }
    /**
     * @param bgImage **the bgImage of the game**
     */
    public void setBgImage(final Image bgImage) {
        this.check();
        this.bgImage = bgImage;
    }
    /**
     * @param frame **the frame that have to add the game panel**
     */
    public void setFrame(final JFrame frame) {
        this.check();
        this.frame = frame;
    }
    /**
     * @param panel **the panel to substitute with the game panel**
     */
    public void setPanel(final JPanel panel) {
        this.check();
        this.panel = panel;
    }
    /**
     * Builds a new game: a GameLoop, a World, a GraphicEnvironment.
     */
    public void build() {
        this.checkNull();
        this.built = true;
        final List<PongElement> graphicBallList = IntStream.range(0, this.nball).mapToObj(i -> this.gmanager.createPongElement(this.resList.get(this.index).toString() + ".png", GraphicType.BALL))
                .collect(Collectors.toList()); 
        final List<Ball> ballList = graphicBallList.stream().map(graphicBall -> new BallImpl(graphicBall)).collect(Collectors.toList());
        final List<Optional<Bar>> barList = this.graphicBarList.stream().map(graphicBar -> { 
            Optional<Bar> ipoteticBar; if (graphicBar.isPresent()) { 
                ipoteticBar = Optional.of(new BarImpl(graphicBar.get())); 
            } else { 
                ipoteticBar = Optional.empty(); 
            }
            return ipoteticBar; 
        }).collect(Collectors.toList());
        final List<Integer> keys = Utilities.readFromFile(GameValues.KEYPATH);
        final List<Bar> team1ToPass = new LinkedList<>();
        final List<Bar> team2ToPass = new LinkedList<>();
        final List<Input> inputToPass = new LinkedList<>();
        final List<KeyListener> listenerToPass = new LinkedList<>();
        IntStream.range(0, this.playerTypeList.size()).forEach(i -> {
            if (barList.get(i).isPresent()) {
                if (i % 2 == 0) {
                    team1ToPass.add(barList.get(i).get());
                    barList.get(i).get().setPosition(new Point(GameValues.DISTANCEFROMBOUND, 
                            GameValues.WORLDHEIGHT / 2));
                } else {
                    team2ToPass.add(barList.get(i).get());
                    barList.get(i).get().setPosition(new Point(GameValues.WORLDWIDTH
                            - (GameValues.BARX + GameValues.DISTANCEFROMBOUND), 
                            GameValues.WORLDHEIGHT / 2));
                }
                switch (this.playerTypeList.get(i)) {
                case PLAYER:
                    final InputListener input = new InputListener(keys.get(i * 2), keys.get(i * 2 + 1), barList.get(i).get());
                    inputToPass.add(input);
                    listenerToPass.add(input);
                    break;
                case AI_EASY:
                    inputToPass.add(new AIImpl(Difficult.EASY, ballList, barList.get(i).get()));
                    break;
                case AI_MEDIUM:
                    inputToPass.add(new AIImpl(Difficult.MEDIUM, ballList, barList.get(i).get()));
                    break;
                case AI_HARD:
                    inputToPass.add(new AIImpl(Difficult.HARD, ballList, barList.get(i).get()));
                    break;
                default:
                    break;
                }
            }
        }); 
        final World world = new WorldImpl(ballList, this.comboPair, this.gmanager, team1ToPass, team2ToPass, inputToPass); 
        final JPanel panelToPass = new PongPanel(this.bgImage, this.gmanager, listenerToPass); 
        final MainLedge ledge = new MainLedge(panelToPass, this.frame);
        Utilities.changePanel(this.frame, this.panel, ledge);
        final Runnable game = new GameLoop(ledge, world); 
        new Thread(game).start(); 
    }

    private void checkNull() {
        this.check();
        if (this.nball <= 0) {
            throw new IllegalArgumentException();
        }
        if (this.index < 0 || this.index > this.resList.size()) {
            throw new IllegalArgumentException();
        }
        Objects.requireNonNull(this.graphicBarList);
        Objects.requireNonNull(this.gmanager);
        Objects.requireNonNull(this.resList);
        Objects.requireNonNull(this.playerTypeList);
        Objects.requireNonNull(this.comboPair);
        Objects.requireNonNull(this.bgImage);
        Objects.requireNonNull(this.frame);
        Objects.requireNonNull(this.panel);
    }

    private void check() {
        if (built) {
            throw new IllegalStateException("already built");
        }
     }
}
