package test;

import static model.Classrooms.A;
import static model.Classrooms.B;
import static model.Classrooms.MAGNA;
import static model.Classrooms.VELA;
import static model.SubjectType.LT1;
import static model.SubjectType.LT3;
import static org.junit.Assert.fail;
import model.Classrooms;
import model.ClassroomsDailyTime;
import model.Subject;
import model.interfaces.IClassroomsDailyTime;
import model.interfaces.ISubject;

import org.junit.Test;

/**
 * * Class for testing the correct working of the class {@link ClassroomsDailyTime}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class TestClassroomsDailyTime {

	private static final String S = "Success!";
	
	/**
	 * Necessary test to control that the class ClassroomsDailyTime follows the correct 
	 * working specified in the methods of the interface {@link IClassroomsDailyTime}.
	 */
	@Test
	public void test() {
		final IClassroomsDailyTime c = new ClassroomsDailyTime();
		try {
			c.add(createSubOOP(), MAGNA, 10, 2);
			c.add(createSubOOP(), A, 10, 2);
			c.add(createSubSO(), VELA, 10, 2);
			c.add(createSubSO(), MAGNA, 13, 2);
			c.remove(MAGNA, 13, 2);
		} catch (IllegalArgumentException e) {
			fail("this sequence of instructions is right!");
		}
		
		IClassroomsDailyTime d;
		try {
			d = new ClassroomsDailyTime(null);
			fail("can't accept null");
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
		
		d = new ClassroomsDailyTime(c);
		if (d.equals(c)) {
			System.out.println(S);
		}
		
		for (final Classrooms cls : c.wherePerforming(createSubOOP(), 11)) {
			if (!(cls.equals(MAGNA) || cls.equals(A))) {
				fail("The only classrooms busied by that subject are Aula Magna and Aula A");
			}
		}
		
		if (!c.wherePerforming(null, 11).isEmpty()) {
			fail("if sub == null the set must be empty!");
		}
		
		for (final Classrooms cls : c.whereTeaching(createSubOOP().getTeachName(), 11)) {
			if (!(cls.equals(MAGNA) || cls.equals(A))) {
				fail("The only classrooms busied by that teacher are Aula Magna and Aula A");
			}
		}
		
		if (!c.whereTeaching(null, 11).isEmpty()) {
			fail("if teach == null the set must be empty!");
		}
		
		try {
			c.add(new Subject("anotherSubject", "viroli", LT3), B, 10, 2);
			fail("viroli already teaching OOP at that time!");
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
		
		try {
			c.add(createSubOOP(), null, 15, 2);
			fail("null can't be accepted");
		} catch (IllegalArgumentException e) { 
			System.out.println(S);
		}
			
		
		try {
			c.remove(null, 10, 2);
			fail("null can't be accepted");
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
		
		
	}
	
	private ISubject createSubOOP() {
		return new Subject("OOP", "viroli", LT1);
	}
	
	private ISubject createSubSO() {
		return new Subject("SO", "salomoni", LT1);
	}
}
