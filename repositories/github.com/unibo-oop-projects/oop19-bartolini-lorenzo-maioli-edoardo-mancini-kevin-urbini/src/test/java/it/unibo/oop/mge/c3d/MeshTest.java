package it.unibo.oop.mge.c3d;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import it.unibo.oop.mge.c3d.geometry.Mesh;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

class MeshTest {

    @Test
    public void test() {
        final Point3D a = Point3D.fromDoubles(-100, -100, 0);
        final Point3D b = Point3D.fromDoubles(100, -100, 0);
        final Point3D c = Point3D.fromDoubles(-100, 100, 0);
        final Point3D d = Point3D.fromDoubles(100, 100, 0);

        final Segment3D ab = Segment3D.fromPoints(a, b);
        final Segment3D bd = Segment3D.fromPoints(b, d);
        final Segment3D cd = Segment3D.fromPoints(c, d);
        final Segment3D ca = Segment3D.fromPoints(c, a);

        final var set = Arrays.asList(ab, bd, cd, ca);

        final var mesh = Mesh.fromSegments(set);
        System.out.println(mesh);
    }

}
