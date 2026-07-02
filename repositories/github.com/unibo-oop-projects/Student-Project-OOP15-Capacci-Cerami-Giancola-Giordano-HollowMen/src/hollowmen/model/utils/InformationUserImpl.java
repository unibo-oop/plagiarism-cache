package hollowmen.model.utils;

import hollowmen.model.Information;
import hollowmen.model.InformationUser;

public class InformationUserImpl implements InformationUser{

	private Information info;
		
	public InformationUserImpl(Information info) {
		this.info = info;
	}
	
	@Override
	public Information getInfo() {
		return this.info;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
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
		InformationUserImpl other = (InformationUserImpl) obj;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InformationUser [info=" + info + "]";
	}

	
	
}
