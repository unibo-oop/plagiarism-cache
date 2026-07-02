package spacesurvival.view.utilities;

import java.awt.Color;
import java.awt.Font;

public interface GraphicsText {

    /**
     * Set font title menu GUI.
     * @param font for text.
     */
    void setFontTitleGUI(Font font);

    /**
     * Set title menu GUI.
     * @param title for menu GUI.
     */
    void setTitleGUI(String title);

    /**
     * Set all menu font except title.
     * @param font for menu GUI expect title.
     */
    void setFontGUI(Font font);

    /**
     * Set color foreground menu GUI.
     * @param color for foreground menu GUI.
     */
    void setForegroundGUI(Color color);
}
