package hollowmen.model.utils;


public class Counter {
	
	private int count;
	
	public Counter() {
		count = 0;
	}
	
	public Counter(int startValue) {
		count = startValue;
	}
	
	public void add(int i) {
		this.count += i;
	}
	
	public void sub(int i) {
		this.count -= i;
	}
	
	public void increase() {
		this.count++;
	}
	
	public void decrease() {
		this.count--;
	}
	
	public int getCount() {
		return this.count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
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
		Counter other = (Counter) obj;
		if (count != other.count)
			return false;
		return true;
	}
	
	
}
