package RecordDemo;

import javax.swing.JFrame;

public class RecordFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new RecordPanel());
		frame.pack();
		frame.setVisible(true);
	}

}
