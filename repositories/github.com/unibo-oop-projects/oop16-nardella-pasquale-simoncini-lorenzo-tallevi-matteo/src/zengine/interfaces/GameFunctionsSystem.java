// this interfaces has too much public methods, because there are lots of utility functions
// to work with GameObjects, but it will be splitted in two different components in the future,
// if necessary. One component for GameObjects, another component for rooms. For now, they work
// fine together.

package zengine.interfaces; ////

import zengine.core.GameObject;

/**
 * This interface represents the Zengine component that handles GameObjects,
 * classes, rooms and global game functions. The component implements methods to
 * create and remove GameObjects from the world, to define and modify "rooms",
 * which are basically loadable sets of GameObjects, and other utility functions
 * related to GameObjects in general.
 */
public interface GameFunctionsSystem {

    /**
     * creates an object of the class className inside Zengine, at position
     * (x,y) inside zengine's world. After being created and placed, the
     * object's create() method is called. className must specify a valid class
     * that extends from GameObject. Returns a reference to the created object,
     * but returns null if something wrong happened.
     * 
     * 
     * @param x
     *            x position to place the object
     * @param y
     *            y position to place the object
     * @param className
     *            name of the class of the object to be created
     * @return the reference of the created object or null if no object can be
     *         created
     */
    GameObject instanceCreate(double x, double y, String className);

    /**
     * adds the given object inside Zengine, at position (x,y) inside zengine's
     * world. After being placed, the object's create() method is called.
     * Returns a reference to the created object, but returns null if something
     * wrong happened. The same object cannot be added twice.
     * 
     * @param <T>
     *            type of the object to add
     * @param x
     *            x position to place the object
     * @param y
     *            y position to place the object
     * @param obj
     *            object to be added to Zengine
     * @return the reference of the created object or null if no object can be
     *         added
     */
    <T extends GameObject> T instanceAdd(double x, double y, T obj);

    /**
     * removes the given object from Zengine. The object's destroy() method is
     * called before being removed. This method returns true if the object could
     * be removed, false otherwise (for example, if it did not exist inside
     * Zengine).
     * 
     * @param obj
     *            object to be removed
     * @return true if the object was actually removed
     */
    boolean instanceDestroy(GameObject obj);

    /**
     * removes all objects of the given class from Zengine. ignoreMe can be null
     * or the reference of a single object that will survive. The objects that
     * will be removed will see their destroy() method called. Returns true if
     * everything went ok.
     * 
     * @param className
     *            name of the class to be cleared
     * @param ignoreMe
     *            reference to an object that should survive the clear
     * @return true if the operation was succesfull
     */
    boolean instanceDestroyAll(String className, GameObject ignoreMe);

    /**
     * destroys every object in Zengine except for the object ignoreMe. If
     * ignoreme is null, everything will be cleared, use with care!
     * 
     * @param ignoreMe
     *            reference of the object that should survive the destruction
     * @return true if the operation was successful
     */
    boolean instanceDestroyEverything(GameObject ignoreMe);

    /**
     * returns true whether the given object exists in Zengine's game world.
     * 
     * @param obj
     *            object to check
     * @return true if given object exists inside Zengine
     */
    boolean instanceExists(GameObject obj);

    /**
     * returns true if at least one instance of the given class exists inside
     * Zengine.
     * 
     * @param className
     *            class to check
     * @return true if at least one instance of the given class exists
     */
    boolean instanceExists(String className);

    /**
     * returns the number of GameObjects currently existing in the game world.
     * 
     * @return the number of GameObjects currently contained in Zengine
     */
    int instanceNumberGeneral();

    /**
     * returns how may objects of the given class are currently existing in the
     * game world.
     * 
     * @param className
     *            class to check
     * @return number of objects contained in Zengine
     */
    int instanceNumber(String className);

    /**
     * checks if the given object's class is the same as the specified class.
     * 
     * @param obj
     *            object to check
     * @param className
     *            name of the class to check
     * @return true if obj is a member of className
     */
    boolean isInstanceOf(GameObject obj, String className);

