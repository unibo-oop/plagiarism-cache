package casim.core;

import casim.controller.automaton.AutomatonController;
import casim.controller.automaton.AutomatonControllerImpl;
import casim.controller.automaton.CoDiControllerImpl;
import casim.controller.menu.MenuControllerImpl;
import casim.model.Automata;
import casim.model.abstraction.cell.AbstractCell;
import casim.model.bryansbrain.BryansBrainCellState;
import casim.model.codi.CoDiConfig;
import casim.model.codi.cell.CoDiCellState;
import casim.model.gameoflife.GameOfLifeState;
import casim.model.langtonsant.LangtonsAntCellState;
import casim.model.langtonsant.LangtonsAntConfig;
import casim.model.rule110.Rule110CellState;
import casim.model.wator.WatorCellState;
import casim.ui.components.grid.CanvasGridBuilderImpl;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.components.menu.automaton.AutomatonMenu;
import casim.ui.components.menu.automaton.config.AutomatonConfigController;
import casim.ui.components.menu.automaton.config.AutomatonWrapConfigController;
import casim.ui.components.page.PageContainer;
import casim.ui.utils.ViewEnum;
import casim.ui.utils.statecolormapper.StateColorMapper;
import casim.ui.utils.statecolormapper.StateColorMapperFactory;
import casim.ui.utils.statecolormapper.StateColorMapperFactoryImpl;
import casim.ui.view.AutomatonViewController;
import casim.ui.view.ConcurrentAutomatonViewController;
import casim.ui.view.codi.CoDiViewController;
import casim.ui.view.codi.ConcurrentCoDiViewController;
import casim.utils.Empty;
import casim.utils.Result;
import casim.utils.automaton.AutomatonFactoryImpl;
import casim.utils.automaton.config.BaseConfig;
import casim.utils.automaton.config.WrappingConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * Implementation of {@link AppManager}.
 */
public final class AppManagerImpl implements AppManager {
    private static final String UNKNOWN_AUTOMATON = "Unknown automaton.";

    private final PageContainer container;
    private final AutomatonFactoryImpl automatonFactory = new AutomatonFactoryImpl();
    private final StateColorMapperFactory stateColorMapperFactory = new StateColorMapperFactoryImpl();

    /**
     * Build the app manager.
     * 
     * @param container the application's {@link PageContainer}
     */
    public AppManagerImpl(final PageContainer container) {
        this.container = container;
    }


    @Override
    public PageContainer getContainer() {
        return this.container;
    }

    @Override
    public void showMainMenu() {
        final var controller = new MenuControllerImpl();
        final var menu = new AutomatonMenu(this, controller);
        this.container.addPage(menu);
    }

    @Override
    public Result<Empty> showConfigMenu(final Automata automata) {
        final var loader = new FXMLLoader(
            getClass().getResource(ViewEnum.AUTOMATON_CONFIG_MENU.getResourceName()));
        final var controller = this.getConfigController(automata);
        loader.setController(controller);
        final Result<VBox> view = Result.executeSupplier(() -> (VBox) loader.load());
        view.ifPresent(this.container::addPage);
        return view.map(x -> new Empty() { });
    }

    @Override
    public Result<Empty> showSimulation(final Automata automata, final BaseConfig config) {
        final var loader = new FXMLLoader(
            getClass().getResource(ViewEnum.AUTOMATON_VIEW.getResourceName()));
        final var viewController = this.getAutomatonViewControllerFromAutomata(automata, config);
        loader.setController(viewController);
        final Result<VBox> view = Result.executeSupplier(() -> (VBox) loader.load());
        view.ifPresent(this.container::addPage);
        return view.map(x -> new Empty() { });
    }

    @SuppressWarnings("unchecked")
    private <T extends AutomatonViewController<?>> T getAutomatonViewControllerFromAutomata(
            final Automata automata, final BaseConfig config) {
        switch (automata) {
            case CODI:
                return (T) this.getCoDiViewController((CoDiConfig) config);
            case RULE110:
                return (T) this.getRule110ViewController(config);
            case WATOR:
                return (T) this.getWatorViewController(config);
            case BRYANS_BRAIN:
                return (T) this.getBryansBrainViewController((WrappingConfig) config);
            case GAME_OF_LIFE:
                return (T) this.getGameOfLifeViewController((WrappingConfig) config);
            case LANGTONS_ANT:
                return (T) this.getLangtonsAntViewController((LangtonsAntConfig) config);
            default:
                throw new IllegalArgumentException(UNKNOWN_AUTOMATON);
        }
    }

