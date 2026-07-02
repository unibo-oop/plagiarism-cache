package room;
import org.junit.Test;

import controller.Room.ControllerRoom;
import controller.Room.ControllerRoomImpl;


public class ControllerRoomTest {

    @Test
    public void tryRoomController() {
        ControllerRoom roomController = new ControllerRoomImpl();
        System.out.println(roomController.getRoom(4).toString());
        System.out.println(roomController.getAll().toString());
    }
}
