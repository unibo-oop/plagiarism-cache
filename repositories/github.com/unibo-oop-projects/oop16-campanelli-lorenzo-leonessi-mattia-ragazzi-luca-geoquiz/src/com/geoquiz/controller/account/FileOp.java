package com.geoquiz.controller.account;

import java.io.IOException;

/**
 * FileOp interface that provides read, save and removeAllAccount methods.
 *
 */
public interface FileOp {

    /**
     * Read from file.
     * 
     * @param namefile
     *            the name of the file.
     * @throws IOException
     *             exception.
     */
    void read(String namefile) throws IOException;

    /**
     * Save on file.
     */
    void save();

    /**
     * Remove all player's account.
     */
    void removeAllAccount();

}
