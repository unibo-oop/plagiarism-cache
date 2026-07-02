package com.biaren.sportclubmanager.soccerbundle.views;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import org.controlsfx.validation.Validator;
import com.biaren.sportclubmanager.corebundle.views.abstractviews.APersonFormPanel;
import com.biaren.sportclubmanager.soccerbundle.enums.SoccerRole;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerPlayer;
import com.biaren.utility.viewparts.VerticalLabelChoiceFieldBox;
import com.biaren.utility.viewparts.VerticalLabelTextFieldBox;
import javafx.scene.layout.Pane;

/**
 * Represents a form panel for a {@link SoccerPlayer}
 * @author nbrunetti
 *
 */
public class SoccerPlayerFormPanel extends APersonFormPanel<SoccerPlayer> {
    
    private VerticalLabelTextFieldBox height;
    private VerticalLabelTextFieldBox weight;
    private VerticalLabelChoiceFieldBox<SoccerRole> mainRole;
    
    /**
     * Creates a {@link SoccerPlayerFormPanel}, instantiate fields,
     * set the layout and apply validation to fields.
     * @param title of form
     * @param parentView of form view 
     */
    public SoccerPlayerFormPanel(final String title, final Pane parentView) {
        super(title, parentView);
        this.height = new VerticalLabelTextFieldBox(Fields.HEIGHT.getLabel());
        this.weight = new VerticalLabelTextFieldBox(Fields.WEIGHT.getLabel());
        this.mainRole = new VerticalLabelChoiceFieldBox<>(Fields.MAINROLE.getLabel(), Arrays.asList(SoccerRole.values()));
        this.setLayout();
        this.validateFields();
    }

    @Override
    protected void setLayout() {
        super.setLayout();
        this.body.getChildren().addAll(this.height, this.weight, this.mainRole);
    }
    
    @Override
    public void fillFormWithData(final SoccerPlayer entity) {
        super.fillFormWithData(entity);
        this.height.getField().setText(String.valueOf(entity.getHeight()));
        this.weight.getField().setText(String.valueOf(entity.getWeight()));
        this.mainRole.getField().setValue(entity.getMainRole());
    }
    
    /**
     * Get data from fields.
     * @return data {@link Map} with label-value
     */
    public Map<String, String> getFieldsData() {
        Map<String, String> data = new HashMap<>(super.getFieldsData());
        data.put(Fields.HEIGHT.getClassFieldName(), this.height.getFieldValue());
        data.put(Fields.WEIGHT.getClassFieldName(), this.weight.getFieldValue());
        data.put(Fields.MAINROLE.getClassFieldName(), this.mainRole.getFieldValue().getRoleDescription());
        return data;
    }
    
    @Override
    protected void updateParentView() {
        ((SoccerPlayerPanel) this.parentView).updateView();
    }
    
    @Override
    protected void validateFields() {
        super.validateFields();
        this.validationSupport.registerValidator(this.height.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(),
                Validator.createPredicateValidator(Fields.HEIGHT.getPredicate(),
                        getMaxFieldLengthMessage(Fields.HEIGHT.getIntLength()))));
        this.validationSupport.registerValidator(this.weight.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(),
                Validator.createPredicateValidator(Fields.WEIGHT.getPredicate(),
                        getMaxFieldLengthMessage(Fields.WEIGHT.getIntLength()))));
        this.validationSupport.registerValidator(this.mainRole.getField(), Validator.createEmptyValidator("Seleziona ruolo"));
    }
 
    /**
     * Enum that represent form fields, with class field name, label name to show on form view,
     * maxLength and a Predicate for validation test.
     * @author nbrunetti
     *
     */
    public enum Fields {
        
        HEIGHT("height", "Altezza", Length.HEIGHT, BaseFormFieldsValidity.checkFieldLength(Length.HEIGHT.getLength())),
        WEIGHT("weight", "Peso", Length.WEIGHT, BaseFormFieldsValidity.checkFieldLength(Length.WEIGHT.getLength())),
        MAINROLE("mainRole", "Ruolo Principale", Length.MAINROLE, s -> true);
        
        private String classFieldName;
        private String label;
        private Length length;
        private Predicate<String> predicate;
        
        private Fields(final String classFieldName, final String label, final Length length, final Predicate<String> predicate) {
            this.classFieldName = classFieldName;
            this.label = label;
            this.length = length;
            this.predicate = predicate;
        }
        
        /**
         * Get class field name
         * @return class field name
         */
        public final String getClassFieldName() {
            return this.classFieldName;
        }
        
        /**
         * Get label string
         * @return label string
         */
        public final String getLabel() {
            return this.label;
        }
        
        /**
         * Get enum length
         * @return enum length
         */
        public final Length getEnumLenght() {
            return this.length;
        }
        
        /**
         * Get int length
         * @return int length
         */
        public final int getIntLength() {
            return this.length.getLength();
        }

        /**
         * Get Predicate to test validation
         * @return {@link Predicate} to test validation
         */
        public final Predicate<String> getPredicate() {
            return this.predicate;
        }
        
        /**
         * Length of fields
         * @author nbrunetti
         *
         */
        static enum Length {
            HEIGHT(6),
            WEIGHT(6),
            MAINROLE(3);
            
            private int length;
            
            private Length(final int length) {
                this.length = length;
            }
            
            public int getLength() {
                return this.length;
            }
        }
    }
}
