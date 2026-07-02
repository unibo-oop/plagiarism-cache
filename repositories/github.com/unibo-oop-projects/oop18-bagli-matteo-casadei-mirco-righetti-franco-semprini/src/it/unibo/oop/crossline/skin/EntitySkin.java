package it.unibo.oop.crossline.skin;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.javafx.embed.swing.Disposer;

import it.unibo.oop.crossline.game.actor.Actor;
import it.unibo.oop.crossline.game.actor.player.Player;
import it.unibo.oop.crossline.game.actor.robot.Robot;
import it.unibo.oop.crossline.skin.attributes.PlayerState;
import it.unibo.oop.crossline.skin.attributes.Shoot.Shoot;
import it.unibo.oop.crossline.skin.attributes.Shoot.ShootPlayerImpl;
import it.unibo.oop.crossline.skin.attributes.Shoot.ShootRobotImpl;
import it.unibo.oop.crossline.skin.attributes.idle.Idle;
import it.unibo.oop.crossline.skin.attributes.idle.IdlePlayerImpl;
import it.unibo.oop.crossline.skin.attributes.idle.IdleRobotImpl;
import it.unibo.oop.crossline.skin.attributes.jump.Jump;
import it.unibo.oop.crossline.skin.attributes.jump.JumpPlayerImpl;
import it.unibo.oop.crossline.skin.attributes.jump.JumpRobotImpl;
import it.unibo.oop.crossline.skin.attributes.run.Run;
import it.unibo.oop.crossline.skin.attributes.run.RunPlayerImpl;
import it.unibo.oop.crossline.skin.attributes.run.RunRobotImpl;

/**
 *
 * @param <X>
 */
public class EntitySkin<X> {

    private SkinStrategy<Actor> entityStrategy;
    private Object type;
    private PlayerState state;
    private AnimationEntity animation;
    private static final float SPEED = 0.3f;


    /**
     * Initialize Skin.
     * @param entity type of object to represent.
     */
    public EntitySkin(final X entity) {
        super();

        this.state = PlayerState.IS_IDLE;
        this.type = entity;

      if (this.type instanceof Player) {

          this.entityStrategy = new SkinStrategy<>(new IdlePlayerImpl(), new JumpPlayerImpl(), new RunPlayerImpl(), new ShootPlayerImpl());

      } else if (this.type instanceof Robot) {


          this.entityStrategy = new SkinStrategy<>(new IdleRobotImpl(), new JumpRobotImpl(), new RunRobotImpl(), new ShootRobotImpl());

      } else {
          System.err.println("Is not possible to draw this entity" + entity.toString());
      }
    }

    /**
     *Set the image to draw before draw.
     */
    public final void setRenderAnimationtoDraw() {

        switch (this.state) {
        case IS_IDLE:

           this.entityStrategy.idle();
            break;

        case IS_JUMPING:

            this.entityStrategy.jump();
            break;

        case IS_RUNING:

            this.entityStrategy.run();
            break;

        case IS_SHOOTING:

            this.entityStrategy.shoot();
            break;

        default:
            break;


    }

    }

    /**
     *Draw the already set animation.
     * @param batch where draw.
     * @param pos place where draw.
     */
    public final void drawAnimation(final SpriteBatch batch, final Vector2 pos) {

        this.animation.drawOnSpriteBatch(batch, pos);

    }

    /**
     *Change state of animation to draw.
     * @param a state of the Entity
     */
    public final void setState(final PlayerState a) {
        this.state = a;
    }

    /**
     *
     * @return The settled value of speed animation
     */
    public static float getSpeed() {
        return SPEED;
    }

    public final void dispose() {
        animation.dispose();
    }

    private final class SkinStrategy<Y extends Actor> extends ApplicationAdapter {


        /**
         * Interface for every type of animation.
         *
         */
        private Idle idle;
        private Jump jump;
        private Run run;
        private Shoot shoot;

        /**
         * Skin would now who is, player or robot.
         *
         * @param state
         */
        private SkinStrategy(final Idle idle, final Jump jump, final Run run, final Shoot shoot) {
            super();
            this.idle = idle;
            this.jump = jump;
            this.run = run;
            this.shoot = shoot;
        }

        /**
         * this method will call every render time to set animationEntity with correct frame
         * that will draw in the view.
         */
        private void idle() {
            animation = this.idle.idle();
        }

        private void jump() {
            animation = this.jump.jump();
        }

        private void run() {
            animation = this.run.run();
        }

        private void shoot() {
            animation = this.shoot.shoot();
        }

}

}
