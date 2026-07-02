package it.unibo.oop.mge.io;

import java.io.IOException;

import it.unibo.oop.mge.c3d.geometry.Mesh;

/**
 * 
 * An object that writes a mesh to file.
 *
 */
public interface MeshWriter {
    /**
     * 
     * @param path the path to write the file in
     * @throws IOException
     */
    void write(String path) throws IOException;

    /**
     * 
     * @param mesh the Mesh to build the MeshWriter from
     * @return a new MeshWriter, for the provided Mesh
     */
    static MeshWriter fromMesh(Mesh mesh) {
        return new MeshWriterImpl(mesh);
    }
}
