package todo.launcher.platforms;

import java.util.Arrays;
import java.util.List;

/**
 * The platform registry contains the instances of all the platforms, and allows
 * the user to detect the one running the program.
 */
public final class PlatformRegistry {
    private static final List<Platform> PLATFORMS;
    private static final Platform DEFAULT_PLATFORM;

    static {
        final Platform linux = new Linux();
        PLATFORMS = Arrays.asList(linux, new MacOs(), new Windows());
        DEFAULT_PLATFORM = linux;
    }

    private PlatformRegistry() {
        // Prevent creating instances of this class
    }

    /**
     * @return the platform currently running the program.
     */
    public static Platform getCurrent() {
        for (final Platform platform : PLATFORMS) {
            if (platform.isCurrent()) {
                return platform;
            }
        }
        return DEFAULT_PLATFORM;
    }
}
