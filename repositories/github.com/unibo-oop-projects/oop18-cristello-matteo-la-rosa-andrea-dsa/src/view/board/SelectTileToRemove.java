package view.board;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controller.DSAController;
import model.board.Tile;
import model.utils.PossibleActionAfterMove;
import view.DimensionUtils;

/**
 * This is the frame that menage the release of a tile.
 */
public class SelectTileToRemove extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JFrame frame = new JFrame();
    private String[] possibleValues = {};
    private final JPanel dialogPanel = new JPanel();
    private final List<Tile> listOfTileToRemove = new ArrayList<Tile>();
    private static final Integer TILEHEIGHT = 50;
    private static final Integer TILEWIDTH = 50;

    /**
     * This is the constructor of the class.
     * 
     * @param listOfTileToRemove
     *                               The treasureTiles of the player.
     * @param dsaControllerP
     *                               The controller.
     */
    public SelectTileToRemove(final List<Tile> listOfTileToRemove, final DSAController dsaControllerP) {
        super();
        final DSAController dsaController = dsaControllerP;
        this.listOfTileToRemove.addAll(listOfTileToRemove);
        final List<String> listOfNumbers = new ArrayList<String>();
        IntStream.range(1, this.listOfTileToRemove.size() + 1).forEach(i -> {
            listOfNumbers.add(String.valueOf(i));
            String[] tmpArray = new String[this.listOfTileToRemove.size()]; // Modificata per evitare pmd
            tmpArray = listOfNumbers.toArray(tmpArray);
            possibleValues = tmpArray; 
            final DrawImagePanel tileImage = new DrawImagePanel(
                    (new TileImageResources(this.listOfTileToRemove.get(i - 1)).getImage()),
                    DimensionUtils.resizeForScreenDimension(new Dimension(TILEWIDTH, TILEHEIGHT))); 

            tileImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            dialogPanel.add(tileImage);
            dialogPanel.add(new JLabel("(" + i + ")"));
        });

        this.frame.add(dialogPanel);

        final String selInput = (String) JOptionPane.showInputDialog(null, dialogPanel, "Choose tile to remove",
                JOptionPane.QUESTION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        if (selInput == null) {
            dsaController.manageAction(PossibleActionAfterMove.RELEASE, 0);
        } else {
            final Integer tmpInt = Integer.valueOf(selInput); // per evitare pmd
            dsaController.manageAction(PossibleActionAfterMove.RELEASE, tmpInt - 1);
        }

    }

}