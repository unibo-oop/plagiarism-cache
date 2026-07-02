package spacesurvival.view.help.concrete;

import spacesurvival.model.EngineImage;
import spacesurvival.model.gui.help.EngineHelp;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.AbstractGUI;
import spacesurvival.view.help.GUIHelp;
import spacesurvival.view.help.utilities.UnitHelp;
import spacesurvival.view.utilities.ButtonLink;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JLabel;

/**
 * Implement every element the help GUI must have.
 */
public class ConcreteHelpGUI extends AbstractGUI implements GUIHelp {
    private static final long serialVersionUID = 243122553613571377L;
    private final JLabel lbTitle;
    private final List<UnitHelp> unitHelps;
    private final ButtonLink btnBack;

    /**
     * Constructor of all help GUI items.
     */
    public ConcreteHelpGUI() {
        super();
        this.lbTitle = new JLabel();
        this.btnBack = new ButtonLink();
        this.unitHelps = Stream.generate(UnitHelp::new)
                .limit(EngineHelp.N_UNIT).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ButtonLink> getBtnActionLinks() {
        return List.of(this.btnBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActionBtnBack(final LinkActionGUI mainAction, final LinkActionGUI intoID) {
        this.btnBack.setCurrentLink(mainAction);
        this.btnBack.setNextLink(intoID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextUnit(final List<String> listName) {
        final AtomicInteger i = new AtomicInteger();
        this.unitHelps.forEach(unit -> unit.setTitleUnit(listName.get(i.getAndIncrement())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBtnText(final List<String> listName) {
        final int i = 0;
        this.btnBack.setText(listName.get(i));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTextAndIconInUnit(final String panelName, final List<EngineImage> engineImages) {
        this.unitHelps.stream().filter(unit -> unit.getTitleUnit().contentEquals(panelName))
                .forEach(unit -> engineImages.forEach(engine -> {
                    engine.setScale(engine.getScaleOf(), super.getWidth());
                    unit.addIconUnit(engine);
                }));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTitleGUI(final String title) {
        this.lbTitle.setText(title);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundGUI(final Color color) {
        this.lbTitle.setForeground(color);
        this.unitHelps.forEach(unit -> unit.setForegroundUnit(color));
        this.btnBack.setForeground(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontTitleGUI(final Font font) {
        this.lbTitle.setFont(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontGUI(final Font font) {
        this.unitHelps.forEach(unit -> unit.setFontTitleUnit(font));
        this.btnBack.setFont(font);
    }

    /**
     * Get label for title menu GUI.
     * @return label for title.
     */
    public JLabel getLabelTitle() {
        return this.lbTitle;
    }

    /**
     * Get back link button.
     * @return ButtonLink for link back.
     */
    public ButtonLink getBtnBack() {
        return this.btnBack;
    }

    /**
     * Get a list of unit help.
     * @return List of UnitHelp
     */
    public List<UnitHelp> getUnitHelps() {
        return this.unitHelps;
    }
}
