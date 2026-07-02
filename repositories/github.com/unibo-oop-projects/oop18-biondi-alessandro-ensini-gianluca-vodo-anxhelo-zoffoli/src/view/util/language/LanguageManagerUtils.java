package view.util.language;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import controller.SettingsHandler;

/**
 * Utility class to manage the dynamic language loading and string binding.
 */
public final class LanguageManagerUtils {

    /** The current selected locale. */
    private static final ObjectProperty<Locale> LOCALE;
    private static List<StringProperty> sList;

    static {
        LOCALE = new SimpleObjectProperty<>(getDefaultLocale());
        LOCALE.addListener((observable, oldValue, newValue) -> Locale.setDefault(newValue));
        sList = new ArrayList<>();
    }

    private LanguageManagerUtils() {
    }

    /**
     * Get the supported Locales.
     *
     * @return list of locale objects.
     */
    public static List<Locale> getSupportedLocales() {
        // using Local.ITALIAN instead of Locale.ITALY don't work
        return new ArrayList<>(Arrays.asList(Locale.ENGLISH, Locale.ITALY));
    }

    /**
     * Get the default locale. This is the one saved in the config file id setted,
     * systems default if contained in the supported locales, english otherwise.
     *
     * @return default locale
     */
    public static Locale getDefaultLocale() {
        final Locale sysDefault = Locale.getDefault();
        try {
            return new Locale(SettingsHandler.loadSettings().getLanguage());
        } catch (Exception e) {
            return getSupportedLocales().contains(sysDefault) ? sysDefault : Locale.ENGLISH;
        }
    }

    /**
     * @return current locale
     */
    public static Locale getLocale() {
        return LOCALE.get();
    }

    /**
     * @return current locale property
     */
    public static ObjectProperty<Locale> getLocaleProperty() {
        return LOCALE;
    }

    /**
     * @return resource bundle of current locale
     */
    public static ResourceBundle getResourceBundele() {
        return ResourceBundle.getBundle("lang/lang", getLocale());
    }

    /**
     * Change the current locale.
     * 
     * @param locale new locale
     */
    public static void setLocale(final Locale locale) {
        LOCALE.set(locale);
        Locale.setDefault(locale);
    }

    /**
     * Gets the string with the given key from the resource bundle for the current
     * locale and returning the result.
     * 
     * @param key message key
     * @return localized string
     */
    public static String get(final String key) {
        return ResourceBundle.getBundle("lang/lang", getLocale()).getString(key);
    }

    /**
     * Creates a String binding to a localized String for the given message bundle
     * key.
     *
     * @param key key
     * @return String binding
     */
    public static StringBinding createStringBinding(final String key) {
        return Bindings.createStringBinding(() -> get(key), LOCALE);
    }

    /**
     * Creates a List binding to a localized String for the given message bundle
     * key.
     * 
     * @param keys keys
     * @return List binded
     */
    public static ObservableValue<ObservableList<String>> createListBinding(final String... keys) {
        final ObservableList<String> list = FXCollections.observableArrayList();
        final ObservableValue<ObservableList<String>> oValue;

        for (final String key : keys) {
            final StringProperty sProperty = new SimpleStringProperty();
            sProperty.bind(createStringBinding(key));
            sList.add(sProperty); // trick to prevent sProperty to be deleted by garbage collector
            list.add(sProperty.getValue());
            sProperty.addListener((observable, oldValue, newValue) -> list.set(list.indexOf(oldValue), newValue));
        }
        oValue = new SimpleListProperty<>(list);
        return oValue;
    }
}
