package ala.models;

/**
 * NormalParadiseLevelGeneratorModel.
 * 
 */
public class NormalParadiseLevelGeneratorModel extends LevelGeneratorPatternModel {

    //Attributes:
    //Debug variable:
    private DebugInformations debugInformations;

    //Game loop variables that is called 60 times per second.
    private static final int STANDARD_DEATH_ANIMATION_COUNTER_VALUE = 0;
    private static final boolean STANDARD_ANIMATION_TIMER_STOPPED_VALUE = false;

    private int deathAnimationCounter;
    private boolean animationTimerStopped;

    private static final int PARADISE_LVL_TYPE = 3;
    //Constructor:
    /**
     * Constructor.
     * 
     * @param lvlNumber
     * 
     */
    public NormalParadiseLevelGeneratorModel(final LVLNUMBER lvlNumber) { //Load game Level resources:
        super(lvlNumber, PARADISE_LVL_TYPE);
        this.debugInformations = new DebugInformations();

        this.deathAnimationCounter = STANDARD_DEATH_ANIMATION_COUNTER_VALUE;
        this.animationTimerStopped = STANDARD_ANIMATION_TIMER_STOPPED_VALUE;

    }

    //Getters&Setterss:
    public final DebugInformations getDebugInformations() {
        return debugInformations;
    }

    public final void setDebugInformations(final DebugInformations debugInformations) {
        this.debugInformations = debugInformations;
    }

    public final int getDeathAnimationCounter() {
        return deathAnimationCounter;
    }

    public final void setDeathAnimationCounter(final int deathAnimationCounter) {
        this.deathAnimationCounter = deathAnimationCounter;
    }

    public final boolean isAnimationTimerStopped() {
        return animationTimerStopped;
    }

    public final void setAnimationTimerStopped(final boolean animationTimerStopped) {
        this.animationTimerStopped = animationTimerStopped;
    }
}
