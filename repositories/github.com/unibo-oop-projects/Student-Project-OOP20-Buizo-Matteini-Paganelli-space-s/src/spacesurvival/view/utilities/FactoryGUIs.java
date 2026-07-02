package spacesurvival.view.utilities;

import spacesurvival.model.EngineImage;
import spacesurvival.utilities.SoundUtils;
import spacesurvival.utilities.dimension.Screen;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;

/**
 * Implements utilities for creating and graphical positioning of GUIs components.
 */
public final class FactoryGUIs {

    /**
     * Min value for progress bar.
     */
    public static final int MIN_PROGRESS = 0;

    /**
     * Min value for progress bar.
     */
    public static final int MAX_PROGRESS = 100;

    /**
     * Step index.
     */
    public static final int STEP_INDEX = 1;

    /**
     * Size thickness H0.
     */
    public static final int THICKNESS_H0 = 3;

    /**
     * Insets size H0.
     */
    public static final int INSET_H0 = 100;

    /**
     * Insets size H1.
     */
    public static final int INSET_H1 = 80;

    /**
     * Insets size H2.
     */
    public static final int INSET_H2 = 10;

    /**
     * Insets size H3.
     */
    public static final int INSET_H3 = 5;

    /**
     * Insets size H4.
     */
    public static final int INSET_H4 = 3;

    /**
     * Create new JPanel transparent with LayoutManager.
     * @param layoutManager for JPanel layout.
     * @return panel transparent with LayoutManager.
     */
    public static JPanel createPanelTransparent(final LayoutManager layoutManager) {
        final JPanel panel = new JPanel(layoutManager);
        panel.setOpaque(false);
        return panel;
    }

    /**
     * Encapsulate the component in a JPanel with the flowLayout.
     * @param component encapsulated.
     * @return encapsulate is JPanel with encapsulated component.
     */
    public static JPanel encapsulatesInPanelFlow(final Component component) {
        final JPanel encapsulate = FactoryGUIs.createPanelTransparent(new FlowLayout());
        encapsulate.add(component);
        return encapsulate;
    }

    /**
     * Encapsulate the component in a JPanel with the orientation flowLayout.
     * @param orientation for FlowLayout.
     * @param component encapsulated.
     * @return encapsulate is JPanel with encapsulated component.
     */
    public static JPanel encapsulatesInPanelFlowOrientation(final int orientation, final Component component) {
        final FlowLayout flow = new FlowLayout();
        flow.setAlignment(orientation);

        final JPanel encapsulate = createPanelTransparent(flow);
        encapsulate.add(component);
        return encapsulate;
    }

    /**
     * Encapsulate a list of component in a JPanel with the flowLayout.
     * @param list is list of component
     * @return unionComponent is JPanel with encapsulated list of component.
     */
    public static JPanel createPanelFlowUnionComponents(final List<? extends Component> list) {
        final JPanel unionComponent =  FactoryGUIs.createPanelTransparent(new FlowLayout());
        for (final Component component : list) {
            unionComponent.add(component);
        }
        return unionComponent;
    }

    /**
     * Encapsulate a list of component in a JPanel with the borderLayout and orientation.
     * @param component encapsulated.
     * @param orientation in BorderLayout.
     * @return panel is JPanel with encapsulated component with orientation.
     */
    public static JPanel encapsulateInPanelBorderOrientation(final Component component, final String orientation) {
        final JPanel panel = FactoryGUIs.createPanelTransparent(new BorderLayout());
        panel.add(component, orientation);
        return panel;
    }

    /**
     * Encapsulate component in JPanel vertically aligned center.
     * @param component encapsulated.
     * @return encapsulate is JPanel with encapsulated component vertically aligned center.
     */
    public static JPanel encapsulateInPanelVerticalCenter(final Component component) {
        final JPanel encapsulate = FactoryGUIs.createPanelTransparent(null);
        encapsulate.setLayout(new BoxLayout(encapsulate, BoxLayout.X_AXIS));
        encapsulate.add(component);
        return encapsulate;
    }

    /**
     * Vertical encapsulation a list of component in JPanel.
     * @param components is list of component.
     * @param inset 
     * @return encapsulate
     */
    public static JPanel createPanelGridBagUnionComponentsVertical(final List<? extends  Component> components, final int inset) {
        final JPanel encapsulate = FactoryGUIs.createPanelTransparent(new GridBagLayout());
        final GridBagConstraints limit = createGBConstraintsBase();
        limit.insets = new Insets(inset / 2, inset, inset / 2, inset);

        for (final Component component : components) {
            encapsulate.add(component, limit);
            limit.gridy++;
        }
        return encapsulate;
    }

    /**
     * Vertical encapsulation a list of component in JPanel with layout GridBag and insets left-bottom.
     * @param components is list of component.
     * @param bottom insets bottom.
     * @param left insets left.
     * @return encapsulate is JPanel with components.
     */
    public static JPanel createPanelGridBagUnionComponentsVerticalInsetExternalSX(final List<? extends JComponent> components, final int bottom, final int left) {
        final JPanel encapsulate = FactoryGUIs.createPanelTransparent(new GridBagLayout());
        final GridBagConstraints limit = createGBConstraintsBase();
        limit.insets = new Insets(0, 0, 0, 0);

        for (final Component component : components) {
            if (components.indexOf(component) == components.size() - 1) {
                limit.insets = new Insets(0, left, bottom, 0);
            }
            encapsulate.add(component, limit);

            limit.gridy++;
        }

        return encapsulate;
    }

