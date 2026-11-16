package HashMapLab;


public class Entry {//pokemon key, pokemon
	private PokemonKey key;
	private Pokemon value;

	//Constructor
	public Entry(PokemonKey k, Pokemon v) {
		key = k;
		value = v;
	}

	//Getters
	public PokemonKey getKey() {
		return key;
	}

	public Pokemon getValue() {
		return value;
	}
	
	//toString
	@Override
	public String toString() {
		return "Entry [key=" + key + ", value=" + value + "]";
	}

	
	
	
}