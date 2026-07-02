package model.messages;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class used to show the release of an achievement.
 */
public class AchievementsUnlock {

    /**
     * Constructor of the class.
     * @param name
     *          name of the achievement 
     */
    public AchievementsUnlock(final String name) {
        final Alert infoMBOX = new Alert(AlertType.INFORMATION);
        infoMBOX.setTitle("ACHIEVEMENT SBLOCCATO!");
        infoMBOX.setHeaderText("Nuovo Achievement!");
        infoMBOX.setContentText("Hai sbloccato: \n" + name + "!");
        infoMBOX.show();
    }

}
