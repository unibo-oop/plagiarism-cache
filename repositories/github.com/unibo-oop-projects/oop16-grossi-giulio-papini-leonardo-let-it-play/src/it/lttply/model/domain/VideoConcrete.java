package it.lttply.model.domain;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * This class provides a simple container for movies.
 */
public class VideoConcrete extends SingleMediaContainerConcrete implements Video {

    private final int duration;

    /**
     * 
     * @param id
     *            the video's id
     * @param size
     *            the video's size
     * @param title
     *            the video's title
     * @param releaseDate
     *            the video's release date
     * @param physicalLocation
     *            the video's size physical location
     * @param tags
     *            the video's {@link List} of tags
     * @param overview
     *            the video's overview
     * @param poster
     *            the video's poster
     * @param rating
     *            the video's rating
     * @param duration
     *            the video's duration
     */
    protected VideoConcrete(final String id, final int size, final String title, final LocalDate releaseDate,
            final Path physicalLocation, final Optional<Map<String, String>> tags, final Optional<String> overview,
            final Optional<Picture> poster, final Optional<Double> rating, final int duration) {
        super(id, size, title, releaseDate, physicalLocation, tags, overview, poster, rating);
        this.duration = duration;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + duration;
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VideoConcrete other = (VideoConcrete) obj;
        return duration != other.duration;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
    }

}
