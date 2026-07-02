package model.card;

import static java.util.Map.entry;

import java.util.HashMap;
import java.util.Map;

import model.effects.*;

/**
 * A class implementing a card.
 * 
 * @author Ryan Perrina
 *
 */
public class Card {

	private static Map<String, Effect> cardEffectsMap = new HashMap<>(Map.ofEntries(entry("missed", new Missed()),
			entry("bang", new Bang()), entry("shofield", new Weapon(2, "shofield")),
			entry("remington", new Weapon(3, "remington")), entry("rev carabine", new Weapon(4, "rev carabine")),
			entry("winchester", new Weapon(5, "winchester")), entry("scope", new Scope()),
			entry("mustang", new ModifyRetreat(1)), entry("hideout", new ModifyRetreat(1)),
			entry("emporium", new Emporium()), entry("panic", new Panic()), entry("beer", new ModifyLifePoints(1)),
			entry("indians", new Indians()), entry("dodge", new Dodge()),
			entry("wells fargo", new DrawCardsFromDeck(3)), entry("stagecoach", new DrawCardsFromDeck(2)),
			entry("saloon", new Saloon(1)), entry("gatling", new Gatling()), entry("cat balou", new CatBalou()),
			entry("jail", new Jail()), entry("volcanic", new Weapon(1, "volcanic"))));

	private String localName;
	private String realName;
	private String cardId;
	private Color color;
	private Effect effect;

	public Card(final String cardId, final String color, final String localName, final String realName) {
		this.cardId = cardId;
		this.localName = localName;
		this.realName = realName;
		this.color = Color.valueOf(color.toUpperCase());
		this.effect = cardEffectsMap.get(realName);
	}

	/**
	 * Returns card id.
	 * 
	 * @return
	 */
	public String getId() {
		return this.cardId;
	}

	/**
	 * Returns card effect.
	 * 
	 * @return
	 */
	public Effect getEffect() {
		return this.effect;
	}

	/**
	 * Returns card color.
	 * 
	 * @return
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Returns card real name.
	 * 
	 * @return
	 */
	public String getRealName() {
		return this.realName;
	}

	/**
	 * Returns card local name.
	 * 
	 * @return
	 */
	public String getLocalName() {
		return this.localName;
	}
}
