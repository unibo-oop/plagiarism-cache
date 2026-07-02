package it.unibo.oop.crossline.skin.attributes.idle;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;

import it.unibo.oop.crossline.skin.AnimationEntity;
import it.unibo.oop.crossline.skin.EntitySkin;

public class IdlePlayerImpl implements Idle {


    private AnimationEntity animation;

    public IdlePlayerImpl() {
        super();
        this.animation =  new AnimationEntity("res/player/idle/idle.atlas","idle", EntitySkin.getSpeed(), PlayMode.LOOP);
    }

    @Override
    public AnimationEntity idle() {

       animation.swichFrameByTime();
       return animation;
    }


}
