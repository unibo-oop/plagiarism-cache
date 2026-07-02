package hollowmen.model;

/**
 * This Interface is used by anyone who need to have an {@link Information}
 * @author pigio
 *
 */
public interface InformationUser {

	/**
	 * This method give an {@code Information} Object for this User
	 * @return {@link Information}
	 */
	public Information getInfo();
	
	
	public default boolean equals(String s) {
		return this.getInfo().getName().equals(s);
	}
	
	
	public default boolean equals(Information info) {
		return this.getInfo().equals(info);
	}
	
	
	public default boolean equals(InformationUser infoUser) {
		return this.getInfo().equals(infoUser.getInfo());
	}
}
