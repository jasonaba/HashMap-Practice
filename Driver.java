package HashMapLab;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame("National Pokedex");
		PokePanel panel = new PokePanel();
		frame.add(panel);
		frame.setLocationRelativeTo(null);//centers
		frame.pack();//fits to panel size
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CustomHashMap pokedex = new CustomHashMap(100);
		
		Pokemon bulb = new Pokemon("bulbasaur",45,"{grass,poison}");
		PokemonKey bulb_k = new PokemonKey(1,1);
		Entry bulb_e = new Entry(bulb_k, bulb);
		pokedex.put(0, bulb_e);
		System.out.println(pokedex.getPokedex());
		System.out.println(pokedex.put(0, null));
		
		
	}

}