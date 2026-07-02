package todo.view.drawables.screens;

import java.util.HashMap;
import java.util.Map;

import org.jooq.lambda.tuple.Tuple2;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TexturesImpl implements Textures {
    private final Map<String, Texture> texturesCache;
    private final TextureAtlas atlas;
    private final XPathDocument manifest;

    public TexturesImpl(final String texturesDirectory, final ResolutionManifest manifest) {
        this.texturesCache = new HashMap<>();
        this.atlas = new TextureAtlas(texturesDirectory + "/textures.atlas");
        this.manifest = manifest.getQueryableManifest();
    }

    @Override
    public Texture getTexture(final String name) {
        return this.texturesCache.containsKey(name) ? this.texturesCache.get(name) : loadTexture(name);
    }

    @Override
    public void dispose() {
        this.atlas.dispose();
    }

    private Texture loadTexture(final String name) {
        final TextureRegion region = this.atlas.findRegion(name);
        final Tuple2<Integer, Integer> x = extractAxis(name, 'x');
        final Tuple2<Integer, Integer> y = extractAxis(name, 'y');
        final Texture texture = new TextureImpl(region, new Vector2(x.v1(), y.v1()), new Vector2(x.v2(), y.v2()));
        this.texturesCache.put(name, texture);
        return texture;
    }

    private Tuple2<Integer, Integer> extractAxis(final String name, final char axis) {
        int from, to;
        try {
            from = this.manifest.getIntegerFromXPath("/textures/" + name + "/@from" + Character.toUpperCase(axis));
            to = this.manifest.getIntegerFromXPath("/textures/" + name + "/@to" + Character.toUpperCase(axis));
        } catch (final NoXPathResultsException e) {
            from = this.manifest.getIntegerFromXPath("/textures/" + name + "/@" + axis);
            to = from;
        }
        return new Tuple2<>(from, to);
    }

    private final class TextureImpl implements Texture {
        private final TextureRegion region;
        private final Vector2 from;
        private final Vector2 to;

        private TextureImpl(final TextureRegion region, final Vector2 from, final Vector2 to) {
            this.region = region;
            this.from = from;
            this.to = to;
        }

        @Override
        public TextureRegion getRegion() {
            return this.region;
        }

        @Override
        public Vector2 getFrom() {
            return this.from.cpy();
        }

        @Override
        public Vector2 getTo() {
            return this.to.cpy();
        }

        @Override
        public float getWidth() {
            return this.region.getRegionWidth();
        }

        @Override
        public float getHeight() {
            return this.region.getRegionHeight();
        }

        @Override
        public Texture scaled() {
            return new ScaledTextureImpl(this, ResolutionManagerImpl.getInstance().getScaleFactor());
        }
    }

    private final class ScaledTextureImpl implements Texture {
        private final Texture texture;
        private final float scale;

        private ScaledTextureImpl(final Texture texture, final float scale) {
            this.texture = texture;
            this.scale = scale;
        }

        @Override
        public TextureRegion getRegion() {
            return this.texture.getRegion();
        }

        @Override
        public Vector2 getFrom() {
            return this.texture.getFrom().scl(this.scale);
        }

        @Override
        public Vector2 getTo() {
            return this.texture.getTo().scl(this.scale);
        }

        @Override
        public float getWidth() {
            return this.texture.getWidth() * this.scale;
        }

        @Override
        public float getHeight() {
            return this.texture.getHeight() * this.scale;
        }

        @Override
        public Texture scaled() {
            throw new UnsupportedOperationException("can't scale a texture multiple times");
        }
    }
}
