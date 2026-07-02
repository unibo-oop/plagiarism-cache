package it.unibo.ninjafrog.enemies;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;

import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.screens.PlayScreen;

public class EnemyControllerImpl implements EnemyController {
    private static final int RINO_LAYER = 7;
    private static final int TURTLE_LAYER = 8;
    private Map<DynamicEnemyModel, EnemyView> rinos;
    private Map<StaticEnemyModel, EnemyView> turtles;
    private final PlayScreen screen;
    private boolean runningLeft;
    private boolean destroyed;
    private float stateTime;

    /**
     * public constructor of the EnemyController.
     * 
     * @param screen the playscreen
     */

    public EnemyControllerImpl(final PlayScreen screen) {
        if (screen == null) {
            throw new IllegalStateException("Screen can't be null.");
        }
        this.screen = screen;
        this.spawnEnemies();
    }

    private void spawnEnemies() {
        final TiledMap map = this.screen.getMap();
        rinos = new HashMap<>();
        for (final MapObject object : map.getLayers().get(RINO_LAYER).getObjects()
                .getByType(RectangleMapObject.class)) {
            final Rectangle rect = ((RectangleMapObject) object).getRectangle();
            rinos.put(new RinoModelImpl(screen, this),
                    new RinoViewImpl(screen, rect.getX() / GameConst.PPM, rect.getY() / GameConst.PPM, this));
        }
        rinos.keySet().forEach(m -> m.defineEnemy());
        turtles = new HashMap<>();
        for (final MapObject object : map.getLayers().get(TURTLE_LAYER).getObjects()
                .getByType(RectangleMapObject.class)) {
            final Rectangle rect = ((RectangleMapObject) object).getRectangle();
            turtles.put(new TurtleModelImpl(screen, this),
                    new TurtleViewImpl(screen, rect.getX() / GameConst.PPM, rect.getY() / GameConst.PPM, this));
        }
        turtles.keySet().forEach(m -> m.defineEnemy());
    }

    @Override
    public final void update(final float dt) {
        rinos.forEach((m, v) -> m.update(dt));
        turtles.forEach((m, v) -> m.update(dt));
    }

    @Override
    public final void draw(final SpriteBatch batch) {
        rinos.forEach((m, v) -> v.draw(batch));
        turtles.forEach((m, v) -> v.draw(batch));
    }

    @Override
    public final void collide(final DynamicEnemyModel rinoModel) {
        checkRinoModel(rinoModel);
        if (rinos.get(rinoModel).isKillable()) {
            rinoModel.collide();
        } else {
            screen.removeLife();
        }
    }

    @Override
    public final boolean isSetToDestroy(final DynamicEnemyModel rinoModel) {
        checkRinoModel(rinoModel);
        return rinoModel.isSetToDestroy();

    }

    @Override
    public final void reverseVelocity(final DynamicEnemyModel rinoModel) {
        checkRinoModel(rinoModel);
        rinoModel.reverseVelocity(true, false);
    }

    @Override
    public final float getX(final DynamicEnemyModel rinoModel) {
        checkRinoModel(rinoModel);
        return this.rinos.get(rinoModel).getX();
    }

    @Override
    public final float getY(final DynamicEnemyModel rinoModel) {
        checkRinoModel(rinoModel);
        return this.rinos.get(rinoModel).getY();
    }

    @Override
    public final boolean isDestroyed(final EnemyView enemyView) {
        if (this.rinos.values().contains(enemyView)) {
            this.rinos.forEach((m, v) -> {
                if (v.equals(enemyView)) {
                    this.destroyed = m.isDestroyed();
                }
            });
        } else if (this.turtles.values().contains(enemyView)) {
            this.turtles.forEach((m, v) -> {
                if (v.equals(enemyView)) {
                    this.destroyed = m.isDestroyed();
                }
            });
        } else {
            throw new IllegalStateException();
        }
        return this.destroyed;
    }

    @Override
    public final float getStateTime(final EnemyView enemyView) {
        if (this.rinos.values().contains(enemyView)) {
            this.rinos.forEach((m, v) -> {
                if (v.equals(enemyView)) {
                    this.stateTime = m.getStateTime();
                }
            });
        } else if (this.turtles.values().contains(enemyView)) {
            this.turtles.forEach((m, v) -> {
                if (v.equals(enemyView)) {
                    this.stateTime = m.getStateTime();
                }
            });
        } else {
            throw new IllegalStateException();
        }
        return this.stateTime;
    }

    @Override
    public final void setDeathRegion(final DynamicEnemyModel rinoModel) {
        checkRinoModel(rinoModel);
        this.rinos.get(rinoModel).setDeathRegion();
    }

    @Override
    public final void updateView(final DynamicEnemyModel rinoModel, final Body body, final float dt) {
        checkRinoModel(rinoModel);
        this.rinos.get(rinoModel).update(body, dt);
    }

    @Override
    public final boolean isRunningLeft(final EnemyView rinoView) {
        this.rinos.forEach((m, v) -> {
            if (v.equals(rinoView)) {
                this.runningLeft = m.isRunningLeft();
            }
        });
        return this.runningLeft;
    }

    @Override
    public final void setRunningLeft(final EnemyView rinoView, final boolean b) {
        this.rinos.forEach((m, v) -> {
            if (v.equals(rinoView)) {
                m.setRunningLeft(b);
            }
        });
    }

    private void checkRinoModel(final DynamicEnemyModel rinoModel) {
        if (!this.rinos.keySet().contains(rinoModel)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public final float getX(final StaticEnemyModel turtleModel) {
        checkTurtleModel(turtleModel);
        return this.turtles.get(turtleModel).getX();
    }

    @Override
    public final float getY(final StaticEnemyModel turtleModel) {
        checkTurtleModel(turtleModel);
        return this.turtles.get(turtleModel).getY();
    }

    @Override
    public final void setDeathRegion(final StaticEnemyModel turtleModel) {
        checkTurtleModel(turtleModel);
        this.turtles.get(turtleModel).setDeathRegion();
    }

    @Override
    public final void updateView(final StaticEnemyModel turtleModel, final Body body, final float dt) {
        checkTurtleModel(turtleModel);
        this.turtles.get(turtleModel).update(body, dt);
    }

    @Override
    public final void collide(final StaticEnemyModel turtleModel, final Short bit) {
        checkTurtleModel(turtleModel);
        if (this.turtles.get(turtleModel).isKillable()) {
            this.screen.removeLife();
        } else if (bit == GameConst.TURTLE) {
            this.screen.removeLife();
            turtleModel.collide();
        } else {
            turtleModel.collide();
        }
    }

    @Override
    public final boolean isSetToDestroy(final StaticEnemyModel turtleModel) {
        checkTurtleModel(turtleModel);
        return turtleModel.isSetToDestroy();
    }

    private void checkTurtleModel(final StaticEnemyModel turtleModel) {
        if (!this.turtles.keySet().contains(turtleModel)) {
            throw new IllegalStateException();
        }
    }

}
