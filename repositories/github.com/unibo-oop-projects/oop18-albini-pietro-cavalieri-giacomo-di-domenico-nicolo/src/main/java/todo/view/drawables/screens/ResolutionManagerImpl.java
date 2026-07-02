package todo.view.drawables.screens;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import todo.utils.Checks;

public class ResolutionManagerImpl implements ResolutionManager {
    private static final float EPSILON = 0.1f;
    private static ResolutionManager instance;
    private State state;
    private AspectRatio currentAspectRatio;
    private final List<Float> aspectRatios;
    private final List<DisplayMode> modes;

    public static ResolutionManager getInstance() {
        if (instance == null) {
            instance = new ResolutionManagerImpl();
        }
        return instance;
    }

    private ResolutionManagerImpl() {
        this.state = new State();
        this.aspectRatios = Arrays.asList(AspectRatio.values())
                                  .stream()
                                  .map(ar -> ar.getManifest().getAspectRatio())
                                  .collect(Collectors.toList());
        this.currentAspectRatio = findAspectRatioFromDisplayMode(this.state.displayMode);
        this.modes = Arrays.asList(Gdx.graphics.getDisplayModes());
    }

    @Override
    public List<DisplayMode> getSupportedDisplayModes() {
        // Find every display mode that has a supported aspect ratio
        return this.modes.stream()
                         .filter(dm -> this.aspectRatios.stream()
                                                        .anyMatch(ar -> Math.abs(
                                                                (float) dm.width / dm.height - ar) < EPSILON))
                         .filter(dm -> dm.width >= 1280 & dm.height >= 720)
                         .collect(Collectors.toList());
    }

    @Override
    public DisplayMode getCurrentDisplayMode() {
        return this.state.displayMode;
    }

    @Override
    public AspectRatio getCurrentAspectRatio() {
        return this.currentAspectRatio;
    }

    @Override
    public Viewport getCurrentViewport() {
        final float scale = getScaleFactor();
        return new FitViewport(this.currentAspectRatio.getManifest().getOriginalWidth() * scale,
                this.currentAspectRatio.getManifest().getOriginalHeight() * scale);
    }

    @Override
    public boolean isFullscreen() {
        return this.state.fullscreen;
    }

    @Override
    public ResolutionUpdater update() {
        // Return a clone of the current state, so that changes are not immediately
        // reflected in this instance. Changes will be reflected only when the caller
        // calls the apply method.
        return new State(this.state.fullscreen, this.state.displayMode);
    }

    @Override
    public float getScaleFactor() {
        return (float) this.state.displayMode.width / this.currentAspectRatio.getManifest().getOriginalWidth();
    }

    private AspectRatio findAspectRatioFromDisplayMode(final DisplayMode mode) {
        final float modeAspectRatio = (float) mode.width / mode.height;
        return Arrays.asList(AspectRatio.values())
                     .stream()
                     .min((ar1, ar2) -> Float.compare(Math.abs(ar1.getManifest().getAspectRatio() - modeAspectRatio),
                             Math.abs(ar2.getManifest().getAspectRatio() - modeAspectRatio)))
                     .get();
    }

    private final class State implements ResolutionUpdater {
        private boolean fullscreen;
        private DisplayMode displayMode;

        private State() {
            this.fullscreen = true;
            this.displayMode = Gdx.graphics.getDisplayMode();
        }

        private State(final boolean fullscreen, final DisplayMode displayMode) {
            this.fullscreen = fullscreen;
            this.displayMode = displayMode;
        }

        @Override
        public ResolutionUpdater setDisplayMode(final DisplayMode mode) {
            Checks.require(getSupportedDisplayModes().contains(Objects.requireNonNull(mode)),
                    IllegalArgumentException.class, "The specified display mode is't supported");
            this.displayMode = Objects.requireNonNull(mode);
            return this;
        }

        @Override
        public ResolutionUpdater setFullscreen(final boolean active) {
            this.fullscreen = active;
            return this;
        }

        @Override
        public void apply() {
            final ResolutionManagerImpl parent = ResolutionManagerImpl.this;
            parent.currentAspectRatio = findAspectRatioFromDisplayMode(this.displayMode);
            if (this.fullscreen) {
                Gdx.graphics.setFullscreenMode(this.displayMode);
            } else {
                Gdx.graphics.setWindowedMode(this.displayMode.width, this.displayMode.height);
            }
            parent.state = this;
        }
    }
}
