package model.ai;

import java.util.function.*;
import controller.command.*;
import model.player.Player;
import utilities.Utilities;

/**
 * Artificial Intelligence simulates Players' decisions, it is a Function from Player to Command.
 * Because of that, it does not have any type of memory, the decisions are taken with greedy algorithms.
 * Its level of Ability can be set in the constructor, by implementing your own ability tier you can program Ai's behavior.
 */
public class Ai implements Function<Player, Command> {

    private final Ability ability;

    /**
     * Allows to build a new Ai whose behavior is based on the given ability level.
     * @param ability
     *              the ability level
     */
    public Ai(final Ability ability) {
        Utilities.requireNonNull(ability);
        this.ability = ability;
    }

    /**
     * @inheritDoc
     */
    @Override
    public final Command apply(final Player p) {
        Utilities.requireNonNull(p);
        return ability.guessingCondition().test(p) ? new GuessCommand(ability.guessingFunction().apply(p))
                : new AskCommand(ability.askingFunction().apply(p));
    }

}
