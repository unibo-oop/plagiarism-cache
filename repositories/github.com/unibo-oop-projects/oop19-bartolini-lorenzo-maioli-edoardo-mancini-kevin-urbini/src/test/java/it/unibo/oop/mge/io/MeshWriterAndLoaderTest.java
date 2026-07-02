package it.unibo.oop.mge.io;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import it.unibo.oop.mge.c3d.geometry.Mesh;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class MeshWriterAndLoaderTest {

    private final Point3D a = Point3D.fromDoubles(-100, -100, 0);
    private final Point3D b = Point3D.fromDoubles(100, -100, 0);
    private final Point3D c = Point3D.fromDoubles(-100, 100, 0);
    private final Point3D d = Point3D.fromDoubles(100, 100, 0);

    private final Segment3D ab = Segment3D.fromPoints(a, b, Color.RED);
    private final Segment3D bd = Segment3D.fromPoints(b, d);
    private final Segment3D cd = Segment3D.fromPoints(c, d);
    private final Segment3D ca = Segment3D.fromPoints(c, a);

    private final List<Segment3D> set = Arrays.asList(ab, bd, cd, ca);
    private final Mesh mesh = Mesh.fromSegments(set);
    private static final String SEP = File.separator; 
    private static final String PATH = System.getProperty("user.home") + SEP + "desktop" + SEP + "test.yml"; 

    @Test
    public void testWriter() throws IOException {
        final MeshWriter writer = MeshWriter.fromMesh(mesh);
        writer.write(PATH);
    }

    @Test
    public void testLoader() throws FileNotFoundException, IOException {
        final MeshLoader loader = new MeshLoaderImpl();
        final Mesh mesh = loader.load(PATH);
        assertEquals(this.mesh.getSegments().stream().collect(Collectors.toSet()), mesh.getSegments().stream().collect(Collectors.toSet()));
    }
}
