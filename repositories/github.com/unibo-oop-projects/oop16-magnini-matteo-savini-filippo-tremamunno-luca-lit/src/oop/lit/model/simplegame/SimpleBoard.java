package oop.lit.model.simplegame;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.groups.AbstractBoard;
import oop.lit.model.simplegame.actions.GroupActionFactory;
import oop.lit.model.simplegame.elements.GroupSBE;
import oop.lit.model.simplegame.elements.SimpleBoardElement;

/**
 * A board of simple board elements.
 */
public class SimpleBoard extends AbstractBoard<SimpleBoardElement> {
    /**
     * 
     */
    private static final long serialVersionUID = 7902205310565648230L;
    private final GroupActionFactory actionFactory;

    /**
     * @param actionFactory
     *      the group action factory for the board
     */
    public SimpleBoard(final GroupActionFactory actionFactory) {
        Objects.requireNonNull(actionFactory);
        this.actionFactory = actionFactory;
    }

    /**
     * @return
     *      A list containing all the group board elements in this.
     */
    public Set<GroupSBE> getGroups() {
        return this.getElements().stream().filter(element -> element instanceof GroupSBE)
                .map(element -> (GroupSBE) element).collect(Collectors.toSet());
    }

    @Override
    public List<Action> getSelectedActions(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        return actionFactory.getGroupActions(this, playingPlayer, turnPlayers);
    }

}
