package types;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Antonio
 *
 */

public class Type implements Serializable{

    private static final long serialVersionUID = 1L;

    public static final String GHOST = "Ghost";
    public static final String FIGHT = "Fight";
    public static final String DARK = "Dark";
    public static final String NORMAL = "Normal";
    public static final String BUG = "Bug";
    public static final String POISON = "Poison";
    public static final String FLYING = "Flying";
    public static final String GROUND = "Ground";
    public static final String ROCK = "Rock";
    public static final String STEEL = "Steel";
    public static final String FIRE = "Fire";
    public static final String WATER = "Water";
    public static final String GRASS = "Grass";
    public static final String ELECTRIC = "Electric";
    public static final String PSYCHIC = "Psychic";
    public static final String ICE = "Ice";
    public static final String DRAGON = "Dragon";
    public static final String FAIRY = "Fairy";

    private final String name;
    private final String color;
    private final String[] weaknesses;
    private final String[] resistances;
    private final String[] immunities;

    public Type(String name, String color, String[] weaknesses, String[] resistances, String[] immunities){
        this.name = name;
        this.color = color;
        this.weaknesses = Arrays.copyOf(weaknesses, weaknesses.length);
        this.resistances = Arrays.copyOf(resistances, resistances.length);
        this.immunities = Arrays.copyOf(immunities, immunities.length);
    }

    public String getTypeName(){
        return this.name;
    }

    public String getTypeColor(){
        return this.color;
    }

    public String[] getTypeWeaknesses(){
        return Arrays.copyOf(this.weaknesses, this.weaknesses.length);
    }

    public String[] getTypeResistances(){
        return Arrays.copyOf(this.resistances, this.resistances.length);
    }

    public String[] getTypeImmunities(){
        return Arrays.copyOf(this.immunities, this.immunities.length);
    }

    public boolean isResistantTo(Type type){
        return Type.containsType(this.resistances, type);
    }

    public boolean isImmuneTo(Type type){
        return Type.containsType(this.immunities, type);
    }

    public boolean isWeakTo(Type type){
        return Type.containsType(this.weaknesses, type);
    }
    
    public static Type getRandomType(){
        Type[] allTypes = new Type[]{new Bug(), new Dark(), new Dragon(), new Electric(), new Fairy(), new Fight(),
                                     new Fire(), new Flying(), new Ghost(), new Grass(), new Ground(), new Ice(),
                                     new Normal(), new Poison(), new Psychic(), new Rock(), new Steel(), new Water()};
        Random random = new Random();
        return allTypes[random.nextInt(allTypes.length)];
    }

    //funzione di utility - controlla se un tipo � compreso in un array
    public static boolean containsType(String[]array, Type type){
        if(array != null){
            for(String typeName : array){
                if(typeName != null && type != null){
                    if(type.name.equals(typeName)){
                        return true;
                    }
                }
            }
        }
        return false; 
    }

    @Override
    public boolean equals(Object type){
        if(type instanceof Type){
            if(this.name.equals(((Type)type).name)){
                return true;
            }
        }
        //if not a Type or non that type
        return false;
    }

}
