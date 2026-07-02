package todo.view.drawables.level.ui;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Disposable;

import todo.vm.instructions.Instruction;

/**
 * This interface represents the program UI.
 */
public interface ProgramUI extends Disposable {
    /**
     * @return the drag and drop utility for handling the draggable actors
     */
    DragAndDrop getDragAndDrop();

    /**
     * Refresh the program UI and sync it with the loaded program.
     *
     * @param instructions is the list of instructions in the program
     */
    void refresh(List<Instruction> instructions);
}
