package editor.model;

import editor.model.ModelEditorImpl.CMB;
import editor.model.ModelEditorImpl.Tool;
import util.Vector2;
/**
 * Interfaccia di ModelEditor.
 */
public interface ModelEditor {
    /**
     * Setta il valore di allSelected.
     * @param allSelected true se sono selezionati tutti gli oggetti.
     */
    void setAllSelected(boolean allSelected);

    /**
     * @return true se tutti gli oggetti sono selezionati
     */
    boolean isSelected();

    /**
     * Imposta il valore di uscita.
     * @param exit true se voglio uscire
     */
    void setExit(boolean exit);

    /**
     * @return true se voglio uscire
     */
    boolean isExit();

    /**
     * Setta il tool in utilizzo.
     * @param usingTool tool in utilizzo
     */
    void setUsingTool(Tool usingTool);

    /**
     * @return tool in utilizzo
     */
    Tool getUsingTool();

    /**
     * Setta il valore della variabile che tiene traccia
     * dell'ultima modifica dopo il salvataggio.
     * 
     * @param modifiedAfterSave true se effettuo altre modifiche
     */
    void setModifiedAfterSave(boolean modifiedAfterSave);

    /**
     * @return se sono state fatte modifiche dopo l'ultimo salvataggio.
     */
    boolean isModifiedAfterSave();

    /**
     * Setta il valore della lminosità.
     * @param lumos valore luminosità
     */
    void setLumos(int lumos);

    /**
     * @return valore della luminosità
     */
    int getLumos();

    /**
     * Setta il valore della velocità di scorrimento.
     * 
     * @param velocity valore velocità scorrimento
     */
    void setVelocityScroll(int velocity);

    /**
     * @return velocità di scorrimento
     */
    int getVelocityScroll();

    /**
     * Setta la visibilità della griglia.
     * @param show true se la voglio visualizzare
     */
    void setShoGrid(boolean show);

    /**
     * @return se visualizza a meno la griglia
     */
    boolean isShowGrid();

    /**
     * disabilita la deselezione delle combo box.
     * @param deselect disabilita deselezione.
     */
    void setDeselectCMB(boolean deselect);

    /**
     * @return se la deselezione delle cmb è attivita.
     */
    boolean isDeselectCMB();

    /**
     * @return tipo di combo box selezionata.
     */
    CMB getSelectedCMB();

    /**
     * Setta il tipo di combo box selezionata.
     * @param cmb combo box selezionata
     */
    void setSelectedCMB(CMB cmb);

    /**
     * Setta il valore delle coordinate attuali del mouse.
     * @param coordinate coordinate attuali del mouse
     */
    void setMouseCoordinate(Vector2<Integer> coordinate);

    /**
     * @return coordinate attuali del mouse
     */
    Vector2<Integer> getMouseCoordinate();

    /**
     * Setta il valore delle coordinate iniziali dal momento in cu inizia il drag.
     * @param coordinate coordinate attuali del mouse
     */
    void setMouseStartCoordinate(Vector2<Integer> coordinate);

    /**
     * @return coordinate iniziali del drag del mouse
     */
    Vector2<Integer> getMouseSartCoordinate();

    /**
     * Setta il valore delle coordinate del player.
     * @param coordinate coordinate attuali del mouse
     */
    void setPlayerCoordinate(Vector2<Integer> coordinate);

    /**
     * @return coordinate player
     */
    Vector2<Integer> getPlayerCoordinate();

    /**
     * Setta il valore del bottone del mouse premuto.
     * @param button bottone premuto
     */
    void setButton(int button);

    /**
     * @return valore del bottone premuto.
     */
    int getButton();
}
