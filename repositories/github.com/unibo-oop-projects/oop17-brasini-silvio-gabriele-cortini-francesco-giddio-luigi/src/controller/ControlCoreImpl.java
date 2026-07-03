package controller;

import interpreter.Interpreter;
import interpreter.InterpreterImpl;

/**
 * where save PathSeparator, select lenguage.
 * 
 * @author Francesco
 *
 */
public class ControlCoreImpl implements ControlCore {

    private PathSeparator ps = new PathSeparatorImpl();
    private String language;
    private String country;
    private Interpreter interpreter;

    @Override
    public PathSeparator getPathSeparator() {
        // TODO Auto-generated method stub
        return this.ps;
    }

    @Override
    public void setLanguage(final String selectedLangauge) {
        // TODO Auto-generated method stub

        this.language = selectedLangauge.split("_")[0];
        this.country = selectedLangauge.split("_")[1];
        System.out.println("language=" + this.language + " country=" + this.country);
        //this.interpreter= new InterpreterImpl(this.language, this.country);

    }
}