package HashMapLab;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

//PROBABLY REMOVE POKEPANEL AND MAKE CUSTOMHASHMAP THE PANEL! but check first before doing that

public class PokePanel extends JPanel {

	JTextArea display = new JTextArea(20, 60);
	CustomHashMap entries = new CustomHashMap(100);

	public PokePanel() {
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		display.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		this.add(new JScrollPane(display), BorderLayout.CENTER);
		display.setEditable(true);

		JPanel buttons = new JPanel(new GridLayout(0, 1, 5, 5));// (rows, columns, padding, padding)
		buttons.add(makeButton("Load File", e -> {

			try {

				loadFromFile();

			} catch (FileNotFoundException e1) {

				// TODO Auto-generated catch block

				e1.printStackTrace();

			}

		}));
		buttons.add(makeButton("Get Pokemon", e->getPokemon()));
		buttons.add(makeButton("Put Pokemon", e->);)
		this.add(buttons, BorderLayout.EAST);
	}

	public JButton makeButton(String name, ActionListener action) {
		JButton button = new JButton(name);
		button.addActionListener(action);// adds whatever action that button does to the button
		return button;
	}

	public void loadFromFile() throws FileNotFoundException {
		display.setText("Loading... \n");
		JFileChooser file = new JFileChooser(new File(System.getProperty("user.dir")));
		file.showOpenDialog(null);

		// go line by line through csv and get data
		Scanner scanner = new Scanner(file.getSelectedFile());
		scanner.nextLine();// skip first line

		

		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			ArrayList<String> dataList = new ArrayList<String>();
			StringBuilder currentData = new StringBuilder();
			boolean inQuotes = false;

			for (char c : line.toCharArray()) {
				if (c == '"') {// if it is a quote
					inQuotes = !inQuotes;
				} else if (c == ',' && !inQuotes) {// if it is a comma separated element
					dataList.add(currentData.toString().trim());// add that element to the list
					currentData.setLength(0);// resets the data we're looking at
				} else {
					currentData.append(c);// add that character to current data
				}
			}
			dataList.add(currentData.toString().trim());// adds the final one (since there is no comma at the end)

			
				PokemonKey p_k = new PokemonKey(Integer.parseInt(dataList.get(0)), Integer.parseInt(dataList.get(4)));
				Pokemon p = new Pokemon(dataList.get(1), Integer.parseInt(dataList.get(2)), dataList.get(3));
				Entry e = new Entry(p_k, p);
				entries.put(e);
			
		}
		display.append("Loading Complete!\n");
	}
	
	public void getPokemon() {
		//Text Panel
		JTextField p_id = new JTextField(15);
		JTextField p_evo_set = new JTextField(15);
		JPanel panel = new JPanel(new GridLayout(2,2,5,5));
		panel.add(new JLabel("Pokemon ID: "));
		panel.add(p_id);
		panel.add(new JLabel("Evolution Set Number: "));
		panel.add(p_evo_set);
		
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Enter Pokemon Information to Get Pokemon:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		 if(result == JOptionPane.OK_OPTION) {//create a PokemonKey from their input
			 PokemonKey p_k = null;
			 
			 //try to parse input
			 try {
			  p_k = new PokemonKey(Integer.parseInt(p_id.getText()), Integer.parseInt(p_evo_set.getText()));
			}catch (NumberFormatException ex) {
				display.append("Invalid information. Please enter integers for both the Pokemon's ID and Evolution Set. \n");
				return;
			}
			 //retrieve pokemon
			 Pokemon retrieved = entries.get(p_k);
			 if(retrieved!=null)
			 display.append(retrieved.toString()+"\n");
			 else
				 display.append("Pokemon not found.\n");
		 }
	}
	
	public void putPokemon() {
		//Text Panel
		String name = JOptionPane.showInputDialog(this, "Pokemon Name");
		String hp = JOptionPane.showInputDialog(this, "Pokemon HP");
		String type = JOptionPane.showInputDialog(this, "Pokemon Type (format: \"{type, type, type, etc}\"");
		String id = JOptionPane.showInputDialog(this, "Pokemon ID");
		String evo_set = JOptionPane.showInputDialog(this, "Pokemon Evolution Set");
		
		try {//try to put a pokemon in before 
		PokemonKey p_k = new PokemonKey(Integer.parseInt(id), Integer.parseInt(evo_set));
		Pokemon p = new Pokemon(name, Integer.parseInt(hp), type);
		Entry p_e = new Entry(p_k, p);
		
		Pokemon removed = entries.put(p_e);
		display.append("Removed Pokemon: " +removed.toString());
		
		}catch (NumberFormatException ex) {
			display.append("Invalid information. Please enter integers for the Pokemon's parameters. \n");
			return;
		}
		
		
		
	}
}