package utilities;

public interface Account {

    /**
     * Returns the account's name.
     * @return name of the account
     */
    String getName();

    /**
     * Sets the account's name.
     * @param name of the account
     */
    void setName(String name);

    /**
     * Returns the account's surname.
     * @return surname of the account
     */
    String getSurname();

    /**
     * Sets the account's surname.
     * @param surname of the account
     */
    void setSurname(String surname);

    /**
     * Returns the account's username.
     * @return username of the account
     */
    String getUsername();

    /**
     * Sets the account's username.
     * @param username of the account 
     */
    void setUsername(String username);

    /**
     * Returns the account's password.
     * @return pass of the account
     */
    String getPassword();

    /**
     * Sets the account's password.
     * @param pass of the account 
     */
    void setPassword(String pass);

    /**
     * Returns the account's type. Administrator or operator.
     * @return type of the account
     */
    TypeAccount type();


}

