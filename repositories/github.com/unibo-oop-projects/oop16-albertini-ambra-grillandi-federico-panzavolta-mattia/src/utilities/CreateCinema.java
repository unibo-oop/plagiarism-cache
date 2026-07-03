package utilities;

import model.Model;
import model.ModelImpl;

/**
 * It is the static class that creates the cinema and its owner and balance.
 *
 */
public final class CreateCinema {

    /**
     * It is the file in which room informations will be saved.
     */
    public static final String FILE_R = "src\\utilities\\Room.txt";
    /**
     * It is the file in which passwords will be saved.
     */
    public static final String FILE_O = "src\\utilities\\Owner.txt";
    /**
     * It is the file in which the balance will be saved.
     */
    public static final String FILE_B = "src\\utilities\\Balance.txt";
    /**
     * It is the file in which films will be saved.
     */
    public static final String FILE_F = "src\\utilities\\Film.txt";

    /**
     * It is the number of rooms that the cinema will contain.
     */
    private static final int N_ROOMS = 4;
    private static final int SEATS = 200;
    private static final double TICKET_PRICE = 6.0;
    private static Model modelCinema = new ModelImpl(N_ROOMS, TICKET_PRICE);

    private CreateCinema() {
    }

    /**
     * @return the model of the cinema.
     */
    public static Model getCinema() {
        return modelCinema;
    }

    /**
     * It sets rooms in the cinema.
     */
    public static void setCinema() {
        final String roomName = "SALA";
        for (int i = 0; i < N_ROOMS; i++) {
            modelCinema.getRoomList().add(modelCinema.createRoom(roomName + i, SEATS));
        }
    }
}
