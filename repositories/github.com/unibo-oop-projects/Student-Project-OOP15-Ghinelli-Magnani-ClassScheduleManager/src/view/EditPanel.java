package view;

import java.awt.BorderLayout; 
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.Controller;
import model.interfaces.ILesson;
import view.utility.CareTaker;
import view.utility.Memento;
import view.utility.ObjectManager;
import view.utility.Originator;
import view.utility.Pair;

/**
 * 
 * A panel which manages all the editing of the table.
 *
 */

public class EditPanel extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = -2977645584952090514L;
    private final JTable table;
    private final JPanel editing = new JPanel();
    private final JPanel slots = new JPanel(new BorderLayout());
    private final JButton keep = new JButton("Keep");
    private final JButton delete = new JButton("Delete");
    private final JButton done = new JButton("Done");
    private final JButton slot1 = new JButton();
    private final JButton slot2 = new JButton();
    private transient Pair<Integer, Integer> cellCoordinates = new Pair<>(0, 0);
    private Object lessonSlot1;
    private Object lessonSlot2;
    private int searchType;
    private transient KeyEventDispatcher undoRedo;
    private final transient CareTaker careTaker = new CareTaker();
    private final transient Originator originator = new Originator();
    
    /**
     * Constructor of the panel, sets his buttons and the actionlistener of them.
     * @param actualTable The table of the main frame.
     * @param mainFrame The main frame of the program.
     */
    
    public EditPanel(final JTable actualTable, final IView mainFrame) {
        this.table = actualTable;
        this.setLayout(new BorderLayout());
        this.keep.addActionListener(e -> {
            final int rowValTmp = this.cellCoordinates.getX();
            final int colValTmp = this.cellCoordinates.getY();
            if (colValTmp == 0 && rowValTmp == 0) {
                Controller.getController().errorMessage("No element selected");
            } else {
                final Object lessonTmp = this.table.getValueAt(rowValTmp, colValTmp);
                if (lessonTmp instanceof ILesson) {
                    if (!this.slot1.isVisible()) {
                        this.originator.setState(new Pair<>(new Pair<>(lessonTmp, Optional.of(0)), new Pair<>(rowValTmp, colValTmp))); //
                        this.careTaker.add(originator.saveStateToMemento()); // memento
                        this.table.setValueAt("", rowValTmp, colValTmp);
                        this.slot1.setText(((ILesson) lessonTmp).getSubject().getName() + "/" + ((ILesson) lessonTmp).getProfessor().getName());
                        this.slot1.setVisible(true);
                        this.lessonSlot1 = (ILesson) lessonTmp;
                        this.cellCoordinates = new Pair<>(0, 0);
                    } else {
                        if (!this.slot2.isVisible()) {
                            this.originator.setState(new Pair<>(new Pair<>(lessonTmp, Optional.of(0)), new Pair<>(rowValTmp, colValTmp))); //
                            this.careTaker.add(originator.saveStateToMemento()); // memento
                            this.table.setValueAt("", rowValTmp, colValTmp);
                            this.slot2.setText(((ILesson) lessonTmp).getSubject().getName() + "/" + ((ILesson) lessonTmp).getProfessor().getName());
                            this.slot2.setVisible(true);
                            this.lessonSlot2 = (ILesson) lessonTmp;
                            this.cellCoordinates = new Pair<>(0, 0);
                        } else {
                            Controller.getController().errorMessage("You can't take another lesson, place one of which you have got at least!");
                        }
                    }
                } else {
                    Controller.getController().errorMessage("You can't take this element, it's not a lesson!");
                }
            }
        });
        this.slot1.addActionListener(e1 -> {
            final int rowVal = this.cellCoordinates.getX();
            final int colVal = this.cellCoordinates.getY();
            final Object lesson = this.table.getValueAt(rowVal, colVal);
            if (lesson instanceof ILesson) {
                Controller.getController().errorMessage("You can't place this lesson over another one!");
            } else {
                if (!lesson.toString().equals("")) {
                    Controller.getController().errorMessage("You can't place this lesson here!");
                } else {
                    this.originator.setState(new Pair<>(new Pair<>(lesson, Optional.of(1)), new Pair<>(rowVal, colVal))); //
                    this.careTaker.add(originator.saveStateToMemento()); // memento
                    this.table.setValueAt(ObjectManager.setNewLessonValues(this.searchType, rowVal, colVal, (ILesson) this.lessonSlot1), rowVal, colVal);
                    this.slot1.setVisible(false);
                    this.cellCoordinates = new Pair<>(0, 0);
                }
            }
        });
        this.slot2.addActionListener(e2 -> {
            final int rowVal = this.cellCoordinates.getX();
            final int colVal = this.cellCoordinates.getY();
            final Object lesson = this.table.getValueAt(rowVal, colVal);
            if (lesson instanceof ILesson) {
                Controller.getController().errorMessage("You can't place this lesson over another one!");
            } else {
                if (!lesson.toString().equals("")) {
                    Controller.getController().errorMessage("You can't place this lesson here!");
                } else {
                    this.originator.setState(new Pair<>(new Pair<>(lesson, Optional.of(2)), new Pair<>(rowVal, colVal))); //
                    this.careTaker.add(originator.saveStateToMemento()); // memento
                    this.table.setValueAt(ObjectManager.setNewLessonValues(this.searchType, rowVal, colVal, (ILesson) this.lessonSlot2), rowVal, colVal);
                    this.slot2.setVisible(false);
                    this.cellCoordinates = new Pair<>(0, 0);
                }
            }
        });
        this.keep.setEnabled(false);
        this.editing.add(keep);
        this.delete.addActionListener(e -> {
            final int rowVal = this.cellCoordinates.getX();
            final int colVal = this.cellCoordinates.getY();
            if (colVal == 0 && rowVal == 0) {
                Controller.getController().errorMessage("No element selected");
            } else {
                final Object lesson = this.table.getValueAt(rowVal, colVal);
                if (lesson instanceof ILesson) {
                    if (JOptionPane.showConfirmDialog((JFrame) mainFrame, "Are you sure to delete this lesson?") == JOptionPane.YES_OPTION) {
                        this.originator.setState(new Pair<>(new Pair<>(lesson, Optional.empty()), new Pair<>(rowVal, colVal))); //
                        this.careTaker.add(originator.saveStateToMemento()); // memento
                        this.table.setValueAt("", rowVal, colVal);
                    }
                } else {
                    Controller.getController().errorMessage("You can't delete this element, it's not a lesson!");
                }
            }
        });
        this.delete.setEnabled(false);
        this.editing.add(delete);
        this.done.addActionListener(e -> {
            if (this.slot1.isVisible() || this.slot2.isVisible()) {
                Controller.getController().errorMessage("You can't end the changements until you have placed every lesson taken!");
                return;
            }
            mainFrame.editMode(false);
            final List<ILesson> changements = new ArrayList<>();
            for (int i = 0; i < this.table.getColumnCount(); i++) {
                for (int y = 0; y < this.table.getRowCount(); y++) {
                    if (this.table.getValueAt(y, i) instanceof ILesson) {
                        changements.add((ILesson) this.table.getValueAt(y, i));
                    }
                }
            }
            this.careTaker.cleanMementoList();
            Controller.getController().setChangements(changements);
        });
        this.done.setEnabled(false);
        this.editing.add(done);
        this.slot1.setVisible(false);
        this.slot2.setVisible(false);
        this.slots.add(slot1, BorderLayout.NORTH);
        this.slots.add(slot2, BorderLayout.CENTER);
        this.add(editing, BorderLayout.NORTH);
        this.add(slots, BorderLayout.SOUTH);
    }
    
    /**
     * Method which enables or disables the button of editing and adds or removes the listeners for the mouse,
     * used to select the cells and for the keyboard used for the functions of undo and redo.
     * @param set Says if the panel change in edit mode or not.
     * @param actualSearchType Actual table's view type.
     */
    
    public void editMode(final boolean set, final int actualSearchType) {
        this.searchType = actualSearchType;
        this.keep.setEnabled(set);
        this.delete.setEnabled(set);
        this.done.setEnabled(set);
        this.table.setCellSelectionEnabled(set);
        if (set) {
            this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    cellCoordinates = new Pair<>(table.rowAtPoint(e.getPoint()), table.columnAtPoint(e.getPoint()));
                }
            });
            this.undoRedo = new KeyEventDispatcher() {
                
                private final Set<Integer> pressed = new HashSet<>();
                private final CareTaker redoCareTaker = new CareTaker();
                private Object mementoStateTmp;
                
                @Override
                public boolean dispatchKeyEvent(final KeyEvent e) {
                    if (e.getID() == KeyEvent.KEY_RELEASED) {
                        pressed.remove(e.getKeyCode());
                    }
                    if (e.getID() == KeyEvent.KEY_PRESSED) {
                        pressed.add(e.getKeyCode());
                        if (pressed.size() == 2) {
                            if (pressed.contains(KeyEvent.VK_CONTROL) && pressed.contains(KeyEvent.VK_Z)) {
                                if (careTaker.mementoListSize() == 0) {
                                    System.out.println("nessun undo possibile da fare");
                                    return false;
                                }
                                final Memento mementoTmp = careTaker.get(careTaker.mementoListSize() - 1);
                                this.mementoStateTmp = table.getValueAt(mementoTmp.getState().getY().getX(), mementoTmp.getState().getY().getY());
                                originator.setState(new Pair<>(new Pair<>(this.mementoStateTmp, mementoTmp.getState().getX().getY()), new Pair<>(mementoTmp.getState().getY().getX(), mementoTmp.getState().getY().getY())));
                                this.redoCareTaker.add(originator.saveStateToMemento());
                                if (mementoTmp.getState().getX().getX() instanceof ILesson) {
                                    table.setValueAt(ObjectManager.setNewLessonValues(searchType, mementoTmp.getState().getY().getX(), mementoTmp.getState().getY().getY(), (ILesson) mementoTmp.getState().getX().getX()), mementoTmp.getState().getY().getX(), mementoTmp.getState().getY().getY());
                                } else {
                                    table.setValueAt(mementoTmp.getState().getX().getX(), mementoTmp.getState().getY().getX(), mementoTmp.getState().getY().getY());
                                }
                                careTaker.removeUsedMemento(careTaker.mementoListSize() - 1);
                                if (mementoTmp.getState().getX().getY().isPresent()) {
                                    if (mementoTmp.getState().getX().getY().get() == 0) {
                                        if (slot2.isVisible()) {
                                            slot2.setVisible(false);
                                        } else {
                                            if (slot1.isVisible()) {
                                                slot1.setVisible(false);
                                            }
                                        }
                                    } else {
                                        if (mementoTmp.getState().getX().getY().get() == 1) {
                                            slot1.setVisible(true);
                                        } else {
                                            slot2.setVisible(true);
                                        }
                                    }
                                }
                            }
                            if (pressed.contains(KeyEvent.VK_CONTROL) && pressed.contains(KeyEvent.VK_Y)) {
                                if (this.redoCareTaker.mementoListSize() == 0) {
                                    System.out.println("nessun redo possibile da fare");
                                    return false;
                                }
                                final Memento mementoTmp = this.redoCareTaker.get(this.redoCareTaker.mementoListSize() - 1);
                                this.mementoStateTmp = table.getValueAt(mementoTmp.getState().getY().getX(), mementoTmp.getState().getY().getY());
                                originator.setState(new Pair<>(new Pair<>(this.mementoStateTmp, mementoTmp.getState().getX().getY()), new Pair<>(mementoTmp.getState().getY().getX(), mementoTmp.getState().getY().getY())));
                                careTaker.add(originator.saveStateToMemento());
                                if (mementoTmp.getState().getX().getX() instanceof ILesson) {
                                    table.setValueAt(ObjectManager.setNewLessonValues(searchType, mementoTmp.getState().getY().getX(), mementoTmp.getState().getY().getY(), (ILesson) mementoTmp.getState().getX().getX()), mementoTmp.getState().getY().getX(), mementoTmp.getState().getY().getY());
                                } else {
                                    table.setValueAt(mementoTmp.getState().getX().getX(), mementoTmp.getState().getY().getX(), mementoTmp.getState().getY().getY());
                                }
                                this.redoCareTaker.removeUsedMemento(this.redoCareTaker.mementoListSize() - 1);
                                if (mementoTmp.getState().getX().getY().isPresent()) {
                                    if (mementoTmp.getState().getX().getY().get() == 0) {
                                        if (slot1.isVisible()) {
                                            slot2.setVisible(true);
                                        } else {
                                            slot1.setVisible(true);
                                        }
                                    } else {
                                        if (mementoTmp.getState().getX().getY().get() == 1) {
                                            slot1.setVisible(false);
                                        } else {
                                            slot2.setVisible(false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return false;
                }
            };
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(undoRedo);
        } else {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(undoRedo);
        }
    }

}
