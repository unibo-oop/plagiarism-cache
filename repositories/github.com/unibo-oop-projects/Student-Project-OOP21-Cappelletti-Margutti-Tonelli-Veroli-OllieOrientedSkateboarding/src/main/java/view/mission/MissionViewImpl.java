package view.mission;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.mission.MissionManager;

/**
 * 
 * Implementation of {@link MissionView}.
 *
 */
public class MissionViewImpl implements MissionView {

    private static final double FONT_SIZE = 20;
    private static final double MISSION_TEXT_X = 400;
    private static final double MISSION_TEXT_Y = 30;
    private final Pane pane;
    private final MissionManager missionManager;

    /**
     * Creates a new MissionViewImpl.
     * @param pane the {@link Pane}.
     * @param missionManager the {@link MissionManager}.
     */
    public MissionViewImpl(final Pane pane, final MissionManager missionManager) {
        super();
        this.pane = pane;
        this.missionManager = missionManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        final Text missionText = new Text(missionManager.getMission().get().toString());
        missionText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FONT_SIZE));
        missionText.setX(MISSION_TEXT_X);
        missionText.setY(MISSION_TEXT_Y);
        this.pane.getChildren().add(missionText);
    }

}
