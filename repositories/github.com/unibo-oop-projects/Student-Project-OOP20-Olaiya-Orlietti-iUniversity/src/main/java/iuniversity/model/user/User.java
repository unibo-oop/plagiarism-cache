package iuniversity.model.user;

public interface User {

    /**
     * @return a string with the name
     */
    String getName();
    /**
     * @return a string with the last name
     */
    String getLastName();
    /**
     * @return a string with the username
     */
    String getUsername();
    /**
     * @return an int with the id
     */
    int getId();
    public enum Gender {

        /**
         * The user is male
         */
        MALE, 
        /**
         * The user is female
         */
        FEMALE, 
        /**
         * The user don't know
         */
        UNSPECIFIED
    }

    public enum UserType{
        
        /**
         * The user is a student
         */
        STUDENT, 
        /**
         * The user is a teacher
         */
        TEACHER, 
        /**
         * The user is an admin
         */
        ADMIN
    }
}
