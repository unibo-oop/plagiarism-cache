package it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated entity contains an
 * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable} reference. A Carrier may even be an Identifiable itself, the
 * nature of the annotation only points to the references which compose the
 * object. Thus, being marked as a Carrier involves that all the validity
 * expectations of the inner Identifiable(s) will be checked recursively, until
 * all are met or one is failing.
 * 
 * @author Manuel Bonarrigo
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Carrier {

}
