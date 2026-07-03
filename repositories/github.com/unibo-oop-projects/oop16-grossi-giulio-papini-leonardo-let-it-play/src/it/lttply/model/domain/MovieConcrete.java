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

import it.lttply.model.builders.MovieBuilder;

/**
 * This class provides a container to store a movie and all of its metainfo.
 *
 */
public final class MovieConcrete extends VideoConcrete implements Movie {

    private final Optional<Picture> backdrop;
    private final Optional<Set<String>> countries;
    private final Optional<Credits> credits;

    /**
     * @param id
     *            the movie's id
     * @param size
     *            the movie's size
     * @param title
     *            the movie's title
     * @param releaseDate
     *            the movie's release date
     * @param physicalLocation
     *            the movie's physical location
     * @param tags
     *            the movie's {@link List} of tags
     * @param overview
     *            the movie's overview
     * @param poster
     *            the movie's poster {@link Picture}
     * @param rating
     *            the movie's rating
     * @param duration
     *            the movie's duration
     * @param backdrop
     *            the movie's backdrop {@link Picture}
     * @param countries
     *            the movie's countries
     * @param credits
     *            the movie's credits
     */
    protected MovieConcrete(final String id, final int size, final String title, final LocalDate releaseDate,
            final Path physicalLocation, final Optional<Map<String, String>> tags, final Optional<String> overview,
            final Optional<Picture> poster, final Optional<Double> rating, final int duration,
            final Optional<Picture> backdrop, final Optional<Set<String>> countries, final Optional<Credits> credits) {
        super(id, size, title, releaseDate, physicalLocation, tags, overview, poster, rating, duration);
        this.backdrop = backdrop;
        this.countries = countries;
        this.credits = credits;
    }

    @Override
    public Optional<Set<String>> getCountries() {
        return this.countries.isPresent() ? Optional.of(new HashSet<>(this.countries.get()))
                : Optional.of(new HashSet<String>());
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
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((backdrop == null) ? 0 : backdrop.hashCode());
        result = prime * result + ((countries == null) ? 0 : countries.hashCode());
        result = prime * result + ((credits == null) ? 0 : credits.hashCode());
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
        if (!(obj instanceof MovieConcrete)) {
            return false;
        }
        final MovieConcrete other = (MovieConcrete) obj;
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
        return true;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
    }

    /**
     * Builder for {@link MovieConcrete} class. See the inherited doc for
     * details.
     */
    public static class MovieBuilderConcrete implements MovieBuilder<MovieBuilderConcrete> {
        private String internalId;
        private Optional<Integer> internalSize = Optional.empty();
        private String internalTitle;
        private LocalDate internalReleaseDate;
        private Path internalPhysicalLocation;

        private Optional<Map<String, String>> internalTags = Optional.empty();
        private Optional<String> internalOverview = Optional.empty();
        private Optional<Picture> internalPoster = Optional.empty();
        private Optional<Double> internalRating = Optional.empty();

        private Optional<Integer> internalDuration = Optional.empty();

        private Optional<Picture> internalBackdrop = Optional.empty();
        private Optional<Set<String>> internalCountries = Optional.empty();
        private Optional<Credits> internalCredits = Optional.empty();

        @Override
        public MovieBuilderConcrete duration(final int duration) {
            this.internalDuration = Optional.ofNullable(duration);
            return this;
        }

        @Override
        public MovieBuilderConcrete size(final int size) {
            this.internalSize = Optional.ofNullable(size);
            return this;
        }

        @Override
        public MovieBuilderConcrete rating(final Double rating) {
            this.internalRating = Optional.ofNullable(rating);
            return this;
        }

        @Override
        public MovieBuilderConcrete id(final String id) {
            this.internalId = id;
            return this;
        }

        @Override
        public MovieBuilderConcrete tags(final Map<String, String> tags) {
            this.internalTags = Optional.ofNullable(tags);
            return this;
        }

        @Override
        public MovieBuilderConcrete overview(final String overview) {
            this.internalOverview = Optional.ofNullable(overview);
            return this;
        }

        @Override
        public MovieBuilderConcrete poster(final Picture poster) {
            this.internalPoster = Optional.ofNullable(poster);
            return this;
        }

        @Override
        public MovieBuilderConcrete title(final String title) {
            this.internalTitle = title;
            return this;
        }

        @Override
        public MovieBuilderConcrete releaseDate(final LocalDate date) {
            this.internalReleaseDate = date;
            return this;
        }

        @Override
        public MovieBuilderConcrete physicalLocation(final Path path) {
            this.internalPhysicalLocation = path;
            return this;
        }

        @Override
        public MovieBuilderConcrete countries(final Set<String> countries) {
            this.internalCountries = Optional.ofNullable(countries);
            return this;
        }

        @Override
        public MovieBuilderConcrete backdrop(final Picture backdrop) {
            this.internalBackdrop = Optional.ofNullable(backdrop);
            return this;
        }

        @Override
        public MovieBuilderConcrete credits(final Credits credits) {
            this.internalCredits = Optional.ofNullable(credits);
            return this;
        }

        @Override
        public Movie build() {
            if (this.internalTitle != null && !this.internalTitle.isEmpty() && this.internalId != null
                    && !this.internalId.isEmpty() && this.internalPhysicalLocation != null
                    && this.internalSize.isPresent() && this.internalDuration.isPresent()
                    && this.internalReleaseDate != null) {
                return new MovieConcrete(this.internalId, this.internalSize.get(), this.internalTitle,
                        this.internalReleaseDate, this.internalPhysicalLocation, this.internalTags,
                        this.internalOverview, this.internalPoster, this.internalRating, this.internalDuration.get(),
                        this.internalBackdrop, this.internalCountries, this.internalCredits);
            } else {
                throw new IllegalStateException();
            }
        }

        @Override
        public Movie buildFrom(final Movie source) {
            Objects.requireNonNull(source);
            this.internalBackdrop = source.getBackdrop();
            this.internalCountries = source.getCountries();
            this.internalDuration = Optional.ofNullable(source.getDuration());
            this.internalId = source.getID();
            this.internalOverview = source.getOverview();
            this.internalPhysicalLocation = source.getPhysicalLocation();
            this.internalPoster = source.getPoster();
            this.internalRating = source.getRating();
            this.internalReleaseDate = source.getReleaseDate();
            this.internalSize = Optional.ofNullable(source.getSize());
            this.internalTags = source.getTags();
            this.internalTitle = source.getTitle();
            this.internalCredits = source.getCredits();
            return this.build();

        }

    }

}
