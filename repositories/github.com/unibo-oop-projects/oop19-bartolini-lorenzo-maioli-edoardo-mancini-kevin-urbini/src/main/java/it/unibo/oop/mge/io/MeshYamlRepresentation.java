package it.unibo.oop.mge.io;

import it.unibo.oop.mge.c3d.geometry.Mesh;

/**
 * 
 * An object that generates the Yaml representation of a Mesh.
 *
 */
public interface MeshYamlRepresentation {
    /**
     * 
     * @return the string representation, in yaml format, of the wrapped mesh
     */
    String toYamlString();

    /**
     * 
     * @param mesh the Mesh to wrap
     * @return a new MeshYamlRepresentation of the provided Mesh
     */
    static MeshYamlRepresentation of(final Mesh mesh) {
        return new MeshYamlRepresentationImpl(mesh);
    }

}
