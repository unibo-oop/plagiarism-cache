package controller.physics;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import model.entities.GameEntityTypes;
import model.entities.components.items.BluePotionEffect;
import model.entities.components.items.RedPotionEffect;
import view.level.generator.GameEngineUtil;

/**
 * Sets every collision and physic phenomenon.
 */
public final class PhysicsUtil {

    private PhysicsUtil() { }

    /**
     * Sets the collision handlers for game entities.
     */
    public static void configureCollisions() {
        FXGL.getApp().getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameEntityTypes.PLAYER, GameEntityTypes.RED_POTION) {
            @Override
            protected void onCollisionBegin(final Entity player, final Entity item) {
                item.getComponent(RedPotionEffect.class).whenTriggered();
            }
        });

        FXGL.getApp().getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameEntityTypes.PLAYER, GameEntityTypes.BLUE_POTION) {
            @Override
            protected void onCollisionBegin(final Entity player, final Entity item) {
                item.getComponent(BluePotionEffect.class).whenTriggered();
            }
        });

        FXGL.getApp().getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameEntityTypes.PLAYER, GameEntityTypes.DOOR) {
                @Override
                protected void onCollisionBegin(final Entity player, final Entity door) {
                        GameEngineUtil.nextLevel();
                }
        });

        FXGL.getApp().getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameEntityTypes.PLAYER, GameEntityTypes.STAIRS_DOWN) {
            @Override
            protected void onCollisionBegin(final Entity player, final Entity stair) {
                    GameEngineUtil.nextLevel();
            }
        });

        FXGL.getApp().getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameEntityTypes.PLAYER, GameEntityTypes.ITEM) {
            @Override
            protected void onCollisionBegin(final Entity player, final Entity item) {
                    item.removeFromWorld();
            }
        });
    }
}
