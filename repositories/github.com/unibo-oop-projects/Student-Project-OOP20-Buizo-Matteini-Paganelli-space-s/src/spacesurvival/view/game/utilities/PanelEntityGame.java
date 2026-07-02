package spacesurvival.view.game.utilities;

import spacesurvival.model.World;
import spacesurvival.model.collision.bounding.CircleBoundingBox;
import spacesurvival.model.common.Pair;
import spacesurvival.model.gameobject.GameObject;
import spacesurvival.model.gameobject.fireable.Boss;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.model.gameobject.takeable.TakeableGameObject;
import spacesurvival.utilities.ThreadUtils;
import spacesurvival.view.game.utilities.commandlife.CallerLife;
import spacesurvival.view.game.utilities.logiccolor.LogicColorShip;
import spacesurvival.view.utilities.GraphicsLayoutUtils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.swing.JPanel;

/**
 * Implements a panel to graph game objects, through a support structure.
 */
public class PanelEntityGame extends JPanel {

    private static final long serialVersionUID = -6158413043296871948L;

    /**
     * Anchor life bar.
     */
    public static final int ANCHOR_X_LIFE_BAR = 0;

    /**
     * Height life bar ledge.
     */
    public static final int HEIGHT_LIFE_BAR = 6;

    /**
     * Height life.
     */
    public static final int HEIGHT_LIFE = 5;

    /**
     * Difference height life bar e life.
     */
    public static final int DIFFERENCE_HEIGHT_LIFE_BAR = Math.abs(HEIGHT_LIFE_BAR - HEIGHT_LIFE);

    private volatile Map<GameObject, Pair<Image, Image>> gameObjects;
    private final CallerLife callerLife;
    private Optional<World> world;

    /**
     * Initialize and create all graphics components.
     */
    public PanelEntityGame() {
        super(); 
        super.setOpaque(true);
        super.setBackground(GraphicsLayoutUtils.BACKGROUND_GAME_COLOR);
        this.gameObjects = new HashMap<>();
        this.world = Optional.empty();
        this.callerLife = new CallerLife(new LogicColorShip());
        new Thread(PanelEntityGame.this::runUpdateGameObjects).start();
    }

    /**
     * Set world for panel.
     * @param world
     */
    public void setWorld(final World world) {
        this.world = Optional.of(world);
    }

    /**
     * Repaint all game component.
     */
    @Override
    public final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        try {
            this.world.get().getAllBullets().forEach(bullet -> {
                g2d.setTransform(bullet.getTransform());
                g2d.drawImage(bullet.getImgBody(), 0, 0, null);
            });

            this.gameObjects.entrySet().forEach(entity -> {
                g2d.setTransform(getCorrectAffineTransformFromBoundingBox(entity.getKey())); 
                g2d.drawImage(entity.getValue().getX(), 0, 0, null);
                g2d.drawImage(entity.getValue().getY(), 0, 0, null);
                this.assignLifeBar(entity.getKey(), g2d);
            });
        } catch (ConcurrentModificationException e) { }
    }

    private void drawLifeBar(final Graphics2D g2d, final GameObject gameObject) {
        this.drawBar(g2d, gameObject);
        this.drawLife(g2d, gameObject);
    }

    private void drawBar(final Graphics2D g2d, final GameObject gameObject) {
        g2d.setColor(Color.WHITE);
        g2d.drawRect(ANCHOR_X_LIFE_BAR, (int) gameObject.getSize().getHeight(), (int) gameObject.getSize().getWidth(), HEIGHT_LIFE_BAR);
    }

    private void drawLife(final Graphics2D g2d, final GameObject gameObject) {
        this.callerLife.executeDrawLife((MainObject) gameObject, g2d);
    }

    private boolean isTargetLife(final GameObject obj) {
        return !(obj instanceof SpaceShipSingleton || obj instanceof TakeableGameObject || obj instanceof Boss);
    }

    private void assignLifeBar(final GameObject gameObject, final Graphics2D g2d) {
        if (this.isTargetLife(gameObject)) {
            this.drawLifeBar(g2d, gameObject);
        }
    }

    private AffineTransform getCorrectAffineTransformFromBoundingBox(final GameObject gameObject) {
        if (gameObject.getBoundingBox() instanceof CircleBoundingBox) {
            final CircleBoundingBox cbb = (CircleBoundingBox) gameObject.getBoundingBox();
            final AffineTransform transform = new AffineTransform();
            transform.setTransform(gameObject.getTransform());
            transform.translate(-cbb.getRadius(), -cbb.getRadius());
            return transform;
        } else {
            return gameObject.getTransform();
        }
    }

    private void updateGameObjects() {
        this.putObjectFromWorld();
        this.deletGameObject();
    }

    private void putObjectFromWorld() {
        this.world.get().getAllObjectsExceptBullets().forEach(obj -> {
            final Pair<Image, Image> pair = new Pair<>(obj.getImgBody(), obj.getImgEffect());
            this.gameObjects.put(obj, pair);
        });
    }

    private void deletGameObject() {
        final Set<GameObject> objDelet = new HashSet<>();

        this.gameObjects.forEach((key, value) -> {
            if (!this.world.get().getAllObjectsExceptBullets().contains(key)) {
                objDelet.add(key);
            }
        });

        objDelet.forEach(this.gameObjects::remove);
    }

    /**
     * Method for filling the support structure.
     */
    public final void runUpdateGameObjects() {
        while (true) {
            if (this.world.isPresent()) {
               this.updateGameObjects();
            }

            ThreadUtils.sleep(ThreadUtils.MEDIUM_SLEEP);
        }
    }

}
