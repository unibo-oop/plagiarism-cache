package it.unibo.oop.supermario.gamemanager;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import it.unibo.oop.supermario.character.Mario;
import it.unibo.oop.supermario.enemies.Enemy;
import it.unibo.oop.supermario.powerup.FireballModel;
import it.unibo.oop.supermario.powerup.Item;
import it.unibo.oop.supermario.powerup.Mushroom;
import it.unibo.oop.supermario.world.CoinBlock;
import it.unibo.oop.supermario.world.FlagTube;

/**
 * Detect all collision in the game.
 *
 */
public class CollisionFinder implements ContactListener {
    @Override
    public final void beginContact(final Contact contact) {
        // Gdx.app.log("BeginContact", "");
        final Fixture fixA = contact.getFixtureA();
        final Fixture fixB = contact.getFixtureB();

        final int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef) {
        case GameManager.ENEMY_BIT | GameManager.ENEMY_BIT:
            ((Enemy) fixA.getUserData()).hitByEnemy((Enemy) fixB.getUserData());
            ((Enemy) fixB.getUserData()).hitByEnemy((Enemy) fixA.getUserData());
            break;
        case GameManager.POWER_UP | GameManager.ENEMY_BIT:
        case GameManager.POWER_UP | GameManager.ENEMY_HEAD_BIT:
        case GameManager.POWER_UP | GameManager.GROUND_BIT:
            if (fixA.getFilterData().categoryBits == GameManager.POWER_UP) {
                ((FireballModel) fixA.getUserData()).onCollide((Enemy) fixB.getUserData());
            } else {
                ((FireballModel) fixB.getUserData()).onCollide((Enemy) fixA.getUserData());
            }
            break;
        case GameManager.ENEMY_BIT | GameManager.PIPE_BIT:
        case GameManager.ENEMY_HEAD_BIT | GameManager.GROUND_BIT:
            if (fixA.getFilterData().categoryBits == GameManager.ENEMY_BIT
                    || fixA.getFilterData().categoryBits == GameManager.ENEMY_HEAD_BIT) {

                ((Enemy) fixA.getUserData()).reverseVelocity(true, false);
            } else {
                ((Enemy) fixB.getUserData()).reverseVelocity(true, false);
            }
            break;
        case GameManager.ENEMY_BIT | GameManager.MARIO_BIT:
            if (fixA.getFilterData().categoryBits == GameManager.MARIO_BIT) {
                ((Mario) fixA.getUserData()).hit((Enemy) fixB.getUserData());
            } else {
                ((Mario) fixB.getUserData()).hit((Enemy) fixA.getUserData());
            }
            break;
        case GameManager.ENEMY_HEAD_BIT | GameManager.FEET_BIT:
            if (fixA.getFilterData().categoryBits == GameManager.ENEMY_HEAD_BIT) {
                ((Enemy) fixA.getUserData()).hitOnHead((Mario) fixB.getUserData());
            } else {
                ((Enemy) fixB.getUserData()).hitOnHead((Mario) fixA.getUserData());
            }
            break;
        case GameManager.ITEM_BIT | GameManager.PIPE_BIT:
            if (fixA.getFilterData().categoryBits == GameManager.ITEM_BIT) {
                ((Mushroom) fixA.getUserData()).reverseVelocity(true, false);
            } else {
                ((Mushroom) fixB.getUserData()).reverseVelocity(true, false);
            }
            break;
        case GameManager.MARIO_HEAD_BIT | GameManager.COIN_BIT:
            if (fixA.getFilterData().categoryBits == GameManager.MARIO_HEAD_BIT) {
                ((CoinBlock) fixB.getUserData()).onCollide((Mario) fixA.getUserData());
            } else {
                ((CoinBlock) fixA.getUserData()).onCollide((Mario) fixB.getUserData());
            }
            break;
        case GameManager.ITEM_BIT | GameManager.MARIO_BIT:
            if (fixA.getFilterData().categoryBits == GameManager.MARIO_BIT) {
                ((Item) fixB.getUserData()).onCollide((Mario) fixA.getUserData());
            } else {
                ((Item) fixA.getUserData()).onCollide((Mario) fixB.getUserData());
            }
            break;
        case GameManager.MARIO_HEAD_BIT | GameManager.BRICK_BIT:
            if (fixA.getFilterData().categoryBits == GameManager.MARIO_HEAD_BIT) {
                ((Item) fixB.getUserData()).onCollide((Mario) fixA.getUserData());
            } else {
                ((Item) fixA.getUserData()).onCollide((Mario) fixB.getUserData());
            }
            break;
        case GameManager.FLAG_BIT | GameManager.MARIO_BIT:
        case GameManager.FLAG_BIT | GameManager.FEET_BIT:
            if (fixA.getFilterData().categoryBits == GameManager.FLAG_BIT) {
                ((FlagTube) fixA.getUserData()).onCollide((Mario) fixB.getUserData());
            } else {
                ((FlagTube) fixB.getUserData()).onCollide((Mario) fixA.getUserData());
            }
            break;
            
        default:
            break;
        }
    }

    @Override
    public final void endContact(final Contact contact) {
    }

    @Override
    public final void preSolve(final Contact contact, final Manifold oldManifold) {
    }

    @Override
    public final void postSolve(final Contact contact, final ContactImpulse impulse) {
    }

}
