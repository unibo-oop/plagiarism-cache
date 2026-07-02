package model.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.player.Player;
import model.skilltree.SkillTree;
import model.skilltree.SkillTreeAttribute;
import model.skilltree.SkillTreeImpl;

/**
 * The SkillTreeManagerImpl is a class that implements SkillTreeManager. It has
 * methods to get all the attributes of a Skilltree, to get the attributes that
 * can be updated and to upgrade a certain attribute of a given Skilltree.
 *
 */
public class SkillTreeManagerImpl implements SkillTreeManager {

    private final Map<Player, SkillTree> skilltrees;

    /**
     * Constructor.
     * 
     * @param players is a list of all players in the actual match.
     */
    public SkillTreeManagerImpl(final List<Player> players) {
        this.skilltrees = new HashMap<>();
        players.forEach(p -> {
            skilltrees.put(p, new SkillTreeImpl());
        });
    }

    private void verifyPlayer(final Player player) {
        if (!skilltrees.containsKey(player)) {
            throw new IllegalStateException();
        }
    }

    /** {@inheritDoc} **/
    @Override
    public List<SkillTreeAttribute> getAllPlayerAttributes(final Player player) {
        verifyPlayer(player);
        return (skilltrees.get(player).getAllAttributes());

    }

    /** {@inheritDoc} **/
    @Override
    public List<SkillTreeAttribute> getUpgradeblePlayerAttributes(final Player player) {
        verifyPlayer(player);
        return (skilltrees.get(player).getUpgradebleAttributes());
    }

    /** {@inheritDoc} **/
    @Override
    public void upgradePlayerAttribute(final Player player, final SkillTreeAttribute attribute) {
        skilltrees.get(player).upgradeAttribute(attribute);
    }

}
