package clashclass.elements.troops;

import clashclass.ai.behaviourtree.BehaviourTreeFactory;
import clashclass.ai.behaviourtree.BehaviourTreeTroopFactoryImpl;
import clashclass.ai.logic.CalculateDamageLogicFactory;
import clashclass.ai.logic.CalculateDamageLogicFactoryImpl;
import clashclass.battle.troopdeath.TroopDeathObservableImpl;
import clashclass.ecs.GameObject;
import clashclass.stats.TroopBaseStatsComponent;

/**
 * Represents an implementation of TroopFactory used for battle.
 */
public class BattleTroopFactoryImpl extends AbstractTroopFactory {
    private static final String PROGRESS_BAR_COLOR_EX = "#C906B3";
    private final BehaviourTreeFactory behaviourTreeFactory;
    private final CalculateDamageLogicFactory damageLogicFactory;

    /**
     * Constructs the factory.
     */
    public BattleTroopFactoryImpl() {
        this.behaviourTreeFactory = new BehaviourTreeTroopFactoryImpl();
        this.damageLogicFactory = new CalculateDamageLogicFactoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalBarbarianComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(TroopHealthStat.BARBARIAN.getHealth()))
                .addComponent(this.getComponentFactory().createProgressBar(PROGRESS_BAR_COLOR_EX))
                .addComponent(new TroopBaseStatsComponent(
                        TroopHealthStat.BARBARIAN.getHealth(),
                        TroopBaseStats.BARBARIAN.getDamage(),
                        TroopBaseStats.BARBARIAN.getAttackSpeed(),
                        TroopBaseStats.BARBARIAN.getMovementSpeed()))
                .addComponent(new TroopDeathObservableImpl())
                .addComponent(this.damageLogicFactory.createForBarbarian())
                .addComponent(this.behaviourTreeFactory.create());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalArcherComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(TroopHealthStat.ARCHER.getHealth()))
                .addComponent(this.getComponentFactory().createProgressBar(PROGRESS_BAR_COLOR_EX))
                .addComponent(new TroopBaseStatsComponent(
                        TroopHealthStat.ARCHER.getHealth(),
                        TroopBaseStats.ARCHER.getDamage(),
                        TroopBaseStats.ARCHER.getAttackSpeed(),
                        TroopBaseStats.ARCHER.getMovementSpeed()))
                .addComponent(new TroopDeathObservableImpl())
                .addComponent(this.damageLogicFactory.createForArcher())
                .addComponent(this.behaviourTreeFactory.create());
    }
}
