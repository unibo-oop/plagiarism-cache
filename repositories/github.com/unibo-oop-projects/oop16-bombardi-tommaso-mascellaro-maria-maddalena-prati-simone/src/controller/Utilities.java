package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import controller.interfaces.Check;
import controller.interfaces.Save;

/**
 * Class with utilities (check and save) implementation.
 */
public class Utilities implements Check, Save {

    private static final int MAX_OBJECTS = 10;
    private static final int MAX_DAYS = 20;
    private static final int MAX_STUDENTS = 4;

    private static final int MIN_NAME_LETTERS = 3;
    private static final int MIN_USER_LETTERS = 5;
    private static final int MAX_USER_LETTERS = 8;

    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int CARD_DATE_LENGTH = 7;
    private static final int CARD_CVC_LENGTH = 3;


    @Override
    public Optional<Integer> checkObjects(final String num) {
        return this.checkNumber(num, MAX_OBJECTS);
    }
    @Override
    public Optional<Integer> checkDays(final String num) {
        return this.checkNumber(num, MAX_DAYS);
    }
    @Override
    public Optional<Integer> checkStudents(final String num) {
        return this.checkNumber(num, MAX_STUDENTS);
    }
    @Override
    public Optional<Integer> checkIndex(final String num) {
        return this.checkNumber(num);
    }
    @Override
    public Optional<String> checkName(final String str) {
        int charCount = 0;
        boolean check = true;
        for (int i = 0; i < str.length(); i++) {
            final char c = str.charAt(i);
            if (Character.isLetter(c)) {
                charCount++;
            } else {
                if (!Character.isSpaceChar(c)) {
                    check = false;
                }
            }
        }
        if (charCount < MIN_NAME_LETTERS || !check) {
            return Optional.empty();
        } else {
            return Optional.of(str);
        }
    }
    @Override
    public Optional<String> checkUsers(final String str) {
        return this.checkString(str, MIN_USER_LETTERS, MAX_USER_LETTERS);
    }
    @Override
    public Optional<String> checkCardNumber(final String str) {
        return this.checkLength(str, CARD_NUMBER_LENGTH, CARD_NUMBER_LENGTH);
    }
    @Override
    public Optional<String> checkCardDate(final String str) {
        return this.checkLength(str, CARD_DATE_LENGTH, CARD_DATE_LENGTH);
    }
    @Override
    public Optional<Integer> checkCardCvc(final String num) {
        if (this.checkLength(num, CARD_CVC_LENGTH, CARD_CVC_LENGTH).isPresent()) {
            return this.checkNumber(num);
        } else {
            return Optional.empty();
        }
    }
    private Optional<Integer> checkNumber(final String num, final int max) {
        if (this.checkNumber(num).isPresent() && this.checkNumber(num).get() > max) {
                return Optional.empty();
        } else {
            return this.checkNumber(num);
        }
    }
    private Optional<Integer> checkNumber(final String num) {
        Optional<Integer> op = Optional.empty();
        if (this.checkInteger(num)) {
             final Integer val = Integer.valueOf(num);
            if (val >= 1) {
                op = Optional.of(val);
            }
        }
        return op;
    }
    private boolean checkInteger(final String num) {
        try {
            Integer.parseInt(num);
        } catch (NumberFormatException n) {
            return false;
        }
        return true;
    }
    private Optional<String> checkString(final String str, final int min, final int max) {
        if (str.isEmpty() || this.checkInteger(str)) {
            return Optional.empty();
        } else {
            return this.checkLength(str, min, max);
        }
    }
    private Optional<String> checkLength(final String str, final int min, final int max) {
        if (str.length() >= min && str.length() <= max) {
            return Optional.of(str);
        } else {
            return Optional.empty();
        }
    }


    @Override
    public File createFile(final File f) {
        if (f.exists()) {
            if (!f.isFile()) {
                try {
                    if (!f.delete()) {
                        throw new IllegalStateException();
                    }
                    if (!f.createNewFile()) {
                        throw new IllegalStateException();
                    }
                } catch (SecurityException se) {
                    throw new IllegalStateException("Security error", se);
                } catch (IOException e) {
                    throw new IllegalStateException("Input output error", e);
                }
            }
        } else {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new IllegalStateException("Input output error", e);
            }
        }
        return f;
    }
    @Override
    public File createDir(final File d) {
        if (d.exists()) {
            if (!d.isDirectory()) {
                try {
                    if (!d.delete()) {
                        throw new IllegalStateException();
                    }
                    if (!d.mkdir()) {
                        throw new IllegalStateException();
                    }
                } catch (SecurityException se) {
                    throw new IllegalStateException("Security error", se);
                }
            }
        } else {
            try {
                d.mkdir();
            } catch (SecurityException se) {
                throw new IllegalStateException("Security error", se);
            } 
        }
        return d;
    }
    @Override
    public Optional<File> writeFile(final Object obj, final File f) {
        if (!f.exists()) {
            try {
                this.createFile(f);
            } catch (IllegalStateException e) {
                return Optional.empty();
            }
        }
        Optional<File> file;
        try {
            final ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(f));
            fileWriter.writeObject(obj);
            file = Optional.of(f);
            fileWriter.close();
        } catch (IOException e) {
            file = Optional.empty();
        }
        return file;
    }
    @Override
    public Optional<Object> readFile(final File f) {
        Optional<Object> obj;
        try {
            final ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(f));
            obj = Optional.of(fileReader.readObject());
            fileReader.close();
        } catch (IOException e1) {
            obj = Optional.empty();
        } catch (ClassNotFoundException e) {
            obj = Optional.empty();
        }
        return obj;
    }

}
