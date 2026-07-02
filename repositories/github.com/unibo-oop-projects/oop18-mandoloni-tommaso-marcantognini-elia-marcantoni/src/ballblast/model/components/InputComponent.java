package ballblast.model.components;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputManager;
import ballblast.model.inputs.InputManager.PlayerTag;

/**
 * Makes a {@link GameObject} controllable through the use of inputs (for
 * example via keyboard).
 */
public class InputComponent extends AbstractComponent {
    private final InputManager inputManager;
    private final PlayerTag tag;
    private List<Consumer<GameObject>> toBeResolved;

    /**
     * Class constructor.
     * 
     * @param inputManager the {@link InputManager} used to received inputs.
     * @param tag          the {@link PlayerTag} used to identifies the
     *                     {@link InputComponent} inside the {@link InputManager}.
     */
    public InputComponent(final InputManager inputManager, final PlayerTag tag) {
        super(ComponentType.INPUT);
        this.inputManager = inputManager;
        this.tag = tag;
        this.toBeResolved = Collections.emptyList();
    }

    @Override
    public final void enable() {
        super.enable();
        this.inputManager.addInputHandler(tag, this);
    }

    @Override
    public final void disable() {
        super.disable();
        this.inputManager.removeInputHandler(this.tag);
    }

    @Override
    public final void update(final double elapsed) {
        if (this.isEnabled()) {
            this.resolveCommands();
        }
    }

    /**
     * Receives commands to be resolved at the next update.
     * 
     * @param list the {@link List} of commands to be resolved.
     */
    public final void receiveCommands(final List<Consumer<GameObject>> list) {
        this.toBeResolved = list;
    }

    private void resolveCommands() {
        this.toBeResolved.forEach(c -> c.accept(this.getParent()));
        this.emptyCommands();
    }

    private void emptyCommands() {
        this.toBeResolved = Collections.emptyList();
    }
}
