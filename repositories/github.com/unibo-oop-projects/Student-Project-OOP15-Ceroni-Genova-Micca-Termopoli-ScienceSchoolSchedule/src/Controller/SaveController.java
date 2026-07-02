package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Model.CoursesImpl;
import Model.ListCourses;
import Model.ListProfessor;
import Model.ListRoom;
import Model.PersonImpl;
import Model.Professor;
import Model.ProfessorImpl;
import Model.RoomImpl;

/**
 * @author Massimiliano Micca
 *
 */
public class SaveController implements SaveControllerInterface {

    private final String path = System.getProperty("user.home") + System.getProperty("file.separator")
            + "SiencesSchoolSchedul.dat";
    ObjToSave obj = new ObjToSave(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    public void save(ObjToSave obj) {
        try {
            FileOutputStream out = new FileOutputStream(new File(path));
            ObjectOutputStream p = new ObjectOutputStream(out);
            p.writeObject(obj);
            p.close();

        } catch (Exception e) {
            System.out.println("Scrittura fallita " + e);
            e.printStackTrace();
        }

    }

    /**
     * read a Object from file. if the file not exsist make a new file.
     */
    private void openFile() {
        if (new File(this.path).exists()) {
            try {
                FileInputStream istream = new FileInputStream(path);
                ObjectInputStream p = new ObjectInputStream(istream);
                try {
                    this.obj = (ObjToSave) p.readObject();
                    p.close();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createNewEmptyList();
            this.save(this.obj);
        }
    }

    public void reset() {
        this.obj.clear();
        createNewEmptyList();
        this.save(this.obj);

    }

    /**
     * this method create a new default file, if it's never set
     */
    private void createNewEmptyList() {

        List<Professor> tempProf = new ArrayList<>();
        List<RoomImpl> tempRoom = new ArrayList<>();
        for (ListProfessor p : ListProfessor.values()) {
            List<CoursesImpl> c = new ArrayList<>();
            for (ListCourses courses : p.getCourses()) {
                c.add(new CoursesImpl(courses.getValue(), courses.getType()));
            }
            tempProf.add(new ProfessorImpl(new PersonImpl(p.getName(), p.getSurname()), c));
        }
        this.obj.setListProfessor(tempProf);
        for (ListRoom p : ListRoom.values()) {
            tempRoom.add(new RoomImpl(p.getValue()));
        }
        this.obj.setListRoom(tempRoom);
    }

    public ObjToSave getObjToSave() {
        if (this.obj.exist())
            this.openFile();
        return this.obj;

    }

}
