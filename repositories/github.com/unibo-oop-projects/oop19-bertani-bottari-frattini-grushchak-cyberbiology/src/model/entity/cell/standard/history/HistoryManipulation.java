package model.entity.cell.standard.history;

import model.entity.cell.standard.obtainable.EnergyTypeEnum;

public interface HistoryManipulation extends History {
    void addEnergy(int value, EnergyTypeEnum energyType);
}
