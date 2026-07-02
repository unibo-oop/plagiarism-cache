package controller.ballscontroller.launch;

import controller.ballscontroller.ballsagent.BallsAgentPause;
import model.ball.Ball;

import java.util.Set;

public interface LaunchControllerFactory {

    /**
     * @param balls        the set of ball to launch
     * @param ballsAgent   the class of the ball movement
     * @param timeInterval the interval between the launch of a ball and the next one
     * @return a Launch based on the class Thread
     */

    LaunchControllerPause standardThreadBallLaunch(Set<Ball> balls, BallsAgentPause ballsAgent, long timeInterval);

    /**
     * @param balls        the set of ball to launch
     * @param ballsAgent   the class of the ball movement
     * @param timeInterval the interval between the launch of a ball and the next one
     * @return a Launch based on the class Thread
     */

    LaunchControllerPause standardThreadPauseBallLaunch(Set<Ball> balls, BallsAgentPause ballsAgent, long timeInterval);
}
