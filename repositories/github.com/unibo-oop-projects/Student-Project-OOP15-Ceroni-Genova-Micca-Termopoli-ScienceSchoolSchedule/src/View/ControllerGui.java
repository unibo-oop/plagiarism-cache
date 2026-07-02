package View;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import Controller.Reservation;
import Controller.SaveController;
import Controller.SaveControllerInterface;
import Model.CoursesImpl;
import Model.Days;
import Model.ErrorException;
import Model.Hours;
import Model.PersonImpl;
import Model.Professor;
import Model.Room;
import Model.RoomImpl;

/**
 * This class is the principal one that fills the cells of the main table by
 * default and dynamic. Also returns the main table, allow to find the number of
 * specific row or column
 * 
 * 
 * @author Massimiliano Micca
 * 
 *         Modify by Anna Termopoli, Galya Genova
 *
 */
public class ControllerGui implements ControllerGuiInterface{

    private final SaveControllerInterface cont = new SaveController();
    private List<Reservation> setReservation = cont.getObjToSave().getListReservation();
    private DefaultTableModel table = new TableGUI();

    /**
     * this method draw all table, the first implement of this method is by Galya
     * 
     */
    private void drawTable() {
        drawDefaultTable();
        for (Reservation res : this.setReservation) {
            this.table.setValueAt(res, this.getRow(res), this.getColum(res));
            this.table.fireTableCellUpdated(this.getRow(res), this.getColum(res));
        }
        this.table.fireTableDataChanged();
    }

    /**
     * this method draw the main table by default
     * 
     */
    private void drawDefaultTable() {
        int i = 0;
        for (Days days : Days.values()) {
            int y = 1;
            for (Hours hours : Hours.values()) {

                this.table.setValueAt(hours.getValue().toUpperCase(), i, y++);

            }
            this.table.setValueAt(days.getString(), i++, 0);
            for (Room room : this.cont.getObjToSave().getListRoom()) {
                this.table.setValueAt(room.getNameRoom(), i++, 0);
            }
        }
    }

    /**
     * for the main table
     * 
     * @return DefaultTableModel
     */
    public DefaultTableModel getTable() {

        drawTable();

        return this.table;

    }

    /**
     * for specific Reservation , returns his row
     * 
     * @param res
     * @return row
     */

    public Integer getRow(Reservation res) {
        int row = 0;
        drawDefaultTable();
        for (int r = 0; r < table.getRowCount(); r++) {
            if (this.table.getValueAt(r, 0).equals(res.getDay().getString())) {
                for (int a = r; a <= r + cont.getObjToSave().getListRoom().size(); a++) {
                    if ((this.table.getValueAt(a, 0).toString()).equals(res.getRoom().getNameRoom())) {
                        row = a;
                    }

                }
            }

        }
        return row;
    }

    /**
     * for specific Reservation returns his column
     * 
     * @param res
     * @return column
     */
    public Integer getColum(Reservation res) {
        int colum = 0;
        drawDefaultTable();
        for (int c = 0; c < table.getColumnCount(); c++) {
            if (this.table.getValueAt(0, c).toString().equals(res.getHour().getValue())) {
                colum = c;
            }
        }
        return colum;
    }

    /**
     * this method convert the String from JComboBOx to obj Reservation
     * 
     * @param prof
     * @param corso
     * @param giorno
     * @param ora
     * @param stanza
     * @return new Reservation
     * @throws ErrorException
     */

    public Reservation matchString(String prof, String corso, String giorno, String ora, String stanza)
            throws ErrorException {
        PersonImpl person = null;
        CoursesImpl cours = null;
        for (Professor p : cont.getObjToSave().getListProfessor()) {

            if (p.getPerson().toString().equals(prof)) {
                person = new PersonImpl(p.getPerson().getName(), p.getPerson().getSurname());

                for (CoursesImpl cous : p.getCourses()) {
                    if (cous.getName().equals(corso)) {
                        cours = new CoursesImpl(cous.getName(), cous.getType());
                    }
                }
            }

        }
        Days day = null;
        for (Days d : Days.values()) {
            if (d.getString().equals(giorno)) {
                day = d;
            }
        }
        Hours h = null;
        for (Hours ho : Hours.values()) {
            if (ho.getValue().equals(ora)) {
                h = ho;
            }
        }
        RoomImpl room = null;
        for (RoomImpl r : cont.getObjToSave().getListRoom()) {
            if (r.getNameRoom().equals(stanza)) {
                room = r;
            }
        }

        if (prof.equals(" ") || giorno.equals(" ") || ora.equals(" ") || stanza.equals(" ")) {
            throw new ErrorException("Selezionare tutti i campi!");
        }
        return new Reservation(person, cours, day, h, room);
    }

    public SaveControllerInterface getCont() {
        return this.cont;
    }

}
