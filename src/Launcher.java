import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Launcher {

	public static void main(final String[] args) {
		Scanner sc = new Scanner (System.in);
		int n = 4;
		System.out.print("Permainan puzzle ini akan di mainkan dengan 4X4 ubin");
		
	    	SwingUtilities.invokeLater(() -> {
	        	final JFrame frame = new JFrame();
	        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        	frame.setTitle("Sliding Puzzle");
	        	frame.setResizable(false);
	        	frame.add(new Main(n, 550, 30), BorderLayout.CENTER);
	      		frame.pack();
	      		frame.setLocationRelativeTo(null);
	      		frame.setVisible(true);
	    	});
	}   
}
