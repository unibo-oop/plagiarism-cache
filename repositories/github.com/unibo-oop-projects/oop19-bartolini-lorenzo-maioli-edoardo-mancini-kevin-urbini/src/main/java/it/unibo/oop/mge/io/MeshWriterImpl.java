package it.unibo.oop.mge.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.oop.mge.c3d.geometry.Mesh;

/**
 * 
 * Base implementation of MeshWriter.
 *
 */
public class MeshWriterImpl implements MeshWriter {

    private final Mesh mesh;

    @Override
    public final void write(final String path) throws IOException {

        // write to file
        Files.write(Paths.get(path), MeshYamlRepresentation.of(this.mesh).toYamlString().getBytes());
    }

    MeshWriterImpl(final Mesh mesh) {
        this.mesh = mesh;
    }

}
