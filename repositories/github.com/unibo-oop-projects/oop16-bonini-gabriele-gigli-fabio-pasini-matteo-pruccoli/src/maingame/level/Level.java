package maingame.level;

import java.awt.Dimension;
import java.util.List;

import maingame.entity.Entity;
import maingame.entity.item.Item;
import maingame.entity.mob.Mob;
import maingame.entity.mob.player.Player;
import maingame.entity.projectile.Projectile;
import maingame.level.tile.Tile;
import util.Vector2;

/** Interfaccia Level. */

public interface Level {

    /** Trova le tile occupate da un oggetto solido. */
    void findSolidTiles();

    /**
     * Ritorna la tile presente nel livello alla data posizione.
     * 
     * @param position
     *            posizione della tile nel livello in precisione tile.
     * @return Tile alla data posizione.
     */
    Tile getTile(Vector2<Integer> position);

    /**
     * Ritorna la tile presente nel livello alla data posizione sommando un dato
     * offset.
     * 
     * @param position
     *            posizione della tile nel livello in precisione tile.
     * @param offset
     *            offset a partire dalla posizione data.
     * @return Colore assegnato alla Tile alla data posizione.
     */
    int getTileColor(Vector2<Integer> position, Vector2<Integer> offset);

    /**
     * Ritorna l'illuminazione di un mob in base a distanza da fonte luminosa.
     * 
     * @param m
     *            Mob del quale deve essere calcolata l'illuminazione.
     * @return Intensità dell'illuminazione.
     */
    double getLightIntensity(Mob m);

    /**
     * Funziona chiamata 60 volte al secondo, gestisce la logica di level.
     * 
     * @param fixedLuminosity
     *            Se diverso da -1 il livello viene renderizzato con una
     *            luminosità predefinita, il ciclo notte/giorno è disattivato.
     */

    void update(int fixedLuminosity);

    /**
     * Aggiunge un entità al livello.
     * 
     * @param e
     *            Entità da aggiungere.
     */

    void add(Entity e);

    /**
     * Ritorna una lista do Mob presenti nel livello ad un certo raggio
     * dall'entità passata come parametro.
     * 
     * @param e
     *            Entità dalla quale si calcola la distanza.
     * @param radius
     *            raggio di ricerca dei mobs.
     * @return Lista di Mob ad un certo raggio dall'entità.
     */
    List<Mob> getMobs(Entity e, int radius);

    /**
     * Ritorna una lista do Item presenti nel livello ad un certo raggio dal Mob
     * passata come parametro.
     * 
     * @param e
     *            Mob dal quale si calcola la distanza.
     * @param radius
     *            raggio di ricerca degli Items.
     * @return Lista di Items ad un certo raggio dal Mob.
     */

    List<Item> getItems(Entity e, int radius);

    /**
     * Renderizza il livello in base ad un'offset passato come parametro.
     * 
     * @param scroll
     *            Offset di renderizzazione del livello.
     */

    void render(Vector2<Integer> scroll);

    /**
     * Ritorna il player appartenente al livello.
     * 
     * @return Player del livello.
     */

    Player getPlayer();

    /**
     * Ritorna tutti i proiettili presenti nel livello.
     * 
     * @return Una lista contenente i proiettili presenti nel livello.
     */

    List<Projectile> getProjectiles();

    /**
     * Ritorna la posizione del Player sullo schermo.
     * 
     * @return Posizione del Player sullo schermo.
     */

    Vector2<Integer> getPlayerScreenPosition();

    /**
     * Ritorna il numero di Tile presenti nel livello.
     * 
     * @return Dimensione in tile del livello.
     */

    Dimension getDimension();

    /**
     * Aggiunge una SandTile all'array contenete tutte le tile di tipo SandTile
     * calpestate.
     * 
     * @param position
     *            Posizione della tile da aggiungere.
     */

    void addSandStep(Vector2<Integer> position);

    /**
     * Ritorna l'array contenente le SandTile calpestate.
     * 
     * @return Array contenente le coordinate delle SandTile calpestate.
     */

    List<Vector2<Integer>> getSandSteps();

    /**
     * Ritorna la posizione iniziale del player nel livello.
     * 
     * @return posizione iniziale del player.
     */
    Vector2<Integer> getPlayerPos();

    /**
     * Aggiunge la posizione di una torcia alla lista torchCoordinates.
     * 
     * @param position
     *            Coordinate della torcia.
     */

    void addTorchCoordinate(Vector2<Integer> position);

    /**
     * Aggiunge la posizione di un albero alla lista treeCoordinates.
     * 
     * @param position
     *            Coordinate dell'albero.
     */
    void addTreeCoordinate(Vector2<Integer> position);

    /**
     * Aggiunge la posizione di un muro alla lista WallCoordinates.
     * 
     * @param position
     *            Coordinate del muro.
     */

    void addWallCoordinate(Vector2<Integer> position);

    /**
     * Ritorna la luminosità del livello.
     * 
     * @return luminosità del livello.
     */
    int getLuminosity();

    /**
     * Setta la luminosità del livello.
     * 
     * @param luminosity
     *            Valore di luminosità.
     */

    void setLuminosity(int luminosity);

    /**
     * Ritorna la luminosità del livello.
     * 
     * @return Intensità luminosa del livello.
     */
    int getBrightness();

    /**
     * Setta la luminosità del livello.
     * 
     * @param brightness
     *            Valore di luminosità.
     */

    void setBrightness(int brightness);

    /**
     * Ritorna il valore della variabile day.
     * 
     * @return Valore che indica se è giotno o notte.
     */

    boolean isDay();

    /**
     * Setta la variabile day.
     * 
     * @param day
     *            True se è giorno false altrimenti.
     */

    void setDay(boolean day);

    /**
     * Ritorna la lista di mobs.
     * 
     * @return Lista di Mobs.
     */
    List<Mob> getMobs();

    /**
     * Setta la lista di mobs.
     * 
     * @param mobs
     *            Lista di mobs.
     */
    void setMobs(List<Mob> mobs);

    /**
     * Ritorna lista di items.
     * 
     * @return lista di items.
     */
    List<Item> getItems();

    /**
     * Setta lista di items.
     * 
     * @param items
     *            lista di items
     */
    void setItems(List<Item> items);

    /**
     * Ritorna una lista di posizioni dei mobs nel livello.
     * 
     * @param mob
     *            da escludere dalla lista.
     * @return Lista coordinate mobs.
     */
    List<Vector2<Integer>> getCoordinateMobs(Mob mob);

    /**
     * Verifica se sulla tile verso cui ci si sta muovendo è presente un oggetto
     * solido.
     * 
     * @param position
     *            Posizione di cui verificare collisione.
     * @param movement
     *            Movimento.
     * 
     * @return True se sulla tile è presente un oggetto solido,false altrimenti.
     */
    boolean isSolidTileItem(Vector2<Integer> position, Vector2<Integer> movement);

    /**
     * Ritorna un array di boolean che indica le tile su cui è presente un
     * oggetto solido.
     * 
     * @return Lista di tiles con oggetti solidi.
     */
    boolean[] getSolidTiles();
}