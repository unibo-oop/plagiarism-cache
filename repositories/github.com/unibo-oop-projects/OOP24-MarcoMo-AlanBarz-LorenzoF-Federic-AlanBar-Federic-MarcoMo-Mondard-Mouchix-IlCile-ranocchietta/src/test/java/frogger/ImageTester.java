package frogger;

import java.awt.image.BufferedImage;

final class ImageTester {
    // Private constructor to prevent instantiation
    private ImageTester() {
        throw new AssertionError();
    }

    public static boolean bufferedImagesEqual(final BufferedImage img1, final BufferedImage img2) {
        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            return false;
        }
        for (int x = 0; x < img1.getWidth(); x++) {
            for (int y = 0; y < img1.getHeight(); y++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
}
