package todo.view.rooms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import todo.controller.OptimizationGoal;
import todo.controller.RoomController;
import todo.controller.events.EventManager;
import todo.controller.events.ExecutionErrorEvent;
import todo.controller.events.ParsingErrorEvent;
import todo.controller.events.PathologicalInputEvent;
import todo.controller.events.SuccessfulExecutionEvent;
import todo.controller.events.WrongOutputEvent;
import todo.view.drawables.level.ui.dialogs.DialogResponse;
import todo.view.drawables.level.ui.dialogs.GameDialogImpl;
import todo.view.menu.MenuObserver;
import todo.view.screens.RoomScreen;
import todo.vm.parser.ParserException;
import todo.vm.parser.Span;

public class RoomEventListenerImpl implements RoomEventListener {
    private static final String RED = "#CC0404";
    private static final String GREEN = "#157115";
    private final RoomController roomController;
    private final MenuObserver menuObserver;
    private final RoomScreen roomScreen;
    private final List<Runnable> deferredRunnables;

    public RoomEventListenerImpl(final RoomController roomController, final RoomScreen roomScreen,
            final MenuObserver menuObserver, final EventManager manager) {
        this.roomController = Objects.requireNonNull(roomController);
        this.roomScreen = Objects.requireNonNull(roomScreen);
        this.menuObserver = Objects.requireNonNull(menuObserver);
        this.deferredRunnables = new ArrayList<>();
        manager.listen(WrongOutputEvent.class, deferred(this::handleWrongOutput));
        manager.listen(ExecutionErrorEvent.class, deferred(this::handleExecutionError));
        manager.listen(SuccessfulExecutionEvent.class, deferred(this::handleSuccessfulExecution));
        manager.listen(PathologicalInputEvent.class, deferred(this::handlePathologicalInput));
        manager.listen(ParsingErrorEvent.class, this::handleParsingError);
    }

    @Override
    public void animationsCompleted() {
        this.deferredRunnables.forEach(action -> {
            action.run();
        });
        this.deferredRunnables.clear();
    }

    private void handleWrongOutput(final WrongOutputEvent event) {
        this.roomScreen.showDialog("The output you provided is not the one expected.", r -> {
            this.roomController.onStop();
        }, GameDialogImpl.ButtonColor.RED, DialogResponse.OK);
    }

    private void handleExecutionError(final ExecutionErrorEvent event) {
        this.roomScreen.showDialog("An error occured during your program execution:\n" + event.getMessage(), r -> {
            this.roomController.onStop();
        }, GameDialogImpl.ButtonColor.RED, DialogResponse.OK);
    }

    private void handlePathologicalInput(final PathologicalInputEvent event) {
        this.roomScreen.showDialog(
                "While your solution works for this input, it fails with other randomly generated ones.\n"
                        + "The input that caused the failure will be used for the next run.",
                r -> {
                    this.roomController.onStop();
                }, GameDialogImpl.ButtonColor.RED, DialogResponse.OK);
    }

    private void handleSuccessfulExecution(final SuccessfulExecutionEvent event) {
        final String instructions = "Number of instructions: " + formatOptimizationGoal(event.getInstructionsGoal());
        final String steps = "Steps taken: " + formatOptimizationGoal(event.getStepsGoal());
        this.roomScreen.showDialog(
                "Congratulations, your program works!\n\nOptional challenges:\n" + instructions + "\n" + steps, r -> {
                    this.roomController.onStop();
                    if (r == DialogResponse.GO_TO_MENU) {
                        this.menuObserver.openMenuScreen();
                    }
                }, GameDialogImpl.ButtonColor.GREEN, DialogResponse.OK, DialogResponse.GO_TO_MENU);
    }

    private void handleParsingError(final ParsingErrorEvent event) {
        final ParserException exc = event.getException();
        // Build the source code with the error spans highlighted
        final String source = exc.getSource();
        final StringBuilder annotatedSource = new StringBuilder();
        int lastIndex = 0;
        for (final Span span : exc.getSpans()) {
            annotatedSource.append(source.substring(lastIndex, span.getStart()));
            annotatedSource.append("[" + RED + "]" + source.substring(span.getStart(), span.getEnd()) + "[]");
            lastIndex = span.getEnd();
        }
        annotatedSource.append(source.substring(lastIndex));
        // Show the dialog
        this.roomScreen.showDialog("There is an error in the program you're trying to copy:\n" + exc.getMessage()
                + "\n\n" + annotatedSource, r -> {
                    // Do nothing.
                }, GameDialogImpl.ButtonColor.RED, DialogResponse.OK);
    }

    private <T> Consumer<T> deferred(final Consumer<T> toDecorate) {
        return arg -> this.deferredRunnables.add(() -> toDecorate.accept(arg));
    }

    private String formatOptimizationGoal(final OptimizationGoal goal) {
        return (goal.isReached() ? "[" + GREEN + "]good!" : "[" + RED + "]not optimal") + "[] (" + goal.getResult()
                + "/" + goal.getTarget() + ")";
    }
}
