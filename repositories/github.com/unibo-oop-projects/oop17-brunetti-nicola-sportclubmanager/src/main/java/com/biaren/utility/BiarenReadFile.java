package com.biaren.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Utility class for read file.
 * @author nbrunetti
 *
 */
public class BiarenReadFile {
    private final InputStream inStream;
    private InputStreamReader inStreamReader;
    private BufferedReader br;
    private boolean open;
    private final String filePath;
    
    /**
     * Constructor.
     * @param path {@link String} path file
     * @param ins the {@link InputStream} to use to handle the file
     */
    public BiarenReadFile(final String path, final InputStream ins) {
        this.filePath = path;
        this.open = false;
        this.inStream = ins;
    }
    
    /**
     * Open file and relative streams.
     * @throws FileNotFoundException if file not exists.
     */
    public void openFile() throws FileNotFoundException {
        this.inStreamReader = new InputStreamReader(this.inStream);
        this.br = new BufferedReader(this.inStreamReader);
        this.open = true;
    }
    
    /**
     * Read and return a single line of the file.
     * @return {@link String} of a single line of the file.
     * @throws IOException if problem with I/O occurs
     */
    public String readSingleLine() throws IOException {
        return this.br.readLine();
    }
    
    /**
     * Close file and relative streams.
     * @throws IOException if problem with I/O occurs
     */
    public void closeFile() throws IOException {
        this.br.close();
        this.inStreamReader.close();
        this.inStream.close();
    }
    
    /**
     * Return true if file is open, false otherwise.
     * @return {@link Boolean} value of file open or not
     */
    public final boolean isOpen() {
        return this.open;
    }
    
    /**
     * Return the file path.
     * @return {@link String} file path
     */
    public final String getPath() {
        return this.filePath;
    }
    
    /**
     * Check if file exist
     * @param f file to check
     * @return true if file exists, false otherwise
     */
    public static boolean fileExists(final File f) {
        if (f.exists() && !f.isDirectory()) {
            return true;
        } 
        return false;
    }
}
