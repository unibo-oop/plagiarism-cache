package it.unibo.oop.mge.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.mge.c3d.MeshDrawerBuilder;
import it.unibo.oop.mge.c3d.geometry.Mesh;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.color.VariableColor;
import it.unibo.oop.mge.color.VariableColorBuilderImpl;
import it.unibo.oop.mge.io.MeshLoader;
import it.unibo.oop.mge.io.MeshLoaderImpl;
import it.unibo.oop.mge.io.MeshWriter;
import it.unibo.oop.mge.libraries.Properties;
import it.unibo.oop.mge.model.FunctionFeatures;
import it.unibo.oop.mge.model.FunctionFeaturesBuilderImpl;
import it.unibo.oop.mge.model.StringComposerImpl;
import it.unibo.oop.mge.view.DrawGraphView;
import it.unibo.oop.mge.view.DrawGraphViewImpl;

public class DrawGraphApp implements DrawGraphViewObserver {

    private final DrawGraphView view;
    private Point3D visualizerTranslation = Point3D.origin();
    private double visualizerRotationXY;
    private double visualizerRotationYZ;
    private final List<Mesh> visualizerMeshes;
    private static final double ROTATION_DELTA = 15 * Math.PI / 180;
    private static final double TRANSLATION_DELTA = 10;
    private static final int GRAPH_G = 99;
    private static final int GRAPH_B = 103;
    private static final VariableColor GRAPH_COLOR = new VariableColorBuilderImpl().blue(GRAPH_B).green(GRAPH_G)
            .build();

    public DrawGraphApp() {
        this.visualizerMeshes = new ArrayList<>();
        this.view = new DrawGraphViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public final void newGraph(final String function, final double max, final double min, final double rate) {
        boolean creationSuccess = false;
        try {
            final FunctionFeatures functionFeatures = new FunctionFeaturesBuilderImpl()
                    .function(new StringComposerImpl().parse(function)).intervals(min, max).rate(rate)
                    .dynamicColor(GRAPH_COLOR).decimalPrecision(4).build();
            this.visualizerMeshes.add(Mesh.fromSegments(functionFeatures.getPolygonalModel()));
            this.visualizerMeshes.add(Mesh.fromSegments(functionFeatures.getPolygonalAxis()));
            this.view.plotProperties(
                    List.of(Properties.MAX.getSyntax() + " " + functionFeatures.getPointOfAbsoluteMax().toString(),
                            Properties.MIN.getSyntax() + " " + functionFeatures.getPointOfAbsoluteMin().toString()));
            creationSuccess = true;
        } catch (IllegalArgumentException e) {
            this.view.expressionIncorrect();
        }
        if (creationSuccess) {
            this.refreshVisualizer();
        }

    }

    public static void main(final String[] args) {
        new DrawGraphApp();
    }

    @Override
    public final void load(final String path) {
        final MeshLoader meshLoader = new MeshLoaderImpl();
        try {
            this.visualizerMeshes.add(meshLoader.load(path));
            this.refreshVisualizer();
        } catch (IOException e) {
            this.view.ioError();
        }
    }

    @Override
    public final void save(final String path) {
        try {
            MeshWriter.fromMesh(Mesh.fromMeshes(visualizerMeshes)).write(path);
        } catch (IOException e) {
            this.view.ioError();
        }
    }

    @Override
    public final void zoomIn() {
        this.visualizerTranslation = this.visualizerTranslation
                .translated(Point3D.fromDoubles(0, -TRANSLATION_DELTA, 0));
        this.refreshVisualizer();

    }

    @Override
    public final void zoomOut() {
        this.visualizerTranslation = this.visualizerTranslation
                .translated(Point3D.fromDoubles(0, TRANSLATION_DELTA, 0));
        this.refreshVisualizer();

    }

    @Override
    public final void moveUp() {
        this.visualizerTranslation = this.visualizerTranslation
                .translated(Point3D.fromDoubles(0, 0, TRANSLATION_DELTA));
        this.refreshVisualizer();

    }

    @Override
    public final void moveLeft() {
        this.visualizerTranslation = this.visualizerTranslation
                .translated(Point3D.fromDoubles(-TRANSLATION_DELTA, 0, 0));
        this.refreshVisualizer();

    }

    @Override
    public final void moveRight() {
        this.visualizerTranslation = this.visualizerTranslation
                .translated(Point3D.fromDoubles(TRANSLATION_DELTA, 0, 0));
        this.refreshVisualizer();

    }

    @Override
    public final void moveDown() {
        this.visualizerTranslation = this.visualizerTranslation
                .translated(Point3D.fromDoubles(0, 0, -TRANSLATION_DELTA));
        this.refreshVisualizer();
    }

    @Override
    public final void increaseXY() {
        this.visualizerRotationXY += ROTATION_DELTA;
        this.refreshVisualizer();

    }

    @Override
    public final void decreaseXY() {
        this.visualizerRotationXY -= ROTATION_DELTA;
        this.refreshVisualizer();

    }

    @Override
    public final void increaseYZ() {
        this.visualizerRotationYZ += ROTATION_DELTA;
        this.refreshVisualizer();

    }

    @Override
    public final void decreaseYZ() {
        this.visualizerRotationYZ -= ROTATION_DELTA;
        this.refreshVisualizer();

    }

    @Override
    public final void quit() {
        System.exit(0);
    }

    private void refreshVisualizer() {
        this.view.plotGraph(MeshDrawerBuilder.create().addAll(visualizerMeshes).translation(visualizerTranslation)
                .rotationXY(visualizerRotationXY).rotationYZ(visualizerRotationYZ).build().render().getSegments());
    }
}
