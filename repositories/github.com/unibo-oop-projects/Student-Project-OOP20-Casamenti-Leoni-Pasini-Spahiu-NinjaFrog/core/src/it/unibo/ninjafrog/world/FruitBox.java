package it.unibo.ninjafrog.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.game.utilities.Pair;
import it.unibo.ninjafrog.screens.PlayScreen;

/**
 * FruitBox class definition. A FruitBox is an
 * {@link it.unibo.ninjafrog.world.InteractiveObject InteractiveObject} which
 * spawns a fruit once you hit it with your head. Defines a
 * {@link it.unibo.ninjafrog.world.Collidable#collide() collide()} method.
 */
public final class FruitBox extends InteractiveObject implements Collidable {
    private static final int FRUITBOX_SCORE = 50;
    private final TiledMapTileSet tileSet;
    private boolean active;
    private int emptyTile = InteractiveObject.CELL_NOT_SET;

    /**
     * Public constructor of a FruitBox object.
     * 
     * @param screen The {@link it.unibo.ninjafrog.screens.PlayScreen PlayScreen}
     *               which contains the game world.
     * @param object The MapObject object which is going to be defined.
     */
    public FruitBox(final PlayScreen screen, final MapObject object) {
        super(screen, object);
        this.tileSet = this.getMap().getTileSets().getTileSet(InteractiveObject.ASSET_NAME);
        this.getFixture().setUserData(this);
        this.setCategoryFilter(GameConst.FRUITBOX);
        this.active = true;
    }

    @Override
    public void collide() {
        if (this.emptyTile == InteractiveObject.CELL_NOT_SET) {
            this.emptyTile = this.getCell().getTile().getId() + InteractiveObject.NEXT_TILE;
        }
        if (this.active) {
            this.getCell().setTile(this.tileSet.getTile(this.emptyTile));
            if (this.getObject().getProperties().containsKey("melon")) {
                this.getScreen().spawnMelon(new Pair<Float, Float>(this.getBodyXPos(),
                        this.getBodyYPos() + this.scale(InteractiveObject.WORLD_OBJ_DIM)));
            } else if (this.getObject().getProperties().containsKey("cherries")) {
                this.getScreen().spawnCherry(new Pair<Float, Float>(this.getBodyXPos(),
                        this.getBodyYPos() + this.scale(InteractiveObject.WORLD_OBJ_DIM)));
            } else {
                this.getScreen().spawnOrange(new Pair<Float, Float>(this.getBodyXPos(),
                        this.getBodyYPos() + this.scale(InteractiveObject.WORLD_OBJ_DIM)));
            }
            this.getScreen().addScore(this);
            this.active = false;
        }
    }

    @Override
    public int getScore() {
        return FRUITBOX_SCORE;
    }

}
