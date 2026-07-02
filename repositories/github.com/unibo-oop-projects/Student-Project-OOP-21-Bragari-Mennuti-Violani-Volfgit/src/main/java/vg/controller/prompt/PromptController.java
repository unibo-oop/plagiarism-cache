package vg.controller.prompt;

import vg.controller.Controller;
import vg.view.ViewManager;
import vg.view.menu.prompt.PromptView;
import vg.view.utils.KeyAction;

/**
 * Controller of {@link PromptView} that manage buttons selection and communicate it to parent controller that launched the PromptView.
 */
public class PromptController extends Controller<PromptView> {
    /**
     * Prompt selection. By default is {@link PromptOption#DENY}
     */
    private PromptOption selection = PromptOption.DENY;

    /**
     * CLass that need and wants to know the selection when is confirmed.
     */
    private final PromptObserver promptObserver;

    public PromptController(final PromptView view,
                            final ViewManager viewManager,
                            final PromptObserver promptObserver) {
        super(view, viewManager);
        this.promptObserver = promptObserver;
        this.selectDeny();
    }

    /**
     * Communicate selection o to observer.
     */
    private void applySelection() {
        this.promptObserver.notifyPromptAnswer(this.selection);
    }

    /**
     * Highlight confirm button then set selection to CONFIRM.
     */
    private void selectConfirm() {
        this.getView().selectConfirm();
        this.selection = PromptOption.CONFIRM;
    }

    /**
     * Highlight deny button then set selection to DENY.
     */
    private void selectDeny() {
        this.getView().selectDeny();
        this.selection = PromptOption.DENY;
    }

    /**
     * There are only two buttons and the apply one so this method
     * handles only {@link KeyAction#LEFT}, {@link KeyAction#RIGHT} and {@link KeyAction#ENTER} actions.
     * @param k {@link KeyAction}
     */
    @Override
    public void keyTapped(final KeyAction k) {
        switch (k) {
            case LEFT: selectConfirm(); break;
            case RIGHT: selectDeny(); break;
            case ENTER: applySelection(); break;
            default:
        }
    }

    /**
     * Redirect action to {@link PromptController#keyTapped(KeyAction)}.
     * @param k {@link KeyAction}
     */
    @Override
    public void keyPressed(final KeyAction k) {
        keyTapped(k);
    }

    /**
     * Unused. Is does nothing.
     * @param k {@link KeyAction}
     */
    @Override
    public void keyReleased(final KeyAction k) {
    }
}
