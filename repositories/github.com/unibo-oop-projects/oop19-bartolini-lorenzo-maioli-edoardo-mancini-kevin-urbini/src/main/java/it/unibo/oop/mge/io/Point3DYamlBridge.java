package it.unibo.oop.mge.io;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;

import it.unibo.oop.mge.c3d.geometry.Point3D;

/**
 * 
 * An object that generates a {@link YamlMapping} from a {@link Point3D}.
 *
 */
public final class Point3DYamlBridge {
    private final Point3D point;

    public static Point3DYamlBridge of(final Point3D point) {
        return new Point3DYamlBridge(point);
    }

    private Point3DYamlBridge(final Point3D point) {
        this.point = point;
    }

    public YamlMapping pointYaml() {
        return Yaml.createYamlMappingBuilder().add("x", Double.toString(point.getX()))
                .add("y", Double.toString(point.getY())).add("z", Double.toString(point.getZ())).build();
    }

}
