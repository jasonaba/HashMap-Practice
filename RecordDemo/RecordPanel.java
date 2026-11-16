package RecordDemo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class RecordPanel extends JPanel{
	JTextArea display = new JTextArea(20,60);
	//Where your map goes
	
	public RecordPanel() {
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		display.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		this.add(new JScrollPane(display), BorderLayout.CENTER);//if it goes over the borders, then you can scroll
		display.setEditable(false);
		display.setText("Hi");
		display.append("\nMy name is Jason");
		// overwrites  display.setText("blahblahblah");
		
		//make buttons that appear on screen that do things
		JPanel buttons = new JPanel(new GridLayout(0,1,5,5));//gridlayout(rows,columns,padding in pixels))
		//load file button
		buttons.add(makeButton("load file", e->{
			try {
				loadFile();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}));//e prolly the default for an action listener
		this.add(buttons,BorderLayout.EAST);
		
		buttons.add(makeButton("Get Record", e->getRecord()));
		
	}
	
	JButton makeButton(String name, ActionListener a) {
		JButton b = new JButton(name);
		b.addActionListener(a);
		return b;
	}
	
	 void loadFile() throws FileNotFoundException {
		display.setText("Loading...");
		JFileChooser file = new JFileChooser(new File(System.getProperty("user.dir")));
		file.showOpenDialog(file);
		Scanner scanner = new Scanner(file.getSelectedFile());
		while(scanner.hasNext()) {
			//take the whole row (creates an array), and gets first column (have to check all the columns for all the data)
			display.append(scanner.nextLine().split(",")[1]+"\n"); 
		}
		scanner.close();
		display.append("Loading complete!");
	}
	 
	 void getRecord() {
			display.append("Record");
		}
}
