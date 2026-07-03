package oop.lit.model.simplegame.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.actions.AbstractAction;
import oop.lit.model.groups.SelectableElementGroup;
import oop.lit.model.simplegame.elements.BasicSBE;
import oop.lit.model.simplegame.elements.FlippableSBE;
import oop.lit.model.simplegame.elements.GroupSBE;
import oop.lit.model.simplegame.elements.SimpleElement;
import oop.lit.model.simplegame.elements.SimpleElement.GroupActionTypes;
import oop.lit.model.simplegame.elements.actions.FlippableSBEActionFactory;
import oop.lit.model.simplegame.elements.actions.GroupSBEActionFactory;
import oop.lit.util.CollectionsUtils;
import oop.lit.util.IllegalInputException;

/**
 * A GroupActionFactory implementation.
 */
public class GroupActionFactoryImpl implements GroupActionFactory {
    /**
     * 
     */
    private static final long serialVersionUID = 2582310598307525079L;
    private final FlippableSBEActionFactory flippableFactory;
    private final GroupSBEActionFactory groupFactory;

    /**
     * @param flippableFactory
     *      a FlippableSBEActionFactory.
     * @param groupFactory
     *      a GroupSBEActionFactory.
     */
    public GroupActionFactoryImpl(final FlippableSBEActionFactory flippableFactory, final GroupSBEActionFactory groupFactory) {
        super();
        this.flippableFactory = flippableFactory;
        this.groupFactory = groupFactory;
    }

    @Override
    public List<Action> getGroupActions(final SelectableElementGroup<? extends SimpleElement> group,
            final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        final List<? extends SimpleElement> selectedElements = group.getSelected();
        if (selectedElements.isEmpty()) {
            return Collections.emptyList();
        }

        final Optional<Set<GroupActionTypes>> groupActions = CollectionsUtils.intersection(selectedElements.stream()
                .map(e -> e.getPossibleGroupActionsTypes(playingPlayer, turnPlayers)).collect(Collectors.toList()));

        if (selectedElements.size() != 1 && (!groupActions.isPresent() || groupActions.get().isEmpty())) {
            return Collections.emptyList();
        }
        final List<Action> res = new ArrayList<>();
        if (selectedElements.size() == 1) {
            res.addAll(selectedElements.get(0).getActions(playingPlayer, turnPlayers));
        } else {
            final List<BasicSBE> basicElements = selectedElements.stream().filter(e -> e instanceof BasicSBE)
                    .map(e -> (BasicSBE) e).collect(Collectors.toList());
            final List<FlippableSBE> flippableElements = basicElements.stream().filter(e -> e instanceof FlippableSBE)
                    .map(e -> ((FlippableSBE) e)).collect(Collectors.toList());
            final List<GroupSBE> groupElements = selectedElements.stream().filter(e -> e instanceof GroupSBE)
                    .map(e -> (GroupSBE) e).collect(Collectors.toList());

            if (groupActions.get().contains(GroupActionTypes.SEND_TO_HAND)) {
                res.add(flippableFactory.getSendToHandAction(basicElements));
            }
            if (groupActions.get().contains(GroupActionTypes.SEND_TO_BOARD)) {
                res.add(flippableFactory.getSendToBoardAction(basicElements));
            }
            if (groupActions.get().contains(GroupActionTypes.SEND_TO_GROUP_TOP)) {
                res.add(flippableFactory.getSendToGroupTopAction(basicElements));
            }
            if (groupActions.get().contains(GroupActionTypes.SEND_TO_GROUP_BOTTOM)) {
                res.add(flippableFactory.getSendToGroupBottomAction(basicElements));
            }
            if (groupActions.get().contains(GroupActionTypes.SEND_TO_GROUP_RANDOM)) {
                res.add(flippableFactory.getSendToGroupRandomAction(basicElements));
            }
            if (groupActions.get().contains(GroupActionTypes.SEND_TO_GROUP_SPECIFIC)) {
                res.add(flippableFactory.getSendToGroupSpecificAction(basicElements));
            }
            if (groupActions.get().contains(GroupActionTypes.FLIP)) {
                res.add(flippableFactory.getFlipAction(flippableElements));
            }
            if (groupActions.get().contains(GroupActionTypes.SHUFFLE)) {
                res.add(groupFactory.getShuffleAction(groupElements));
            }
        }

        //Aggiungo azioni che posso fare sugli elementi solo dal gruppo.
        if (groupActions.get().contains(GroupActionTypes.REMOVE)) {
            res.add(new AbstractAction("Remove") { //removes an element from the group and calls its remove method.
                @Override
                public void perform() throws IllegalInputException {
                    selectedElements.forEach(element -> {
                        group.removeElement(element);
                        element.removed();
                    });
                }
            });
        }

        return res;
    }
}
