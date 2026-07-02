package casim.ui.components.menu.automaton.config;

import java.util.Random;

import casim.core.AppManager;
import casim.model.Automata;
import casim.model.langtonsant.LangtonsAnt;
import casim.model.langtonsant.LangtonsAntConfig;
import casim.utils.automaton.config.BaseConfig;
import casim.utils.automaton.config.WrappingConfig;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Region;

/**
 * Controller class for the fxml AutomatonConfigMenu with also the wrap configuration.
 */
public class AutomatonWrapConfigController extends AutomatonConfigController {
    private final CheckBox checkBox;
    private final Random rng = new Random();

    /**
     * Construct a new {@link AutomatonWrapConfigController}.
     * 
     * @param appManager the application's manager.
     * @param automata the automata to simulate.
     */
    public AutomatonWrapConfigController(final AppManager appManager, final Automata automata) {
        super(appManager, automata);
        this.checkBox = new CheckBox("Wrapped Grid");
        this.checkBox.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        this.checkBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     * Returns true if the wrapped configuration is selected, false otherwise.
     * 
     * @return true if the automaton uses a wrapped grid, false otherwise.
     */
    protected boolean isWrapped() {
        return this.checkBox.isSelected();
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.addExtension(checkBox);
    }

    @Override
    protected BaseConfig getConfig() {
        final var isAutomatic = this.isAutomatic();
        final var isWrapped = this.isWrapped();
        final int size = Integer.parseInt(this.getSizeText());

        switch (this.getAutomata()) {
            case BRYANS_BRAIN:
            case GAME_OF_LIFE:
                return (BaseConfig) new WrappingConfig(size, size, isAutomatic, isWrapped);
            case LANGTONS_ANT:
                final var ants = LangtonsAnt.MIN_ANTS + this.rng.nextInt(LangtonsAnt.MAX_ANTS - LangtonsAnt.MIN_ANTS + 1);
                return (BaseConfig) new LangtonsAntConfig(size, size, isAutomatic, isWrapped, ants);
            default:
                throw new UnsupportedOperationException(WRONG_MENU);
        }
    }
}
