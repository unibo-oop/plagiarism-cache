package it.unibo.oop.mge.libraries;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class EnumUtilityImpl<X extends GenericEnum> implements EnumUtility<X> {
    private final Class<X> type;

    public EnumUtilityImpl(final Class<X> type) {
        this.type = type;
    }

    private Optional<X> getOptionalElement(final String syntax) {
        return Arrays.asList(this.type.getEnumConstants()).stream().filter(i -> i.getSyntax().equals(syntax))
                .findFirst();
    }

    @Override
    public List<String> getSyntaxList() {
        return Arrays.asList(this.type.getEnumConstants()).stream().<String>map(i -> i.getSyntax())
                .collect(Collectors.toList());
    }

    @Override
    public X getElement(final String syntax) {
        if (enumContains(syntax)) {
            return getOptionalElement(syntax).get();
        } else {
            throw new IllegalArgumentException("Error using EnumUtilityImpl: the syntax doesn't exists");
        }
    }

    @Override
    public Boolean enumContains(final String syntax) {
        return getOptionalElement(syntax).isPresent();
    }
}
