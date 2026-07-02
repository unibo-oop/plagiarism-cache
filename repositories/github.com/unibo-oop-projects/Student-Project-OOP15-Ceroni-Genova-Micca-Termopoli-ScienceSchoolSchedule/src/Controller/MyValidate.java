package Controller;

import Model.ErrorException;
import Model.WarningException;

/**
 * @author Massimiliano Micca
 *
 */
public class MyValidate implements MyValidateInterface {
    private ValidateErrorInterface validateError = new ValidateError();
    private ValidateWarningInterface validateWarning = new ValidateWarning();
    private ValidateCDLInterface validateCDL = new ValidateCDL();

    public void validation(Reservation res) throws ErrorException, WarningException {

        validateError.validateErrore(res);
        validateWarning.validateWARNING(res);
        validateCDL.validate(res);
    }

}
