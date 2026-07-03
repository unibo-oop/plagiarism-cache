package view.gamescreen;

import view.ViewImpl;

/**
 * 
 * This class represent a slider tahat shows up only when last movement is to be performed.
 *
 */
public class TankMoverSlider extends TankSlider {

    /**
     * 
     * Class Constructor.
     * 
     * @param width
     *              Preferred slider width.
     * 
     */
    public TankMoverSlider(final double width) {
        super(width);
    }

    @Override
    public void setMax() {
       this.setMax(ViewImpl.getIstance().getOriginState().get().getTanks() - 1);
    }

}
