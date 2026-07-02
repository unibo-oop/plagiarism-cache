package Controller;

import java.util.List;

import Model.Professor;
import Model.RoomImpl;

/**
 * @author Massimiliano Micca
 *
 *         this class is used to make a obj
 */
public interface ObjToSaveInterface {

    /**
     * used for take a list of room from obj
     * 
     * @return
     */
    List<RoomImpl> getListRoom();

    /**
     * used for set a list of room in a obj
     * 
     * @param listRoom
     */
    void setListRoom(List<RoomImpl> listRoom);

    /**
     * used for take a list of professor from obj
     * 
     * @return
     */
    List<Professor> getListProfessor();

    /**
     * used for set a list of professor in a obj
     * 
     * @param listProfessor
     */
    void setListProfessor(List<Professor> listProfessor);

    /**
     * used for get a list of reservation in a obj
     * 
     * @return
     */
    List<Reservation> getListReservation();

    /**
     * used for set a list of reservation in a obj
     * 
     * @param listReservation
     */
    void setListReservation(List<Reservation> listReservation);

    /**
     * this method is never used but need to clear all list in default value
     */
    void clear();

    /**
     * @return
     */
    boolean exist();
}