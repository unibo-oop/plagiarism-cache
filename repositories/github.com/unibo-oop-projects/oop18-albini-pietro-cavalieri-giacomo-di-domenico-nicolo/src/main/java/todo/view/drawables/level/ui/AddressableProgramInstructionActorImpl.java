package todo.view.drawables.level.ui;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import todo.controller.RoomController;
import todo.utils.UIConstants;
import todo.vm.instructions.AddressableInstruction;
import todo.vm.instructions.Instruction;

/**
 * This class represents an implementation of the
 * {@link AddressableProgrmInstructionActor} interface.
 */
class AddressableProgramInstructionActorImpl extends BaseProgramInstructionActor
        implements AddressableProgramInstructionActor {
    private static final int MAX_VISIBLE_ADDRESSES = 4;
    private static final float FONT_TO_SELECTBOX_RATIO = 0.5f;
    private static final float SELECTBOX_PAD = 8f;
    private static final float SELECTBOX_WIDTH_RATIO = 0.25f;

    private final Function<Integer, AddressableInstruction> instructionFunction;
    private int address;
    private SelectBox<Integer> addressBox;
    private final List<Integer> addressSpace;

    AddressableProgramInstructionActorImpl(final AddressableInstruction instruction,
            final Function<Integer, AddressableInstruction> instructionFunction, final RoomController roomController,
            final ProgramUI programUI, final NinePatch image, final String text, final float width,
            final List<Integer> addressSpace) {
        super(instruction, roomController, programUI, image, UIConstants.ARIAL_FILE, text, width);
        this.instructionFunction = Objects.requireNonNull(instructionFunction);
        this.address = instruction.getMemoryAddress();
        this.addressSpace = Objects.requireNonNull(addressSpace);
        this.addressBox.setItems(this.addressSpace.toArray(new Integer[0]));
        this.addressBox.setSelectedIndex(this.address);
        this.addressBox.addListener(new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                final int selectedAddress = AddressableProgramInstructionActorImpl.this.addressBox.getSelectedIndex();
                AddressableProgramInstructionActorImpl.this.setMemoryAddress(selectedAddress);
            }
        });
    }

    @Override
    public InstructionActor copy() {
        return new AddressableProgramInstructionActorImpl((AddressableInstruction) super.getInstruction(),
                this.instructionFunction, super.getRoomController(), super.getProgramUI(), super.getImage(),
                super.getText(), super.getWidth(), this.addressSpace);
    }

    @Override
    public int getMemoryAddress() {
        return this.address;
    }

    @Override
    public void setMemoryAddress(final int address) {
        this.address = address;
        this.addressBox.setSelectedIndex(this.address);
        // Replace the instruction
        final Instruction newInstruction = this.instructionFunction.apply(address);
        super.getRoomController().replace(getInstruction(), newInstruction);
    }

    @Override
    protected TextButton additionalBuild(final TextButton btn, final float width) {
        // Get the font
        final int fontSize = (int) (width * BaseInstructionActor.WIDTH_HEIGHT_RATIO
                * BaseInstructionActor.FONT_HEIGHT_RATIO * FONT_TO_SELECTBOX_RATIO);
        final BitmapFont font = UIConstants.generateFont(UIConstants.ARIAL_FILE, Color.BLACK, fontSize);
        // Make the inner image with the address
        final NinePatchDrawable whiteDrawable = new NinePatchDrawable(UIConstants.WHITE_BUTTON_PATCH);
        final NinePatchDrawable scrollbarDrawable = new NinePatchDrawable(UIConstants.SCROLLBAR_PATCH);
        final NinePatchDrawable scrollbarKnobDrawable = new NinePatchDrawable(UIConstants.SCROLLBAR_KNOB_PATCH);
        // Make the select box
        final ScrollPaneStyle scrollStyle = new ScrollPaneStyle(whiteDrawable, scrollbarDrawable, scrollbarKnobDrawable,
                scrollbarDrawable, scrollbarKnobDrawable);
        final ListStyle listStyle = new ListStyle(font, Color.BLUE, Color.BLACK,
                new NinePatchDrawable(UIConstants.SELECTED_PATCH));
        final SelectBoxStyle selectBoxStyle = new SelectBoxStyle(font, Color.BLACK, whiteDrawable, scrollStyle,
                listStyle);
        this.addressBox = new SelectBox<>(selectBoxStyle);
        this.addressBox.setMaxListCount(MAX_VISIBLE_ADDRESSES);
        btn.add(this.addressBox).expand().pad(SELECTBOX_PAD).width(width * SELECTBOX_WIDTH_RATIO).center().right();
        return btn;
    }
}
