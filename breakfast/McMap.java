package breakfast;

import java.util.Iterator;

public class McMap implements Iterable<MenuItem> {

	Purchase[] purchases;
	int size;

	public static final double LOADFACTOR = .75;
	private static final Info tomb = new Info(0, 0, -1);

	public McMap() {
		super();
		this.purchases = new Purchase[20];
		size = 0;
	}

	public Info put(Purchase p) {
		int index = p.key.hashCode() % purchases.length;// finds index/key where the

		while (purchases[index] != null && !p.key.equals(purchases[index].key)) {
			index = ++index % purchases.length;// if the index is = to the length, then it will set it back to 0
		}

		if (purchases[index] == null) {
			purchases[index] = p;
			size++;
			if (1.0 * size / purchases.length > LOADFACTOR) {// rememba you can't be exactly equal to the loadfactor bc
																// doubles aren't precise
				this.resize();
			}
			return null;
		}

		Info temp = purchases[index].value;
		purchases[index] = p;
		size++;
		return temp;
	}

	public Info get(MenuItem m) {
		int index = m.hashCode() % purchases.length;
		while (purchases[index] != null && !purchases[index].key.equals(m)) {
			index = ++index % purchases.length;// if the index is = to the length, then it will set it back to 0
		}

		return purchases[index] == null || purchases[index].value == tomb ? null : purchases[index].value;
		// can't call null.value, so you have to have the conditional
	}

	public Info remove(MenuItem key) {
		int index = key.hashCode() % purchases.length;

		while (purchases[index] != null && !purchases[index].key.equals(key)) {
			index = ++index % purchases.length;
		}

		if (purchases[index] == null || purchases[index].value == tomb) {// can say "==" for tomb because it is a static
																			// object that every entry with tombstones
																			// point to
			return null;
		}

		Info temp = purchases[index].value;
		purchases[index].value = tomb;
		return temp;

	}

	private int resize() {
		McMap newHash = new McMap();
		newHash.purchases = new Purchase[purchases.length * 2];
		size = 0;
		for (Purchase p : purchases) {
			if (p != null && p.value != tomb) {
				newHash.put(p);
				size++;
			}
		}
		this.purchases = newHash.purchases;
		return purchases.length;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<MenuItem> iterator() {
		// TODO Auto-generated method stub
		return new McIterator();
	}

	private class McIterator implements Iterator<MenuItem> {
		int index;

		public McIterator() {
			index = 0;
			moveToValid();
		}

		public void moveToValid() {
			while (hasNext() && (purchases[index] == null || purchases[index].value == tomb)) {
				//as long as there is a next and the current is either null or a tombstone
				index++;
			}
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index < purchases.length;
		}

		@Override
		public MenuItem next() {
			// TODO Auto-generated method stub
			if(hasNext()) {
				MenuItem temp = purchases[index++].key;//moves to the next and saves the previous entry
				moveToValid();
				System.out.println("used our next()");
				return temp;
			}
			return null;
		}

	}

}
