package iuniversity.test.model.exams;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iuniversity.model.exams.StudentRegistrationStrategy;
import iuniversity.model.exams.StudentRegistrationStrategyFactory;
import iuniversity.model.exams.StudentRegistrationStrategyFactoryImpl;
import iuniversity.model.user.Student;
import iuniversity.test.SampleTestData;

public final class StudentRegistrationStrategyFactoryTest {

    private final StudentRegistrationStrategyFactory strategyFactory = new StudentRegistrationStrategyFactoryImpl();
    private final SampleTestData sampleData = new SampleTestData();
    private final Student marioRossi = sampleData.getMarioRossi();
    private final Student lucianoVerdi = sampleData.getLucianoVerdi();
    private final Student lucaBianchi = sampleData.getLucaBianchi();

    private List<Student> registrationList;

    @BeforeEach
    public void init() {
        registrationList = new ArrayList<>();
    }

    @Test
    public void testAtTheTopOfList() {
        final StudentRegistrationStrategy registrationStrategy = strategyFactory.atTheTopOfList();
        registrationStrategy.register(registrationList, marioRossi);
        registrationStrategy.register(registrationList, lucaBianchi);
        registrationStrategy.register(registrationList, lucianoVerdi);
        assertEquals(List.of(lucianoVerdi, lucaBianchi, marioRossi), registrationList);
        registrationList.clear();
        registrationStrategy.register(registrationList, lucaBianchi);
        registrationStrategy.register(registrationList, marioRossi);
        registrationStrategy.register(registrationList, lucianoVerdi);
        assertEquals(List.of(lucianoVerdi, marioRossi, lucaBianchi), registrationList);
    }

    @Test
    public void testAtTheEndOfList() {
        final StudentRegistrationStrategy registrationStrategy = strategyFactory.atTheEndOfList();
        registrationStrategy.register(registrationList, marioRossi);
        registrationStrategy.register(registrationList, lucaBianchi);
        registrationStrategy.register(registrationList, lucianoVerdi);
        assertEquals(List.of(marioRossi, lucaBianchi, lucianoVerdi), registrationList);
        registrationList.clear();
        registrationStrategy.register(registrationList, lucaBianchi);
        registrationStrategy.register(registrationList, marioRossi);
        registrationStrategy.register(registrationList, lucianoVerdi);
        assertEquals(List.of(lucaBianchi, marioRossi, lucianoVerdi), registrationList);
    }

    @Test
    public void testAlphabeticaOrder() {
        final StudentRegistrationStrategy registrationStrategy = strategyFactory.alfabeticalOrder();
        registrationStrategy.register(registrationList, marioRossi);
        registrationStrategy.register(registrationList, lucaBianchi);
        registrationStrategy.register(registrationList, lucianoVerdi);
        assertEquals(List.of(lucaBianchi, marioRossi, lucianoVerdi), registrationList);
        registrationList.clear();
        registrationStrategy.register(registrationList, lucaBianchi);
        registrationStrategy.register(registrationList, marioRossi);
        registrationStrategy.register(registrationList, lucianoVerdi);
        assertEquals(List.of(lucaBianchi, marioRossi, lucianoVerdi), registrationList);
    }

}
