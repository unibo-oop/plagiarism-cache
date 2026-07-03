package org.gitgud.application.overview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gitgud.application.utils.ApplicationUtils;
import org.gitgud.icons.IconType;
import org.gitgud.utils.Pair;
import org.gitgud.utils.Utils;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 */
public class OverviewFxmlController implements OverviewView {

    private static final String LOCAL_GROUP_NAME = "local";
    private static final String ACTIVE_CLASS = "active";
    private static final String DISABLED_CLASS = "disabled";

    private static final Image FOLDER_OPEN_ICON = ApplicationUtils.createImageIcon("folder-open",
            IconType.ICON_COLORED);
    private static final Image SHOW_ICON = ApplicationUtils.createImageIcon("show", IconType.ICON_LIGHT);
    private static final Image HIDE_ICON = ApplicationUtils.createImageIcon("hide", IconType.ICON_LIGHT);
    private static final Image CHECK_ICON = ApplicationUtils.createImageIcon("check", IconType.ICON_LIGHT);
    private static final Image MINUS_ICON = ApplicationUtils.createImageIcon("minus", IconType.ICON_LIGHT);
    private static final Image PLUS_ICON = ApplicationUtils.createImageIcon("plus", IconType.ICON_LIGHT);
    private static final Image CLOUD_DOWN_ICON = ApplicationUtils.createImageIcon("cloud-up", IconType.ICON_LIGHT);
    private static final Image CLOUD_UP_ICON = ApplicationUtils.createImageIcon("cloud-down", IconType.ICON_LIGHT);

    private static final Insets GROUP_ICON_INSETS = new Insets(2.5, 6.0, 0.0, 0.0);
    private static final Insets BRANCH_ICON_INSETS = new Insets(2.5, 6.0, 0.0, 0.0);

    private static final int BRANCH_NOTIFICATION_ICON_SIZE = 14;

    private OverviewController ctrl;

    @FXML
    private Label lRepositoryName;
    @FXML
    private Pane branchesTabContent;
    @FXML
    private Pane remotesTabContent;
    @FXML
    private Pane tagsTabContent;

    @FXML
    private Pane branchTabHeader;
    @FXML
    private Pane remoteTabHeader;
    @FXML
    private Pane tagTabHeader;

    private final Map<String, Pane> branchGroups = new HashMap<>();
    private final Map<String, Pane> localBranches = new HashMap<>();

    private final Set<String> closedGroups = new HashSet<>();

    @Override
    public void addLocalBranch(final String branchName) {
        localBranches.put(branchName, addBranch(LOCAL_GROUP_NAME, branchName));
    }

    @Override
    public void addRemote(final String remoteName, final Pair<String, String> upstreams, final Color color) {
        final Label url = new Label();
        final ImageView link = ApplicationUtils.createIcon("fetch", IconType.ICON_LIGHT, 14);
        final MenuItem fetchMenu = new MenuItem("Fetch " + remoteName);
        final MenuItem editMenu = new MenuItem("Edit " + remoteName);
        final MenuItem removeMenu = new MenuItem("Remove " + remoteName);
        final SplitMenuButton smbRemote = new SplitMenuButton(fetchMenu, new SeparatorMenuItem(), editMenu, removeMenu);
        final Pane remoteLabel = new Pane(smbRemote);
        final Pane vbRemote = new HBox(url, remoteLabel);

        link.getStyleClass().add("overview-tab-branch-icon");
        HBox.setMargin(link, BRANCH_ICON_INSETS);
        smbRemote.setText(remoteName);
        url.setGraphic(link);
        url.setTooltip(new Tooltip("Fetch: " + upstreams.getX() + "\nPush: " + upstreams.getY()));
        url.getStyleClass().add("overview-remote-url");
        remoteLabel.setBackground(new Background(new BackgroundFill(color, new CornerRadii(3.0), Insets.EMPTY)));
        smbRemote.getStyleClass().add("overview-remote-title");
        vbRemote.getStyleClass().add("overview-tab-remote");

        fetchMenu.setOnAction(e -> Utils.doHardWork(() -> ctrl.fetchRemote(remoteName)));
        editMenu.setOnAction(e -> Utils.doHardWork(() -> ctrl.editRemote(remoteName)));
        removeMenu.setOnAction(e -> Utils.doHardWork(() -> ctrl.removeRemote(remoteName)));

        remotesTabContent.getChildren().add(vbRemote);
    }

    @Override
    public void addRemoteBranch(final String remoteName, final String branchName) {
        addBranch(remoteName, branchName);
    }

