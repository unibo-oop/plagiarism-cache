package com.biaren.sportclubmanager.soccerbundle.viewutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerMatchData;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Utility class for {@link TableView}. Provides static methods to get {@link TableColumn} with
 * data from entities.
 * @author nbrunetti
 *
 */
public class SoccerGameMatchTableUtil {

    /**
     * Get {@link TableColumn} for {@link SoccerMatchData} match date
     * @return table column of specified field
     */
    public static TableColumn<SoccerMatchData, LocalDate> getDateColumn() {
        TableColumn<SoccerMatchData, LocalDate> dateCol = new TableColumn<>("Data");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        return dateCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerMatchData} home team
     * @return table column of specified field
     */
    public static TableColumn<SoccerMatchData, String> getHomeTeamGoalColumn() {
        TableColumn<SoccerMatchData, String> homeTeamGoalCol = new TableColumn<>("Squadra Casa");
        homeTeamGoalCol.setCellValueFactory(cellData -> {
            SoccerMatchData s = cellData.getValue();
            return new ReadOnlyStringWrapper(s.getHomeTeam() + ": " + s.getHomeTeamGoal());
        });
        return homeTeamGoalCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerMatchData} away team
     * @return table column of specified field
     */
    public static TableColumn<SoccerMatchData, String> getAwayTeamGoalColumn() {
        TableColumn<SoccerMatchData, String> awayTeamGoalCol = new TableColumn<>("Squadra Ospite");
        awayTeamGoalCol.setCellValueFactory(cellData -> {
            SoccerMatchData s = cellData.getValue();
            return new ReadOnlyStringWrapper(s.getAwayTeam() + ": " + s.getAwayTeamGoal());
        });
        return awayTeamGoalCol;
    }
    
    /**
     * Get {@link TableColumn} list
     * @return table column list of all specified fields
     */
    public static List<TableColumn<SoccerMatchData, ?>> tableColumnList() {
        List<TableColumn<SoccerMatchData, ?>> list = new ArrayList<>();
        
        list.add(getDateColumn());
        list.add(getHomeTeamGoalColumn());
        list.add(getAwayTeamGoalColumn());
        return list;
    }
}
