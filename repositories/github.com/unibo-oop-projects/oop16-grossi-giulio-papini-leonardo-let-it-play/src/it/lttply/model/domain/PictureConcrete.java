package it.lttply.model.domain;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import it.lttply.model.builders.PictureBuilder;

/**
 * This class provides a simple container for Pictures. Since this is a
 * container, it provides some commons mediainfo including the physical location
 * of the picture, that you must use it to build your own image.
 */
public final class PictureConcrete extends SingleMediaContainerConcrete implements Picture {

    private static final String ERR = "A picture doesn't have a poster";
    private final PictureFormat format;
    private final Optional<String> author;

    /**
     * 
     * @param id
     *            the picture's id
     * @param size
     *            the picture's size
     * @param title
     *            the picture's title
     * @param releaseDate
     *            the picture's release date
     * @param physicalLocation
     *            the picture's physical location
     * @param tags
     *            the picture's {@link List} of tags
     * @param overview
     *            the picture's overview
     * @param poster
     *            the picture's poster (unsupported)
     * @param rating
     *            the picture's rating
     * @param format
     *            the picture's format
     * @param author
     *            the picture's author
     */
    protected PictureConcrete(final String id, final int size, final String title, final LocalDate releaseDate,
            final Path physicalLocation, final Optional<Map<String, String>> tags, final Optional<String> overview,
            final Optional<Picture> poster, final Optional<Double> rating, final PictureFormat format,
            final Optional<String> author) {
        super(id, size, title, releaseDate, physicalLocation, tags, overview, poster, rating);
        this.format = format;
        this.author = author;
    }

    @Override
    public PictureFormat getFormat() {
        return this.format;
    }

    @Override
    public Optional<Picture> getPoster() {
        throw new UnsupportedOperationException(ERR);
    }

    @Override
    public Optional<String> getAuthor() {
        return this.author;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((format == null) ? 0 : format.hashCode());
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
        if (!(obj instanceof PictureConcrete)) {
            return false;
        }
        final PictureConcrete other = (PictureConcrete) obj;
        if (author == null) {
            if (other.author != null) {
                return false;
            }
        } else if (!author.equals(other.author)) {
            return false;
        }
        return format == other.format;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
    }

    /**
     * This is the internal builder of a {@link Picture}.
     *
     */
    public static class PictureConcreteBuilder implements PictureBuilder<PictureConcreteBuilder> {

        private String internalId;
        private Optional<Integer> internalSize = Optional.empty();
        private String internalTitle;
        private LocalDate internalReleaseDate;
        private Path internalPhysicalLocation;
        private PictureFormat internalFormat;

        private Optional<Map<String, String>> internalTags = Optional.empty();
        private Optional<String> internalOverview = Optional.empty();
        private Optional<Picture> internalPoster = Optional.empty();
        private Optional<Double> internalRating = Optional.empty();
        private Optional<String> internalAuthor = Optional.empty();

        @Override
        public PictureConcreteBuilder size(final int size) {
            this.internalSize = Optional.ofNullable(size);
            return this;
        }

        @Override
        public PictureConcreteBuilder rating(final Double rating) {
            this.internalRating = Optional.ofNullable(rating);
            return this;
        }

        @Override
        public PictureConcreteBuilder id(final String id) {
            this.internalId = id;
            return this;
        }

        @Override
        public PictureConcreteBuilder tags(final Map<String, String> tags) {
            this.internalTags = Optional.ofNullable(tags);
            return this;
        }

        @Override
        public PictureConcreteBuilder overview(final String overview) {
            this.internalOverview = Optional.ofNullable(overview);
            return this;
        }

        @Override
        public PictureConcreteBuilder poster(final Picture poster) {
            throw new UnsupportedOperationException(ERR);
        }

        @Override
        public PictureConcreteBuilder title(final String title) {
            this.internalTitle = title;
            return this;
        }

        @Override
        public PictureConcreteBuilder releaseDate(final LocalDate date) {
            this.internalReleaseDate = date;
            return this;
        }

        @Override
        public PictureConcreteBuilder physicalLocation(final Path path) {
            this.internalPhysicalLocation = path;
            return this;
        }

        @Override
        public PictureConcreteBuilder format(final PictureFormat format) {
            this.internalFormat = format;
            return this;
        }

        @Override
        public PictureConcreteBuilder author(final String author) {
            this.internalAuthor = Optional.ofNullable(author);
            return this;
        }

        @Override
        public Picture build() {
            if (this.internalTitle != null && !this.internalTitle.isEmpty() && this.internalId != null
                    && !this.internalId.isEmpty() && this.internalPhysicalLocation != null
                    && this.internalSize.isPresent() && this.internalReleaseDate != null
                    && this.internalFormat != null) {
                return new PictureConcrete(this.internalId, this.internalSize.get(), this.internalTitle,
                        this.internalReleaseDate, this.internalPhysicalLocation, this.internalTags,
                        this.internalOverview, this.internalPoster, this.internalRating, this.internalFormat,
                        this.internalAuthor);
            } else {
                throw new IllegalStateException();
            }
        }

        @Override
        public Picture buildFrom(final Picture source) {
            Objects.requireNonNull(source);
            this.internalFormat = source.getFormat();
            this.internalId = source.getID();
            this.internalOverview = source.getOverview();
            this.internalPhysicalLocation = source.getPhysicalLocation();
            this.internalPoster = source.getPoster();
            this.internalRating = source.getRating();
            this.internalReleaseDate = source.getReleaseDate();
            this.internalSize = Optional.ofNullable(source.getSize());
            this.internalTags = source.getTags();
            this.internalTitle = source.getTitle();
            this.internalAuthor = source.getAuthor();
            return this.build();
        }

    }

}
