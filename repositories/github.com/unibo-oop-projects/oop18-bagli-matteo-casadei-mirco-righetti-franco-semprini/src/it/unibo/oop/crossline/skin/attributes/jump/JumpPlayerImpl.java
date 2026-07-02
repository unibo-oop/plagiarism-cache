package it.unibo.oop.crossline.skin.attributes.jump;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import it.unibo.oop.crossline.skin.AnimationEntity;

public class JumpPlayerImpl implements Jump {

    private AnimationEntity animation;

    @Override
    public AnimationEntity jump() {

        animation = new AnimationEntity("res/player/squash/squash.atlas","squash", 0.2f, PlayMode.LOOP);
        return animation;
    }

}
