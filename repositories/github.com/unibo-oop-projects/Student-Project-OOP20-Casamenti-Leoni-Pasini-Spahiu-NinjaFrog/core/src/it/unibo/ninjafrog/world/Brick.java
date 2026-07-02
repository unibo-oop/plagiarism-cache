package it.unibo.ninjafrog.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.screens.PlayScreen;

/**
 * Brick class definition. A Brick is an
 * {@link it.unibo.ninjafrog.world.InteractiveObject InteractiveObject} which is
 * destroyed after you hit it with your head. Defines a
 * {@link it.unibo.ninjafrog.world.Collidable#collide() collide()} method.
 */
public final class Brick extends InteractiveObject implements Collidable {
    private static final int BRICK_SCORE = 25;
    private static final int ANIMATION_TIME = 100;
    private final TiledMapTileSet tileSet;
    private int destroyedTile = InteractiveObject.CELL_NOT_SET;

    /**
     * Public constructor of a Brick object.
     * 
     * @param screen The {@link it.unibo.ninjafrog.screens.PlayScreen PlayScreen}
     *               which contains the game world.
     * @param object The MapObject object which is going to be defined.
     */
    public Brick(final PlayScreen screen, final MapObject object) {
        super(screen, object);
        this.tileSet = this.getMap().getTileSets().getTileSet(InteractiveObject.ASSET_NAME);
        this.getFixture().setUserData(this);
        this.setCategoryFilter(GameConst.BRICK);
    }

    @Override
    public void collide() {
        this.setCategoryFilter(GameConst.DESTROYED);
        if (this.destroyedTile == InteractiveObject.CELL_NOT_SET) {
            this.destroyedTile = this.getCell().getTile().getId() + InteractiveObject.NEXT_TILE;
        }
        this.getCell().setTile(this.tileSet.getTile(this.destroyedTile));
        new Thread(() -> {
            try {
                Thread.sleep(ANIMATION_TIME);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.getCell().setTile(null);
        }).start();
        this.getScreen().addScore(this);
    }

    @Override
    public int getScore() {
        return BRICK_SCORE;
    }

}
