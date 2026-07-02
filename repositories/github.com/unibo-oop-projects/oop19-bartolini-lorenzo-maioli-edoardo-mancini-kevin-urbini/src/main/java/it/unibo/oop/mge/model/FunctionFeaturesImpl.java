package it.unibo.oop.mge.model;

import java.awt.Color;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;
import it.unibo.oop.mge.color.ColorGenerator;
import it.unibo.oop.mge.color.ColorGeneratorImpl;
import it.unibo.oop.mge.color.VariableColor;
import it.unibo.oop.mge.function.AlgebricFunction;
import it.unibo.oop.mge.libraries.Pair;
import it.unibo.oop.mge.libraries.PointND;
import it.unibo.oop.mge.libraries.PointNDImpl;
import it.unibo.oop.mge.libraries.Variable;

public final class FunctionFeaturesImpl implements FunctionFeatures {
    private final Pair<Double, Double> interval;
    private final Integer width;
    private final Integer decimalPrecision;
    private final List<Point3D> points;
    private final ColorGenerator cg;

    protected FunctionFeaturesImpl(final AlgebricFunction function, final Pair<Double, Double> interval,
            final Double rate, final Optional<VariableColor> varColor, final Optional<Color> staticColor,
            final Integer decimalPrecision) {
        this.interval = interval;
        this.decimalPrecision = decimalPrecision;
        this.width = (int) (Math.abs(interval.getFst() - interval.getSnd()) / rate);
        this.points = getPoint3DfromPointND(getPointsFromFunction(function, rate));
        this.cg = varColor.isPresent()
                ? new ColorGeneratorImpl(varColor.get(), this.getPointOfAbsoluteMin().getZ(),
                        this.getPointOfAbsoluteMax().getZ())
                : new ColorGeneratorImpl(staticColor.get());
    }

    private static List<Point3D> getPoint3DfromPointND(final List<PointND> points) {
        return points.stream().<Point3D>map(i -> Point3D.fromDoubles(i.getVariableValue(Variable.X),
                i.getVariableValue(Variable.Y), i.getFunctionValue())).collect(Collectors.toList());
    }

    private static List<Point3D> getRealPoints(final List<Point3D> points) {
        return points.stream().filter(i -> Double.isFinite(i.getZ())).collect(Collectors.toList());
    }

    private double troncateDouble(final Double value) {
        return Math.floor(Math.pow(10, this.decimalPrecision) * value) / Math.pow(10, this.decimalPrecision);
    }

    private List<PointND> getPointsFromFunction(final AlgebricFunction function, final Double rate) {
        /*
         * This function takes the number of the point and the number of the coordinate
         * and return the coordinate of that number
         */
        final BiFunction<Integer, Integer, Double> coordinateGen = (i,
                j) -> ((int) (i / Math.pow(this.width + 1, j)) % (this.width + 1)) * rate + this.interval.getFst();

        return IntStream.range(0, (int) Math.pow(this.width + 1, Variable.values().length)).<PointND>mapToObj(i -> {
            final Map<Variable, Double> coordinates = IntStream.range(0, Variable.values().length).boxed().collect(
                    Collectors.toMap(a -> Variable.values()[a], a -> troncateDouble(coordinateGen.apply(i, a))));
            return new PointNDImpl(coordinates, function.resolve(coordinates));
        }).collect(Collectors.toList());
    }

    /*
     * This method allows to generate a list of segment from a list of points each
     * segment is composed by 2 points: 1 ) point.get(f(i)) 2 ) point.get(f(i+1))
     * where f is the function given named 'posDetector'
     */
    private List<Segment3D> getSegmentList(final List<Point3D> points, final Function<Integer, Integer> posDetector) {
        return IntStream.range(0, points.size() - 1).mapToObj(i -> {
            final Point3D a = points.get(posDetector.apply(i));
            final Point3D b = points.get(posDetector.apply(i + 1));
            return Segment3D.fromPoints(a, b, this.cg.getColorFromDouble((a.getZ() + b.getZ()) / 2));
        }).filter(i -> Double.isFinite(i.getA().getZ()) && Double.isFinite(i.getB().getZ()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Segment3D> getPolygonalAxis() {
        return List.of(
                Segment3D.fromPoints(Point3D.fromDoubles(this.interval.getFst(), 0, 0),
                        Point3D.fromDoubles(this.interval.getSnd(), 0, 0)),
                Segment3D.fromPoints(Point3D.fromDoubles(0, this.interval.getFst(), 0),
                        Point3D.fromDoubles(0, this.interval.getSnd(), 0)),
                Segment3D.fromPoints(Point3D.fromDoubles(0, 0, this.interval.getFst()),
                        Point3D.fromDoubles(0, 0, this.interval.getSnd())));
    }

    public Point3D getPointOfAbsoluteMax() {
        return getRealPoints(this.points).stream().max((i, j) -> Double.compare(i.getZ(), j.getZ())).get();
    }

    public Point3D getPointOfAbsoluteMin() {
        return getRealPoints(this.points).stream().min((i, j) -> Double.compare(i.getZ(), j.getZ())).get();
    }

    public List<Segment3D> getPolygonalModel() {

        return Stream.concat(
                /* We call this method to generate all the horizontal segments */
                getSegmentList(points, i -> i).stream().filter(i -> i.getA().getY() == i.getB().getY()),
                /* We call this method to generate all the vertical segments */
                getSegmentList(points, i -> (int) ((i % (this.width + 1)) * (this.width + 1) + i / (this.width + 1)))
                        .stream().filter(i -> i.getA().getX() == i.getB().getX()))
                .collect(Collectors.toList());
    }
}
