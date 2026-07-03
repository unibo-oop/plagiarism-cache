package breakout.view.utils;

import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Create a textfield with a title.
 */
public class TitledTextField extends Group {

    private static final Font FONT = Font.loadFont("file:res/Fonts/PixelFont.ttf", 20);

    private final Text title = new Text(); // lable tipo blocco
    private final TextField textField;
    private final VBox box;

    /**
     * 
     * @param name
     *            the text in Text
     * @param text
     *            the Textfield text
     * @param width
     *            Textfield width size
     * @param height
     *            Textfield height size
     */
    public TitledTextField(final String name, final TextField text, final double width, final double height) {
        this.title.setText(name);
        this.title.setFont(FONT);
        this.title.setId("advanced_text");
        this.textField = text;
        this.textField.setPrefSize(width, height);
        this.box = new VBox(this.title, this.textField);
        this.getChildren().add(this.box);
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
     * @return the textfield text
     */
    public TextField getTextField() {
        return this.textField;
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
     * @param style
     *            the style to use in the title
     */
    public void setTextStyle(final Font font, final String style) {
        this.title.setFont(font);
        this.title.setId(style);
    }

}