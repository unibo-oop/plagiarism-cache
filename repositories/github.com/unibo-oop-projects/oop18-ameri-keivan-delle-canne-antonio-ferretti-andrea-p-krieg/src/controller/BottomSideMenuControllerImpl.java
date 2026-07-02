package controller;

import java.util.List;

import controller.selection.GameCommandsUsingSelection;
import model.objects.unit.Unit;
import view.UpdatableView;

/**
 * The BottomSideMenuControllerImpl class extends SecondaryControllerImpl and
 * implements BottomSideMenuController. It represent the implementation of the
 * controller part of the bottom side menu.
 */
public class BottomSideMenuControllerImpl extends AbstractSecondaryController implements BottomSideMenuController {

    private final GameCommandsUsingSelection model;

    /**
     * BottomSideMenuControllerImpl constructor.
     * 
     * @param view  is the view part linked to this class.
     * @param model is the model part linked to this class.
     */
    public BottomSideMenuControllerImpl(final UpdatableView view, final GameCommandsUsingSelection model) {
        super(view);
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createUnit(final int unitLevel) {
        model.createUnitFromSelectedCity(model.getPossibleUnit().get(unitLevel));
        super.notifyObserver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getActualSelectionName() {
        if (!model.getActualSelection().isPresent()) {
            return "Nothing selected";
        } else {
            return model.getActualSelection().get().getValue().getDescription();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfPossibleUnit() {
        return model.getPossibleUnit().size();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IllegalStateException if the level doesn't match with a unit.
     */
    @Override
    public String getUnitName(final int level) {
        return getUnit(level).getName();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IllegalStateException if the level doesn't match with a unit.
     * 
     */
    @Override
    public String getUnitCostToString(final int level) {
        return getUnit(level).getCostToString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canCreate() {
        return model.canSelectedCityCreate();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IllegalStateException if the level doesn't match with a unit.
     */
    @Override
    public boolean hasEnoughResourcesToCreate(final int level) {
        return model.canCreateUnit(getUnit(level));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Unit> getPossibleUnit() {
        return model.getPossibleUnit();
    }

    private Unit getUnit(final int level) {
        if (getNumberOfPossibleUnit() > level) {
            return getPossibleUnit().get(level);
        } else {
            throw new IllegalStateException();
        }
    }

}
