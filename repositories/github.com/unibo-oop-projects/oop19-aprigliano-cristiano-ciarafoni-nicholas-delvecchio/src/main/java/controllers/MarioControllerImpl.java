package controllers;

import model.mario.MarioModel;
import model.mario.MarioModelImpl;
import model.mario.MarioType;
import view.MarioView;
import view.MarioViewImpl;

public class MarioControllerImpl implements MarioController {

    private final MarioView marioView;
    private final MarioModel marioModel;

    /**
     * This is the constructor method that initializes all fields of the class and start the view.
     */
    public MarioControllerImpl() {
        this.marioModel = new MarioModelImpl();
        this.marioView = new MarioViewImpl();
        this.startMarioView();
    }

    @Override
    public void startMarioView() {
        marioView.setImg(marioModel.getMarioRunImgPath());
        marioView.setPosition(marioModel.getMarioPosX(), marioModel.getMarioPosY());
        marioView.setHeight(marioModel.getMarioHeight());
        marioView.setWidth(marioModel.getMarioWidth());
    }

    @Override
    public MarioView getMarioView() {
        return this.marioView;
    }

    @Override
    public MarioModel getMarioModel() {
        return this.marioModel;
    }

    @Override
    public void marioMovement(final double n) {
        this.marioModel.marioUpdate(n);
        this.marioView.updatePosition(marioModel.getMarioPosY());
    }

    @Override
    public void changeMarioSkin(final MarioType type) {
        if (type == MarioType.JUMP) {
            marioView.setImg(marioModel.getMarioJumpImgPath());
        } else if (type == MarioType.RUN) {
            marioView.setImg(marioModel.getMarioRunImgPath());
        } else if (type == MarioType.POWER_JUMP) {
            marioView.setImg(marioModel.getMarioPowerJumpImgPath());
        } else {
            marioView.setImg(marioModel.getMarioPowerImgPath());
        }
    }
}
