package it.unibo.oop.mge.c3d.geometry;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * 
 * Base implementation of Mesh.
 *
 */
public class MeshImpl implements Mesh {
    private final List<Segment3D> segments;

    MeshImpl(final List<Segment3D> segments) {
        this.segments = segments;
    }

    @Override
    public final double getScale() {
        return segments.stream().flatMap(seg -> Stream.of(seg.getA(), seg.getB()))
                .flatMapToDouble(point -> DoubleStream.of(point.getX(), point.getY(), point.getZ()))
                .map(el -> Math.abs(el)).filter(el -> el != 0).max().orElse(1);
    }

    @Override
    public final List<Segment3D> getSegments() {
        return this.segments;
    }

    @Override
    public final String toString() {
        return "MeshImpl [segments=" + segments + "]";
    }

    @Override
    public final Mesh transformed(final Function<Double, Double> transformation) {
        return new MeshImpl(
                this.segments.stream().map(seg -> seg.transformed(transformation)).collect(Collectors.toList()));
    }

    @Override
    public final Mesh translated(final Point3D vector) {
        return new MeshImpl(this.segments.stream()
                .map(seg -> seg.translated(vector.getX(), vector.getY(), vector.getZ())).collect(Collectors.toList()));
    }
}
