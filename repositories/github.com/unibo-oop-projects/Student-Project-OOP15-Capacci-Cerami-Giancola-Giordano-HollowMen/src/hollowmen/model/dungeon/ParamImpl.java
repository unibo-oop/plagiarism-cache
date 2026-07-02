package hollowmen.model.dungeon;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

import hollowmen.model.Information;
import hollowmen.model.Modifier;
import hollowmen.model.Parameter;
import hollowmen.model.utils.InformationUserImpl;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

/**
 * This class implements {@link Parameter}
 * @author pigio
 *
 */
public class ParamImpl extends InformationUserImpl implements Parameter{
	
	private Map<Pair<BinaryOperator<Double>, Double>, List<Modifier>> modifiersMap = new HashMap<>();
	
	private double baseValue;

	private double sum = 0;
	private double mul = 1;
	
	/**
	 * This constructor create a {@code Parameter} with no {@link Modifier}
	 * attached and with a base value
	 * @param info {@link Information}
	 * @param baseValue {@code double}
	 */
	public ParamImpl(Information info, double baseValue) {
		super(info);
		this.baseValue = baseValue;
	}
	
	/**
	 * This is a Cloner constructor
	 * @param param
	 */
	public ParamImpl(Parameter param) {
		super(param.getInfo());
		this.baseValue = param.getValue();
		param.getModifiers().stream().forEach(m -> this.addModifier(new ModifierImpl(m)));
	}
	
	/**
	 * @return (baseValue + all ADD {@code Modifier}) * all MUL {@code Modifier}
	 */
	@Override
	public double getValue() {
		return (baseValue + sum) * mul;
	}

	/**
	 * {@inheritDoc Parameter}
	 */
	@Override
	public void addModifier(Modifier mod) throws IllegalArgumentException, NullPointerException {
		ExceptionThrower.checkNullPointer(mod);
		ExceptionThrower.checkIllegalArgument(mod, m -> this.getInfo().getName().equals(m));
		this.getOrPrepareList(mod).add(mod);
		
		if(mod.getOperation().equals(Modifier.Operation.ADD.getOp())) {
			this.sum = mod.getOperation().apply(this.sum, mod.getParameter().getValue());
		}
		if(mod.getOperation().equals(Modifier.Operation.MUL.getOp())) {
			this.mul = mod.getOperation().apply(this.mul, mod.getParameter().getValue());
		}
	}

	/**
	 * {@inheritDoc Parameter}
	 */
	@Override
	public void removeModifier(Modifier mod) throws IllegalArgumentException, NullPointerException {
		ExceptionThrower.checkNullPointer(mod);
		ExceptionThrower.checkIllegalArgument(mod, m -> this.getInfo().getName().equals(m));
		this.getOrPrepareList(mod).remove(mod);
		
		if(mod.getOperation().equals(Modifier.Operation.ADD.getOp())) {
			this.sum = mod.getReverseOperation().apply(this.sum, mod.getParameter().getValue());
		}
		if(mod.getOperation().equals(Modifier.Operation.MUL.getOp())) {
			this.mul = mod.getReverseOperation().apply(this.mul, mod.getParameter().getValue());
		}
	}
	
	/**
	 * {@inheritDoc Parameter}
	 */
	@Override
	public Collection<Modifier> getModifiers() {
		Collection<Modifier> mod = new LinkedList<>();
		this.modifiersMap.entrySet().stream()
			.map(e -> e.getValue())
			.forEach(p -> mod.addAll(p));
		return mod;
	}
	
	private List<Modifier> getOrPrepareList(Modifier mod) {
		return this.modifiersMap.merge(this.keyGen(mod), new LinkedList<>(), (x, y) -> x);
	}
	
	
	private Pair<BinaryOperator<Double>, Double> keyGen(Modifier mod) {
		return new Pair<>(mod.getOperation(), mod.getParameter().getValue());
	}

	@Override
	public String toString() {
		return "Parameter : name "+ super.toString() + " Value : "+this.getValue();
	}

}
