package it.unibo.controller;

import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayDeque;
import java.util.HashMap;

import it.unibo.controller.interfaces.BulletControllerInterface;
import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.BulletModel;
import it.unibo.model.CannonModel;
import it.unibo.model.Grid;
import it.unibo.model.KeyboardModel;
import it.unibo.model.Point2DI;
import it.unibo.model.Puyo;
import it.unibo.model.Scale;
import it.unibo.model.ScoreModel;
import it.unibo.model.interfaces.PuyoInterface;
import it.unibo.view.CannonView;

/**
 * A controller responsible for handling the {@link BulletModel} mechanics.
 * Bullets are fired sequentially from the cannon and
 * interact with the {@link Grid} of {@link Puyo}.
 * This controller listens for game ticks, processes keyboard input,
 * updates the bullet movement, triggers an explosion or unfreeze when
 * the target is hit, triggers the progress bar reset,
 * and updates the {@link ScoreModel} points.
 */
public class BulletController implements TickListenerInterface, BulletControllerInterface {
    /**
     * The bullet model
     */
    private final BulletModel bulletModel;
    /**
     * The grid of puyos
     */
    private final Grid grid;
    /**
     * The keyboard model that repeatedly checks the pressed keys
     */
    private final KeyboardModel k;
    /**
     * The progress bar controller whose reset occurs on an unfreeze
     */
    private final ProgressBarController progressBar;
    /**
     * The cannon view, used to make the bullet always start from
     * the center of the cannon through its positions on the x axis
     */
    private CannonView cannonView;
    /**
     * The graphic's scaling
     */
    private final Scale scale;
    /**
     * The score controller, used to update the score during an explosion
     */
    private final ScoreController scoreController;
    /**
     * The coordinates of the cannon's target, aka where the player is aiming
     */
    private Point2DI target;

    /**
     * Constructs a new bullet controller
     * 
     * @param bulletModel     The bullet model
     * @param grid            The game grid of puyos
     * @param k               The keyboard model for the space bar input
     * @param progressBar     The progress bar controller
     * @param cannonView      The cannon render
     * @param scoreController The controller for the score points
     * @param scale           The graphic's scaling
     */
    public BulletController(BulletModel bulletModel, Grid grid, KeyboardModel k, ProgressBarController progressBar,
            CannonView cannonView, ScoreController scoreController, Scale scale) {
        this.bulletModel = bulletModel;
        this.grid = grid;
        this.k = k;
        this.progressBar = progressBar;
        this.cannonView = cannonView;
        this.scoreController = scoreController;
        this.scale = scale;
    }

    /**
     * This method is executed every game tick and controls bullet behavior.
     * If the spacebar is pressed and the bullet is currently inactive, it fires.
     * The bullet's start and target positions are determined based on the cannon's
     * angle and x position,
     * and then approximated to a Point2DI position, to improve the grid
     * explosions' precision.
     * When the bullet cannot longer update its position, it calls explodePuyos.
     */
    @Override
    public void onTick() {
        if (k.isKeyPressed(KeyEvent.VK_SPACE) && !bulletModel.isActive()) {
            Point2DI source = cannonView.getCenter();
            CannonModel cannonModel = cannonView.getCannonModel();
            int puyoCol = Math.min((int) (cannonModel.getX() * 8), 7);
            int puyoRow = Math.min((int) ((1.0 - cannonModel.getAngle()) * 8), 7);
            this.target = new Point2DI(puyoCol, puyoRow);
            int puyoCellSize = this.scale.getScale() / 16;
            int puyoGridOffsetX = puyoCellSize * 4;
            int puyoGridOffsetY = puyoCellSize;
            Point2DI target = new Point2DI(
                    puyoGridOffsetX + (int) (cannonModel.getX() * grid.getCols() * puyoCellSize),
                    puyoGridOffsetY + (int) ((1.0 - cannonModel.getAngle()) * grid.getRows() * puyoCellSize));
            bulletModel.shootBullet(Point2DI.toPoint2D(source), Point2DI.toPoint2D(target));
        }
        if (bulletModel.isActive()) {
            if (!bulletModel.updatePosition()) {
                explodePuyos(target);
            }
        }
    }

    /**
     * Handles the explosion logic when a bullet reaches a Puyo.
     * If no Puyo exists at the target location, exits the function.
     * If the Puyo is already marked for explosion (deathClock is set), exits the
     * function.
     * If the targeted Puyo is frozen, checks if the progress bar allows unfreezing
     * it.
     * Uses Breadth-First Search (BFS) to find all connected Puyos of the same
     * color.
     * Iterates over neighboring cells,
     * Stops searching if: the position is already checked, doesn't contain a Puyo,
     * is outside the grid,
     * contains a Puyo of a different color, contains a frozen Puyo.
     * Marks each connected Puyo for explosion after a short delay based on
     * distance.
     * Adds points based on the number of Puyos destroyed.
     */
    @Override
    public void explodePuyos(Point2DI target) {
        PuyoInterface puyo = grid.getPuyo(target.x(), target.y());
        if (puyo == null) {
            return;
        }
        if (puyo.getDeathClock().isPresent()) {
            return;
        }
        if (puyo.getFreezeClock().isPresent()) {
            if (progressBar.resetBar()) {
                puyo.setFreezeClock(Optional.empty());
            }
            return;
        }
        ArrayDeque<Point2DI> q = new ArrayDeque<>();
        Map<Point2DI, Integer> d = new HashMap<>();
        q.add(target);
        d.put(target, 0);
        while (!q.isEmpty()) {
            Point2DI u = q.poll();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0 || dx != 0 && dy != 0) {
                        continue;
                    }
                    Point2DI v = Point2DI.add(u, new Point2DI(dx, dy));
                    if (d.containsKey(v) ||
                            !grid.isValidPosition(v.x(), v.y()) ||
                            grid.getPuyo(v.x(), v.y()) == null ||
                            grid.getPuyo(v.x(), v.y()).getFreezeClock().isPresent() ||
                            !grid.getPuyo(v.x(), v.y()).getColor().equals(puyo.getColor())) {
                        continue;
                    }
                    q.add(v);
                    d.put(v, d.get(u) + 1);
                }
            }
        }
        for (var entry : d.entrySet()) {
            Point2DI p = entry.getKey();
            int dist = entry.getValue();
            PuyoInterface puyoToExplode = grid.getPuyo(p.x(), p.y());
            int deathClock = 10 + dist * 2;
            puyoToExplode.setDeathClock(Optional.of(deathClock));
        }
        scoreController.addPoints(d.size());
    }

    /**
     * A method to later set the {@link CannonView}, which is initialized to null
     * in the {@link ControllerStorage}, due to dependencies
     */
    @Override
    public void setCannonView(CannonView cannonView) {
        this.cannonView = cannonView;
    }

}