package it.unibo.game.app.model;

import it.unibo.game.app.api.Dimension;

/**
 * class that models an object dimension in terms of height and width.
 */
public class DimensionImpl implements Dimension {

  private double width;
  private double height;

  /**
   * constructor of this class.
   * 
   * @param height object height
   * @param width  object width
   */
  public DimensionImpl(final double height, final double width) {
    this.height = height;
    this.width = width;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getHeight() {

    return this.height;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getWidth() {

    return this.width;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setHeight(final double height) {

    this.height = height;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setWidth(final double width) {

    this.width = width;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void increaseWidth(final double width) {

    this.width += width;
  }

}
