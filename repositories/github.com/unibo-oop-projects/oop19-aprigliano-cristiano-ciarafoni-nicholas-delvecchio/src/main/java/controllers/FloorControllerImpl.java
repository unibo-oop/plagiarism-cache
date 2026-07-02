package controllers;

import model.world.FloorModel;
import model.world.FloorModelImpl;
import view.FloorView;
import view.FloorViewImpl;

public class FloorControllerImpl implements FloorController {

    private final FloorView floorView;
    private final FloorModel floorModel;

    /**
     * FloorControllerImpl constructor.
     */
    public FloorControllerImpl() {
        this.floorView = new FloorViewImpl();
        this.floorModel = new FloorModelImpl();
        this.startFloorView();
    }

    /**
     * This function has to start the view of the floor object.
     */
    @Override
    public void startFloorView() {
        floorView.setImg(floorModel.getFloorImgPath());
        floorView.setHeight(floorModel.getFloorHeight());
        floorView.setWidth(floorModel.getFloorWidth());
        floorView.setPosition(floorModel.getFloorPosX(), floorModel.getFloorPosY());
    }

    /**
     *
     * @return the view of the floor object.
     */
    @Override
    public FloorView getFloorView() {
        return this.floorView;
    }

    /**
     *
     * @return the model of the floor object.
     */
    @Override
    public FloorModel getFloorModel() {
        return this.floorModel;
    }
}
