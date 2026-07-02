package com.biaren.sportclubmanager.soccerbundle.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.controlsfx.validation.Validator;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerMatchData;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerPlayer;
import com.biaren.utility.DateUtil;
import com.biaren.utility.viewparts.VerticalLabelComboBox;
import com.biaren.utility.viewparts.VerticalLabelDatePickerBox;
import com.biaren.utility.viewparts.VerticalLabelTextFieldBox;
import javafx.scene.layout.Pane;

/**
 * Represents a form panel for a {@link SoccerMatchData}
 * @author nbrunetti
 *
 */
public class SoccerMatchDataFormPanel extends BaseForm<SoccerMatchData>{

    private VerticalLabelDatePickerBox date;
    private VerticalLabelTextFieldBox homeTeam;
    private VerticalLabelTextFieldBox awayTeam;
    private VerticalLabelTextFieldBox homeTeamGoal;
    private VerticalLabelTextFieldBox awayTeamGoal;
    private VerticalLabelComboBox<String> goals;
    private VerticalLabelComboBox<String> warnings;
    private VerticalLabelComboBox<String> expulsions;

    /**
     * Creates a {@link SoccerMatchDataFormPanel}, instantiate fields,
     * set the layout and apply validation to fields.
     * @param title of form
     * @param parentView form parent view
     */
    public SoccerMatchDataFormPanel(String title, Pane parentView) {
        super(title, parentView);
        this.date = new VerticalLabelDatePickerBox(Fields.DATE.getLabel());
        this.homeTeam = new VerticalLabelTextFieldBox(Fields.HOMETEAM.getLabel());
        this.awayTeam = new VerticalLabelTextFieldBox(Fields.AWAYTEAM.getLabel());
        this.homeTeamGoal = new VerticalLabelTextFieldBox(Fields.HOMEGOAL.getLabel());
        this.awayTeamGoal = new VerticalLabelTextFieldBox(Fields.AWAYGOAL.getLabel());
        this.goals = new VerticalLabelComboBox<>(Fields.GOALS.getLabel(), 
                SoccerPlayer.getSoccerPlayersNamesList(BaseModelImpl.getInstance().getPlayersList()));
        this.warnings = new VerticalLabelComboBox<>(Fields.WARNINGS.getLabel(), 
                SoccerPlayer.getSoccerPlayersNamesList(BaseModelImpl.getInstance().getPlayersList()));
        this.expulsions = new VerticalLabelComboBox<>(Fields.EXPULSION.getLabel(), 
                SoccerPlayer.getSoccerPlayersNamesList(BaseModelImpl.getInstance().getPlayersList()));
        this.setLayout();
        this.validateFields();
    }
    
    @Override
    protected void setLayout() {
        super.setLayout();
        this.body.getChildren().addAll(this.date, this.homeTeam, this.awayTeam,
                this.homeTeamGoal, this.awayTeamGoal, this.goals,
                this.warnings, this.expulsions);
    }

    @Override
    public void fillFormWithData(SoccerMatchData entity) {
        this.date.getField().setValue(entity.getDate());
        this.homeTeam.getField().setText(entity.getHomeTeam());
        this.awayTeam.getField().setText(entity.getAwayTeam());
        this.homeTeamGoal.getField().setText(String.valueOf(entity.getHomeTeamGoal()));
        this.awayTeamGoal.getField().setText(String.valueOf(entity.getAwayTeamGoal()));
        entity.getGoals().forEach(s -> this.goals.getField().getCheckModel().check(s.getNameSurname()));
        entity.getWarnings().forEach(s -> this.warnings.getField().getCheckModel().check(s.getNameSurname()));
        entity.getExpulsions().forEach(s -> this.goals.getField().getCheckModel().check(s.getNameSurname()));
    }
    
    /**
     * Get data from fields.
     * @return data {@link Map} with label-value
     */
    public Map<String, String> getFieldsData() {
        Map<String, String> data = new HashMap<>();
        data.put(Fields.DATE.getClassFieldName(), this.date.getFieldValue());
        data.put(Fields.HOMETEAM.getClassFieldName(), this.homeTeam.getFieldValue());
        data.put(Fields.AWAYTEAM.getClassFieldName(), this.awayTeam.getFieldValue());
        data.put(Fields.HOMEGOAL.getClassFieldName(), this.homeTeamGoal.getFieldValue());
        data.put(Fields.AWAYGOAL.getClassFieldName(), this.awayTeamGoal.getFieldValue());
        return data;
    }
    
