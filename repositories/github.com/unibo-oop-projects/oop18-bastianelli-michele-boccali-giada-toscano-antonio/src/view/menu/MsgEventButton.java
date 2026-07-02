package view.menu;

import view.GenericView;

/**
 * This class create a concrete MsgEventButton that send a MsgEvent on the given
 * view controller when is pressed. It don't add other action but set the
 * message as Button text.
 */
public class MsgEventButton extends AbstractEventButton {

    public MsgEventButton(final GenericView view, final String msgString) {
        super(view, msgString);
        this.getButton().setText(msgString);
    }

    @Override
    public void setAction() { }

}
