package spacesurvival.utilities;

import spacesurvival.utilities.path.Background;
import spacesurvival.utilities.path.SoundPath;

/**
 *
 */
public enum LinkActionGUI {
    /**
     * Link menu.
     */
    LINK_MENU("Menu", SoundPath.MENU, Background.MAIN, StateLevelGUI.FOREGROUND),

    /**
     * Link game.
     */
    LINK_GAME("Game", SoundPath.GAME, Background.GAME, StateLevelGUI.FOREGROUND),

    /**
     * Link Settings.
     */
    LINK_SETTING("Settings", SoundPath.MENU, Background.MAIN, StateLevelGUI.OVERLAY),

    /**
     * Link Scoreboard.
     */
    LINK_SCOREBOARD("Scoreboard", SoundPath.MENU, Background.MAIN, StateLevelGUI.OVERLAY),

    /**
     * Link Sound.
     */
    LINK_SOUND("Sound", SoundPath.MENU, Background.MAIN, StateLevelGUI.OVERLAY),

    /**
     * Link Help.
     */
    LINK_HELP("Help", SoundPath.MENU, Background.MAIN, StateLevelGUI.OVERLAY),

    /**
     * Link Quit.
     */
    LINK_QUIT("Quit", SoundPath.MENU, Background.MAIN, StateLevelGUI.NOTHING),

    /**
     * Link Pause.
     */
    LINK_PAUSE("Pause", SoundPath.MENU, Background.MAIN, StateLevelGUI.OVERLAY),

    /**
     * Link Loading.
     */
    LINK_LOADING("Loading", SoundPath.MENU, Background.MAIN, StateLevelGUI.FOREGROUND),

    /**
     * Link Back.
     */
    LINK_BACK("Back", SoundPath.MENU, Background.MAIN, StateLevelGUI.NOTHING),

    /**
     * Link Dead.
     */
    LINK_DEAD("Dead", SoundPath.MENU, Background.DEAD2, StateLevelGUI.FOREGROUND);

    private final String name;
    private final SoundPath sound;
    private final String background;
    private final StateLevelGUI level;

    /**
     * 
     * @param name
     * @param sound
     * @param background
     * @param level
     */
    LinkActionGUI(final String name, final SoundPath sound, final String background, final StateLevelGUI level) {
        this.name = name;
        this.sound = sound;
        this.level = level;
        this.background = background;
    }

    public String getIdName() {
        return this.name;
    }

    public SoundPath getSound() {
        return this.sound;
    }

    public String getBackground() {
        return this.background;
    }

    public StateLevelGUI getStateLevel() {
        return this.level;
    }

    @Override
    public String toString() {
        return "LinkActionGUI: " + "[" + this.name + "]";
    }
}
