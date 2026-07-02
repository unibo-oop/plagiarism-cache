package model.user;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This interface contains methods to communicate with user archive.
 *
 * @author Edoardo
 *
 */
public interface ArchiveUser {

  /**
   * This method return the map of user into the archive.
   *
   * @return the userArchive
   */
  Map<Integer, UserImpl> getUserArchive();

  /**
   * This method must be called only at the start of the program, if it had
   * already file config saved. With this method you set the main field
   * userArchive.
   *
   * @param initUserArchive
   *          saved user's archive.
   * @throws Exception
   *           in the case which field it's already initialized.
   */
  void setArchiveUserImpl(final Map<Integer, UserImpl> initUserArchive) throws Exception;

  /**
   * This method adds a User into the archive.
   *
   * @param initUser
   *          the object user in order to add to the archive.
   * @throws Exception
   *           if user is not contained into the archive.
   */
  void addUser(final UserImpl initUser) throws Exception;

  /**
   * This method remove/delete user from the archive.
   *
   * @param userId
   *          user's identifier.
   * @throws Exception
   *           if user is not contained into the archive.
   */
  void removeUser(final Integer userId) throws Exception;

  /**
   * This method return the required user with identifier = userId.
   *
   * @param userId
   *          user's identifier.
   * @return User with userId
   * @throws Exception
   *           if user is not contained into the archive.
   */
  UserImpl getUser(final Integer userId) throws Exception;

  /**
   * @param userId
   *          to find into the archive.
   * @return true if userId is into the archive, else false.
   */
  boolean contains(final Integer userId);

  /**
   * This method return a Set of all UserId in the archive.
   *
   * @return UserId set.
   */
  Set<Integer> getUserId();

  /**
   * This method @return a List of username of user that were already
   * registered.
   */
  List<String> getAllUsername();
}
