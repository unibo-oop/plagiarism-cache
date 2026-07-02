package model.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MyFileImpl implements MyFile {

    private final File file;
    private Set<String> list = new TreeSet<>();

    public MyFileImpl(final String fileName)  {
        this.file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("ERRORE NON E' POSSIBILE CREARE IL FILE");
            }
        }
    }

    @Override
    public final Set<String> fileReader() {
       try {
           FileReader fr = new FileReader(file);
           BufferedReader reader = new BufferedReader(fr);
           String line = reader.readLine();
           while (line != null) {
               if (line.isEmpty()) {
                   line = reader.readLine();
                   continue;
               }
               list.add(line);
               line = reader.readLine();
           }
           reader.close();
       } catch (IOException e) {
           System.out.println("Eccezione generata nella lettura del file");
       }
       return list;
    }

    @Override
    public final boolean fileWriter(final String string) {
        try {
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fr);
            writer.newLine();
            writer.write(string.toLowerCase());
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Eccezione generata nella scrittura del file");
            return false;
        }
    }

    @Override
    public final String fileSearch(final String string) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                if (line.contains(string.toLowerCase())) {
                    reader.close();
                    return line;
                }
            line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Eccezione generata nella ricerca del file");
        }
        return null;

    }

    @Override
    public final void emptyfile() {
        PrintWriter writer;
        try {
            writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Eccezione generata nel svuotare il file");
            e.printStackTrace();
        }
    }

    @Override
    public final boolean deleteline(final String string) {
        List<String> listw = new LinkedList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                if (!line.contains(string.toLowerCase()) && !line.equals("")) {
                   listw.add(line);
                }
            line = reader.readLine();
            }
            reader.close();
            this.emptyfile();
            for (var i: listw) {
                this.fileWriter(i);
            }
            return true;
        } catch (IOException e) {
            System.out.println("Eccezione generata nella ricerca del file");
            return false;
        }
    }

    @Override
    public final File getFile() {
        return this.file;
    }

}
