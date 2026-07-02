package notwist.base;

import java.util.Date;

public interface Discussion {

	public String getTitle();
	
	public String getDescription();
	
	public int getIdUser();
	
	public int getIdDiscussion();
	
	public Date getData();
	
	public Category getCategory();
	
	@Override
	public String toString();

}
