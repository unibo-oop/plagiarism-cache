package it.unibo.coinquify.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import it.unibo.coinquify.calendar.model.Event;

/**
 * Read and write events.
 */
public final class EventFile {
    private EventFile() {
    }

    /**
     * Write the list of events in the files.
     * 
     * @param events
     *            list
     * @throws IOException
     *             if there're problems
     */
    public static void writeEvents(final List<Event> events) throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(FilePathsConstants.EVENTS)));

        for (final Event e : events) {
            oos.writeObject(e);
        }
        oos.writeObject(null);
        oos.close();
    }

    /**
     * 
     * @return list of all events in the files
     */
    public static List<Event> readEvents() {
        final List<Event> events = new ArrayList<>();

        try {
            final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(FilePathsConstants.EVENTS)));
            for (Object obj = ois.readObject(); obj != null; obj = ois.readObject()) {
                events.add((Event) obj);
            }
            ois.close();
            return events;
        } catch (Exception e) {
            return events;
        }
    }
}