    @Override
    public void addTag(final String tagName) {
        final ImageView icon = ApplicationUtils.createIcon(SHOW_ICON, 12);
        final SplitMenuButton title = new SplitMenuButton();
        final HBox tag = new HBox(icon, title);

        HBox.setMargin(icon, BRANCH_ICON_INSETS);
        icon.setVisible(false);
        title.setText(tagName);
        title.getStyleClass().add("overview-tab-branch-title");
        tag.getStyleClass().add("overview-tab-branch");

        tag.hoverProperty().addListener((obs, o, n) -> {
            if (!tag.getStyleClass().contains(ACTIVE_CLASS)) {
                if (n) {
                    icon.setVisible(true);
                } else {
                    icon.setVisible(false);
                }
            }
        });

        icon.setOnMouseClicked(e -> {
            if (tag.getStyleClass().contains(DISABLED_CLASS)) {
                icon.setImage(SHOW_ICON);
                tag.getStyleClass().remove(DISABLED_CLASS);
                Utils.doHardWork(() -> ctrl.setTagEnabled(tagName, true));
            } else {
                icon.setImage(HIDE_ICON);
                tag.getStyleClass().add(DISABLED_CLASS);
                Utils.doHardWork(() -> ctrl.setTagEnabled(tagName, false));
            }
        });

        tagsTabContent.getChildren().add(tag);
    }

    @Override
    public void attachController(final OverviewController ctrl) {
        this.ctrl = ctrl;
    }

    @Override
    public void resetBranches() {
        branchesTabContent.getChildren().clear();
        branchGroups.clear();
        localBranches.clear();
    }

    @Override
    public void resetRemotes() {
        remotesTabContent.getChildren().clear();
    }

    @Override
    public void resetTags() {
        tagsTabContent.getChildren().clear();
    }

    @Override
    public void setCheckedOutBranch(final String branchName) {
        final Pane branch = localBranches.get(branchName);
        final ImageView icon = (ImageView) branch.getChildrenUnmodifiable().get(0);

        branch.getStyleClass().remove(DISABLED_CLASS);
        branch.getStyleClass().add(ACTIVE_CLASS);
        icon.setImage(CHECK_ICON);
        icon.setVisible(true);
    }

    @Override
    public void setRepositoryName(final String repositoryName) {
        ((ImageView) lRepositoryName.getGraphic()).setImage(FOLDER_OPEN_ICON);
        lRepositoryName.setText(repositoryName);

        closedGroups.clear();
        Arrays.asList(branchTabHeader, remoteTabHeader, tagTabHeader).forEach(p -> {
            final Pane tabContent = (Pane) p.getParent().getChildrenUnmodifiable().get(1);
            ApplicationUtils.setVisible(tabContent);
            p.getStyleClass().remove("closed");
        });
    }

    @Override
    public void setUpdates(final String branchName, final Pair<Integer, Integer> updates) {
        final Pane branch = localBranches.get(branchName);

        final Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        branch.getChildren().add(region);

        if (updates.getX() > 0) {
            final ImageView icon = ApplicationUtils.createIcon(CLOUD_DOWN_ICON, BRANCH_NOTIFICATION_ICON_SIZE);
            final Label number = new Label(String.valueOf(updates.getX()));
            number.setGraphic(icon);
            number.getStyleClass().add("overview-branch-notification");
            branch.getChildren().add(number);
        }
        if (updates.getY() > 0) {
            final ImageView icon = ApplicationUtils.createIcon(CLOUD_UP_ICON, BRANCH_NOTIFICATION_ICON_SIZE);
            final Label number = new Label(String.valueOf(updates.getY()));
            number.setGraphic(icon);
            number.getStyleClass().add("overview-branch-notification");
            branch.getChildren().add(number);
        }
    }

