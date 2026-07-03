package com.geoquiz.model.xmlunmarshal;

/**
 * This enum contains various info about the XML files.
 *
 */
public enum XMLFiles {
    /**
     * Info about CountriesInfo.xml file.
     */
    COUNTRIES_INFO(CountryInfo.class, "/data/CountriesInfo.xml"),
    /**
     * Info about MonumentsDifficult.xml file.
     */
    MONUMENTS_DIFFICULT(MonumentInfo.class, "/data/MonumentsDifficult.xml"),
    /**
     * Info about MonumentsEasy file.
     */
    MONUMENTS_EASY(MonumentInfo.class, "/data/MonumentsEasy.xml"),
    /**
     * Info about MonumentsMedium file.
     */
    MONUMENTS_MEDIUM(MonumentInfo.class, "/data/MonumentsMedium.xml"),
    /**
     * Info about TypicalDishes.xml file.
     */
    TYPICAL_DISHES(TypicalDish.class, "/data/TypicalDishes.xml");

    private final Class<?> clazz;
    private final String pathName;

    <T> XMLFiles(final Class<T> clazz, final String pathName) {
        this.clazz = clazz;
        this.pathName = pathName;
    }

    @SuppressWarnings("unchecked")
    <T> Class<T> getClazz() {
        return (Class<T>) this.clazz;
    }

    String getPathName() {
        return this.pathName;
    }
}
