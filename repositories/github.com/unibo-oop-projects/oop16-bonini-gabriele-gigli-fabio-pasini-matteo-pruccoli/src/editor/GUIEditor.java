package editor;

import java.awt.Canvas;

import editor.model.ModelEditorImpl.CMB;
import editor.model.ModelEditorImpl.Tool;
/**
 * Interfaccia GUIEditor.
 */
public interface GUIEditor {

    /**
     * Aggiungie il player alla combo box dei mob.
     */
    void addPlayer();

    /**
     * Rimuove il player dalla combo box dei mob.
     */
    void removePlayer();

    /**
     * Ritorna l'indice della combo box richiesta.
     * 
     * @param cmb
     *            combbox specificata
     * @return indice
     */
    int getCmbIndex(CMB cmb);

    /**
     * Seleziona una specifica combo box.
     * 
     * @param cmb
     *            la combo box da selezionare
     */
    void setSelectedCMB(CMB cmb);

    /**
     * Setta l'elemento selezionato di una combo box.
     * 
     * @param cmb
     *            combo box specifica
     * @param index
     *            indice da selezionare
     */
    void setCmbIndex(CMB cmb, int index);

    /**
     * Abilita il menu del tool specificato.
     * 
     * @param tool
     *            menu del tool
     * @param enable
     *            true per abilitarlo
     */
    void enableRdbTool(Tool tool, boolean enable);

    /**
     * abilita menu item new.
     * 
     * @param enable
     *            true per abilitarlo
     */
    void enableMenuItemNew(boolean enable);

    /**
     * abilita menu item save.
     * 
     * @param enable
     *            true per abilitarlo
     */
    void enablemenuItemSave(boolean enable);

    /**
     * abilita menu item load.
     * 
     * @param enable
     *            true per abilitarlo
     */
    void enableMenuItemLoad(boolean enable);

    /**
     * abilita menu item undo.
     * 
     * @param enable
     *            true per abilitarlo
     */
    void enableMenuItemUndo(boolean enable);

    /**
     * abilita menu item redo.
     * 
     * @param enable
     *            true per abilitarlo
     */
    void enableMenuItemRedo(boolean enable);

    /**
     * abilita menu item select all.
     * 
     * @param enable
     *            true per abilitarlo
     */
    void enableMenuItemSelectAll(boolean enable);

    /**
     * abilita combo box.
     * 
     * @param cmb
     *            specifica la combo box
     * @param enable
     *            true per abilitarla
     */
    void enableCmb(CMB cmb, boolean enable);

    /**
     * @return il canvas contenente la mappa di gioco
     */
    Canvas getCanvas();

    /**
     * Imposta tutte le combo box senza elementi selezionati. 
     */
    void deselect();

    /**
     * Mostra il frame dell'editor.
     */
    void open();

    /**
     * Chiude il frame dell'editor.
     */
    void disposeFrame();

    /**
     * Reimposta i valori dell'editor a quelli predefiniti.
     */
    void reset();

    /**
     * Seleziona il radio btn del tool specificato.
     * 
     * @param tool
     *            tool da selezionare
     * @param enable
     *            true per selezionarlo
     */
    void selectRDBTool(Tool tool, boolean enable);

    /**
     * Setta le impostazioni per il posizionamento degli oggetti. 
     */
    void setDraw();
}
