package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerMatchData;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Basic panel with team statistics
 * @author nbrunetti
 *
 */
public class StatisticsPanel extends VBox {

    final Label madeGoals;
    final Label concedeGoals;
    final Label warnings;
    final Label expulsions;
    
    /**
     * Creates a {@link StatisticsPanel}
     */
    public StatisticsPanel() {
        this.madeGoals = new Label();
        this.concedeGoals = new Label();
        this.warnings = new Label();
        this.expulsions = new Label();
        this.setLabelsText();
        this.setLayout();
    }
    
    private void setLabelsText() {
        this.madeGoals.setText("Totale goal fatti: " + SoccerMatchData.getTotalMadeGoals(BaseModelImpl.getInstance().getMatchesList()));
        this.concedeGoals.setText("Totale goal subiti: " + SoccerMatchData.getTotalConcededGoal(BaseModelImpl.getInstance().getMatchesList()));
        this.warnings.setText("Totale ammonizioni: " + SoccerMatchData.getTotalWarnings(BaseModelImpl.getInstance().getMatchesList()));
        this.expulsions.setText("Totale espulsioni: " + SoccerMatchData.getTotalExpulsion(BaseModelImpl.getInstance().getMatchesList()));
    }
    
    private void setLayout() {
        this.getChildren().addAll(this.madeGoals, this.concedeGoals, this.warnings, this.expulsions);
    }
}
