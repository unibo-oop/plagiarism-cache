package oop.lit.model.simplegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.groups.OrderedSelectableGroup;
import oop.lit.model.simplegame.actions.GroupActionFactory;
import oop.lit.model.simplegame.elements.SimpleElement;

/**
 * A SelectableGroup of SimpleElements.
 *
 * @param <H>
 *      the type of elements held.
 */
public class SimpleSelectableGroup<H extends SimpleElement> extends OrderedSelectableGroup<H> {
    /**
     * 
     */
    private static final long serialVersionUID = 8085852989517407555L;
    private final GroupActionFactory actionFactory;

    /**
     * @param name
     *      the group name.
     * @param actionFactory
     *      the actionFactory for the elements contained in the group.
     */
    public SimpleSelectableGroup(final Optional<String> name, final GroupActionFactory actionFactory) {
        super(name);
        this.actionFactory = actionFactory;
    }

    @Override
    public List<Action> getSelectedActions(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        final List<Action> res = new ArrayList<>();
        if (this.getMoveToHeadAction().canBePerformed()) {
            res.add(this.getMoveToHeadAction());
        }
        if (this.getMoveToTailAction().canBePerformed()) {
            res.add(this.getMoveToTailAction());
        }
        res.addAll(actionFactory.getGroupActions(this, playingPlayer, turnPlayers));
        return res;
    }

}
