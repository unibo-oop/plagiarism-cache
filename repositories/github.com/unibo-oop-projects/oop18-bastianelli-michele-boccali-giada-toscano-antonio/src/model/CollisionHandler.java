package model;

import java.util.HashMap;
import java.util.Map;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.WorldManifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

import common.EventBusConnection;
import enumerators.EntityType;
import model.components.CollisionImpl;
import model.entities.EntityModel;
import model.entities.PlayerModel;

/**
 * Handle the collisions inside the game.
 */
public class CollisionHandler extends EventBusConnection implements ContactListener {

    private boolean postSolveNeeded;
    private GameModel gameModel;
    
    public CollisionHandler(GameModel gameModel) {
        super();
        this.gameModel = gameModel;
    }

    // use to better calculate the collision side
    //private static final float DELTA_COLLISION = 10.0f;

    /**
     * Represents the collision sides based on the player position.
     */
    public enum CollisionSide {
        BOTTOM, OTHERS;
    }

    @Override
    public final void beginContact(final Contact contact) {
    }

    @Override
    public final void endContact(final Contact arg0) {
    }

    @Override
    public final void postSolve(final Contact contact, final ContactImpulse arg1) {
        if (postSolveNeeded) {
            applyCollisionEffects(contact);
        }
    }

    @Override
    public final void preSolve(final Contact contact, final Manifold manfold) {
        postSolveNeeded = true;
        final Map<EntityType, EntityModel> types = extractTypes(contact);

        if (types.containsKey(EntityType.PLAYER)) {
            final PlayerModel player = (PlayerModel) types.get(EntityType.PLAYER);
            if (types.containsKey(EntityType.PLATFORM)) {
                playerAndPlatform(contact, checkCollisionSide(contact, player));
            } else if (types.containsKey(EntityType.COIN)) {
                playerAndCoin(contact);
            }
        }
        types.clear();
    }

    /**
     * Handle the collision between the player and a platform.
     */
    private void playerAndPlatform(final Contact contact, final CollisionSide side) {
        if (side.equals(CollisionSide.BOTTOM)) {
            contact.setEnabled(true);
        } else {
            contact.setEnabled(false);
        }
        //postSolveNeeded = true;
    }

    /**
     * Handle the collision between the player and a coin.
     */
    private void playerAndCoin(final Contact contact) {
        contact.setEnabled(false);
        applyCollisionEffects(contact);
        postSolveNeeded = false;
    }

    private void applyCollisionEffects(final Contact contact) {
        final EntityModel entityA = getEntityModel(contact.getFixtureA().getBody());
        final EntityModel entityB = getEntityModel(contact.getFixtureB().getBody());
        final CollisionSide collisionSide = checkCollisionSide(contact, recognizePlayer(entityA, entityB));
        if (entityA.contain(CollisionImpl.class)) {
            entityA.getComponent(CollisionImpl.class).applyCollisionEffect(entityB, collisionSide);
        }
        if (entityB.contain(CollisionImpl.class)) {
            entityB.getComponent(CollisionImpl.class).applyCollisionEffect(entityA, collisionSide);
        }
    }

    private Map<EntityType, EntityModel> extractTypes(final Contact contact) {
        final EntityModel entityA = getEntityModel(contact.getFixtureA().getBody());
        final EntityModel entityB = getEntityModel(contact.getFixtureB().getBody());

        final Map<EntityType, EntityModel> types = new HashMap<>();
        types.put(entityA.getEntityType(), entityA);
        types.put(entityB.getEntityType(), entityB);
        return types;
    }

    private CollisionSide checkCollisionSide(final Contact contact, final EntityModel entity) {
        final WorldManifold worldManifold = new WorldManifold();
        contact.getWorldManifold(worldManifold);
        final boolean collidingBottom = isCollidingBottom(worldManifold.points[0].y, entity);
        if (collidingBottom) {
            return CollisionSide.BOTTOM;
        } else {
            return CollisionSide.OTHERS;
        }
    }

    /**
     * @param collisionY
     * @param entity
     * @return true if the entity is colliding on bottom side, false otherwise
     */
    private boolean isCollidingBottom(final float collisionY, final EntityModel entity) {
//        final float bottomSide = entity.getModel().getBottomSide();
//        return (collisionY <= bottomSide && collisionY >= (bottomSide - DELTA_COLLISION));
        return (collisionY <= entity.getBottomSide() && collisionY >= entity.getCenter().y);
    }

    private EntityModel getEntityModel(final Body body) {
        return gameModel.getEntityFromBody(body).getModel();
    }

    private EntityModel recognizePlayer(final EntityModel entityA, final EntityModel entityB) {
        if (entityA.getEntityType().equals(EntityType.PLAYER)) {
            return entityA;
        } else {
            return entityB;
        }
    }

}
