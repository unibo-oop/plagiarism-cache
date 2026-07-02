package hollowmen.model.roomentity.hero.skilltree;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import hollowmen.model.SkillNode;
import hollowmen.model.SkillTree;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.Counter;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

/**
 * This class implements {@link SkillTree}<br>
 * NOTE: not in game but tested and work correctly
 * @author pigio
 *
 */
public class SkillTreeImpl implements SkillTree{
	
	private int skillPointUnspent;
		
	private Map<Pair<String, Integer>, Collection<SkillNode>> branchLevelNodes = new HashMap<>();
	
	//unlocked SkillNode
	private Map<String, SkillNode> allTargets = new HashMap<>();
	
	//the sum of all the Counter value in a branch
	private Map<String, Counter> pointsOnBranch = new HashMap<>();
	
	//the sum of all skill point for the branch in the level
	private Map<Pair<String, Integer>, Counter> pointsOnBranchLevel = new HashMap<>();
	
	/**
	 * This constructor build and empty {@code TargetPointSystem} with the given <b>allSkillNode</b>
	 * @param allSkillNode
	 */
	public SkillTreeImpl(Collection<SkillNode> allSkillNode) {
		this.initMaps(allSkillNode);
		allSkillNode.stream().forEach(s -> this.checkNewTargets(s));
		this.skillPointUnspent = 0;
	}
		
	/**
	 * This constructor build a {@code TargetPointSystem} with the given <b>allSkillNodeAndPoints</b> and set
	 * his unspent point using <b>pointUnspent</b>
	 * @param allSkillNodeAndPoints
	 * @param pointUnspent
	 */
	public SkillTreeImpl(Collection<Pair<SkillNode, Integer>> allSkillNodeAndPoints, int pointUnspent) {
		this(allSkillNodeAndPoints.stream().map(p -> p.getX()).collect(Collectors.toList()));
		allSkillNodeAndPoints.stream()
			.filter(p -> p.getX().getLevel() == 0)
			.forEach(p -> {
			for(int i = 0; i < p.getY(); i++) {
				this.spendPointOn(p.getX());
			}
		});
	}
	
	/**
	 * {@inheritDoc TargetPointSystem}
	 */
	@Override
	public Collection<SkillNode> getTargets() {
		return this.allTargets.entrySet().stream()
				.map(e -> e.getValue())
				.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc TargetPointSystem}
	 */
	@Override
	public int getUnspentPoint() {
		return this.skillPointUnspent;
	}

	/**
	 * {@inheritDoc TargetPointSystem}
	 */
	@Override
	public void addPoint(int pointToAdd) {
		this.skillPointUnspent += pointToAdd;
	}

	/**
	 * {@inheritDoc TargetPointSystem}
	 */
	@Override
	public Collection<SkillNode> getSkillNodes() {
		List<SkillNode> list = new LinkedList<>();
		this.branchLevelNodes.entrySet().stream()
				.forEach(e -> e.getValue().stream()
						.forEach(o -> list.add(o)));
		return list;	
	}

	/**
	 * {@inheritDoc TargetPointSystem}
	 */
	@Override
	public void retrievePointFrom(SkillNode target) throws IllegalArgumentException {
		ExceptionThrower.checkIllegalArgument(target, t -> !this.allTargets.containsKey(t.getInfo().getName()));
		retrieveUpdateRoutine(this.allTargets.get(target.getInfo().getName()), 1);
		checkMaxValueIntegrity(this.allTargets.get(target.getInfo().getName()).getTag(), 1);
		checkTreeIntegrity(this.allTargets.get(target.getInfo().getName()));
	}
	
	private void retrieveUpdateRoutine(SkillNode target, int value) {
		this.allTargets.get(target.getInfo().getName()).subToValue(value);
		this.pointsOnBranchLevel.get(keyGen(target)).sub(value);
		this.pointsOnBranch.get(target.getTag()).sub(value);
		this.skillPointUnspent += value;
		
	}
	
	
	private void checkMaxValueIntegrity(String tag, int change) {
		int previousLevel = (this.pointsOnBranch.get(tag).getCount() + change) / Constants.SKILLPOINTS_FORUPGRADE;
		if(previousLevel > this.maxLevelForTag(tag)) {
			this.removeTargetLevel(keyGen(tag, previousLevel));
		}
	}

	

