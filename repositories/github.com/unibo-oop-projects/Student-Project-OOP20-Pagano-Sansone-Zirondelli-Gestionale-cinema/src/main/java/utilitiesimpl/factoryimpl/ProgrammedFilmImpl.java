package utilitiesimpl.factoryimpl;

import java.time.LocalDate;
import java.time.LocalTime;

import utilities.TimeSlot;
import utilities.factory.ProgrammedFilm;
import utilitiesimpl.Hall;
/**
 * Describe programmed film with some information.
 *  */
public final class ProgrammedFilmImpl implements ProgrammedFilm {

    private final int id; // reference to idFilms
    private final Hall hall;
    private final double price;
    private final LocalDate date;
    private final TimeSlot timeSlot;

    ProgrammedFilmImpl(final int id, final  Hall hall, final  double price, final  LocalDate date, final TimeSlot timeSlot) {
        super();
        this.id = id;
        this.hall = hall;
        this.price = price;
        this.date = date;
        this.timeSlot = timeSlot;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public int getIdProgrammation() {
        return this.id;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public Hall getHall() {
        return this.hall;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public double getProgrammationPrice() {
        return this.price;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public LocalDate getDate() {
        return this.date;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public LocalTime getStartTime() {
        return this.timeSlot.getStartTime();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public LocalTime getEndTime() {
        return this.timeSlot.getEndTime();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((hall == null) ? 0 : hall.hashCode());
        result = prime * result + id;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((timeSlot == null) ? 0 : timeSlot.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProgrammedFilmImpl other = (ProgrammedFilmImpl) obj;
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        if (hall != other.hall) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (timeSlot == null) {
            if (other.timeSlot != null) {
                return false;
            }
        } else if (!timeSlot.equals(other.timeSlot)) {
            return false;
            }
        return true;
    }

    @Override
    public String toString() {
        return "ProgrammedFilmImpl [id=" + id + ", hall=" + hall + ", price=" + price + ", date=" + date + ", timeSlot="
                + timeSlot + "]";
    }
}
