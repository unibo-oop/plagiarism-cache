package test;

import static model.SubjectType.LT1;
import static model.SubjectType.LT2;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import model.DailyTime;
import model.Subject;
import model.interfaces.IDailyTime;
import model.interfaces.ISubject;

import org.junit.Test;

/**
 * Class for testing the correct working of the class {@link DailyTime}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class TestDailyTime {

	private static final String S = "Success!";
	
	/**
	 * Necessary test to control that the class DailyTime follows the correct working 
	 * specified in the methods of the interface {@link IDailyTime}.
	 */
	@Test
	public void test(){
		final IDailyTime d = new DailyTime();
		try{
			d.add(new Subject("OOP", "viroli", LT2), 9, 9);
			d.remove(9, 9);
			d.add(new Subject("Fisica", "Campari", LT2), 15, 2);
			d.add(new Subject("OOP", "viroli", LT2), 17, 1);
			assertTrue(d.copy().equals(d));	
		} catch (IllegalArgumentException e) {
			fail("these sequence of instruction is right");
		}
		
		IDailyTime dt;
		try {
			dt = new DailyTime(null);
			fail("can't accept null");
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
		
		dt = new DailyTime(d);
		if (dt.equals(d)) {
			System.out.println(S);
		}
		
		try {
			d.add(new Subject(null, "salomoni", LT2), 9, 1);
			fail("Subject name can't be null"); 
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
		
		try {
			d.add(new Subject("SO", null, LT2), 9, 1);
			fail("Teacher name can't be null"); 
		} catch (IllegalArgumentException e) { 
			System.out.println(S);
		}
		
		try {
			d.add(new Subject("SO", "salomoni", null), 9, 1);
			fail("Subject Type can't be null"); 
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
		
		try {
			d.add(createSubSO(), 15, 1);
			fail("these hours are busy"); 
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
	
		try {
			d.add(createSubSO(), 25, 1);
			fail("invalid hour");
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
		
		try {
			d.add(createSubSO(), 12, 10);
			fail("out of range");
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
		
		try {
			d.add(createSubSO(), 12, 0);
			fail("invalid number of hours");
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
		
		try {
			d.remove(24, 1);
			fail("invalid hour");
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
		
		try {
			d.remove(12, 8);
			fail("out of range");
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
		
		try {
			d.getSubject(-1);
			fail("invalid hour");
		} catch (IllegalArgumentException e) {
			System.out.println(S);
		}
	}
	
	private ISubject createSubSO() {
		return new Subject("SO", "salomoni", LT1);
	}

}
