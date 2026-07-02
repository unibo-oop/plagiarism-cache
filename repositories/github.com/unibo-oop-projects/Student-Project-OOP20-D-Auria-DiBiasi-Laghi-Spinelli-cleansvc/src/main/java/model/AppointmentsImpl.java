package model;

import model.users.Clients;

public class AppointmentsImpl implements Appointments {

    private String date;
    private String hour;
    private Clients client;
    private double time;
    private double earn;

    public AppointmentsImpl(final String date, final String hour, final Clients client, final double time,
            final double earn) {
        this.date = date;
        this.hour = hour;
        this.client = client;
        this.time = time;
        this.earn = earn;
    }

    /**
     * @return date
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * @return hour
     */
    @Override
    public String getHour() {
        return this.hour;
    }

    /**
     * @return the Client
     */
    @Override
    public Clients getClient() {
        return this.client;
    }

    /**
     * @return minutes
     */
    @Override
    public double getTime() {
        return this.time;
    }

    /**
     * @return earn
     */
    @Override
    public double getEarn() {
        return this.earn;
    }

}
