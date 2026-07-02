package cubestructure;

/**
 * DO NOT USE THIS
 * Class used for manual test during debugging.
 * 
 */
public final class TestCubeStructureHelper {

    private TestCubeStructureHelper() {
    }
    /**
     * Uncomment the test u want to try and remember to import possible library used.
     * From default it craft a {@link Cube3X3} whit a Random configuration,
     * it print it and then it check if is a valid {@link Cube3X3}.
     * @param args -
     */
    public static void main(final String[] args) {
        final Cube3X3 rubikCube = new Cube3X3();
        final CubeCheck prova = new CubeCheck();
        rubikCube.setRandomCube();
        //rubikCube.setCompletedCube();
        //(BIANCO R&L checkOK)
        //MoveUtils.turn(rubikCube.getRubikCube(), Color.RED, Direction.RIGHT);
        //MoveUtils.turn(rubikCube.getRubikCube(), Color.RED, Direction.LEFT);
        //(ROSSO checkOK)

        // rubikCube.getRubikCube()[0][0][0].setCube(Color.WHITE, Color.RED, Color.BLUE, Color.GRAY,Color.GRAY, Color.GRAY);
        //combinazioni da 3 colori con facce sbagliate
        //(passato)rubikCube.getRubikCube()[0][0][0].setCube(Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.GRAY,Color.GRAY, Color.GRAY);
        //(passato)rubikCube.getRubikCube()[0][0][0].setCube(Color.YELLOW, Color.YELLOW, Color.RED, Color.GRAY,Color.GRAY, Color.GRAY);
        //(passato)rubikCube.getRubikCube()[0][0][0].setCube(Color.RED, Color.YELLOW, Color.YELLOW, Color.GRAY,Color.GRAY, Color.GRAY);
        //(passato)rubikCube.getRubikCube()[0][0][0].setCube(Color.YELLOW, Color.RED, Color.YELLOW, Color.GRAY,Color.GRAY, Color.GRAY);
        //combinazioni cubi non completi da 3 facce
        //(passato)rubikCube.getRubikCube()[2][2][2].setCube(Color.GRAY, Color.YELLOW, Color.RED, Color.GRAY,Color.GRAY, Color.GRAY);
        //(passato)rubikCube.getRubikCube()[2][2][2].setCube(Color.YELLOW, Color.RED, Color.GRAY, Color.GRAY,Color.GRAY, Color.GRAY);
        //(passato)rubikCube.getRubikCube()[2][2][2].setCube(Color.YELLOW, Color.GRAY, Color.RED, Color.GRAY,Color.GRAY, Color.GRAY);
        //(passato)rubikCube.getRubikCube()[0][0][0].setCube(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY,Color.GRAY, Color.GRAY);
        //combinazione 2 non completi
        //(passato)rubikCube.getRubikCube()[2][2][1].setCube(Color.YELLOW, Color.GRAY, Color.GRAY, Color.GRAY,Color.GRAY, Color.GRAY);
        //(passato)rubikCube.getRubikCube()[2][2][1].setCube(Color.GRAY, Color.YELLOW, Color.GRAY, Color.GRAY,Color.GRAY, Color.GRAY);
        //(passato)rubikCube.getRubikCube()[0][0][0].setCube(Color.YELLOW, Color.RED, Color.GRAY, Color.GRAY,Color.GRAY, Color.GRAY);
        for (int l = 0; l < 3; l++) {
            for (int h = 0; h < 3; h++) {
                for (int d = 0; d < 3; d++) {
                    rubikCube.getRubikCube()[l][h][d].cubeToString();
                }
            }
        }

        System.out.println("*****************************************");
        System.out.println(prova.checkUserWork(rubikCube));
        System.out.println("*****************************************");
        System.out.println("fatto ? " + prova.isItDone());
        System.out.println("*****************************************");
        /*
        for(int l = 0 ;l < 3;l++) {
                for(int h = 0 ;h < 3;h++) {
                        for(int d = 0 ;d < 3;d++) {
                                rubikCube.getRubikCube()[l][h][d].cubeToString();
                        }
                }
        }
        //*/
        // Move.Turn(rubikCube.rubik, Color.WHITE, Direction.LEFT);
        /*
        System.out.println("*****************************************");
        for(int l = 0 ;l < 3;l++) {
                for(int h = 0 ;h < 3;h++) {
                        for(int d = 0 ;d < 3;d++) {
                                rubikCube.rubik[l][h][d].cubeToString();
                        }
                }
        } 
        */
    }
}
