package view.navy_builder_interface;

import view.interfaces.BasicView;
import view.interfaces.ConfermableView;
/**
 * Interface for the {@link NavyBuilder} user interface.
 */
public interface NavyBuilderUI extends BasicView, ConfermableView {
   /**
    * Sets a random disposition.
    */
   void random();
}
