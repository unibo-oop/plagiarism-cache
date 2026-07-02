package view.board;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import model.board.GameBoard;

/**
 * This class draw the board (tile line), the boat and eventually the players on the board panel.
 */
public class BoardDrawer {
    /**
     * This method draw the entire tileline (the main board).
     * 
     * @param tileLine
     *                          The tileLine from model.
     * @param coordinates
     *                          The coordinates from model.
     * @param itemDimension
     *                          The dimension from controller.
     * @return A List of image panel applied on board, each one is a tile.
     * @throws IOException
     */
    public final List<DrawImagePanel> createTilesDrawList(final GameBoard tileLine,
            final CoordinatesForBoard coordinates,
            final Dimension itemDimension) {
        final List<DrawImagePanel> tileImagesLine = new LinkedList<DrawImagePanel>();

        tileLine.getTilesLine().entrySet().forEach(x -> {
            tileImagesLine
                    .add(new DrawImagePanel(new TileImageResources(x.getValue().getTile()).getImage(), itemDimension));
            tileImagesLine.get(x.getKey() - 1).setBounds(coordinates.getCoordinates().get(x.getKey() - 1).getX(),
                    coordinates.getCoordinates().get(x.getKey() - 1).getY(), itemDimension.width, itemDimension.height);

        });

        IntStream.range(0, tileImagesLine.size()).forEach(x -> {

            tileImagesLine.get(x).setBounds(coordinates.getCoordinates().get(x).getX(),
                    coordinates.getCoordinates().get(x).getY(), itemDimension.width, itemDimension.height);

        });

        return tileImagesLine;

    }

    /**
     * This method draw the players on the board if model say they are on it.
     * 
     * @param tileLine
     *                          The tileLine from model.
     * @param coordinates
     *                          The coordinates from model.
     * @param itemDimension
     *                          The dimension from controller.
     * @return A List of image panel applied on board, each one is a tile.
     */
    public final List<DrawImagePanel> createPlayerDrawList(final GameBoard tileLine,
            final CoordinatesForBoard coordinates,
            final Dimension itemDimension) {
        final List<DrawImagePanel> tileImagesLine = new LinkedList<DrawImagePanel>();

        tileLine.getTilesLine().entrySet().stream().filter(x -> x.getValue().getPlayer().isPresent()).forEach(x -> {
            tileImagesLine
                    .add(new DrawImagePanel(
                            new MeepleImageResources(x.getValue().getPlayer().get().getDirection(),
                                    x.getValue().getPlayer().get().getPlayerColor()).getImageAddress(),
                            itemDimension));
            tileImagesLine.get(tileImagesLine.size() - 1).setBounds(
                    coordinates.getCoordinates().get(x.getKey() - 1).getX(),
                    coordinates.getCoordinates().get(x.getKey() - 1).getY(), itemDimension.width,
                    itemDimension.height);

        });

        return tileImagesLine;

    }

    /**
     * This method draw the boat on the board panel.
     * 
     * @param coordinates
     *                          The coordinates utility manager.
     * @param itemDimension
     *                          The dimension of the item.
     * @return A panel with the image of the boat on it.
     * @throws IOException
     */
    public final DrawImagePanel drawBoat(final CoordinatesForBoard coordinates,
            final Dimension itemDimension) {
        final DrawImagePanel boatImage = new DrawImagePanel(TileTypeAddressView.BOATTILE.getAddress(), itemDimension);
        boatImage.setBounds(coordinates.getBoatCoordinates().getX(), coordinates.getBoatCoordinates().getY(),
                (int) itemDimension.getWidth(), (int) itemDimension.getWidth());
        return boatImage;

    }

}
