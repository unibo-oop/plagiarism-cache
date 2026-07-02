package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.model.SponsorImpl;
import com.biaren.sportclubmanager.corebundle.viewutil.SponsorTableUtil;
import javafx.scene.control.TableRow;

/**
 * Represent a panel view for {@link SponsorPanel}.
 * Implements {@link BaseListView} to get a view of entities.
 * @author nbrunetti
 *
 */
public class SponsorPanel extends BaseListView<SponsorImpl> {

    /**
     * Creates a {@link SponsorPanel}
     */
    public SponsorPanel() {
        super("Sponsor");
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
    protected void rowClickEvent(final TableRow<SponsorImpl> row) {
        try {
            final SponsorImpl rowData = row.getItem();
            this.showModal(this.controller.viewEntityActionHandler(rowData));
        } catch (NullPointerException e) {
            //Do nothing: click on an empty row
        }
        
    }
    
    @Override
    protected void updateTable() {
        this.table.getColumns().addAll(SponsorTableUtil.tableColumnList());
        this.table.getItems().addAll(BaseModelImpl.getInstance().getSponsor());
    }
}
