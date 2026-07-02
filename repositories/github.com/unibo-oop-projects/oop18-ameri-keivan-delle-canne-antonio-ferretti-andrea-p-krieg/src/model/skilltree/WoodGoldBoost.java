package model.skilltree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.BasicCostImpl;
import model.Cost;

/**
 * The WoodGoldBoost class extends SkillTreeAttributeAbstract. If the user
 * increase the level of this class, he will get more gold and wood from his
 * structure at the beginning of his turn.
 */
public class WoodGoldBoost extends SkillTreeAttributeAbstract {

    private static final String ATTRIBUTE_NAME = "Resourse boost increase: unlock ";
    private static final int INITIAL_VALUE = 0;
    private static final int COST_VALUE = 200;
    private final List<Double> boost = Arrays.asList(1.0, 1.25, 1.50, 1.75, 2.00);
    private final List<Cost> costList;

    /**
     * WoodGoldBoost constructor.
     */
    public WoodGoldBoost() {
        super(INITIAL_VALUE);
        this.costList = createCostList();
    }

    private List<Cost> createCostList() {
        final List<Cost> costList = new LinkedList<>();
        boost.forEach(d -> {
            if (d != 0) {
                costList.add(new BasicCostImpl(
                        Optional.of((int) Math.round((COST_VALUE + (100 * d)) * ((boost.indexOf(d) + 1) / 2))),
                        Optional.of((int) Math.round((COST_VALUE + (100 * d)) * ((boost.indexOf(d) + 1) / 2))),
                        Optional.empty()));
            } else {
                costList.add(new BasicCostImpl());
            }
        });
        return costList;
    }

    private int getPercentualValue(final Double value) {
        return (int) Math.round((value - 1) * 100);
    }

    /** {@inheritDoc} **/
    @Override
    public String getAttributeName() {
        return ATTRIBUTE_NAME + getPercentualValue(boost.get(getCurrentValue() + 1)) + "%";
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canUpgrade() {
        return getCurrentValue() < boost.size() - 1;
    }

    /** {@inheritDoc} **/
    @Override
    public Cost getCost() {
        return this.costList.get(getCurrentValue() + 1);
    }

    /**
     * This method can be use to get the actual resource boost.
     * @return the resource boost.
     */
    public double getBoost() {
        return this.boost.get(getCurrentValue());
    }

}
