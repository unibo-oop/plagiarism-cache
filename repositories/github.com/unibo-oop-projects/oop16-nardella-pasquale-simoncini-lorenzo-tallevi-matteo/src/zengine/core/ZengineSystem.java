// this class has too much public methods for the same reason of its interface, but it will
// be splitted in two different components in the future, if necessary. One component for GameObjects,
// another component for rooms. For now, they work fine together.
package zengine.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import user.ZengineInitializer;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject.Executor;
import zengine.geometry.HitboxPoint;
import zengine.interfaces.GameFunctionsSystem;

final class ZengineSystem implements ZengineComponent, GameFunctionsSystem {

    /**
     * non-drawing methods that would not be callable inside the drawing event
     * can be called inside the drawing event, to avoid errors, without logger
     * warnings. Set this field to false to turn off this behavior. Drawing
     * methods can still be called only during draw events.
     */
    private static boolean allowCodeInsideDraw = true;

    private static ZengineSystem system = new ZengineSystem(); // = null;

    private final Set<String> validClassNames = new HashSet<String>();

    // list of gameObject sorted by depth. It is iterated during update event
    // and draw event.
    // Insertion and removal of GameObjects are buffered and synchronized to the
    // update() event.
    // Not synchronized with the draw event, so modifications to this list may
    // cause some frame skipping
    private final List<GameObject> instanceListDepth = new ArrayList<>();

    // This is a buffer that contains the list of changes to apply to the
    // instanceListDepth before the iteration of a new update() event
    private final List<ModificationTask> taskList = new LinkedList<>();

    // If someone requested to sort the instanceListDepth. The request will be
    // solved ASAP
    private boolean gottaSortInstanceListDepth; // = false;

    // List of GameObject ordered by classNames. Modifications to the
    // GameObjects existing into the game world are instantly backed by this
    // data structure, so it is safe to usie it to count the number of
    // GameObject, but do not iterate it! Use instanceListDepth to iterate,
    // since it is synchronized to the update() event
    private final Map<String, Set<GameObject>> instanceList = new HashMap<>();

    // shortcut
    private final String classLocation = Zengine.USR_CLASS_PATH;

    // list of maps sorted by roomName
    private final Map<String, Room> roomList = new HashMap<>();
    // last loaded room
    private String roomCurrentField = "";
    // size of last loaded room
    private double roomWidthField; // = 0;
    private double roomHeightField; // = 0;
    // room to be loaded at end of this update() event
    private Room pendingRoom; // = null;

    // these are true if the relative event is being run
    private boolean initializeEvent; // = false;
    private boolean createEvent; // = false;
    private boolean destroyEvent; // = false;
    private boolean updateEvent; // = false;
    private volatile boolean drawEvent; // = false;
    private boolean collisionEvent; // = false;
    private boolean mouseClickedEvent; // = false;

    // used in some warning messages
    // it hurts my feelings but pmd wants it
    private static final String DURING_THE_DRAW_EVENT = " during the draw event";

    private ZengineSystem() {
        // Exists only to defeat instantiation.
    }

    public static ZengineSystem getSystem() {
        return system;
    }

    @Override
    public ZengineComponent getComponent() {
        return getSystem();
    }

    @Override
    public void link() {
        // build some refs? maybe in future
    }

    @Override
    public void update() {
        updateAll();
    }

    @Override
    public void initialize() {
        ZengineLogger.getLogger().loggerMessage("executing ZengineInitializer.initialize()");
        initializeEvent = true;
        new ZengineInitializer().initialize();
        warnIfSomethingsWeird();
        initializeEvent = false;
    }

    @Override
    public void reinitialize() {
        ZengineAudio.getAudio().getComponent().reinitialize();
        ZengineAssets.getAssets().getComponent().reinitialize();
        ZengineGUI.getGUI().getComponent().reinitialize();
        ZengineInput.getInput().getComponent().reinitialize();
        ZengineLogger.getLogger().getComponent().reinitialize();

        flushResources();
        restoreDefaultValues();
        initialize();
    }

    @Override
    public void restoreDefaultValues() {
        roomCurrentField = "";
        roomWidthField = 0;
        roomHeightField = 0;
        pendingRoom = null;
    }

