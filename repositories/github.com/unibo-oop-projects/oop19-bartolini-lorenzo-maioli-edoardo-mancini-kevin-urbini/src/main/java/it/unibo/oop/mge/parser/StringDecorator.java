package it.unibo.oop.mge.parser;

public abstract class StringDecorator implements StringModifier {
    private String oldString;

    public StringDecorator(final Object fstring) {
        oldString = fstring.toString();
    }

    @Override
    public final String toString() {
        return stringParser(oldString);
    }

    protected abstract String stringParser(String str);

}
