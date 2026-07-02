package model.world;

import model.entity.EntityTypeNameEnum;
import model.entity.cell.CellTypeNameEnum;
import model.entity.cell.standard.obtainable.EnergyTypeEnum;
import model.caster.CastClass;
import model.caster.CastClassImp;

public class CellInfosImp implements CellInfos {

    private final World world;

    private final CastClass cast = new CastClassImp();

    private int total = 0;

    private int partial = 0;
    /**
     * number of cells used to get medium values.
     */
    private int counter;

    public CellInfosImp(final World world) {
        this.world = world;
        this.counter = this.setCounter();
    }

    private int setCounter() {
        return (int) this.world.getMap().values().stream()
                .map(m -> m.getEntity())
                .filter(f -> f.isPresent())
                .map(m -> m.get())
                .filter(f -> f.getEntityType().equals(EntityTypeNameEnum.CELL))
                .map(m -> cast.cellCast(m))
                .filter(f -> f.getCellTypeName().equals(CellTypeNameEnum.CELL_STANDARD_ALIVE))
                .map(m -> cast.cellStandardCast(m))
                .filter(f -> f.isActive())
                .count();
    }

    @Override
    public final int getMediumAge() {
        this.total = 0;

        world.getMap().values().stream()
        .map(m -> m.getEntity())
        .filter(f -> f.isPresent())
        .map(m -> m.get())
        .filter(f -> f.getEntityType().equals(EntityTypeNameEnum.CELL))
        .map(m -> cast.cellCast(m))
        .filter(f -> f.getCellTypeName().equals(CellTypeNameEnum.CELL_STANDARD_ALIVE))
        .map(m -> cast.cellStandardCast(m))
        .filter(f -> f.isActive())
        .forEach(c -> this.total = Math.addExact(this.total, c.getAge()));

        return this.total / this.counter;
    }

    @Override
    public final int getMediumPercEatingEnergies() {
        this.total = 0;
        this.partial = 0;

        world.getMap().values().stream()
        .map(m -> m.getEntity())
        .filter(f -> f.isPresent())
        .map(m -> m.get())
        .filter(f -> f.getEntityType().equals(EntityTypeNameEnum.CELL))
        .map(m -> cast.cellCast(m))
        .filter(f -> f.getCellTypeName().equals(CellTypeNameEnum.CELL_STANDARD_ALIVE))
        .map(m -> cast.cellStandardCast(m))
        .filter(f -> f.isActive())
        .filter(c -> c.getTotalEnergyGained() !=  0)
        .forEach(c -> {
            this.partial = Math.addExact(this.partial, c.getSpecificEnergyGained(EnergyTypeEnum.EATING));
            this.total = Math.addExact(this.total, c.getTotalEnergyGained());
        });

        if (this.total == 0) {
            return 0;
    }
        return (this.partial * 100) / this.total;
    }

    @Override
    public final int getMediumPercPhotosyntesisEnergy() {
        this.total = 0;
        this.partial = 0;

        world.getMap().values().stream()
        .map(m -> m.getEntity())
        .filter(f -> f.isPresent())
        .map(m -> m.get())
        .filter(f -> f.getEntityType().equals(EntityTypeNameEnum.CELL))
        .map(m -> cast.cellCast(m))
        .filter(f -> f.getCellTypeName().equals(CellTypeNameEnum.CELL_STANDARD_ALIVE))
        .map(m -> cast.cellStandardCast(m))
        .filter(f -> f.isActive())
        .filter(c -> c.getTotalEnergyGained() !=  0)
        .forEach(c -> {
            this.total = Math.addExact(this.total, c.getTotalEnergyGained());
            this.partial = Math.addExact(this.partial, c.getSpecificEnergyGained(EnergyTypeEnum.PHOTOSYNTHESIS));
        });

        if (this.total == 0) {
            return 0;
        }
        return (this.partial * 100) / this.total;
    }

    @Override
    public final int getMediumPercMineralEnergy() {
        this.total = 0;
        this.partial = 0;

        world.getMap().values().stream()
        .map(m -> m.getEntity())
        .filter(f -> f.isPresent())
        .map(m -> m.get())
        .filter(f -> f.getEntityType().equals(EntityTypeNameEnum.CELL))
        .map(m -> cast.cellCast(m))
        .filter(f -> f.getCellTypeName().equals(CellTypeNameEnum.CELL_STANDARD_ALIVE))
        .map(m -> cast.cellStandardCast(m))
        .filter(f -> f.isActive())
        .filter(c -> c.getTotalEnergyGained() != 0)
        .forEach(c -> {
            this.total = Math.addExact(this.total, c.getTotalEnergyGained());
            this.partial = Math.addExact(this.partial, c.getSpecificEnergyGained(EnergyTypeEnum.CONVERTING_MINERAL));
        });

        if (this.total == 0) {
            return 0;
        }
        return (this.partial * 100) / this.total;
    }

    @Override
    public final int getMediumPercAltruismEnergy() {
        this.total = 0;
        this.partial = 0;

        world.getMap().values().stream()
        .map(m -> m.getEntity())
        .filter(f -> f.isPresent())
        .map(m -> m.get())
        .filter(f -> f.getEntityType().equals(EntityTypeNameEnum.CELL))
        .map(m -> cast.cellCast(m))
        .filter(f -> f.getCellTypeName().equals(CellTypeNameEnum.CELL_STANDARD_ALIVE))
        .map(m -> cast.cellStandardCast(m))
        .filter(f -> f.isActive())
        .filter(c -> c.getTotalEnergyGained() != 0)
        .forEach(c -> {
            this.partial = Math.addExact(this.partial, c.getSpecificEnergyGained(EnergyTypeEnum.ALTRUISM));
            this.total = Math.addExact(this.total, c.getTotalEnergyGained());
        });

        if (this.total == 0) {
            return 0;
        }
        return (this.partial * 100) / this.total;
    }

    @Override
    public final int getMediumTotalEnergy() {
        this.total = 0;

        world.getMap().values().stream()
        .map(m -> m.getEntity())
        .filter(f -> f.isPresent())
        .map(m -> m.get())
        .filter(f -> f.getEntityType().equals(EntityTypeNameEnum.CELL))
        .map(m -> cast.cellCast(m))
        .filter(f -> f.getCellTypeName().equals(CellTypeNameEnum.CELL_STANDARD_ALIVE))
        .map(m -> cast.cellStandardCast(m))
        .filter(f -> f.isActive())
        .forEach(c -> Math.addExact(this.total, c.getEnergy()));

        return this.total / this.counter;

    }

    @Override
    public final int getMediumNumberofGenes() {
        this.total = 0;

        world.getMap().values().stream()
        .map(m -> m.getEntity())
        .filter(f -> f.isPresent())
        .map(m -> m.get())
        .filter(f -> f.getEntityType().equals(EntityTypeNameEnum.CELL))
        .map(m -> cast.cellCast(m))
        .filter(f -> f.getCellTypeName().equals(CellTypeNameEnum.CELL_STANDARD_ALIVE))
        .map(m -> cast.cellStandardCast(m))
        .filter(f -> f.isActive())
        .forEach(c -> Math.addExact(this.total, c.getNumberOfGenes()));

        return this.total / this.counter;
    }

    @Override
    public final int getDeadCellsNumber() {
        return world.getDeadCells();
    }

    @Override
    public final int getAliveCellsNumber() {
        return world.getAliveCells();
    }

}
