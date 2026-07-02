package org.vise.model.user;

/**
 * This interface represents the logged user.
 *
 */
public interface CurrentUser extends User {

    /**
     * 
     * @return
     *          The email of current user.
     */
    String getEmail();

    /**
     * 
     * @return
     *          The password of current user.
     */
    String getPassword();

    /**
     * 
     * @param activity
     *          The description of the activity.
     */
    void setActivity(String activity);

    /**
     * 
     * @return
     *          The activity that the user is going to do.
     */
    String getActivity();
}
