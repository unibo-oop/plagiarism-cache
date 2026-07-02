package it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Such an annotation binds a domain model interface to a domain schema entity.
 * @author Manuel Bonarrigo
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface InterfaceToSchemaEntity {

    /**
     * Obtains the name of the domain schema entity the annotated interface is bound
     * to.
     * 
     * @return a String representing the name of the domain schema entity.
     */
    String schemaEntity();

    /**
     * Obtains the field the schema entity is known to be identified with. The
     * default value is set to "ID".
     * 
     * @return a String containing the name of the field identifying the domain
     *         schema entity.
     */
    String identifierName() default "ID";

}
