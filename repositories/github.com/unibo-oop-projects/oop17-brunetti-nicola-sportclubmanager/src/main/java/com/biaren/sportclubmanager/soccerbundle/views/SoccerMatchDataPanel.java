package com.biaren.sportclubmanager.soccerbundle.views;

import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.views.BaseListView;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerMatchData;
import com.biaren.sportclubmanager.soccerbundle.viewutil.SoccerGameMatchTableUtil;
import javafx.scene.control.TableRow;

/**
 * Represent a panel view for {@link SoccerMatchData} list
 * @author nbrunetti
 *
 */
public class SoccerMatchDataPanel extends BaseListView<SoccerMatchData> {
    
    /**
     * Creates a new {@link SoccerMatchDataPanel}
     */
    public SoccerMatchDataPanel() {
        super("Partite");
        this.updateTable();
        this.setLayout();
    }
    
    /**
     * Set panel layout
     */
    protected void setLayout() {
        super.setLayout();
    }
    
    @Override
    protected void rowClickEvent(final TableRow<SoccerMatchData> row) {
        try {
            final SoccerMatchData rowData = row.getItem();
            this.showModal(this.controller.viewEntityActionHandler(rowData));
        } catch (NullPointerException e) {
            //Do nothing: click on an empty row
        }
    }
    
    @Override
    protected void updateTable() {
        this.table.getColumns().addAll(SoccerGameMatchTableUtil.tableColumnList());
        this.table.getItems().addAll(BaseModelImpl.getInstance().getMatches());
    }
}
