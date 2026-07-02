package movestructure;

import cubestructure.Cube;
import javafx.scene.paint.Color;
/**
 * Class that have the purpose of rotate Cube3X3.
 */
public final class MoveUtils {
    private static final int ROT_START_AND_END = 5;
    private static final int FIRST_RIGHT_LAST_LEFT_1 = 6;
    private static final int FIRST_RIGHT_LAST_LEFT_2 = 7;
    private static final int FIRST_LEFT_LAST_RIGHT_1 = 0;
    private static final int FIRST_LEFT_LAST_RIGHT_2 = 1;

    private MoveUtils() {
    }
    /**
     * Method that rotate the specified {@link SideUtils} in the specified {@link Direction}.
     * @param rubik - {@link Cube}[][][] subject of rotation
     * @param centerC  - {@link Color} of the central {@link Cube} of the chosen {@link SideUtils}
     * @param direction - Enumerator {@link Direction}
     */
    public static void turn(final Cube[][][] rubik,  final Color centerC, final Direction direction) {
        Direction dir = direction;
        SideUtils.extractSide(rubik, centerC);
        if (Color.GREEN == centerC || Color.YELLOW == centerC || Color.ORANGE == centerC) {
            if (dir == Direction.LEFT) {
                dir = Direction.RIGHT;
            } else {
                dir = Direction.LEFT;
            }
        }
        //Start of the Right rotation
        if (dir == Direction.RIGHT) {
            rightRotation(centerC);
        } else { 
            //Start of the Left rotation
            leftRotation(centerC);
        }
    }
    private static void leftRotation(final Color centerC) {
        final Cube temp1 = SideUtils.getSide().get(0).getCopyOf();
        final Cube temp2 = SideUtils.getSide().get(1).getCopyOf();

        for (int i = 0; i <= ROT_START_AND_END; i++) {
            if (Color.WHITE == centerC || Color.YELLOW == centerC) {
                if (Color.WHITE == centerC) {
                    SideUtils.getSide().get(i).setTop(SideUtils.getSide().get(i + 2).getTop());
                } else {
                    SideUtils.getSide().get(i).setBottom(SideUtils.getSide().get(i + 2).getBottom());
                }
                SideUtils.getSide().get(i).setRight(SideUtils.getSide().get(i + 2).getFront());
                SideUtils.getSide().get(i).setFront(SideUtils.getSide().get(i + 2).getLeft());
                SideUtils.getSide().get(i).setLeft(SideUtils.getSide().get(i + 2).getBack());
                SideUtils.getSide().get(i).setBack(SideUtils.getSide().get(i + 2).getRight());
            } else {
                if (Color.RED == centerC || Color.ORANGE == centerC) {
                    if (Color.RED == centerC) {
                        SideUtils.getSide().get(i).setFront(SideUtils.getSide().get(i + 2).getFront());
                    } else {
                        SideUtils.getSide().get(i).setBack(SideUtils.getSide().get(i + 2).getBack());
                    }
                    SideUtils.getSide().get(i).setRight(SideUtils.getSide().get(i + 2).getBottom());
                    SideUtils.getSide().get(i).setBottom(SideUtils.getSide().get(i + 2).getLeft());
                    SideUtils.getSide().get(i).setLeft(SideUtils.getSide().get(i + 2).getTop());
                    SideUtils.getSide().get(i).setTop(SideUtils.getSide().get(i + 2).getRight());
                } else {
                    if (Color.GREEN == centerC || Color.BLUE == centerC) {
                        if (Color.GREEN == centerC) {
                            SideUtils.getSide().get(i).setLeft(SideUtils.getSide().get(i + 2).getLeft());
                        } else {
                            SideUtils.getSide().get(i).setRight(SideUtils.getSide().get(i + 2).getRight());
                        }
                        SideUtils.getSide().get(i).setBottom(SideUtils.getSide().get(i + 2).getFront());
                        SideUtils.getSide().get(i).setBack(SideUtils.getSide().get(i + 2).getBottom());
                        SideUtils.getSide().get(i).setTop(SideUtils.getSide().get(i + 2).getBack());
                        SideUtils.getSide().get(i).setFront(SideUtils.getSide().get(i + 2).getTop());
                    }
                }
            }
        }
        //settings of the last 2 cubes
        if (Color.WHITE == centerC || Color.YELLOW == centerC) {
            for (int i = FIRST_RIGHT_LAST_LEFT_1; i <= FIRST_RIGHT_LAST_LEFT_2; i++) {
                if (Color.WHITE == centerC) {
                    SideUtils.getSide().get(i).setTop(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getTop() : temp2.getTop());
                } else {
                    SideUtils.getSide().get(i).setBottom(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getBottom() : temp2.getBottom());
                }
                SideUtils.getSide().get(i).setRight(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getFront() : temp2.getFront());
                SideUtils.getSide().get(i).setFront(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getLeft() : temp2.getLeft());
                SideUtils.getSide().get(i).setLeft(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getBack() : temp2.getBack());
                SideUtils.getSide().get(i).setBack(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getRight() : temp2.getRight());
            }
        } else {
            if (Color.RED == centerC || Color.ORANGE == centerC) {
                for (int i = FIRST_RIGHT_LAST_LEFT_1; i <= FIRST_RIGHT_LAST_LEFT_2; i++) {
                    if (Color.RED == centerC) {
                        SideUtils.getSide().get(i).setFront(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getFront() : temp2.getFront());
                    } else {
                        SideUtils.getSide().get(i).setBack(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getBack() : temp2.getBack());
                    }
                    SideUtils.getSide().get(i).setRight(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getBottom() : temp2.getBottom());
                    SideUtils.getSide().get(i).setBottom(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getLeft() : temp2.getLeft());
                    SideUtils.getSide().get(i).setLeft(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getTop() : temp2.getTop());
                    SideUtils.getSide().get(i).setTop(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getRight() : temp2.getRight());
                }
            } else {
                if (Color.GREEN == centerC || Color.BLUE == centerC) {
                    for (int i = FIRST_RIGHT_LAST_LEFT_1; i <= FIRST_RIGHT_LAST_LEFT_2; i++) {
                        if (Color.GREEN == centerC) {
                            SideUtils.getSide().get(i).setLeft(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getLeft() : temp2.getLeft());
                        } else {
                            SideUtils.getSide().get(i).setRight(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getRight() : temp2.getRight());
                        }
                        SideUtils.getSide().get(i).setBottom(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getFront() : temp2.getFront());
                        SideUtils.getSide().get(i).setBack(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getBottom() : temp2.getBottom());
                        SideUtils.getSide().get(i).setTop(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getBack() : temp2.getBack());
                        SideUtils.getSide().get(i).setFront(i == FIRST_RIGHT_LAST_LEFT_1 ? temp1.getTop() : temp2.getTop());
                    }
                }
            }
        }
    }

