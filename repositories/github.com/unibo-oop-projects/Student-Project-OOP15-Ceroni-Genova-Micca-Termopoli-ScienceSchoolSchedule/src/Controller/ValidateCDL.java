package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.ErrorException;
import Model.Type;
import Model.WarningException;

/**
 * @author Massimiliano Micca
 *
 */
public class ValidateCDL implements ValidateCDLInterface {
    private ControllerWorkersInterface contWorkers = new ControllerWorkers();
    private Reservation cont;

    public void validate(Reservation res) throws WarningException, ErrorException {
        this.cont = res;

        checkProf();
        if (cont.getCourse().getType().equals(Type.FIRST_YEAR)) {
            // check first year course
            checkCDLFirstYear();
        } else if (Type.getSecondYears().contains(cont.getCourse().getType())) {
            // check second year course
            checkCDLSecondYear();

        } else if (Type.getThirdYears().contains(cont.getCourse().getType())) {
            // check third year course
            checkCDLThirdYear();

        } else if (cont.getCourse().getType().equals(Type.FOURTH_YEAR)) {
            // check fourth year course
            checkCDLFourthYear();

        } else if (Type.getFifthYears().contains(cont.getCourse().getType())) {
            // check fifth year course
            checkCDLFifthYear();
        }
    }

    private void checkProf() throws ErrorException {

        for (Reservation res : this.contWorkers.getByDay(cont.getDay())) {
            if (cont.getHour().getValue().equals(res.getHour().getValue())) {
                if (res.getPerson().toString().equals(cont.getPerson().toString())) {
                    throw new ErrorException("Il titolare del corso è gia impegnato in questa fascia oraria."); //
                }
            }
        }
    }

    /**
     * this method check if in the same time slot are a equal type of course
     * 
     * @return
     * @throws WarningException
     * @throws ErrorException
     */
    private void checkCDLFirstYear() throws WarningException, ErrorException {
        List<Type> listType = new ArrayList<>();

        for (Reservation res : this.contWorkers.getByDay(cont.getDay())) {
            if (cont.getHour().getValue().equals(res.getHour().getValue())) {
                if (res.getCourse().getName().equals(cont.getCourse().getName())
                        && !res.getRoom().getNameRoom().equals(cont.getRoom().getNameRoom())
                        && !res.getPerson().toString().equals(cont.getPerson().toString())) {
                    throw new WarningException(
                            "Un corso del primo anno è già inserito in questo periodo" + "\n" + "Inserirne un altro?"); //
                }
                listType.add(res.getCourse().getType());
            }
        }
        if (listType.contains(Type.FIRST_YEAR)) {
            throw new ErrorException(
                    "Nella stessa fascia oraria è già presente un corso del primo anno, quindi non può essere sovrapposto");
        }
    }

    /**
     * 
     * this method check if in the same time slot are a equal type of course
     * 
     * @return
     * @throws WarningException
     * @throws ErrorException
     */
    private void checkCDLSecondYear() throws WarningException, ErrorException {
        List<Type> listType = new ArrayList<>();

        for (Reservation res : this.contWorkers.getByDay(cont.getDay())) {
            if ((cont.getHour().getValue()).equals(res.getHour().getValue())) {
                if (res.getCourse().getName().equals(cont.getCourse().getName())
                        && !res.getRoom().getNameRoom().equals(cont.getRoom().getNameRoom())
                        && !res.getPerson().toString().equals(cont.getPerson().toString())) {
                    throw new WarningException("Un corso del secondo anno è già inserito in questo periodo" + "\n"
                            + "Inserirne un altro?"); //
                }
                listType.add(res.getCourse().getType());
            }
        }
        if (listType.contains(Type.SECOND_YEAR)) {
            throw new ErrorException(
                    "Nella stessa fascia oraria è già presente un corso del secondo anno, quindi non può essere sovrapposto");
        } else if (listType.contains(Type.SECOND_YEAR_ENG) && cont.getCourse().getType().equals(Type.SECOND_YEAR_SCI)) {

        } else if (listType.contains(Type.SECOND_YEAR_SCI) && cont.getCourse().getType().equals(Type.SECOND_YEAR_ENG)) {

        } else if (!listType.contains(cont.getCourse().getType())) {

        } else {
            throw new ErrorException(
                    "Nella stessa fascia oraria è già presente un corso del secondo anno, quindi non può essere sovrapposto");
        }
    }

