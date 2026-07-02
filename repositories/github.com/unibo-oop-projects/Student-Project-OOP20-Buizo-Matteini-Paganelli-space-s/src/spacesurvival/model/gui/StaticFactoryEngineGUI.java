package spacesurvival.model.gui;

import spacesurvival.model.gui.dead.EngineDead;
import spacesurvival.model.gui.game.EngineGame;
import spacesurvival.model.gui.help.EngineHelp;
import spacesurvival.model.gui.loading.EngineLoading;
import spacesurvival.model.gui.menu.EngineMenu;
import spacesurvival.model.gui.pause.EnginePause;
import spacesurvival.model.gui.scoreboard.EngineScoreboard;
import spacesurvival.model.gui.settings.EngineSettings;
import spacesurvival.model.gui.sound.EngineSound;

/**
 * StaticFactoryEngineGUI. create GUI model.
 */
public final class StaticFactoryEngineGUI {

    /**
     * Create a model of the loading GUI.
     *
     * @return loading GUI model.
     */
    public static EngineLoading createEngineLoading() {
        return new EngineLoading();
    }

    /**
     * Create a model of the game GUI.
     *
     * @return game GUI model.
     */
    public static EngineGame createEngineGame() {
        return new EngineGame();
    }

    /**
     * Create a model of the menu GUI.
     *
     * @return menu GUI model.
     */
    public static EngineMenu createEngineMenu() {
        return new EngineMenu();
    }

    /**
     * Create a model of the settings GUI.
     *
     * @return settings GUI model.
     */
    public static EngineSettings createEngineSettings() {
        return new EngineSettings();
    }

    /**
     * Create a model of the scoreboard GUI.
     *
     * @return scoreboard GUI model.
     */
    public static EngineScoreboard createEngineScoreboard() {
        return new EngineScoreboard();
    }

    /**
     * Create a model of the sound GUI.
     *
     * @return sound GUI model.
     */
    public static EngineSound createEngineSound() {
        return new EngineSound();
    }

    /**
     * Create a model of the help GUI.
     *
     * @return help GUI model
     */
    public static EngineHelp createEngineHelp() {
        return new EngineHelp();
    }

    /**
     * Create a model of the pause GUI.
     *
     * @return pause GUI model.
     */
    public static EnginePause createEnginePause() {
        return new EnginePause();
    }

    /**
     * Create a model of the dead GUI.
     * @return dead GUI model.
     */
    public static EngineDead createEngineDead() {
        return new EngineDead();
    }

    private StaticFactoryEngineGUI() {
    }
}
