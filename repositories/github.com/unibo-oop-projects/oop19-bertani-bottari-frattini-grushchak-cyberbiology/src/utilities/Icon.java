package utilities;

import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;

/*
 * Enumeration of the icon used by the application.
 */
public enum Icon {

    /**
     * Enum of the icon for this application menu window.
     */
    APPLICATION("image" + File.separator + "genomy.icon.png"),
    /**
     * Enum of the icon for this color menu window.
     */
    COLOR("image" + File.separator + "color.icon.png"),
    /**
     * Enum relativa all'icona info del programma.
     */
    INFO("image" + File.separator + "info.icon.png"),
    /**
     * Enum relativa all'icona logo del programma.
     */
    LOGO("image" + File.separator + "genomy.logo.png"),
    /**
     * Enum of the icon for this language menu window.
     */
    LANGUAGE("image" + File.separator + "language.icon.png"),
    /**
     * Enum of the icon for this setting menu window.
     */
    SETTING("image" + File.separator + "settings.icon.png");

    private ImageIcon icon;

    Icon(final String name) { 
        URL iconURL = ClassLoader.getSystemResource(name);
        this.icon = new ImageIcon(iconURL);
    }

    public ImageIcon getIcon() {
        return icon;
    }

}
