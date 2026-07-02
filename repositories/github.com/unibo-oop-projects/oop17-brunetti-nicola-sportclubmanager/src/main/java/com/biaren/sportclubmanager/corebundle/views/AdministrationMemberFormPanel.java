package com.biaren.sportclubmanager.corebundle.views;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import org.controlsfx.validation.Validator;
import com.biaren.sportclubmanager.corebundle.model.AdministrationMember;
import com.biaren.sportclubmanager.corebundle.model.enums.AdministrationMemberRole;
import com.biaren.sportclubmanager.corebundle.views.abstractviews.APersonFormPanel;
import com.biaren.utility.viewparts.VerticalLabelChoiceFieldBox;
import javafx.scene.layout.Pane;

/**
 * Represent a form panel for a {@link AdministrationMember}
 * @author nbrunetti
 *
 */
public class AdministrationMemberFormPanel extends APersonFormPanel<AdministrationMember> {

    private VerticalLabelChoiceFieldBox<AdministrationMemberRole> role;
    
    /**
     * Creates a {@link AdministrationMemberFormPanel}, instantiate fields,
     * set the layout and apply validation to fields.
     * @param title of form
     * @param parentView form parent view
     */
    public AdministrationMemberFormPanel(final String title, final Pane parentView) {
        super(title, parentView);
        this.role = new VerticalLabelChoiceFieldBox<>(Fields.ROLE.getLabel(), Arrays.asList(AdministrationMemberRole.values()));
        this.setLayout();
        this.validateFields();
    }

    @Override
    protected void setLayout() {
        super.setLayout();
        this.body.getChildren().addAll(this.role);
    }
    
    @Override
    public void fillFormWithData(final AdministrationMember entity) {
        super.fillFormWithData(entity);
        this.role.getField().setValue(entity.getAdministrationMemberRole());
    }
    
    /**
     * Get data from fields.
     * @return data {@link Map} with label-value
     */
    public Map<String, String> getFieldsData() {
        Map<String, String> data = new HashMap<>(super.getFieldsData());
        data.put(Fields.ROLE.getClassFieldName(), this.role.getFieldValue().getRoleDescription());
        return data;
    }
    
    @Override
    protected void updateParentView() {
        ((AdministrationMemberPanel) this.parentView).updateView();
    }
    
    @Override
    protected void validateFields() {
        super.validateFields();
        this.validationSupport.registerValidator(this.role.getField(), Validator.createEmptyValidator("Seleziona ruolo"));
    }
    
    /**
     * Enum that represent form fields, with class field name, label name to show on form view,
     * maxLength and a Predicate for validation test.
     * @author nbrunetti
     *
     */
    public enum Fields {
        
        /*
         * This is a special case: the role has predefined values chosen by a combobox.
         * Length and predicate should not be specified because they're not essential.
         * They are however written to maintain consistency with codes written in other classes.
         * The validity checks for role will be performed directly by Validator class.
         */
        ROLE("administrationMemberRole", "Ruolo", Length.ROLE, s -> true);
        
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
            ROLE(20);
            
            private int length;
            
            private Length(final int length) {
                this.length = length;
            }
            
            /**
             * Get length value
             * @return length value
             */
            public int getLength() {
                return this.length;
            }
        }
    }
}
