package controller;

/**
 * multi-plataform resource.
 * @author Francesco
 */
public interface PathSeparator {
    /**
     * the separator window / linux or mac \.
     * 
     * @return separator / or \
     */
    String getSeparator();

    /**
     * the java home.
     * 
     * @return JavaHome
     */
    String getJavaHome();

    /**
     * directory where this porject call.
     * 
     * @return path
     */
    String getDir();

    /**
     * user name.
     * 
     * @return name account
     */
    String getName();

    /**
     * user home.
     * 
     * @return path
     */
    String getUserHome();

    /**
     * test of all function.
     */
    void test();
}