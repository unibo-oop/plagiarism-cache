package talisman.model.quests;

import java.util.Random;

public enum QuestObjectType {

    FOLLOWER,
    TWO_COINS,
    THREE_COINS,
    MAGIC_OBJECT;

    public static QuestObjectType getRandom(){

        switch (new Random().nextInt(4)){

            case 0: return FOLLOWER;
            case 1: return TWO_COINS;
            case 2: return THREE_COINS;
            case 3: return MAGIC_OBJECT;
            default: return null;
        }
    }

    public String toString(){

        switch (this){

            case FOLLOWER: return "Deliver (discard) 1 Follower";
            case TWO_COINS: return "Deliver (discard) 2 Gold Coins";
            case THREE_COINS: return "Deliver (discard) 3 Gold Coins";
            case MAGIC_OBJECT: return "Deliver (discard) 1 Magic Object";
            default: return null;
        }
    }
}