    private static void rightRotation(final Color centerC) {
        final Cube temp1 = SideUtils.getSide().get(FIRST_RIGHT_LAST_LEFT_1).getCopyOf();
        final Cube temp2 = SideUtils.getSide().get(FIRST_RIGHT_LAST_LEFT_2).getCopyOf();

        for (int i = ROT_START_AND_END; i >= 0; i--) {
            if (Color.WHITE == centerC || Color.YELLOW == centerC) {
                if (Color.WHITE == centerC) {
                    SideUtils.getSide().get(i + 2).setTop(SideUtils.getSide().get(i).getTop());
                } else {
                    SideUtils.getSide().get(i + 2).setBottom(SideUtils.getSide().get(i).getBottom());
                }
                SideUtils.getSide().get(i + 2).setRight(SideUtils.getSide().get(i).getBack());
                SideUtils.getSide().get(i + 2).setFront(SideUtils.getSide().get(i).getRight());
                SideUtils.getSide().get(i + 2).setLeft(SideUtils.getSide().get(i).getFront());
                SideUtils.getSide().get(i + 2).setBack(SideUtils.getSide().get(i).getLeft());
            } else {
                if (Color.RED == centerC || Color.ORANGE == centerC) {
                    if (Color.RED == centerC) {
                        SideUtils.getSide().get(i + 2).setFront(SideUtils.getSide().get(i).getFront());
                    } else {
                        SideUtils.getSide().get(i + 2).setBack(SideUtils.getSide().get(i).getBack());
                    }
                    SideUtils.getSide().get(i + 2).setRight(SideUtils.getSide().get(i).getTop());
                    SideUtils.getSide().get(i + 2).setBottom(SideUtils.getSide().get(i).getRight());
                    SideUtils.getSide().get(i + 2).setLeft(SideUtils.getSide().get(i).getBottom());
                    SideUtils.getSide().get(i + 2).setTop(SideUtils.getSide().get(i).getLeft());
                } else {
                    if (Color.GREEN == centerC || Color.BLUE == centerC) {
                        if (Color.GREEN == centerC) {
                            SideUtils.getSide().get(i + 2).setLeft(SideUtils.getSide().get(i).getLeft());
                        } else {
                            SideUtils.getSide().get(i + 2).setRight(SideUtils.getSide().get(i).getRight());
                        }
                        SideUtils.getSide().get(i + 2).setBottom(SideUtils.getSide().get(i).getBack());
                        SideUtils.getSide().get(i + 2).setBack(SideUtils.getSide().get(i).getTop());
                        SideUtils.getSide().get(i + 2).setTop(SideUtils.getSide().get(i).getFront());
                        SideUtils.getSide().get(i + 2).setFront(SideUtils.getSide().get(i).getBottom());
                    }
                }
            }
        }
        //setting of the last 2 cubes
        if (Color.WHITE == centerC || Color.YELLOW == centerC) {
            for (int i = FIRST_LEFT_LAST_RIGHT_1; i <= FIRST_LEFT_LAST_RIGHT_2; i++) {
                if (Color.WHITE == centerC) {
                    SideUtils.getSide().get(i).setTop(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getTop() : temp2.getTop());
                } else {
                    SideUtils.getSide().get(i).setBottom(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getBottom() : temp2.getBottom());
                }
                SideUtils.getSide().get(i).setRight(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getBack() : temp2.getBack());
                SideUtils.getSide().get(i).setFront(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getRight() : temp2.getRight());
                SideUtils.getSide().get(i).setLeft(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getFront() : temp2.getFront());
                SideUtils.getSide().get(i).setBack(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getLeft() : temp2.getLeft());
            }
        } else {
            if (Color.RED == centerC || Color.ORANGE == centerC) {
                for (int i = 0; i <= FIRST_LEFT_LAST_RIGHT_2; i++) {
                    if (Color.RED == centerC) {
                        SideUtils.getSide().get(i).setFront(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getFront() : temp2.getFront());
                    } else {
                        SideUtils.getSide().get(i).setBack(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getBack() : temp2.getBack());
                    }
                    SideUtils.getSide().get(i).setRight(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getTop() : temp2.getTop());
                    SideUtils.getSide().get(i).setBottom(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getRight() : temp2.getRight());
                    SideUtils.getSide().get(i).setLeft(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getBottom() : temp2.getBottom());
                    SideUtils.getSide().get(i).setTop(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getLeft() : temp2.getLeft());
                }
            } else {
                if (Color.GREEN == centerC || Color.BLUE == centerC) {
                    for (int i = FIRST_LEFT_LAST_RIGHT_1; i <= FIRST_LEFT_LAST_RIGHT_2; i++) {
                        if (Color.GREEN == centerC) {
                            SideUtils.getSide().get(i).setLeft(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getLeft() : temp2.getLeft());
                        } else {
                            SideUtils.getSide().get(i).setRight(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getRight() : temp2.getRight());
                        }
                        SideUtils.getSide().get(i).setBottom(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getBack() : temp2.getBack());
                        SideUtils.getSide().get(i).setBack(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getTop() : temp2.getTop());
                        SideUtils.getSide().get(i).setTop(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getFront() : temp2.getFront());
                        SideUtils.getSide().get(i).setFront(i == FIRST_LEFT_LAST_RIGHT_1 ? temp1.getBottom() : temp2.getBottom());
                    }
                }
            }
        }
    }
}
