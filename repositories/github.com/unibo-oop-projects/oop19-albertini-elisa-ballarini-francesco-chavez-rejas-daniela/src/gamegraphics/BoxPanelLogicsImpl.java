package gamegraphics;

import pair.Pair;

/**
 * Logics Class for the BoxPanel.
 * 
 */
public class BoxPanelLogicsImpl extends GridPanelLogicsImpl implements BoxPanelLogics {
    /**
     * @param width : Width of the Panel
     * @param height : Height of the Panel
     * @param rows : Rows of the Panel
     * @param cols : Columns of the Panel
     */
    public BoxPanelLogicsImpl(final int width, final int height, final int rows, final int cols) {
        super(width, height, rows, cols);
        this.lowerDimensions();
    }

    @Override
    public final void lowerDimensions() {
        // Makes width and height slightly lower
        this.setSize(new Pair<Integer, Integer>(this.getSize().getX() - (this.getSize().getX() / 10),
                this.getSize().getY() - (this.getSize().getY() / 10)));

        this.cancelProtrusion();
        this.setBoxSize(new Pair<Integer, Integer>(this.getSize().getY() / this.getnLines().getX(),
                this.getSize().getX() / this.getnLines().getY()));
    }
}
