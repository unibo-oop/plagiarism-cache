package talisman.view;

import java.util.EventListener;
import java.util.List;

public interface TalismanChoiceActionWindow {
    interface ChoiceListener extends EventListener {
        void optionChoosen(int option);
    }

    /**
     * Shows the window without waiting for it to close.
     */
    void showAsync();

    /**
     * Shows a new window for choosing an option.
     * 
     * @param options       the list of options
     * @param optionsStatus a list of booleans used to set which options are
     *                      selectable or not
     * @param listener      the listener to call when an option is selected
     */
    static void show(final List<String> options, final List<Boolean> optionsStatus, final ChoiceListener listener) {
        final TalismanChoiceActionWindow window = new TalismanChoiceActionWindowImpl(options, optionsStatus, listener);
        window.showAsync();
    }
}
