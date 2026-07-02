package hollowmen.model.roomentity.hero.skilltree;

import hollowmen.model.Information;
import hollowmen.model.SkillNode;
import hollowmen.model.utils.SimpleLimitedCounter;

/**
 * This class extends {@link SimpleLimitedCounter} and implements {@link SkillNode}<br>
 * This class hold the behavior of any {@code SkillNode}<br>
 * New SkillNode should extends this class and Override {@code addToValue()} and {@code subToValue} calling
 * {@code super.*ToValue()} and then adding their code
 * @author pigio
 *
 */
public class SkillNodeImpl extends SimpleLimitedCounter implements SkillNode{

	private Information info;
	private int nodeLevel;
	private String tag;
	
	
	public SkillNodeImpl(Information info, String tag, int level, double limit){
		super(0, limit);
		this.info = info;
		this.tag = tag;
		this.nodeLevel = level;
	}

	public SkillNodeImpl(Information info, String tag, int level) {
		this(info, tag, level, 1);
	}
	
	/**
	 * {@inheritDoc SkillNode}
	 */
	@Override
	public Information getInfo() {
		return info;
	}

	/**
	 * {@inheritDoc SkillNode}
	 */
	@Override
	public int getLevel() {
		return this.nodeLevel;
	}

	/**
	 * {@inheritDoc SkillNode}
	 */
	@Override
	public String getTag() {
		return this.tag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + nodeLevel;
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkillNodeImpl other = (SkillNodeImpl) obj;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (nodeLevel != other.nodeLevel)
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		return true;
	}

}
