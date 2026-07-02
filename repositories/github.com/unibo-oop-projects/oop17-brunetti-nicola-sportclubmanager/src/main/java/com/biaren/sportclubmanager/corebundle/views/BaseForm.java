package com.biaren.sportclubmanager.corebundle.views;

import java.util.function.Predicate;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import com.biaren.sportclubmanager.corebundle.controller.interfaces.FormController;
import com.biaren.sportclubmanager.corebundle.viewparts.BaseFormFooter;
import com.biaren.sportclubmanager.corebundle.viewparts.BaseHeader;
import com.biaren.sportclubmanager.corebundle.views.interfaces.FormView;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a BaseForm to extends for specific data.
 * Provides an header, a footer and a central body to set the appropriate
 * form panel. 
 * Take cares of parent view to update contents.
 * Provides validations support for form data fields.
 * @author nbrunetti
 *
 * @param <T> type of entity to show or works with
 */
public abstract class BaseForm<T> extends VBox implements FormView<T> {
    
    /** Min field lenght for required fields */
    protected final static int REQUIRED_FIELD_MIN_LENGTH = 1;
    /** Base header of the form*/
    protected final BaseHeader header;
    /** Base form footer of the form */
    protected final BaseFormFooter footer;
    /** Body content of the form, here will be inserted the correct pane */
    protected final VBox body;
    /** Scroll pane as utility with large forms */
    protected final ScrollPane scrollPaneBody;
    /** Parent view to performs some updates after works on entity */
    protected final Pane parentView;
    /** Controller for the form */
    protected FormController controller;
    /** Validation support to validate fields on user inputs */
    protected ValidationSupport validationSupport;

    /**
     * Creates a {@link BaseForm}
     * @param title of the form
     * @param parentView parent of base form
     */
    public BaseForm(final String title, final Pane parentView) {
        this.header = new BaseHeader(title);
        this.footer = new BaseFormFooter();
        this.body = new VBox();
        this.scrollPaneBody = new ScrollPane();
        this.parentView = parentView;
        this.validationSupport = new ValidationSupport();
        this.attachEvents();
    }
    
    /**
     * Attach the form controller to control the form
     */
    public void attachFormController(final FormController controller) {
        this.controller = controller;
    }
    
    /**
     * Set panel layout
     */
    protected void setLayout() {
        this.scrollPaneBody.setContent(this.body);
        this.getChildren().addAll(this.header, this.scrollPaneBody, this.footer);
    }
    
    /**
     * Attach events to panel,
     */
    protected void attachEvents() {
        this.submitButtonActionHandler();
        this.closeButtonActionHandler();
        this.deleteButtonActionHandler();
    }
    
    /**
     * Close window.
     */
    private void closeWindowAction() {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }
      
    /**
     * Performs close action on close button.
     */
    protected void closeButtonActionHandler() {
        this.footer.getCloseButton().setOnAction(e -> {
            this.closeWindowAction();
        });
    }
    
    /**
     * Set action on submit button, only if all fields
     * pass validation test. Shown an alert if some errors occured.
     * Calls the correct action event, update data and close the form.
     */
    protected void submitButtonActionHandler() {
        this.footer.getSubmitButton().setOnAction(e -> {
            if (this.validationSupport.isInvalid()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Errore Form Dati");
                alert.setHeaderText("Attenzione: errori nella compilazione del form.");
                alert.setContentText("Correggere i dati e ritentare.");
                alert.showAndWait();
            } else {
                this.controller.submitActionEvent();
                this.updateParentView();
                this.closeWindowAction();
            }
        });
    }

    /**
     * Set action on delete button.
     * Calls the correct action event, update data and close the form.
     */
    protected void deleteButtonActionHandler() {
        this.footer.getDeleteButton().setOnAction(e -> {
            this.controller.deleteActionEvent();
            this.updateParentView();
            this.closeWindowAction();
        });
    }
    
    /**
     * Disable or enable submit button. 
     * Pass true value to disable the button, false to enable.
     * @param flag {@link Boolean}
     */
    public final void setSubmitButtonInability(final boolean flag) {
        this.footer.getSubmitButton().setDisable(flag);
    }
    
    /**
     * Disable or enable delete button visibility.
     * Pass true value to enable visibility, false to hide.
     * @param flag {@link Boolean}
     */
    public final void setDeleteButtonVisibility(final boolean flag) {
        this.footer.getDeleteButton().setVisible(flag);
    }
    
    /**
     * Update parent view data.
     */
    protected abstract void updateParentView();
    
    /**
     * Validate fields on user input.
     */
    protected abstract void validateFields();
    
    /**
     * Class with static method for validate fields.
     * @author nbrunetti
     *
     */
    protected static class BaseFormFieldsValidity {
        
        public static Predicate<String> checkEmptyField() {
            return s -> s.trim().length() >= REQUIRED_FIELD_MIN_LENGTH;
        }
        
        public static Validator<String> getEmptyfieldValidator() {
            return Validator.createEmptyValidator("Campo richiesto.");
        }
        
        public static Predicate<String> checkFieldLength(final int lenght) {
            return s -> s.trim().length() <= lenght;
        }
        
        public static Predicate<String> checkOnlyNumbers() {
            return s -> s.matches("[0-9]+");
        }
        
    }   
    
}
