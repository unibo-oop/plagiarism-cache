package model.common;

public interface Movement {
    void setMovement(double xMovement, double yMovement);

    double getXMovement();

    double getYMovement();

    void mul(double speed);

}
