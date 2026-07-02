package talisman.model.character.defaultCharacters;

enum Assassin implements Character {
    ;

    public static CharacterType getType() {

        return CharacterType.ASSASSIN;
    }


    public static String[] getLore() {

        return new String[] {"The assassin's specialty is the use of stealth to ambush and kill targets"};
    }


    public static int getHealth() {

        return 4;
    }


    public static int getStrength() {

        return 3;
    }


    public static int getCraft() {

        return 3;
    }


    public static int getFate() {

        return 3;
    }
}
