package model;

import model.users.Clients;

public interface Appointments {

    String getDate();
    String getHour();
    Clients getClient();
    double getTime();
    double getEarn();

}
