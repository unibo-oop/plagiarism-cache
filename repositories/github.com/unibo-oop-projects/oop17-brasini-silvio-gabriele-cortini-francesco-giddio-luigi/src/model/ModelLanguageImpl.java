package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.PathSeparator;

public class ModelLanguageImpl implements ModelLanguage {

    private List<String> listFile;
    private final List<String> listLanguage;
    private final List<String> list_Language;

    public ModelLanguageImpl(final PathSeparator ps) {
        // TODO Auto-generated constructor stub
        String dir = ps.getDir() + ps.getSeparator() + "src" + ps.getSeparator() + "LenguageSelector.txt";
        try {
            System.out.println(dir);
            this.listFile = Collections.unmodifiableList(Files.lines(Paths.get(dir)).collect(Collectors.toList()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("file LenguageSelector.txt non trovato" + this.getClass());
        }
        System.out.println(this.listFile);
        // "English en_US", su una sola riga considerata come una stringa, bisogna
        // separarla

        // elimina qualsiasi carattere tranne "a-zA-Z" e "_"
        this.listFile = splittedBy(this.listFile, "[^a-zA-Z_]");
        // si viene a creare una lista con null
        this.listFile = Collections
                .unmodifiableList(this.listFile.stream().filter(s -> !s.equals("")).collect(Collectors.toList()));

        this.list_Language = Collections
                .unmodifiableList(this.listFile.stream().filter(s -> s.contains("_")).collect(Collectors.toList()));
        this.listLanguage = Collections
                .unmodifiableList(this.listFile.stream().filter(s -> !s.contains("_")).collect(Collectors.toList()));
        System.out.println(this.listFile);
        System.out.println(this.listLanguage);
    }

    private List<String> splittedBy(final List<String> l, final String regex) {
        return Collections
                .unmodifiableList(l.stream().flatMap(s -> Stream.of(s.split(regex))).collect(Collectors.toList()));
    }

    @Override
    public List<String> getListLanguage() {
        // TODO Auto-generated method stub
        return Collections.unmodifiableList(this.listLanguage);
    }

    @Override
    public String getSelectedLangauge(final int selectedIndex) {
        // TODO Auto-generated method stub
        return this.list_Language.get(selectedIndex);
    }

}
