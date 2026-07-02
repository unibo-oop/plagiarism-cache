package utilitiesimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import utilities.Seat;
import utilities.Ticket;

public class TicketImpl implements Ticket {
        private final LocalDate data;
        private final LocalTime time;
        private final Set<Seat> setSeat;
        private final double price;
        private final int id;
        private final Hall hall;

        public TicketImpl(final LocalDate data, final LocalTime time, final Set<Seat> setSeat, final double price, final int id, final Hall hall) {
                this.data = data;
                this.hall = hall;
                this.setSeat = setSeat;
                this.price = price;
                this.id = id;
                this.time = time;
        }
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocalTime getTime() {
	    return time;
	}
	/**
         * {@inheritDoc}
         */
        @Override
	public LocalDate getDate() {
		return data;
	}
        /**
         * {@inheritDoc}
         */
        @Override
	public Set<Seat> getSetSeat() {
		return setSeat;
	}
        /**
         * {@inheritDoc}
         */
        @Override
	public double getPrice() {
		return price;
	}
        /**
         * {@inheritDoc}
         */
        @Override
	public int getId() {
		return id;
	}
        /**
         * {@inheritDoc}
         */
        @Override
	public Hall getHall() {
		return hall;
	}

    @Override
    public String toString() {
        return "TicketImpl [data=" + data + ", time=" + time + ", setSeat=" + setSeat + ", price=" + price + ", id="
                + id + ", hall=" + hall + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((hall == null) ? 0 : hall.hashCode());
        result = prime * result + id;
        result = prime * result + ((time == null) ? 0 : time.hashCode());
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
        TicketImpl other = (TicketImpl) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        if (hall != other.hall) {
            return false;
        }
        if (id != other.id) {
            return false;
        } 
        if (time == null) {
            if (other.time != null) {
                return false;
            }
        } else if (!time.equals(other.time)) {
            return false;
        }
        return true;
    }

}
