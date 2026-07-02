package it.unibo.oop.mge.io;

import java.io.FileNotFoundException;
import java.io.IOException;

import it.unibo.oop.mge.c3d.geometry.Mesh;

/**
 * Mesh loader from file interface.
 */
public interface MeshLoader {
    /**
     * Load a mesh from file path.
     *
     * @param path the file path
     * @return the mesh created from file
     * @throws FileNotFoundException the file not found exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    Mesh load(String path) throws FileNotFoundException, IOException;
}
