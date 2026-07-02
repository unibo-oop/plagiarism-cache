package talisman.model.quests;

/**
 * Deliver an object to complete this quest
 *
 * @author Enrico Maria Montanari
 */
public class DeliverObject extends TalismanQuest{
    private static final long serialVersionUID = 522224177252193499L;
    
    private final QuestObjectType objectType;

    /**
     * The Quest requires an object to deliver, it can be chosen or let the game decide random
     *
     * @param objectType the object to deliver
     */
    public DeliverObject(QuestObjectType objectType){

        this.objectType = objectType;
    }

    public DeliverObject() {

        this.objectType = QuestObjectType.getRandom();
    }

    /**
     * Get the object to deliver
     *
     * @return an instance of the object
     */
    public QuestObjectType getObjectType(){

        return objectType;
    }

    /**
     * Get a description of the objective for the current quest
     *
     * @return the description of the object to deliver
     */
    @Override
    public String toString(){

        return objectType.toString();
    }

}
