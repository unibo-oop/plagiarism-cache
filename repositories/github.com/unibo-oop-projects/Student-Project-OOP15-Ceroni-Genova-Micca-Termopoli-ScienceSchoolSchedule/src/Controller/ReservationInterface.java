package Controller;

import Model.CoursesImpl;
import Model.Hours;
import Model.PersonImpl;
import Model.RoomImpl;

/**
 * 
 * @author Massimiliano Micca
 *
 */
public interface ReservationInterface {

    void setPerson(PersonImpl prof);

    void setCourse(CoursesImpl course);

    void setHour(Hours hour);

    void setRoom(RoomImpl room);

    PersonImpl getPerson();

    CoursesImpl getCourse();

    Hours getHour();

    RoomImpl getRoom();

}