    private void warnIfSomethingsWeird() {
        if (roomGetNumberOfDefinedRooms() <= 0) {
            ZengineLogger.getLogger().loggerWarning("you have defined no rooms, is it okay?");
        }
        if (ZengineAssets.getAssets().fontGetNumberOfDefinedFonts() <= 0) {
            ZengineLogger.getLogger().loggerWarning("you have defined no fonts, is it okay?");
        }
    }

    private void flushResources() {
        ZengineLogger.getLogger().loggerMessage("clearing rooms...");
        roomList.clear();
    }

    @Override
    public boolean isInitializeEvent() {
        return initializeEvent;
    }

    @Override
    public boolean isCreateEvent() {
        return createEvent;
    }

    @Override
    public boolean isDestroyEvent() {
        return destroyEvent;
    }

    @Override
    public boolean isUpdateEvent() {
        return updateEvent;
    }

    @Override
    public boolean isCollisionEvent() {
        return collisionEvent;
    }

    @Override
    public boolean isDrawEvent() {
        return drawEvent;
    }

    @Override
    public boolean isMouseClickedEvent() {
        return mouseClickedEvent;
    }

    public void updateAll() {
        synchronized (this) {
            updateEvent = true;
            for (final GameObject a : instanceListDepth) {
                if (!a.isDead() && a.isActive() && !a.isFrozen()) {
                    a.update();

                    // collision event
                    // for each class of the list of collisions
                    collisionEvent = true;
                    // for each object of that class
                    for (final String classInteraction : a.getCollisionInteraction()) {
                        final Set<GameObject> set = getInstanceList(classInteraction);
                        // Exclude me, if necessary
                        set.remove(a);
                        for (final GameObject o : set) {
                            if (a.isColliding(o) && !a.isEthereal() && !o.isEthereal() && o.isActive()) {
                                a.collide(o);
                            }
                        }
                    }
                    collisionEvent = false;

                    // mouseClicked event
                    // for each registered mouse button
                    mouseClickedEvent = true;
                    for (final ZengineMouseConstant c : a.getMouseInteraction()) {
                        if (ZengineInput.getInput().mouseCheckPressed(c) && a.getHitbox().isTouching(
                                new HitboxPoint(ZengineInput.getInput().mouseX(), ZengineInput.getInput().mouseY()))) {
                            a.mouseClicked(c);
                        }
                    }
                    mouseClickedEvent = false;
                }
            }
            this.resolveTasks();
            updateEvent = false;
        }
    }

    public void drawAll() {
        // draw event

        // synchronized because draw event may be called by another thread
        // could be done better but it works for small games
        synchronized (this) {
            drawEvent = true;
            try {
                // safer to iterate over a copy of the list? generally yes but
                // isn't this synchronized?
                for (final GameObject a : instanceListDepth) {
                    if (!a.isDead() && a.isActive() && a.isVisible()) {
                        a.draw();
                    }
                }
            } catch (java.util.ConcurrentModificationException e) {
                ZengineLogger.getLogger().loggerMessage("draw event skipped to avoid concurrent modifications");
            }
            drawEvent = false;
        }
    }

    // should have been package private, but public is fine as well since the
    // class is package private
    /**
     * asks the System to sort its GameObjects as soon as possible.
     */
    public void sortInstanceListDepth() {
        gottaSortInstanceListDepth = true;
    }

    private void resolveTasks() {
        // update instanceListDepth with the modifications buffered in taskList,
        // also load an eventual pending room
        for (final ModificationTask task : taskList) {
            if (task.operationType) {
                instanceListDepth.add(task.targetObj);
            } else {
                instanceListDepth.remove(task.targetObj);
            }
        }
        if (gottaSortInstanceListDepth) {
            // not necessary anymore to chech if looper is not warming up, if
            // this method is called while synchronized(this)
            instanceListDepth.sort((g1, g2) -> g2.getDepth() - g1.getDepth());
            gottaSortInstanceListDepth = false;
        }
        taskList.clear();
        if (pendingRoom != null) {
            roomCurrentField = pendingRoom.getName();
            roomWidthField = pendingRoom.getWidth();
            roomHeightField = pendingRoom.getHeight();

            for (final RoomDescriptor d : pendingRoom.getElementsList()) {
                instanceCreate(d.getX(), d.getY(), d.getClassName());
                // fare invece una passata di instanceGenerate e poi
                // chiamare l'evento create() di tutti?
            }

            pendingRoom.executeCreationCode(roomCurrentField);
            pendingRoom = null;
        }
    }