	//Check if from the target below the sum of all previous skill points spent
	//(target included) are more than the skillpoints to unlock the next level
	//eventually remove all the nodes up target
	private void checkTreeIntegrity(SkillNode target) {
		int sumPreviousNodePoints = this.pointsOnBranchLevel.entrySet().stream()
				.filter(x -> x.getKey().getX().equals(target.getTag()))
				.filter(x -> x.getKey().getY() <= target.getLevel())
				.map(x -> x.getValue().getCount())
				.reduce((x,y) -> x + y).orElse(0);
		this.pointsOnBranchLevel.entrySet().stream()
				.filter(x -> x.getKey().getX().equals(target.getTag()))
				.filter(x -> x.getKey().getY() <= target.getLevel())
				.map(x -> x.getValue().getCount());
		if(sumPreviousNodePoints < (target.getLevel()+1) * Constants.SKILLPOINTS_FORUPGRADE ) {
			this.removeAllPointsFromLevelToLimit(keyGen(target));
		}
		
	}
		
	//This will remove all points from the level (NOT included) give to the end
	private void removeAllPointsFromLevelToLimit(Pair<String, Integer> levelToRemove) {
		this.branchLevelNodes.entrySet().stream()
			.filter(e -> e.getKey().getY() > levelToRemove.getY())
			.map(e -> e.getKey())
			.forEach(x -> this.removeTargetLevel(x));
	}
	
	//This will remove the target level, retrieving all his points and
	//removing all this level SkillNode from the allTargets Map
	private void removeTargetLevel(Pair<String, Integer> levelToRemove) {
		this.branchLevelNodes.get(levelToRemove).stream()
			.forEach(s -> {
				if(this.allTargets.containsKey(s.getInfo().getName())){
					this.retrieveUpdateRoutine(s, (int) s.getValue());
					this.allTargets.remove(s.getInfo().getName());
				}
			});
	}
	
	/**
	 * {@inheritDoc TargetPointSystem}
	 */
	@Override
	public void spendPointOn(SkillNode target) throws IllegalStateException, IllegalArgumentException {
		ExceptionThrower.checkIllegalArgument(target, t -> !this.allTargets.containsKey(t.getInfo().getName()));
		ExceptionThrower.checkIllegalState(this, s -> s.getUnspentPoint() <= 0);
		spendUpdateRoutine(this.allTargets.get(target.getInfo().getName()), 1);
		checkNewTargets(this.allTargets.get(target.getInfo().getName()));
	}
	
	private void spendUpdateRoutine(SkillNode target, int value) {
		this.allTargets.get(target.getInfo().getName()).addToValue(value);
		this.pointsOnBranchLevel.get(keyGen(target)).add(value);
		this.pointsOnBranch.get(target.getTag()).add(value);
		this.skillPointUnspent-= value;
	}
	
	
	private void checkNewTargets(SkillNode target) {
		if(this.pointsOnBranch.get(target.getTag()).getCount() % Constants.SKILLPOINTS_FORUPGRADE == 0) {
			int levelToUnlock = this.maxLevelForTag(target.getTag());
			this.branchLevelNodes.get(keyGen(target.getTag(), levelToUnlock)).stream()
				.forEach(s -> allTargets.put(s.getInfo().getName(), s));
		}
	}
	
	
	private Collection<SkillNode> getOrPrepare(SkillNode node) {
		return this.branchLevelNodes.merge(keyGen(node), new LinkedList<>(), (x, y) -> x);
	}
	
	private Counter getOrPrepareBranch(SkillNode node) {
		return this.pointsOnBranch.merge(node.getTag(), new Counter(), (x, y) -> x);
	}
	
	private Counter getOrPrepareBranchLevel(SkillNode node) {
		return this.pointsOnBranchLevel.merge(keyGen(node), new Counter(), (x, y) -> x);
	}
	
	private void initMaps(Collection<SkillNode> skillNode) {
		skillNode.stream().forEach(x -> this.getOrPrepare(x).add(x));
		skillNode.stream().forEach(x -> this.getOrPrepareBranch(x));
		skillNode.stream().forEach(x -> this.getOrPrepareBranchLevel(x));
	}
	
	private int maxLevelForTag(String tag) {
		return this.pointsOnBranch.get(tag).getCount() / Constants.SKILLPOINTS_FORUPGRADE;
	}
	
	private Pair<String, Integer> keyGen(String s, Integer i) {
		return new Pair<>(s, i);
	}
	
	private Pair<String, Integer> keyGen(SkillNode sN) {
		return this.keyGen(sN.getTag(), sN.getLevel());
	}
	
}
