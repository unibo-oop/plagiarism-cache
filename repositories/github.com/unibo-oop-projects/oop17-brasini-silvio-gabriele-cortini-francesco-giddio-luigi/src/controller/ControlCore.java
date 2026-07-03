package controller;

import java.util.Optional;

/**
 * interface for get some string saved in controlcoreimpl.
 * 
 * @author Francesco
 *
 */
public interface ControlCore {
    /**
     * PathSeparator, linux / ; window \.
     * 
     * @return / or \
     */
    PathSeparator getPathSeparator();

    /**
     * set lenguage from lenguage_GUI (SelectLenguage).
     * 
     * @param selectedLengauge
     *            expected for example it_IT
     */
    void setLanguage(String selectedLengauge);
}