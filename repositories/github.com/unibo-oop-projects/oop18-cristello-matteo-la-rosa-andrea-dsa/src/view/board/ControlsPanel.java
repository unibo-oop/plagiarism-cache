package view.board;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.JButton;
import javax.swing.JPanel;
import controller.DSAController;
import model.utils.Direction;
import model.utils.PossibleActionAfterMove;
import view.DimensionUtils;

/**
 * This class menage the JPanel that contain buttons.
 */
public class ControlsPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JButton buttonTODEEP = new ActionButtonImpl("Go deep sea", Direction.DEEP.toString());

    private final JButton buttonTOBOAT = new ActionButtonImpl("Return to boat", Direction.TO_BOAT.toString());

    private final JButton buttonTORELEASE = new ActionButtonImpl("Release a treasure and pass turn",
            PossibleActionAfterMove.RELEASE.toString());

    private final JButton buttonGETTEASURE = new ActionButtonImpl("Pick Up treasure and pass turn",
            PossibleActionAfterMove.PICK_UP.toString());

    private final JButton buttonENDTURN = new ActionButtonImpl("Pass turn",
            PossibleActionAfterMove.PASS_TURN.toString());

    private final DSAController dsaController;

    private static final Integer X = 5; 
    private static final Integer Y = 40; 

    /**
     * @param dsaController
     *                          The controller added to the panel to control action.
     */

    public ControlsPanel(final DSAController dsaController) {
        super();
        this.dsaController = dsaController;
        this.setLayout(new FlowLayout(FlowLayout.CENTER, DimensionUtils.xForScreenDimension(X), DimensionUtils.yForScreenDimension(Y))); // create dimension app
        this.add(this.buttonTODEEP);
        this.add(this.buttonTOBOAT);
        this.add(this.buttonGETTEASURE);
        this.add(this.buttonTORELEASE);
        this.add(this.buttonENDTURN);

        IntStream.range(0, this.getComponentCount()).forEach(x -> this.getComponent(x).setEnabled(false));

        final ActionListener actionListenerButtonTODEEP = e -> {
            dsaController.movePlayer(Direction.DEEP);
        };

        final ActionListener actionListenerButtonTOBOAT = e -> {
            dsaController.movePlayer(Direction.TO_BOAT);
        };

        final ActionListener actionListenerButtonTORELEASE = e -> {
            new SelectTileToRemove(
                    this.dsaController.getPlayerInTurn().getPlayerTreasures().stream()
                            .collect(Collectors.toList()),
                    this.dsaController);
        };

        final ActionListener actionListenerButtonTOGETTREASURE = e -> {
            dsaController.manageAction(PossibleActionAfterMove.PICK_UP);
        };

        final ActionListener actionListenerButtonTOENDTURN = e -> {
            dsaController.manageAction(PossibleActionAfterMove.PASS_TURN);
        };

        this.buttonTODEEP.addActionListener(actionListenerButtonTODEEP);
        this.buttonTOBOAT.addActionListener(actionListenerButtonTOBOAT);
        this.buttonGETTEASURE.addActionListener(actionListenerButtonTOGETTREASURE);
        this.buttonTORELEASE.addActionListener(actionListenerButtonTORELEASE);
        this.buttonENDTURN.addActionListener(actionListenerButtonTOENDTURN);

    }

    /**
     * This method is a getter for all the button list.
     * 
     * @return una lista con tutti i bottoni presenti nel JPanel
     */

    public List<Component> getAllButtonList() {
        return Arrays.asList(this.getComponents());
    }

    /**
     * 
     */
    public final void setOffAll() {
        IntStream.range(0, this.getComponentCount()).forEach(x -> this.getComponent(x).setEnabled(false));
    }

    /**
     * @param setButton
     *                      The list of button to set On (Pushable).
     */
    public final void setOnSpecific(final List<String> setButton) {
        this.setOffAll();
        Arrays.asList(this.getComponents()).stream()
                .map(x -> (JButton) x)
                .forEach(x -> {
                    if (setButton.contains(x.getActionCommand())) {
                        x.setEnabled(true);
                    }

                });
    }

}
