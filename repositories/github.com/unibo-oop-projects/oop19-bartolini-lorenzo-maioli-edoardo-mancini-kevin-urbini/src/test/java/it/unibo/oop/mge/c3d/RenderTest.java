package it.unibo.oop.mge.c3d;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import it.unibo.oop.mge.c3d.geometry.Mesh;
import it.unibo.oop.mge.c3d.geometry.Point2D;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment2D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

public class RenderTest extends Canvas {

    /**
     *
     */
    private static final int SCREEN_SIZE = 200;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final List<Segment2D> output;

    public static void main(final String[] args) {

        final Point3D a = Point3D.fromDoubles(-100, -100, 0);
        final Point3D b = Point3D.fromDoubles(100, -100, 0);
        final Point3D c = Point3D.fromDoubles(-100, 100, 0);
        final Point3D d = Point3D.fromDoubles(100, 100, 0);

        final Segment3D ab = Segment3D.fromPoints(a, b);
        final Segment3D bd = Segment3D.fromPoints(b, d, Color.RED);
        final Segment3D cd = Segment3D.fromPoints(c, d, Color.GREEN);
        final Segment3D ca = Segment3D.fromPoints(c, a);

        final var set = Arrays.asList(ab, bd, cd, ca);

//        final MeshVisualizer visualizer = MeshVisualizer.create();
//
//        visualizer.setMesh(set);
//
//        visualizer.setTranslation(Point3D.fromDoubles(80, 50, -30));
//        final var output = visualizer.render();
        final var output = MeshDrawerBuilder.create().add(Mesh.fromSegments(set)).rotationXY(.1).rotationYZ(.2)
                .translation(Point3D.fromDoubles(0, 0, -30)).build().render().getSegments();

        final RenderTest dr = new RenderTest(output);
        final JFrame frame = new JFrame("My Drawing");
        frame.add(dr);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public final Dimension getPreferredSize() {
        return new Dimension(SCREEN_SIZE * 2, SCREEN_SIZE * 2);
    }

    @Override
    public final void paint(final Graphics g) {
        super.paint(g);
        output.stream()
                .map(seg -> Segment2D.fromPoints(Point2D.fromDoubles(seg.getA().getX(), -seg.getA().getY()),
                        Point2D.fromDoubles(seg.getB().getX(), -seg.getB().getY()), seg.getColor()))
                .map(el -> el.transformed(coord -> coord * SCREEN_SIZE + SCREEN_SIZE)).forEach(el -> {
                    System.out.println(el.getColor());
                    g.setColor(el.getColor());
                    g.drawLine((int) el.getA().getX(), (int) el.getA().getY(), (int) el.getB().getX(),
                            (int) el.getB().getY());
                });
    }

    RenderTest(final List<Segment2D> model) {
        this.output = model;
    }

}