    /**
     * returns true if the two given objects are touching. Collision checking is
     * based on their current hitbox.
     * 
     * @param obj1
     *            first object to check
     * @param obj2
     *            second object to check
     * @return true if the two object's hitboxes are touching
     */
    boolean instanceIsColliding(GameObject obj1, GameObject obj2);

    /**
     * checks if the given object is colliding with at least one of the members
     * of class className. Of course if obj belongs to className, itself is
     * excluded from the computation.
     * 
     * @param obj
     *            object to check
     * @param className
     *            class to check
     * @return true if obj is touching at least one object of class className
     */
    boolean instanceIsColliding(GameObject obj, String className);

    /**
     * returns the class of the given object (a String containing the name of
     * the class as it would be used by Zengine).
     * 
     * @param obj
     *            object to inspect
     * @return a string representation of the object's class
     */
    String instanceClassName(GameObject obj);

    /**
     * returns true if the given string is a valid class name.
     * 
     * @param className
     *            string to be tested
     * @return true if clasName described a class that exists and extends
     *         GameObject
     */
    boolean classExists(String className);

    /**
     * terminates zengine with the given termination code number.
     * 
     * @param exitCode
     *            code to terminate the program with
     * @return the exit code
     */
    int gameTerminate(int exitCode);

    /**
     * restarts the game by resetting the initial conditions. The initialize()
     * method of ZengineInizializer is called again. Keep in mind that this
     * method does not restart the whole application, it only restarts Zengine
     * and its components. This means that every object unrelated to Zengine is
     * left untouched. This method does not clear any memory if not necessary.
     */
    void gameRestart();

    /**
     * runs the given lambda with the given object. The self parameter of the
     * lambda will contain a reference of the object currently running the
     * lambda.
     * 
     * @param obj
     *            target object
     * @param e
     *            Executor containing the code to be executed
     */
    void with(GameObject obj, GameObject.Executor e);

    /**
     * runs the given lambda with all of the instances of class className that
     * currently exist in Zengine. The self parameter of the lambda will contain
     * a reference of the object currently running the lambda.
     * 
     * @param className
     *            target class
     * @param e
     *            Executor containing the code to be executed
     */
    void with(String className, GameObject.Executor e);

    /**
     * returns the reference to an existing object of the given class. If only
     * one object of the given class currently exists, the reference of that
     * object will be returned. If more than one object currently exists, the
     * reference of one of them will be returned but it is undetermined which
     * one exactly. If no object exist, null is returned. It is good practice to
     * always test if instanceExists() the value returned by this method, since
     * it may return null if no GameObject is found.
     * 
     * @param className
     *            name of the class of the object to retrieve
     * @return the reference of an object of the specified class, or null if no
     *         such object exists
     */
    GameObject instanceGet(String className);

    /**
     * returns a reference of the nearest object of given class(es) to the given
     * point, or null if such object does not exist. It is good practice to
     * always test if instanceExists() the value returned by this method, since
     * it may return null if no GameObject is found.
     * 
     * @param x
     *            x coordinate of the point from which to calculate the distance
     * @param y
     *            y coordinate of the point from which to calculate the distance
     * @param classNames
     *            one or more class of objects to check
     * 
     * @return reference of the nearest object or null if no object exists
     */
    GameObject instanceNearest(double x, double y, String... classNames);

    /**
     * returns a reference of the furthest object of given class(es) to the
     * given point, or null if such object does not exist. It is good practice
     * to always test if instanceExists() the value returned by this method,
     * since it may return null if no GameObject is found.
     * 
     * @param x
     *            x coordinate of the point from which to calculate the distance
     * @param y
     *            y coordinate of the point from which to calculate the distance
     * @param classNames
     *            one or more class of objects to check
     * 
     * @return reference of the furthest object or null if no object exists
     */
    GameObject instanceFurthest(double x, double y, String... classNames);

    /**
     * returns true when called during the initialize method of the
     * ZengineInitializer.
     * 
     * @return true during the initialize() event
     */
    boolean isInitializeEvent();

