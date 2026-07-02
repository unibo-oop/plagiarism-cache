package virtualworld;

import java.util.Map;

import entity.UUIDActor;
import entity.CollisionBox;
import entity.UUIDProjectile;
import entity.Faction;

/**
 * It's the rappresentation of a Virtual Map that contain entities.
 * @param <A>
 * @param <P>
 */
public interface VirtualMap<A extends UUIDActor, P extends UUIDProjectile> {

    Map<A, CollisionBox<Integer>> getActors(Faction faction);

    Map<A, CollisionBox<Integer>> getActors();

    Map<P, CollisionBox<Integer>> getProjectiles();

    Movment addProjectile(P projectile, int x, int y) throws OutofBoundariesException;

    Movment addProjectile(P projectile, CollisionBox<Integer> collisionbox) throws OutofBoundariesException;

    Movment addActor(A actor, int x, int y) throws OutofBoundariesException;

    Movment addActor(A actor, CollisionBox<Integer> collisionbox) throws OutofBoundariesException;

    void removeActor(A actor);

    void removeProjectile(P projectile);

    int getHeigth();

    int getWidth();

}
