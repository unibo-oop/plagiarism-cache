package hollowmen.model.dungeon.time;

import java.util.Collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import hollowmen.model.Actor;
import hollowmen.model.Information;
import hollowmen.model.Modifier;
import hollowmen.model.utils.Actors;

/**
 * This class represents a generic {@link Parameter} buff or debuff
 * STILL NOT IN GAME
 * @author pigio
 *
 */
public class GenericBuff {

	private Information info;
	private Multimap<String, Modifier> effect = ArrayListMultimap.create();
	private double duration;

	public GenericBuff(Information name, double duration, Collection<Modifier> stateEff) {
		this.info = name;
		this.duration = duration;
		stateEff.stream().forEach(m -> effect.put(m.getParameter().getInfo().getName(), m));
	}

	public Information getInfo() {
		return this.info;
	}

	public void attachTo(Actor actor) {
		try{
			effect.entries().stream().forEach(e -> Actors.addModifier(actor, e.getValue()));
		} catch (IllegalArgumentException e){}
		actor.getStatus().add(this.info);
		TimerSingleton.getInstance().register(actor, duration, 
				x -> effect.entries().stream().forEach(e -> Actors.removeModifier(x, e.getValue())));
	}
}

