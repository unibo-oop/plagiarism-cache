package oop.lit.model.simplegame.elements.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import oop.lit.model.Action;
import oop.lit.model.actions.AbstractAction;
import oop.lit.model.actions.Actions;
import oop.lit.model.simplegame.SimplePlayerHand;
import oop.lit.model.simplegame.SimplePlayerManager;
import oop.lit.model.simplegame.elements.BasicSBE;
import oop.lit.model.simplegame.elements.GroupSBE;
import oop.lit.util.CollectionsUtils;
import oop.lit.util.IllegalInputException;
import oop.lit.util.InputRequest;
import oop.lit.util.InputRequestsFactory;

/**
 * An action factory for a Basic simple board element.
 */
public class BasicSBEActionFactoryImpl extends SBEActionFactoryImpl implements BasicSBEActionFactory {
    /**
     * 
     */
    private static final long serialVersionUID = 944014331379816833L;
    private final SimplePlayerManager pManager;

    /**
     * @param pManager
     *      this game player manager.
     */
    public BasicSBEActionFactoryImpl(final SimplePlayerManager pManager) {
        super(pManager);
        this.pManager = pManager;
    }

//SEND TO GROUP ACTIONS
    @Override
    public Action getSendToGroupTopAction(final Collection<BasicSBE> elements) {
        return new SendToGroupAction("Send to group top", elements, this.pManager) {
            @Override
            protected int getPosition(final GroupSBE chosenGroup) throws IllegalInputException {
                return 0;
            }
        };
    }
    @Override
    public Action getSendToGroupBottomAction(final Collection<BasicSBE> elements) {
        return new SendToGroupAction("Send to group bottom", elements, this.pManager) {
            @Override
            protected int getPosition(final GroupSBE chosenGroup) throws IllegalInputException {
                return chosenGroup.getContainedElementsSize();
            }
        };
    }
    @Override
    public Action getSendToGroupRandomAction(final Collection<BasicSBE> elements) {
        return new SendToGroupAction("Send to group random position", elements, this.pManager) {
            private final Random ran = new Random(System.nanoTime());
            @Override
            protected int getPosition(final GroupSBE chosenGroup) throws IllegalInputException {
                return ran.nextInt(chosenGroup.getContainedElementsSize() + 1);
            }
        };
    }
    @Override
    public Action getSendToGroupSpecificAction(final Collection<BasicSBE> elements) {
        return new SendToGroupAction("Send to group specific position", elements, this.pManager) {
            private Optional<InputRequest<Integer>> positionIR = Optional.empty();
            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                final List<InputRequest<?>> requests = new ArrayList<>(super.getRequests(irFactory));
                this.positionIR = Optional.of(irFactory.getIntInputRequest("position", Optional.empty()));
                requests.add(this.positionIR.get());
                return requests;
            }
            @Override
            protected int getPosition(final GroupSBE chosenGroup) throws IllegalInputException {
                final int value = Actions.checkPresentAndGet(this.positionIR);
                if (value < 1 || value > chosenGroup.getContainedElementsSize() + 1) {
                    throw new IllegalInputException("Invalid value for \"" + this.positionIR.get().getLabel() + "\";\n"
                            + "Value must be between 1 and " + (chosenGroup.getContainedElementsSize() + 1));
                }
                this.positionIR = Optional.empty();
                return value - 1;
            }
        };
    }
