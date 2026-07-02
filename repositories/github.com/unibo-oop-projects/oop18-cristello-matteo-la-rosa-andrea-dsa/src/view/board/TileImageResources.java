package view.board;

import javax.swing.JComponent;

import model.board.Tile;
import model.board.TileType;

/**
 * This is a tile image resources.
 */


public class TileImageResources extends JComponent {

    private static final Integer LOWTRIANGLE = 0;
    private static final Integer UPTRIANGLE = 3;
    private static final Integer LOWSQUARE = 4;
    private static final Integer UPSQUARE = 7;
    private static final Integer LOWPENTAGON = 8;
    private static final Integer UPPENTAGON = 11;
    private static final Integer LOWHEXAGON = 12;
    private static final Integer UPHEXAGON = 15;

    private static final long serialVersionUID = 1L;

    private final String address;

    /**
     * @param tile The tile of wich should get the image address.
     */
    public TileImageResources(final Tile tile) {
        super();
        this.address = this.returnTileIconAddr(tile);
    }

    /**
     * @param tile
     * @return
     */
    private String returnTileIconAddr(final Tile tile) {
        String address = null;

        if (tile.getType().equals(TileType.TREASURE.tileType())) {
            if (tile.getValue() >= LOWTRIANGLE && tile.getValue() <= UPTRIANGLE) {
                address = new String(TileTypeAddressView.TRIANGLE.getAddress());
            } else if (tile.getValue() >= LOWSQUARE && tile.getValue() <= UPSQUARE) {
                address = new String(TileTypeAddressView.SQUARE.getAddress());
            } else if (tile.getValue() >= LOWPENTAGON && tile.getValue() <= UPPENTAGON) {
                address = new String(TileTypeAddressView.PENTAGON.getAddress());
            } else if (tile.getValue() >= LOWHEXAGON && tile.getValue() <= UPHEXAGON) {
                address = new String(TileTypeAddressView.HEXAGON.getAddress());
            }
        } else if (tile.getType().equals(TileType.EMPTY.tileType())) {
            address = new String(TileTypeAddressView.EMPTY.getAddress());
        } else if (tile.getType().equals(TileType.TREASUREGROUP.tileType())) {
            address = new String(TileTypeAddressView.TREASUREGROUP.getAddress());
        }
        return address;
    }

    /**
     * @return The address of the image.
     */
    public String getImage() {
        return this.address;
    }

}
