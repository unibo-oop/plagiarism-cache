package view.frames;

/**
 *
 */
public abstract class AbstractRefreshableViewFrame extends AbstractViewFrame {
    static final int GAMEFRAMEBUTTONSFONTSIZE = 4;
    static final int GAMEFRAMEBUTTONSINSETTOPBOTTOM = 3;
    static final int GAMEFRAMEBUTTONSINSETLEFT = 400;
    static final double TAMAGOTCHIBOTTOM = 2;
    static final double TAMAGOTCHILEFT = 350;
    static final double LOGOMAINSIZE = 300;
    static final double ITEMMAINANIMATIONSIZE = 100;
    static final int STATSINSETS = 5;
    static final int STATSSPACING = 10;
    static final double ANIMATIONMOVEDURATION = 0.3;
    static final double ANIMATIONFADEDURATION = 2000;
    static final int ITEMSINSETSSPACING = 10;
    static final double ANIMATIONXRIGTHBORDER = 850;
    static final double ANIMATIONXLEFTBORDER = -10;
    static final double ANIMATIONXCENTERVALUE = 450;
    static final double ANIMATIONYTOPBORDER = 200;
    static final double ANIMATIONYBOTTOMBORDER = 570;
    static final double ANIMATIONYCENTERVALUE = 400;
    static final double ANIMATIONROTATEDURATION = 1;
    static final double ANIMATIONHUNGRYVALUE = 500;
    static final double HUNGRYLEFTANCHOR = 450;
    static final double CLEANESSXSTOP = 150;
    static final double CLEANXSTART = 0;
    static final int AGEFONTSIZE = 4;
    static final double SHOPINVBTNSPACING = 20;
    static final double BTNAGESPACING = 150;

    /**
     * Refreshes the view.
     * 
     * @param both
     *            defines whether to refresh items or stats
     */
    public abstract void refresh(boolean both);

    /**
     * Modified the value of the character's age.
     */
    public abstract void refreshAge();

    @Override
    public abstract void clearStage();

    /**
     * Clears the stage and saves the game state.
     */
    public abstract void clearAndSave();

    @Override
    public void setExitOperation() {
        getStage().setOnCloseRequest(e -> {
            clearAndSave();
            System.exit(0);
        });
    }
}
