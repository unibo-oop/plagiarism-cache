package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.model.FacilityImpl;
import com.biaren.sportclubmanager.corebundle.viewutil.FacilityTableUtil;
import javafx.scene.control.TableRow;

/**
 * Represents a panel view for {@link FacilityImpl}.
 * Implements {@link BaseListView} to get a view of entities.
 * @author nbrunetti
 *
 */
public class FacilityPanel extends BaseListView<FacilityImpl> {

    /**
     * Creates a {@link FacilityPanel}
     */
    public FacilityPanel() {
        super("Strutture");
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
    protected void rowClickEvent(final TableRow<FacilityImpl> row) {
        try {
            final FacilityImpl rowData = row.getItem();
            this.showModal(this.controller.viewEntityActionHandler(rowData));
        } catch (NullPointerException e) {
            //Do nothing: click on an empty row
        }
        
    }
    
    @Override
    protected void updateTable() {
        this.table.getColumns().addAll(FacilityTableUtil.tableColumnList());
        this.table.getItems().addAll(BaseModelImpl.getInstance().getFacilitiesList());
    }
}
