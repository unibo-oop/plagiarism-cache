package it.lttply.model.utils;

import it.lttply.model.domain.Person;
import it.lttply.model.domain.Picture;
import it.lttply.model.domain.PictureFormat;

/**
 * Provides identifiers for different kind of images, useful to associate every
 * kind of image to its {@link PictureFormat}.
 * 
 */
public enum PictureType {
    /**
     * Indicates that the {@link Picture} must be managed as poster which have
     * its specific {@link PictureFormat}.
     */
    IMAGE_POSTER,

    /**
     * Indicates that the {@link Picture} must be managed as backdrop which have
     * its specific {@link PictureFormat}.
     */
    IMAGE_BACKDROP,

    /**
     * Indicates that the {@link Picture} must be managed as profile photo of a
     * {@link Person} which have its specific {@link PictureFormat}.
     */
    IMAGE_PROFILE_PHOTO
}
