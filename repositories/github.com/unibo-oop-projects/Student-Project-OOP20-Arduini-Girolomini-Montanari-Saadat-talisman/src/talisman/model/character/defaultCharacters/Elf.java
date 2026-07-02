package talisman.model.character.defaultCharacters;

enum Elf implements Character {
    ;

    public static CharacterType getType() {

        return CharacterType.ELF;
    }


    public static String[] getLore() {

        return new String[] {"Elves seem generally to have been thought of as beings with magical powers and supernatural beauty", "ambivalent towards everyday people and capable of either helping or hindering them."};
    }


    public static int getHealth() {

        return 4;
    }


    public static int getStrength() {

        return 3;
    }


    public static int getCraft() {

        return 4;
    }


    public static int getFate() {

        return 3;
    }
}
