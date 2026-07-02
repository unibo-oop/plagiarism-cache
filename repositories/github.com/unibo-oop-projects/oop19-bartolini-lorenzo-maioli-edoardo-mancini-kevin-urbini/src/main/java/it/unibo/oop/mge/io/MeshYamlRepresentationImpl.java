package it.unibo.oop.mge.io;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlNode;

import it.unibo.oop.mge.c3d.geometry.Mesh;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

/**
 * 
 * Base implementation of MeshYamlRepresentation.
 *
 */
public final class MeshYamlRepresentationImpl implements MeshYamlRepresentation {
    private final Mesh mesh;

    // package protected
    MeshYamlRepresentationImpl(final Mesh mesh) {
        this.mesh = mesh;
    }

    @Override
    public String toYamlString() {
        // set up data for later steps
        final List<Point3D> uniquePoints = mesh.getSegments().stream()
                .flatMap((final Segment3D seg) -> Stream.of(seg.getA(), seg.getB())).distinct()
                .collect(Collectors.toList());
        final Map<Point3D, String> pointToNames = IntStream.range(0, uniquePoints.size()).boxed()
                .collect(Collectors.toMap(uniquePoints::get, this::generatePointName));

        // build points node
        var pointsNodeBuilder = Yaml.createYamlMappingBuilder();
        for (int i = 0; i < uniquePoints.size(); i++) {
            pointsNodeBuilder = pointsNodeBuilder.add(this.generatePointName(i),
                    Point3DYamlBridge.of(uniquePoints.get(i)).pointYaml());
        }
        final var pointsNode = pointsNodeBuilder.build();

        // build segments node
        final Set<YamlNode> segments = mesh.getSegments().stream()
                .map(seg -> Segment3DYamlBridge.of(seg).segmentYaml(pointToNames::get)).collect(Collectors.toSet());
        var segmentsNodeBuilder = Yaml.createYamlSequenceBuilder();
        for (final YamlNode seg : segments) {
            segmentsNodeBuilder = segmentsNodeBuilder.add(seg);
        }
        final var segmentsNode = segmentsNodeBuilder.build();

        // build complete node
        final YamlNode completeNode = Yaml.createYamlMappingBuilder().add("points", pointsNode)
                .add("segments", segmentsNode).build();

        return completeNode.toString();
    }

    private String generatePointName(final int index) {
        return new StringBuilder().append("p").append(Integer.toString(index)).toString();
    }

}
