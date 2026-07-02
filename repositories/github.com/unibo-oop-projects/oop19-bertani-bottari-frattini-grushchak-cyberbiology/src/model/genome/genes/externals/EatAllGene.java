package model.genome.genes.externals;

import java.util.Optional;

import model.direction.Direction;
import model.direction.DirectionDecryptor;
import model.entity.Entity;
import model.entity.EntityTypeNameEnum;
import model.entity.cell.Cell;
import model.entity.cell.CellTypeNameEnum;
import model.entity.cell.cellDead.CellDead;
import model.entity.cell.standard.CellStandard;
import model.entity.cell.standard.obtainable.EnergyTypeEnum;
import model.genome.genes.steps.GenomeStepEnum;
import model.world.World;

/**
 * 
 * A cell try to eat another cell in an direction.
 *
 */
public class EatAllGene extends AbstractExternalGene implements CellFamily {
    private static final float ENERGY_PORTION = 0.5F;
    private final int nutritionOfDeadCell;
    /**
     * @param world the current world of simulation.
     * @param nutritionOfDeadCell the quantity of energy that receive cell if it ate an dead cell.
     */
    public EatAllGene(final World world, final int nutritionOfDeadCell) {
        super(world);
        this.nutritionOfDeadCell = nutritionOfDeadCell;
    }

    @Override
    public final void launch(final CellStandard cell) {
        final int parameter = cell.getGeneValueWithOffsetAndJump(GenomeStepEnum.ONE.getStep());
        final Direction direction = DirectionDecryptor.getDirection(parameter);
        final Optional<Entity> something = getEntityInDirection(cell.getX(), cell.getY(), direction);

        if (something.isPresent() && something.get().getEntityType().equals(EntityTypeNameEnum.CELL)) {
            final CellTypeNameEnum cellToAttackType = castToCell(something.get()).getCellTypeName();

            switch (cellToAttackType) {
            case CELL_STANDARD_ALIVE:
                CellStandard victim = 
                    (CellStandard) tryCastToSpecificCell(something.get(), CellTypeNameEnum.CELL_STANDARD_ALIVE).get(); //type was controlled, is not empty
                if (!areRelative(cell, victim)) { //Do not attack relatives.
                    battle(cell, victim);
                }
                break;
            case CELL_DEAD:
                cell.incrementEnergy(nutritionOfDeadCell, EnergyTypeEnum.EATING);
                getWorld().removeCell(something.get().getX(), something.get().getY());
                break;
            default: //do nothing.
            }
        }
    }

    private void battle(final CellStandard cell, final CellStandard victim) {
        //if the cell has less minerals than victim.
        if (cell.getMineral() < victim.getMineral()) {
            cell.decrementMineral(cell.getMineral()); // cell lost all its minerals
            victim.decrementMineral(cell.getMineral()); // victim lost quantity of minerals equal to the quantity that lost cell. 
            // if the cell has no more than double the energy than the victim has minerals
            if (cell.getEnergy() / 2 < victim.getMineral()) { 
                victim.decrementMineral(cell.getEnergy() / 2); // victim lost its minerals in battle.
                getWorld().removeCell(cell.getX(), cell.getY()); // cell lost the battle and died
                cell.deactive();
            } else { //otherwise the cell kills and eats victim
                cell.decrementEnergy(victim.getMineral() * 2); // cell lost energy in battle.
                final int energyToAdd = Math.round(ENERGY_PORTION * victim.getEnergy());
                cell.incrementEnergy(energyToAdd + nutritionOfDeadCell, EnergyTypeEnum.EATING); //the cell eats victim.
                getWorld().removeCell(victim.getX(), victim.getY());
                victim.deactive();
            }
        //if the cell has more minerals than victim. so cell kills and eats victim.
        } else {
            cell.decrementMineral(victim.getMineral()); // cell lost minerals in battle.
            final int energyToAdd = Math.round(ENERGY_PORTION * victim.getEnergy());
            cell.incrementEnergy(energyToAdd + nutritionOfDeadCell, EnergyTypeEnum.EATING);
            getWorld().removeCell(victim.getX(), victim.getY());
            victim.deactive();
        }
    }

    @Override
    public final String getDescription() {
        return "Eat all";
    }
}
