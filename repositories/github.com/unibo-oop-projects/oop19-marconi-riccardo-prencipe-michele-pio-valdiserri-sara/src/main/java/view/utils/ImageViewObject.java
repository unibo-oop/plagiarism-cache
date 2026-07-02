package view.utils;

import javafx.scene.image.Image;

/**
 * Convert to an Image Object.
 *
 */
public enum ImageViewObject {

  /**
   * Application icon 2048.
   */
  LOGO("view/images/2048_logo.png"),

  /**
   * Image of how to play.
   */
  HTP("view/images/howtoplay.jpg"),

  /**
   * Image of 2048.
   */
  IMAGE2048("view/images/2048.png");

  private Image image;

  ImageViewObject(final String name) {
    this.image = new Image(this.getClass().getClassLoader().getResourceAsStream(name));
  }

  /**
   * This method return an image.
   * 
   * @return {@link Image}
   */
  public Image getImage() {
    return this.image;
  }
}
