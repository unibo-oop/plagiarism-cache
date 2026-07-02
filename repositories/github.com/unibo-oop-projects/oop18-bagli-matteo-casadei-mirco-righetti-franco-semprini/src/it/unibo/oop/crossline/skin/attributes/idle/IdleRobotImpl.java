package it.unibo.oop.crossline.skin.attributes.idle;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import it.unibo.oop.crossline.skin.AnimationEntity;
import it.unibo.oop.crossline.skin.EntitySkin;

public class IdleRobotImpl implements Idle {

    private AnimationEntity animation;

    public IdleRobotImpl() {
        super();
        this.animation =  new AnimationEntity("res/robot/idle/idle.atlas","idle", EntitySkin.getSpeed(), PlayMode.LOOP);
    }

    @Override
    public AnimationEntity idle() {
        animation.swichFrameByTime();
        return animation;
    }



}
