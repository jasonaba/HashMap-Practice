package HashMapLab;


public class CustomHashMap {
	private Node[] pokedex;// the list that pokemon are being held in
	private int size;
	private static final double MAX_LOADFACTOR = .75;
	private static final double MIN_LOADFACTOR = .1;

	public CustomHashMap(int list_cap) {
		this.pokedex = new Node[list_cap];
		size = 0;
	}

	public Node[] getPokedex() {
		return pokedex;
	}

	public void setPokedex(Node[] pokedex) {
		this.pokedex = pokedex;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	// loadfile() adds entrues to the map and displays only the keys on the screen

	/**
	 * put the pokemon in this specific spot in the list to demonstrate collisions
	 * 
	 * @param e - the entry of pokemon and the key
	 * @return the previous pokemon in that spot
	 */
	public Pokemon put(int index, Entry e) {
		if (index >= pokedex.length || index < 0) {
			System.out.println("That isn't a valid index!");
			return null;
		}

		if (e == null) {// should we not accept them putting a null pokemon into the pokedex?
			return null;
		}
		Pokemon output = null;
		if (pokedex[index] == null) {
			pokedex[index] = new Node(e);
			size++;// increment size since we aren't replacing a Pokemon
		} else {
			Node runner = pokedex[index];
			while (runner.getNext() != null && !runner.getData().getKey().equals(e.getKey())) {
				// traverse linked list of nodes until we can place this node at the end or if
				// we find the same key
				runner = runner.getNext();
			}
			if (!runner.getData().getKey().equals(e.getKey())) {// if we didn't land on an entry with the same key as e
				runner.setNext(new Node(e));
				size++;// increment size since we aren't replacing a Pokemon
				//don't get anything so it will output null
			}
			// replace the current node with this one if the key is the same
			else {
				output = runner.getData().getValue();//get the pokemon we replaced
				runner.setData(e);// we don't increment size because we replaced it
			}
		}
		return output;
	}

	public Pokemon put(Entry e) {
		if (e == null) {
			return null;
		}
		Pokemon output = null;
		int index = Math.abs(e.getKey().hashCode() % pokedex.length);
		if (pokedex[index] == null) {
			pokedex[index] = new Node(e);
			size++;// increment size s we aren't replacing a Pokemon
		} else {
			Node runner = pokedex[index];
			while (runner.getNext() != null && !runner.getData().getKey().equals(e.getKey())) {
				// traverse linked list of nodes until we can place this node at the end or if
				// we find the same key
				runner = runner.getNext();
			}
			if (!runner.getData().getKey().equals(e.getKey())) {// if we didn't land on an entry with the same key as e
				runner.setNext(new Node(e));
				size++;// increment size since we aren't replacing a Pokemon
				//don't get anything so it will output null
			}
			// replace the current node with this one if the key is the same
			else {
				output = runner.getData().getValue();//get the pokemon we replaced
				runner.setData(e);// we don't increment size because we replaced it
			}
		}
		return output;
	}

	public Pokemon get(PokemonKey k) {
		if (k == null)
			return null;
		int index = Math.abs(k.hashCode() % pokedex.length);// index of where it should be
		Node runner = pokedex[index];
		while (runner != null && !runner.getData().getKey().equals(k)) {
			// while the runner's key is not the same as the desired key and it isn't null
			runner = runner.getNext();
		}
		if (runner == null) {
			return null;
		}
		return runner.getData().getValue();// only occurs when we find the entry with the same key

	}

	public Pokemon remove(PokemonKey k) {
		if (k == null)
			return null;
		int index = Math.abs(k.hashCode() % pokedex.length);// index of where it should be
		Node runner = pokedex[index];
		Node previous = null;
		while (runner != null && !runner.getData().getKey().equals(k)) {
			// while the runner's key is not the same as the desired key and it isn't null
			previous = runner;
			runner = runner.getNext();
		}
		if (runner == null) {
			return null;// Pokemon with specified key isn't found
		}
		if (previous == null) {
			pokedex[index] = runner.getNext();
		} else {
			previous.setNext(runner.getNext());
		}
		runner.setNext(null);// so the list can't be accessed by this value anymore
		size--;// decrease size if we are removing a Pokemon

		return runner.getData().getValue();// returns the node we removed
	}

	/**
	 * Updates the HashMap to be not too large or small (based on the loadfactors)
	 */
	public void rehash() {
		if ((double) (size) / pokedex.length < MIN_LOADFACTOR) {//if the size gets too small
			CustomHashMap new_map = new CustomHashMap((int) (pokedex.length * .25));// create a new list that is approx
																					// 1/4 the size of the previous list
			for (int i = 0; i < pokedex.length; i++) {
				if (pokedex[i] == null)
					continue;
				Node runner = pokedex[i];
				while (runner != null) {// while there is still nodes left in the individual linked lists
					new_map.put(runner.getData());// does the hashing itself
					runner = runner.getNext();
				}
			}
			pokedex = new_map.getPokedex();
		}
		if ((double) (size) / pokedex.length > MAX_LOADFACTOR) {//If the size gets too large
			CustomHashMap new_map = new CustomHashMap(pokedex.length * 2);// create a new list with cap 2x of the
																			// previous list
			for (int i = 0; i < pokedex.length; i++) {// iterates through entire hashmap
				if (pokedex[i] == null)
					continue;
				Node runner = pokedex[i];
				while (runner != null) {// while there is still nodes left in the individual linked lists
					new_map.put(runner.getData());// does the hashing itself
					runner = runner.getNext();
				}
			}
			pokedex = new_map.getPokedex();
		}
	}

}