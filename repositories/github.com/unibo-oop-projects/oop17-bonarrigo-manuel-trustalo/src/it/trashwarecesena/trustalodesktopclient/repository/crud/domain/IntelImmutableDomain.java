package it.trashwarecesena.trustalodesktopclient.repository.crud.domain;

import it.trashwarecesena.trustalodesktopclient.repository.crud.RequestDispatcher;
/**
 * Marker interface which joins the {@link RequestDispatcher} behaviour over the
 * {@link CrudIntel} one.
 * <p>
 * Makes the implementor able to (and constrained of) correlate the ability of
 * being entrusted with the dispatching of requests while also actually pursuing
 * the CRUD operations over whatsoever persistence technology he is able to
 * operate onto.
 * <p>
 * Implementors needs to resolve any discrepancy between the semantically
 * related interfaces methods.
 *
 * @author Manuel Bonarrigo
 */
public interface IntelImmutableDomain extends RequestDispatcher, CrudIntel {

}
