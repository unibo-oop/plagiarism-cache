package boxhead.model.entities.zombies;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import boxhead.model.entities.utils.Collision;
import boxhead.model.entities.utils.Direction;
import boxhead.model.entities.Player;

/**
 * {@link ZombieAI} implementation
 */
public class ZombieAIImpl implements ZombieAI {
	
   private Set<BoundingBox> walls;

   public ZombieAIImpl() {
	   this.walls = new HashSet<>();
   }
   
   /**
    * {@inheritDoc}
    */
   @Override
   public final void determineNextMovement(final Set<Zombie> zombies, final Player player) {
       zombies.stream().forEach(z -> {
           this.computePositionWithCollisions(z, zombies, player);
           this.checkCollisionWithPlayer(z, player);
       });
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void setWalls(final Set<BoundingBox> walls) {
       this.walls = walls;

   }

   /**
    * Computes next position.
    * 
    * @param position starting position;
    * @param direction direction;
    * @return a next position Point2D
    */
   private Point2D computeNextPosition(final Point2D position, final Direction direction) {
       final double nextPosX = position.getX() + direction.traduce().getX();
       final double nextPosY = position.getY() + direction.traduce().getY();
       return new Point2D(nextPosX, nextPosY);
   }

   /**
    * Compute all zombies next position, checking collisions
    * 
    * @param zombie  to move
    * @param zombies set of all zombies
    * @param player
    */
   private void computePositionWithCollisions(final Zombie zombie, final Set<Zombie> zombies, final Player player) {
       Direction currentDirection = zombie.getDirection();
       final Set<BoundingBox> entities = zombies.stream().filter(z -> !z.equals(zombie)).map(z -> z.getBoundingBox())
               .collect(Collectors.toSet());
       if (!this.walls.isEmpty()) {
    	   entities.addAll(this.walls);
       }

       final Set<Direction> collisions = this.checkCollisions(zombie, entities, player);

       if (!collisions.contains(player.getDirection())){
           currentDirection = getDirectionToPlayer(zombie, player);
       } else{
           if (collisions.contains(Direction.NORTH)) {
               if (!collisions.contains(Direction.EAST)) {
                   currentDirection = Direction.NORTH_EAST;
               } else if (!collisions.contains(Direction.WEST)) {
                   currentDirection = Direction.NORTH_WEST;
               } else if (!collisions.contains(Direction.SOUTH)){
                   currentDirection = Direction.SOUTH;
               }
           }

       }      
       zombie.setDirection(currentDirection);
       zombie.setPosition(this.computeNextPosition(zombie.getPosition(), currentDirection));
   }

   /**
    * Check if next positions is occupied 
    * 
    * @param zombie zombie to move
    * @param entity entity to check
    * @param angle  direction to check
    * @return true if the position is occupied, false otherwise
    */
   private boolean isOccupied(final Zombie zombie, final BoundingBox entity, final Direction angle) {
       final Point2D nextPos = computeNextPosition(zombie.getPosition(), angle);
       return Collision.isColliding(
               new BoundingBox(nextPos.getX(), nextPos.getY(), zombie.getWidth()+8, zombie.getHeight()+8), entity);
   }

   /**
    * For every direction, check collisions
    * 
    * @param zombie zombie to move.
    * @param entities set of entities to check collisions with
    * @param player player
    * @return set of collision directions
    */
   private Set<Direction> checkCollisions(final Zombie zombie, final Set<BoundingBox> entities, final Player player) {
       final Set<Direction> directions = new HashSet<>();
       entities.stream().forEach(e -> {
           if (this.isOccupied(zombie, e, Direction.NORTH)) {
               directions.add(Direction.NORTH);
           }
           if (this.isOccupied(zombie, e, Direction.NORTH_WEST)) {
               directions.add(Direction.NORTH_WEST);
           }
           if (this.isOccupied(zombie, e, Direction.NORTH_EAST)) {
               directions.add(Direction.NORTH_EAST);
           }
           if (this.isOccupied(zombie, e, Direction.WEST)) {
               directions.add(Direction.WEST);
           }
           if (this.isOccupied(zombie, e, Direction.EAST)) {
               directions.add(Direction.EAST);
           }
           if (this.isOccupied(zombie, e, Direction.SOUTH)) {
               directions.add(Direction.SOUTH);
           }
           if (this.isOccupied(zombie, e, Direction.SOUTH_WEST)) {
               directions.add(Direction.SOUTH_WEST);
           }
           if (this.isOccupied(zombie, e, Direction.SOUTH_EAST)) {
               directions.add(Direction.SOUTH_EAST);
           }       
           if (this.isOccupied(zombie, e, ZombieAIImpl.getDirectionToPlayer(zombie, player))) {
               directions.add(player.getDirection());
           }
       });
       return directions;
   }

   /**
    * Checks if zombie is colliding with player and in case damages it.
    * @param zombie to check.
    * @param player
    */
   private void checkCollisionWithPlayer(final Zombie zombie, final Player player) {
       if (Collision.isColliding(zombie.getBoundingBox(), player.getBoundingBox())) {
           player.takeDamage(zombie.getDamage());
       }
   }

   /**
    * 
    * @param zombie zombie
    * @param player player
    * @return a direction (in degrees) after checked the nearest angle
    */
   public static final Direction getDirectionToPlayer(final Zombie zombie, final Player player) {
       final double distanceX = player.getPosition().getX() - (zombie.getPosition().getX());
       final double distanceY = player.getPosition().getY() - (zombie.getPosition().getY());
       final double degree = Math.toDegrees(Math.atan2(distanceY, distanceX));
       return checkNearestAngle(degree,player);
       
   }
   
   /*
    * Return the nearest direction based on player degree
    */
   private static Direction checkNearestAngle(final double degree, Player player) {    
       //System.out.println(degree);
       if (degree > -22.5  && degree <= 22.5 ) {
               return Direction.EAST;
           }else
           if(degree > 112.5 && degree <= 157.5) {
               return Direction.SOUTH_WEST;
           }else
           if(degree > 22.5 && degree <= 67.5) {
               return Direction.SOUTH_EAST;
           }else
           if(degree > -157.5 && degree <= -112.5) {
               return Direction.NORTH_WEST;
           }else
           if(degree > -112.5 && degree <= -67.5) {
               return Direction.NORTH;
           }else
           if(degree > -67.5 && degree <= -22.5) {
               return Direction.NORTH_EAST;
           }else
           if(degree > 67.5 && degree <= 112.5) {
               return Direction.SOUTH;
           }else
           if((degree > 157.5 && degree <=180) || (degree < -157.5 && degree >=-180)){
               return Direction.WEST;
           }
           return Direction.NULL;
   }
   }