    @Override
    public GameObject instanceCreate(final double x, final double y, final String className) {
        GameObject ob = null;
        final String completeClassName = classLocation + className;
        try {
            ob = (GameObject) Class.forName(completeClassName).newInstance();
        } catch (InstantiationException ex) {
            ZengineLogger.getLogger().loggerWarning("instantiation error for " + completeClassName);
        } catch (IllegalAccessException ex) {
            ZengineLogger.getLogger().loggerWarning("illegal access for " + completeClassName);
        } catch (ClassNotFoundException ex) {
            ZengineLogger.getLogger().loggerWarning("class not found: " + completeClassName);
        } catch (ClassCastException ex) {
            ZengineLogger.getLogger()
                    .loggerWarning("cannot instantiate: " + completeClassName + " because it's not a sublcass of GameObject");
        } catch (NoClassDefFoundError ex) {
            ZengineLogger.getLogger().loggerWarning("class definition not found: " + completeClassName);
        }
        return instanceAdd(x, y, ob);
    }

    @Override
    public <T extends GameObject> T instanceAdd(final double x, final double y, final T obj) {
        if (!isDrawEvent() || allowCodeInsideDraw) {
            if (obj != null) {
                final String className = this.instanceClassName(obj);
                updateExistingClassNames(className);
                final boolean riuscito = instanceList.get(className).add(obj);

                if (riuscito) {
                    obj.setX(x);
                    obj.setY(y);
                    createEvent = true;
                    obj.create();
                    createEvent = false;

                    taskList.add(new ModificationTask(true, obj));
                    sortInstanceListDepth();
                } else {
                    ZengineLogger.getLogger().loggerWarning("tried to add " + className + " twice");
                }
            }
            return obj;
        } else {
            ZengineLogger.getLogger().loggerWarning("trying to instantiate something inside the draw event: returning null");
            return null;
        }
    }

