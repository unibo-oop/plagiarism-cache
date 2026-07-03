package com.jlearn.view.utilities.enums;

import java.io.File;
import java.nio.file.Path;

/**
 * Enum for vieW Audio {@link File} {@link Path}.
 */
public enum SoundFX {

    /**
     *
     */
    CLOCK("Clock.mp3"),

    /**
    *
    */
    CLOSE_MENU_ROTATE("CloseMenuRotateFX.wav"),
    /**
     *
     */
    OPEN_MENU_ROTATE("OpenMenuRotateFX.wav"),

    /**
    *
    */
    BACK_BOTTON("BackBottonFX.wav"),

    /**
    *
    */
    GAME_OVER("GameOverFX.wav"),
    /**
    *
    */
    MENU_ANTH("Menu.mp3"),
    /**
    *
    */
    OPEN_MENU_POPUP("OpenMenuPopupFX.wav"),
    /**
    *
    */
    POP("PopFX.wav"),
    /**
    *
    */
    SELECT_LVL("SelectedLevelFX.wav"),
    /**
    *
    */
    STATS_LOAD("StatisticsLoadingFX.wav"),
    /**
     *
     */
    CAMERA("Camera.wav"),
    /**
     *
     */
    AUDIO_SWITCH("AudioSwitch.wav"),
    /**
     *
     */
    DRAWER("Drawer.wav"),
    /**
     *
     */
    SELECTION1("Selection1.wav"),
    /**
     *
     */
    SELECTION2("Selection2.wav"),
    /**
     *
     */
    SPEECH_ON("SpeechOn.wav"),
    /**
     *
     */
    SPEECH_OFF("SpeechOff.wav"),
    /**
    *
    */
    SWIPE("SwipeFX.wav"),
    /**
     *
     */
    THEORY("kaelyn-the-mood.mp3"),
    /**
     *
     */
    STATISTICS("olmos-hold-me.mp3"),
    /**
     *
     */
    EXERCISE("rare-pure-ft-zeina.mp3");

    private String path;

    SoundFX(final String path) {
        this.path = path;
    }

    /**
     * @return the exerciseTypeName
     */
    public String getPath() {
        return this.path;
    }

}
