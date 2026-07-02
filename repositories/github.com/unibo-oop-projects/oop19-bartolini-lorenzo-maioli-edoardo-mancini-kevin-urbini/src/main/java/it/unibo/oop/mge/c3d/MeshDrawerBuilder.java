package it.unibo.oop.mge.c3d;

import java.util.Collection;
import java.util.Collections;

import it.unibo.oop.mge.c3d.geometry.Mesh;
import it.unibo.oop.mge.c3d.geometry.Point3D;

/**
 * 
 * Builder for the class {@link MeshDrawer}.
 *
 */
public interface MeshDrawerBuilder {
    /**
     * 
     * @return a new MeshDrawerBuilder
     */
    static MeshDrawerBuilder create() {
        return new MeshDrawerBuilderImpl(Collections.emptyList(), 0, 0, Point3D.origin());
    }

    /**
     * 
     * @param mesh the mesh to add to the {@link MeshDrawer} that is being built
     * @return a new MeshDrawerBuilder
     */
    MeshDrawerBuilder add(Mesh mesh);

    /**
     * 
     * @param meshes the meshes to add to the {@link MeshDrawer} that is being built
     * @return a new MeshDrawerBuilder
     */
    MeshDrawerBuilder addAll(Collection<Mesh> meshes);

    /**
     * 
     * @return the new {@link MeshDrawer}
     */
    MeshDrawer build();

    /**
     * 
     * @param rotationXY the rotationXY of the {@link MeshDrawer} that is being
     *                   built
     * @return a new MeshDrawerBuilder
     */
    MeshDrawerBuilder rotationXY(double rotationXY);

    /**
     * 
     * @param rotationYZ the rotationYZ of the {@link MeshDrawer} that is being
     *                   built
     * @return a new MeshDrawerBuilder
     */
    MeshDrawerBuilder rotationYZ(double rotationYZ);

    /**
     * 
     * @param translation the translation of the {@link MeshDrawer} that is being
     *                    built
     * @return a new MeshDrawerBuilder
     */
    MeshDrawerBuilder translation(Point3D translation);

}
