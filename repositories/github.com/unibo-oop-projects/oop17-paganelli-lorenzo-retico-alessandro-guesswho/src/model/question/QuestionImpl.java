package model.question;

import java.util.Objects;
import model.attribute.*;
import static utilities.Messages.*;
import utilities.*;

class QuestionImpl implements Question {

    private final Attribute attribute;

    QuestionImpl(final Attribute attribute) {
        Utilities.requireNonNull(attribute);
        this.attribute = attribute;
    }

    @Override
    public Attribute toAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return (attribute.getTrait().equals(Trait.GENDER) ? GENDER_QUESTION_PREFIX : QUESTION_PREFIX)
                + this.getArticle() + attribute.toString().replace(Trait.GENDER.toString(), "")
                + QUESTION_SUFFIX;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Question ? ((Question) obj).toAttribute().equals(attribute) : false;
    }

    private String getArticle() {
        try {
            return attribute.getTrait().getClass().getField(attribute.getTrait().name()).isAnnotationPresent(ToString.class) 
                    ? attribute.getTrait().getClass().getField(attribute.getTrait().name()).getAnnotation(ToString.class).article() + " "
                    : "";
        } catch (NoSuchFieldException | SecurityException ingored) {
            return "";
        }
    }

}
