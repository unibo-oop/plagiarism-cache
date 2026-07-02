package model.entity.cell.standard.history;

import java.util.EnumMap;

import model.entity.cell.standard.obtainable.EnergyTypeEnum;

public class HistoryImpl implements HistoryManipulation {

    private final EnumMap<EnergyTypeEnum, Integer> energyMap;
    private int totalEnergy;
    private final int generation;

    public HistoryImpl(final int generation) {
        energyMap = new EnumMap<EnergyTypeEnum, Integer>(EnergyTypeEnum.class);
        this.generation = generation;
        for (var i : EnergyTypeEnum.values()) {
            this.energyMap.put(i, 0);
        }
    }

    @Override
    public final int getTotalEnergyGained() {
        return this.totalEnergy;
    }

    @Override
    public final int getSpecificEnergyGained(final EnergyTypeEnum energyType) {
        return this.energyMap.get(energyType);
    }

    @Override
    public final void addEnergy(final int value, final EnergyTypeEnum energyType) {
        this.totalEnergy += value;
        this.energyMap.put(energyType, value + this.energyMap.get(energyType));
    }

    @Override
    public final int getGeneration() {
        return this.generation;
    }
}
