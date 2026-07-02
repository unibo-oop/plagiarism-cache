package view.menu;

import common.MsgStrings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.BackgroundMusic;
import view.GenericView;

/**
 * This class create a contrete AudioEventBusButton that send a MsgEvent on the
 * given view controller when is pressed. When is pressed, the button image is
 * changed.
 */
public class AudioEventButton extends AbstractEventButton {

    private final Image FIRST = new Image("images/sound.png", 25, 25, true, true);
    private final Image SECOND = new Image("images/mute.png", 25, 25, true, true);

    public AudioEventButton(final GenericView view) {
        super(view, MsgStrings.SWITCH_AUDIO_MODE);
        super.getButton().setGraphic(new ImageView(BackgroundMusic.getInstance().isOn() ? FIRST : SECOND));
    }

    private void chooseImageBtn() {
        super.getButton().setGraphic(new ImageView(BackgroundMusic.getInstance().isOn() ? SECOND : FIRST));
    }

    @Override
    public void setAction() {
        this.chooseImageBtn();
    }
}
