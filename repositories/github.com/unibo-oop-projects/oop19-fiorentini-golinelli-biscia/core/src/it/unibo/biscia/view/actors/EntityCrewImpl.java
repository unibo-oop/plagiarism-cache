package it.unibo.biscia.view.actors;

import it.unibo.biscia.core.Entity;
import it.unibo.biscia.core.EntityType;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Implementation of {@link EntityCrew}. Colors
 * {@link EntityCrewImpl#entitiesColors}
 * 
 */
public class EntityCrewImpl extends Group implements EntityCrew {

    private final Map<Entity, EntityActor> entitesActors;
    private final ShapeRenderer shapeRenderer;
    private float cellWidth;
    private float cellHeight;
    /**
     * Colors of entities by their respective {@link EntityType}.
     */
    private final Map<EntityType, Color> entitiesColors = new EnumMap<>(EntityType.class) {
        private static final long serialVersionUID = 1L;
        {
            this.put(EntityType.SNAKE, Color.WHITE);
            this.put(EntityType.WALL, Color.ORANGE);
            this.put(EntityType.FOOD, Color.GREEN);
        }
    };

    public EntityCrewImpl() {
        this.shapeRenderer = new ShapeRenderer();
        this.entitesActors = new HashMap<>();
    }

    @Override
    public final void draw(final Batch batch, final float parentAlpha) {
        batch.end();
        this.shapeRenderer.begin(ShapeType.Filled);
        this.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        // disegno i vari entityActor
        super.draw(batch, parentAlpha);
        shapeRenderer.end();
        batch.begin();
    }

    @Override
    public final void reset(final float cellWidth, final float cellHeight, final Collection<Entity> entities) {
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.entitesActors.clear();
        Gdx.app.postRunnable(() -> this.clear());
        this.addEntities(entities);
    }

    @Override
    public final void addEntity(final Entity entity) {
        EntityActor entityActor = new EntityActor(entity, cellWidth, cellHeight, entitiesColors.get(entity.getType()),
                shapeRenderer);
        this.entitesActors.put(entity, entityActor);
        Gdx.app.postRunnable(() -> this.addActor(entityActor));
    }

    @Override
    public final void addEntities(final Collection<Entity> entities) {
        entities.stream().forEach(this::addEntity);
    }

    @Override
    public final void removeEntity(final Entity entity) {
        EntityActor eA = this.entitesActors.remove(entity);
        Gdx.app.postRunnable(() -> this.removeActor(eA));
    }

    @Override
    public final void removeEntities(final Collection<Entity> entities) {
        entities.forEach(this::removeEntity);
    }

}
