package it.unibo.oop.mge.parser;

public class BlankDeleter extends StringDecorator {

    public BlankDeleter(final Object fstring) {
        super(fstring);
    }

    protected final String stringParser(final String str) {
        return str.chars().filter(i -> i != ' ')
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

}
