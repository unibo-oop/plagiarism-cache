package controller;
/**
 * changePass.
 *
 */
public interface ChangePasswordController {
    /**
     * 
     * @param oldPass
     * oldPass
     * @param newPass
     * newPass
     */
    void confirmChange(String oldPass, String newPass);
}
