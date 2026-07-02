package it.unibo.oop.mge.model;

import it.unibo.oop.mge.function.AlgebricFunction;
import it.unibo.oop.mge.parser.BlankDeleter;
import it.unibo.oop.mge.parser.BracketsResolver;
import it.unibo.oop.mge.parser.CheckBrackets;
import it.unibo.oop.mge.parser.AssembleFunctionImpl;

public final class StringComposerImpl implements StringComposer {

    public AlgebricFunction parse(final String str) {
        return new AssembleFunctionImpl()
                .resolveFunction(new BracketsResolver(new CheckBrackets(new BlankDeleter(str))).toString());
    }
}
