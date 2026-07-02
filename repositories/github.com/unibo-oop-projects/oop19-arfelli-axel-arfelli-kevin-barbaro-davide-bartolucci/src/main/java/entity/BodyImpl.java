package entity;

import java.util.Optional;

import virtualworld.Movment;
import virtualworld.VirtualMap;

public class BodyImpl implements VirtualBody {

    private CollisionBox<Integer> hitbox;
    private VirtualMap map;
    private Movment movmentcontrol = null;


    /**
     * @param hitbox
     */
    public BodyImpl(final CollisionBox<Integer> hitbox) {
        this.hitbox = hitbox;
    }

//    private Movment movmentcontrol = null;

    @Override
    public final boolean move(final int x, final int y) {
        if (this.movmentcontrol == null) {
             return false;
        }
        if (this.movmentcontrol.moveTo(x, y)) {
            this.hitbox = this.movmentcontrol.getCollisionBox();
            return true;
        }
        return false;
    }

    @Override
    public final CollisionBox<Integer> getCollisionBox() {
        return hitbox;
    }

    public final void setMotion(final Movment movmentcontrol) {
        this.movmentcontrol = movmentcontrol;
    }

    @Override
    public final boolean checkCollision(final CollisionBox<Integer> box) {
        return hitbox.checkCollision(box);
    }

    @Override
    public final Optional<VirtualMap> getMap() {
        if (movmentcontrol == null) {
            return Optional.empty();
        }

        return Optional.of(movmentcontrol.getMap());
    }

	public void setMap(VirtualMap map) {
		this.map = map;
	}
}
