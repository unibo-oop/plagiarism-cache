package it.lttply.model.domain;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import it.lttply.model.builders.EpisodeBuilder;

/**
 * This class provides a container to store an {@link Episode} of a
 * {@link Season} of a {@link TVSerie}.
 */
public class EpisodeConcrete extends VideoConcrete implements Episode {
    private final int progressiveNumber;

    /**
     * @param id
     *            the episode's id
     * @param size
     *            the episode's size
     * @param title
     *            the episode's title
     * @param releaseDate
     *            the episode's release date
     * @param physicalLocation
     *            the episode's physical location
     * @param tags
     *            the episode's {@link List} of tags
     * @param overview
     *            the episode's overview
     * @param poster
     *            the episode's poster
     * @param rating
     *            the episode's rating
     * @param duration
     *            the episode's duration
     * @param progressiveNumber
     *            the episode's progressive number
     */
    protected EpisodeConcrete(final String id, final int size, final String title, final LocalDate releaseDate,
            final Path physicalLocation, final Optional<Map<String, String>> tags, final Optional<String> overview,
            final Optional<Picture> poster, final Optional<Double> rating, final int duration,
            final int progressiveNumber) {
        super(id, size, title, releaseDate, physicalLocation, tags, overview, poster, rating, duration);
        this.progressiveNumber = progressiveNumber;
    }

    @Override
    public int getProgressiveNumber() {
        return this.progressiveNumber;
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
        if (!(obj instanceof EpisodeConcrete)) {
            return false;
        }
        final EpisodeConcrete other = (EpisodeConcrete) obj;
        return progressiveNumber == other.progressiveNumber;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
    }

    /**
     * Builder for {@link Episode}. See the inherited doc for details.
     */
    public static class EpisodeConcreteBuilder implements EpisodeBuilder<EpisodeConcreteBuilder> {

        private static final String ERR_NOTNECESSARY = "You don't have to add tags for the episode, they are already specified into the tv series";
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
        private Optional<Integer> internalProgressiveNumber = Optional.empty();

        @Override
        public EpisodeConcreteBuilder duration(final int duration) {
            this.internalDuration = Optional.ofNullable(duration);
            return this;
        }

        @Override
        public EpisodeConcreteBuilder rating(final Double rating) {
            this.internalRating = Optional.ofNullable(rating);
            return this;
        }

        @Override
        public EpisodeConcreteBuilder id(final String id) {
            this.internalId = id;
            return this;
        }

        @Override
        public EpisodeConcreteBuilder tags(final Map<String, String> tags) {
            throw new IllegalStateException(ERR_NOTNECESSARY);
        }

        @Override
        public EpisodeConcreteBuilder overview(final String overview) {
            this.internalOverview = Optional.ofNullable(overview);
            return this;
        }

        @Override
        public EpisodeConcreteBuilder poster(final Picture poster) {
            this.internalPoster = Optional.ofNullable(poster);
            return this;
        }

        @Override
        public EpisodeConcreteBuilder title(final String title) {
            this.internalTitle = title;
            return this;
        }

        @Override
        public EpisodeConcreteBuilder releaseDate(final LocalDate date) {
            this.internalReleaseDate = date;
            return this;
        }

        @Override
        public EpisodeConcreteBuilder physicalLocation(final Path path) {
            this.internalPhysicalLocation = path;
            return this;
        }

        @Override
        public EpisodeConcreteBuilder size(final int size) {
            this.internalSize = Optional.ofNullable(size);
            return this;
        }

        @Override
        public EpisodeConcreteBuilder progressiveNumber(final int progressiveNumber) {
            this.internalProgressiveNumber = Optional.ofNullable(progressiveNumber);
            return this;

        }

        @Override
        public Episode build() {
            if (this.internalId != null && !this.internalId.isEmpty() && this.internalSize.isPresent()
                    && this.internalReleaseDate != null && this.internalPhysicalLocation != null
                    && this.internalProgressiveNumber.isPresent() && this.internalTitle != null
                    && !this.internalTitle.isEmpty() && this.internalDuration.isPresent()) {
                return new EpisodeConcrete(this.internalId, this.internalSize.get(), this.internalTitle,
                        this.internalReleaseDate, this.internalPhysicalLocation, this.internalTags,
                        this.internalOverview, this.internalPoster, this.internalRating, this.internalDuration.get(),
                        this.internalProgressiveNumber.get());
            } else {
                throw new IllegalStateException();
            }

        }

        @Override
        public Episode buildFrom(final Episode source) {
            Objects.requireNonNull(source);
            this.internalDuration = Optional.ofNullable(source.getDuration());
            this.internalId = source.getID();
            this.internalOverview = source.getOverview();
            this.internalPhysicalLocation = source.getPhysicalLocation();
            this.internalPoster = source.getPoster();
            this.internalProgressiveNumber = Optional.ofNullable(source.getProgressiveNumber());
            this.internalRating = source.getRating();
            this.internalReleaseDate = source.getReleaseDate();
            this.internalSize = Optional.ofNullable(source.getSize());
            this.internalTags = source.getTags();
            this.internalTitle = source.getTitle();
            return this.build();
        }

    }
}
