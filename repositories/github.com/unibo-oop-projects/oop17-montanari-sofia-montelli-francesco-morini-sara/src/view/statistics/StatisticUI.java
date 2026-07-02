package view.statistics;
/**
 *Interface for the statistics scenes.
 * @param <X> generic type that define a controller.
 */
public interface StatisticUI<X> {
    /**
     * 
     * @return a controller.
     */
     X getController();

     /**
      * 
      * @param controller to set in a view
      */
     void setController(X controller);

     /**
      * Initialize all the data in the view.
      * 
      */
     void initialize();
     /**
      * method that close the application.
      */
     void exit();
}