//GET SEND TO HAND ACTION
    @Override
    public Action getSendToHandAction(final Collection<BasicSBE> elements) {
        return new AbstractAction("Send to hand") {
            private Optional<InputRequest<SimplePlayerHand>> handRequest;
            @Override
            public boolean canBePerformed() {
                return elements.stream().allMatch(BasicSBE::isOnBoard) //gli elementi sono nella board
                        && pManager.getPlayingPlayer().isPresent() //c'è un playingPlayer
                        && (pManager.getPlayerHand(pManager.getPlayingPlayer().get()).isPresent() //il playing player ha una mano associata o
                                || (pManager.getPlayingPlayer().get().isGM() //il player è gm e c'è almeno una mano associata ad un player.
                                        && !pManager.getAllPlayersHand().isEmpty()));
            }
            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.checkPerformable();
                if (pManager.getPlayingPlayer().get().isGM()) {
                    this.handRequest = Optional.of(irFactory.getChoiceInputRequest(
                            "Player",
                            CollectionsUtils.mapWithDifferentStrings(pManager.getAllPlayersHand().values(),
                                    hand -> hand.getGroupName().orElse("(no name)")),
                            Optional.empty()
                            ));
                    return Arrays.asList(this.handRequest.get());
                }
                this.handRequest = Optional.empty();
                return Collections.emptyList();
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
              //avendo fatto checkPerformable se canBePerformed funziona sono sicuro che ci sono elementi dentro gli optional.
                if (pManager.getPlayingPlayer().get().isGM() && !this.handRequest.isPresent()) {
                    throw new IllegalInputException("This action needs input");
                }
                if (this.handRequest.isPresent()) {
                    final SimplePlayerHand hand = Actions.checkPresentAndGet(this.handRequest);
                    elements.forEach(element -> element.sendToHand(hand));
                    this.handRequest = Optional.empty();
                } else {
                    final SimplePlayerHand playerHand = pManager.getPlayerHand(pManager.getPlayingPlayer().get()).get();
                    elements.forEach(element -> element.sendToHand(playerHand));
                }
            }
        };
    }
//GET SEND TO BOARD ACTION
    @Override
    public Action getSendToBoardAction(final Collection<BasicSBE> elements) {
        return new AbstractAction("Send to board") {
            @Override
            public boolean canBePerformed() {
                return elements.stream().allMatch(element -> !element.isOnBoard());
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                elements.forEach(BasicSBE::sendToBoard);
            }
        };

    }

    //classe basica per le send to group actions varie. Basta implementare il metodo getPosition (ed eventualmente aggiungere altre input request.
    private abstract static class SendToGroupAction extends AbstractAction {
        private final SimplePlayerManager pManager;
        private final Collection<BasicSBE> elements;
        private Optional<InputRequest<GroupSBE>> groupChoice;

        protected SendToGroupAction(final String label, final Collection<BasicSBE> elements,
                final SimplePlayerManager pManager) {
            super(label);
            this.pManager = pManager;
            this.elements = elements;
        }

        @Override
        public boolean canBePerformed() {
            if (!this.pManager.getPlayingPlayer().isPresent()) {
                return false;
            }
            return this.elements.stream().allMatch(BasicSBE::isOnBoard) && !this.getPossibleGroups().isEmpty();
        }
        @Override
        public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
            this.checkPerformable();
            this.groupChoice = Optional
                    .of(irFactory.getChoiceInputRequest("group",
                            CollectionsUtils.mapWithDifferentStrings(
                                    this.getPossibleGroups(),
                                    group -> group.getName().orElse("(no name")),
                            Optional.empty()));
            return Arrays.asList(this.groupChoice.get());
        }

        @Override
        public void perform() throws IllegalInputException {
            this.checkPerformable();
            final GroupSBE group = Actions.checkPresentAndGet(this.groupChoice);
            try {
                final int position = this.getPosition(group);
                this.elements.forEach(element -> element.sendToGroup(group, position));
            } catch (IllegalArgumentException e) {
                throw new IllegalInputException("position not valid; must be between 1 and "
                        + (group.getContainedElementsSize() + 1) + " (for the provided group)");
            }
            this.groupChoice = Optional.empty();
        }

        private Set<GroupSBE> getPossibleGroups() {
            final Optional<Set<GroupSBE>> groups = CollectionsUtils.intersection(
                    this.elements.stream().map(BasicSBE::getPossibleGroups).collect(Collectors.toList()));
            if (!groups.isPresent()) {
                return Collections.emptySet();
            }
            groups.get().removeIf(group -> !group.canPlayerAdd(
                        this.pManager.getPlayingPlayer().get(),
                        this.pManager.isPlayerTurn(this.pManager.getPlayingPlayer().get())
                    ));
            return groups.get();
        }

        protected abstract int getPosition(GroupSBE chosenGroup) throws IllegalInputException;
    };
}
