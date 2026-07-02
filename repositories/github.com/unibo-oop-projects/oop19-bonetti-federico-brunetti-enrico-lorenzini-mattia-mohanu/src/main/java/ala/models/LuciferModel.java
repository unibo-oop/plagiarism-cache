package ala.models;

/**
 * LuciferModel class.
 * 
 */
public class LuciferModel extends LuciferBasicModel implements LuciferAbilitiesModel {
    //Attributes:
    private static final double MAX_JUMP_HEIGHT = 400;
    private double currentJumpHeight = 0;
    private static final double DELTA_JUMP_MAX_VELOCITY = 50; //Lucifer sprite move up of this value every frame.
    private static final double DELTA_JUMP_MED_VELOCITY = 20;
    private static final double DELTA_JUMP_MIN_VELOCITY = 5;
    private static final double LUCIFER_X_SPEED = 5;
    private boolean luciferOnPlatform = false;

    private static final int FIRST_DIVISION = 100;
    private static final int SECOND_DIVISION = 300;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * 
     */
    public LuciferModel(final double x, final double y) {
        super(x, y);
    }

    //Getters&Setters:
    public static double getMaxJumpHeight() {
        return MAX_JUMP_HEIGHT;
    }

    public final double getCurrentJumpHeight() {
        return currentJumpHeight;
    }

    public static double getDeltaJumpMaxVelocity() {
        return DELTA_JUMP_MAX_VELOCITY;
    }

    public static double getDeltaJumpMedVelocity() {
        return DELTA_JUMP_MED_VELOCITY;
    }

    public final void setCurrentJumpHeight(final double currentJumpHeight) {
        this.currentJumpHeight = currentJumpHeight;
    }

    public final boolean isLuciferOnPlatform() {
        return luciferOnPlatform;
    }

    public final void setLuciferOnPlatform(final boolean luciferOnPlatform) {
        this.luciferOnPlatform = luciferOnPlatform;
    }

    public static double getLuciferXSpeed() {
        return LUCIFER_X_SPEED;
    }

    //Methods:
    /**
     * manage Lucifer jumps on platforms.
     * 
     */
   @Override
    public final void jumpManager() {
        if (this.getDy() == 0 && !this.luciferOnPlatform) {
            this.setDy(LUCIFER_X_SPEED);
        } else if (this.getDy() < 0) {
            if (currentJumpHeight < FIRST_DIVISION) {
                currentJumpHeight += DELTA_JUMP_MAX_VELOCITY;
            } else if (currentJumpHeight < SECOND_DIVISION && currentJumpHeight >= FIRST_DIVISION) {
                currentJumpHeight += DELTA_JUMP_MED_VELOCITY;
                this.setDy(-DELTA_JUMP_MED_VELOCITY);
            } else if (currentJumpHeight < MAX_JUMP_HEIGHT && currentJumpHeight >= SECOND_DIVISION) {
                currentJumpHeight += DELTA_JUMP_MIN_VELOCITY;
                this.setDy(-DELTA_JUMP_MIN_VELOCITY);
            } else if (currentJumpHeight == MAX_JUMP_HEIGHT) {
                    this.setDy(DELTA_JUMP_MIN_VELOCITY);
                    currentJumpHeight -= DELTA_JUMP_MIN_VELOCITY; //Because the first cycle go down
                }
        } else if (this.getDy() > 0) {
            if (currentJumpHeight <= MAX_JUMP_HEIGHT && currentJumpHeight > SECOND_DIVISION) {
                currentJumpHeight -= DELTA_JUMP_MIN_VELOCITY;
            } else if (currentJumpHeight <= SECOND_DIVISION && currentJumpHeight > FIRST_DIVISION) {
                currentJumpHeight -= DELTA_JUMP_MED_VELOCITY;
                this.setDy(DELTA_JUMP_MED_VELOCITY);
            } else if (currentJumpHeight > 0 && currentJumpHeight <= FIRST_DIVISION) {
                currentJumpHeight -= DELTA_JUMP_MAX_VELOCITY;
                this.setDy(DELTA_JUMP_MAX_VELOCITY);
            } else if (!this.luciferOnPlatform) { //MAX_JUMP_HEIGHT should be a multiple of DELTA_JUMP is very important for this.
                this.currentJumpHeight = MAX_JUMP_HEIGHT;
            }
        }
    }
}
