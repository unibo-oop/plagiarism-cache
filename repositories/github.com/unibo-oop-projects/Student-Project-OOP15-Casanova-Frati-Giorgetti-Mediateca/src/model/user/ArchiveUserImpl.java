package model.user;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.UserException;

/**
 * This class implements Serializable and ArchiveUser, it has only one field
 * which contains the map of user register to the 'Mediateca'.
 *
 * @author Edoardo
 *
 */
public final class ArchiveUserImpl implements Serializable, ArchiveUser {

  /**
   *
   */
  private static final long serialVersionUID = -891777149481744993L;
  private static ArchiveUserImpl singleton = null;
  private Map<Integer, UserImpl> userArchive = new HashMap<>();

  private ArchiveUserImpl() {
  }

  @Override
  public Map<Integer, UserImpl> getUserArchive() {
    return this.userArchive;
  }

  /**
   * Singleton constructor.
   *
   * @return the ArchiveUser.
   */
  public static ArchiveUserImpl getArchiveImpl() {
    if (ArchiveUserImpl.singleton == null) {
      ArchiveUserImpl.singleton = new ArchiveUserImpl();
    }
    return ArchiveUserImpl.singleton;
  }

  @Override
  public void setArchiveUserImpl(final Map<Integer, UserImpl> initUserArchive) throws Exception {
    this.userArchive.putAll(initUserArchive);
  }

  @Override
  public void addUser(final UserImpl initUser) throws UserException {
    if (!ArchiveUserImpl.singleton.contains(initUser.getIdUser())) {
      ArchiveUserImpl.singleton.getUserArchive().put(initUser.getIdUser(), initUser);
    } else {
      throw new UserException("User: " + initUser.getIdUser()
                  + "contained into the archive.Can not add one more time.");
    }
  }

  @Override
  public void removeUser(final Integer userId) throws UserException {
    if (ArchiveUserImpl.singleton.contains(userId)) {
      ArchiveUserImpl.singleton.getUserArchive().remove(userId);
    } else {
      throw new UserException(
                  "User: " + userId + " not contained into the archive. Can not remove it");
    }
  }

  @Override
  public UserImpl getUser(final Integer userId) throws UserException {
    if (ArchiveUserImpl.singleton.contains(userId)) {
      return ArchiveUserImpl.singleton.getUserArchive().get(userId);
    } else {
      throw new UserException(
                  "User: " + userId + " not contained into the archive. Can not remove it");
    }
  }

  @Override
  public boolean contains(final Integer userId) {
    return ArchiveUserImpl.singleton.getUserArchive().containsKey(userId);
  }

  @Override
  public Set<Integer> getUserId() {
    return Collections.unmodifiableSet(ArchiveUserImpl.singleton.getUserArchive().keySet());
  }

  @Override
  public List<String> getAllUsername() {
    List<String> all = new LinkedList<>();
    for (UserImpl i : this.userArchive.values()) {
      all.add(i.getUsername());
    }
    return all;
  }
}
