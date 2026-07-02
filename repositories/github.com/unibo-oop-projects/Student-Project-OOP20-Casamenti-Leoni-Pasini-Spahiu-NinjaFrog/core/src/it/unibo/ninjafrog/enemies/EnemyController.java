package it.unibo.ninjafrog.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Definition of the EnemyController interface.
 */
public interface EnemyController {
    /**
     * Method that calls the update method update for every rino and turtle model.
     * 
     * @param dt the delta of the time
     */
    void update(float dt);

    /**
     * Method that calls the draw method for every rino and turtle view.
     * 
     * @param batch
     */
    void draw(SpriteBatch batch);

    /**
     * Method that check if the dynamicEnemyModel is in rinos and if can be killed
     * and call the collide method in the dynamicEnemyModel.
     * 
     * @param dynamicEnemyModel the model interface of the dynamicEnemy A
     *                          {@link it.unibo.ninjafrog.enemies.DynamicEnemyModel
     *                          DynamicEnemyModel}
     */
    void collide(DynamicEnemyModel dynamicEnemyModel);

    /**
     * Method that check if the dynamicEnemyModel is in the rinos map.
     * 
     * @param dynamicEnemyModel A
     *                          {@link it.unibo.ninjafrog.enemies.DynamicEnemyModel
     *                          DynamicEnemyModel} the model interface of the
     *                          dynamic enemy
     * @return the method isSetToDestroy of the dynamicEnemyModel
     */
    boolean isSetToDestroy(DynamicEnemyModel dynamicEnemyModel);

    /**
     * Method that check if the dynamicEnemyModel is in the rinos map and call the
     * method reverseVelocity in the DynamicEnemyModel.
     * 
     * @param dynamicEnemyModel A
     *                          {@link it.unibo.ninjafrog.enemies.DynamicEnemyModel
     *                          DynamicEnemyModel} the model interface of the
     *                          dynamic enemy
     */
    void reverseVelocity(DynamicEnemyModel dynamicEnemyModel);

    /**
     * Method that check if the dynamicEnemyModel is in the rinos map.
     * 
     * @param dynamicEnemyModel {@link it.unibo.ninjafrog.enemies.DynamicEnemyModel
     *                          DynamicEnemyModel} the model interface of the
     *                          dynamic enemy
     * @return the method getX of the corresponding view part
     */
    float getX(DynamicEnemyModel dynamicEnemyModel);

    /**
     * Method that check if the rinoModel is in the rinos map.
     * 
     * @param dynamicEnemyModel {@link it.unibo.ninjafrog.enemies.DynamicEnemyModel
     *                          DynamicEnemyModel} the model of the enemy dynamic
     *                          enemy
     * @return the method getX of the corresponding view part
     */
    float getY(DynamicEnemyModel dynamicEnemyModel);

    /**
     * Method that check if the view is in rinos map or in the turtles map.
     * 
     * @param enemyView {@link it.unibo.ninjafrog.enemies.EnemyView EnemyView} the
     *                  view of the enemy
     * @return the boolean "destroyed" of the corresponding modelImpl
     */
    boolean isDestroyed(EnemyView enemyView);

    /**
     * Method that check if the view is in rinos map or in the turtles map.
     * 
     * @param enemyView {@link it.unibo.ninjafrog.enemies.EnemyView EnemyView} the
     *                  view of the enemy
     * @return the state time of the corresponding modelImpl
     */

    float getStateTime(EnemyView enemyView);

    /**
     * Method that check if the dynamicEnemyModel is in the rinos map and call the
     * method setDeathRegion of the corresponding view part.
     * 
     * @param dynamicEnemyModel {@link it.unibo.ninjafrog.enemies.DynamicEnemyModel
     *                          DynamicEnemyModel} the model of the enemy dynamic
     *                          enemy
     */

    void setDeathRegion(DynamicEnemyModel dynamicEnemyModel);

    /**
     * Method that check if the dynamicEnemyModel is in the rinos map and call the
     * method update in the corresponding view part.
     * 
     * @param dynamicEnemyModel {@link it.unibo.ninjafrog.enemies.DynamicEnemyModel
     *                          DynamicEnemyModel} the model of the enemy dynamic
     *                          enemy
     * @param body              the body of the enemies
     * @param dt                the delta of the time
     */

    void updateView(DynamicEnemyModel dynamicEnemyModel, Body body, float dt);

    /**
     * Method that check if the enemyView is in the rinos map.
     * 
     * @param enemyView {@link it.unibo.ninjafrog.enemies.EnemyView EnemyView} the
     *                  view of the enemy
     * @return the result of the method is runningLeft of the DynamicEnemyModel
     */

    boolean isRunningLeft(EnemyView enemyView);

    /**
     * Method that check if the enemyView is in the rinos map and set the boolean
     * runningLeft of the rinoModelImpl.
     * 
     * @param enemyView {@link it.unibo.ninjafrog.enemies.EnemyView EnemyView} the
     *                  view of the enemy
     * @param b         this is the boolean that will set runningLeft
     */

    void setRunningLeft(EnemyView enemyView, boolean b);

    /**
     * Method that check if the staticEnemyModel is in the turtles map and return X
     * position of the turtle.
     * 
     * @param staticEnemyModel {@link it.unibo.ninjafrog.enemies.StaticEnemyModel
     *                         StaticEnemyModel} the model part of the enemy turtle
     * @return the method getX of the corresponding of the view part
     */

    float getX(StaticEnemyModel staticEnemyModel);

    /**
     * Method that check if the StaticEnemyModel is in the turtles map and return Y
     * position of the turtle.
     * 
     * @param staticEnemyModel {@link it.unibo.ninjafrog.enemies.StaticEnemyModel
     *                         StaticEnemyModel} the model part of the enemy turtle
     * @return the method getY of the corresponding of the view part
     */

    float getY(StaticEnemyModel staticEnemyModel);

    /**
     * Method that check if the StaticEnemyModel is in the turtles map and call the
     * method setDeathRegion in the TurtleView.
     * 
     * @param staticEnemyModel {@link it.unibo.ninjafrog.enemies.StaticEnemyModel
     *                         StaticEnemyModel} the model part of the enemy turtle
     */

    void setDeathRegion(StaticEnemyModel staticEnemyModel);

    /**
     * Method that check if the StaticEnemyModel is in the turtles map and call the
     * method setDeathRegion in the TurtleView.
     * 
     * @param staticEnemyModel {@link it.unibo.ninjafrog.enemies.StaticEnemyModel
     *                         StaticEnemyModel} the model part of the enemy turtle
     * @param body             the body of the enemy turtle
     * @param dt               the delta of the time
     */

    void updateView(StaticEnemyModel staticEnemyModel, Body body, float dt);

    /**
     * Method that check if the StaticEnemyModel is in the turtles map and call the
     * method collide in the TurtleView.
     * 
     * @param staticEnemyModel {@link it.unibo.ninjafrog.enemies.StaticEnemyModel
     *                         StaticEnemyModel} the model part of the enemy turtle
     * @param bit              the part of the body where the main chapter hit the
     *                         turtle
     */

    void collide(StaticEnemyModel staticEnemyModel, Short bit);

    /**
     * Method that check if the StaticEnemyModel is in the turtles map and call the
     * method collide in the TurtleView.
     * 
     * @param staticEnemyModel {@link it.unibo.ninjafrog.enemies.StaticEnemyModel
     *                         StaticEnemyModel} the model part of the enemy turtle
     * @return the boolean isSetToDestroy in the rinoViewImpl
     */
    boolean isSetToDestroy(StaticEnemyModel staticEnemyModel);

}
