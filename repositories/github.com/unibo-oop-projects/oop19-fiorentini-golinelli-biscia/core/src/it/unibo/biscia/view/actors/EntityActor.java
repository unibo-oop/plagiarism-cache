package it.unibo.biscia.view.actors;

import it.unibo.biscia.core.Cell;
import it.unibo.biscia.core.Entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Takes care of displaying a single {@link Entity} with all its {@link Cell}
 * and with its appropriate Color.
 * 
 * @see EntityCrew
 *
 */
public class EntityActor extends Actor {
    private final Entity entity;
    private final Color color;
    private final float width;
    private final float height;
    private final ShapeRenderer shapeRenderer;

    public EntityActor(final Entity entity, final float width, final float height, final Color color,
            final ShapeRenderer shapeRenderer) {
        this.entity = entity;
        this.color = color;
        this.width = width;
        this.height = height;
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public final void draw(final Batch batch, final float parentAlpha) {
        this.shapeRenderer.setColor(this.color);
        Vector2 stageCoords = this.getParent().localToStageCoordinates(new Vector2(this.getX(), this.getY()));
        // TODO: questo potrebbe migliorare e usi una copyOnWriteArrayList lato core obv
        for (Cell c : new ArrayList<>(entity.getCells())) {
            this.shapeRenderer.rect(c.getCol() * width + stageCoords.x, c.getRow() * height + stageCoords.y, width,
                    height);
        }
    }

}
