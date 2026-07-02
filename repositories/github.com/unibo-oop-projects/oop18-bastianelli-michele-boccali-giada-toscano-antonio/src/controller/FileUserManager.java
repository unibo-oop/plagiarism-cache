package controller;

import java.util.Optional;
import java.util.Set;

import common.CommonStrings;
import model.user.CurrentUser;
import model.user.User;
import model.user.UserProfile;
import utils.FileManager;
import utils.FileManagerImpl;

/**
 * Class with static method to handle a FileManager of Users.
 */
public final class FileUserManager {

    private FileUserManager() {
    }

    /**
     * Load all users from the login file.
     * 
     * @return a set of users or an empty set if no user is present
     */
    public static Set<User> load() {
        final FileManagerImpl<User> fm = new FileManagerImpl<User>();
        return fm.load(CommonStrings.LOGIN_FILE);
    }

    /**
     * Update into the file user information or add it if User don't exist. Then,
     * file is saved.
     * 
     * @param user : the user to be update or add into the file
     */
    public static void update(final User user) {
        final Set<User> users = FileUserManager.load();
        FileUserManager.searchUsername(users, user.getUserProfile().getUsername()).ifPresent(
            e -> {System.out.println(users.remove(e));

            System.out.println(users);
            System.out.println(user);}
        );
        users.add(user);
        FileUserManager.save(users);
    }

    /**
     * Save set of user into the file. It rewrite the entire file.
     * 
     * @param users : the users set to be saved into the file
     */
    public static void save(final Set<User> users) {
        final FileManager<User> fm = new FileManagerImpl<>();
        fm.save(CommonStrings.LOGIN_FILE, users);
    }

    /**
     * Search if a profile is present in the given set of User.
     * 
     * @param users   : the set of users
     * @param profile : profile to be search
     * @return true if username and password match
     */
    public static boolean searchUserProfile(final Set<User> users, final UserProfile profile) {
        for (final User user : users) {
            if (user.getUserProfile().getUsername().equals(profile.getUsername())
                    && (user.getUserProfile().getPassword().equals(profile.getPassword()))) {
                CurrentUser.getInstance().setUser(user);
                return true;
            }
        }
        return false;
    }

    /**
     * Search if the username is present in the given set of User.
     * 
     * @param users    : the set of users
     * @param username : username to be search
     * @return true if the username is already present
     */
    public static Optional<User> searchUsername(final Set<User> users, final String username) {
        for (final User user : users) {
            if (user.getUserProfile().getUsername().equals(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
