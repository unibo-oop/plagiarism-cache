package util.image;

/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 * source https://bugs.openjdk.java.net/browse/JDK-8091216.
 */
public class ImageViewPane extends Region {

    private ObjectProperty<ImageView> imageViewProperty = new SimpleObjectProperty<ImageView>();

    /**
     * 
     * @return see source website
     */
    public ObjectProperty<ImageView> imageViewProperty() {
        return imageViewProperty;
    }

    /**
     * 
     * @return see source website
     */
    public ImageView getImageView() {
        return imageViewProperty.get();
    }

    /**
     * 
     * @param imageView see source website
     */
    public void setImageView(final ImageView imageView) {
        this.imageViewProperty.set(imageView);
    }

    /**
     * see source website.
     */
    public ImageViewPane() {
        this(new ImageView());
    }

    /** {@inheritDoc} **/
    @Override
    protected void layoutChildren() {
        ImageView imageView = imageViewProperty.get();
        if (imageView != null) {
            imageView.setFitWidth(getWidth());
            imageView.setFitHeight(getHeight());
            layoutInArea(imageView, 0, 0, getWidth(), getHeight(), 0, HPos.CENTER, VPos.CENTER);
        }
        super.layoutChildren();
    }

    /**
     * 
     * @param imageView see source website
     */
    public ImageViewPane(final ImageView imageView) {
        imageViewProperty.addListener(new ChangeListener<ImageView>() {

            /** {@inheritDoc} **/
            @Override
            public void changed(final ObservableValue<? extends ImageView> arg0, final ImageView oldIV, final ImageView newIV) {
                if (oldIV != null) {
                    getChildren().remove(oldIV);
                }
                if (newIV != null) {
                    getChildren().add(newIV);
                }
            }
        });
        this.imageViewProperty.set(imageView);
    }
}
