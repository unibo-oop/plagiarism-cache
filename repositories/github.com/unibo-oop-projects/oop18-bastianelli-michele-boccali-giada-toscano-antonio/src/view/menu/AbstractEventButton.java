package view.menu;

import javafx.scene.control.Button;
import view.GenericView;

/**
 * This class create an abstract AbstractEventBusButton that send a MsgEvent on
 * the given view controller when is pressed. The abstract method setAction must
 * be implemented to do something before event is post to the bus.
 */
public abstract class AbstractEventButton {

    private final Button b;
    private final String msgString;

    public AbstractEventButton(final GenericView view, final String msgString) {
        this.msgString = msgString;
        b = new Button();
        b.setOnAction(e -> {
            this.setAction();
            view.getController().sendMsg(this.getMsg());
        });
    }

    public abstract void setAction();

    public Button getButton() {
        return b;
    }

    public String getMsg() {
        return msgString;
    }
}
