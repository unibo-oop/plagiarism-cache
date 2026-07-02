package model.mario;

public class MarioModelImpl implements MarioModel {

    private static final double INITIAL_POS_Y = 200.0;
    private static final double INITIAL_POS_X = 20.0;
    private static final double MARIO_WIDTH = 63.0;
    private static final double MARIO_HEIGHT = 85.0;

    private final String marioRunImgPath;
    private final String marioJumpImgPath;
    private final String marioPowerImgPath;
    private final String marioPowerJumpImgPath;
    private double posY;
    private final double posX;
    private MarioType type;

    public MarioModelImpl() {
        type = MarioType.RUN;
        this.posX = INITIAL_POS_X;
        this.posY = INITIAL_POS_Y;
        this.marioRunImgPath = "images/mario.png";
        this.marioJumpImgPath = "images/mario_jump.png";
        this.marioPowerImgPath = "images/luigi-power-up.png";
        this.marioPowerJumpImgPath = "images/luigi_jump.png";
    }

    @Override
    public void setType(final MarioType type) {
        this.type = type;
    }

    @Override
    public MarioType getType() {
        return this.type;
    }

    @Override
    public String getMarioRunImgPath() {
        return marioRunImgPath;
    }

    @Override
    public String getMarioJumpImgPath() {
        return marioJumpImgPath;
    }

    @Override
    public String getMarioPowerImgPath() {
        return marioPowerImgPath;
    }

    @Override
    public String getMarioPowerJumpImgPath() {
        return marioPowerJumpImgPath;
    }

    @Override
    public double getMarioWidth() {
        return MARIO_WIDTH;
    }

    @Override
    public double getMarioHeight() {
        return MARIO_HEIGHT;
    }

    @Override
    public void setPosY(double posY) {
        this.posY = posY;
    }

    @Override
    public double getMarioPosY() {
        return posY;
    }

    @Override
    public double getMarioPosX() {
        return posX;
    }

    @Override
    public void marioUpdate(final double n) {
        setPosY(this.getMarioPosY() + n);
    }
}
