package iuniversity.storage.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import iuniversity.model.didactics.Course;
import iuniversity.model.didactics.DegreeProgramme;
import iuniversity.model.exams.ExamCall;
import iuniversity.model.exams.ExamReport;
import iuniversity.model.user.Student;
import iuniversity.model.user.Teacher;
import iuniversity.storage.DataStore;

/**
 * This class is responsible for saving and retrieving data. If needed files are not present it creates them.
 *
 */
public class FileDataStoreImpl implements DataStore {
    private static final String PATH_SEPARATOR = System.getProperty("file.separator");
    private static final String STORE_PATH = System.getProperty("user.home") + PATH_SEPARATOR + ".iuniversity"
            + PATH_SEPARATOR + "store" + PATH_SEPARATOR;
    private static final String STUDENTS_STORE_FILE = "students.txt";
    private static final String TEACHERS_STORE_FILE = "teachers.txt";
    private static final String COURSES_STORE_FILE = "courses.txt";
    private static final String DEGREE_PROGRAMMES_STORE_FILE = "degree_programmes.txt";
    private static final String EXAM_CALLS_STORE_FILE = "exam_calls.txt";
    private static final String EXAM_REPORTS_STORE_FILE = "exam_reports.txt";

    /**
     * Initializes the file data store.
     * Creates a directory in user home in which data files will be written
     */
    public FileDataStoreImpl() {
        try {
            final File store = new File(STORE_PATH);
            FileUtils.forceMkdir(store);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <X> void save(final Collection<X> obj, final String path) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(STORE_PATH + path))) {
            out.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createStoreFileIfAbsent(final String file) {
        final File storeFile = new File(STORE_PATH + file);
        if (!storeFile.exists()) {
            try {
                FileUtils.copyInputStreamToFile(ClassLoader.getSystemResourceAsStream("data/store/" + file), storeFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <X> Collection<X> load(final String file) {
        createStoreFileIfAbsent(file);
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(STORE_PATH + file))) {
            return (Collection<X>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveStudents(final Collection<Student> students) {
        this.save(students, STUDENTS_STORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Student> loadStudents() {
        return this.<Student>load(STUDENTS_STORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveTeachers(final Collection<Teacher> teachers) {
        this.save(teachers, TEACHERS_STORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Teacher> loadTeachers() {
        return this.<Teacher>load(TEACHERS_STORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCourses(final Collection<Course> courses) {
        this.save(courses, COURSES_STORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Course> loadCourses() {
        return this.<Course>load(COURSES_STORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveDegreeProgrammes(final Collection<DegreeProgramme> degreeProgrammes) {
        this.save(degreeProgrammes, DEGREE_PROGRAMMES_STORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<DegreeProgramme> loadDegreeProgrammes() {
        return this.<DegreeProgramme>load(DEGREE_PROGRAMMES_STORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveExamCalls(final Collection<ExamCall> examCalls) {
        this.save(examCalls, EXAM_CALLS_STORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<ExamCall> loadExamCalls() {
        return this.<ExamCall>load(EXAM_CALLS_STORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveExamReports(final Collection<ExamReport> examReports) {
        this.save(examReports, EXAM_REPORTS_STORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<ExamReport> loadExamReports() {
        return this.<ExamReport>load(EXAM_REPORTS_STORE_FILE);
    }

}
