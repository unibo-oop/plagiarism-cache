package hollowmen.model.item;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import hollowmen.model.HeroClass;
import hollowmen.model.Information;
import hollowmen.model.Item;
import hollowmen.model.Modifier;
import hollowmen.model.Parameter;
import hollowmen.utilities.ExceptionThrower;

/**
 * This class is a first implementation of {@link Item}
 * For instantiate this class use the static {@code builder()} method which return a {@link ItemBuilder}
 * @author pigio
 *
 */
public class ItemImpl implements Item{

	private Information info;
	
	private ItemState state;
	
	private Multimap<String, Modifier> mod = ArrayListMultimap.create();
	
	private int goldValue;
	
	private int rarity;
	
	private String slot;
	
	private String heroClassEquippable;
	
	private ItemImpl(Information info, ItemState state, Collection<Modifier> mod, int sellValue, int rarity,
			String slot, String heroClassEquippable) {
		this.info = info;
		this.state = state;
		mod.stream().forEach(m -> this.mod.put(m.getParameter().getInfo().getName(), m));
		this.goldValue = sellValue;
		this.rarity = rarity;
		this.slot = slot;
		this.heroClassEquippable = heroClassEquippable;
	}

	private ItemImpl() {};
	
	/**
	 * This method give an {@code ItemBuilder} that is the only way to get an instance
	 * @return {@link ItemBuilder}
	 */
	public static ItemBuilder builder() {
		return new Builder();
	}
	
	private static class Builder extends ItemImpl implements ItemBuilder {

		private Collection<Modifier> mod;
		private final Collection<String> heroClass = heroClassName();
		private final Collection<String> slotName = slotName();
		private final Collection<String> paramName = paramName();
		
		private Collection<String> heroClassName() {
			Collection<String> list = new LinkedList<>();
			for(HeroClass.Name h : HeroClass.Name.values()) {
				list.add(h.toString());
			}
			return list;
		}
		
		private Collection<String> slotName() {
			Collection<String> list = new LinkedList<>();
			for(Item.SlotName s : Item.SlotName.values()) {
				list.add(s.toString());
			}
			return list;
		}
		
		private Collection<String> paramName() {
			Collection<String> list = new LinkedList<>();
			for(Parameter.ParamName p : Parameter.ParamName.values()) {
				list.add(p.toString());
			}
			return list;
		}
		
		@Override
		public ItemBuilder info(Information info) {
			super.info = info;
			return this;
		}

		@Override
		public ItemBuilder state(ItemState state) {
			super.state = state;
			return this;
		}

		@Override
		public ItemBuilder modifier(Collection<Modifier> coll) {
			this.mod = coll;
			return this;
		}

		@Override
		public ItemBuilder value(int i) {
			super.goldValue = i;
			return this;
		}

		@Override
		public ItemBuilder rarity(int i) {
			super.rarity = i;
			return this;
		}

		@Override
		public ItemBuilder slot(String slot) {
			super.slot = slot;
			return this;
		}

		@Override
		public ItemBuilder heroClass(String className) {
			super.heroClassEquippable = className;
			return this;
		}

		
		private void check(Item i) throws IllegalStateException{
			ExceptionThrower.checkNullPointer(i.getInfo());
			ExceptionThrower.checkNullPointer(i.getState());
			ExceptionThrower.checkNullPointer(i.getModifiers());
			ExceptionThrower.checkNullPointer(i.getSlot());
			ExceptionThrower.checkNullPointer(i.getHeroClassEquippable());
			ExceptionThrower.checkIllegalState(i.getInfo(), x -> !x.getDescription().isPresent());
			ExceptionThrower.checkIllegalState(i.getModifiers(), m -> m.isEmpty());
			ExceptionThrower.checkIllegalState(i.getRarity(), x -> x <= 0);
			ExceptionThrower.checkIllegalState(i.getGoldValue(), x -> x <= 0);
			ExceptionThrower.checkIllegalState(i.getSlot(), x -> !this.slotName.contains(x));
			ExceptionThrower.checkIllegalState(i.getHeroClassEquippable(), x -> !this.heroClass.contains(x));
			ExceptionThrower.checkIllegalState(this.mod, 
					c -> !this.paramName.containsAll(c.stream().map(e -> e.getParameter().getInfo().getName())
							.collect(Collectors.toList())));
		}
		
		@Override
		public Item build() throws IllegalStateException{
			Item i = new ItemImpl(super.getInfo(), 
					super.getState(), this.mod, super.getGoldValue(), 
					super.getRarity(), super.getSlot(), super.getHeroClassEquippable());
			check(i);
			return i;
		}

		
	}
	
	/**
	 * {@inheritDoc Item}
	 */
	@Override
	public Information getInfo() {
		return this.info;
	}

	/**
	 * {@inheritDoc Item}
	 */
	@Override
	public ItemState getState() {
		return this.state;
	}
	
	/**
	 * {@inheritDoc Item}
	 */
	@Override
	public void setState(ItemState state) {
		this.state = state;
	}
	
	/**
	 * {@inheritDoc Item}
	 */
	@Override
	public Multimap<String, Modifier> getModifiers() {
		return this.mod;
	}

	/**
	 * {@inheritDoc Item}
	 */
	@Override
	public int getGoldValue() {
		return this.goldValue;
	} 

	/**
	 * {@inheritDoc Item}
	 */
	@Override
	public int getRarity() {
		return this.rarity;
	}

	/**
	 * {@inheritDoc Item}
	 */
	@Override
	public String getSlot() {
		return this.slot;
	}

	/**
	 * {@inheritDoc Item}
	 */
	@Override
	public String getHeroClassEquippable() {
		return this.heroClassEquippable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((heroClassEquippable == null) ? 0 : heroClassEquippable.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + rarity;
		result = prime * result + goldValue;
		result = prime * result + ((slot == null) ? 0 : slot.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemImpl other = (ItemImpl) obj;
		if (heroClassEquippable == null) {
			if (other.heroClassEquippable != null)
				return false;
		} else if (!heroClassEquippable.equals(other.heroClassEquippable))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (rarity != other.rarity)
			return false;
		if (slot == null) {
			if (other.slot != null)
				return false;
		} else if (!slot.equals(other.slot))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item name -> " + info + " -> state -> " + state + " -> mod ->" + mod;
	}

	
	
}
