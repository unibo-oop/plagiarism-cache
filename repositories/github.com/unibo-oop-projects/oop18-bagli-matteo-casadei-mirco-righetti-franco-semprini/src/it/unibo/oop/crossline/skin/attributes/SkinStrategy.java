package it.unibo.oop.crossline.skin.attributes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import it.unibo.oop.crossline.game.actor.Actor;
import it.unibo.oop.crossline.game.actor.player.Player;
import it.unibo.oop.crossline.game.actor.player.PlayerImpl;
import it.unibo.oop.crossline.game.actor.robot.Robot;
import it.unibo.oop.crossline.game.actor.robot.RobotImpl;
import it.unibo.oop.crossline.skin.AnimationEntity;
import it.unibo.oop.crossline.skin.attributes.Shoot.Shoot;
import it.unibo.oop.crossline.skin.attributes.idle.Idle;
import it.unibo.oop.crossline.skin.attributes.jump.Jump;
import it.unibo.oop.crossline.skin.attributes.run.Run;



/**
 *
 *
 *
 */







//        case IS_IDLE:
//
//            break;
//
//        case IS_JUMPING:
//            animation = new AnimationEntity("res/player/squash/squash.atlas",
//                    "squash", 0.2f, PlayMode.LOOP);
//
//            break;
//
//        case IS_RUNING:
//            animation = new AnimationEntity("res/player/run/run.atlas",
//                    "run", 0.2f, PlayMode.LOOP);
//            break;
//
//        case IS_SHOOTING:
//            animation = new AnimationEntity("res/player/action/shoot.atlas",
//                    "shoot", 0.2f, PlayMode.LOOP);
//            break;
//
//        default:
//            break;
//        }



//    public void render (final Player player) {
//
//        pointPlayer = new Vector2(player.getPosition().x,player.getPosition().y);
//
//
//
//        float lunghToPlayer= pointPlayer.len();
//        float scale = lunghToCenter/ lunghToPlayer;
//
//
//        /**
//         * Ogni volta che viene chiamata swichframe cambio frame in base alla velocità impostata tramite il float nel costruttore
//         *
//         */
//        animation.swichFrameByTime();
//
//
//        // Calculates the x and y position to center the screen
//
//
//
//        Object originX = ((player.getPosition().x * scale));//- animation.getDimension().x);
//        Object originY = ((player.getPosition().y * scale));//- animation.getDimension().y);
//
////        Object originX = player.getPosition().x ; //- animation.getDimension().x / 2;
////        Object originY = player.getPosition().y ; //- animation.getDimension().y / 2;
//
//
//
//
//        /**
//         *
//         *Disegno i frame nella posizione indicata dal vettore
//         *
//         */
//
//        // Drawing the frame
//        batch.begin();
//        animation.drawOnSpriteBatch(batch, new Vector2((float) originX, (float) originY));
//        batch.end();
//    }
//
//
//    public void dispose() {
//        // Resource releasing
//        animation.dispose();
//    }