    private boolean updateExistingClassNames(final String className) {
        // if className is a valid class and it is not cointained in
        // instanceList, put it there
        // this is a bit different from classExists, classExists stores full
        // pathnames, this stores partial paths. Also, classExists does not
        // alloc a new HashSet

        // IMPORTANT call this before every instanceList.get() to be sure it
        // does not return null

        // returns true if className is valid and wasn't there
        if (classExists(className)) {
            if (!instanceList.containsKey(className)) {
                instanceList.put(className, new HashSet<>());
                ZengineLogger.getLogger().loggerMessage("created entry for " + className);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean instanceRemove(final GameObject obj, final boolean performDestroy, final boolean persistentsAsWell) {
        // same as instanceDestroy, but you can choose if to perform the destroy
        // event
        if (!isDrawEvent() || allowCodeInsideDraw) {
            if (instanceExists(obj)) {
                if (!obj.isPersistent() || persistentsAsWell) {
                    final String className = instanceClassName(obj);

                    if (performDestroy) {
                        destroyEvent = true;
                        obj.destroy();
                        destroyEvent = false;
                    }
                    obj.setDead(true);

                    updateExistingClassNames(className);
                    instanceList.get(className).remove(obj);

                    final ModificationTask task = taskListElement(true, obj);
                    if (task != null) {
                        taskList.remove(task);
                    } else {
                        // in teoricamente ci dovrebbe essere solo una task
                        // che referenzia lo stesso obj
                        taskList.add(new ModificationTask(false, obj));
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            ZengineLogger.getLogger().loggerWarning("trying to remove instance during the draw event");
            return false;
        }
    }

    private boolean instanceRemoveAll(final String className, final GameObject ignoreMe, final boolean performDestroy,
            final boolean persistentsAsWell) {
        // same as instanceDestroyAll, but you can choose if to perform the
        // destroy event
        with(className, self -> {
            if (self != ignoreMe) {
                instanceRemove(self, performDestroy, persistentsAsWell);
            }
        });
        return !instanceExists(className);
    }

    private boolean instanceRemoveEverything(final GameObject ignoreMe, final boolean performDestroy,
            final boolean persistentsAsWell) {
        boolean ret = true;
        boolean prova = true;
        for (final String className : getLoadedClasses()) {
            prova = instanceRemoveAll(className, ignoreMe, performDestroy, persistentsAsWell);
            if (!prova) {
                ret = false;
            }
        }
        return ret;
    }

    @Override
    public boolean instanceDestroy(final GameObject obj) {
        return instanceRemove(obj, true, true);
    }

    @Override
    public boolean instanceDestroyAll(final String className, final GameObject ignoreMe) {
        return instanceRemoveAll(className, ignoreMe, true, true);
    }

    @Override
    public boolean instanceDestroyEverything(final GameObject ignoreMe) {
        return instanceRemoveEverything(ignoreMe, true, true);
    }

    @Override
    public boolean instanceExists(final GameObject obj) {
        if (obj == null) {
            return false;
        } else {
            final String className = instanceClassName(obj);
            if (updateExistingClassNames(className)) {
                // if updating the classNames a new entry is created, it means
                // the object could not exist before
                return false;
            } else {
                if (instanceExists(className)) {
                    // if the class is registered, check if the relative list
                    // contains it
                    return instanceList.get(className).contains(obj);
                } else {
                    return false;
                }
            }
        }
    }

    @Override
    public boolean instanceExists(final String className) {
        if (className == null) {
            return false;
        } else {
            return instanceNumber(className) > 0;
        }
    }

    @Override
    public int instanceNumberGeneral() {
        int count = 0;
        for (final Set<GameObject> c : instanceList.values()) {
            count += c.size();
        }
        return count;
    }

    @Override
    public int instanceNumber(final String className) {
        // if (classExists(className)) {
        // classExists() is already done in updateExistingClassNames
        updateExistingClassNames(className);
        final Set<GameObject> set = instanceList.get(className);
        if (set != null) {
            return set.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isInstanceOf(final GameObject obj, final String className) {
        return instanceClassName(obj).equals(className);
    }

    @Override
    public boolean instanceIsColliding(final GameObject obj1, final GameObject obj2) {
        return obj1.isColliding(obj2);
    }

    @Override
    public boolean instanceIsColliding(final GameObject obj, final String className) {
        boolean trovato = false;
        for (final GameObject o : getInstanceList(className)) {
            if (!o.equals(obj) && o.isColliding(obj)) {
                trovato = true;
                break;
            }
        }
        return trovato;
    }

    @Override
    public String instanceClassName(final GameObject obj) {
        String ret = obj.getClass().getCanonicalName();
        if (ret.startsWith(classLocation)) {
            ret = ret.substring(classLocation.length());
        } else {
            ret = "";
            ZengineLogger.getLogger().loggerWarning("cannot retrieve class for a class outside " + classLocation);
        }
        return ret;
    }

    // it is ok to suppress findbugs warnings since this method is just calling
    // System.exit(); the goal of the method is to shut down the application.
    // More advanced shutting down routine can be implemented later.
    @Override
    @SuppressFBWarnings()
    public int gameTerminate(final int exitCode) {
        System.exit(exitCode);
        return exitCode;
    }

    @Override
    public void gameRestart() {
        ZengineLogger.getLogger().loggerMessage("restarting game...");
        if (instanceRemoveEverything(null, false, true)) {
            // flush operations may go here
            ZengineLogger.getLogger().loggerMessage("cleared all objects");
            reinitialize();
        } else {
            ZengineLogger.getLogger().loggerError("unable to restart game");
        }
    }

    @Override
    public void with(final GameObject obj, final Executor e) {
        if (obj != null) {
            obj.execute(e, obj);
        }
    }

    @Override
    public void with(final String className, final Executor e) {
        for (final GameObject g : getInstanceList(className)) {
            with(g, e);
        }
    }

    private ModificationTask taskListElement(final boolean operationType, final GameObject targetObj) {
        // returns a task matching the given arguments or null if none is
        // found
        ModificationTask found = null;
        for (final ModificationTask p : taskList) {
            if (operationType == p.retrieveOperationType() && targetObj.equals(p.getTargetObj())) {
                found = p;
            }
        }
        return found;
    }

    private Set<GameObject> getInstanceList(final String className) {
        // returns a a set of GameObjects of the given class currently existing,
        // if class is invalid returns an empty set

        // invece di tornare sempre un nuovo set, considera un approcio
        // lazy
        if (instanceExists(className) && classExists(className)) {
            updateExistingClassNames(className);
            return new HashSet<>(instanceList.get(className));
        } else {
            return new HashSet<>();
        }
    }

    private Set<String> getLoadedClasses() {
        // returns a list of all classes loaded in the hasmap
        return instanceList.keySet();
    }

    @Override
    public boolean classExists(final String className) {
        final String completeClassName = classLocation + className;
        if (validClassNames.contains(completeClassName)) {
            return true;
        } else {
            try {
                final GameObject ob = (GameObject) Class.forName(completeClassName).newInstance();
                if (ob != null) {
                    validClassNames.add(completeClassName);
                    return true;
                } else {
                    return false;
                }
            } catch (InstantiationException ex) {
                ZengineLogger.getLogger().loggerWarning("instantiation error for " + completeClassName);
                return false;
            } catch (IllegalAccessException ex) {
                ZengineLogger.getLogger().loggerWarning("illegal access for " + completeClassName);
                return false;
            } catch (ClassNotFoundException ex) {
                ZengineLogger.getLogger().loggerWarning("class not found: " + completeClassName);
                return false;
            } catch (ClassCastException ex) {
                ZengineLogger.getLogger().loggerWarning(completeClassName + " is not a sublcass of GameObject");
                return false;
            } catch (NoClassDefFoundError ex) {
                ZengineLogger.getLogger().loggerWarning("class definition not found: " + completeClassName);
                return false;
            }
        }
    }

    @Override
    public GameObject instanceNearest(final double x, final double y, final String... classNames) {
        GameObject nearest = null;
        double record = Double.MAX_VALUE;
        double dist = 0;
        if (classNames.length > 0) {
            for (final String className : classNames) {
                if (classExists(className)) {
                    for (final GameObject self : getInstanceList(className)) {
                        dist = ZengineUtilities.getUtilities().pointDistance(x, y, self.getX(), self.getY());
                        if (dist < record) {
                            record = dist;
                            nearest = self;
                        }
                    }
                } else {
                    ZengineLogger.getLogger().loggerWarning("nonexistent class " + className);
                }
            }
        }
        return nearest;
    }

    @Override
    public GameObject instanceFurthest(final double x, final double y, final String... classNames) {
        GameObject furthest = null;
        double record = -1;
        double dist = 0;
        if (classNames.length > 0) {
            for (final String className : classNames) {
                if (classExists(className)) {
                    for (final GameObject self : getInstanceList(className)) {
                        dist = ZengineUtilities.getUtilities().pointDistance(x, y, self.getX(), self.getY());
                        if (dist > record) {
                            record = dist;
                            furthest = self;
                        }
                    }
                } else {
                    ZengineLogger.getLogger().loggerWarning("nonexistent class " + className);
                }
            }
        }
        return furthest;
    }

    @Override
    public boolean roomDefine(final String roomName, final int width, final int height) {
        if (isInitializeEvent()) {
            if (!roomExists(roomName)) {
                roomList.put(roomName, new Room(width, height, roomName));
                return true;
            } else {
                ZengineLogger.getLogger().loggerWarning("Warning: trying to redefine " + roomName);
                return false;
            }
        } else {
            ZengineLogger.getLogger().loggerWarning("Warning: cannot define rooms outside initializate event");
            return false;
        }
    }

    @Override
    public boolean roomAddElement(final String roomName, final String className, final double x, final double y) {
        if (!isDrawEvent() || allowCodeInsideDraw) {
            if (roomExists(roomName)) {
                if (classExists(className)) {
                    return roomList.get(roomName).getElementsList().add(new RoomDescriptor(className, x, y));
                } else {
                    ZengineLogger.getLogger()
                            .loggerWarning("Warning: trying to add invalid class " + className + " to " + roomName);
                    return false;
                }
            } else {
                warningNonexistentRoom(roomName);
                return false;
            }
        } else {
            warningModyfyRoomsOnDrawEvent(roomName);
            return false;
        }
    }

    @Override
    public boolean roomAddSituation(final String roomName) {
        if (!isDrawEvent() || allowCodeInsideDraw) {
            if (roomExists(roomName)) {
                final Set<String> classList = new HashSet<>(instanceList.keySet());
                for (final String className : classList) {
                    for (final GameObject o : getInstanceList(className)) {
                        roomAddElement(roomName, className, o.getX(), o.getY());
                    }
                }
                return true;
            } else {
                warningNonexistentRoom(roomName);
                return false;
            }
        } else {
            warningModyfyRoomsOnDrawEvent(roomName);
            return false;
        }
    }

    @Override
    public boolean roomMerge(final String roomToCopyTo, final String roomToCopyFrom, final boolean keepOriginalDimension) {
        if (!isDrawEvent() || allowCodeInsideDraw) {
            if (roomExists(roomToCopyTo)) {
                if (roomExists(roomToCopyFrom)) {
                    roomList.get(roomToCopyTo).getElementsList().addAll(roomList.get(roomToCopyFrom).getElementsList());
                    if (!keepOriginalDimension) {
                        roomList.get(roomToCopyTo).setWidth(roomList.get(roomToCopyFrom).getWidth());
                        roomList.get(roomToCopyTo).setHeight(roomList.get(roomToCopyFrom).getHeight());
                    }
                    return true;
                } else {
                    warningNonexistentRoom(roomToCopyFrom);
                    return false;
                }
            } else {
                warningNonexistentRoom(roomToCopyTo);
                return false;
            }
        } else {
            warningModyfyRoomsOnDrawEvent(roomToCopyTo);
            return false;
        }
    }

    @Override
    public boolean roomClear(final String roomName) {
        if (!isDrawEvent() || allowCodeInsideDraw) {
            if (roomExists(roomName)) {
                roomList.get(roomName).getElementsList().clear();
                return true;
            } else {
                warningNonexistentRoom(roomName);
                return false;
            }
        } else {
            ZengineLogger.getLogger().loggerWarning("trying to clear room " + roomName + DURING_THE_DRAW_EVENT);
            return false;
        }
    }

    @Override
    public boolean roomGoto(final String roomName) {
        if (!isDrawEvent() || allowCodeInsideDraw) {
            if (roomExists(roomName)) {
                // perform ending code before removing everything
                if (roomExists(roomCurrent()) && pendingRoom == null) {
                    roomList.get(roomCurrent()).executeEndingCode(roomCurrent());
                }
                instanceRemoveEverything(null, false, false);
                return roomLoad(roomName);
            } else {
                warningNonexistentRoom(roomName);
                return false;
            }
        } else {
            ZengineLogger.getLogger().loggerWarning("trying to go to room " + roomName + DURING_THE_DRAW_EVENT);
            return false;
        }
    }

    @Override
    public boolean roomLoad(final String roomName) {
        if (!isDrawEvent() || allowCodeInsideDraw) {
            if (roomExists(roomName)) {
                // the room is now pending
                pendingRoom = roomList.get(roomName);
                return true;
            } else {
                warningNonexistentRoom(roomName);
                return false;
            }
        } else {
            ZengineLogger.getLogger().loggerWarning("trying to load room " + roomName + DURING_THE_DRAW_EVENT);
            return false;
        }
    }

    @Override
    public double roomGetWidth(final String roomName) {
        if (roomExists(roomName)) {
            return roomList.get(roomName).getWidth();
        } else {
            warningNonexistentRoom(roomName);
            return 0;
        }
    }

    @Override
    public double roomGetHeight(final String roomName) {
        if (roomExists(roomName)) {
            return roomList.get(roomName).getHeight();
        } else {
            warningNonexistentRoom(roomName);
            return 0;
        }
    }

    @Override
    public int roomGetNumberOfElements(final String roomName) {
        if (roomExists(roomName)) {
            return roomList.get(roomName).getElementsList().size();
        } else {
            warningNonexistentRoom(roomName);
            return 0;
        }
    }

    @Override
    public String roomCurrent() {
        return roomCurrentField;
    }

    @Override
    public boolean roomExists(final String roomName) {
        return roomList.containsKey(roomName);
    }

    @Override
    public double roomWidth() {
        return roomWidthField;
    }

    @Override
    public double roomHeight() {
        return roomHeightField;
    }

    @Override
    public RoomCode roomSetCreationCode(final String roomName, final RoomCode code) {
        if (!isDrawEvent() || allowCodeInsideDraw) {
            if (roomExists(roomName)) {
                final RoomCode oldCode = roomList.get(roomName).getCreationCode();
                roomList.get(roomName).setCreationCode(code);
                return oldCode;
            } else {
                warningNonexistentRoom(roomName);
                return null;
            }
        } else {
            warningModyfyRoomsOnDrawEvent(roomName);
            return null;
        }
    }

    @Override
    public RoomCode roomSetEndingCode(final String roomName, final RoomCode code) {
        if (!isDrawEvent() || allowCodeInsideDraw) {
            if (roomExists(roomName)) {
                final RoomCode oldCode = roomList.get(roomName).getEndingCode();
                roomList.get(roomName).setEndingCode(code);
                return oldCode;
            } else {
                warningNonexistentRoom(roomName);
                return null;
            }
        } else {
            warningModyfyRoomsOnDrawEvent(roomName);
            return null;
        }
    }

    private int roomGetNumberOfDefinedRooms() {
        return roomList.size();
    }

    @Override
    public boolean roomRestart() {
        if (roomExists(roomCurrent())) {
            return roomGoto(roomCurrent());
        } else {
            ZengineLogger.getLogger().loggerWarning("could not restart the room because no existing room is active");
            return false;
        }
    }

    @Override
    public GameObject instanceGet(final String className) {
        if (instanceExists(className)) {
            final Set<GameObject> list = getInstanceList(className);
            if (!list.isEmpty()) {
                return list.iterator().next();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    // these are for pmd
    private void warningModyfyRoomsOnDrawEvent(final String roomName) {
        ZengineLogger.getLogger().loggerWarning("trying to modify room " + roomName + DURING_THE_DRAW_EVENT);
    }

    private void warningNonexistentRoom(final String roomName) {
        ZengineLogger.getLogger().loggerWarning("Warning: nonexistent room " + roomName);
    }

    /**
     * This class carries informations of how to modify the instanceListDepth,
     * between an update() and the other. The list of modification tasks will
     * patch the instanceListDepth to match the current status of GameObjects
     */
    private class ModificationTask {
        // true for add object, false for destroy object
        private final boolean operationType;
        private final GameObject targetObj;

        ModificationTask(final boolean operationType, final GameObject targetObj) {
            this.operationType = operationType;
            this.targetObj = targetObj;
        }

        public boolean retrieveOperationType() {
            return operationType;
        }

        public GameObject getTargetObj() {
            return targetObj;
        }
    }

    /**
     * This class describes a room, which wraps a collection of RoomDescriptors
     * and more properities.
     */
    private class Room {
        private final List<RoomDescriptor> elementsList;
        private final String name;
        private double width;
        private double height;
        private RoomCode creationCode;
        private RoomCode endingCode;

        Room(final double width, final double height, final String name) {
            this.width = ZengineUtilities.getUtilities().max(width, 0d);
            this.height = ZengineUtilities.getUtilities().max(height, 0d);
            this.elementsList = new ArrayList<>();
            this.name = name;
            this.creationCode = null;
            this.endingCode = null;
        }

        public void setWidth(final double width) {
            this.width = width;
        }

        public void setHeight(final double height) {
            this.height = height;
        }

        public double getWidth() {
            return width;
        }

        public double getHeight() {
            return height;
        }

        public List<RoomDescriptor> getElementsList() {
            return elementsList;
        }

        public RoomCode getCreationCode() {
            return creationCode;
        }

        public void setCreationCode(final RoomCode code) {
            this.creationCode = code;
        }

        public RoomCode getEndingCode() {
            return endingCode;
        }

        public void setEndingCode(final RoomCode code) {
            this.endingCode = code;
        }

        public String getName() {
            return name;
        }

        public void executeCreationCode(final String roomName) {
            if (creationCode != null) {
                creationCode.execute(roomName);
            }
        }

        public void executeEndingCode(final String roomName) {
            if (endingCode != null) {
                endingCode.execute(roomName);
            }
        }
    }

    /**
     * This class represents a GameObject in a room. A list of RoomDescriptors
     * can be used to determine which objects must be loaded and where.
     */
    private class RoomDescriptor {
        private final String className;
        private final double x;
        private final double y;

        RoomDescriptor(final String className, final double x, final double y) {
            this.className = className;
            this.x = x;
            this.y = y;
        }

        public String getClassName() {
            return className;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }
}
