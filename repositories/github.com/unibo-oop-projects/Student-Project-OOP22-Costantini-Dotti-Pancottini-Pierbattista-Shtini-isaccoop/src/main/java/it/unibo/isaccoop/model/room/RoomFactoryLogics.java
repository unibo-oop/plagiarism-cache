package it.unibo.isaccoop.model.room;

/**
 * Delegated class that contains the logic to check conditions while creating rooms.
 */
public final class RoomFactoryLogics {

    private final int totalNumberOfRooms;
    private boolean alreadyCreatedBossRoom;
    private boolean alreadyCreatedStartRoom;
    private boolean alreadyCreatedTreasureRoom;
    private boolean alreadyCreatedShopRoom;

    /**
     * Constructor. Requires the total number of rooms to be created
     * @param totalNumberOfRooms the total number of rooms to be created
     */
    protected RoomFactoryLogics(final int totalNumberOfRooms) {
        this.totalNumberOfRooms = totalNumberOfRooms;
        this.alreadyCreatedBossRoom = false;
        this.alreadyCreatedStartRoom = false;
        this.alreadyCreatedTreasureRoom = false;
        this.alreadyCreatedShopRoom = false;
    }

    /**
     * Check if the next room to generate can be a START room.
     * @param roomCount the number of rooms currently generated (0-based count)
     * @return true if can build START room (i.e.: if this is the FIRST room - with roomCount = 0), 
     * false otherwise
     */
    public boolean canBuildStartRoom(final int roomCount) {
        return roomCount == 0;
    }

    /**
     * Check if the next room to generate can be a BOSS room.
     * @param roomCount the number of rooms currently generated (0-based count)
     * @return true if can build BOSS room (i.e.: if this is the LAST room - 
     * with roomCount = totalNumberOfRooms - 1), false otherwise
     */
    public boolean canBuildBossRoom(final int roomCount) {
        return roomCount == this.totalNumberOfRooms - 1;
    }

    /**
     * Check if the next room to generate can be a NON BOSS or NON START room.
     * @param roomCount the number of rooms currently generated (0-based count)
     * @return true if can build a NON BOSS or NON START room (i.e.: if this is not the FIRST or LAST room),
     * false otherwise
     */
    public boolean canBuildNonBossNonStartRoom(final int roomCount) {
        return roomCount < this.totalNumberOfRooms;
    }

    /**
     * Check if has already been created a START room.
     * @return true if a START has already been created, false otherwise
     */
    public boolean hasAlreadyBuiltStartRoom() {
        return this.alreadyCreatedStartRoom;
    }

    /**
     * Check if has already been created a BOSS room.
     * @return true if a BOSS has already been created, false otherwise
     */
    public boolean hasAlreadyBuiltBossRoom() {
        return this.alreadyCreatedBossRoom;
    }

    /**
     * Check if has already been created a SHOP room.
     * @return true if a SHOP has already been created, false otherwise
     */
    public boolean hasAlreadyBuiltShopRoom() {
        return this.alreadyCreatedShopRoom;
    }

    /**
     * Check if has already been created a TREASURE room.
     * @return true if a TREASURE has already been created, false otherwise
     */
    public boolean hasAlreadyBuiltTreasureRoom() {
        return this.alreadyCreatedTreasureRoom;
    }

    /**
     * Set that has already been created a START room.
     */
    public void setAlreadyBuiltStartRoom() {
        this.alreadyCreatedStartRoom = true;
    }

    /**
     * Set that has already been created a SHOP room.
     */
    public void setAlreadyBuiltShopRoom() {
        this.alreadyCreatedShopRoom = true;
    }

    /**
     * Set that has already been created a TREASURE room.
     */
    public void setAlreadyBuiltTreasuretRoom() {
        this.alreadyCreatedTreasureRoom = true;
    }

    /**
     * Set that has already been created a BOSS room.
     */
    public void setAlreadyBuiltBossRoom() {
        this.alreadyCreatedBossRoom = true;
    }
}
