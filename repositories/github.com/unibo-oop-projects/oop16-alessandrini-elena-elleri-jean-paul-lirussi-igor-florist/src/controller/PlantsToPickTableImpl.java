package controller;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import model.PlantImpl;
import model.PlantingImpl;
import view.ToBePickedView;

/**
 * 
 * This class implements {@link PlantsToPickTable} interface.
 *
 */
public class PlantsToPickTableImpl implements PlantsToPickTable {

    private final int numCol = 3;
    private final List<PlantingImpl> plantingList;
    private ToBePickedView view;
    private int select;
    private Object[][] plantingTable;

    /**
     * Builder for {@link PlantsToPickTableImpl}.
     * 
     * @param plantingList
     *      {@link PlantingImpl}
     */
    public PlantsToPickTableImpl(final List<PlantingImpl> plantingList) {
        this.plantingList = plantingList;
        try {
            plantingTable = new Object[plantingList.size()][numCol];
            for (int i = 0; i < plantingList.size(); i++) {
                PlantingImpl p = plantingList.get(i);
                plantingTable[i][0] = p.getPlant().getName();
                plantingTable[i][1] = p.getDates().
                        getSecond().
                        format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                plantingTable[i][2] = this.plantingList.indexOf(p);
            }
            this.view = new ToBePickedView(plantingTable, this);
            this.view.init();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException in PlantToPickTodayTable");
        }
    }

    @Override
    public void selectPlanting(final int pos) {
        this.select = pos;
    }

    @Override
    public void removePlanting() {
        try {
            int posToReset = select;
            PlantingImpl posToPick = plantingList.get(posToReset);
            GreenhouseControllerImpl.getInstance().removePlant(posToPick);
            GreenhouseControllerImpl.getInstance().getPlantingManager().removePlanting((PlantImpl) posToPick.getPlant(), posToPick);
            GreenhouseControllerImpl.getInstance().getGreenhouseView().resetPlantButton(posToReset);
        } catch (NullPointerException e) { 
            System.out.println("Null Pointer Exception");
        }
    }

    @Override
    public Object[][] getToPickTable() {
        return this.plantingTable;
    }
}
