package talisman.util;

import java.awt.Component;
import java.awt.Point;

import javax.swing.SwingUtilities;

public final class SwingViewUtils {
    private SwingViewUtils() {
    }

    public static Point getGlobalPosition(final Component component) {
        final Component root = SwingUtilities.getRoot(component);
        // This can happen if called before setting visible the frame, if the component
        // is the root (and why should you call this then?) or when testing
        if (root == null || !root.isVisible()) {
            return new Point(0, 0);
        }
        return SwingUtilities.convertPoint(component.getParent(), component.getLocation(), null);
    }

    public static Point globalToLocalPosition(final Component localComponent, final int x, final int y) {
        return SwingUtilities.convertPoint(SwingUtilities.getRoot(localComponent), x, y, localComponent.getParent());
    }
}
