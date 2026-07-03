package it.lttply.model.domain;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import it.lttply.model.builders.SeasonBuilder;

/**
 * This class provides a container to store a {@link Season} of a
 * {@link TVSerie}, its episode and its mediainfo.
 *
 */
public final class SeasonConcrete extends MultiMediaContainerConcrete<Episode> implements Season {
    private final int progressiveNumber;
    private static final String ERR = "A season is an abstract concept, it doesn't exists in secondary memory";
    private static final String ERR2 = "a season is a abstract concept, it doesn't have its personal size, you can retrieve it by summing the sizes of the episodes";

    /**
     * @param id
     *            the season's id
     * @param size
     *            the season's size (unsupported)
     * @param title
     *            the season's title
     * @param releaseDate
     *            the season's release date (first air date)
     * @param physicalLocation
     *            the season's physical location (folder)
     * @param tags
     *            the season's {@link List} of tags
     * @param overview
     *            the season's overview
     * @param poster
     *            the season's poster
     * @param elements
     *            the season's {@link Set} of {@link Episode}
     * @param progressiveNumber
     *            the season's progressive number
     */
    protected SeasonConcrete(final String id, final int size, final String title, final LocalDate releaseDate,
            final Path physicalLocation, final Optional<Map<String, String>> tags, final Optional<String> overview,
            final Optional<Picture> poster, final Optional<Set<Episode>> elements, final int progressiveNumber) {
        super(id, size, title, releaseDate, physicalLocation, tags, overview, poster, elements);
        this.progressiveNumber = progressiveNumber;
    }

    @Override
    public int getProgressiveNumber() {
        return this.progressiveNumber;
    }

    @Override
    public Path getPhysicalLocation() {
        throw new UnsupportedOperationException(ERR);
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException(ERR2);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + progressiveNumber;
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
        if (!(obj instanceof SeasonConcrete)) {
            return false;
        }
        final SeasonConcrete other = (SeasonConcrete) obj;
        return progressiveNumber == other.progressiveNumber;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, 
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }

    /**
     * Builder for {@link SeasonConcrete} class. See the inherited doc for
     * details.
     */
    public static class SeasonConcreteBuilder implements SeasonBuilder<SeasonConcreteBuilder> {
        private static final String ERR_NOTNECESSARY = "You don't have to add tags for the season, they are the same of the tv series";
        private String internalId;
        private Optional<Integer> internalProgressiveNumber = Optional.empty();
        private String internalTitle;
        private LocalDate internalReleaseDate;

        private Optional<Map<String, String>> internalTags = Optional.empty();
        private Optional<String> internalOverview = Optional.empty();
        private Optional<Picture> internalPoster = Optional.empty();

        private Optional<Set<Episode>> internalElements = Optional.empty();

        @Override
        public SeasonConcreteBuilder elements(final Set<Episode> elements) {
            this.internalElements = Optional.ofNullable(elements);
            return this;
        }

        @Override
        public SeasonConcreteBuilder id(final String id) {
            this.internalId = id;
            return this;
        }

        @Override
        public SeasonConcreteBuilder tags(final Map<String, String> tags) {
            throw new IllegalStateException(ERR_NOTNECESSARY);
        }

        @Override
        public SeasonConcreteBuilder overview(final String overview) {
            this.internalOverview = Optional.ofNullable(overview);
            return this;
        }

        @Override
        public SeasonConcreteBuilder poster(final Picture poster) {
            this.internalPoster = Optional.ofNullable(poster);
            return this;
        }

        @Override
        public SeasonConcreteBuilder title(final String title) {
            this.internalTitle = title;
            return this;
        }

        @Override
        public SeasonConcreteBuilder releaseDate(final LocalDate date) {
            this.internalReleaseDate = date;
            return this;
        }

        @Override
        public SeasonConcreteBuilder physicalLocation(final Path path) {
            throw new UnsupportedOperationException(ERR);
        }

        @Override
        public SeasonConcreteBuilder size(final int size) {
            throw new UnsupportedOperationException(ERR2);
        }

        @Override
        public SeasonConcreteBuilder progressiveNumber(final int progressiveNumber) {
            this.internalProgressiveNumber = Optional.ofNullable(progressiveNumber);
            return this;
        }

        @Override
        public Season build() {
            if (this.internalTitle != null && !this.internalTitle.isEmpty() && this.internalId != null
                    && !this.internalTitle.isEmpty() && this.internalReleaseDate != null
                    && this.internalProgressiveNumber.isPresent()) {
                return new SeasonConcrete(this.internalId, -1, this.internalTitle, this.internalReleaseDate,
                        Paths.get(""), this.internalTags, this.internalOverview, this.internalPoster,
                        this.internalElements, this.internalProgressiveNumber.get());
            } else {
                throw new IllegalStateException();
            }
        }

        @Override
        public Season buildFrom(final Season source) {
            Objects.requireNonNull(source);
            this.internalElements = source.getElements();
            this.internalId = source.getID();
            this.internalOverview = source.getOverview();
            this.internalPoster = source.getPoster();
            this.internalProgressiveNumber = Optional.ofNullable(source.getProgressiveNumber());
            this.internalReleaseDate = source.getReleaseDate();
            this.internalTags = source.getTags();
            this.internalTitle = source.getTitle();
            return this.build();
        }

    }

}
