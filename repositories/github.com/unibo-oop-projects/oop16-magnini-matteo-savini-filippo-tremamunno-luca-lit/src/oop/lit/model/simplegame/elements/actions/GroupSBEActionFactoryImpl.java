package oop.lit.model.simplegame.elements.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import oop.lit.model.Action;
import oop.lit.model.actions.AbstractAction;
import oop.lit.model.actions.Actions;
import oop.lit.model.groups.GroupViewer;
import oop.lit.model.simplegame.SimplePlayerManager;
import oop.lit.model.simplegame.elements.GroupSBE;
import oop.lit.util.IllegalInputException;
import oop.lit.util.InputRequest;
import oop.lit.util.InputRequestsFactory;

/**
 * A GroupSBEActionFactory implementation.
 */
public class GroupSBEActionFactoryImpl extends SBEActionFactoryImpl implements GroupSBEActionFactory {
    /**
     * 
     */
    private static final long serialVersionUID = 4213985482502009519L;
    private final GroupViewer groupViewer;

    /**
     * @param pManager
     *      this game player manager.
     * @param groupViewer
     *      this game group viewer.
     */
    public GroupSBEActionFactoryImpl(final SimplePlayerManager pManager, final GroupViewer groupViewer) {
        super(pManager);
        this.groupViewer = groupViewer;
    }

    @Override
    public Action getShowGroupAction(final GroupSBE element) {
        return new AbstractAction("See group content") {
            @Override
            public boolean canBePerformed() {
                return element.getSelectableElementGroupModel().isVisualizable();
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                groupViewer.showGroup(element.getSelectableElementGroupModel());
            }
        };
    }
    @Override
    public Action getUseGroupAction(final GroupSBE element) {
        return new AbstractAction("Use group content") {
            @Override
            public boolean canBePerformed() {
                return element.getSelectableElementGroupModel().isVisualizable();
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                groupViewer.showSelectable(element.getSelectableElementGroupModel());
            }
        };
    }
    @Override
    public Action getShuffleAction(final Collection<GroupSBE> elements) {
        return new AbstractAction("Shuffle") {
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                elements.forEach(GroupSBE::shuffle);
            }
        };
    }
    @Override
    public Action getDrawFromTopAction(final GroupSBE element) {
        return new DrawAction("Draw from top", element) {
            @Override
            protected int getPosition() throws IllegalInputException {
                return 0;
            }
        };
    }
    @Override
    public Action getDrawFromBottomAction(final GroupSBE element) {
        return new DrawAction("Draw from bottom", element) {
            @Override
            protected int getPosition() throws IllegalInputException {
                return element.getContainedElementsSize() - 1;
            }
        };
    }
    @Override
    public Action getDrawRandomAction(final GroupSBE element) {
        return new DrawAction("Draw random", element) {
            private final Random ran = new Random(System.nanoTime());
            @Override
            protected int getPosition() throws IllegalInputException {
                return ran.nextInt(element.getContainedElementsSize());
            }
        };
    }
    @Override
    public Action getDrawSpecificAction(final GroupSBE element) {
        return new DrawAction("Draw specific", element) {
            private Optional<InputRequest<Integer>> positionRequest = Optional.empty();
            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.checkPerformable();
                final List<InputRequest<?>> res = new ArrayList<>(super.getRequests(irFactory));
                this.positionRequest = Optional.of(irFactory.getIntInputRequest("position", Optional.empty()));
                res.add(this.positionRequest.get());
                return res;
            }
            @Override
            protected int getPosition() throws IllegalInputException {
                final Integer position = Actions.checkPresentAndGet(this.positionRequest);
                if (position < 1 || position > (element.getContainedElementsSize() + 1)) {
                    throw new IllegalInputException("Invalid value for \"" + positionRequest.get().getLabel() + "\";\n"
                            + "Value must be between 1 and " + (element.getContainedElementsSize() + 1));
                }
                this.positionRequest = Optional.empty();
                return position - 1;
            }
        };
    }

    private abstract static class DrawAction extends AbstractAction {
        private final GroupSBE element;
        protected DrawAction(final String label, final GroupSBE element) {
            super(label);
            this.element = element;
        }
        @Override
        public boolean canBePerformed() {
            return element.getContainedElementsSize() > 0;
        }
        @Override
        public void perform() throws IllegalInputException {
            this.checkPerformable();
            this.element.draw(this.getPosition());
        }
        protected abstract int getPosition() throws IllegalInputException;
    }
}
