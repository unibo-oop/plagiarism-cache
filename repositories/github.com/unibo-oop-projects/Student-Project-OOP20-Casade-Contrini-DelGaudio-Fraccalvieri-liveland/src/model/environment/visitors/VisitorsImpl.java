package model.environment.visitors;

import view.menu.VisitorsOutOfBoundException;

public class VisitorsImpl implements Visitors {

    private final int visitors;
    private static final int MAX_VISITORS = 300; 

    public VisitorsImpl(final int visitors) {
        if (visitors >= 1 && visitors <= MAX_VISITORS) {
            this.visitors = visitors;
        } else {
            throw new VisitorsOutOfBoundException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getVisitorsNumber() {
        return this.visitors;
    }

}
