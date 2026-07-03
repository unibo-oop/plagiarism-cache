package it.lttply.model;

import it.lttply.model.domain.Source;
import it.lttply.model.domain.TVSerie;
import it.lttply.model.domain.TVSerieConcrete;

/**
 * This class permits to manage a single {@link TVSerie}.
 */
public final class SingleTVSerieModel implements SingleMediaModel<TVSerie> {

    private static final String ERR_NOT_SUPPORTED = "This feature isn't still supported yet!";
    private final TVSerie tvserie;

    /**
     * Constructs a new instance of {@link SingleMovieModel}.
     * 
     * @param tvserie
     *            the {@link TVSerie} to be stored and managed
     */
    public SingleTVSerieModel(final TVSerie tvserie) {
        this.tvserie = tvserie;
    }

    @Override
    public void reload(final Source source, final boolean overwrite) {
        throw new UnsupportedOperationException(ERR_NOT_SUPPORTED);
    }

    @Override
    public TVSerie get() {
        return new TVSerieConcrete.TVSerieConcreteBuilder().buildFrom(this.tvserie);
    }

}
