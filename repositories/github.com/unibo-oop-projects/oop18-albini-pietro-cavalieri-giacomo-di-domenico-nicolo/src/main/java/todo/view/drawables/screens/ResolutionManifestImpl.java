package todo.view.drawables.screens;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.badlogic.gdx.Gdx;

public class ResolutionManifestImpl implements ResolutionManifest {
    private static final float VALUEBOX_TO_TILE_RATIO = 0.67f;
    private final XPathDocument manifest;
    private final int originalWidth;
    private final int originalHeight;
    private final int floorTileSize;
    private final int floorFirstTileY;

    public ResolutionManifestImpl(final String aspectRatioDirectory) {
        // Try and load the manifest: the exceptions are unchecked because they should
        // never happen as the stream is opened from an internal file and it's not
        // user-operated
        try (InputStream manifestStream = Gdx.files.internal(aspectRatioDirectory + "/manifest.xml").read()) {
            try {
                this.manifest = new XPathDocument(
                        DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(manifestStream), "/manifest");
            } catch (final SAXException e) {
                throw new IllegalStateException("The XML parser has encountered a SAX exception", e);
            } catch (final IOException e) {
                throw new IllegalArgumentException("The provided manifest file has encountered an IO error", e);
            } catch (final ParserConfigurationException e) {
                throw new IllegalStateException("The XML parser has been improperly configured", e);
            }
        } catch (final IOException e) {
            throw new IllegalStateException("Reading the manifest file has triggered an IO exception", e);
        }
        // Populate the fields of the class here, so they can be immutable
        this.originalWidth = this.manifest.getIntegerFromXPath("/properties/originalWidth");
        this.originalHeight = this.manifest.getIntegerFromXPath("/properties/originalHeight");
        this.floorTileSize = this.manifest.getIntegerFromXPath("/properties/floorTileSize");
        this.floorFirstTileY = this.manifest.getIntegerFromXPath("/properties/firstTileY");
    }

    @Override
    public float getAspectRatio() {
        return (float) this.originalWidth / (float) this.originalHeight;
    }

    @Override
    public int getOriginalWidth() {
        return this.originalWidth;
    }

    @Override
    public int getOriginalHeight() {
        return this.originalHeight;
    }

    @Override
    public float getFloorTileSize() {
        return this.floorTileSize;
    }

    @Override
    public float getScaledFloorTileSize() {
        return getFloorTileSize() * ResolutionManagerImpl.getInstance().getScaleFactor();
    }

    @Override
    public float getScaledValueBoxSize() {
        return getScaledFloorTileSize() * VALUEBOX_TO_TILE_RATIO;
    }

    @Override
    public float getFirstTileY() {
        return this.floorFirstTileY;
    }

    @Override
    public XPathDocument getQueryableManifest() {
        return this.manifest;
    }
}
