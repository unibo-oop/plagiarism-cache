package maingame.cursor;

/** 
 * Definisce i possibili cursori.
 */
public enum CURSOR {
    /**
     * Cursore di tipo mirino.
     */
    CURSORS_SNIPER("/cursors/mirino.png");

    private String path;

    CURSOR(final String path) {
        this.path = path;
    }

    /**
     * @return path dell'immagine del cursore
     */
    public String getPath() {
        return this.path;
    }
}
