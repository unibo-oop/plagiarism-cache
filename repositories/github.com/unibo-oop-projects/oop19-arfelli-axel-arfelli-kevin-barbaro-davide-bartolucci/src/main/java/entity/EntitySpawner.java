package entity;

import java.util.Set;

public interface EntitySpawner {

    void spwanProjectile(Projectile p);

    void spwanActor(Actor a);

    Set<Actor> getDespawnedActors();

    Set<Projectile> getDespawnedProjectiles();

}
