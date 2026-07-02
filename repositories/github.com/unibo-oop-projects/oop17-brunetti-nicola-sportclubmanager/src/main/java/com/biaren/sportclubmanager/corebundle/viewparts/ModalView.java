package com.biaren.sportclubmanager.corebundle.viewparts;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Modal View to show on screen on certain event fires.
 * @author nbrunetti
 *
 * @param <T> the type of entity to show and work
 */
public class ModalView<T> extends VBox {

    private final BaseHeader header;
    private final BaseFormFooter footer;
    private final VBox body;
    private Pane content;
    private T entity;
    
    /**
     * Creates a {@link ModalView}
     * @param content of modal
     * @param modalTitle title of modal
     */
    public ModalView(final Pane content, final String modalTitle) {
        this.header = new BaseHeader(modalTitle);
        this.footer = new BaseFormFooter();
        this.body = new VBox();
        this.entity = null;
        this.content = content;
        this.setContent(content);
        this.setLayout();
    }
    
    /**
     * Creates a {@link ModalView}
     * @param content content of modal
     * @param modalTitle title of modal
     * @param entity entity to show on modal
     */
    public ModalView(final Pane content, final String modalTitle, final T entity) {
        this(content, modalTitle);
        this.entity = entity;
    }
    
    private void setLayout() {
        if (this.entity == null) {
            this.footer.getChildren().remove(this.footer.getDeleteButton());
        } else {
            this.getChildren().addAll(this.header, this.body, this.footer);
        }
    }
    
    /**
     * Get current modal's content
     * @return modal's content pane
     */
    public Pane getContent() {
        return this.content;
    }
    
    /**
     * Get submit button reference
     * @return {@link Button} reference
     */
    public Button getSubmitButton() {
        return this.footer.getSubmitButton();
    }
    
    /**
     * Get close button reference
     * @return {@link Button} reference
     */
    public Button getCloseButton() {
        return this.footer.getCloseButton();
    }
    
    /**
     * Get delete button reference
     * @return {@link Button} reference
     */
    public Button getDeleteButton() {
        return this.footer.getDeleteButton();
    }
    
    /**
     * Get current entity shown on modal
     * @return entity
     */
    public T getEntity() {
        return this.entity;
    }
    
    private void setContent(final Pane content) {
        this.body.getChildren().add(content);
    }
}
