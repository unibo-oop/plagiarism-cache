package oop.lit.model.simplegame.elements.actions;

import java.util.Collection;

import oop.lit.model.Action;
import oop.lit.model.actions.AbstractAction;
import oop.lit.model.simplegame.SimplePlayerManager;
import oop.lit.model.simplegame.elements.FlippableSBE;
import oop.lit.util.IllegalInputException;

/**
 * A FlippableSBEActionFactory implementation.
 */
public class FlippableSBEActionFactoryImpl extends BasicSBEActionFactoryImpl implements FlippableSBEActionFactory {
    /**
     * 
     */
    private static final long serialVersionUID = -8273662211664588181L;
    /**
     * @param pManager
     *      this game player manager.
     */
    public FlippableSBEActionFactoryImpl(final SimplePlayerManager pManager) {
        super(pManager);
    }
    @Override
    public Action getFlipAction(final Collection<FlippableSBE> elements) {
        return new AbstractAction("Flip") {
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                elements.forEach(FlippableSBE::flip);
            }
        };
    }
}
