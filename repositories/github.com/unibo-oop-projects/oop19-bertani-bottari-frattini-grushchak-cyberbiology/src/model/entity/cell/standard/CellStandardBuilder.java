package model.entity.cell.standard;

import java.util.List;

import model.direction.Direction;
import model.entity.cell.standard.action.ActionsManipulation;


public interface CellStandardBuilder {
    CellStandard build();
    CellStandardBuilder setX(int x);
    CellStandardBuilder setY(int y);
    CellStandardBuilder setDirection(Direction direction);

    CellStandardBuilder setEnergy(int energy);

    CellStandardBuilder setMineral(int mineral);

    CellStandardBuilder setGenome(List<Integer> genome);

    CellStandardBuilder setActions(ActionsManipulation actions);
    CellStandardBuilder setGeneration(int generation);

}
