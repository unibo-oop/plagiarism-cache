package talisman.model.character.defaultCharacters;

enum Ghoul implements Character {
    ;

    public static CharacterType getType() {

        return CharacterType.GHOUL;
    }


    public static String[] getLore() {

        return new String[] {"The ghoul is a demon-like being or monstrous humanoid associated with graveyards and consuming human flesh"};
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
