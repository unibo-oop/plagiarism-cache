package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements a StudyRoom, it is Serializable and it has one field
 * that is a map which has key the date and the value a ArrayList of UserId( 0
 * means that the sit is free, another number means that it is busy). The map is
 * Modifiable only by addDate(), takeSit(), cancelSit(). The getter method
 * return a COPY !
 *
 * @author Edoardo
 *
 */
public class StudyRoomImpl implements Serializable, StudyRoom {

  private static final long serialVersionUID = -6559792291462784732L;
  private static final int N = 100;

  private Map<GregorianCalendar, ArrayList<Integer>> mapStudyRoom = new HashMap<>();

  /**
   * Empty constructor.
   */
  public StudyRoomImpl() {
  }

  /**
   * Constructor with @param initStudyRoom which contains the saved StudyRoom
   * data.
   */
  public StudyRoomImpl(final Map<GregorianCalendar, ArrayList<Integer>> initStudyRoom) {
    this.mapStudyRoom.putAll(initStudyRoom);
  }

  @Override
  public Map<GregorianCalendar, ArrayList<Integer>> getStudyRoom() {
    return Collections.unmodifiableMap(this.mapStudyRoom);
  }

  @Override
  public void addDate(final GregorianCalendar day) {
    if (!this.mapStudyRoom.containsKey(day) || this.mapStudyRoom.isEmpty()) {
      ArrayList<Integer> al = new ArrayList<Integer>(StudyRoomImpl.N);
      for (int i = 0; i < StudyRoomImpl.N; i++) {
        al.add(i, 0);
      }
      this.mapStudyRoom.put(day, al);
    }
  }

  @Override
  public List<Integer> getAllSit(final GregorianCalendar day) {
    if (!this.mapStudyRoom.containsKey(day)) {
      this.addDate(day);
    }
    return Collections.unmodifiableList(this.mapStudyRoom.get(day));
  }

  @Override
  public void takeSit(final GregorianCalendar day, final Integer sit, final Integer userId)
              throws Exception {
    if (!this.mapStudyRoom.containsKey(day)) {
      this.addDate(day);
    }
    if ((sit < StudyRoomImpl.N) && (sit >= 0)) {
      if (this.mapStudyRoom.get(day).get(sit) == 0) {
        this.mapStudyRoom.get(day).set(sit, userId);
      } else {
        throw new Exception(+sit + " is a busy sit.");
      }
    } else {
      throw new Exception(+sit + "not valid sit position.");
    }
  }

  @Override
  public void cancelSit(final GregorianCalendar day, final Integer sit, final Integer userId)
              throws Exception {
    if (this.mapStudyRoom.containsKey(day)) {
      if ((sit < StudyRoomImpl.N) && (sit >= 0)) {
        if (this.mapStudyRoom.get(day).get(sit).equals(userId)) {
          this.mapStudyRoom.get(day).set(sit, 0);
        } else {
          throw new Exception(+sit + " not busy by " + userId);
        }
      } else {
        throw new Exception(+sit + " not valid position.");
      }
    }
  }

  @Override
  public int getNumSit() {
    return StudyRoomImpl.N;
  }
}
