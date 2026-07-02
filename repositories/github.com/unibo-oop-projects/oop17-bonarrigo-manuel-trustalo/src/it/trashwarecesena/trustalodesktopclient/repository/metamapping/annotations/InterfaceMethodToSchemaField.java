package it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Such an annotation binds a getter method to its return type and the common
 * name of the schema entity field the obtained value is expected to be
 * associated with.
 * 
 * @author Manuel Bonarrigo
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface InterfaceMethodToSchemaField {

    /**
     * Obtains the class of the object that would be returned by the annotated
     * getter. This method is needed to bypass the JVM erasure mechanism, and being
     * able of knowing at runtime which value will be contained, for instance, by an
     * {@link java.util.Optional Optional}.
     * 
     * @return a {@link Class} expressing the eventual value obtained from invoking
     *         such a method.
     */
    Class<?> returnType();

    /**
     * Obtains the name of the schema field this method is linked to.
     * 
     * @return a String expressing the name of the field.
     */
    String schemaField();
}
