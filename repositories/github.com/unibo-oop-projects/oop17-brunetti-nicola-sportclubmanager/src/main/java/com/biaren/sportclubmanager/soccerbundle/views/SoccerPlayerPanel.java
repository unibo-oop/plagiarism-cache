package com.biaren.sportclubmanager.soccerbundle.views;

import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.views.BaseListView;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerPlayer;
import com.biaren.sportclubmanager.soccerbundle.viewutil.SoccerPlayerTableUtil;
import javafx.scene.control.TableRow;

/**
 * Represents a panel view for {@link SoccerPlayer} list
 * @author nbrunetti
 *
 */
public class SoccerPlayerPanel extends BaseListView<SoccerPlayer> {
    
    /**
     * Crates a new {@link SoccerPlayerPanel}
     */
    public SoccerPlayerPanel() {
        super("Giocatori");
        this.updateView();
        this.setLayout();
    }
    
    /**
     * Set panel layout
     */
    protected void setLayout() {
        super.setLayout();
    }

    @Override
    protected void rowClickEvent(final TableRow<SoccerPlayer> row) {
        try {
            final SoccerPlayer rowData = row.getItem();
            this.showModal(this.controller.viewEntityActionHandler(rowData));
        } catch (NullPointerException e) {
            //Do nothing: click on an empty row
        }
    }
    
    @Override
    protected void updateTable() {
        this.table.getColumns().addAll(SoccerPlayerTableUtil.tableColumnList());
        this.table.getItems().addAll(BaseModelImpl.getInstance().getPlayers());
    }
}
