package com.biaren.sportclubmanager.corebundle.views.abstractviews;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import org.controlsfx.validation.Validator;
import com.biaren.sportclubmanager.corebundle.model.ClubMember;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.utility.DateUtil;
import com.biaren.utility.viewparts.VerticalLabelDatePickerBox;
import com.biaren.utility.viewparts.VerticalLabelTextFieldBox;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;

/**
 * Abstract Form for a generic Person.
 * @author nbrunetti
 *
 * @param <T> type to extension version of this class
 */
public abstract class APersonFormPanel<T extends ClubMember> extends BaseForm<T>{  
    
    /* http://blog.marketto.it/2016/01/regex-validazione-codice-fiscale-con-omocodia/#regexp-perfetta */
    //invece non mi funziona...
    public final static String FISCAL_CODE_REGEX = "/^(?:(?:[B-DF-HJ-NP-TV-Z]|[AEIOU])[AEIOU][AEIOUX]"
            + "|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}[\\dLMNP-V]{2}(?:[A-EHLMPR-T]"
            + "(?:[04LQ][1-9MNP-V]|[1256LMRS][\\dLMNP-V])|[DHPS][37PT][0L]"
            + "|[ACELMRT][37PT][01LM])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}"
            + "|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$/i";
    
    /** Very simple regex for a basic validation of a fiscal code */
    public final static String FISCAL_CODE_MIN_REGEX = "[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]";
    /** nName text field box*/
    protected VerticalLabelTextFieldBox name;
    /** Surname text field box*/
    protected VerticalLabelTextFieldBox surname;
    /** Birth date field box, with DatePicker */
    protected VerticalLabelDatePickerBox birthDate;
    /** Birth place field box*/
    protected VerticalLabelTextFieldBox birthPlace;
    /** Nationality text field box*/
    protected VerticalLabelTextFieldBox nationality;
    /** Residence address field box*/
    protected VerticalLabelTextFieldBox residenceAddress;
    /** Residence city field box*/
    protected VerticalLabelTextFieldBox residenceCity;
    /** Residence district field box*/
    protected VerticalLabelTextFieldBox residenceDistrict;
    /** Residence nation field box*/
    protected VerticalLabelTextFieldBox residenceNation;
    /** Fiscalcode field box */
    protected VerticalLabelTextFieldBox fiscalCode;

    /**
     * Constructor of {@link APersonFormPanel}. Cannot use because abstract, but can
     * extends to performs similar actions.
     * @param title of the form
     * @param parentView of the form
     */
    protected APersonFormPanel(final String title, final Pane parentView) {
        super(title, parentView);
        this.name = new VerticalLabelTextFieldBox(Fields.NAME.getLabel());
        this.surname = new VerticalLabelTextFieldBox(Fields.SURNAME.getLabel());
        this.birthDate = new VerticalLabelDatePickerBox(Fields.BIRTHDATE.getLabel());
        this.birthPlace = new VerticalLabelTextFieldBox(Fields.BIRTHPLACE.getLabel());
        this.nationality = new VerticalLabelTextFieldBox(Fields.NATIONALITY.getLabel());
        this.residenceAddress = new VerticalLabelTextFieldBox(Fields.ADDRESS.getLabel());
        this.residenceCity = new VerticalLabelTextFieldBox(Fields.CITY.getLabel());
        this.residenceDistrict = new VerticalLabelTextFieldBox(Fields.DISTRICT.getLabel());
        this.residenceNation = new VerticalLabelTextFieldBox(Fields.NATION.getLabel());
        this.fiscalCode = new VerticalLabelTextFieldBox(Fields.FISCALCODE.getLabel());
    }
  
    /**
     * Fill form with entity data passed to method.
     */
    public void fillFormWithData(final T c) {
        this.getNameBox().getField().setText(c.getName());
        this.getSurnameBox().getField().setText(c.getSurname());
        this.getBirthDateBox().getField().setValue(c.getBirthDate());
        this.getBirthPlaceBox().getField().setText(c.getBirthPlace());
        this.getNationalityBox().getField().setText(c.getNationality());
        this.getResidenceDistrictBox().getField().setText(c.getResidenceDistrict());
        this.getResidenceAddressBox().getField().setText(c.getResidenceAddress());
        this.getResidenceCityBox().getField().setText(c.getResidenceCity());
        this.getResidenceNationBox().getField().setText(c.getResidenceNation());
        this.getFiscalCodeBox().getField().setText(c.getFiscalCode());
    }
    
    /**
     * Get name box with name field
     * @return {@link VerticalLabelTextFieldBox} of name field
     */
    public VerticalLabelTextFieldBox getNameBox() {
        return this.name;
    }
    
    /**
     * Get surname box with surname field
     * @return {@link VerticalLabelTextFieldBox} of surname field
     */
    public VerticalLabelTextFieldBox getSurnameBox() {
        return this.surname;
    }
    
