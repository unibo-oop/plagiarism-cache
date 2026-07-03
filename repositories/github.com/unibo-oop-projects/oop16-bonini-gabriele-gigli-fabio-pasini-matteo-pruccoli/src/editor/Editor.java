package editor;

import java.awt.Dimension;
import java.io.File;
import java.util.List;

import editor.model.ModelEditorImpl.CMB;
import editor.model.ModelEditorImpl.Tool;
import maingame.level.Level;
import util.Vector2;

/** Interfaccia editor. */
public interface Editor {

    /**
     * Crea un livello vuoto.
     * 
     * @param dimension
     *            Dimensione del livello da creare.
     */
    void newLevel(Dimension dimension);

    /**
     * Carica un livello da file.
     * 
     * @param file
     *          File png in cui è codificato il livello.
     */
    void loadLevel(File file);

    /**
     * Annulla l'ultima operazione eseguita.
     */
    void undo();

    /**
     * Ripete l'ultima operazione annullata.
     */
    void redo();

    /**
     * Funzione eseguita 60 volte al secondo,gestisce la logica.
     */

    void update();

    /** Funzione utilizzata per la renderizzazione del livello. */

    void render();

    /**
     * Crea un livello a partire dagli array tiles e mobsItems che contengono
     * colori corrispondent a tile e mobs/oggetti.
     */

    void createLevel();

    /**
     * Aggiorna la cronologia.
     */
    void updateHistory();

    /**
     * Effettua il salvataggio su file del livello creato.
     * 
     * @param file
     *            File in cui salvare il livello.
     */

    void save(File file);

    /**
     * Gestisce l'eliminazione/aggiunta di tiles,mobs ed oggetto.
     * 
     * @param tool
     *            Indica il tipo di tool utilizzato per la modifica del livello.
     * @param mousePosition
     *            Posizione corrente del mouse.
     * @param type
     *            Indica se si sta aggiungendo una tile, mob od oggetto
     * @param index
     *            Indice della tile, mob od oggetto che si sta aggiungendo.
     * @param dragging
     *            Indica se si sta spostando il mouse tenendo permuto un tasto.
     */

    void toolsActions(Tool tool, Vector2<Integer> mousePosition, CMB type, int index, boolean dragging);

    /**
     * Gestisce lo scorrimento del livello quando si trascina il mouse.
     * 
     * @param mousePosition
     *            Posizione corrente del mouse.
     * @param mouseStartPosition
     *            Posizione del cursore nel momento che si è iniziato a
     *            trascinare il mouse.
     * @param playerStartPosition
     *            Posizione del player nel livello nel momento che si è iniziato
     *            a trascinare il mouse.
     * @param scrollVelocity
     *            /** Velocità di scorrimento del livello.
     */
    void mapMovement(Vector2<Integer> mousePosition, Vector2<Integer> mouseStartPosition, Vector2<Integer> playerStartPosition,
            int scrollVelocity);

    /**
     * Ritorna il livello corrente.
     * 
     * @return Livello che si sta modificando.
     */
    Level getLevel();

    /**
     * Ritorna il player del livello.
     * 
     * @return Player del livello corrente.
     * 
     */
    Vector2<Integer> getPlayer();

    /** Effettua il reset dell'editor. */
    void reset();

    /** Conferma il salvataggio del livello . */
    void confirmSave();

    /**
     * Ritorna le tiles del livello.
     * 
     * @return Tiles.
     */
    int[] getTiles();

    /**
     * Ritorna i mob/items del livello.
     * 
     * @return Mobs/items del livello.
     */
    int[] getMobsItems();

    /**
     * Ritorna la cronologia di modifca delle tiles.
     * 
     * @return Cronologia tiles.
     */
    List<int[]> getTilesHistory();

    /**
     * Ritorna la cronologia di modifca di mobs ed items.
     * 
     * @return Cronologia mobs e items.
     */
    List<int[]> getMobsItemsHistory();

}