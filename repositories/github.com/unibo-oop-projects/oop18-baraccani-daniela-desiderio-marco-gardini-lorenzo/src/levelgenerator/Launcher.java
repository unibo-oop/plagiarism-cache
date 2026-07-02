package levelgenerator;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Launcher class for the LevelGenerator.
 */
public final class Launcher {

    private Launcher() {
    }

    /**
     * Main method.
     * 
     * @param args input arguments
     */
    public static void main(final String[] args) {
        final int size = 22; // Default matrix size;
        final int blockSize = 50; // Default block size
        try {
            new View(size, blockSize);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, "Error. Can't open Level Generator");

        }

    }
}
