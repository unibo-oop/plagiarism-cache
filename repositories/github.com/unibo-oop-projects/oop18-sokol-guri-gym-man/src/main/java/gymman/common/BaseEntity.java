package gymman.common;

import java.util.UUID;

public abstract class BaseEntity implements Entity {
	protected String id;
	
	public BaseEntity() {
		this.id = UUID.randomUUID().toString();
	}
	
	public BaseEntity(Entity entity) {
		this.id = entity.getId();
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BaseEntity)) {
			return false;
		}
		
		BaseEntity otherEntity = (BaseEntity)other;
		return this.getId().equals(otherEntity.getId());
	}
}
