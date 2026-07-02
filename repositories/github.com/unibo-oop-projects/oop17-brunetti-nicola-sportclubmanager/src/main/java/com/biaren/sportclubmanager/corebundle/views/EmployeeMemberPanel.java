package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.model.EmployeeMember;
import com.biaren.sportclubmanager.corebundle.viewutil.EmployeeTableUtil;
import javafx.scene.control.TableRow;

/**
 * Represents a panel view for {@link EmployeeMember}.
 * Implements {@link BaseListView} to get a view of entities.
 * @author nbrunetti
 *
 */
public class EmployeeMemberPanel extends BaseListView<EmployeeMember> {

    /**
     * Creates a {@link EmployeeMemberPanel}
     */
    public EmployeeMemberPanel() {
        super("Dipendenti");
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
    protected void rowClickEvent(final TableRow<EmployeeMember> row) {
        try {
            final EmployeeMember rowData = row.getItem();
            this.showModal(this.controller.viewEntityActionHandler(rowData));
        } catch (NullPointerException e) {
            //Do nothing: click on an empty row
        }
    }

    @Override
    protected void updateTable() {
        this.table.getColumns().addAll(EmployeeTableUtil.tableColumnList());
        this.table.getItems().addAll(BaseModelImpl.getInstance().getEmployeesMembersList());
    }
}
