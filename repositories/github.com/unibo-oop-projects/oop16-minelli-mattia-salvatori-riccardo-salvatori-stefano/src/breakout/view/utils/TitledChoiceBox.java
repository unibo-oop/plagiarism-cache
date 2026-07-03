package breakout.view.utils;

import javafx.scene.Group;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Create a choicebox with a title.
 *
 * @param <T>
 *            the type of elements in the choicebox
 */
public class TitledChoiceBox<T> extends Group {

    private final Text title = new Text(); // lable tipo blocco
    private final ChoiceBox<T> choiceBox;
    private final VBox box;

    /**
     * 
     * @param name
     *            the text in Text
     * @param choice
     *            the type of choicebox
     * @param width
     *            Choicebox width size
     * @param height
     *            Choicebox height size
     */
    public TitledChoiceBox(final String name, final ChoiceBox<T> choice, final double width, final double height) {
        this.title.setText(name);
        this.choiceBox = choice;
        this.choiceBox.setPrefSize(width, height);
        this.box = new VBox(this.title, this.choiceBox);
        this.getChildren().add(this.box);
        this.getStylesheets().add("stylesheet.css");

    }

    /**
     * 
     * @return the Title text
     */
    public Text getText() {
        return this.title;
    }

    /**
     * 
     * @return the choicebox
     */
    public ChoiceBox<T> getChoiceBox() {
        return this.choiceBox;
    }

    /**
     * 
     * @return the Vbox
     */
    public VBox getVBox() {
        return this.box;
    }

    /**
     * 
     * @param spacing
     *            the space to set between each child in the vbox
     */
    public void setVBoxSpacing(final double spacing) {
        this.box.setSpacing(spacing);
    }

    /**
     * 
     * @param font
     *            the new font to use in the title
     * @param id
     *            the id to use in the title
     */
    public void setTextStyle(final Font font, final String id) {
        this.title.setFont(font);
        this.title.setId(id);
    }

}
