package view;

/**
 * Interface for Study Room.
 *
 * @author Luca Giorgetti
 *
 */
public interface StudyRoom {
	/**
	 * Return the taken sit.
	 *
	 * @return the taken sit
	 */
	int getTakenSit();

	/**
	 * Sets the current study room status (occupied sits).
	 *
	 * @param status
	 *            array of occupied sits
	 */
	void setStudyRoomStatus(int[] status);

	/**
	 * Returns the selected day.
	 *
	 * @return the selected day
	 */
	int getDateDay();

	/**
	 * Returns the selected month.
	 *
	 * @return the selected month
	 */
	int getDateMonth();

	/**
	 * Returns the selected day.
	 *
	 * @return the selected year
	 */
	int getDateYear();
}
