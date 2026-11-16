package breakfast;

public class Purchase {//Entry ( [MenuItem | Info] )
	MenuItem key;
	Info value;
	
	public Purchase(MenuItem key, Info value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return key.size + " " + key.name + ": " + value + "]";
	}
	
	
}
