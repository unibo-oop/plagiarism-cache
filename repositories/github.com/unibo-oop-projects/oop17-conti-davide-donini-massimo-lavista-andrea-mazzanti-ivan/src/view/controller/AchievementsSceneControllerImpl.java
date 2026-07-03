package view.controller;

import java.util.Map;
import java.util.Map.Entry;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.data.Achievement;
import model.data.AchievementType;

/**
 * Implements the scene for achievements.
 */
public class AchievementsSceneControllerImpl extends AbstractSecondarySceneController
        implements AchievementsSceneController {

    @FXML
    private GridPane achievementsTable;

    @Override
    public final void setAchievements(final Map<AchievementType, Achievement> achievements) {
        // create label for each achievement
        int i = 0;
        for (final Entry<AchievementType, Achievement> a : achievements.entrySet()) {
            final Label level = new Label("Lvl " + a.getValue().getLevelAchievement());
            final Label name = new Label(String.valueOf(a.getKey()));
            final Label value;
            if (a.getValue().getNextTargets().isPresent()) {
                value = new Label(a.getValue().getPlayerValue() + " / " + a.getValue().getNextTargets().get());
            } else {
                value = new Label("Completed");
            }
            // (node, columnIndex, rowIndex)
            this.achievementsTable.add(level, 1, i + 1);
            this.achievementsTable.add(name, 2, i + 1);
            this.achievementsTable.add(value, 3, i + 1);
            i++;
        }
    }

}
