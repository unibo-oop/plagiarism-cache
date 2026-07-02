package hollowmen.model.roomentity.hero;

import java.util.Collection;
import java.util.Collections;

import hollowmen.model.Challenge;
import hollowmen.model.HeroClass;
import hollowmen.model.Information;
import hollowmen.model.Parameter;
import hollowmen.model.SkillTree;

/**
 * This class implements {@link HeroClass}
 * @author pigio
 *
 */
public class HeroClassImpl implements HeroClass{

	private Information info;
	
	private SkillTree skillTree;
	
	private Collection<Parameter> param;
	
	private Challenge challenge;
	
	
	public HeroClassImpl(Information info, SkillTree skillTree, Collection<Parameter> param, Challenge challenge) {
		this.info = info;
		this.skillTree = skillTree;
		this.param = param;
		this.challenge = challenge;
	}

	/**
	 * {@inheritDoc HeroClass}
	 */
	@Override
	public Information getInfo() {
		return this.info;
	}

	/**
	 * {@inheritDoc HeroClass}
	 */
	@Override
	public SkillTree getSkillTree() {
		return this.skillTree;
	}

	/**
	 * {@inheritDoc HeroClass}
	 */
	@Override
	public Collection<Parameter> getBaseParam() {
		return Collections.unmodifiableCollection(this.param);
	}

	/**
	 * {@inheritDoc HeroClass}
	 */
	@Override
	public Challenge getChallenge() {
		return this.challenge;
	}

	
}
