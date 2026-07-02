package controller.navy_setup;

import java.util.List;

import controller.AbstractController;
import model.navy.NavyBuilderImpl;
import view.navy_setup.NavySetupUI;

/**
 * Basic implementation of {@link NavySetupUIController}.
 */
public final class NavySetupUIControllerImpl extends AbstractController implements NavySetupUIController {

    /**
     * The associated view.
     */
    private final NavySetupUI view;

    /**
     * Creates a new {@link NavySetupUIController}.
     * @param view is the view to associate with
     */
    public NavySetupUIControllerImpl(final NavySetupUI view) {
        super();
        this.view = view;
    }

    @Override
    public void inputData(final List<Integer> sizeList, final int gridSize) {
        try {
            new NavyBuilderImpl(sizeList, gridSize);
            view.enableConfirm();
        } catch (IllegalArgumentException e) {
            view.disableConfirm();
        }
    }

    @Override
    public void setDimension(final List<Integer> sizeList, final int gridSize) {
            getMasterController().setNavyBuilderSpecification(sizeList, gridSize);
    }
}