    /**
     * 
     * this method check if in the same time slot are a equal type of course
     * 
     * @return
     * @throws WarningException
     * @throws ErrorException
     */
    private void checkCDLThirdYear() throws WarningException, ErrorException {

        List<Type> listType = new ArrayList<>();

        for (Reservation res : this.contWorkers.getByDay(cont.getDay())) {
            if ((cont.getHour().getValue()).equals(res.getHour().getValue())) {
                if (res.getCourse().getName().equals(cont.getCourse().getName())
                        && !res.getRoom().getNameRoom().equals(cont.getRoom().getNameRoom())
                        && !res.getPerson().toString().equals(cont.getPerson().toString())) {
                    throw new WarningException(
                            "Un corso del terzo anno è già inserito in questo periodo" + "\n" + "Inserirne un altro?"); //
                }
                listType.add(res.getCourse().getType());
            }
        }
        if (listType.contains(Type.THIRD_YEAR)) {
            throw new ErrorException(
                    "Nella stessa fascia oraria è già presente un corso del terzo anno, quindi non può essere sovrapposto");

        } else if (listType.contains(Type.THIRD_YEAR_ENG) && cont.getCourse().getType().equals(Type.THIRD_YEAR_SCI)) {

        } else if (listType.contains(Type.THIRD_YEAR_SCI) && cont.getCourse().getType().equals(Type.THIRD_YEAR_ENG)) {

        } else if (listType.contains(Type.THIRD_YEAR_OPT) && cont.getCourse().getType().equals(Type.THIRD_YEAR_OPT)) {

        } else if (!listType.contains(cont.getCourse().getType())) {

        } else {
            throw new ErrorException(
                    "Nella stessa fascia oraria è già presente un corso del terzo anno, quindi non può essere sovrapposto");
        }
    }

    /**
     * 
     * this method check if in the same time slot are a equal type of course
     * 
     * @return
     * @throws WarningException
     * @throws ErrorException
     */
    private void checkCDLFourthYear() throws WarningException, ErrorException {

        List<Type> listType = new ArrayList<>();
        for (Reservation res : this.contWorkers.getByDay(cont.getDay())) {
            if ((cont.getHour().getValue()).equals(res.getHour().getValue())) {
                if (res.getCourse().getName().equals(cont.getCourse().getName())
                        && !res.getRoom().getNameRoom().equals(cont.getRoom().getNameRoom())
                        && !res.getPerson().toString().equals(cont.getPerson().toString())) {
                    throw new WarningException(
                            "Un corso del quarto anno è già inserito in questo periodo" + "\n" + "Inserirne un altro?"); //
                }
                listType.add(res.getCourse().getType());

            }
        }
        if (listType.contains(Type.FOURTH_YEAR)) {
            throw new ErrorException(
                    "Nella stessa fascia oraria è già presente un corso del quarto anno, quindi non può essere sovrapposto");
        }
    }

    /**
     * this method check if in the same time slot are a equal type of course
     * 
     * @return
     * @throws WarningException
     * @throws ErrorException
     */
    private void checkCDLFifthYear() throws WarningException, ErrorException {

        List<Type> listType = new ArrayList<>();

        for (Reservation res : this.contWorkers.getByDay(cont.getDay())) {
            if ((cont.getHour().getValue()).equals(res.getHour().getValue())) {
                if (res.getCourse().getName().equals(cont.getCourse().getName())
                        && !res.getRoom().getNameRoom().equals(cont.getRoom().getNameRoom())
                        && !res.getPerson().toString().equals(cont.getPerson().toString())) {
                    throw new WarningException(
                            "Un corso del quinto anno è già inserito in questo periodo" + "\n" + "Inserirne un altro?");
                }
                listType.add(res.getCourse().getType());

            }
        }
        if (listType.contains(Type.FIFTH_YEAR) || listType.contains(Type.FIFTH_YEAR_OPT)) {
            throw new ErrorException(
                    "Nella stessa fascia oraria è già presente un corso del quinto anno, quindi non può essere sovrapposto");
        }

    }
}
