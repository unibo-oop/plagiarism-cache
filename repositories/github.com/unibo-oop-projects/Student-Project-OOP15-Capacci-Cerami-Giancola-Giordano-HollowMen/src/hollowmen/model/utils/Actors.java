package hollowmen.model.utils;

import java.util.Collection;

import hollowmen.enumerators.ActorState;
import hollowmen.model.Actor;
import hollowmen.model.Modifier;
import hollowmen.model.Parameter.ParamName;
import hollowmen.model.dungeon.ModifierImpl;
import hollowmen.utilities.ExceptionThrower;

public class Actors {

	private Actors() {};
	
	
	public static void addModifier(Actor subj, Modifier mod) throws IllegalArgumentException {
		ExceptionThrower.checkIllegalArgument(subj.getParameters(), 
				p -> !p.containsKey(mod.getParameter().getInfo().getName()));
		subj.getParameters().get(mod.getParameter().getInfo().getName()).addModifier(mod);
	}
	
	public static void removeModifier(Actor subj, Modifier mod) throws IllegalArgumentException {
		ExceptionThrower.checkIllegalArgument(subj.getParameters(), 
				p -> !p.containsKey(mod.getParameter().getInfo().getName()));
		subj.getParameters().get(mod.getParameter().getInfo().getName()).removeModifier(mod);
	}
	
	public static void addAllModifier(Actor subj, Collection<Modifier> mod) throws IllegalArgumentException {
		mod.stream().forEach(x -> addModifier(subj, x));
	}
	
	public static void removeAllModifier(Actor subj, Collection<Modifier> mod) throws IllegalArgumentException {
		mod.stream().forEach(x -> removeModifier(subj, x));
	}
	
	public static void regenerate(Actor subj) {
		subj.getStatus().clear();
		subj.getBody().setGravityScale(1f);
		subj.setState(ActorState.STANDING.toString());
		double maxHP = subj.getParameters().get(ParamName.HPMAX.toString()).getValue();
		Actors.addModifier(subj, new ModifierImpl(ParamName.HP.toString(), maxHP, Modifier.Operation.ADD));
	}
	
}
