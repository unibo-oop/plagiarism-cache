package it.unibo.oop.crossline.skin.attributes.run;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import it.unibo.oop.crossline.skin.AnimationEntity;

public class RunPlayerImpl implements Run {

private AnimationEntity animation;

    @Override
    public AnimationEntity run() {
        
        return animation = new AnimationEntity("res/player/run/run.atlas","run", 0.2f, PlayMode.LOOP);

    }
}
