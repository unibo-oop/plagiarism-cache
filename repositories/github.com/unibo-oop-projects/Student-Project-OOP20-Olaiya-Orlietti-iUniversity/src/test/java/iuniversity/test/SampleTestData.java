package iuniversity.test;

import java.time.LocalDate;
import java.util.Set;

import iuniversity.model.didactics.Course;
import iuniversity.model.didactics.CourseImpl;
import iuniversity.model.didactics.DegreeProgramme;
import iuniversity.model.didactics.DegreeProgrammeImpl;
import iuniversity.model.didactics.DegreeProgramme.DegreeType;
import iuniversity.model.user.Student;
import iuniversity.model.user.StudentImpl;
import iuniversity.model.user.User.Gender;

public final class SampleTestData {

    private final Course analisiMatematica = new CourseImpl("Analisi Matematica", 12);
    private final Course programmazione = new CourseImpl("Programmazione", 12);
    private final Course algebra = new CourseImpl("Algebra e Geometria", 6);
    private final DegreeProgramme ingegneria = new DegreeProgrammeImpl("Ingegneria e scienze informatica",
            DegreeType.BACHELOR, Set.of(analisiMatematica, programmazione, algebra));
    private final Student marioRossi = new StudentImpl.StudentBuilder("Mario", "Rossi", 1, 1).address("via")
            .dateOfBirth(LocalDate.now()).degreeProgramme(ingegneria).gender(Gender.MALE).username("stu.rossi.mario")
            .build();
    private final Student lucianoVerdi = new StudentImpl.StudentBuilder("Luciano", "Verdi", 2, 2).address("via")
            .dateOfBirth(LocalDate.now()).degreeProgramme(ingegneria).gender(Gender.MALE).username("stu.verdi.luciani")
            .build();
    private final Student lucaBianchi = new StudentImpl.StudentBuilder("Luca", "Bianchi", 3, 3).address("via")
            .dateOfBirth(LocalDate.now()).degreeProgramme(ingegneria).gender(Gender.MALE).username("stu.bianchi.luca")
            .build();

    public Course getAnalisiMatematica() {
        return analisiMatematica;
    }

    public Course getProgrammazione() {
        return programmazione;
    }

    public Course getAlgebra() {
        return algebra;
    }

    public DegreeProgramme getIngegneria() {
        return ingegneria;
    }

    public Student getLucianoVerdi() {
        return lucianoVerdi;
    }

    public Student getMarioRossi() {
        return marioRossi;
    }

    public Student getLucaBianchi() {
        return lucaBianchi;
    }
}
