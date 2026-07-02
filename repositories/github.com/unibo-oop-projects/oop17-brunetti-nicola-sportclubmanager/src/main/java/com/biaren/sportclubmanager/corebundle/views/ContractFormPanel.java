package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.utility.viewparts.VerticalLabelDatePickerBox;
import com.biaren.utility.viewparts.VerticalLabelTextFieldBox;

import javafx.scene.layout.VBox;

//-----------------------------------------------------
//NON UTILIZZATO PER SFORAMENTO ORE LAVORO
//-----------------------------------------------------

public class ContractFormPanel extends VBox {
    
    private VerticalLabelDatePickerBox init;
    private VerticalLabelDatePickerBox end;
    private VerticalLabelTextFieldBox salary;
    
    public ContractFormPanel() {
        this.init = new VerticalLabelDatePickerBox("Init Date");
        this.end = new VerticalLabelDatePickerBox("End Date");
        this.salary = new VerticalLabelTextFieldBox("Salary per Year");
        this.setLayout();
    }
    
    private void setLayout() {
        this.getChildren().addAll(this.init, this.end, this.salary);
    }
}
