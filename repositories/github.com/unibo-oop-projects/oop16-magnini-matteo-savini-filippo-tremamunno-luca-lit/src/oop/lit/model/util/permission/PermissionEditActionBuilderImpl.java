package oop.lit.model.util.permission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.actions.AbstractAction;
import oop.lit.model.util.permission.PermissionHolder.Permission;
import oop.lit.util.IllegalInputException;
import oop.lit.util.InputRequest;
import oop.lit.util.InputRequestsFactory;

/**
 * An implementation for a PermissionEditActinoBuilder, building a permission edit action used to edit the specified player actions.
 */
public class PermissionEditActionBuilderImpl implements PermissionEditActionBuilder {
    private final List<PermissionHolder> holders = new ArrayList<>();
    private final PlayerModel player;
    private boolean used; //= false

    /**
     * @param player
     *      the player this permission edit builder is about.
     */
    public PermissionEditActionBuilderImpl(final PlayerModel player) {
        this.player = player;
    }
    @Override
    public PermissionEditActionBuilder addPermissionHolder(final PermissionHolder holder) {
        this.holders.add(holder);
        return this;
    }
    @Override
    public Action build() {
        if (this.holders.isEmpty()) {
            throw new IllegalStateException("No permission holder set");
        }
        if (this.used) {
            throw new IllegalStateException("Builder was already used");
        }
        this.used = true;
        return new AbstractAction("Edit " + player.getName() + " permission") {
            private Optional<List<InputRequest<Permission>>> requests = Optional.empty();

            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.requests = Optional.of(
                    holders.stream().map(holder -> {
                        return irFactory.getChoiceInputRequest(
                            holder.getLabel(),
                            Arrays.asList(Permission.values()).stream()
                                .collect(Collectors.toMap(Permission::toString, Function.identity())),
                            Optional.of(holder.getPlayerPermission(player).toString())); })
                    .collect(Collectors.toList()));
                return new ArrayList<>(this.requests.get());
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                if (!this.requests.isPresent()) {
                    throw new IllegalInputException("This action needs input.");
                }
                if (!this.requests.get().stream().allMatch(request -> request.getStoredValue().isPresent())) {
                    throw new IllegalInputException("Insert all values");
                }
                for (int i = 0; i < holders.size(); i++) {
                    holders.get(i).setPlayerPermission(player, this.requests.get().get(i).getStoredValue().get());
                }
                this.requests = Optional.empty();
            }
        };
    }
}
