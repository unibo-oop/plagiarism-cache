package vg.model.mystery_box.logic_blink;

/**
 * This class is used to create a new LogicBlink.
 */
public final class StaticFactoryBlink {
    private StaticFactoryBlink() {
    }
    /**
     * This method is used to create a new LogicBlink.
     * @return a new LogicBlink.
     */
    public static LogicBlink createLogicBlinkMysteryBox() {
        final int timeHide = 3000;
        final int timeShow = 4000;
        return new LogicBlinkImpl(timeShow, timeHide);
    }
    /**
     * This method is used to create a new LogicBlink when is picked up.
     * @return a new LogicBlink.
     */
    public static LogicBlink createLogicBlinkPickUp() {
        final int timeHide = 0;
        final int timeShow = 2500;
        return new LogicBlinkImpl(timeShow, timeHide);
    }


}
