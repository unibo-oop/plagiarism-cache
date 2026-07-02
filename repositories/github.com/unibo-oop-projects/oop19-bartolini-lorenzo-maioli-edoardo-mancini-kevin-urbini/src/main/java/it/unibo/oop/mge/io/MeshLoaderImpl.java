package it.unibo.oop.mge.io;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlSequence;

import it.unibo.oop.mge.c3d.geometry.Mesh;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

/**
 * Implementation of the mesh loader.
 */
public class MeshLoaderImpl implements MeshLoader {

    private final List<Segment3D> segments = new LinkedList<>();

    /**
     * Load a mesh from file path.
     *
     * @param path the file path
     * @return the mesh created from file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    public final Mesh load(final String path) throws IOException {
        final File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            final YamlMapping mesh = Yaml.createYamlInput(f).readYamlMapping();
            final YamlMapping yamlPoints = mesh.yamlMapping("points");
            final YamlSequence yamlSegments = mesh.yamlSequence("segments");
            for (int i = 0; i < yamlSegments.size(); i++) {
                final YamlMapping yamlSegment = yamlSegments.yamlMapping(i);
                final Point3D pointA = toPoint(yamlPoints.yamlMapping(yamlSegment.string("a")));
                final Point3D pointB = toPoint(yamlPoints.yamlMapping(yamlSegment.string("b")));
                final Color color = toColor(yamlSegments.yamlMapping(i).yamlMapping("color"));
                this.segments.add(Segment3D.fromPoints(pointA, pointB, color));
            }
            return Mesh.fromSegments(this.segments);
        } else {
            throw new IOException();
        }
    }

    /**
     * Create a Point3D from given YamlMapping.
     *
     * @param coords the coordinates
     * @return the point 3D
     */
    private Point3D toPoint(final YamlMapping coords) {
        return Point3D.fromDoubles(coords.doubleNumber("x"), coords.doubleNumber("y"), coords.doubleNumber("z"));
    }

    /**
     * Create a color from given YamlMapping.
     *
     * @param color the color
     * @return the color
     */
    private Color toColor(final YamlMapping color) {
        return new Color(color.integer("r"), color.integer("g"), color.integer("b"));
    }
}
