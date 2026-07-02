package controller.gamecontroller;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import utilities.Utilities;

class FileHandler extends Handler {

    private final List<String> strings = new LinkedList<>();

    @Override
    public void publish(final LogRecord record) {
        Utilities.requireNonNull(record);
        strings.add(record.getParameters()[0] + ": " + record.getMessage());
    }

    @Override
    public void flush() {
        strings.clear();
    }

    @Override
    public void close() throws SecurityException {
        try (PrintStream printer = new PrintStream(new File("Log.txt"))) {
            strings.stream().forEach(s -> printer.println(s));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        flush();
    }

}
