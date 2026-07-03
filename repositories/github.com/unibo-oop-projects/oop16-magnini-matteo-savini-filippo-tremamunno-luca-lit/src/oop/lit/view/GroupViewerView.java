package oop.lit.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import oop.lit.model.ElementGroupModel;
import oop.lit.model.GroupViewerModel;
import oop.lit.model.SelectableElementGroupModel;
import oop.lit.util.Observer;
import oop.lit.view.controller.selectionandaction.AbstractSelectionAndActions;

/**
 * A class used to update, in accordance to the Observer pattern, changes with regards to the number of
 * element groups (both selectable and non-selectable) displayed in the Elements TabPane.
 */
public class GroupViewerView implements Observer {

    private final TabPane tabPane;
    private final GroupViewerModel groupViewer;
    private final Map<SelectableElementGroupModel, SelectableElementGroupView> selectableMap = new HashMap<>();
    private final Map<ElementGroupModel, ElementGroupView> nonSelectableMap = new HashMap<>();
    private final AbstractSelectionAndActions select;

    /**
     * Public constructor for initializing the TabPane graphical element and the GroupViewerModel
     * field. It also attaches the observer (the class itself) to the latter.
     * 
     * @param tabPane
     *         the pane holding the tabs where the elements groups are displayed
     * @param groupViewer
     *         the object holding the groups that need to be shown
     * @param select
     *         the instance of Selection that gives the class access to the method that allows
     *         us to attach the relative EventHandler
     */
    public GroupViewerView(final TabPane tabPane, final GroupViewerModel groupViewer, final AbstractSelectionAndActions select) {
        this.tabPane = tabPane;
        this.groupViewer = groupViewer;
        this.groupViewer.attach(this);
        this.select = select;
        this.groupViewer.getNonSelectableGroups().forEach(this::addEGM);
        this.groupViewer.getSelectableGroups().forEach(this::addSelectableEGM);
    }

    // Il metodo si comporta in modo molto simile al notifyChange della classe ElementGroupView. Anche qui
    // comincio il lavoro su copie di collezioni, in questo caso gli entry set di GroupViewer e delle mappe
    // di gruppi di elementi, selezionabili e non. Tramite istruzioni condizionali cerco discrepanze nella
    // corrispondenza biunivoca che dovrebbe sempre essere presente tra GroupViewer e mappe e se ne trovo
    // aggiorno l'uno o le altre tramite metodi privati appositi.
    @Override
    public void notifyChange() {
        final Set<ElementGroupModel> nonSelectFromViewer = this.groupViewer.getNonSelectableGroups();
        final Set<SelectableElementGroupModel> selectFromViewer = this.groupViewer.getSelectableGroups();
        final Set<ElementGroupModel> nonSelectFromMap = new HashSet<>(this.nonSelectableMap.keySet());
        final Set<SelectableElementGroupModel> selectFromMap = new HashSet<>(this.selectableMap.keySet());

        Set<ElementGroupModel> changedNonSelectable = new HashSet<>(nonSelectFromViewer);
        changedNonSelectable.removeAll(nonSelectFromMap);
        for (final ElementGroupModel egm : changedNonSelectable) {
            this.addEGM(egm);
        }
        changedNonSelectable = new HashSet<>(nonSelectFromMap);
        changedNonSelectable.removeAll(nonSelectFromViewer);
        for (final ElementGroupModel egm : changedNonSelectable) {
            this.removeEGM(egm);
        }

        Set<SelectableElementGroupModel> changedSelectable = new HashSet<>(selectFromViewer);
        changedSelectable.removeAll(selectFromMap);
        for (final SelectableElementGroupModel egm : changedSelectable) {
            this.addSelectableEGM(egm);
        }
        changedSelectable = new HashSet<>(selectFromMap);
        changedSelectable.removeAll(selectFromViewer);
        for (final SelectableElementGroupModel egm : changedSelectable) {
            this.removeSelectableEGM(egm);
        }
    }

    // Metodo privato per aggiungere un gruppo di elementi di gioco all'interfaccia grafica 
    // dell'applicazione ed il suo relativo Tab al Tab Panel degli elementi.
    private void addEGM(final ElementGroupModel element) {
        final ElementGroupView egv = new ElementGroupView(element);
        egv.initTab();
        final Tab tab = egv.getTab(); 
        this.tabPane.getTabs().add(tab);
        this.nonSelectableMap.put(element, egv);
        tab.setOnClosed(e -> {
            this.nonSelectableMap.remove(element);
            this.groupViewer.stopShowing(element);
            egv.removed();
        });
    }

    // Metodo privato per rimuovere un gruppo di elementi di gioco dall'interfaccia grafica 
    // dell'applicazione ed il suo relativo Tab dal Tab Panel degli elementi.
    private void removeEGM(final ElementGroupModel element) {
        final ElementGroupView temp = this.nonSelectableMap.remove(element);
        temp.closeTab();
    }

    // Metodo privato per aggiungere un gruppo selezionabile all'interfaccia grafica dell'applicazione 
    // ed il suo relativo Tab al Tab Panel degli elementi.
    private void addSelectableEGM(final SelectableElementGroupModel element) {
        final SelectableElementGroupView segv = new SelectableElementGroupView(element, select);
        segv.initTab();
        final Tab tab = segv.getTab();
        this.tabPane.getTabs().add(tab);
        this.selectableMap.put(element, segv);
        tab.setOnClosed(e -> {
            this.nonSelectableMap.remove(element);
            this.groupViewer.stopShowing(element);
            segv.removed();
        });
    }

    // Metodo privato per rimuovere un gruppo selezionabile dall'interfaccia grafica dell'applicazione 
    // ed il suo relativo Tab dal Tab Panel degli elementi.
    private void removeSelectableEGM(final SelectableElementGroupModel element) {
        final SelectableElementGroupView temp = this.selectableMap.remove(element);
        temp.closeTab();
    }

}
