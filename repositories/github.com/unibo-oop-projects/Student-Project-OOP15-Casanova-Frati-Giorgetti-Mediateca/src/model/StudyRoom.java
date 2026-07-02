package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 * This class implements a StudyRoom, it is Serializable and it has one field
 * that is a map which has key the date and the value a ArrayList of UserId( 0
 * means that the sit is free, another number means that it is busy).
 *
 * @author Edoardo
 *
 */
public interface StudyRoom {

  /**
   * This method @return a copy of the StudyRoom's map in order to ba saved.
   */
  Map<GregorianCalendar, ArrayList<Integer>> getStudyRoom();

  /**
   * This method add a day into the archive of StudyRoom.
   *
   * @param day
   *          to add.
   */
  void addDate(final GregorianCalendar day);

  /**
   * This method return a list of integer which represent the user list that
   * have taken a sit. In the case which the day is not in the map, it will be
   * add.
   *
   * @param day
   *          to search.
   * @return the list of free sit.
   */
  List<Integer> getAllSit(final GregorianCalendar day);

  /**
   * This method add a userId to a specific sit into the StudyRoom. In the case
   * which the day is not in the map, it will be add.
   *
   * @param day
   *          to search.
   * @param sit
   *          required.
   * @param userId
   *          user's identifier.
   * @throws Exception
   *           in the case which the sit is a number < 0 || >= StudyRoom.N && in
   *           the case of busy sit.
   */
  void takeSit(final GregorianCalendar day, final Integer sit, final Integer userId)
              throws Exception;

  /**
   * This method remove a userId from a specific sit into the study room. In the
   * case which the day is not into the map, it will be anything.
   *
   * @param day
   *          to search.
   * @param sit
   *          required to cancel.
   * @param userId
   *          to remove.
   * @throws Exception
   *           in the case which the sit is a number < 0 || >= StudyRoom.N or if
   *           the sit is not busy by the specific userId.
   */
  void cancelSit(final GregorianCalendar day, final Integer sit, final Integer userId)
              throws Exception;

  /**
   *
   * @return the number of sit.
   */
  int getNumSit();
}
