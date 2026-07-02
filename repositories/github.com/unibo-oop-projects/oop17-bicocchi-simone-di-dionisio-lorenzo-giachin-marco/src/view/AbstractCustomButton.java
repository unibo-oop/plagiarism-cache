package view;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

/**
 * A custom button for the items of the shop and inventory.
 */
public abstract class AbstractCustomButton extends Button implements CustomViewComponent {
    private String item;
    private Category category;
    private static final int DIM = 90;

    /**
     * 
     * @param img 
     * @param cat 
     * @param newItem 
     * @param value 
     */
    public AbstractCustomButton(final ImageView img, final String newItem, final Category cat, final int value) {
        this.item = newItem;
        this.category = cat;
        this.setGraphic(img);
        this.setTooltip(new Tooltip("Oggetto: " + item + "\n" + "Valore: " + value));
        this.setId("yellowImage2");
        img.setFitHeight(DIM);
        img.setFitWidth(DIM);
        this.setAction();
    }

    /**
     * 
     * @return item
     */
    public String getItem() {
        return this.item;
    }

    /**
     * 
     * @return category
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * 
     */
    public abstract void setAction();
}