    private AutomatonViewController<BryansBrainCellState> getBryansBrainViewController(
            final WrappingConfig config) {
        final var controller = new AutomatonControllerImpl<>(automatonFactory.getBryansBrainRandom(config));
        return this.getAutomatonViewController(
            controller,
            getGrid(config.getCols(), config.getRows()),
            this.stateColorMapperFactory.getBryansBrainStateColorMapper(),
            config.isAutomatic());
    }

    private AutomatonViewController<CoDiCellState> getCoDiViewController(
            final CoDiConfig config) {
        final var controller = new CoDiControllerImpl(automatonFactory.getCoDi(config));
        return this.getCoDiViewController(
            controller,
            this.getGrid(config.getCols(), config.getRows()),
            this.stateColorMapperFactory.getCoDiStateColorMapper(),
            config.isAutomatic());
    }

    private AutomatonViewController<WatorCellState> getWatorViewController(
            final BaseConfig config) {
        final var controller = new AutomatonControllerImpl<>(automatonFactory.getWator(config));
        return this.getAutomatonViewController(
            controller,
            this.getGrid(config.getCols(), config.getRows()),
            this.stateColorMapperFactory.getWatorStateColorMapper(),
            config.isAutomatic());
    }

    private AutomatonViewController<LangtonsAntCellState> getLangtonsAntViewController(
            final LangtonsAntConfig config) {
        final var controller = new AutomatonControllerImpl<>(automatonFactory.getLangtonsAnt(config));
        return this.getAutomatonViewController(
            controller,
            this.getGrid(config.getCols(), config.getRows()),
            this.stateColorMapperFactory.getLangtonsAntStateColorMapper(),
            config.isAutomatic());
    }

    private AutomatonViewController<Rule110CellState> getRule110ViewController(
            final BaseConfig config) {
        final var controller = new AutomatonControllerImpl<>(automatonFactory.getRule110(config));
        return this.getAutomatonViewController(
            controller,
            this.getGrid(config.getCols(), config.getRows()),
            this.stateColorMapperFactory.getRule110StateColorMapper(),
            config.isAutomatic());
    }

    private AutomatonViewController<GameOfLifeState> getGameOfLifeViewController(final WrappingConfig config) {
        final var controller = new AutomatonControllerImpl<>(automatonFactory.getGameOfLife(config));
        return this.getAutomatonViewController(
            controller,
            this.getGrid(config.getCols(), config.getRows()),
            this.stateColorMapperFactory.getGameOfLifeStateColorMapper(),
            config.isAutomatic());
    }

    private <T, C extends AbstractCell<T>> AutomatonViewController<T> getAutomatonViewController(
            final AutomatonController<T> controller, final CanvasGridImpl grid,
            final StateColorMapper<T> mapper, final boolean isAutomatic) {
        return isAutomatic
            ? new ConcurrentAutomatonViewController<>(this, controller, grid, mapper)
            : new AutomatonViewController<>(this, controller, grid, mapper);
    } 

    private AutomatonViewController<CoDiCellState> getCoDiViewController(
            final CoDiControllerImpl controller, final CanvasGridImpl grid,
            final StateColorMapper<CoDiCellState> mapper, final boolean isAutomatic) {
        return isAutomatic
            ? new ConcurrentCoDiViewController(this, controller, grid, mapper)
            : new CoDiViewController(this, controller, grid, mapper);
    } 

    private AutomatonConfigController getConfigController(final Automata automata) {
        switch (automata) {
            case CODI:
            case RULE110:
            case WATOR:
                return new AutomatonConfigController(this, automata);
            case BRYANS_BRAIN:
            case GAME_OF_LIFE:
            case LANGTONS_ANT:
                return new AutomatonWrapConfigController(this, automata);
            default:
                throw new IllegalArgumentException(UNKNOWN_AUTOMATON);
        }
    }

    private CanvasGridImpl getGrid(final int cols, final int rows) {
        return (CanvasGridImpl) new CanvasGridBuilderImpl().build(rows, cols);
    }
}
