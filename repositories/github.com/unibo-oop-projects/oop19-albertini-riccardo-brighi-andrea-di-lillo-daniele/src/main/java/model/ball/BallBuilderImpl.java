package model.ball;

import element.Elements;
import element.Point2D;
import element.Vector2D;
import utility.Counter;
import utility.CounterImpl;
import utility.Observer;
import utility.Source;
import utility.SourceImpl;

import java.util.Objects;
import java.util.Optional;

public class BallBuilderImpl implements BallBuilder {

    private double radius = 1;
    private int damage = 1;
    private int startSpeed = 1;
    private final Source<Ball> movementUpdate = new SourceImpl<>();
    private final Source<Ball> stopUpdate = new SourceImpl<>();
    private Point2D startPosition;

    @Override
    public Ball build() {
        if (Objects.isNull(startPosition)) {
            throw new IllegalStateException();
        }
        return new Ball() {

            private final Counter speed = new CounterImpl(startSpeed);
            private Point2D position = startPosition;
            private Vector2D direction = Elements.VECTOR_NULL;
            private Optional<Point2D> destination = Optional.empty();

            @Override
            public double getRadius() {
                return radius;
            }

            @Override
            public synchronized double getSpeed() {
                return direction.isNullVector() ? 0 : speed.getValue();
            }

            @Override
            public synchronized void incrementSpeed() {
                this.speed.increment();
            }

            @Override
            public int getDamage() {
                return damage;
            }

            @Override
            public synchronized Point2D getPosition() {
                return this.position;
            }

            @Override
            public synchronized Vector2D getDirection() {
                return this.direction;
            }

            @Override
            public synchronized void setDirection(final Vector2D newDirection) {
                if (Objects.isNull(newDirection)) {
                    throw new IllegalArgumentException();
                }
                if (!this.direction.equals(newDirection)) {
                    this.direction = newDirection;
                    if (this.direction.isNullVector()) {
                        this.speed.reset();
                        stopUpdate.notifyObservers(this);
                    } else {
                        this.direction = this.direction.getNormalizedVector();
                    }
                }
            }

            @Override
            public synchronized boolean isStationary() {
                return this.direction.isNullVector();
            }

            @Override
            public synchronized void moveByTime(final long timeInterval) {
                this.moveByDistance(timeInterval * this.getSpeed());
            }

            @Override
            public synchronized void moveByDistance(final double distance) {
                if (destination.isPresent()) {
                    final Vector2D difference = destination.get().subtraction(this.position);
                    if (difference.hasSameNormalizedVector(this.direction) && difference.getModulus() <= distance) {
                        this.move(difference);
                        this.setDirection(Elements.VECTOR_NULL);
                        this.destination = Optional.empty();
                    }
                }
                this.move(this.direction.scalarMultiplication(distance));
            }

            private void move(final Vector2D distance) {
                if (!distance.isNullVector()) {
                    this.position = this.position.sum(distance);
                    movementUpdate.notifyObservers(this);
                }
            }

            @Override
            public synchronized void collision(final Point2D centerPoint, final Vector2D newDirection) {
                if (Objects.isNull(centerPoint) || Objects.isNull(newDirection)) {
                    throw new IllegalArgumentException();
                }
                if (!centerPoint.equals(this.position)) {
                    final Vector2D distance = this.position.subtraction(centerPoint);
                    if (distance.isNullVector() || direction.isNullVector() || !distance.hasSameNormalizedVector(this.direction)) {
                        throw new IllegalArgumentException();
                    }
                    this.position = centerPoint;
                    this.setDirection(newDirection);
                    this.moveByDistance(distance.getModulus());
                }
            }

            @Override
            public synchronized void setDestination(final Point2D destination) {
                if (this.position.equals(destination)) {
                    this.setDirection(Elements.VECTOR_NULL);
                } else {
                    this.destination = Optional.of(destination);
                }
            }
        };
    }

    @Override
    public BallBuilder setRadius(final double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException();
        }
        this.radius = radius;
        return this;
    }

    @Override
    public BallBuilder addDamage(final int damage) {
        if (damage <= 0) {
            throw new IllegalArgumentException();
        }
        this.damage = damage;
        return this;
    }

    @Override
    public BallBuilder addStartSpeed(final int startSpeed) {
        if (startSpeed <= 0) {
            throw new IllegalArgumentException();
        }
        this.startSpeed = startSpeed;
        return this;
    }

    @Override
    public BallBuilder addStartPosition(final Point2D startPosition) {
        if (Objects.isNull(startPosition)) {
            throw new IllegalArgumentException();
        }
        this.startPosition = startPosition;
        return this;
    }

    @Override
    public BallBuilder addMovementObserver(final Observer<Ball> observer) {
        if (Objects.isNull(observer)) {
            throw new IllegalArgumentException();
        }
        this.movementUpdate.addObserver(observer);
        return this;
    }

    @Override
    public BallBuilder addStopObserver(final Observer<Ball> observer) {
        if (Objects.isNull(observer)) {
            throw new IllegalArgumentException();
        }
        this.stopUpdate.addObserver(observer);
        return this;
    }
}