    /**
     * Horizontal encapsulation a list of component in JPanel with layout GridBag and insets.
     * @param components is list of component.
     * @param inset is size of insets.
     * @return encapsulate is JPanel with components
     */
    public static JPanel createPanelGridBagUnionComponentsHorizontal(final List<? extends  JComponent> components, final int inset) {
        final JPanel encapsulate =  FactoryGUIs.createPanelTransparent(new GridBagLayout());
        final GridBagConstraints limit = createGBConstraintsBase();
        limit.insets = new Insets(inset, inset / 2, inset, inset / 2);

        for (final Component component : components) {
            encapsulate.add(component, limit);
            limit.gridx++;
        }
        return encapsulate;
    }

    /**
     * Reset parameters of GridBagConstraints.
     * @param gridBagConstraints separator in Layout.
     */
    public static void resetGridBagConstraints(final GridBagConstraints gridBagConstraints) {
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
    }

    /**
     * Create GridBagConstraints with fill.
     * @param fill
     * @return gridBaag with fill.
     */
    public static GridBagConstraints createGBConstraintFill(final int fill) {
        final GridBagConstraints gridBaag = new GridBagConstraints();
        gridBaag.insets = new Insets(FactoryGUIs.INSET_H4, FactoryGUIs.INSET_H4, FactoryGUIs.INSET_H4, FactoryGUIs.INSET_H4);
        FactoryGUIs.resetGridBagConstraints(gridBaag);
        gridBaag.fill = fill;
        return gridBaag;
    }

    /**
     * Create default GridBagConstraints. 
     * @return default GridBagConstraints.
     */
    public static GridBagConstraints createGBConstraintsBase() {
        return FactoryGUIs.createGBConstraintFill(GridBagConstraints.HORIZONTAL);
    }

    /**
     * Create template GridBagConstraints for title.
     * @param separationTitle is size to separate from title.
     * @return gridBaag is template GridBagConstraints for title.
     */
    public static GridBagConstraints createGBConstraintsWithSpaceTitle(final int separationTitle) {
        final GridBagConstraints gridBaag = FactoryGUIs.createGBConstraintFill(GridBagConstraints.HORIZONTAL);

        gridBaag.ipady = separationTitle;
        return gridBaag;
    }

    /**
     * Create JComponent empty and transparent.
     * @return JComponent transparent and empty.
     */
    public static JComponent getJComponentEmpty() {
        return FactoryGUIs.createPanelTransparent(null);
    }

    /**
     * Set transparent JButton.
     * setOpaque to false
     * setContentAreaFilled to false
     * setFocusable to false
     * @param button
     */
    public static void setTransparentJButton(final JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
    }

    /**
     * Set icon in JButton from scale. 
     * @param button without icon.
     * @param pathIcon to image.
     * @param scaleOf is size for scale respect to.
     * @param respectTo is size for scale.
     */
    public static void setIconJButtonFromRate(final JButton button, final String pathIcon,
                                              final int scaleOf, final int respectTo) {
        final EngineImage engineImage = new EngineImage(scaleOf, respectTo, pathIcon);
        final JImage imag = new JImage(pathIcon, engineImage.getSize());
        button.setIcon(imag.getImageIcon());
    }

    /**
     * Set icon in JButton from scale. 
     * @param button without icon.
     * @param engineImage model of image for icon.
     */
    public static void setIconJButtonFromRate(final JButton button, final EngineImage engineImage) {
        FactoryGUIs.setIconJButtonFromRate(button, engineImage.getPath(),
                engineImage.getScaleOf(), engineImage.getRespectTo());
    }

    /**
     * Set default parameters for JSlider.
     * setOpaque to false
     * setMajorTickSpacing to DEFAULT_MAJOR_TICK_SPACING
     * setMinorTickSpacing to DEFAULT_MINOR_TICK_SPACING
     * setPaintTicks to true
     * setPaintLabels true
     * @param slider that set parameters.
     */
    public static void setDefaultJSlider(final JSlider slider) {
        slider.setOpaque(false);
        slider.setMajorTickSpacing(SoundUtils.DEFAULT_MAJOR_TICK_SPACING);
        slider.setMinorTickSpacing(SoundUtils.DEFAULT_MINOR_TICK_SPACING);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
    }

    /**
     * Set default parameters for JFrame.
     * setBounds to RECTANGLE_FULLSCREEN
     * setDefaultCloseOperation to EXIT_ON_CLOSE
     * setUndecorated to true
     * setResizable to false
     * setBackground to COLOR_OPACITY_BLACK
     * @param frame that set parameters.
     */
    public static void setDefaultJFrame(final JFrame frame) {
        frame.setBounds(Screen.RECTANGLE_FULLSCREEN);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setBackground(GraphicsLayoutUtils.COLOR_OPACITY_BLACK);
    }

    private FactoryGUIs() {
    }
}
