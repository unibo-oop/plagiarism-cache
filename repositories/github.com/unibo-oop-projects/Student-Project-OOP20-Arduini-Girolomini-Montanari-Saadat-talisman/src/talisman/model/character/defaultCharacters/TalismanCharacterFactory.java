package talisman.model.character.defaultCharacters;

import talisman.model.character.CharacterModelImpl;

public class TalismanCharacterFactory {

    public static CharacterModelImpl createAssassinCharacter(){

        return new CharacterModelImpl(Assassin.getHealth(), Assassin.getStrength(), Assassin.getCraft(), 0, Assassin.getFate(), Assassin.getType());
    }

    public static CharacterModelImpl createDruidCharacter(){

        return new CharacterModelImpl(Druid.getHealth(), Druid.getStrength(), Druid.getCraft(), 0, Druid.getFate(), Druid.getType());
    }

    public static CharacterModelImpl createDwarfCharacter(){

        return new CharacterModelImpl(Dwarf.getHealth(), Dwarf.getStrength(), Dwarf.getCraft(), 0, Dwarf.getFate(), Dwarf.getType());
    }

    public static CharacterModelImpl createElfCharacter(){

        return new CharacterModelImpl(Elf.getHealth(), Elf.getStrength(), Elf.getCraft(), 0, Elf.getFate(), Elf.getType());
    }

    public static CharacterModelImpl createGhoulCharacter(){

        return new CharacterModelImpl(Ghoul.getHealth(), Ghoul.getStrength(), Ghoul.getCraft(), 0, Ghoul.getFate(), Ghoul.getType());
    }
}