    private Pane addBranch(final String groupName, final String branchName) {
        Pane group = branchGroups.get(groupName);
        if (group == null) {
            group = addBranchGroup(groupName);
            branchGroups.put(groupName, group);
        }
        group = (Pane) group.getChildrenUnmodifiable().get(1);

        final ImageView icon = ApplicationUtils.createIcon(SHOW_ICON, 12);
        final SplitMenuButton title = new SplitMenuButton();
        final HBox branch = new HBox(icon, title);

        HBox.setMargin(icon, BRANCH_ICON_INSETS);
        icon.setVisible(false);
        title.setText(branchName);
        title.getStyleClass().add("overview-tab-branch-title");
        branch.getStyleClass().add("overview-tab-branch");

        if (groupName.equals(LOCAL_GROUP_NAME)) {
            title.getItems().addAll(createLocalBranchMenu(branchName));
        } else {
            title.getItems().addAll(createRemoteBranchMenu(groupName, branchName));
        }

        branch.hoverProperty().addListener((obs, o, n) -> {
            if (!branch.getStyleClass().contains(ACTIVE_CLASS)) {
                if (n) {
                    icon.setVisible(true);
                } else {
                    icon.setVisible(false);
                }
            }
        });

        icon.setOnMouseClicked(e -> {
            if (branch.getStyleClass().contains(ACTIVE_CLASS)) {
                return;
            }

            if (branch.getStyleClass().contains(DISABLED_CLASS)) {
                icon.setImage(SHOW_ICON);
                branch.getStyleClass().remove(DISABLED_CLASS);

                if (groupName.equals(LOCAL_GROUP_NAME)) {
                    Utils.doHardWork(() -> ctrl.setLocalBranchEnabled(branchName, true));
                } else {
                    Utils.doHardWork(() -> ctrl.setRemoteBranchEnabled(groupName, branchName, true));
                }
            } else {
                icon.setImage(HIDE_ICON);
                branch.getStyleClass().add(DISABLED_CLASS);

                if (groupName.equals(LOCAL_GROUP_NAME)) {
                    Utils.doHardWork(() -> ctrl.setLocalBranchEnabled(branchName, false));
                } else {
                    Utils.doHardWork(() -> ctrl.setRemoteBranchEnabled(groupName, branchName, false));
                }
            }
        });

        title.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                if (groupName.equals(LOCAL_GROUP_NAME)) {
                    Utils.doHardWork(() -> ctrl.checkoutLocal(branchName));
                } else {
                    Utils.doHardWork(() -> ctrl.checkoutRemote(groupName, branchName));
                }
            }
        });

        group.getChildren().add(branch);

        return branch;
    }

    private Pane addBranchGroup(final String groupName) {
        final boolean closed = closedGroups.contains(groupName);
        final ImageView icon = ApplicationUtils.createIcon(closed ? PLUS_ICON : MINUS_ICON, 12);
        final Label title = new Label(groupName);
        final HBox header = new HBox(icon, title);
        final VBox content = new VBox();
        final VBox group = new VBox(header, content);

        icon.getStyleClass().add("overview-tab-branch-icon");
        HBox.setMargin(icon, GROUP_ICON_INSETS);
        title.getStyleClass().add("overview-tab-group-title");
        header.getStyleClass().add("overview-tab-group");

        header.setOnMouseClicked(e -> {
            if (content.isVisible()) {
                ApplicationUtils.setInvisible(content);
                icon.setImage(PLUS_ICON);
                closedGroups.add(groupName);
            } else {
                ApplicationUtils.setVisible(content);
                icon.setImage(MINUS_ICON);
                closedGroups.remove(groupName);
            }
        });

        if (closed) {
            ApplicationUtils.setInvisible(content);
        }

        branchesTabContent.getChildren().add(group);
        branchGroups.put(groupName, group);

        return group;
    }

    private List<MenuItem> createLocalBranchMenu(final String branchName) {
        final MenuItem checkoutMenu = new MenuItem("Checkout " + branchName);
        final MenuItem upstreamMenu = new MenuItem("Set upstream");
        final MenuItem deleteMenu = new MenuItem("Delete " + branchName);

        checkoutMenu.setOnAction(e -> Utils.doHardWork(() -> ctrl.checkoutLocal(branchName)));
        upstreamMenu.setOnAction(e -> Utils.doHardWork(() -> ctrl.setUpstream(branchName)));
        deleteMenu.setOnAction(e -> Utils.doHardWork(() -> ctrl.deleteLocalBranch(branchName)));

        return Arrays.asList(checkoutMenu, new SeparatorMenuItem(), upstreamMenu, deleteMenu);
    }

    private List<MenuItem> createRemoteBranchMenu(final String remoteName, final String branchName) {
        final MenuItem checkoutMenu = new MenuItem("Checkout " + remoteName + "/" + branchName);
        final MenuItem copyMenu = new MenuItem("Copy local " + remoteName + "/" + branchName);
        final MenuItem renameMenu = new MenuItem("Rename " + remoteName + "/" + branchName);
        final MenuItem deleteMenu = new MenuItem("Delete " + remoteName + "/" + branchName);

        checkoutMenu.setOnAction(e -> Utils.doHardWork(() -> ctrl.checkoutLocal(branchName)));
        copyMenu.setOnAction(e -> Utils.doHardWork(() -> ctrl.checkoutRemote(remoteName, branchName)));
        renameMenu.setOnAction(e -> Utils.doHardWork(() -> ctrl.renameRemoteBranch(remoteName, branchName)));
        deleteMenu.setOnAction(e -> Utils.doHardWork(() -> ctrl.deleteRemoteBranch(remoteName, branchName)));

        return Arrays.asList(checkoutMenu, copyMenu, new SeparatorMenuItem(), renameMenu, deleteMenu);
    }

    @FXML
    private void toggleGroup(final MouseEvent event) { // NOPMD
        final Pane overviewTabHeader = (Pane) event.getSource();
        final Pane overviewTabContent = (Pane) overviewTabHeader.getParent().getChildrenUnmodifiable().get(1);

        if (overviewTabContent.isVisible()) {
            ApplicationUtils.setInvisible(overviewTabContent);
            overviewTabHeader.getStyleClass().add("closed");
        } else {
            ApplicationUtils.setVisible(overviewTabContent);
            overviewTabHeader.getStyleClass().remove("closed");
        }
    }

}
