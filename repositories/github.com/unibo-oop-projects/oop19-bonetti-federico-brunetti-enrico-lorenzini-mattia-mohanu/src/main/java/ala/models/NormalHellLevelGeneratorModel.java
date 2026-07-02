package ala.models;

/**
 * NormalHellLevelGeneratorModel class.
 * 
 */
public class NormalHellLevelGeneratorModel extends LevelGeneratorPatternModel {
    //Attributes:
    //Debug variable:
    private DebugInformations debugInformations;

    private int deathAnimationCounter;
    private boolean animationTimerStopped;

    private static final int INFERNO_LVL_TYPE = 1;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param lvlNumber
     * 
     */
    public NormalHellLevelGeneratorModel(final LVLNUMBER lvlNumber) { //Load game Level resources:
        super(lvlNumber, INFERNO_LVL_TYPE);

        this.debugInformations = new DebugInformations();

        this.deathAnimationCounter = LevelGeneratorPatternModel.START_DEATH_ANIMATION_COUNTER_VALUE;
        this.animationTimerStopped = LevelGeneratorPatternModel.START_ANIMATION_TIMER_STOPPED_VALUE;
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
