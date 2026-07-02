package manager;

import view.View;

/**
 * Interface of the view manager that will display the current view to the user.
 *
 */
public interface ViewManager {

    /**
     * @param view : the new view that will be displayed in the frame.
     */
    void setView(View view);

}
