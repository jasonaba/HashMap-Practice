package breakfast;

import java.util.Objects;

public class MenuItem {
	String name;
	char size;

	public MenuItem(String name, char size) {
		super();
		this.name = name;
		this.size = size;
	}
	
	public MenuItem(String name) {
		this(name, 'X');
	}

	@Override
	public int hashCode() {
		return Math.abs(Objects.hash(name, Integer.valueOf(size)));//or else the hashcode could be negative
	}

	@Override
	public boolean equals(Object obj) {// when creating a hashcode, you can't use variables in the hashcode that aren't
										// used in the equals area (or else you might not be able to find the right
										// values when searching
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		return Objects.equals(name, other.name) && size == other.size;
	}

	
	
}
