package editor.model;

import util.Vector2;
import util.Vector2Impl;

/**
 * Model per Editor.
 */
public final class ModelEditorImpl implements ModelEditor {
    private boolean allSelected;
    private boolean exit;
    private Tool tool;
    private boolean modifiedAfterSave;
    private int scrollVelocity;
    private int lumos;
    private boolean showGrid;
    private boolean deselectCMB;
    private CMB selectedCMB;
    private Vector2<Integer> mouseCoordinate = new Vector2Impl<Integer>(-1, -1);
    private Vector2<Integer> mouseStartCoordinate = new Vector2Impl<Integer>(-1, -1);
    private Vector2<Integer> playerCoordinate = new Vector2Impl<Integer>(0, 0);
    private int button = -1;

    private static ModelEditor modelEditor = new ModelEditorImpl();

    /**
     * Definisce i possibili tool.
     */
    public enum Tool {
        /**
         * Tool per posizionamento.
         */
        DRAW, 
        /**
         * Tool per cancellazione.
         */
        CLEAR;
    }

    /**
     * Definisce diversi tipo si combo box per i tipo di oggetti da posizionare.
     */
    public enum CMB {
        /**
         * Identifica la combo box per i mob.
         */
        MOB, 
        /**
         * Identifica la combo box per le tile.
         */
        TILE, 
        /**
         * Identifica la combo box per gli item.
         */
        ITEM;
    }

    /**
     * Costruttore per ModelEditor, inizializza i campi.
     */
    public ModelEditorImpl() {
        this.allSelected = false;
        this.exit = false;
        this.tool = null;
        modifiedAfterSave = false;
        showGrid = false;
        deselectCMB = false;
        selectedCMB = null;
    }

    /**
     * @return Model statica per GUIEditor.
     */
    public static ModelEditor getModelEditor() {
        return modelEditor;
    }

    @Override
    public void setAllSelected(final boolean allSelected) {
        this.allSelected = allSelected;
    }

    @Override
    public boolean isSelected() {
        return this.allSelected;
    }

    @Override
    public void setExit(final boolean exit) {
        this.exit = exit;
    }

    @Override
    public boolean isExit() {
        return this.exit;
    }

    @Override
    public void setUsingTool(final Tool usingTool) {
        this.tool = usingTool;
    }

    @Override
    public Tool getUsingTool() {
        return this.tool;
    }

    @Override
    public void setModifiedAfterSave(final boolean modifiedAfterSave) {
        this.modifiedAfterSave = modifiedAfterSave;
    }

    @Override
    public boolean isModifiedAfterSave() {
        return this.modifiedAfterSave;
    }

    @Override
    public void setLumos(final int lumos) {
        if (lumos >= 0) {
            this.lumos = lumos;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getLumos() {
        return this.lumos;
    }

    @Override
    public void setVelocityScroll(final int velocity) {
        if (velocity >= 1) {
            this.scrollVelocity = velocity;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getVelocityScroll() {
        return this.scrollVelocity;
    }

    @Override
    public void setShoGrid(final boolean show) {
        this.showGrid = show;
    }

    @Override
    public boolean isShowGrid() {
        return this.showGrid;
    }

    @Override
    public void setDeselectCMB(final boolean deselect) {
        this.deselectCMB = deselect;
    }

    @Override
    public boolean isDeselectCMB() {
        return this.deselectCMB;
    }

    @Override
    public void setSelectedCMB(final CMB cmb) {
        this.selectedCMB = cmb;
    }

    @Override
    public CMB getSelectedCMB() {
        return this.selectedCMB;
    }

    @Override
    public void setMouseCoordinate(final Vector2<Integer> coordinate) {
        this.mouseCoordinate = coordinate;
    }

    @Override
    public Vector2<Integer> getMouseCoordinate() {
        return this.mouseCoordinate;
    }

    @Override
    public void setMouseStartCoordinate(final Vector2<Integer> coordinate) {
        this.mouseStartCoordinate = coordinate;
    }

    @Override
    public Vector2<Integer> getMouseSartCoordinate() {
        return this.mouseStartCoordinate;
    }

    @Override
    public void setPlayerCoordinate(final Vector2<Integer> coordinate) {
        this.playerCoordinate = coordinate;
    }

    @Override
    public Vector2<Integer> getPlayerCoordinate() {
        return this.playerCoordinate;
    }

    @Override
    public void setButton(final int button) {
        this.button = button;
    }

    @Override
    public int getButton() {
        return this.button;
    }

}
