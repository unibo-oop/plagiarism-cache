package it.lttply.model.domain;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * This class provides a generic container ideal to contain media files. This
 * class should be extended to get all the benefits.
 */
public class SingleMediaContainerConcrete implements SingleMediaContainer {

    private final String id;
    private final int size;
    private final String title;
    private final LocalDate releaseDate;
    private final Path physicalLocation;
    private final Optional<Map<String, String>> tags;
    private final Optional<String> overview;
    private final Optional<Picture> poster;
    private final Optional<Double> rating;

    /**
     * @param id
     *            the media container's id
     * @param size
     *            the media container's size
     * @param title
     *            the media container's title
     * @param releaseDate
     *            the media container's release date
     * @param physicalLocation
     *            the media container's physical location
     * @param tags
     *            the media container's {@link List} of tags
     * @param overview
     *            the media container's overview
     * @param poster
     *            the media container's poster
     * @param rating
     *            the media container's rating
     */
    protected SingleMediaContainerConcrete(final String id, final int size, final String title,
            final LocalDate releaseDate, final Path physicalLocation, final Optional<Map<String, String>> tags,
            final Optional<String> overview, final Optional<Picture> poster, final Optional<Double> rating) {
        super();
        this.id = id;
        this.size = size;
        this.title = title;
        this.releaseDate = releaseDate;
        this.physicalLocation = physicalLocation;
        this.tags = tags;
        this.overview = overview;
        this.poster = poster;
        this.rating = rating;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public Optional<Map<String, String>> getTags() {
        return this.tags.isPresent() ? Optional.of(new HashMap<>(this.tags.get()))
                : Optional.of(new HashMap<String, String>());
    }

    @Override
    public Optional<String> getOverview() {
        return this.overview;
    }

    @Override
    public Optional<Picture> getPoster() {
        return this.poster;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    @Override
    public Path getPhysicalLocation() {
        return Paths.get(this.physicalLocation.toString());
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public Optional<Double> getRating() {
        return this.rating;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((overview == null) ? 0 : overview.hashCode());
        result = prime * result + ((physicalLocation == null) ? 0 : physicalLocation.hashCode());
        result = prime * result + ((poster == null) ? 0 : poster.hashCode());
        result = prime * result + ((rating == null) ? 0 : rating.hashCode());
        result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
        result = prime * result + size;
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SingleMediaContainerConcrete)) {
            return false;
        }
        final SingleMediaContainerConcrete other = (SingleMediaContainerConcrete) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (overview == null) {
            if (other.overview != null) {
                return false;
            }
        } else if (!overview.equals(other.overview)) {
            return false;
        }
        if (physicalLocation == null) {
            if (other.physicalLocation != null) {
                return false;
            }
        } else if (!physicalLocation.equals(other.physicalLocation)) {
            return false;
        }
        if (poster == null) {
            if (other.poster != null) {
                return false;
            }
        } else if (!poster.equals(other.poster)) {
            return false;
        }
        if (rating == null) {
            if (other.rating != null) {
                return false;
            }
        } else if (!rating.equals(other.rating)) {
            return false;
        }
        if (releaseDate == null) {
            if (other.releaseDate != null) {
                return false;
            }
        } else if (!releaseDate.equals(other.releaseDate)) {
            return false;
        }
        if (size != other.size) {
            return false;
        }
        if (tags == null) {
            if (other.tags != null) {
                return false;
            }
        } else if (!tags.equals(other.tags)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
    }

}
