package model.difficulty;

import java.util.Iterator;
import java.util.List;

public class DiffValSpeedOffsetIncrement implements SpeedOffsetIncrement {

    private final Iterator<Double> increments;

    public DiffValSpeedOffsetIncrement() {

        this.increments = new Iterator<Double>() {

            private final List<Double> values = List.of(0.1, 0.07, 0.06, 0.03);
            private Iterator<Double> it = values.iterator();

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Double next() {
                if (!it.hasNext()) {
                    it = values.iterator();
                }
                return it.next();

            }

        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getIncrement() {
        return this.increments.next();
    }

}
