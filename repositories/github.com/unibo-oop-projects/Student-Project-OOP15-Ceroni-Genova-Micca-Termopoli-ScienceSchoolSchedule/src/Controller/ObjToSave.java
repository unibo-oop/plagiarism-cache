package Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Model.Professor;
import Model.RoomImpl;

/**
 * @author Massimiliano Micca I have build this class for save the data in a
 *         file; It saves a multiple data (List<RoomImpl>,List<Professor> ,List
 *         <Reservation> ) Make an object composed to tree fields ( List<>) whit
 *         getters and setters.
 */
public class ObjToSave implements ObjToSaveInterface, Serializable {

    private static final long serialVersionUID = 1L;
    private List<RoomImpl> listRoom = new ArrayList<>();
    private List<Professor> listProfessor = new ArrayList<>();
    private List<Reservation> listReservation = new ArrayList<>();

    /**
     * builder
     * 
     * @param listRoom
     * @param listProfessor
     * @param listReservation
     */
    public ObjToSave(List<RoomImpl> listRoom, List<Professor> listProfessor, List<Reservation> listReservation) {
        this.listReservation = listReservation;
        this.listProfessor = listProfessor;
        this.listRoom = listRoom;

    }

    public boolean exist() {
        if (this.listProfessor.isEmpty() || this.listRoom.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public List<RoomImpl> getListRoom() {
        return listRoom;
    }

    public void setListRoom(List<RoomImpl> listRoom) {
        this.listRoom = listRoom;
    }

    public List<Professor> getListProfessor() {
        return listProfessor;
    }

    public void setListProfessor(List<Professor> listProfessor) {
        this.listProfessor = listProfessor;
    }

    public List<Reservation> getListReservation() {
        return listReservation;
    }

    public void setListReservation(List<Reservation> listReservation) {
        this.listReservation = listReservation;
    }

    public void clear() {
        this.listProfessor.clear();
        this.listReservation.clear();
        this.listRoom.clear();
    }

}