    /**
     * Get birth date box with birth date field
     * @return {@link VerticalLabelDatePickerBox} of birth date field
     */
    public VerticalLabelDatePickerBox getBirthDateBox() {
        return this.birthDate;
    }
    
    /**
     * Get birth place box with birth place field
     * @return {@link VerticalLabelTextFieldBox} of birth place field
     */
    public VerticalLabelTextFieldBox getBirthPlaceBox() {
        return this.birthPlace;
    }
    
    /**
     * Get nationality box with nationality field
     * @return {@link VerticalLabelTextFieldBox} of nationality field
     */
    public VerticalLabelTextFieldBox getNationalityBox() {
        return this.nationality;
    }
    
    /**
     * Get residence address box with residence address field
     * @return {@link VerticalLabelTextFieldBox} of residence address field
     */
    public VerticalLabelTextFieldBox getResidenceAddressBox() {
        return this.residenceAddress;
    }
    
    /**
     * Get residence city box with residence city field
     * @return {@link VerticalLabelTextFieldBox} of residence city field
     */
    public VerticalLabelTextFieldBox getResidenceCityBox() {
        return this.residenceCity;
    }
    
    /**
     * Get residence district box with surname field
     * @return {@link VerticalLabelTextFieldBox} of residence district field
     */
    public VerticalLabelTextFieldBox getResidenceDistrictBox() {
        return this.residenceDistrict;
    }
    
    /**
     * Get residence nation box with surname field
     * @return {@link VerticalLabelTextFieldBox} of residence nation field
     */
    public VerticalLabelTextFieldBox getResidenceNationBox() {
        return this.residenceNation;
    }
    
    /**
     * Get fiscalcode box with fiscalcode field
     * @return {@link VerticalLabelTextFieldBox} of fiscalcode field
     */
    public VerticalLabelTextFieldBox getFiscalCodeBox() {
        return this.fiscalCode;
    }
    
    /**
     * Get fields data from form's fields.
     * @return {@link Map} with pair classFieldname-value
     */
    protected Map<String, String> getFieldsData() {
        Map<String, String> data = new HashMap<>();
        data.put(Fields.NAME.getClassFieldName(), this.name.getFieldValue());
        data.put(Fields.SURNAME.getClassFieldName(), this.surname.getFieldValue());
        data.put(Fields.BIRTHDATE.getClassFieldName(), this.birthDate.getFieldValue());
        data.put(Fields.BIRTHPLACE.getClassFieldName(), this.birthPlace.getFieldValue());
        data.put(Fields.NATIONALITY.getClassFieldName(), this.nationality.getFieldValue());
        data.put(Fields.DISTRICT.getClassFieldName(), this.residenceDistrict.getFieldValue());
        data.put(Fields.ADDRESS.getClassFieldName(), this.residenceAddress.getFieldValue());
        data.put(Fields.CITY.getClassFieldName(), this.residenceCity.getFieldValue());
        data.put(Fields.NATION.getClassFieldName(), this.residenceNation.getFieldValue());
        data.put(Fields.FISCALCODE.getClassFieldName(), this.fiscalCode.getFieldValue());
        return data;
    }
    
    /**
     * Set form's layout
     */
    protected void setLayout() {
        super.setLayout();
        this.birthDate.setPadding(new Insets(5, 10, 10, 10));
        this.birthDate.setSpacing(10);
        this.body.getChildren().addAll(this.name, this.surname, this.birthDate, this.birthPlace,
                this.nationality, this.residenceAddress, this.residenceCity, this.residenceDistrict,
                this.residenceNation, this.fiscalCode);
    }
    
