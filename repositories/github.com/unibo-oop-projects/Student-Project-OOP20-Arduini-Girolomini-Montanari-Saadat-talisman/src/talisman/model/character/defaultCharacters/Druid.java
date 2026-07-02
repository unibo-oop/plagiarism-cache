package talisman.model.character.defaultCharacters;

enum Druid implements Character {
    ;

    public static CharacterType getType() {

        return CharacterType.DRUID;
    }


    public static String[] getLore() {

        return new String[] {"Druids serve as priests of nature.", "They use mistletoe as a holy symbol, and tend to be more involved with plants and animals than with humans."};
    }


    public static int getHealth() {

        return 4;
    }


    public static int getStrength() {

        return 2;
    }


    public static int getCraft() {

        return 4;
    }


    public static int getFate() {

        return 4;
    }
}
