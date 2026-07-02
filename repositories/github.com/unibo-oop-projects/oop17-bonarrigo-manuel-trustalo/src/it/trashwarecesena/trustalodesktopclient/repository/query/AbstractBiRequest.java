package it.trashwarecesena.trustalodesktopclient.repository.query;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * An extension of {@link AbstractSingleRequest} adding another
 * parameter to meet the {@link BiRequest} requirements. The
 * semantic differences between the two payloads is to be attributed by the
 * client, but as a rule-of-thumb the first in considered the more important.
 * <p>
 * Since every method is already implemented and declared as <b>final</b>, the
 * only kind of extension allowed is one such to provide a narrowing type.
 * 
 * @author Manuel Bonarrigo
 */

public abstract class AbstractBiRequest extends AbstractSingleRequest implements BiRequest {

    private final Object secondEntry;

    /**
     * Initialize the {@link BiRequest} over the two parameters.
     * 
     * @param firstEntry
     *            The most important, non-nullable, out of the two parameters.
     * @param secondEntry
     *            The less important, non-nullable, out of the two parameters.
     */
    public AbstractBiRequest(final Object firstEntry, final Object secondEntry) {
        super(firstEntry);
        this.secondEntry = Objects.requireNonNull(secondEntry, "The second entry of a BiRequest" + ErrorString.NO_NULL);
    }

    @Override
    public final Object getSecondPayload() {
        return this.secondEntry;
    }

}