    /*
     * Problems with DatePicker Validation. 
     * 
     * Validator.createPredicateValidator(Fields.BIRTHDATE.getPredicate(), "Formato data non corretta. Formato: gg/mm/aaaa")));
     * 
     * On validation control it generates a ClassCastException, LocalDate to String.
     * Because the ControlsFX online documentation is not exhaustive and i fail to understand my mistake in short time,
     * i solve the problem making the field not directly editable with keyboard and only by choose the value with DatePicker.
     * 
     */
    /**
     * Performs validation on field using controlsfx validation
     */
    @Override
    protected void validateFields() {
        this.validationSupport.registerValidator(this.name.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(), 
                Validator.createPredicateValidator(Fields.NAME.getPredicate(),
                        getMaxFieldLengthMessage(Fields.NAME.getIntLength()))));
        this.validationSupport.registerValidator(this.surname.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(), 
                Validator.createPredicateValidator(Fields.SURNAME.getPredicate(), 
                        getMaxFieldLengthMessage(Fields.SURNAME.getIntLength()))));
        this.validationSupport.registerValidator(this.birthDate.getField(),
                BaseFormFieldsValidity.getEmptyfieldValidator());
        this.validationSupport.registerValidator(this.birthPlace.getField(),Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(), 
                Validator.createPredicateValidator(Fields.BIRTHPLACE.getPredicate(), 
                         getMaxFieldLengthMessage(Fields.BIRTHPLACE.getIntLength()))));
        this.validationSupport.registerValidator(this.nationality.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(),
                Validator.createPredicateValidator(Fields.NATIONALITY.getPredicate(),
                        getMaxFieldLengthMessage(Fields.NATIONALITY.getIntLength()))));
        this.validationSupport.registerValidator(this.residenceAddress.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(),
                Validator.createPredicateValidator(Fields.ADDRESS.getPredicate(),
                        getMaxFieldLengthMessage(Fields.ADDRESS.getIntLength()))));
        this.validationSupport.registerValidator(this.residenceCity.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(),
                Validator.createPredicateValidator(Fields.CITY.getPredicate(),
                        getMaxFieldLengthMessage(Fields.CITY.getIntLength()))));
        this.validationSupport.registerValidator(this.residenceDistrict.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(),
                Validator.createPredicateValidator(Fields.DISTRICT.getPredicate(),
                        getMaxFieldLengthMessage(Fields.DISTRICT.getIntLength()))));
        this.validationSupport.registerValidator(this.residenceNation.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(),
                Validator.createPredicateValidator(Fields.NATION.getPredicate(),
                        getMaxFieldLengthMessage(Fields.NATION.getIntLength()))));
        this.validationSupport.registerValidator(this.fiscalCode.getField(), BaseFormFieldsValidity.getEmptyfieldValidator());
        
        /* disabilitato controllo su codice fiscale palloso per fase di debug. Sopra viene effettuato il controllo
         * del solo "campo vuoto". Se si volesse testare il funzionamento della validazione, commentare la riga sopra
         * e decommentare il blocco di istruzioni qui sotto.
         */
        
//        this.validationSupport.registerValidator(this.fiscalCode.getField(), Validator.combine(
//                BaseFormFieldsValidity.getEmptyfieldValidator(),
//                Validator.createPredicateValidator(Fields.FISCALCODE.getPredicate(),
//                "Codice Fiscale non valido.")));
    }
    
    /** 
     * Extends {@link BaseFormFieldsValidity} to include another specific validity operation
     * @author nbrunetti
     *
     */
    protected static class PersonFormFieldsValidity extends BaseFormFieldsValidity {
        
        /**
         * Validate fiscal code string
         * @return true if test is passed
         */
        public static Predicate<String> checkFiscalCode() {
            return s -> s.matches(FISCAL_CODE_MIN_REGEX);
        }
    }
    
    /**
     * Enum that represent form fields, with class field name, label name to show on form view,
     * maxLength and a Predicate for validation test.
     * @author nbrunetti
     *
     */
    public enum Fields {
        NAME("name", "Nome", Length.NAME, BaseFormFieldsValidity.checkFieldLength(Length.NAME.getLength())),
        SURNAME("surname", "Cognome", Length.SURNAME, BaseFormFieldsValidity.checkFieldLength(Length.SURNAME.getLength())),
        BIRTHDATE("birthDate", "Data di nascita", Length.BIRTHDATE, s -> DateUtil.validDate(s)),
        BIRTHPLACE("birthPlace", "Luogo di nascita", Length.BIRTHPLACE, BaseFormFieldsValidity.checkFieldLength(Length.BIRTHPLACE.getLength())),
        NATIONALITY("nationality", "Nazionalità", Length.NATIONALITY, BaseFormFieldsValidity.checkFieldLength(Length.NATIONALITY.getLength())),
        DISTRICT("residenceDistrict", "Regione di residenza", Length.DISTRICT, BaseFormFieldsValidity.checkFieldLength(Length.DISTRICT.getLength())),
        ADDRESS("residenceAddress", "Indirizzo di residenza", Length.ADDRESS, BaseFormFieldsValidity.checkFieldLength(Length.ADDRESS.getLength())),
        CITY("residenceCity", "Città di residenza", Length.CITY, BaseFormFieldsValidity.checkFieldLength(Length.CITY.getLength())),
        NATION("residenceNation", "Nazione di residenza", Length.NATION, BaseFormFieldsValidity.checkFieldLength(Length.NATION.getLength())),
        FISCALCODE("fiscalCode", "Codice fiscale", Length.FISCALCODE, PersonFormFieldsValidity.checkFiscalCode());
        
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
            NAME(50),
            SURNAME(50),
            BIRTHDATE(10),
            BIRTHPLACE(50),
            NATIONALITY(60),
            DISTRICT(50),
            ADDRESS(50),
            CITY(60),
            NATION(30),
            FISCALCODE(16);
            
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
