package it.unibo.oop.mge.io;

import java.util.function.Function;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;

import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

/**
 * 
 * An object that generates a {@link YamlMapping} from a {@link Segment3D}.
 *
 */
public final class Segment3DYamlBridge {
    private final Segment3D segment;

    public static Segment3DYamlBridge of(final Segment3D segment) {
        return new Segment3DYamlBridge(segment);
    }

    private Segment3DYamlBridge(final Segment3D segment) {
        this.segment = segment;
    }

    /**
     * 
     * @param mapping the function describing how to generate a name for each point
     * @return the new mapping
     */
    public YamlMapping segmentYaml(final Function<Point3D, String> mapping) {
        return Yaml.createYamlMappingBuilder().add("a", mapping.apply(segment.getA()))
                .add("b", mapping.apply(segment.getB()))
                .add("color", ColorYamlNodeConverter.of(segment.getColor()).colorYaml()).build();
    }

}
