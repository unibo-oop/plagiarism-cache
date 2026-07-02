package zombieversity.controller.sound;

import javafx.scene.media.Media;

/**
 * Interface to manage sounds.
 */
public interface SoundController {

    /**
     * method to check if a file is audio.
     * 
     * @param name
     * @return boolean - true if the file is audio file
     */
    boolean checkIsMedia(String name);

    /**
     * Adding new audio source by url.
     * 
     * @param name
     * @param url
     */
    void addSound(String name, String url);

    /**
     * Adding new sound by media.
     * 
     * @param name
     * @param media
     */
    void addSound(String name, Media media);

    /**
     * stop all the audios.
     */
    void stopGeneral();

    /**
     * start all the audios.
     */
    void startGeneral();

    /**
     * Start specific audio.
     * 
     * @param name
     */
    void startSound(String name);

    /**
     * stop specific audio.
     * 
     * @param name
     */
    void stopSound(String name);

}
