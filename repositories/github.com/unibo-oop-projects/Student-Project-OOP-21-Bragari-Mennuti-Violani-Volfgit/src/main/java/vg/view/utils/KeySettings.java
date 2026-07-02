package vg.view.utils;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class KeySettings {
    /**
     * The map the associates an action to a key.
     */
    private final Map<KeyCode, KeyAction> currentSettings;

    /**
     * Private constructor.
     * @param settings {{@link #currentSettings}}
     * @see KeyAction
     */
    private KeySettings(Map<KeyCode, KeyAction> settings) {
        this.currentSettings = settings;
    }

    /**
     * Default constructor that associates {@link KeyAction} to
     * {@link KeyCode} as is.
     * @return {@link KeySettings}
     */
    public static KeySettings defaultKeySettings() {
        Map<KeyCode, KeyAction> settings = new HashMap<KeyCode, KeyAction>();
        Stream.of(KeyAction.values()).filter(e -> !e.equals(KeyAction.NOACTION)).forEach(e -> settings.put(KeyCode.valueOf(e.toString()), e));
        return new KeySettings(settings);
    }

    /**
     * Constructor for {@link KeySettings} from customized settings.
     * @param settings the map from where each {@link KeyAction} will be bound
     * @return {@link KeySettings}
     */
    public static KeySettings fromSettings(final Map<KeyCode, KeyAction> settings) {
        return new KeySettings(settings);
    }

    /**
     * Returns the {@link KeyAction} associated with the {@link KeyCode}.
     * @param k the {@link KeyCode}
     * @return {@link KeyAction}
     */
    public KeyAction getAction(KeyCode k) {
        var t =  this.currentSettings.get(k);
        if (t==null){
           return KeyAction.NOACTION;
        } else return t;
    }
}