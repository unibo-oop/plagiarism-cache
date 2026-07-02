package fargoal.model.entity.monsters.impl;

import fargoal.commons.api.Position;
import fargoal.model.entity.monsters.ai.Ai;
import fargoal.model.entity.monsters.api.AbstractMonster;
import fargoal.model.entity.monsters.api.MonsterType;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.RenderFactory;

/**
 * A class that develops the monster Spider:
 * an enemy that have the ability to climb walls
 * and that can attack the player by jumping down 
 * from them.
 */
public class Spider extends AbstractMonster {

    private static final int NEXT_MOVE = 1000;
    private static final int MIN_WAIT = 1000;
    private static final int MAX_WAIT = 1500;
    private static final int FLOOR_CHANGE = 12;
    private final int minimumWait;
    private int nextMove;

    /**
     * A constructor for the Spider; it uses the
     * super of the AbstractMonster constructor.
     * 
     * @param position - the starting position
     * @param level - the level of the monster
     * @param floorManager - to get infos about the other entities/items
     * @param renderFactory - to give a render to the Spider
     */
    public Spider(final Position position, 
            final Integer level,
            final FloorManager floorManager,
            final RenderFactory renderFactory) {
        super(position, level, floorManager);
        if (floorManager.getFloorLevel() > FLOOR_CHANGE) {
            minimumWait = MIN_WAIT;
        } else {
            minimumWait = MAX_WAIT;
        }
        setMonsterType(MonsterType.SPIDER);
        this.setRenderer(renderFactory);
    }

    private void setRenderer(final RenderFactory renderFactory) {
        this.setRender(renderFactory.spiderRenderer(this));
    }

    /** {@inheritDoc} */
    @Override
    public String getTag() {
        return "SPIDER";
    }

    /** {@inheritDoc} */
    @Override
    public void update(final FloorManager floorManager) {
        final long temp = System.currentTimeMillis();
        if (Math.abs(this.getTimer() - temp) >= nextMove) {
            this.nextMove = this.getRandom(NEXT_MOVE * this.getSkill() / this.getLevel()) + minimumWait;
            this.setTimer();
            if (this.areNeighbours(floorManager, 1) 
                    && !floorManager.getPlayer().isImmune()
                    && floorManager.getPlayer().isVisible()) {
                floorManager.getPlayer().setIsAttacked(true);
                this.setIsFighting(true);
                floorManager.getPlayer().battle(this);
            } else {
                Ai.move(this, floorManager.getPlayer(), floorManager);
            }
        }
    }
}
