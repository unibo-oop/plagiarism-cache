package rogue.model.creature;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.util.Pair;

/**
 * This class represents the standard strategy with which increase the level value.
 */
public final class StandardLevelIncreaseStrategy implements LevelIncreaseStrategy {

    /**
     * This enumeration represents the association between the experience value,
     * kept in the first field, and the level one, kept in the second field.
     */
    enum ExpLevel {
        L1(0, 1),      L2(7, 2),      L3(16, 3),     L4(30, 4), 
        L5(50, 5),     L6(75, 6),     L7(115, 7),    L8(170, 8),
        L9(250, 9),    L10(355, 10),  L11(525, 11),  L12(725, 12),
        L13(1050, 13), L14(1475, 14), L15(2125, 15), L16(3000, 16), 
        L17(4250, 17), L18(6000, 18), L19(8500, 19), L20(12_000, 20);

        private final int expValue;
        private final int levelValue;

        ExpLevel(final int expValue, final int levelValue) {
            this.expValue = expValue;
            this.levelValue = levelValue;
        }

    }

    private List<Pair<Integer, Integer>> values;

    /**
     * Creates a new standard strategy for level increase.
     */
    public StandardLevelIncreaseStrategy() {
        this.values = Arrays.asList(ExpLevel.values()).stream()
            .map(i -> new Pair<>(i.expValue, i.levelValue))
            .collect(Collectors.toList());
    }

    @Override
    public int getLevel(final int experience) {
        return this.values.stream()
            .filter(p -> p.getKey() <= experience)
            .map(p -> p.getValue())
            .reduce((l1, l2) -> l2).get();
    }

}
