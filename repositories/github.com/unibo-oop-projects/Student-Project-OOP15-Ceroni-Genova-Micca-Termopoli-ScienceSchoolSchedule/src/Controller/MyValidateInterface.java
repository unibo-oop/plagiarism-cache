package Controller;

import Model.ErrorException;
import Model.WarningException;

public interface MyValidateInterface {

    void validation(Reservation res) throws ErrorException, WarningException;

}