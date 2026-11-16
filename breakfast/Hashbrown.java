package breakfast;

import java.util.Arrays;

public class Hashbrown {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object obj = new Object();
		System.out.println(obj.hashCode());
		
		Object obj2 = new Object();
		System.out.println(obj2.hashCode());
		String str = "Hello";
		System.out.println(str.hashCode());
		
		for(char c : str.toCharArray()) {
			System.out.println("" + c + ": " + (int)c);
		}
		//create HashMap
		McMap Glastonbury = new McMap();
		MenuItem hash = new MenuItem("Hashbrown", 'S');
		Info hashInfo = new Info(1.59, 140, 8);
		Glastonbury.put(new Purchase(hash, hashInfo));
		
		MenuItem McGriddle = new MenuItem("McGriddle", 'L');
		Info grinfo = new Info(5.89, 550, 7);
		Glastonbury.put(new Purchase(McGriddle, grinfo));
		/*
		System.out.println(Arrays.toString(Glastonbury.purchases));
		System.out.println(Glastonbury.get(new MenuItem("Hashbrown", 'S')));
		*/
		//test remove(key)
		System.out.println(Glastonbury.remove(McGriddle));
		System.out.println(Arrays.toString(Glastonbury.purchases));
		System.out.println(Glastonbury.size);
		
		for(int i = 0; i<14; i++) {
			MenuItem temp = new MenuItem("Food" + i, 'L');
			Info temp_info = new Info(1+i*20, 100 + i *10, 5);
			Glastonbury.put(new Purchase(temp, temp_info));
		}
		System.out.println(Glastonbury.purchases.length);
		System.out.println(Glastonbury.size);
		
		
		//iterate through Glastonbury McMap
		
		for(MenuItem m : Glastonbury) {
			System.out.println(m.name);
		}
		
	}

}
