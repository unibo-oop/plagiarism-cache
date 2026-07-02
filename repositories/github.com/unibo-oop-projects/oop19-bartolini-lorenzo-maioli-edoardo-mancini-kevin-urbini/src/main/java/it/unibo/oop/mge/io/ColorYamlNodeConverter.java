package it.unibo.oop.mge.io;

import java.awt.Color;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;

/**
 * 
 * An object that generates a {@link YamlMapping} from a {@link Color}.
 *
 */
public final class ColorYamlNodeConverter {
    private final Color color;

    public static ColorYamlNodeConverter of(final Color color) {
        return new ColorYamlNodeConverter(color);
    }

    private ColorYamlNodeConverter(final Color color) {
        this.color = color;
    }

    public YamlMapping colorYaml() {
        return Yaml.createYamlMappingBuilder().add("r", Integer.toString(color.getRed()))
                .add("g", Integer.toString(color.getGreen())).add("b", Integer.toString(color.getBlue())).build();
    }

}