    /**
     * returns true when called during the create() event of a GameObject.
     * 
     * @return true inside a create() event
     */
    boolean isCreateEvent();

    /**
     * returns true when called during the destroy() event of a GameObject.
     * 
     * @return true inside a destroy() event
     */
    boolean isDestroyEvent();

    /**
     * returns true when called during the update() event. Keep in mind that the
     * update() event includes other sub-events: mouseClicked(), collide(),
     * sometimes also destroy() and create().
     * 
     * @return true during the update() event
     */
    boolean isUpdateEvent();

    /**
     * returns true when called during the draw() event.
     * 
     * @return true during the draw() event
     */
    boolean isDrawEvent();

    /**
     * returns true when called during a collide() event. Keep in mind that the
     * collide() event is always included inside the update() event.
     * 
     * @return true during the collide() event
     */
    boolean isCollisionEvent();

    /**
     * returns true when called during a mouseClicked() event. Keep in mind that
     * the mouseClicked() event is always included inside the update() event.
     * 
     * @return true during the mouseClicked() event
     */
    boolean isMouseClickedEvent();

    /**
     * defines a new room called roomName, with width and height. These
     * dimensions are just representative of the world described by this room,
     * and have no effect on the game unless you do something by yourself (for
     * example by locking the camera inside the room).
     * 
     * @param roomName
     *            name of the new room to define
     * @param width
     *            width of the room in pixels (min 0)
     * @param height
     *            height of the room in pixels (min 0)
     * @return true if the operation was successful (no other rooms existed that
     *         the same name)
     */
    boolean roomDefine(String roomName, int width, int height);

    /**
     * sets the given lambda as the given room's creation code. The code will be
     * executed each time the room is loaded, after every GameObject is loaded.
     * You can use it to give the room more infos about its initialization (for
     * example, to always set the view to a certain position, to load/unload
     * certain variables etc).
     * 
     * @param roomName
     *            the name of the room to assign the code
     * @param code
     *            RoomCode containing the code to be executed
     * @return a reference to the previously existing creation code for the
     *         given room, or null if the room does not exist or didn't have any
     *         creation code
     */
    RoomCode roomSetCreationCode(String roomName, RoomCode code);

    /**
     * sets the given lambda as the specified room's ending code. The code will
     * be executed each time the room is about to end because you go to another
     * room, or you are restarting it. The code will not be called with the
     * roomLoad() method because it does not end the room, it loads a room
     * inside the current one. The code is called right before clearing all the
     * GameObjects. You can use it to save some progress, for example. Keep in
     * mind that the code is NOT called if the whole application is closing, for
     * example with a gameTerminate().
     * 
     * @param roomName
     *            the name of the room to assign the code
     * @param code
     *            RoomCode containing the code to be executed
     * @return a reference to the previously existing ending code for the given
     *         room, or null if the room does not exist or didn't have any
     *         ending code
     */
    RoomCode roomSetEndingCode(String roomName, RoomCode code);

    /**
     * adds an element (a GameObject) to the given room. An element describes
     * which object is populating the room at it's initial state, and where it
     * is located.
     * 
     * @param roomName
     *            name of the room to modify
     * @param className
     *            name of the class of object to add to the room. Must be a
     *            GameObject
     * @param x
     *            initial x coordinate of the GameObject within the room
     * @param y
     *            initial y coordinate of the GameObject within the room
     * @return true if the operation was successful (rooName must exist,
     *         className must describs a valid class)
     */
    boolean roomAddElement(String roomName, String className, double x, double y);

    /**
     * takes a snapshot of the current situation. Each GameObject that currently
     * exist is added as element to the given room. Keep in mind that for each
     * GameObject, only the class and position is saved. Every other information
     * is not saved.
     * 
     * @param roomName
     *            name of the room to put the current situation of GameObjects
     * @return true if the operation was successful (roomName must be an
     *         existing room)
     */
    boolean roomAddSituation(String roomName);

