package com.biaren.utility.viewparts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

//INUTILIZZATA, LA RESA GRAFICA NON ERA SODDISFACENTE E NON HO AVUTO TEMPO DI 
//SCRIVE IL CSS (ARGOMENTO FUORI CORSO)

/**
 * Multiple choice button. Simulate a checkbox with button instead of
 * classical squares to check.
 * @author nbrunetti
 *
 * @param <C> the type of data to handle
 */
public class VerticalMultipleButtonChoiceBox<C> extends VBox {
    
    private FlowPane box;
    private List<ToggleButton> toggleButtonList;
    private List<C> choices;
    private Label label;
   
    //debug
    private ToggleButton test = new ToggleButton("TEST");
    
    
    public VerticalMultipleButtonChoiceBox(final String labelName, final List<C> choices) {
        this.label = new Label(labelName);
        this.choices = choices;
        this.box = new FlowPane();
        this.toggleButtonList = this.generateButtonList();
        
        //proprietà buttons
        this.setButtonListProperties();

        this.setLayout();
    }
    
    public List<ToggleButton> getButtonList() {
        return new ArrayList<>(this.toggleButtonList);
    }
    
    public Label getLabel() {
        return this.label;
    }
    
    public List<ToggleButton> generateButtonList() {
        return choices.stream().map(e -> new ToggleButton(e.toString())).collect(Collectors.toList());
    }
    
    public List<ToggleButton> correctButtonList(final C choice) {
        return choices.stream().filter(b -> ! choice.equals(b)).map(e -> new ToggleButton(e.toString())).collect(Collectors.toList());
    }
    
    private void setLayout() {
        this.box.getChildren().addAll(this.toggleButtonList);
        this.getChildren().add(this.box);
        this.getChildren().add(this.test);
    }
    
    public List<String> getSelectedText() {
        return this.toggleButtonList.stream().filter(b -> b.isSelected()).map(b -> b.getText()).collect(Collectors.toList());
    }
    
    private void setButtonListProperties() {
        this.toggleButtonList.forEach(b -> b.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                ToggleButton b = (ToggleButton) event.getSource();
                //cambio colore
            }
        }));
    }
}
