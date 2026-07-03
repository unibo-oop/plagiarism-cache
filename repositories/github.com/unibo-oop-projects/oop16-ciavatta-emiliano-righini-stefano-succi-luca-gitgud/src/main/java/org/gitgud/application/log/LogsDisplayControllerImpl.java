package org.gitgud.application.log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.gitgud.application.WorkingAreaController;
import org.gitgud.application.utils.ApplicationUtils;
import org.gitgud.application.utils.ColorManager;
import org.gitgud.events.repository.RepositoryChangedListener;
import org.gitgud.events.repository.RepositoryUpdatedListener;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.Model;
import org.gitgud.model.branch.LogManager;
import org.gitgud.model.branch.LogManagerImpl;
import org.gitgud.model.commons.Commit;
import org.gitgud.utils.Pair;
import org.gitgud.utils.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 *
 */
public class LogsDisplayControllerImpl
        implements LogsDisplayController, RepositoryChangedListener, RepositoryUpdatedListener {

    private final Model model;
    private final WorkingAreaController parent;
    private LogsView view;
    private LogManager logManager;
    private ColorManager colorManager = ApplicationUtils.createColorManager();
    private Pane mainPane;
    private boolean ready = false; // NOPMD

    private int lineCounter = 0; // NOPMD

    private final List<Commit> commits = new LinkedList<>();
    // private Color lastCommitColor;

    // here branches IDs are stored with their name
    private final Map<String, String> branches = new HashMap<>();

    // for manage color selection in merge situations
    // private final Map<Color, Integer> colorPriorities = new HashMap<>();
    // not implemented yet

    // key are the branches, values are the colors
    private final Map<String, Color> branchColor = new HashMap<>();

    // mapping the color for each commit for future prints
    private final Map<String, Color> colorMap = new HashMap<>();

    // ---------------index---expected_commit-----------------------
    private final Map<Integer, String> columns = new HashMap<>();

    /**
     * Very often a column must remain free from commits and wait to be filled by a long vertical and curved link this
     * data structure stores these special column reservations.
     */// -------------Commit--column------------------------------------
    private final Map<String, List<Integer>> linkSpaceReservations = new HashMap<>();
    //

    // -----------terminal-commit------start-points(sons)_coordinates-------------------------
    private final Map<String, List<CanvasElement<Integer, Integer>>> links = new HashMap<>();

    private class CanvasElement<X, Y> extends Pair<X, Y> {

        private final Color color;

        CanvasElement(final X x, final Y y, final Color color) {
            super(x, y);
            this.color = color;
        }

        public Color getColor() {
            return this.color;
        }
    }

    /**
     * @param parent
     *            the parent.
     * @param model
     *            the model.
     */
    public LogsDisplayControllerImpl(final WorkingAreaController parent, final Model model) {
        this.parent = parent;
        this.model = model;

        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());

        try {
            mainPane = loader.load(getClass().getResourceAsStream("LogsView.fxml"));
            view = loader.getController();
            view.attachController(this);
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: LogsView.fxml");
        }
        colorManager = ApplicationUtils.createColorManager();
        VBox.setVgrow(mainPane, Priority.ALWAYS);
        this.model.getRepositoryModel().addRepositoryChangedListener(this);
        this.model.getRepositoryModel().addRepositoryUpdatedListener(this);
    }

    @Override
    public void addTag(final String branch) {
        parent.showWarningNotification("Operation not implemented", "EH! VOOOOLEVII!");
    }

    @Override
    public void createBranchFrom(final String branch) {
        parent.getBranchController().createBranch(branch);
    }

    @Override
    public void deleteBranch(final String branch) {
        parent.getBranchController().deleteLocalBranch(branch);
    }

    private Integer getKeyByValue(final String value, final Map<Integer, String> map) {
        return map.entrySet().stream().filter(e -> e.getValue().equals(value)).findAny().get().getKey();
    }

    private Integer getMaxCol() {
        return columns.keySet().stream().max((a, b) -> Integer.compare(a, b)).get();
    }

    @Override
    public Pane getPane() {
        return mainPane;
    }

    private void init() {
        /* here ask for commits */

        logManager = model.getBranchModel().getLogManager().setMaxCount(LogManagerImpl.STD_MAX_OUTPUT);
        model.getBranchModel().getLocalBranches().forEach(b -> {
            final String sha = logManager.getCommit(b).get().getID();
            if (!branchColor.containsKey(b)) {
                branchColor.put(b, colorManager.requestColor());
            }
            colorMap.put(sha, branchColor.get(b));
            logManager.markStart(b);
            branches.put(b, sha);
        });
        view.addColumn();
        columns.put(0, "empty"); // NOPMD
    }

    /* here assemble the info for print a new line with commit info */
    private void insertRecord(final Commit c) {
        final String color = "#" + Integer.toHexString(colorMap.get(c.getID()).hashCode());
        final List<String> branches = this.branches.entrySet().stream().filter(s -> s.getValue().equals(c.getID()))
                .map(s -> s.getKey()).collect(Collectors.toList());
        view.writeNewRecord(lineCounter, c.getID(), c.getShortMessage(), c.getAuthor().getName(), color, branches,
                Arrays.asList("genitore"));
    }

    // insert a new son who will need to be linked to a specific parent
    private void manageLink(final String son, final int sonCol, final int line, final String parent) {
        if (links.containsKey(parent)) {
            links.get(parent).add(new CanvasElement<>(sonCol, line, colorMap.get(son)));
        } else {
            links.put(parent, Arrays.asList(new CanvasElement<>(sonCol, line, colorMap.get(son))).stream()
                    .collect(Collectors.toList()));
        }
    }

    private void manageParents(final int sonCol, final int line, final List<String> parents) {
        final String son = columns.get(sonCol); // NOPMD
        columns.remove(sonCol);
        if (parents.isEmpty()) {
            columns.put(sonCol, "empty"); // initial commit
            return;
        }
        if (!colorMap.containsKey(parents.get(0))) {
            colorMap.put(parents.get(0), colorMap.get(son));
        }

        manageLink(son, sonCol, line, parents.get(0));
        if (columns.values().contains(parents.get(0))) {
            columns.put(sonCol, "link"); // column must be reserved for a possible long link (line+curve)/curve+line
            if (!linkSpaceReservations.containsKey(parents.get(0))) {
                linkSpaceReservations.put(parents.get(0), new LinkedList<>());
            }
            linkSpaceReservations.get(parents.get(0)).add(sonCol);
        } else {
            columns.put(sonCol, parents.get(0));
        }
        if (parents.size() > 1) {
            for (int i = 1; i < parents.size(); i++) {

                manageLink(son, sonCol, line, parents.get(i));
                if (!colorMap.containsKey(parents.get(i))) {
                    colorMap.put(parents.get(i), colorMap.get(son));
                }
                if (!columns.values().contains(parents.get(i))) {

                    if (columns.values().contains("empty")) {
                        final int newCol = getKeyByValue("empty", columns);
                        columns.put(newCol, parents.get(i));
                    } else {
                        view.addColumn();
                        columns.put(columns.keySet().stream().max((a, b) -> Integer.compare(a, b)).get() + 1,
                                parents.get(i));
                    }
                }

            }
        }

    }

    @Override
    public void mergeOnHead(final String branch) {
        parent.getMergeController().merge(branch);
    }

    @Override
    public void onContentUpdated() {
        // not-needed
    }

    private void onEndReached() {
        view.endLogs();
        // this.parent.showInfoNotification("Yaaaa Booooi!", "Congratulations! You reached the first commit! LOL no one
        // cares.");
    }

    @Override
    public void onManualUpdated() {
        Utils.updateView(() -> resetLogsDisplay());
    }

    @Override
    public void onRepositoryChanged() {
        Utils.updateView(() -> {
            colorManager = ApplicationUtils.createColorManager();
            branchColor.clear();
            resetLogsDisplay();
            if (!commits.isEmpty()) {
                parent.updateSelectedCommits(Arrays.asList(commits.get(0)));
            }
        });

    }

    @Override
    public void onRepositoryUpdated() {
        Utils.updateView(() -> resetLogsDisplay());
    }

    @Override
    public void openDiffOverview(final List<String> commits) {
        parent.updateSelectedCommits(this.commits
                .stream()
                .filter(c -> commits.contains(c.getID()))
                .sorted((c1, c2) -> c1.getWhen().compareTo(c2.getWhen()))
                .collect(Collectors.toList()));
        Utils.doHardWork(() -> parent.displayDiffOverviewFromLogs());
    }

    private void printCommit(final Commit c) {
        int col;
        view.addLine();
        // color already set for branches in init() for all other normal commits, their son already added them

        if (columns.values().contains(c.getID())) {
            col = getKeyByValue(c.getID(), columns);
        } else {
            if (columns.values().contains("empty")) {
                col = getKeyByValue("empty", columns);
                columns.put(col, c.getID()); /* this line prevents a bug in color management */
            } else { // il commit è inatteso e nessuna colonna è libera
                col = getMaxCol() + 1;
                columns.put(col, c.getID());
                view.addColumn();
            }
        }
        printLink(c.getID(), col);  // draw links previously decided
        view.drawCommit(new Pair<>(col, lineCounter), colorMap.get(c.getID()));
        manageParents(col, lineCounter++, c.getParentsId());
    }

    // draw any up-link for the specified commit , removes the scheduled link from the map right after
    private void printLink(final String name, final int col) {
        links.entrySet()
                .stream()
                .filter(k -> k.getKey().equals(name))
                .findAny()
                .map(p -> p.getValue())
                .ifPresent(l -> {
                    l.forEach(o -> {
                        final boolean longLinkMode = linkSpaceReservations.containsKey(name)
                                && linkSpaceReservations.get(name).stream().findAny().get() == col;
                        if (branches.values().contains(name) /* serve capire se il figlio è un merge */) { // it's a
                                                                                                           // branch.
                            view.drawLink(o, new Pair<>(col, lineCounter), longLinkMode, colorMap.get(name));
                        } else {
                            view.drawLink(o, new Pair<>(col, lineCounter), longLinkMode, o.getColor());
                        }
                    });
                    links.remove(name); // cleaning the entry after drawing
                    if (linkSpaceReservations.containsKey(name)) {
                        linkSpaceReservations.get(name).forEach(reservedColumn -> {
                            columns.put(reservedColumn, "empty");
                        });
                        linkSpaceReservations.remove(name);
                    }
                });
    }

    @Override
    public void refresh() {
        if (!ready) {
            init();
            ready = true;
        }
        final Iterator<Commit> it = logManager.getLogs();
        if (!it.hasNext()) {
            onEndReached();
        }
        it.forEachRemaining(commit -> {
            commits.add(commit);
            printCommit(commit);
            insertRecord(commit);
        });
    }

    @Override
    public void renameBranch(final String branch) {
        parent.getBranchController().renameLocalBranch(branch);
    }

    private void resetLogsDisplay() {
        branches.clear();
        commits.clear();
        columns.clear();
        lineCounter = 0;
        links.clear();
        linkSpaceReservations.clear();
        colorMap.clear();
        ready = false;
        view.resetView();
        refresh();
    }

    @Override
    public void sendWarningMessage(final String title, final String msg) {
        parent.showWarningNotification(title, msg);
    }
}
