package maingame.level;

/**
 * Enum in cui sono definiti tutti i livelli selezionabili.
 */
public enum LevelEnum {
    /** Livello principale. */
    MAIN("/levels/level.png", "/levels/level_MI.png", -1),
    /** Livello pozzo. */
    PIT("/levels/pitLevel.png", "/levels/pitLevel_MI.png", 150),
    /** Livello casa. */
    HOUSE("/levels/house.png", "/levels/house_MI.png", 0);

    private String levelPath;
    private String mobsItemsPath;
    private int luminosity;

    LevelEnum(final String levelPath, final String mobsItemsPath, final int luminosity) {
        this.levelPath = levelPath;
        this.mobsItemsPath = mobsItemsPath;
        this.luminosity = luminosity;
    }

    /**
     * Ritorna il percorso del file in cui è definito il livello.
     * 
     * @return Percorso file mobs/items.
     */
    public String getlevelPath() {
        return levelPath;
    }

    /**
     * Ritorna il percorso del file in cui sono definiti i mobs/items presenti
     * nel livello.
     * 
     * @return Percorso file livello.
     */
    public String getmobsItemsPath() {
        return mobsItemsPath;
    }

    /**
     * Ritorna la luminosità del livello.
     * 
     * @return la luminosità fissata del livello o -1 se la luminosità è
     *         variabile
     */
    public int getLuminosity() {
        return luminosity;
    }
}