    /**
     * copies all the elements contained in roomToCopyFrom to roomToCopyTo. At
     * the end, roomToCopyTo will be a merged version of the two initial rooms.
     * Creation codes and other codes are untouched.
     * 
     * @param roomToCopyTo
     *            destination room, the place where to copy the elements
     * @param roomToCopyFrom
     *            source room, the room containing the elements that must be
     *            copied
     * @param keepOriginalDimension
     *            if this is set to true, roomToCopyTo will keep its original
     *            dimensions, otherwise it will get roomToCopyFrom's dimensions
     * @return true if the operation was successful (rooms must be already
     *         existing)
     */
    boolean roomMerge(String roomToCopyTo, String roomToCopyFrom, boolean keepOriginalDimension);

    /**
     * removes every element from roomName, making the room empty. This does not
     * affect the creation code and other room parameters.
     * 
     * @param roomName
     *            name of the room to clear
     * @return true if the operation was successful (roomName must be existing)
     */
    boolean roomClear(String roomName);

    /**
     * clears the scene from every non-persistent GameObject (their destroy()
     * event is NOT called), then loads the GameObjects contained in the room
     * roomName. Keep in mind that loading a room is not immediate, objects of
     * the new room will start to exist from the next call of the update()
     * event. If you need to do something with the objects of the new room, use
     * a Creation Code. It is called right after the new room has been loaded.
     * 
     * @param roomName
     *            name of the room to load
     * @return true if the operation was successful (roomName must be an
     *         existing room)
     */
    boolean roomGoto(String roomName);

    /**
     * loads every GameObject contained in the room roomName. Their create()
     * event is called. The objects already in the current scene will not be
     * removed. Keep in mind that loading a room is not immediate, objects of
     * the new room will start to exist from the next call of the update()
     * event. If you need to do something with the objects of the new room, use
     * a Creation Code. It is called right after the new room has been loaded.
     * 
     * @param roomName
     *            name of the room to load
     * @return true if the operation was successful (roomName must be an
     *         existing room)
     */
    boolean roomLoad(String roomName);

    /**
     * restarts the current room. Basically, it goes to the current room. Take
     * care for persistent objects, they may duplicate themself. Also, keep in
     * mind that restarting a room is not immediate, objects of the new room
     * will start to exist from the next call of the update() event. If you need
     * to do something with the objects of the new room, use a Creation Code. It
     * is called right after the new room has been loaded.
     * 
     * @return true if restart was successful
     */
    boolean roomRestart();

    /**
     * returns the width of the given room.
     * 
     * @param roomName
     *            name of the room to check
     * @return the width of the room, or 0 if no room exist with that name
     */
    double roomGetWidth(String roomName);

    /**
     * returns the height of the given room.
     * 
     * @param roomName
     *            name of the room to check
     * @return the height of the room, or 0 if no room exist with that name
     */
    double roomGetHeight(String roomName);

    /**
     * returns the number of elements contained in the given room.
     * 
     * @param roomName
     *            name of the room to check
     * @return the number of elements contained in the given room, or 0 if no
     *         room exist with that name
     */
    int roomGetNumberOfElements(String roomName);

    /**
     * returns the name of the last room loaded with roomGoto() or roomLoad().
     * 
     * @return the name of the current room, or an empty string if a room has
     *         never been loaded before
     */
    String roomCurrent();

    /**
     * checks if a room with the given name exists (is defined).
     * 
     * @param roomName
     *            name of the room to check
     * @return true if roomName is the name of an existing room
     */
    boolean roomExists(String roomName);

    /**
     * returns the width of the current room.
     * 
     * @return the width of the current room
     */
    double roomWidth();

    /**
     * returns the height of the current room.
     * 
     * @return the height of the current room
     */
    double roomHeight();

    /**
     * lambda to work with room creation codes.
     */
    @FunctionalInterface
    interface RoomCode {
        /**
         * This method contains the code that must be executed each time the
         * room is loaded.
         * 
         * @param roomName
         *            contains name of the room being loaded
         */
        void execute(String roomName);
    }
}
