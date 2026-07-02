package input.input_component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.Pair;

import utils.PairUtils;
import input.command.CommandSurvivor;
import input.input_controller.KeyCodes;
import input.input_controller.InputController;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorState;

/**
 * Component that processes common input commands for a {@link Survivor}.
 * 
 * <p>
 * This component maps keyboard inputs to movement or shooting actions,
 * updating the survivor's velocity and state accordingly.
 * </p>
 * 
 * <p>
 * Movement keys: W, A, S, D and no input (idle).
 * </p>
 * <p>
 * Shooting keys: Arrow keys (up, down, left, right).
 * </p>
 */
public class InputCommonComponent implements InputSurvivorComponent {

    private static final Pair<Double, Double> WORLD_LEFT_DIRECTION = Pair.of(-1d, 0d);
    private static final Pair<Double, Double> WORLD_RIGHT_DIRECTION = Pair.of(1d, 0d);
    private static final Pair<Double, Double> WORLD_UP_DIRECTION = Pair.of(0d, 1d);
    private static final Pair<Double, Double> WORLD_DOWN_DIRECTION = Pair.of(0d, -1d);

    private final Map<Integer, Consumer<Survivor>> movementActions = new HashMap<>();
    private final Map<Integer, Consumer<Survivor>> shootingActions = new HashMap<>();

    /**
     * Constructs a new {@code InputCommonComponent} and initializes
     * the mappings from input codes to survivor actions.
     */
    public InputCommonComponent() {
        movementActions.put(KeyCodes.KEY_W.getKeyCode(),
                s -> moveSurvivor(s, WORLD_UP_DIRECTION, SurvivorState.SURVIVOR_MOVE_UP));
        movementActions.put(KeyCodes.KEY_S.getKeyCode(),
                s -> moveSurvivor(s, WORLD_DOWN_DIRECTION, SurvivorState.SURVIVOR_MOVE_DOWN));
        movementActions.put(KeyCodes.KEY_A.getKeyCode(),
                s -> moveSurvivor(s, WORLD_LEFT_DIRECTION, SurvivorState.SURVIVOR_MOVE_LEFT));
        movementActions.put(KeyCodes.KEY_D.getKeyCode(),
                s -> moveSurvivor(s, WORLD_RIGHT_DIRECTION, SurvivorState.SURVIVOR_MOVE_RIGHT));
        movementActions.put(KeyCodes.NONE.getKeyCode(), s -> {
            s.setVelocity(Pair.of(0d, 0d));
            s.setState(SurvivorState.SURVIVOR_IDLE);
        });

        shootingActions.put(KeyCodes.ARROW_UP.getKeyCode(),
                s -> shootInDirection(s, WORLD_UP_DIRECTION, SurvivorState.SURVIOR_SHOOT_UP));
        shootingActions.put(KeyCodes.ARROW_DOWN.getKeyCode(),
                s -> shootInDirection(s, WORLD_DOWN_DIRECTION, SurvivorState.SURVIOR_SHOOT_DOWN));
        shootingActions.put(KeyCodes.ARROW_LEFT.getKeyCode(),
                s -> shootInDirection(s, WORLD_LEFT_DIRECTION, SurvivorState.SURVIOR_SHOOT_LEFT));
        shootingActions.put(KeyCodes.ARROW_RIGHT.getKeyCode(),
                s -> shootInDirection(s, WORLD_RIGHT_DIRECTION, SurvivorState.SURVIOR_SHOOT_RIGHT));
    }

    /**
     * Updates the given survivor based on the current input code from the
     * controller.
     * 
     * <p>
     * If the input corresponds to a movement or shooting command, the appropriate
     * action is issued to the survivor.
     * </p>
     * 
     * @param sur  the survivor to update
     * @param ctrl the input controller providing the current input code
     */
    @Override
    public void update(final Survivor sur, final InputController ctrl) {
        int inputCode = ctrl.getInputCode();

        if (movementActions.containsKey(inputCode)) {
            CommandSurvivor.issue(sur, movementActions.get(inputCode));
        } else if (shootingActions.containsKey(inputCode)) {
            CommandSurvivor.issue(sur, shootingActions.get(inputCode));
        }
    }

    /**
     * Helper method to move the survivor in a given direction with the survivor's
     * base velocity,
     * and update the survivor's state.
     * 
     * @param s     the survivor to move
     * @param dir   the direction vector (normalized or unit vector)
     * @param state the new survivor state to set
     */
    private void moveSurvivor(final Survivor s, final Pair<Double, Double> dir, final SurvivorState state) {
        s.setVelocity(PairUtils.mulScale(dir, PairUtils.module(s.getBaseSurvivorVel())));
        s.setState(state);
    }

    /**
     * Helper method to make the survivor shoot in a given direction.
     * The survivor's velocity is set to zero and state updated accordingly.
     * The survivor's weapon is aimed in the specified direction from the current
     * position.
     * 
     * @param s     the survivor shooting
     * @param dir   the direction to aim and shoot
     * @param state the new survivor state to set (shooting state)
     */
    private void shootInDirection(final Survivor s, final Pair<Double, Double> dir, final SurvivorState state) {
        s.setState(state);
        s.setVelocity(Pair.of(0d, 0d));
        s.getWeapon().aim(dir, s.getCurrentPos());
    }
}
