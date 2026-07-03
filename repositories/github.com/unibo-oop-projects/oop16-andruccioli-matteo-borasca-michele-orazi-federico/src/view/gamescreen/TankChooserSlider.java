package view.gamescreen;

import view.ViewImpl;

/**
 * 
 * This class represent a slider responsible of choosing the quantity of tanks to move or the quantity to attack with.
 *
 */
public class TankChooserSlider extends TankSlider {

    /**
     * 
     * Class Constructor.
     * 
     * @param width
     *              Preferred slider width.
     * 
     */
    public TankChooserSlider(final double width) {
        super(width);
    }

    @Override
    public void setMax() {
        switch(ViewImpl.getIstance().getAtkState().get().getTanks()) {
        case 3:
            this.setMax(2);
            break;
        case 2:
            this.setMax(1);
            break;
        case 1:
            this.setMax(0);
            break;
        default:
            this.setMax(3);
        }
    }

}
