package org.gitgud.utils;

/**
 * Utility class used to shortener file system or http urls. Copied and adapted from
 * http://www.rgagnon.com/javadetails/java-0661.html
 */
public final class FileUtils {

    private static final String SHORTENER_BACKSLASH_REGEX = "\\\\";
    private static final String SHORTENER_SLASH_REGEX = "/";
    private static final String SHORTENER_BACKSLASH = "\\";
    private static final String SHORTENER_SLASH = "/";
    private static final String SHORTENER_ELLIPSE = "...";

    private FileUtils() {
    }

    /**
     * Compact a path into a given number of characters. Similar to the Win32 API PathCompactPathExA
     *
     * @param path
     *            the path to shortener
     * @param limit
     *            the chars limit
     * @return the formatted string
     */
    public static String pathLengthShortener(final String path, final int limit) {

        if (path.length() <= limit) {
            return path;
        }

        final char[] shortPathArray = new char[limit];
        final char[] pathArray = path.toCharArray();
        final char[] ellipseArray = SHORTENER_ELLIPSE.toCharArray();

        final int pathindex = pathArray.length - 1;
        final int shortpathindex = limit - 1;

        // fill the array from the end
        int i = 0;
        for (; i < limit; i++) {
            if (pathArray[pathindex - i] != '/' && pathArray[pathindex - i] != '\\') {
                shortPathArray[shortpathindex - i] = pathArray[pathindex - i];
            } else {
                break;
            }
        }
        // check how much space is left
        final int free = limit - i;

        if (free < SHORTENER_ELLIPSE.length()) {
            // fill the beginning with ellipse
            System.arraycopy(ellipseArray, 0, shortPathArray, 0, ellipseArray.length);
            /*
             * for (int j = 0; j < ellipseArray.length; j++) { shortPathArray[j] = ellipseArray[j]; }
             */
        } else {
            // fill the beginning with path and leave room for the ellipse
            int j = 0;
            for (; j + ellipseArray.length < free; j++) { // NOPMD
                shortPathArray[j] = pathArray[j];
            }
            // ... add the ellipse
            for (int k = 0; j + k < free; k++) {
                shortPathArray[j + k] = ellipseArray[k];
            }
        }
        return new String(shortPathArray);
    }

    /**
     * Return shorter path based on the limited threshold ex. C:/1/2/test.txt return C:/1/.../test.txt if threshold is 1
     *
     * @param path
     *            the path to shortener
     * @param threshold
     *            the maximum directory to take
     * @return the shortener path
     */
    public static String pathShortener(final String path, final int threshold) {
        if (path.length() < 10) {
            return path;
        }

        String regex = SHORTENER_BACKSLASH_REGEX;
        String sep = SHORTENER_BACKSLASH;

        if (path.indexOf('/') > 0) {
            regex = SHORTENER_SLASH_REGEX;
            sep = SHORTENER_SLASH;
        }

        String[] pathtemp = path.split(regex);
        // remove empty elements
        int elem = 0;
        final String[] newtemp = new String[pathtemp.length];
        int j = 0;
        for (int i = 0; i < pathtemp.length; i++) {
            if (!pathtemp[i].equals("")) {
                newtemp[j++] = pathtemp[i];
                elem++;
            }
        }
        pathtemp = newtemp;
        if (elem > threshold) {
            final StringBuilder sb = new StringBuilder();
            int index = 0;

            // drive or protocol
            final int pos2dots = path.indexOf(':');
            if (pos2dots > 0) {
                // case c:\ c:/ etc.
                sb.append(path.substring(0, pos2dots + 2));
                index++;
                // case http:// ftp:// etc.
                if (path.indexOf(":/") > 0 && pathtemp[0].length() > 2) {
                    sb.append(SHORTENER_SLASH);
                }
            } else {
                final boolean isUNC = path.substring(0, 2).equals(SHORTENER_BACKSLASH_REGEX);
                if (isUNC) {
                    sb.append(SHORTENER_BACKSLASH).append(SHORTENER_BACKSLASH);
                }
            }

            for (; index <= threshold; index++) {
                sb.append(pathtemp[index]).append(sep);
            }

            if (index == elem - 1) {
                sb.append(pathtemp[elem - 1]);
            } else {
                sb.append(SHORTENER_ELLIPSE).append(sep).append(pathtemp[elem - 1]);
            }
            return sb.toString();
        }
        return path;
    }

}
