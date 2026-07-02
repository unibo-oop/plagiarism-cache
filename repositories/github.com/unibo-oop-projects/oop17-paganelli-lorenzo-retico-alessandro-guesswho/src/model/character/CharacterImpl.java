package model.character;

import java.util.*;
import java.util.stream.Collectors;
import model.attribute.Attribute;
import utilities.Utilities;

class CharacterImpl implements Character {

    private final String name;
    private final Set<Attribute> attributes;

    CharacterImpl(final String name, final Set<Attribute> attributes) {
        Utilities.requireNonNull(name, attributes);
        if (attributes.isEmpty()) {
            throw new IllegalArgumentException("Characters shall have at least one Attribute");
        }
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Set<Attribute> getAttributes() {
        return Collections.unmodifiableSet(attributes);
    }

    @Override
    public boolean has(final Attribute attribute) {
        Utilities.requireNonNull(attribute);
        return attributes.stream().anyMatch(attr -> attr.getTrait().equals(attribute.getTrait()) 
                && attr.getFeatures().containsAll(attribute.getFeatures()));
    }

    @Override
    public String toString() {
        return "Name: " + name + System.lineSeparator() + "Attributes: " + attributes.stream().map(a -> a.toString())
                .collect(Collectors.joining(" / "));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.attributes);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Character ? name.equals(((Character) obj).getName()) 
                && attributes.equals(((Character) obj).getAttributes()) : false;
    }

}
