package model.util.format;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Manage allowed image and Mustashi proprietary format. Implements method that
 * make simple to work with file dialog using ExtensionFilter
 */
public class FormatManager {

    // used to build path
    private static final String SEP = System.getProperty("file.separator");
    private static final String USER_HOME = System.getProperty("user.home") + SEP;
    private static final String MSHI_WORKSPACE_NAME = "Mustashi";

    private static final String CONVOLUTION_WORKSPACE_NAME = MSHI_WORKSPACE_NAME + SEP + "Convolutions" + SEP;
    private static final String HISTORIES_WORKSPACE_NAME = MSHI_WORKSPACE_NAME + SEP + "Histories" + SEP;

    // path to workspace location
    private static final String DEFAULT_SETTINGS_PATH = USER_HOME + MSHI_WORKSPACE_NAME + SEP;
    private static final String DEFAULT_CONVOLUTIONS_PATH = USER_HOME + CONVOLUTION_WORKSPACE_NAME;
    private static final String DEFAULT_HISTORIES_PATH = USER_HOME + HISTORIES_WORKSPACE_NAME;

    // extension filter
    private static final String JPEG = Format.jpeg.toString();
    private static final String JPG = Format.jpg.toString();
    private static final String MSHI = "*" + Format.mshi.toString();
    private static final ExtensionFilter JPEG_JPG_EXT = new ExtensionFilter("JPEG/JPG", JPEG, JPG);

    private static final String IMAGES_PROJECT_FILTER_NAME = "IMAGES/PROJECT";
    private static final ExtensionFilter ALL_EXT = new ExtensionFilter(IMAGES_PROJECT_FILTER_NAME,
            getAllowedMustashiFormat());
    private static final Map<String, ExtensionFilter> MAP = new HashMap<>();
    private static final FileChooser CHOOSER = new FileChooser();
    private static boolean extensionIsSet;

    /**
     * @return DEFAULT_SETTINGS_PATH
     */
    public static String getDefaultSettingsPath() {
        final File tmp = new File(DEFAULT_SETTINGS_PATH);
        if (tmp.exists() || tmp.mkdirs()) {
            return DEFAULT_SETTINGS_PATH;
        }
        return DEFAULT_SETTINGS_PATH;
    }

    /**
     * @return DEFAULT_CONVOLUTIONS_PATH
     */
    public static String getDefaultConvolutionsPath() {
        final File tmp = new File(DEFAULT_CONVOLUTIONS_PATH);
        if (tmp.exists() || tmp.mkdirs()) {
            return DEFAULT_CONVOLUTIONS_PATH;
        }
        return DEFAULT_CONVOLUTIONS_PATH;
    }

    /**
     * @return DEFAULT_HISTORIES_PATH
     */
    public static String getDefaultHistoriesPath() {
        final File tmp = new File(DEFAULT_HISTORIES_PATH);
        if (tmp.exists() || tmp.mkdirs()) {
            return DEFAULT_HISTORIES_PATH;
        }
        return DEFAULT_HISTORIES_PATH;
    }

    /**
     * @return new initialized fileChooser with "all allowed format" extension
     *         filter setted as selected
     */
    public FileChooser getLoaderChooser() {
        setExtensionFilter();
        CHOOSER.setSelectedExtensionFilter(getExtensionFilter("**" + ALL_EXT.getDescription()));
        return CHOOSER;
    }

    /**
     * @param extFilter filter to set as selected, use as example: "*.png"
     * @return new initialized FileChooser with extFilter as selected extension
     *         filter
     */
    public FileChooser getSaverChooser(final String extFilter) {
        setExtensionFilter();
        if (extFilter.equals(JPEG) || extFilter.equals(JPG)) {
            CHOOSER.setSelectedExtensionFilter(getExtensionFilter("**" + JPEG_JPG_EXT.getDescription()));
        } else {
            CHOOSER.setSelectedExtensionFilter(getExtensionFilter(extFilter));
        }
        return CHOOSER;
    }

    /**
     * @param format to verify if allowed
     * @return true if format allowed
     */
    public static boolean isAllowedMustashiFormat(final String format) {
        setExtensionFilter();
        for (final String s : getAllowedMustashiFormat()) {
            if (s.equals(format)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Initialize MAP and CHOOSER list of extension filter
     */
    private static void setExtensionFilter() {
        if (extensionIsSet) {
            return;
        }
        for (final ExtensionFilter e : getAllowedExtensionFilter()) {
            MAP.put(e.getDescription(), e);
        }

        for (final String e : MAP.keySet()) {
            if (!e.equals(ALL_EXT.getDescription())) {
                CHOOSER.getExtensionFilters().add(MAP.get(e));
            }
        }
        CHOOSER.getExtensionFilters().add(MAP.get(ALL_EXT.getDescription()));

        extensionIsSet = true;
    }

    /*
     * Return a new ExtensionFilter if format param is an allowed one. Have to give
     * format in this form: " *.format "
     * 
     * param: format to transform in ExtensionFilter
     * 
     * return: new ExtensionFilter with format if allowed, otherwise "*.*"
     */
    private static ExtensionFilter getExtensionFilter(final String format) {
        setExtensionFilter();
        final ExtensionFilter tmpExt = MAP.get(format.substring(2).toUpperCase(Locale.getDefault()));
        return tmpExt != null ? tmpExt : new ExtensionFilter("*.*", "*.*");
    }

    /*
     * Return in list all allowed images and project format in respectively new
     * ExtensionFilter, adding "all allowed format" in a single ExtensionFilter at
     * the end of list.
     * 
     * return: allowed extension filter to FileChooser dialog (es."PNG","*.png")
     */
    private static List<ExtensionFilter> getAllowedExtensionFilter() {
        final List<ExtensionFilter> list = new ArrayList<ExtensionFilter>();
        for (final String f : getAllowedMustashiFormat()) {
            if (f.equals(JPEG)) {
                list.add(JPEG_JPG_EXT);
            } else if (!f.equals(JPG)) {
                list.add(new ExtensionFilter(f.substring(2).toUpperCase(Locale.getDefault()), f));
            }
        }
        list.add(ALL_EXT);
        return list;
    }

    /*
     * return all allowed images and project format preceded by *.
     * 
     * (es: *.png, *.mshi )
     */
    private static List<String> getAllowedMustashiFormat() {
        final List<String> list = new ArrayList<String>();
        for (final Format p : Format.values()) {
            if (p.toString().substring(0, 1).equals("*")) {
                list.add(p.toString());
            }
        }
        list.add(MSHI);
        return list;
    }

}
