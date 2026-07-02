package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.utility.viewparts.VerticalLabelCheckBoxFieldBox;
import com.biaren.utility.viewparts.VerticalLabelTextFieldBox;

import javafx.scene.layout.Pane;

public class SportVenueFormPanel extends FacilityFormPanel {

    private VerticalLabelTextFieldBox totalSeats;
    private VerticalLabelTextFieldBox fieldLength;
    private VerticalLabelTextFieldBox fieldWidth;
    private VerticalLabelCheckBoxFieldBox<Boolean> indoor;
    
    public SportVenueFormPanel(final String title, final Pane parentView) {
        super(title, parentView);
        this.totalSeats = new VerticalLabelTextFieldBox("Total Seats");
        this.fieldLength = new VerticalLabelTextFieldBox("Field Length");
        this.fieldWidth = new VerticalLabelTextFieldBox("Field Width");
        this.indoor = new VerticalLabelCheckBoxFieldBox<>("Is Indoor?", new Boolean[] {true, false});
        this.setAdditionalLayout();
    }
    
     protected void setAdditionalLayout() {
         this.getChildren().addAll(this.totalSeats, this.fieldLength, this.fieldWidth, this.indoor);
     }
    
}
