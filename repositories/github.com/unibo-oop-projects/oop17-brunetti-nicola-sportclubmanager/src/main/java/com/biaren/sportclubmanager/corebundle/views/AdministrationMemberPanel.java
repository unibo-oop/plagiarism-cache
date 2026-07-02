package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.sportclubmanager.corebundle.model.AdministrationMember;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.viewutil.AdministrationTableUtil;
import javafx.scene.control.TableRow;

/**
 * Represents a panel view for {@link AdministrationMember}.
 * Implements {@link BaseListView} to get a view of entities.
 * @author nbrunetti
 *
 */
public class AdministrationMemberPanel extends BaseListView<AdministrationMember> {

    /**
     * Creates a {@link AdministrationMemberPanel}
     */
    public AdministrationMemberPanel() {
        super("Società");
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
    protected void rowClickEvent(final TableRow<AdministrationMember> row) {
        try {
            final AdministrationMember rowData = row.getItem();
            this.showModal(this.controller.viewEntityActionHandler(rowData));
        } catch (NullPointerException e) {
            //Do nothing: click on an empty row
        }
    }
    
    @Override
    protected void updateTable() {
        this.table.getColumns().addAll(AdministrationTableUtil.tableColumnList());
        this.table.getItems().addAll(BaseModelImpl.getInstance().getAdministrationMembersList());
    }
}
