package zombieversity.model.entities.utils;

public class LifeImpl implements Life {

    private int hp;

    public LifeImpl(final int maxHp) {
        this.hp = maxHp;
    }

    @Override
    public final int getHP() {
        return this.hp;
    }

    @Override
    public final void decreaseHP(final int damage) {
        this.hp -= damage;
    }

    @Override
    public final boolean isAlive() {
        return this.hp > 0;
    }

}
