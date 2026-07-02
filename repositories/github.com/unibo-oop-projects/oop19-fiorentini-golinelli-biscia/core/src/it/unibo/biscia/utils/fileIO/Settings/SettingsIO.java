package it.unibo.biscia.utils.fileIO.Settings;

import it.unibo.biscia.core.Controller;
import it.unibo.biscia.utils.Pair;

import java.util.List;
import java.util.Map;

/**
 * Getting and adding the game's settings from and to the setting's file.
 *
 */
public interface SettingsIO {

    /**
     * Get the number of players setted. This corresponds to the {@code "Players"}
     * entry key.
     * 
     * @return A Pair containing the entry key and the number of players setted.
     */
    Pair<String, Integer> getNumberOfPlayers();

    /**
     * Get Player1's name. This corresponds to the {@code "Name player1"} entry key.
     * 
     * @return A Pair containing the entry key and the Player1's name
     */
    Pair<String, String> getNamePlayer1();

    /**
     * Get Player2's name. This corresponds to the {@code "Name player2"} entry key.
     * 
     * @return A Pair containing the entry key and the Player2's name
     */
    Pair<String, String> getNamePlayer2();

    /**
     * Get initial speed. This corresponds to the {@code "Initial speed"} entry key.
     * 
     * @return A Pair containing the entry key and the initial speed.
     */
    Pair<String, Controller.Speed> getInitialSpeed();

    /**
     * Get if the increasing speed is setted. This corresponds to the
     * {@code "increasing speed"} entry key.
     * 
     * @return A Pair containing the entry key and the increasing speed value
     */
    Pair<String, Boolean> getIncreasingSpeed();

    /**
     * Get if the music is setted. This corresponds to the {@code "Music"} entry
     * key.
     * 
     * @return A Pair containing the entry key and the Music value
     */
    Pair<String, Boolean> getMusic();

    /**
     * Get if the sounds are setted. This corresponds to the {@code "Sounds"} entry
     * key.
     * 
     * @return A Pair containing the entry key and the sounds value.
     */
    Pair<String, Boolean> getSounds();

    /**
     * Get all the settings.
     * 
     * @return A List {@link Pair} containing the setting's name and value
     */
    List<Pair<String, ?>> getSettings();

    /**
     * Adds all the settings as a Map.
     * 
     * @param settings the Map {@code setting's name -> setting's value}
     */
    void addSettings(Map<String, ?> settings);

}
