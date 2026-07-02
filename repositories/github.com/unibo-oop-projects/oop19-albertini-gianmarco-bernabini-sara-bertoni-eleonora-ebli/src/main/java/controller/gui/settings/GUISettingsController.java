package controller.gui.settings;

import java.io.IOException;

import javafx.fxml.FXML;

public interface GUISettingsController {
    /**
     * 
     * @return secreenSizeBox.value
     *      Screen size set at the moment in double
     */
    Double getScreenSizeDimensions();

    /**
     * 
     * @return musicSettings
     *      Current settings for music
     */
    boolean isMusicSettings();

    /**
     * 
     * @return soundSettings
     *      Current settings for sounds
     */
    boolean isSoundSettings();

    /**
     * 
     * @return screenSize
     *      Current settings for screen
     */
    String getScreenSize();

    /**
     * 
     * @param music
     *      Sets the starting music setting
     * @param sound
     *      Sets the starting sound setting
     * @param screenSize
     *      Sets the starting screen size setting
     */
    void initializeSettings(boolean music, boolean sound, String screenSize);

    @FXML
    void setMusicSettings();

    @FXML
    void screenSizeSelection();

    @FXML
    void exitBtnOnClickHandler() throws IOException;
}
