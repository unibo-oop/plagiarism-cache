package it.unibo.oop.mge.c3d;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import it.unibo.oop.mge.c3d.geometry.Mesh;
import it.unibo.oop.mge.c3d.geometry.Point3D;

/**
 * 
 * Base implementation of MeshDrawerBuilder.
 *
 */
public class MeshDrawerBuilderImpl implements MeshDrawerBuilder {
    private final List<Mesh> meshes;
    private final double rotationXY;
    private final double rotationYZ;
    private final Point3D translation;

    MeshDrawerBuilderImpl(final List<Mesh> meshes, final double rotationXY, final double rotationYZ,
            final Point3D translation) {
        super();
        this.meshes = meshes;
        this.rotationXY = rotationXY;
        this.rotationYZ = rotationYZ;
        this.translation = translation;
    }

    @Override
    public final MeshDrawerBuilder add(final Mesh mesh) {
        return new MeshDrawerBuilderImpl(new ImmutableList.Builder<Mesh>().addAll(this.meshes).add(mesh).build(),
                this.rotationXY, this.rotationYZ, this.translation);
    }

    @Override
    public final MeshDrawerBuilder addAll(final Collection<Mesh> meshes) {
        return new MeshDrawerBuilderImpl(new ImmutableList.Builder<Mesh>().addAll(this.meshes).addAll(meshes).build(),
                this.rotationXY, this.rotationYZ, this.translation);
    }

    @Override
    public final MeshDrawer build() {
        return new MeshDrawerImpl(this.meshes, this.rotationXY, this.rotationYZ, this.translation);
    }

    @Override
    public final MeshDrawerBuilder rotationXY(final double rotationXY) {
        return new MeshDrawerBuilderImpl(this.meshes, rotationXY, this.rotationYZ, this.translation);
    }

    @Override
    public final MeshDrawerBuilder rotationYZ(final double rotationYZ) {
        return new MeshDrawerBuilderImpl(this.meshes, this.rotationXY, rotationYZ, this.translation);
    }

    @Override
    public final MeshDrawerBuilder translation(final Point3D translation) {
        return new MeshDrawerBuilderImpl(this.meshes, this.rotationXY, this.rotationYZ, translation);
    }

}