    /**
     * Get data from combo box fields.
     * @return data {@link Map} with label-value
     */
    public Map<String, List<SoccerPlayer>> getComboBoxData() {
        Map<String, List<SoccerPlayer>> data = new HashMap<>();
        data.put(Fields.GOALS.getClassFieldName(), this.getPlayersEntityFromNames(this.goals.getFieldValue()));
        data.put(Fields.WARNINGS.getClassFieldName(), this.getPlayersEntityFromNames(this.warnings.getFieldValue()));
        data.put(Fields.EXPULSION.getClassFieldName(), this.getPlayersEntityFromNames(this.expulsions.getFieldValue()));
        return data;
    }
    
    private List<SoccerPlayer> getPlayersEntityFromNames(final List<String> names) {
        return names.stream()
        .map(n -> n.split(" "))
        .map(n -> SoccerPlayer.getPlayerFromName(BaseModelImpl.getInstance().getPlayersList(), n[0], n[1]))
        .collect(Collectors.toList());
    }

    @Override
    protected void updateParentView() {
        ((SoccerMatchDataPanel) this.parentView).updateView();
    }

    @Override
    protected void validateFields() {
        this.validationSupport.registerValidator(this.date.getField(), 
                BaseFormFieldsValidity.getEmptyfieldValidator());
        this.validationSupport.registerValidator(this.homeTeam.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(),
                Validator.createPredicateValidator(Fields.HOMETEAM.getPredicate(), 
                        getMaxFieldLengthMessage(Fields.HOMETEAM.getIntLength()))));
        this.validationSupport.registerValidator(this.awayTeam.getField(), Validator.combine(
                BaseFormFieldsValidity.getEmptyfieldValidator(),
                Validator.createPredicateValidator(Fields.AWAYTEAM.getPredicate(), 
                        getMaxFieldLengthMessage(Fields.AWAYTEAM.getIntLength()))));
        this.validationSupport.registerValidator(this.homeTeamGoal.getField(), Validator.combine(
                MatchFormFieldsValidity.getGoalFieldValidator(),
                Validator.createPredicateValidator(Fields.HOMEGOAL.getPredicate(), getOnlyIntegerNumberAcceptedMessage())));
        this.validationSupport.registerValidator(this.awayTeamGoal.getField(), Validator.combine(
                MatchFormFieldsValidity.getGoalFieldValidator(),
                Validator.createPredicateValidator(Fields.AWAYGOAL.getPredicate(), getOnlyIntegerNumberAcceptedMessage())));
    }
    
    /** 
     * Extends {@link BaseFormFieldsValidity} to include another specific validity operation
     * @author nbrunetti
     *
     */
    protected static class MatchFormFieldsValidity extends BaseFormFieldsValidity {
        
        public static Validator<String> getGoalFieldValidator() {
            return Validator.createEmptyValidator("Campo richiesto. Inserire 0 se nessun goal segnato");
        }
    }
    
    /**
     * Enum that represent form fields, with class field name, label name to show on form view,
     * maxLength and a Predicate for validation test.
     * @author nbrunetti
     *
     */
    public enum Fields {
        
        DATE("date", "Data", Length.DATE, s -> DateUtil.validDate(s)),
        HOMETEAM("homeTeam", "Squadra di Casa", Length.HOMETEAM, BaseFormFieldsValidity.checkFieldLength(Length.HOMETEAM.getLength())),
        AWAYTEAM("awayTeam", "Squadra Ospite", Length.AWAYTEAM, BaseFormFieldsValidity.checkFieldLength(Length.HOMETEAM.getLength())),
        HOMEGOAL("homeGoal", "Goal Squadra Casa", Length.HOMEGOAL, BaseFormFieldsValidity.checkOnlyNumbers()),
        AWAYGOAL("awayGoal", "Goal Squadra Opite", Length.AWAYGOAL, BaseFormFieldsValidity.checkOnlyNumbers()),
        GOALS("goals", "Goal", Length.GOALS, s -> true),
        WARNINGS("warnings", "Ammonizioni", Length.WARNINGS, s -> true),
        EXPULSION("expulsions", "Espulsioni", Length.EXPULSIONS, s -> true);
        
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
            DATE(10),
            HOMETEAM(20),
            AWAYTEAM(20),
            HOMEGOAL(3),
            AWAYGOAL(3),
            GOALS(1000),
            WARNINGS(1000),
            EXPULSIONS(1000);
            
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
