package todo.controller.clipboard;

import java.util.Optional;

/**
 * This interface represents an entity that allows to interact with a clipboard
 * to copy ({@link #set}) or paste ({@link #get}) a string to it.
 */
public interface ClipboardProvider {
    /**
     * @return the string content of the clipboard, if any
     */
    Optional<String> get();

    /**
     * @param string the string to be copied on the clipboard overwriting any
     *            previously present one
     */
    void set(String string);
}
