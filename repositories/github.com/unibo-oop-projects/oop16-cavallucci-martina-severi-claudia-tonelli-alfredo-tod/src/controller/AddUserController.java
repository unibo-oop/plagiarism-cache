package controller;

import java.util.Map;
/**
 * 
 * Add user controller.
 *
 */
public interface AddUserController {

    /**
     * when the register button is clicked.
     */
    void newRegisterLogin();
    /**
     * when you confirm.
     * @param map
     * map
     * 
     */
    void confirmRegistration(Map<UserInfo, String> map);
}
