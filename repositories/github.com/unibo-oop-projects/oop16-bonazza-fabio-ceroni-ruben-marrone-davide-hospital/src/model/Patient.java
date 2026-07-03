package model;

/**
 * 
 * Interface for Patient.
 *
 */
public interface Patient {

    /**
     * name getter.
     * @return patient's name
     */
    String getName();

    /**
     * surname getter.
     * @return patient's surname
     */
    String getSurname();

    /**
     * codfis getter.
     * @return patient's fiscal code
     */
    String getCodfis();

    /**
     * sex getter.
     * @return patient's sex
     */
    String getSex();

    /**
     * disease getter.
     * @return patient's disease
     */
    String getDisease();

    /**
     * log getter.
     * @return patient's log
     */
    String getLog();

    /**
     * age getter.
     * @return patient's age
     */
    int getAge();

    /**
     * priority's name getter.
     * @return patient's priority
     */
    String getPriorityName();

    /**
     * priority level getter.
     * @return patient's priority level
     */
    int getPriorityLevel();

    /**
     * doctor getter.
     * @return patient's doctor
     */
    Doctor getDoctor();

    /**
     * operation getter.
     * @return patient's surgery status
     */
    boolean isBeingOperated();
    /**
     * updates the patients log.
     * @param log l
     *
     */
    void updateLog(String log);

    /**
     * sets the patients doctor.
     * @param d Doctor
     * @throws IllegalStateException e
     */
    void setDoctor(Doctor d) throws IllegalStateException;

    /**
     * gets the patients ward.
     * @return Ward w
     */
    Ward getWard();
    /**
     * sets the patients ward.
     * @param ward w
     */
    void setWard(Ward ward);
    /**
     * sets operation status.
     * @param status s
     */
    void setOperated(boolean status);
}