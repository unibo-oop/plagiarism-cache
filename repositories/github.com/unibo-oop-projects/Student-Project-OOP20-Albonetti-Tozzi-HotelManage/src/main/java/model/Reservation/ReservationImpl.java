package model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.client.Client;
import model.room.Room;

public class ReservationImpl implements Reservation {

    private Client client;
    private Room room;
    private Date dateIn;
    private Date dateOut;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public ReservationImpl(final Client client, final Date dateIn, final Date dateOut, final Room room) {
        this.client = client;
        this.room = room;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    @Override
    public final Client getClient() {
        return this.client;
    }

    @Override
    public final Room getRoom() {
        return this.room;
    }

    @Override
    public final Date getDateIn() {
       return this.dateIn;
    }

    @Override
    public final Date getDateOut() {
       return this.dateOut;
    }

    @Override
    public final void setRoom(final Room room) {
        this.room = room;
    }

    @Override
    public final void setDateIn(final Date dateIn) {
        this.dateIn = dateIn;
    }

    @Override
    public final void setDateOut(final Date dateOut) {
        this.dateOut = dateOut;
    }

    @Override
    public final void setClient(final Client client) {
       this.client = client;
    }

    @Override
    public final String toString() {
        return client.getId() + "." + this.dateFormatter.format(dateIn) + "." + this.dateFormatter.format(dateOut) + "." + this.room.getNumber();
    }
}
