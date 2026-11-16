package HashMapLab;

import java.util.Objects;

public class PokemonKey {//pokemon key
	private int id;
	private int evo_set;/*pichu, pikachu, raichu share evo_set = 10*/
	
	public PokemonKey(int id, int evo_set) {
		this.id = id;
		this.evo_set = evo_set;
	}

	@Override
	public int hashCode() {
		return (id * 31) + (7*evo_set);
		}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PokemonKey other = (PokemonKey) obj;
		return this.id == other.id && this.evo_set==other.evo_set;
	}


	
	
}
