package basicgui;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Size implements SizeInterface {

  private final Dimension screenSize;

  public Size() {
    this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  }

  @Override
  public int getSizeWidth(final int perc) {
    return (int) (this.screenSize.width * ((double) perc / 100));
  }

  @Override
  public int getSizeHeight(final int perc) {
    return (int) (this.screenSize.height * ((double) perc / 100));
  }

  @Override
  public Dimension getDimension(final int perc) {
    final Dimension tmp = this.screenSize;
    tmp.height = tmp.height * perc / 100;
    tmp.width = tmp.width * perc / 100;
    return tmp;
  }

  @Override
  public Dimension getComponentDimension(final int component, final int windowPerc,
      final Dimension dim) {
    final Dimension tmp = new Dimension(dim);
    switch (component) {
      case 0:
        tmp.height = tmp.height / 10;
        tmp.width = tmp.width * windowPerc / 100;
        break;
      case 1:
        tmp.height = tmp.height * windowPerc / 100;
        tmp.width = tmp.width * windowPerc / 100;
        break;
      case 2:
        tmp.height = tmp.height * windowPerc / 100;
        tmp.width = tmp.width / 10;
        break;
      default:
        break;
    }
    return tmp;
  }

}
