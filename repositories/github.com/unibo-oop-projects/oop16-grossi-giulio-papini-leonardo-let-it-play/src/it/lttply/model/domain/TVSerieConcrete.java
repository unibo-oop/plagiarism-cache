package it.lttply.model.domain;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import it.lttply.model.builders.TVSerieBuilder;

/**
 * This class provides a container to store a {@link TVSerie}.
 */
public final class TVSerieConcrete extends MultiMediaContainerConcrete<Season> implements TVSerie {

    private static final String ERR = "a TVSerie is a concept, it doesn't have its personal size, you can retrieve it by summing the sizes of the episodes";
    private final Optional<Picture> backdrop;
    private final Optional<Set<String>> countries;
    private final Optional<Credits> credits;
    private final Optional<Double> rating;

    /**
     * @param id
     *            the tv serie's id
     * @param size
     *            the tv serie's size
     * @param title
     *            the tv serie's title
     * @param releaseDate
     *            the tv serie's release date
     * @param physicalLocation
     *            the tv serie's physical location
     * @param tags
     *            the tv serie's {@link List} of tags
     * @param overview
     *            the tv serie's overview
     * @param poster
     *            the tv serie's poster
     * @param elements
     *            the tv serie's {@link Set} of {@link Episode}
     * @param backdrop
     *            the tv serie's backdrop
     * @param countries
     *            the tv serie's countries
     * @param credits
     *            the tv serie's credits
     * @param rating
     *            the tv serie's rating
     */
    protected TVSerieConcrete(final String id, final int size, final String title, final LocalDate releaseDate,
            final Path physicalLocation, final Optional<Map<String, String>> tags, final Optional<String> overview,
            final Optional<Picture> poster, final Optional<Set<Season>> elements, final Optional<Picture> backdrop,
            final Optional<Set<String>> countries, final Optional<Credits> credits, final Optional<Double> rating) {
        super(id, size, title, releaseDate, physicalLocation, tags, overview, poster, elements);
        this.backdrop = backdrop;
        this.countries = countries;
        this.credits = credits;
        this.rating = rating;
    }

    @Override
    public Optional<Set<String>> getCountries() {
        return this.countries.isPresent() ? Optional.of(new HashSet<>(this.countries.get()))
                : Optional.of(new HashSet<String>());
    }

    @Override
    public Optional<Double> getRating() {
        return this.rating;
    }

    @Override
    public Optional<Picture> getBackdrop() {
        return this.backdrop;
    }

    @Override
    public Optional<Credits> getCredits() {
        return this.credits;
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException(ERR);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((backdrop == null) ? 0 : backdrop.hashCode());
        result = prime * result + ((countries == null) ? 0 : countries.hashCode());
        result = prime * result + ((credits == null) ? 0 : credits.hashCode());
        result = prime * result + ((rating == null) ? 0 : rating.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof TVSerieConcrete)) {
            return false;
        }
        final TVSerieConcrete other = (TVSerieConcrete) obj;
        if (backdrop == null) {
            if (other.backdrop != null) {
                return false;
            }
        } else if (!backdrop.equals(other.backdrop)) {
            return false;
        }
        if (countries == null) {
            if (other.countries != null) {
                return false;
            }
        } else if (!countries.equals(other.countries)) {
            return false;
        }
        if (credits == null) {
            if (other.credits != null) {
                return false;
            }
        } else if (!credits.equals(other.credits)) {
            return false;
        }
        if (rating == null) {
            if (other.rating != null) {
                return false;
            }
        } else if (!rating.equals(other.rating)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, 
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }

    /**
     * Builder for {@link TVSerie} class. See the inherited doc for details.
     */
    public static class TVSerieConcreteBuilder implements TVSerieBuilder<TVSerieConcreteBuilder> {
        private Optional<Set<Season>> internalElements = Optional.empty();

        private String internalId;
        private String internalTitle;
        private LocalDate internalReleaseDate;
        private Path internalPhysicalLocation;

        private Optional<Map<String, String>> internalTags = Optional.empty();
        private Optional<String> internalOverview = Optional.empty();
        private Optional<Picture> internalPoster = Optional.empty();
        private Optional<Double> internalRating = Optional.empty();

        private Optional<Picture> internalBackdrop = Optional.empty();
        private Optional<Set<String>> internalCountries = Optional.empty();
        private Optional<Credits> internalCredits = Optional.empty();

        @Override
        public TVSerieConcreteBuilder elements(final Set<Season> elements) {
            this.internalElements = Optional.ofNullable(new HashSet<>(elements));
            return this;
        }

        @Override
        public TVSerieConcreteBuilder id(final String id) {
            this.internalId = id;
            return this;
        }

        @Override
        public TVSerieConcreteBuilder tags(final Map<String, String> tags) {
            this.internalTags = Optional.of(tags);
            return this;
        }

        @Override
        public TVSerieConcreteBuilder overview(final String overview) {
            this.internalOverview = Optional.ofNullable(overview);
            return this;
        }

        @Override
        public TVSerieConcreteBuilder poster(final Picture poster) {
            this.internalPoster = Optional.ofNullable(poster);
            return this;
        }

        @Override
        public TVSerieConcreteBuilder title(final String title) {
            this.internalTitle = title;
            return this;
        }

        @Override
        public TVSerieConcreteBuilder releaseDate(final LocalDate date) {
            this.internalReleaseDate = date;
            return this;
        }

        @Override
        public TVSerieConcreteBuilder physicalLocation(final Path path) {
            this.internalPhysicalLocation = path;
            return this;
        }

        @Override
        public TVSerieConcreteBuilder countries(final Set<String> countries) {
            this.internalCountries = Optional.of(countries);
            return this;
        }

        @Override
        public TVSerieConcreteBuilder rating(final Double rating) {
            this.internalRating = Optional.ofNullable(rating);
            return this;
        }

        @Override
        public TVSerieConcreteBuilder backdrop(final Picture backdrop) {
            this.internalBackdrop = Optional.ofNullable(backdrop);
            return this;
        }

        @Override
        public TVSerieConcreteBuilder size(final int size) {
            throw new UnsupportedOperationException(ERR);
        }

        @Override
        public TVSerieConcreteBuilder credits(final Credits credits) {
            this.internalCredits = Optional.ofNullable(credits);
            return this;
        }

        @Override
        public TVSerie build() {
            if (this.internalId != null && !this.internalId.isEmpty() && this.internalPhysicalLocation != null
                    && this.internalTitle != null && !this.internalTitle.isEmpty()
                    && this.internalReleaseDate != null) {
                return new TVSerieConcrete(this.internalId, -1, this.internalTitle, this.internalReleaseDate,
                        this.internalPhysicalLocation, this.internalTags, this.internalOverview, this.internalPoster,
                        this.internalElements, this.internalBackdrop, this.internalCountries, this.internalCredits,
                        this.internalRating);
            } else {
                throw new IllegalStateException();
            }
        }

        @Override
        public TVSerie buildFrom(final TVSerie source) {
            Objects.requireNonNull(source);
            this.internalBackdrop = source.getBackdrop();
            this.internalCountries = source.getCountries();
            this.internalCredits = source.getCredits();
            this.internalElements = source.getElements();
            this.internalId = source.getID();
            this.internalOverview = source.getOverview();
            this.internalPhysicalLocation = source.getPhysicalLocation();
            this.internalPoster = source.getPoster();
            this.internalRating = source.getRating();
            this.internalReleaseDate = source.getReleaseDate();
            this.internalTags = source.getTags();
            this.internalTitle = source.getTitle();
            return this.build();
        }

    }

}
