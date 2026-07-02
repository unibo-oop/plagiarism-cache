package model.entity.cell.standard.age;

public interface AgeManipulation extends Age {

    /**
     * increment with a +1.
     */
    void increment();
    /**
     * 
     * @return <code>true</code> if is dead for old age.
     */
    boolean isDead();

}
