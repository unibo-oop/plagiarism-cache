package hollowmen.model;

import java.util.Collection;


/**
 * This interface represent an object that has {@link SkillBranch} and
 * can spend point on the {@link SkillNode}
 * @author pigio
 *
 */
public interface SkillTree extends TargetPointSystem<SkillNode>{

	/**
	 * This method gives all the {@code SkillNode} from this tree
	 * @return {@link Collection}<><br>
	 * NOTE : unmodifiable Collection
	 */
	public Collection<SkillNode> getSkillNodes();
	
}
