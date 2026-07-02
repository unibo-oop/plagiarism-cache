package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.sportclubmanager.corebundle.model.StaffMember;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.viewutil.StaffTableUtil;
import javafx.scene.control.TableRow;

/**
 * Represents a panel view for {@link StaffMember}.
 * Implements {@link BaseListView} to get a view of entities.
 * @author nbrunetti
 *
 */
public class StaffMemberPanel extends BaseListView<StaffMember> {

    /**
     * Creates a {@link StaffMemberPanel}
     */
    public StaffMemberPanel() {
        super("Staff");
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
    protected void rowClickEvent(final TableRow<StaffMember> row) {
        try {
            final StaffMember rowData = row.getItem();
            this.showModal(this.controller.viewEntityActionHandler(rowData));
        } catch (NullPointerException e) {
            //Do nothing: click on an empty row
        }
    }
    
    @Override
    protected void updateTable() {
        this.table.getColumns().addAll(StaffTableUtil.tableColumnList());
        this.table.getItems().addAll(BaseModelImpl.getInstance().getStaff());
    }
}
