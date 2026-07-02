package model.minigames.sizecount;

import java.util.LinkedList;
import java.util.List;

/**
 * The implementation of the {@link Dividers}.
 *
 */
public class DividersImpl implements Dividers {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getDividers(final Integer number) {
        final List<Integer> dividers = new LinkedList<>();
        final Double root = Math.sqrt(number);
        Integer divider;

        for (int i = 1; i <= root; i++) {
            divider = number % i;
            if (divider == 0) {
                dividers.add(i);
                if (i != number / i) {
                    dividers.add(number / i);
                }
            }
        }
        return dividers;
    }
}
