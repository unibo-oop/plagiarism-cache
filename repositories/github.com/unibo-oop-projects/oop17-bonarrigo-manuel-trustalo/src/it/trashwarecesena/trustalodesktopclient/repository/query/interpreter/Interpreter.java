package it.trashwarecesena.trustalodesktopclient.repository.query.interpreter;

import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * Provides utility to translate a {@link QueryObject} into anything
 * understandable by the interested persistence storage.
 * 
 * @author Manuel Bonarrigo
 *
 */
public interface Interpreter {

    /**
     * Translates a {@link QueryObject} into a String, which form is to be chosen by
     * implementors.
     * 
     * @param query
     *            a non-null {@link QueryObject} to be translated.
     * @return a {@link String} expressing the QueryObject in a textual form.
     */
    String translate(QueryObject query);

}
