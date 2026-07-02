package ballblast.model.inputs;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Consumer;

import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import ballblast.model.components.ComponentType;
import ballblast.model.components.InputComponent;
import ballblast.model.components.ShooterComponent;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.Player;

/**
 * Concrete {@link InputManager} implementation.
 */
public class InputManagerImpl implements InputManager {
    private static final Map<InputType, Consumer<GameObject>> COMMANDS;
    private Map<PlayerTag, InputComponent> inputHandlers;

    static {
        COMMANDS = ImmutableMap.<InputType, Consumer<GameObject>>builder()
                .put(InputType.SHOOT,             g -> findShooter(g).ifPresent(ShooterComponent::startShooting))
                .put(InputType.STOP_SHOOTING,     g -> findShooter(g).ifPresent(ShooterComponent::stopShooting))
                .put(InputType.MOVE_LEFT,         InputManagerImpl::moveLeft)
                .put(InputType.MOVE_RIGHT,        InputManagerImpl::moveRight)
                .put(InputType.STOP_MOVING_LEFT,  InputManagerImpl::stopMovingLeft)
                .put(InputType.STOP_MOVING_RIGHT, InputManagerImpl::stopMovingRight)
                .build();
    }

    /**
     * Class constructor.
     */
    public InputManagerImpl() {
        this.inputHandlers = Collections.emptyMap();
    }

    @Override
    public final void addInputHandler(final PlayerTag tag, final InputComponent inputComponent) {
        this.inputHandlers = ImmutableMap.<PlayerTag, InputComponent>builder()
                .putAll(this.inputHandlers)
                .put(tag, inputComponent)
                .build();
    }

    @Override
    public final void removeInputHandler(final PlayerTag tag) {
        this.inputHandlers = this.inputHandlers.entrySet().stream()
                .filter(e -> e.getKey() != tag)
                .collect(ImmutableMap.toImmutableMap(Entry::getKey, Entry::getValue));
    }

    @Override
    public final void processInputs(final PlayerTag tag, final List<InputType> inputs) {
        this.inputHandlers.get(tag).receiveCommands(this.translateInputs(inputs));
    }

    private List<Consumer<GameObject>> translateInputs(final List<InputType> toBeTranslated) {
        return toBeTranslated.stream().map(i -> COMMANDS.get(i)).collect(ImmutableList.toImmutableList());
    }

    private static Optional<ShooterComponent> findShooter(final GameObject g) {
        return g.getComponents().stream()
                .filter(c -> c.getType() == ComponentType.SHOOTER)
                .map(c -> (ShooterComponent) c).findFirst();
    }

    private static void stopMovingLeft(final GameObject g) {
        if (g.getVelocity().getX() < 0) {
            g.setVelocity(Vector2D.create(0, 0));
        }
    }

    private static void stopMovingRight(final GameObject g) {
        if (g.getVelocity().getX() > 0) {
            g.setVelocity(Vector2D.create(0, 0));
        }
    }

    private static void moveLeft(final GameObject g) {
        final Player player = ((Player) g);
        player.setVelocity(Vector2D.create(-player.getSpeed(), 0));
    }

    private static void moveRight(final GameObject g) {
        final Player player = ((Player) g);
        player.setVelocity(Vector2D.create(player.getSpeed(), 0));
    }
}
