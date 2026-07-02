package it.unibo.model.tiles.tileset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import it.unibo.commons.Constants;
import it.unibo.model.documentextractor.DocumentExtractor;
import it.unibo.model.documentextractor.TagType;
import it.unibo.model.documentextractor.factory.DocumentExtractorFactoryImpl;
import it.unibo.model.tiles.Tile;
import it.unibo.model.tiles.TileImpl;

/**
 * Implementation of the TileSet interface for parsing TMX file and extracting tiles.
 */
public class TileSetImpl implements TileSet {

    private final String tmx;
    private final List<Tile> tiles;

    /**
     * Constructs a new TileSetImpl object by specifying the TMX file to parse.
     *
     * @param tmx The path to the TMX file to parse.
     */
    public TileSetImpl(final String tmx) {
        this.tmx = tmx;
        this.tiles = new ArrayList<>();
    }

    /**
     * Reads the TMX file, parses it, and extracts tiles.
     * 
     * @return A list of Tile objects extracted from the TMX file.
     */
    @Override
    public List<Tile> read() {
        parseTMXFile();
        return Collections.unmodifiableList(this.tiles);
    }

    /**
     * Parses the TMX file to identify sprite sheets and divide them into tiles.
     */
    private void parseTMXFile() {
        final DocumentExtractor documentExtractor = new DocumentExtractorFactoryImpl()
            .createDocumentExtractor(this.tmx);
        final NodeList tileSetNodeList = documentExtractor
            .getElements(TagType.TILESET);

        IntStream.range(0, tileSetNodeList.getLength())
            .mapToObj(i -> (Element) tileSetNodeList.item(i))
            .forEach(tilesetElement -> {
                divideSpriteSheet(
                    Integer.parseInt(Objects.requireNonNull(
                        tilesetElement.getElementsByTagName(TagType.IMAGE.toString())
                            .item(0))
                            .getAttributes()
                            .getNamedItem(TagType.WIDTH.toString())
                            .getTextContent()),
                    Integer.parseInt(Objects.requireNonNull(
                        tilesetElement.getElementsByTagName(TagType.IMAGE.toString())
                            .item(0))
                            .getAttributes()
                            .getNamedItem(TagType.HEIGHT.toString())
                            .getTextContent()),
                    Objects.requireNonNull(
                        tilesetElement.getElementsByTagName(TagType.IMAGE.toString())
                            .item(0))
                            .getAttributes()
                            .getNamedItem(TagType.SOURCE.toString())
                            .getTextContent());
            });
    }

    /**
     * Splits the sprite sheet into tiles of the appropriate size.
     *
     * @param width    The width of the tileset image.
     * @param height   The height of the tileset image.
     * @param srcImage The source image of the tileset.
     */
    private void divideSpriteSheet(final int width, final int height, final String srcImage) {
        IntStream.range(0, height / Constants.TILE_SIZE)
                .boxed()
                .flatMap(
                    row -> IntStream.range(0, width / Constants.TILE_SIZE)
                .mapToObj(
                    column -> new TileImpl(
                        column * Constants.TILE_SIZE,
                        row * Constants.TILE_SIZE,
                        srcImage)))
                .forEach(tiles::add);
    }

}
