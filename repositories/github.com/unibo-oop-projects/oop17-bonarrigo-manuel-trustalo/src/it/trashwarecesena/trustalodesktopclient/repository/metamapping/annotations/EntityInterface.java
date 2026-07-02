package it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Indicates the root in a domain model object hierarchy tree, which enables a
 * safe way to discover the most generic interface assignable from any domain
 * model object.
 * 
 * @author Manuel Bonarrigo
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface EntityInterface {

}
