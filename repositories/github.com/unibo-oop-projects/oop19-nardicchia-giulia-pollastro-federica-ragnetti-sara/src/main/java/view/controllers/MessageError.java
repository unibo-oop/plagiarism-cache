package view.controllers;

/**
 * Enumeration for error message in the login scene.
 *
 */
public enum MessageError {

    /**
     * String to show when fields are empty.
     */
    EMPTY_FIELD("Fields cannot be empty!"),

    /**
     * String to show when user insert an existing username.
     */
    EXISTING_USERNAME("Username already exists!"),

    /**
     * String to show when password is wrong or the username doesn't exist.
     */
    INCORRECT_ENTRY("Incorrect password or non-existent user!");

    private String message;

    MessageError(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "*" + this.message;
    }
}
