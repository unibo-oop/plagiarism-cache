package controller.navy_builder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import controller.AbstractController;
import model.basic_component.StaticPoint2D;
import model.navy.Navy;
import model.navy.NavyBuilder;
import model.navy.RandomNavyFactoryImpl;
import model.navy.RandomNavyFactory;
import model.ship.BuilderShip;
import model.ship.BuilderShipImpl;
import view.navy_builder_interface.NavyBuilderUI;

/**
 * Implementation of {@link NavyBuilderUIController}.
 */
public final class NavyBuilderUIControllerImpl extends AbstractController implements NavyBuilderUIController {
    /**
     * The associated {@link NavyBuilderUI}.
     */
    private final NavyBuilderUI view;

    /**
     * Internal data to build a navy.
     */
    private final NavyBuilder navyBuilder = getModel().getNavyBuilder();

    /**
     * A {@link BuilderShip} for build up a {@link Ship}.
     * Used as support for the {@link NavyBuilder}
     */
    private final BuilderShip shipBuilder = new BuilderShipImpl();

    /**
     * The {@link Navy} currently under construction.
     */
    private Optional<Navy> navy = Optional.empty();

    /**
     * Constructor of {@link NavyBuilderUIController}.
     * @param view is the {@link NavyBuilderUI} thats is be controlled
     */
    public NavyBuilderUIControllerImpl(final NavyBuilderUI view) {
        super();
        this.view = view;
        navyBuilder.reset();
        shipBuilder.reset();
    }

    @Override
    public int getGridSize() {
        return navyBuilder.getGridSize();
    }

    @Override
    public List<Integer> getMissingShipCount() {
        return navyBuilder.getMissingShips();
    }

    @Override
    public void setCoord(final StaticPoint2D point) throws IllegalStateException {
        if (!shipBuilder.getFirstCoord().isPresent()) {
            shipBuilder.setFirstCoord(point);
        } else {
            if (!shipBuilder.getFirstCoord().get().equals(point)) {
                shipBuilder.setSecondCoord(point);
            } 
            navyBuilder.addShip(shipBuilder.build());
        }
        if (navyBuilder.canBuild()) {
            navy = Optional.of(navyBuilder.buildNavy());
        }
        view.update();
    }

    @Override
    public Set<StaticPoint2D> getAvaiableCoord() {
        if (shipBuilder.getFirstCoord().isPresent()) {
            if (shipBuilder.getSize().isPresent()) {
                return navyBuilder.getAvailablePositionsSecondCord(shipBuilder.getFirstCoord().get(), shipBuilder.getSize().get());
            } else {
                return navyBuilder.getAvailablePositionsSecondCord(shipBuilder.getFirstCoord().get());
            }
        } else if (!shipBuilder.getSize().isPresent()) {
            return navyBuilder.getAvailablePositions();
        } else {
            return navyBuilder.getAvaiablePositions(shipBuilder.getSize().get());
        }
    }

    @Override
    public Set<StaticPoint2D> getOccupiedPosition() {
        return navyBuilder.getAllOccupiedPosition();
    }

    @Override
    public void setCurrentShipSize(final int size) {
        shipBuilder.setSizeShip(size);
    }

    @Override
    public void reset() {
        navyBuilder.reset();
        shipBuilder.reset();
        navy = Optional.empty();
    }

    @Override
    public void newRandomDisposition() {
        if (navyBuilder.getMissingShips().stream().allMatch(currentMissing -> currentMissing == 0)) {
            reset();
        }
        final RandomNavyFactory randomNavyFactory = new RandomNavyFactoryImpl();
        navy = Optional.of(randomNavyFactory.getCostumRandomFormation(navyBuilder));
        view.update();
    }

    @Override
    public boolean canConfirm() {
        return navy.isPresent();
    }

    @Override
    public void confirm() {
        if (navy.isPresent()) {
            getMasterController().confirmNavy(navy.get());
        }
    }
}
