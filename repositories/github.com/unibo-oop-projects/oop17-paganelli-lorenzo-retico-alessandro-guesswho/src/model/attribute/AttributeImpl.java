package model.attribute;

import java.util.*;
import model.question.*;
import utilities.*;
import java.util.stream.*;

class AttributeImpl implements Attribute {

    private final Trait trait;
    private final Set<Enum<?>> features;

    AttributeImpl(final Trait trait, final Set<Enum<?>> features) {
        Utilities.requireNonNull(trait, features);
        if (features.stream().anyMatch(feature -> !trait.getPossibleFeatures().contains(feature.getClass()))) {
            throw new IllegalArgumentException("Not supported features");
        }
        this.trait = trait;
        this.features = features;
    }

    @Override
    public Trait getTrait() {
        return this.trait;
    }

    @Override
    public Set<Enum<?>> getFeatures() {
        return Collections.unmodifiableSet(features);
    }

    @Override
    public Set<Question> possibleQuestions() {
        return Stream.concat(Arrays.asList(QuestionFactory.from(trait)).stream(), 
                features.stream().map(feature -> QuestionFactory.from(trait, feature))).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return features.stream().map(feature -> feature.toString().toLowerCase(Locale.ITALIAN))
                .collect(Collectors.joining(", ")) + (features.isEmpty() ? "" : " ") + trait.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.trait, this.features);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Attribute ? trait.equals(((Attribute) obj).getTrait()) 
                && features.equals(((Attribute) obj).getFeatures()) : false;
    }

}
