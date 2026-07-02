package casim.ui.components.menu.automaton;

import java.util.stream.Collectors;

import casim.controller.menu.MenuController;
import casim.core.AppManager;
import casim.ui.components.menu.AbstractMenu;
import casim.utils.ViewUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

/**
 * Main menu of the application.
 */
public class AutomatonMenu extends AbstractMenu {
    private static final double BUTTON_SPACING = 15.0;

    /**
     * Construct a new menu.
     * 
     * @param appManager the {@link AppManager} app manager.
     * @param controller the {@link MenuController} controlling the menu.
     */
    public AutomatonMenu(final AppManager appManager, final MenuController controller) {
        super(appManager, controller);
        this.init();
    }

    private void init() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(BUTTON_SPACING);
        ViewUtils.fitToAnchorPane(this);
        final var buttons = this.getMenuController().getMenuItems().stream()
            .map(x -> new AutomatonMenuButton(x.getName(), x, this))
            .collect(Collectors.toList());
        buttons.forEach(btn -> {
            btn.setPrefSize(Button.USE_COMPUTED_SIZE, Button.USE_COMPUTED_SIZE);
            btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        });
        this.addNodes(buttons);
    }
}
