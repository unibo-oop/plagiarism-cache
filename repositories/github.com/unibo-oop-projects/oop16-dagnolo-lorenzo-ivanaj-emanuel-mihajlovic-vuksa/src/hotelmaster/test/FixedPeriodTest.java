package hotelmaster.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import hotelmaster.utility.time.FixedPeriod;

/**
 * Tests for the FixedPeriod class.
 */
public class FixedPeriodTest {
    // CHECKSTYLE:OFF: checkstyle:magicnumber
    /**
     * Check if the duration is correct.
     */
    @org.junit.Test
    public void duration() {
        FixedPeriod varying = FixedPeriod.of(LocalDate.ofYearDay(2, 1), LocalDate.ofYearDay(3, 1));
        assertEquals(varying.getTotalDays(), 365);
        varying = FixedPeriod.of(LocalDate.of(2, Month.JANUARY, 1), LocalDate.of(2, Month.FEBRUARY, 1));
        assertEquals(varying.getTotalDays(), Month.JANUARY.length(false));
    }

    /**
     * Check if the overlaps function works correctly.
     */
    @org.junit.Test
    public void overlaps() {
        final FixedPeriod base = FixedPeriod.of(LocalDate.ofYearDay(2, 1), LocalDate.ofYearDay(5, 1));
        FixedPeriod varying;
        varying = FixedPeriod.of(LocalDate.ofYearDay(0, 1), LocalDate.ofYearDay(1, 1));
        assertFalse(base.overlaps(varying));
        assertFalse(varying.overlaps(base));
        varying = FixedPeriod.of(LocalDate.ofYearDay(1, 1), LocalDate.ofYearDay(2, 1));
        assertTrue(base.overlaps(varying));
        assertTrue(varying.overlaps(base));
        varying = FixedPeriod.of(LocalDate.ofYearDay(2, 1), LocalDate.ofYearDay(3, 1));
        assertTrue(base.overlaps(varying));
        assertTrue(varying.overlaps(base));
        varying = FixedPeriod.of(LocalDate.ofYearDay(3, 1), LocalDate.ofYearDay(4, 1));
        assertTrue(base.overlaps(varying));
        assertTrue(varying.overlaps(base));
        varying = FixedPeriod.of(LocalDate.ofYearDay(4, 1), LocalDate.ofYearDay(5, 1));
        assertTrue(base.overlaps(varying));
        assertTrue(varying.overlaps(base));
        varying = FixedPeriod.of(LocalDate.ofYearDay(5, 1), LocalDate.ofYearDay(6, 1));
        assertTrue(base.overlaps(varying));
        assertTrue(varying.overlaps(base));
        varying = FixedPeriod.of(LocalDate.ofYearDay(6, 1), LocalDate.ofYearDay(7, 1));
        assertFalse(base.overlaps(varying));
        assertFalse(varying.overlaps(base));
        varying = FixedPeriod.of(LocalDate.ofYearDay(1, 1), LocalDate.ofYearDay(3, 1));
        assertTrue(base.overlaps(varying));
        assertTrue(varying.overlaps(base));
        varying = FixedPeriod.of(LocalDate.ofYearDay(3, 1), LocalDate.ofYearDay(6, 1));
        assertTrue(base.overlaps(varying));
        assertTrue(varying.overlaps(base));
    }

    @org.junit.Test
    public void comparison() {
        final FixedPeriod base = FixedPeriod.of(LocalDate.ofYearDay(2, 1), LocalDate.ofYearDay(5, 1));
        FixedPeriod varying;
        varying = FixedPeriod.of(LocalDate.ofYearDay(0, 1), LocalDate.ofYearDay(1, 1));
        assertTrue("Base date should be greater than varying date", base.compareTo(varying) > 0);
        assertTrue("Varying date should be lesser than base date", varying.compareTo(base) < 0);
        assertFalse("Base and varying should not be the same", base.equals(varying));
        varying = FixedPeriod.of(LocalDate.ofYearDay(2, 1), LocalDate.ofYearDay(4, 1));
        assertTrue("Base date should be greater than varying date", base.compareTo(varying) > 0);
        assertTrue("Varying date should be lesser than base date", varying.compareTo(base) < 0);
        assertFalse("Base and varying should not be the same", base.equals(varying));
        varying = FixedPeriod.of(LocalDate.ofYearDay(2, 1), LocalDate.ofYearDay(5, 1));
        assertSame("Base and varying should be the same", base.compareTo(varying), 0);
        assertSame("Base and varying should be the same", varying.compareTo(base), 0);
        assertEquals("Base and varying should be the same", base, varying);
    }

    @org.junit.Test
    public void intersections() {
        final FixedPeriod base = FixedPeriod.of(LocalDate.ofYearDay(2, 1), LocalDate.ofYearDay(5, 1));
        FixedPeriod varying;
        varying = FixedPeriod.of(LocalDate.ofYearDay(0, 1), LocalDate.ofYearDay(1, 1));
        assertFalse("Base and varying should not intersect", base.getIntersectionWith(varying).isPresent());
        varying = FixedPeriod.of(LocalDate.ofYearDay(1, 1), LocalDate.ofYearDay(4, 1));
        Optional<FixedPeriod> intersection = varying.getIntersectionWith(base);
        assertTrue("Base and varying should intersect", intersection.isPresent());
        if (intersection.isPresent()) {
            final FixedPeriod inters = intersection.get();
            assertTrue("Intersection should have base's beginning date",
                    inters.getBeginning().equals(base.getBeginning()));
            assertTrue("Intersection should have varying's end date", inters.getEnd().equals(varying.getEnd()));
        }
        varying = FixedPeriod.of(LocalDate.ofYearDay(2, 1), LocalDate.ofYearDay(3, 1));
        intersection = varying.getIntersectionWith(base);
        assertTrue("Base and varying should intersect", intersection.isPresent());
        if (intersection.isPresent()) {
            final FixedPeriod inters = intersection.get();
            assertTrue("Beginning dates should all be equals", inters.getBeginning().equals(base.getBeginning()));
            assertTrue("Beginning dates should all be equals", inters.getBeginning().equals(varying.getBeginning()));
            assertTrue("Intersection should have varying's end date", inters.getEnd().equals(varying.getEnd()));
        }
    }
    // CHECKSTYLE:ON: checkstyle:magicnumber
}
