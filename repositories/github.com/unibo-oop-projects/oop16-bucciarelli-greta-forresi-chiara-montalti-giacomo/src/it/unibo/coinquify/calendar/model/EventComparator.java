package it.unibo.coinquify.calendar.model;

import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    @Override
    public int compare(final Event o1, final Event o2) {
        // return 1 if rhs should be before lhs
        // return -1 if lhs should be before rhs
        // return 0 otherwise
        if (o1.getStartDate().before(o2.getStartDate())) {
            return -1;
        }
        if (o1.getStartDate().after(o2.getStartDate())) {
            return 1;
        }
        if (o1.getStartTime().before(o2.getStartTime())) {
            return -1;
        }
        if (o1.getStartTime().after(o2.getStartTime())) {
            return 1;
        }
        if (o1.getEndDate().before(o2.getEndDate())) {
            return -1;
        }
        if (o1.getEndDate().after(o2.getEndDate())) {
            return 1;
        }
        if (o1.getEndTime().before(o2.getEndTime())) {
            return -1;
        }
        if (o1.getEndTime().after(o2.getEndTime())) {
            return 1;
        }
        return 0;
    }

}
