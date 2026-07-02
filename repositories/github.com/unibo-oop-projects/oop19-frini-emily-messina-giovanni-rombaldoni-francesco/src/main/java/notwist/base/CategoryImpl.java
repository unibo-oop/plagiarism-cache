package notwist.base;

public class CategoryImpl implements Category {

	private Integer id;
	private String name;
	
	public CategoryImpl(final Integer id, final String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	public Integer getId() {
		return this.id;
	}
	@Override
	public String getName() {
		return this.name;
	}
	
}
