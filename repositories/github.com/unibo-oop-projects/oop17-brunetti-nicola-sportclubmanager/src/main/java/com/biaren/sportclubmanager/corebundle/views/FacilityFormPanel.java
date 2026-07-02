package com.biaren.sportclubmanager.corebundle.views;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import org.controlsfx.validation.Validator;
import com.biaren.sportclubmanager.corebundle.model.FacilityImpl;
import com.biaren.utility.viewparts.VerticalLabelTextAreaBox;
import com.biaren.utility.viewparts.VerticalLabelTextFieldBox;
import javafx.scene.layout.Pane;

/**
 * Represent a form panel for a {@link FacilityImpl}
 * @author nbrunetti
 *
 */
public class FacilityFormPanel extends BaseForm<FacilityImpl> {

    private VerticalLabelTextFieldBox name;
    private VerticalLabelTextFieldBox address;
    private VerticalLabelTextAreaBox description;
        
    /**
     * Creates a {@link FacilityFormPanel}, instantiate fields,
     * set the layout and apply validation to fields.
     * @param title of form
     * @param parentView of form view
     */
    public FacilityFormPanel(final String title, final Pane parentView) {
        super(title, parentView);
        this.name = new VerticalLabelTextFieldBox(Fields.NAME.getLabel());
        this.address = new VerticalLabelTextFieldBox(Fields.ADDRESS.getLabel());
        this.description = new VerticalLabelTextAreaBox(Fields.DESCRIPTION.getLabel());
        this.setLayout();
        this.validateFields();
    }
    
    @Override
    protected void setLayout() {
        super.setLayout();
        this.body.getChildren().addAll(this.name, this.address, this.description);
    }

    @Override
    public void fillFormWithData(final FacilityImpl entity) {
        this.name.getField().setText(entity.getName());
        this.address.getField().setText(entity.getAddress());
        this.description.getField().setText(entity.getDescription());
    }

    /**
     * Get data from fields.
     * @return data {@link Map} with label-value
     */
    public Map<String, String> getFieldsData() {
        Map<String, String> data = new HashMap<>();
        data.put(Fields.NAME.getClassFieldName(), this.name.getFieldValue());
        data.put(Fields.ADDRESS.getClassFieldName(), this.address.getFieldValue());
        data.put(Fields.DESCRIPTION.getClassFieldName(), this.description.getFieldValue());
        return data;
    }

    @Override
    protected void updateParentView() {
        ((FacilityPanel) this.parentView).updateView();
    }
    
    @Override
    protected void validateFields() {
        this.validationSupport.registerValidator(this.name.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(),
                Validator.createPredicateValidator(Fields.NAME.getPredicate(), 
                        getMaxFieldLengthMessage(Fields.NAME.getIntLength()))));
        this.validationSupport.registerValidator(this.address.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(),
                Validator.createPredicateValidator(Fields.ADDRESS.getPredicate(), 
                        getMaxFieldLengthMessage(Fields.ADDRESS.getIntLength()))));
        this.validationSupport.registerValidator(this.description.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(),
                Validator.createPredicateValidator(Fields.DESCRIPTION.getPredicate(), 
                        getMaxFieldLengthMessage(Fields.DESCRIPTION.getIntLength()))));
    }
    
    /**
     * Enum that represent form fields, with class field name, label name to show on form view,
     * maxLength and a Predicate for validation test.
     * @author nbrunetti
     *
     */
    public enum Fields {
       
        NAME("name", "Ruolo", Length.NAME, BaseFormFieldsValidity.checkFieldLength(Length.NAME.getLength())),
        ADDRESS("address", "Indirizzo", Length.ADDRESS, BaseFormFieldsValidity.checkFieldLength(Length.ADDRESS.getLength())),
        DESCRIPTION("description", "Descrizione", Length.DESCRIPTION, BaseFormFieldsValidity.checkFieldLength(Length.DESCRIPTION.getLength()));
        
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
        public final Length getEnumLength() {
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
            NAME(30),
            ADDRESS(50),
            DESCRIPTION(500);
            
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

    
