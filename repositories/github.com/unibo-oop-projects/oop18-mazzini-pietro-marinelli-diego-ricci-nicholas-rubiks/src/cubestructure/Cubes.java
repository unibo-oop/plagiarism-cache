package cubestructure;

import javafx.scene.paint.Color;
import movestructure.Face;

/**
 * this code craft a Cube Object that is the basic structure of the RubikCube.
 */
public interface Cubes {

    /**
     * Method used to set the Color of the cube's faces.
     * @param topC - color of the Top face of the cube
     * @param frontC - color of the Front face of the cube
     * @param leftC - color of the Left face of the cube
     * @param rightC - color of the Right face of the cube
     * @param backC - color of the Back face of the cube
     * @param bottomC - color of the Bottom face of the cube
     */
    void setCube(Color topC, Color frontC, Color leftC, Color rightC, Color backC, Color bottomC);
    /**
     * Method used by the Controller to set cube that User's trying to set.
     * @param face - {@link Face}
     * @param color - {@link Color}, color to set in the chosen Face
     */
    void setFromVisual(Face face, Color color);
    /**
     * Method that set color of the Top face of the cube. 
     * @param color {@link Color},color to set in the chosen Face
     */
    void setTop(Color color);
    /**
     * Method that set color of the Front face of the cube. 
     * @param color {@link Color},color to set in the chosen Face
     * */
    void setFront(Color color);
    /**
     * Method that set color of the Back face of the cube. 
     * @param color {@link Color},color to set in the chosen Face
     * */
    void setBack(Color color);
    /**
     * Method that set color of the Right face of the cube. 
     * @param color {@link Color},color to set in the chosen Face
     * */
    void setRight(Color color);
    /**
     * Method that set color of the Left face of the cube. 
     * @param color {@link Color},color to set in the chosen Face
     * */
    void setLeft(Color color);
    /**
     * Method that set color of the Bottom face of the cube. 
     * @param color {@link Color},color to set in the chosen Face
     * */
    void setBottom(Color color);
    /**
     * Return a reference of the Cube.
     * @return {@link Cube}
     */
    Cube getCube();
    /**
     * Return {@link Color}, of Top face of the cube.
     * @return {@link Color}
     */
    Color getTop();
    /**
     * Return {@link Color}, of Front face of the cube.
     * @return {@link Color}
     */
    Color getFront();
    /**
     * Return {@link Color}, of Black face of the cube.
     * @return {@link Color}
     */
    Color getBack();
    /**
     * Return {@link Color}, of Right face of the cube.
     * @return {@link Color}
     */
    Color getRight();
    /**
     * Return {@link Color}, of Left face of the cube.
     * @return {@link Color}
     */
    Color getLeft();
    /**
     * Return {@link Color}, of Bottom face of the cube.
     * @return {@link Color}
     */
    Color getBottom();
    /**
     * Return {@link Cube}.id value of identifier of the cube.
     * @return {@link Cubes}.id
     */
    int getID();
    /**
     * Return Row position of the cube in the RubikCube array.
     * @return int
     */
    int getRow();
    /**
     * Return Column position of the cube in the RubikCube array.
     * @return int
     */
    int getColumn();
    /**
     * Return Depth position of the cube in the RubikCube array.
     * @return int
     */
    int getDepth();
    /**
     * Return a {@link Cube} that's a copy of this {@link Cube}.
     * @return {@link Cube}
     */
    Cube getCopyOf();
    /**
     * Print a scheme of the information of the this {@link Cube}.
     */
    void cubeToString();
}
